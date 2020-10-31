package us.spring.dayary.common.exception;

import us.spring.dayary.domain.Status;

public class DayaryException extends RuntimeException {
    static final long serialVersionUID = -1989121633042149827L;

    private Status status;

    public DayaryException(Status status) {
        super(status.name());
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}