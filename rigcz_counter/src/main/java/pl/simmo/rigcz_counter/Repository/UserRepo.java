package pl.simmo.rigcz_counter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.simmo.rigcz_counter.Entity.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
   Optional<User> findUserByUsername(String username);
   Boolean existsByUsername(String username);
}
