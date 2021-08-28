package py.com.asepy.migrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.asepy.migrator.entity.DepartmentEntity;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    Optional<DepartmentEntity> getByName(String departmentName);
}
