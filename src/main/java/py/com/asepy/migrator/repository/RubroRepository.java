package py.com.asepy.migrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.asepy.migrator.entity.RubrosEntity;

import java.util.Optional;

public interface RubroRepository extends JpaRepository<RubrosEntity, Long> {
    Optional<RubrosEntity> getByDescription(String rubroDescription);
}
