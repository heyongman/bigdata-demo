package com.he;

/**
 * 实现排序
 */
public class User implements Comparable<User>{
    private String name;
    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 复写方法
     * @param o
     * @return
     */
    @Override
    public int compareTo(User o) {
        if (this.age.equals(o.age)){
            return this.name.compareTo(o.name);
        }else {
            return this.age-o.age;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
