package us.spring.dayary.common.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class DayaryRequestWrapper extends HttpServletRequestWrapper {

    private byte[] body;

    public byte[] getBody() {
        return body;
    }

    public DayaryRequestWrapper(HttpServletRequest request) {
        super(request);
        try {
            body = request.getInputStream().readAllBytes();
        } catch (IOException ex) {
            body = new byte[0];
        }
    }

    @Override
    public ServletInputStream getInputStream() {
        return new ServletInputStream() {
            ByteArrayInputStream bais = new ByteArrayInputStream(body);

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() {
                return bais.read();
            }
        };
    }
}