package Backend.backend.repository;

import Backend.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
}
