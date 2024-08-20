package site.shug.spring.autowire;

public class AutoWire1 {
    public AutoWire2 autoWire2;
    public  AutoWire1() {}
    public AutoWire1(AutoWire2 autoWire2) {
        System.out.println("AutoWire1");
        this.autoWire2 = autoWire2;
    }
    public AutoWire2 getAutoWire2() {
        return autoWire2;
    }
    public void setAutoWire2(AutoWire2 autoWire2) {
        System.out.println("setAutoWire2");
        this.autoWire2 = autoWire2;
    }

    @Override
    public String toString() {
        return "autowire1 hava " + autoWire2;
    }
}
