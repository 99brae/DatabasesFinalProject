--I will be providing statements with first the way they appear in my code, then 1 or more filled in examples. The question marks are all filled by my code using JDBC

--ADD BOOK TO DATABASE 
--insert into book(title, author, retail_cost, genre, sold, available, ISBN, bulk_cost, publisher_percentage, publisher_id, sold_last_month, pages) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
--here is an example that is filled in
insert into book(title, author, retail_cost, genre, sold, available, ISBN, bulk_cost, publisher_percentage, publisher_id, sold_last_month, pages) values('book1', 'author1', 20.00, 'genre1', 0, 1, 1, 10.00, 50.00, 1, 0, 100)



--REMOVE BOOK FROM DATABASE 
--DELETE FROM book WHERE ISBN=?
DELETE FROM book WHERE ISBN=1
DELETE FROM book WHERE ISBN=2

--ADD PUBLISHER TO DATABASE
--insert into publisher(pub_name, address, email, phone_number, money_made, publisher_id) values(?, ?, ?, ?, ?, ?)
insert into publisher(pub_name, address, email, phone_number, money_made, publisher_id) values('bob', '123 street street, city, province, A1B2C3', 'bob@bob.com',1234567890, 20.00, 1)


--REMOVE PUBLISHER FROM DATABASE
--DELETE FROM publisher WHERE publisher_id=?
DELETE FROM publisher WHERE publisher_id=1
DELETE FROM publisher WHERE publisher_id=2

--UPDATE STOCK OF A BOOK BY ISBN
--UPDATE book SET available=? where ISBN=?
UPDATE book SET available=35 where ISBN=1
UPDATE book SET available=22 where ISBN=2

--SELECT CURRENT STOCK OF A BOOK 
--SELECT available FROM book where ISBN=?
SELECT available FROM book where ISBN=1
SELECT available FROM book where ISBN=2

--SELECT SUM OF ALL SALES MADE
--select sum(sold) from book
select sum(sold) from book


--SELECT SUM OF ALL AVAILABLE BOOKS
--select sum(available) from book
select sum(available) from book

--SELECT SUM OF SOLD AND GENRE AND GROUP BY GENRE FOR A SALES PER GENRE REPORT
select genre, sum(sold) from book group by genre

--SELECT SUM OF SOLD AND AUTHOR AND GROUP BY AUTHOR FOR A SALES PER AUTHOR REPORT
select author, sum(sold) from book group by author

--SELECT SUM OF SOLD AND PUBLISHER AND GROUP BY PUBLISHER FOR A SALES PER PUBLISHER REPORT
select pub_name, sum(sold) from book, publisher group by pub_name