--I will be providing statements with first the way they appear in my code (AS A COMMENT), then 1 or more filled in examples. The question marks are all filled by my code using JDBC

--SEARCH BOOKS BY TITLE
--select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where title=?
select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where title='booktitle'
select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where title='othertitle'


--SEARCH BOOKS BY GENRE
--select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where genre=?
select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where genre='bookgenre'
select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where genre='othergenre'

--SEARCH BOOKS BY ISBN
--select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where ISBN=?
select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where ISBN=1
select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where ISBN=2

--SEARCH BOOKS BY AUTHOR
--select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where author=?
select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where author='Braedon'
select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where author='OtherAuthor'

--ADD USER TO STORE_USER TABLE
--insert into store_user(user_id, date_of_birth, address, email, full_name, phone_number) values(?, ?, ?, ?, ?, ?)
insert into store_user(user_id, date_of_birth, address, email, full_name, phone_number) values('username', '06/01/1999', '123 street street, city, province, A1B2C3', 'email@address.com', 'first last',1234567890)

--QUERY STORE_USER TABLE FOR USER ID TO LOGIN
--select user_id from store_user where user_id=?
select user_id from store_user where user_id='bob'
select user_id from store_user where user_id='Braedon'

--QUERY ISBN, AVAILABLE FROM BOOK TO DETERMINE IF IT IS IN STOCK WITH ISBN
--select ISBN, available from book where ISBN=?
select ISBN, available from book where ISBN=1
select ISBN, available from book where ISBN=2

--NOTE: REMOVE FROM CART HANDLED BY MY PROGRAM NOT THE DATABASE

--QUERY BOOK, PUBLISHER FOR PRINTING CART
--select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where ISBN=?
select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where ISBN=1
select title, author, genre, ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book inner join publisher on book.publisher_id = publisher.publisher_id where ISBN=2

--QUERY THE HIGHEST ORDER NUMBER TO DETERMINE NEXT ORDER NUMBER 
--select max(order_number) from store_order
select max(order_number) from store_order

--INSERT INTO ORDER DURING CHECKOUT
--insert into store_order(order_number, billing_info, shipping_info, user_id, tracking_info) values (?,?,?,?,?)
insert into store_order(order_number, billing_info, shipping_info, user_id, tracking_info) values (1,1234567890123456,'123 house street, city, province, A1B2C3','user','In Progress')
insert into store_order(order_number, billing_info, shipping_info, user_id, tracking_info) values (1,1234567890123456,'321 house street, city, province, A1B2C3','otherUser','Delivered')

--INSERT ALL ORDERED BOOKS INTO ORDERED BOOKS 
--insert into ordered_books(order_number, ISBN) values(?,?)
insert into ordered_books(order_number, ISBN) values(1,1)
insert into ordered_books(order_number, ISBN) values(2,34)

--LOAD DATA FOR A SPECIFIC BOOK BY ISBN IN CHECHOUT
--SELECT available, sold, publisher_percentage, publisher_id, sold_last_month, retail_cost FROM book where ISBN=?
SELECT available, sold, publisher_percentage, publisher_id, sold_last_month, retail_cost FROM book where ISBN=1
SELECT available, sold, publisher_percentage, publisher_id, sold_last_month, retail_cost FROM book where ISBN=27

--UPDATE BOOK AFTER A SALE
--UPDATE book SET available=?, sold=?, sold_last_month=? where ISBN=?
UPDATE book SET available=10, sold=5, sold_last_month=4 where ISBN=1

--GET PUBLISHER PROFITS
--SELECT money_made FROM publisher where publisher_id=?
SELECT money_made FROM publisher where publisher_id=1
SELECT money_made FROM publisher where publisher_id=56

--UPDATE PUBLISHER PROFITS 
--UPDATE publisher SET money_made=? where publisher_id=?
UPDATE publisher SET money_made=3000 where publisher_id=1

--QUERY ORDER BY ORDER NUMBER 
--select * from store_order where order_number=?
select * from store_order where order_number=1
select * from store_order where order_number=5

--GET INFO ON ALL BOOKS IN AN ORDER 
--select title, author, genre, book.ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book, ordered_books, publisher where order_number=? and ordered_books.ISBN = book.ISBN
select title, author, genre, book.ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book, ordered_books, publisher where order_number=1 and ordered_books.ISBN = book.ISBN
select title, author, genre, book.ISBN, pub_name, publisher.publisher_id, pages, retail_cost from book, ordered_books, publisher where order_number=89 and ordered_books.ISBN = book.ISBN





