insert into roles(role, authority_id)
values ('ADMIN', (select id from authorities where authority = 'FULL')),
       ('GUEST', (select id from authorities where authority = 'READ_ONLY'));
