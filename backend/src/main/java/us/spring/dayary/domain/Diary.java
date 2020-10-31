package us.spring.dayary.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "DIARY")
public class Diary {

    @Id
    @Column(name = "SEQ")
    private long seq;

    @Column(name = "MEMBER_SEQ")
    private String memberSeq;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "REG_DATE")
    private long regDate;

    @Column(name = "ACTIVATION")
    private char activation;
}
