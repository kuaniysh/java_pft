package ru.stqa.pft.mantis.model;

/**
 * Created by kuanysh on 24.04.16.
 */
public class MailMessage {
    public String to;
    public String text;

    public MailMessage(String to, String text){
        this.to = to;
        this.text =  text;
    }
}