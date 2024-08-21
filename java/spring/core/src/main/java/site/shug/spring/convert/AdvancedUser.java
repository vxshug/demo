package site.shug.spring.convert;

public class AdvancedUser {
    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "AdvancedUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
