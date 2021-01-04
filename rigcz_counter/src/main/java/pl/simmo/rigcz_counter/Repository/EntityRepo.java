package pl.simmo.rigcz_counter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.simmo.rigcz_counter.Entity.DbEntity;

public interface EntityRepo extends JpaRepository<DbEntity,Long> {
}
