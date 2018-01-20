package com.fortune.model;

import javax.persistence.*;
import java.util.Set;

/**
 * @author fchidzikwe
 */

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToOne
    private  User messageFrom;


    @OneToOne
    private User messageTo;


    private Integer readed;


    private String header;



    @Column(name="text", length=1512)
    private String text;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRead() {
        return readed;
    }

    public void setRead(int readed) {
        this.readed = readed;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public User getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(User messageFrom) {
        this.messageFrom = messageFrom;
    }

    public User getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(User messageTo) {
        this.messageTo = messageTo;
    }

    public Integer getReaded() {
        return readed;
    }

    public void setReaded(Integer readed) {
        this.readed = readed;
    }

    public void setRead(Integer readed) {
        this.readed = readed;
    }
}
