package site.shug.spring.convert;

import java.beans.PropertyEditorSupport;

public class PropertyEditorUser extends PropertyEditorSupport {
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println("PropertyEditorUser.setAsText: " + text);
        String[] a = text.split(";");
        String name = a[0];
        int age = Integer.parseInt(a[1]);
        User user = new User();
        user.setName(name);
        user.setAge(age);
        setValue(user);
    }
}
