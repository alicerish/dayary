package us.spring.dayary.service;

import org.springframework.stereotype.Service;
import us.spring.dayary.repository.DiaryRepository;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

}
