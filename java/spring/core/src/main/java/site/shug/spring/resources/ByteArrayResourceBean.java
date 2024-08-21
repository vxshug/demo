package site.shug.spring.resources;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 这是给定字节数组的Resource实现。它为给定的字节数组创建一个ByteArrayInputStream 。
 * {@code @Value}注入的是{@code String}, 由于没有String转换成{code ByteArrayResource}的PropertyEditor, ConversionService, Converter, Formatter
 * 所有手动实现{@code  Converter<String, ByteArrayResource>} 将{@code String}转换成{@code ByteArrayResource}
 */
@Component
@Configurable
public class ByteArrayResourceBean implements Converter<String, ByteArrayResource> {
    @Value("ByteArrayResource")
    private ByteArrayResource resource;

    private String body;

    @PostConstruct
    public void init() throws IOException {
        this.body = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        return "ByteArrayResourceBean{" +
                "body='" + body + '\'' +
                '}';
    }

    @Override
    public ByteArrayResource convert(String source) {
        return new ByteArrayResource(source.getBytes(StandardCharsets.UTF_8));
    }
}
