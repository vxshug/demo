package site.shug.spring.resources;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Configurable
@Component
public class FileSystemResourceBean {
    @Value("build.gradle.kts")
    private FileSystemResource resource;

    private String body;

    @PostConstruct
    public void init() throws IOException {
        this.body = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        return "FileSystemResourceBean{" +
                "body='" + body + '\'' +
                '}';
    }
}
