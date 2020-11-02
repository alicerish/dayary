package us.spring.dayary.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import us.spring.dayary.common.exception.DayaryException;
import us.spring.dayary.domain.Status;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DayaryErrorController implements ErrorController {

    @GetMapping("/error")
    public String error(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status == null) {
            throw new DayaryException(Status.BAD);
        }
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
        if (httpStatus == HttpStatus.NOT_FOUND) {//404
            return "forward:/error/error_404.html";
        } else {
            throw new DayaryException(Status.BAD);
        }
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}