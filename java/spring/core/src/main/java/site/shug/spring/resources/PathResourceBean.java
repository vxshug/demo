package site.shug.spring.resources;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;


@Configurable
@Component
public class PathResourceBean {
    @Value("./")
    private PathResource resource;

    private String body;

    @PostConstruct
    public void init() {
        this.body = resource.getPath();
    }

    @Override
    public String toString() {
        return "PathResourceBean{" +
                "body='" + body + '\'' +
                '}';
    }
}
