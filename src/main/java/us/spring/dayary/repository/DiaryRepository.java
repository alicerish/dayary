package us.spring.dayary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.spring.dayary.domain.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
