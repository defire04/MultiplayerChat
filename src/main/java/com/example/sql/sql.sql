DROP TABLE Member;
CREATE TABLE Member(
  id  INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  username VARCHAR(100) NOT NULL,
  password VARCHAR NOT NULl,
  role VARCHAR(100) NOT NULL
);
SELECT * FROM Member;




DROP TABLE Chat;
CREATE TABLE Chat(
  id  INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  owner_id INT REFERENCES Member(id) ON DELETE SET NULL,
  name VARCHAR(100) NOT NUll,
  created_at TIMESTAMP,
  description VARCHAR
);
SELECT * FROM Chat;


INSERT INTO Chat (owner_id, name, created_at) VALUES (1, 'What is homework today', TIMESTAMP '2004-10-19 10:23:54') ;
INSERT INTO Chat (owner_id, name, created_at) VALUES (1, 'Test', TIMESTAMP '2010-10-19 10:23:54') ;




DROP TABLE Message;
CREATE TABLE Message(
  id  INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  user_id INT REFERENCES Member(id) ON DELETE SET NULL,
  chat_id INT REFERENCES Chat(id) ON DELETE SET NULL,
  text VARCHAR NOT NULL,
  sent_at TIMESTAMP,
  members_username VARCHAR NOT NULL
);


SELECT * FROM Message;

DROP TABLE chat_member;
CREATE TABLE chat_member(
    member_id INT REFERENCES Member(id) ON DELETE SET NULL,
    chat_id INT REFERENCES Chat(id) ON DELETE SET NULL,
    primary key (member_id)
);
SELECT * FROM chat_member;




DROP TABLE chat_messages;

CREATE TABLE chat_messages(
    chat_id INT REFERENCES Chat(id) ON DELETE SET NULL,
    message_id INT REFERENCES Message(id) ON DELETE SET NULL,
    primary key (message_id)
);

SELECT * FROM chat_messages;



DROP TABLE chat_member;
DROP TABLE chat_messages;
DROP TABLE message;
DROP TABLE chat;
