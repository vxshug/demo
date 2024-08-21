package site.shug.spring.data.method;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class CustomUser {
    @NotContainBlank
    private String name;
    @Min(6)
    @Max(150)
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
        return "CustomUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
