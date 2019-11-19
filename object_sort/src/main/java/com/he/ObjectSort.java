package com.he;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectSort {
    public static void main(String[] args) {
        User u1 = new User("tony", 19);
        User u2 = new User("jack", 16);
        User u3 = new User("tom", 80);
        User u4 = new User("jbson", 44);
        User u5 = new User("jason", 44);

        List<User> userList = new ArrayList<>();
        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        userList.add(u4);
        userList.add(u5);
//          进行排序，实体类中定义排序方法
        Collections.sort(userList);

        for (User u:userList) {
            System.out.println("年龄：" + u.getAge() + "  姓名：" + u.getName());
        }

    }
}
