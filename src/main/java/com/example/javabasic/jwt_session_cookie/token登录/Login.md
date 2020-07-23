JWT Token登录
   通讯录平台
   main服务：
        1.校验accountName对应的用户是否存在，以及用户的状态是否有效
        2.校验企业license数量有无超出
        3.调用Account Client判断用户名密码是否正确
        4.Account逻辑
            1）.获取登录人账号名和客户端标识（web端or客户端）
            2）.校验客户端登录的id和client secret是否正确，校验企业license数量有没有超出；
            3）.检查账户名对应的用户是否存在
            4）.使用查询到的entity检验账号密码是否正确
                使用Spring security中  org.springframework.security.crypto.bcrypt
                校验请求中传过来的密码与加盐加密之后的密码是否一致
            5）.更新账户登录信息
                登录时间  登录次数  登录持续天数
                新知识：LocateDate的用法
                    public AccountEntity updateAccountDetail(AccountEntity accountEntity) {
                        log.trace("更新账户登录时间等信息开始");
                        AccountDetailEntity accountDetailEntity = accountEntity.getAccountDetail();
                        accountDetailEntity.setLoginTimes(accountDetailEntity.getLoginTimes() + 1);
                        DateTime lastLoginTime = accountDetailEntity.getLastLoginTime();
                        Integer lct = accountDetailEntity.getLoginContinuousTimes();
                        if (Objects.isNull(lct)) {
                            // 持续登录天数未赋值，置为 0
                            lct = 0;
                        }
                        int loginContinuousTimes;
                        if (Objects.isNull(lastLoginTime)) {
                            // 从没有登录过，持续登录天数置为 1
                            loginContinuousTimes = 1;
                        } else if (LocalDate.now().isEqual(lastLoginTime.toLocalDate())) {
                            // 上次登录是同一天
                            loginContinuousTimes = lct;
                        } else if (LocalDate.now().minusDays(1).isEqual(lastLoginTime.toLocalDate())) {
                            // 上次登录是前一天
                            loginContinuousTimes = lct + 1;
                        } else {
                            // 上次登录是一天前，重置持续登录天数为 0
                            loginContinuousTimes = 0;
                        }
                        accountDetailEntity.setLoginContinuousTimes(loginContinuousTimes);
                        accountDetailEntity.setLastLoginTime(dateTimeService.getDateTimeNow());
                        AccountEntity saved = accountRepository.save(accountEntity);
                        log.trace("保存账户登录时间信息结束");
                        return saved;
                    }
            6）返回账号的基本信息和详细信息
        5.返回用户可以访问的token menus roles permissions
            获取用户的角色；
            根据角色获取用户具有的权限；
            根据权限获取用户可以访问的菜单；
            获取用户所属的企业 部门信息；
            创建访问token和刷新token
   **1>创建访问token**
                1)使用java.security.KeyPair获取私钥:ECPrivateKey privateKey = (ECPrivateKey) this.keyPair.getPrivate();
                2)使用私钥创建一个签名器JWSSigner:JWSSigner signer = new ECDSASigner(privateKey);
                    这里是使用的椭圆曲线算法生成的签名算法
                3）生成token载荷，其中包括生成token的过期时间，签发token人，面向用户等属性
                   subject--设置面向用户，一般设置为当前登录账户；
                   issuser---token签发者；
                   expirationTime----token过期时间
                   audience-----受众群体：在通讯录平台中设置了一个静态字符串
                   其他需要在token中存储的参数，通过claim进行声明，如userId  enterpriseId accountName permissions roles等信息
                4）生成JWT的头部
                    JWT头部包含签名所用的加密算法
                    new JWSHeader.Builder(JWSAlgorithm.ES512).keyID(configs.getKeyId()).build()
                    keyID()中设置的私钥id是如何获取的？？
                5）使用签名器和私钥对JWT头部和载荷进行签名，最后序列化为字符串即可

                private InternalTokenItem createAccessToken(
                            InternalToken infos, Collection<String> roles, Collection<String> permissions)
                            throws JOSEException {
                        log.debug("生成 access token");

                        // Get private + public EC key
                        ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();

                        // Create the ECDSA signer
                        JWSSigner signer = new ECDSASigner(privateKey);
                        //生成token的过期时间
                        DateTime expires = DateTime.now().plusMinutes(configs.getAccessTokenExpiresIn());
                        // Prepare JWT with claims set
                        JWTClaimsSet claimsSet =
                                new JWTClaimsSet.Builder()
                                        .jwtID(infos.getTokenId())
                                        //subject--设置面向用户，一般设置为当前登录账户
                                        .subject(infos.getAccountId())
                                        //issuser---token签发者
                                        .issuer(JWT_VALUE_ISS)
                                        .expirationTime(expires.toDate())
                                        .audience(JWT_VALUE_AUD)
                                        .claim(JWT_KEY_USER_ID, infos.getUserId())
                                        .claim(JWT_KEY_ACCOUNT_NAME, infos.getAccountName())
                                        .claim(JWT_KEY_ENTERPRISE_ID, infos.getEnterpriseId())
                                        .claim(JWT_KEY_CLIENT_ID, infos.getClientId())
                                        .claim(JWT_KEY_ROLES, roles)
                                        .claim(JWT_KEY_AUTHORITIES, permissions)
                                        .build();
                        //代签名的JWT头部和载荷
                        SignedJWT signedJwt =
                                new SignedJWT(
                                        new JWSHeader.Builder(JWSAlgorithm.ES512).keyID(configs.getKeyId()).build(),
                                        claimsSet);
                        log.debug("对 access token 进行签名");
                        // Compute the EC signature
                        signedJwt.sign(signer);

                        // Serialize the JWS to compact form
                        String token = signedJwt.serialize();
                        log.debug("生成 access token 成功");
                        return InternalTokenItem.builder().token(token).expiresIn(expires.getMillis()).build();
                    }


   **2>创建刷新token**
               刷新token与访问token的不同之处：
               1.过期时间不同，刷新token的过期时间要更长一点，通讯录平台，访问token的过期时间为5分钟，刷新token的过期时间为15分钟
               2.刷新token的字符串长度会小一些，因为之前访问token中包含的role permission等信息，前端可以保存在本地
   返回给前端的TokenItem中为什么还添加了一个属性用来存储token的过期时间？

   Token在数据库中的存储刷新token的目的----待验证


Token过期之后，刷新token接口: 刷新token接口的意义在于使用访问token对访问token进行刷新

   1.判断刷新token的有效性：
        1).将refreshToken由字符串转换为SignedJWT对象，使用公钥验证token签名
        2).获取刷新token的过期时间，判断刷新token是否过期；
        3).获取刷新token的tokenId,根据tokenId userId clientId判断刷新token在数据库中是否存在，不存在则提示用户重新登录
        4).存在则判断刷新token的状态是否为有效状态,存在则校验通过

        public JWTClaimsSet checkRefreshToken(String refreshToken, String userId, String clientId) {
                JWTClaimsSet claims = parseToken(refreshToken);
                DateTime expired = JwtUtils.expirationTime(claims);
                if (Objects.isNull(expired) || DateTime.now().isAfter(expired)) {
                    throw new JwtRefreshTokenExpiredException("刷新 Token 已过期，请重新登录", expired);
                }
                String tokenId = claims.getJWTID();
                if (Objects.isNull(tokenId)) {
                    throw new JwtRefreshTokenInvalidException("刷新 Token 不正确，请重新登录");
                }
                TokenEntity token =
                        findByTokenIdOptional(tokenId, userId, clientId)
                                .orElseThrow(
                                        () -> new JwtRefreshTokenInvalidException("刷新 Token 不存在，请重新登录"));
                if (!TOKEN_STATUS_AVAILABLE.equals(token.getTokenStatus())) {
                    throw new JwtRefreshTokenInvalidException("刷新 Token 状态不正确，请重新登录");
                }
                return claims;
            }

            private JWTClaimsSet parseToken(String refreshToken) {
                    //1.获得公钥
                    ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
                    SignedJWT jwsObject;
                    try {
                        //2.获得刷新token
                        jwsObject = SignedJWT.parse(refreshToken);
                        //3.因为前端传回来的是之前使用私钥签名过的刷新token,使用公钥验证刷新Token
                        jwsObject.verify(new ECDSAVerifier(publicKey));
                        //4.返回刷新token的负载
                        return jwsObject.getJWTClaimsSet();
                    } catch (ParseException | JOSEException e) {
                        throw new JwtTokenParseException("Refresh Token 解析失败", e, refreshToken);
                    }
                }


   2.根据userId  versionId 获取用户的权限 角色等信息，生成一个新的TokenItem
        1).获取刷新token的过期剩余时间
        2）判断刷新token的剩余过期时间是否小于refreshToken过期时间阈值
            如果剩余时间小于这个阈值，则会重新生成TokenItem，包含新的访问token和新的刷新token
            只是生成一个新的访问token,拼接为一个TokenItem进行返回

        @Transactional(rollbackFor = RuntimeException.class)
            public TokenItem refresh(
                    InternalToken infos,
                    String refreshToken,
                    JWTClaimsSet claims,
                    Collection<String> roles,
                    Collection<String> permissions) {
                DateTime expired = JwtUtils.expirationTime(claims);
                //获取当前refreshToken的过期剩余时间，为什么+1？
                long expiredIn = (expired.getMillis() - System.currentTimeMillis()) / (1000 * 60) + 1;
                //判断刷新token的剩余过期时间是否小于refreshToken的过期时间阈值
                //如果剩余时间小于这个阈值，则会重新生成TokenItem，包含新的访问token和新的刷新token,否则的话只是生成新的访问token
                if (expiredIn <= configs.getRefreshTokenExpiresThreshold()) {
                    return createTokenItem(infos, roles, permissions);
                }
                InternalTokenItem accessToken;
                try {
                    String tokenId = claims.getJWTID();
                    infos.setTokenId(tokenId);
                    accessToken = createAccessToken(infos, roles, permissions);
                } catch (JOSEException e) {
                    throw new JwtTokenGenerateException("JWT Token 生成失败", e);
                }
                TokenItem rtn = new TokenItem();
                rtn.setRefreshToken(refreshToken);
                rtn.setAccessToken(accessToken.getToken());
                rtn.setExpiresIn(accessToken.getExpiresIn());

                return rtn;
            }


GateWay如何对token进行验证