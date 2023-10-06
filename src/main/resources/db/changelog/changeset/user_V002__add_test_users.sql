INSERT INTO users (username, preferred_contact, email, phone)
VALUES
    ('user1', 'SMS', 'email1', 'phone1'),
    ('user2', 'EMAIL', 'email2', 'phone2'),
    ('user3', 'TELEGRAM', 'email3', 'phone3')
ON CONFLICT DO NOTHING;