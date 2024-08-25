package site.shug.spring.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;
import site.shug.spring.common.dao.User;

import java.util.List;
import java.util.Set;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    // List可以替换成`Set`, `Iterable`, `Streamable`
    List<User> findByNameContaining(String username);
    // 异步查询结果
    // @Async
    //Future<User> findByFirstname(String firstname);
    //@Async
    //CompletableFuture<User> findOneByFirstname(String firstname);

    // 识别某些特定类型，例如Pageable 、 Sort和Limit ，以便动态地将分页、排序和限制应用于您的查询。
    Page<User> findByName(String name, Pageable pageable);

    // Slice<User> findByLastname(String lastname, Pageable pageable);
    // List<User> findByLastname(String lastname, Sort sort);
    // List<User> findByLastname(String lastname, Sort sort, Limit limit);
    // List<User> findByLastname(String lastname, Pageable pageable);
}

