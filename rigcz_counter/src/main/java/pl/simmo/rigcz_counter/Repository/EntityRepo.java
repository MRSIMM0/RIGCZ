package pl.simmo.rigcz_counter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.simmo.rigcz_counter.Entity.DbEntity;
import pl.simmo.rigcz_counter.Entity.User;

import java.util.List;

public interface EntityRepo extends JpaRepository<DbEntity,Long> {
   List<DbEntity> findByCreatedBy(User username);
}
