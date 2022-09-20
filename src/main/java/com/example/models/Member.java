package com.example.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Member")
public class Member {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "owner")
    private List<Chat> chatsWhereMemberIsOwner;


    @ManyToOne
    @JoinTable(
            name = "chat_member",
            joinColumns = {@JoinColumn (name = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "chat_id")}
    )
    private Chat chatInWhichTheUser;


    public Member() {
    }

    public Member(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Member(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Chat> getChatsWhereMemberIsOwner() {
        return chatsWhereMemberIsOwner;
    }

    public void setChatsWhereMemberIsOwner(List<Chat> chatsWhereMemberIsOwner) {
        this.chatsWhereMemberIsOwner = chatsWhereMemberIsOwner;
    }

    public Chat getChatInWhichTheUser() {
        return chatInWhichTheUser;
    }

    public void setChatInWhichTheUser(Chat chatInWhichTheUser) {
        this.chatInWhichTheUser = chatInWhichTheUser;
    }
}
