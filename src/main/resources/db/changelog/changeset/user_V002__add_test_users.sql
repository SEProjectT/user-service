INSERT INTO users (username, preferred_contact, email, phone)
VALUES
    ('user1', 'SMS', 'test@test.ru', 'phone1'),
    ('user2', 'EMAIL', 'fk-00@list.ru', 'phone2'),
    ('user3', 'TELEGRAM', 'team@team.ru', 'phone3')
ON CONFLICT DO NOTHING;