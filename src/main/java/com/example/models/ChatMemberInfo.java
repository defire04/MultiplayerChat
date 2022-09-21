package com.example.models;

import javax.persistence.*;

@Entity
@Table(name = "chat_member")
public class ChatMemberInfo {

    @Id
    @Column(name = "member_id")
    private int memberId;

    @Column(name = "chat_id")
    private int chatId;


}
