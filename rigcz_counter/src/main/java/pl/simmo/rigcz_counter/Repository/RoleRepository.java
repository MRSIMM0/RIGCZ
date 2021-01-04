package pl.simmo.rigcz_counter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.simmo.rigcz_counter.Entity.Role;
import pl.simmo.rigcz_counter.Entity.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName);
}
