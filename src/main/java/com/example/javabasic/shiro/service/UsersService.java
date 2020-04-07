package com.example.javabasic.shiro.service;

import com.example.javabasic.shiro.entity.Permission;
import com.example.javabasic.shiro.entity.Role;
import com.example.javabasic.shiro.entity.User;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author：Cheng.
 * @date：Created in 23:41 2020/2/25
 */
@Service
public class UsersService {


    public User getUserByName(String userName) {
        return getMapByName(userName);
    }

    public User getUserByName2(String userName) {
        return getMapByName2(userName);
    }

    public List<User> getUserByName3(String userName) {
        return getMapByName3(userName);
    }

    public List<User> getUserByNameAndPassword(String userName, String password) {
        User user = getMapByName(userName);
        List<User> users = new ArrayList<>();
        if(Objects.equals(user.getPassword(),password)){
            users.add(user);
        }
        return users;
    }

    public List<User> getUserByNameAndPassword2(String userName, String password) {
        User user = getMapByName2(userName);
        List<User> users = new ArrayList<>();
        if(Objects.equals(user.getPassword(),password)){
            users.add(user);
        }
        return users;
    }


    public List<User> getUserByNameAndPassword3(String userName, String password) {
        List<User> users = getMapByName3(userName);
        List<User> matchUsers = new ArrayList<>();
        users.forEach(user -> {if(Objects.equals(user.getPassword(),password)){
            matchUsers.add(user);
        }});
        return  matchUsers;
    }

    private User getMapByName(String userName){
        Permission permission1 = new Permission("1","query");
        Permission  permission2 = new Permission("2","add");

        Set<Permission> permissionSet1 = new HashSet<>();
        permissionSet1.add(permission1);
        permissionSet1.add(permission2);

        Role role1 = new Role("1","admin",permissionSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);

//        User user1 = new User(1L,"zhangsan","123456",roleSet1);
        Map<String, User> map = new HashMap<>();
//        map.put(user1.getUserName(), user1);

        Permission permission3 = new Permission("3","query");
        Set<Permission> permissionSet2 = new HashSet<>();
        permissionSet2.add(permission3);
        Role role2 = new Role("2","user",permissionSet2);
        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(role2);
        User user2 = new User(2L,"lisi","123456",roleSet1);
        map.put(user2.getUserName(), user2);

        User user3 = new User(3L,"zhang","123",roleSet1);
        map.put(user3.getUserName(), user3);
        return map.get(userName);
    }



    private User getMapByName2(String userName){
        Permission permission1 = new Permission("1","query");
        Permission  permission2 = new Permission("2","add");

        Set<Permission> permissionSet1 = new HashSet<>();
        permissionSet1.add(permission1);
        permissionSet1.add(permission2);

        Role role1 = new Role("1","admin",permissionSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);

        Map<String, User> map = new HashMap<>();


        User user2 = new User(4L,"wang","123",roleSet1);
        map.put(user2.getUserName(), user2);
        return map.get(userName);
    }


    private List<User> getMapByName3(String userName){
        Permission permission1 = new Permission("1","query");
        Permission  permission2 = new Permission("2","add");

        Set<Permission> permissionSet1 = new HashSet<>();
        permissionSet1.add(permission1);
        permissionSet1.add(permission2);

        Role role1 = new Role("1","admin",permissionSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);

        Map<String, User> map = new HashMap<>();


        User user1 = new User(5L,"zhang@163.com","123",roleSet1);
        map.put(user1.getUserName(), user1);

        Set<User> users = (Set<User>) map.values();
        List<User> matchUsers = new ArrayList<>();
        users.forEach(user -> {if(user1.getUserName().contains(userName)){
            matchUsers.add(user);
        }});
        return matchUsers;
    }
}
