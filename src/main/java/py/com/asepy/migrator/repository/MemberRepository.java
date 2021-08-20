package py.com.asepy.migrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.asepy.migrator.entity.MembersEntity;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MembersEntity, String> {
    Optional<MembersEntity> findFirstByRefId(Integer refId);
}
