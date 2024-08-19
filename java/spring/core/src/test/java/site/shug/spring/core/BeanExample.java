package site.shug.spring.core;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BeanExample {
    private String name;
    public Integer age;
    public BeanExampleDep dep;
    public List<Integer> list;
    public Set<Integer> set;
    public Map<String,Integer> map;

    public List<Integer> getList() {
        return list;
    }
    public void setList(List<Integer> list) {
        this.list = list;
    }
    public Set<Integer> getSet() {
        return set;
    }
    public void setSet(Set<Integer> set) {
        this.set = set;
    }
    public Map<String, Integer> getMap() {
        return map;
    }
    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }
    public BeanExampleDep getDep() {
        return dep;
    }
    public void setDep(BeanExampleDep dep) {
        this.dep = dep;
    }
    public Integer getAge() {
        System.out.println("getAge");
        return age;
    }
    public void setAge(Integer age) {
        System.out.println("setAge");
        this.age = age;
    }
    public String getName() {
        System.out.println("getName");
        return name;
    }
    public void setName(String name) {
        this.name = name;
        System.out.println("setName");
    }
    public BeanExample(String name, Integer age) {
        this.name = name;
        this.age = age;
        System.out.println("site.shug.sping.core.BeanExample(String name, Integer age)");
    }
    public BeanExample(String name, Integer age, BeanExampleDep dep, List<Integer> list, Set<Integer> set, Map<String,Integer> map) {
        this.name = name;
        this.age = age;
        this.dep = dep;
        this.list = list;
        this.set = set;
        this.map = map;
        System.out.println("site.shug.sping.core.BeanExample(String name, Integer age, BeanExampleDep dep, List<Integer> list, Set<Integer> set, Map<String,Integer> map)");
    }
    public BeanExample() {
        System.out.println("site.shug.sping.core.BeanExample");
    }
    public static BeanExample createInstance() {
        System.out.println("createInstance");
        return new BeanExample();
    }
    public static BeanExample createInstance1(String name, Integer age) {
        System.out.println("createInstance1");
        return new BeanExample(name, age);
    }

    @Override
    public String toString() {
        return "My name is " + name + " and age is " + age + " and dep is " + dep + " and list is " + list + " and set is " + set + " and map is " + map;
    }
}
