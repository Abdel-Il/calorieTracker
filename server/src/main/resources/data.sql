INSERT INTO USER (user_id, name) VALUES (1, 'Abderrahman'), (2, 'Maurice');

INSERT INTO FOOD (food_id, name, calorie_count) VALUES (1, 'Slice of bread', 70), (2, 'Steak', 270), (3, 'Banana', 110),
                                                       (14, 'Oats', 389), (4, 'Tuna', 131), (5, 'Peanuts', 567),
                                                       (6, 'Protein bar', 200), (7, 'Chicken', 165), (8, 'Beef', 250),
                                                       (9, 'Pizza', 400), (10, 'Rice', 130), (11, 'Watermelon', 30),
                                                       (12, 'Mystery food 1', 1000), (13, 'Mystery food 2', 2100),
                                                       (15, 'Uranium', 1000000);

INSERT INTO USER_FOOD (food_id, id, user_id, created_At, price) VALUES (13, 1, 1, '2023-09-01', 1),(13, 2, 1, '2023-09-02', 1),
                                                                       (13, 3, 1, '2023-09-03', 1),(12, 4, 1, '2023-09-04', 1),
                                                                       (13, 5, 1, '2023-09-05', 1),(13, 6, 1, '2023-09-06', 1),
                                                                       (12, 7, 1, '2023-09-07', 1),(13, 8, 1, '2023-09-08', 1),
                                                                       (13, 9, 1, '2023-09-09', 1),(12, 10, 1, '2023-09-10', 1),
                                                                       (12, 11, 1, '2023-09-11', 1),(12, 12, 1, '2023-09-12', 1),
                                                                       (1, 13, 1, '2023-09-13', 1),(1, 14, 1, '2023-09-14', 1),
                                                                       (1, 15, 1, '2023-09-15', 1),(1, 16, 1, '2023-09-16', 1),
                                                                       (1, 17, 1, '2023-09-07', 1),(1, 18, 1, '2023-09-07', 1),
                                                                       (12, 19, 1, '2023-09-17', 30),(9, 20, 1, '2023-09-17', 50);
--                                                                        (1, 1, 1, '2023-07-07', 1),(1, 1, 1, '2023-07-07', 1),
--                                                                        (1, 1, 1, '2023-07-07', 1),(1, 1, 1, '2023-07-07', 1),
--                                                                        (1, 1, 1, '2023-07-07', 1),(1, 1, 1, '2023-07-07', 1),
--                                                                        (1, 1, 1, '2023-07-07', 1),(1, 1, 1, '2023-07-07', 1),
