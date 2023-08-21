insert into user_profile (id, username, email, password_hash, role)
values ('7cfb092f-e7df-497b-bcf3-f4c7c6ebb341', 'user1', 'user1@example.com',
        '$2a$12$kcNfKueCbal2UB.Eac2JlOOEUmPg9p/9acS8QpVd.0cXmoHiw2Lz.', 'USER'),
       ('1698f3cf-8751-48f9-9445-9d2d8ff2b062', 'user2', 'user2@example.com',
        '$2a$12$YplcA43IL7eYZajOWoJTRuw2DsFFDffXaX0sH7tC0.T.NBPvX6jZe', 'USER'),
       ('f2d798c0-9d73-42d8-bce0-57f535b97784', 'user3', 'user3@example.com',
        '$2a$12$0uBcllLqihoeGP1sdF6hA.Q8JpvIwc8VPB1mMu5Koqd1BNsQxVN5O', 'USER'),
       ('f2d798c0-9d73-42d8-1234-57f535b97784', 'user4', 'user4@example.com',
        '$2a$12$0uBcllLqihoeGP1sdF6hA.Q8JpvIwc8VPB1mMu5Koqd1BNsQxVN5O', 'USER'),
       ('f2d798c0-9d73-42d8-4321-57f535b97784', 'user5', 'user5@example.com',
        '$2a$12$0uBcllLqihoeGP1sdF6hA.Q8JpvIwc8VPB1mMu5Koqd1BNsQxVN5O', 'USER');

insert into relation (source_user_id, target_user_id, relation_type)
values ('7cfb092f-e7df-497b-bcf3-f4c7c6ebb341', '1698f3cf-8751-48f9-9445-9d2d8ff2b062', 'SUBSCRIBER'),
       ('1698f3cf-8751-48f9-9445-9d2d8ff2b062', '7cfb092f-e7df-497b-bcf3-f4c7c6ebb341', 'SUBSCRIBER'),
       ('1698f3cf-8751-48f9-9445-9d2d8ff2b062', 'f2d798c0-9d73-42d8-bce0-57f535b97784', 'SUBSCRIBER'),
       ('f2d798c0-9d73-42d8-bce0-57f535b97784', 'f2d798c0-9d73-42d8-1234-57f535b97784', 'SUBSCRIBER'),
       ('f2d798c0-9d73-42d8-1234-57f535b97784', 'f2d798c0-9d73-42d8-4321-57f535b97784', 'SUBSCRIBER');