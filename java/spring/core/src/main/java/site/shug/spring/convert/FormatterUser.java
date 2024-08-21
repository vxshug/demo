package site.shug.spring.convert;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;


public class FormatterUser implements Formatter<User> {

    @Override
    public User parse(String text, Locale locale) throws ParseException {
        System.out.println("Formatter.parse: text=" + text + ", locale=" + locale.toString());
        String[] arr = text.split(";");
        User user = new User();
        user.setName(arr[0]);
        user.setAge(Integer.parseInt(arr[1]));
        return user;
    }

    @Override
    public String print(User object, Locale locale) {
        System.out.println("Formatter.print: object=" + object + ", locale=" + locale.toString());
        return object.toString();
    }
}
