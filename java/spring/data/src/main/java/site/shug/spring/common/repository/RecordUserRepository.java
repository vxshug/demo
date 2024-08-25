package site.shug.spring.common.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import site.shug.spring.common.dao.RecordUser;

@Repository
public interface RecordUserRepository extends CrudRepository<RecordUser, Long> {
}
