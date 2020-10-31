package us.spring.dayary.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "SEQ")
    private long seq;

    @Column(name = "ID")
    private String id;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "SIGNATURE")
    private String signature;

    @Column(name = "REG_DATE")
    private long regDate;

    @Column(name = "ACTIVATION")
    private char activation;
}
