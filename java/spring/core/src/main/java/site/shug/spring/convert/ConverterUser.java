package site.shug.spring.convert;

import org.springframework.core.convert.converter.Converter;

public class ConverterUser implements Converter<User, AdvancedUser> {

    @Override
    public AdvancedUser convert(User source) {
        AdvancedUser advancedUser = new AdvancedUser();
        advancedUser.setName(source.getName());
        advancedUser.setAge(source.getAge());
        return advancedUser;
    }
}
