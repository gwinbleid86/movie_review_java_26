insert into user_table(email, username, password, enabled, role_id)
VALUES ('qwe@qwe.qwe',
        'qwe',
        '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2',
        TRUE,
        (select id from roles where role = 'ADMIN')),
       ('ewq@ewq.com',
        'ewq',
        '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2',
        TRUE,
        (select id from roles where role = 'GUEST'))