-- 유저 데이터
INSERT INTO book_user (user_id, user_pass, user_phone_number) VALUES('user1', '1234', '010-1111-2222');
INSERT INTO book_user (user_id, user_pass, user_phone_number) VALUES('user2', '2345', '010-3333-4444');

-- book_info 데이터
INSERT INTO book_info VALUES ('1', '도서1', '저자1', now());
INSERT INTO book_info VALUES ('2', '도서2', '저자2', now());
INSERT INTO book_info VALUES ('3', '도서3', '저자3', now());
INSERT INTO book_info VALUES ('10', '도서10', '저자10', now());
INSERT INTO book_info VALUES ('16', '도서116', '저자16', now());

-- book_copy 데이터
INSERT INTO book_copy(book_isbn) VALUES('1');
INSERT INTO book_copy(book_isbn) VALUES('2');
INSERT INTO book_copy(book_isbn) VALUES('3');
INSERT INTO book_copy(book_isbn) VALUES('10');
INSERT INTO book_copy(book_isbn) VALUES('16');

-- book_use_status 데이터

-- book_use_status 데이터
INSERT INTO book_use_status (book_seq, user_id, borrow_start, borrow_end, return_date)
VALUES (3, 'user1', '2023-6-1', '2023-6-14', null);
INSERT INTO book_use_status (book_seq, user_id, borrow_start, borrow_end, return_date)
VALUES (10, 'user1', '2023-6-5', '2023-6-18', null);
INSERT INTO book_use_status (book_seq, user_id, borrow_start, borrow_end, return_date)
VALUES (16, 'user1', '2023-6-5', '2023-6-18', null);
UPDATE book_use_status SET return_date = '2023-6-14'
WHERE book_seq = 16 AND user_id = 'user1';
