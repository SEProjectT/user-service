CREATE TABLE IF NOT EXISTS users (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    username varchar(64) UNIQUE NOT NULL,
    preferred_contact varchar(32) NOT NULL,
    email varchar(64) UNIQUE NOT NULL,
    phone varchar(32) UNIQUE NOT NULL
);

INSERT INTO users (username, preferred_contact, email, phone) VALUES ('user1', 'SMS', 'email1', 'phone1');
INSERT INTO users (username, preferred_contact, email, phone) VALUES ('user2', 'EMAIL', 'email2', 'phone2');
INSERT INTO users (username, preferred_contact, email, phone) VALUES ('user3', 'TELEGRAM', 'email3', 'phone3');