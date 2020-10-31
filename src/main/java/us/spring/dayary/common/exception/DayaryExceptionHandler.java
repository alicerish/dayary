package us.spring.dayary.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import us.spring.dayary.common.interceptor.DayaryLogPrinter;
import us.spring.dayary.domain.Status;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestControllerAdvice
public class DayaryExceptionHandler {

    private final DayaryLogPrinter dayaryLogPrinter;

    public DayaryExceptionHandler(DayaryLogPrinter dayaryLogPrinter) {
        this.dayaryLogPrinter = dayaryLogPrinter;
    }

    @ExceptionHandler(DayaryException.class)
    public ResponseEntity boxException(Exception e, HttpServletRequest request) {
        DayaryException de = (DayaryException) e;
        dayaryLogPrinter.printException(request, de.getStatus());
        return new ResponseEntity<>(new HashMap<>() {{
            put("status", de.getStatus());
        }}, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity CommonException(Exception e) {
        dayaryLogPrinter.printStackTrace(e);
        return new ResponseEntity<>(new HashMap<>() {{
            put("status", Status.BAD);
        }}, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
