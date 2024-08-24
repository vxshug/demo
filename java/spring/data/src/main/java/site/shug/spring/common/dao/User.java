package site.shug.spring.common.dao;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class User {
    @Id
    private Long id;
    private @AccessType(AccessType.Type.PROPERTY) String name; // 使用setName方法设置值
    private @AccessType(AccessType.Type.FIELD) String nickname; // 使用字段访问设置值
    private int age;
    //    @PersistenceCreator
    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        System.out.println("有参构造函数: " + this);
    }
    public User() {
        System.out.println("User 无参构造函数");
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
                ", age=" + age +
                '}';
    }
}
