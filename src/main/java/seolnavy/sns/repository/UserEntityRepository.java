package seolnavy.sns.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import seolnavy.sns.model.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

	Optional<UserEntity> findByUserName(String userName);

}
