package site.shug.spring.resources;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.InputStreamResource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * InputStreamResource是给定InputStream的Resource实现。
 * 仅当没有适用的特定Resource实现时才应使用它。特别是，尽可能首选ByteArrayResource或任何基于文件的Resource实现。
 * 实现{@code Converter<String, InputStreamResource>}并注入到Spring容器
 */
public class InputStreamResourceBean implements Converter<String, InputStreamResource> {
    @Value("build.gradle.kts")
    private InputStreamResource resource;

    private String body;

    @PostConstruct
    public void init() throws IOException {
        this.body = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        return "InputStreamResourceBean{" +
                "body='" + body + '\'' +
                '}';
    }

    @Override
    public InputStreamResource convert(String source) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(source);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new InputStreamResource(inputStream);
    }
}
