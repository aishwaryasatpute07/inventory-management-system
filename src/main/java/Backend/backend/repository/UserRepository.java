package Backend.backend.repository;

import Backend.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserEmailIgnoreCase(String userEmail);
    boolean existsByUserEmail(String userEmail);
    // Use @Query to safely query isUser without changing the field
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    Optional<User> login(@Param("username") String username, @Param("password") String password);
}
