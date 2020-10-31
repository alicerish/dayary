package us.spring.dayary.controller;

import org.springframework.stereotype.Controller;
import us.spring.dayary.service.DiaryService;

@Controller
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

}
