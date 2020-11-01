package us.spring.dayary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.spring.dayary.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findById(String id);
}
