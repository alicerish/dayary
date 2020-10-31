package us.spring.dayary.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.spring.dayary.domain.Status;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@Component
public class DayaryLogPrinter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String HEAD = "┏━━━━━━━━━━━━━━━━━━━━━━";
    private final String BODY = "┃";
    private final String FOOT = "┗━━━━━━━━━━━━━━━━━━━━━━";
    private final String NEW_LINE = "\n";
    private final String PADDING_EXCEPTION = "%10s";

    public void printException(HttpServletRequest httpServletRequest, Status status) {
        String code = (String) httpServletRequest.getAttribute("code");
        String log = "";
        log += NEW_LINE;
        log += HEAD + NEW_LINE;
        log += BODY + String.format(PADDING_EXCEPTION, "BOX: ") + "EXCEPTION" + NEW_LINE;
        log += BODY + String.format(PADDING_EXCEPTION, "Code: ") + code + NEW_LINE;
        log += BODY + String.format(PADDING_EXCEPTION, "Cause: ") + status.name() + NEW_LINE;
        log += BODY + String.format(PADDING_EXCEPTION, "Message: ") + status.getMessage() + NEW_LINE;
        log += FOOT;
        logger.error(log);
    }

    public void printStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        String message = sw.toString();
        logger.info(message);
    }
}