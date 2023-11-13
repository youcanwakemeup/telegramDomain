-- liquibase formatted sql

-- changeset asharipov:0.1 USERS TABLE CREATED
CREATE TABLE users (
    telegram_chat_id INTEGER PRIMARY KEY,
    last_message_at TIMESTAMP
);

-- changeset asharipov:0.2 MESSAGES TABLE CREATED
CREATE TABLE messages (
    message_id SERIAL PRIMARY KEY,
    telegram_chat_id INTEGER,
    message_text TEXT,
    sent_at TIMESTAMP,
    FOREIGN KEY (telegram_chat_id) REFERENCES users(telegram_chat_id)
);

-- changeset asharipov:0.3 DAILY_DOMAINS TABLE CREATED
CREATE TABLE daily_domains (
    domain_name VARCHAR(255) PRIMARY KEY,
    hotness INTEGER,
    price INTEGER,
    links INTEGER,
    visitors INTEGER,
    old INTEGER,
    delete_date DATE,
    "block" BOOLEAN
);


