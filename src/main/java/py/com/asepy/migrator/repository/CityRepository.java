package py.com.asepy.migrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.asepy.migrator.entity.CityEntity;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
    Optional<CityEntity> getByName(String cityName);
}
