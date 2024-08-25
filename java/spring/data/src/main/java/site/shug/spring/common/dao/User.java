package site.shug.spring.common.dao;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

// 用于配置从类到数据库表的映射的注释。
@Table
public class User {
    // 标识主键
    @Id
    private final Long id;
    private @AccessType(AccessType.Type.PROPERTY) String name; // 使用setName方法设置值
    private @AccessType(AccessType.Type.FIELD) String nickname; // 使用字段访问设置值
    // 默认使用字段访问设置值
    private int age;

    // 忽略password字段
    @Transient
    private String password;

    // 无参数的工厂函数
    @PersistenceCreator
    public static User userFactory() {
        System.out.println("工厂函数");
        return new User(1L);
    }

    // 带参数的工厂函数
    // @PersistenceCreator
    public static User userFactory1(String name) {
        System.out.println("userFactory1工厂函数: name = " + name);
        return new User(1L, name, 1);
    }


    // @PersistenceCreator
    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        System.out.println("有参构造函数: " + this);
    }
    public User(Long id) {
        this.id = id;
    }

    public User withId(Long id) {
        System.out.println("withId: " + id);
        return new User(id, name, age);
    }
    public void setAge(int age) {
        System.out.println("注入age属性: " + age);
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        System.out.println("注入name属性: " + name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}