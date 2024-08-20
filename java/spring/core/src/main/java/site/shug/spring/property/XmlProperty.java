package site.shug.spring.property;

public class XmlProperty {
    private String name;
    private int age;

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "XmlProperty{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
