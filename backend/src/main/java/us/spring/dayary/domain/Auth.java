package us.spring.dayary.domain;

import lombok.Data;

@Data
public class Auth {
    private long seq;
    private long dateOfIssue;
    private long dateOfExpiry;
    private String signature;
}
