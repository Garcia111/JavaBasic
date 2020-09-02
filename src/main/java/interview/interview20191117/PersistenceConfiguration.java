/*
 * Copyright (C) 2018 China Telecom System Integration Co., Ltd.
 * All rights reserved.
 */
package interview.interview20191117;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created on 2018/5/30.
 *
 * @author cuiwei
 * @since 1.0.0
 */
@Component
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
//@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@EnableJpaAuditing()
public class PersistenceConfiguration {

//    private static final String PROPERTY_NAME_DATABASE_DRIVER = "spring.datasource.driver-class-name";
//    private static final String PROPERTY_NAME_DATABASE_URL = "spring.datasource.url";
//    private static final String PROPERTY_NAME_DATABASE_USERNAME = "spring.datasource.username";
//    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "spring.datasource.password";

//    @Resource
//    private Environment environment;
//
//
//    /**
//     * Date time provider date time provider.
//     *
//     * @return the date time provider
//     */
//    @Bean
//    public DateTimeProvider dateTimeProvider(DateTimeService dateTimeService) {
//        return dateTimeService::getNow;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//        dataSource.setDriverClassName(environment
//                .getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
//        dataSource.setUrl(environment
//                .getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
//        dataSource.setUsername(environment
//                .getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
//        dataSource.setPassword(environment
//                .getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
//
//        return dataSource;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        EntityManagerFactory factory = entityManagerFactory();
//        JpaTransactionManager manager = new JpaTransactionManager(factory);
//        return manager;
//    }
//
//
//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(Boolean.TRUE);
//        vendorAdapter.setShowSql(Boolean.TRUE);
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("interview.interview20191117");
//        factory.setJpaDialect(new DefaultJpaDialect());
//        factory.setDataSource(dataSource());
//        factory.afterPropertiesSet();
//        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
//        return factory.getObject();
//    }
//
//    @Bean
//    public HibernateExceptionTranslator hibernateExceptionTranslator() {
//        return new HibernateExceptionTranslator();
//    }
}
