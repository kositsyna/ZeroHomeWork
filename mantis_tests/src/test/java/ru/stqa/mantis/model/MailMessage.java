package ru.stqa.mantis.model;

public record MailMessage(String from, String content) {
    public MailMessage (){
        this ("","");//конструктор без параметров
    }
    public MailMessage withFrom (String from){
        return new MailMessage(from, this.content);
    }
    public MailMessage  withContent (String content){
        return new MailMessage(this.from, content);
    }
}
