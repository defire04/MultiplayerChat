package com.example.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Message")
public class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "chat_id")
    private int chatId;

    @Column(name = "text")
    private String text;

    @Column(name = "sent_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentAt;

    @Column(name = "members_username")
    private String username;

    @ManyToOne
    @JoinTable(
            name = "chat_messages",
            joinColumns = {@JoinColumn (name = "message_id")},
            inverseJoinColumns = {@JoinColumn(name = "chat_id")}
    )
    private Chat chat;

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Message() {
    }

    public Message(int userId, int chatId, String text, String username) {
        this.userId = userId;
        this.chatId = chatId;
        this.text = text;
        this.sentAt = new Date();
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
