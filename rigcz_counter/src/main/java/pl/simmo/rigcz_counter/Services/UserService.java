package pl.simmo.rigcz_counter.Services;

import pl.simmo.rigcz_counter.Entity.User;

import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> find(Long id);

    Optional<User> findByUsername(String username);
}
