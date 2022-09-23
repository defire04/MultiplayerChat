package com.example.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Chat")
public class Chat {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Member owner;

    @OneToMany
    @JoinTable(
            name = "chat_messages",
            joinColumns = {@JoinColumn (name = "chat_id")},
            inverseJoinColumns = {@JoinColumn(name = "message_id")}
    )
    private List<Message> messages;

    @OneToMany
    @JoinTable(
            name = "chat_member",
            joinColumns = {@JoinColumn (name = "chat_id")},
            inverseJoinColumns = {@JoinColumn(name = "member_id")}
    )
    private List<Member> members = new ArrayList<>();

    public Chat() {
    }

    public Chat(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public boolean isMemberInChat(Member member){
       return this.members.contains(member);
    }

}
