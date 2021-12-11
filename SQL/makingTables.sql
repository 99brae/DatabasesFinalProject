
--These commands (ORDER MATTERS) create the tables of my database
create table store_user
	(user_id		varchar(20),
	 date_of_birth		varchar(10),
	 address		varchar(40),
	 email			varchar(64),
	 full_name		varchar(32),
	 phone_number	numeric(10,0),	 
	 primary key (user_id)
	);
	
	create table publisher
	(pub_name		varchar(32),
	 address		varchar(40),
	 email 			varchar(64),
	 phone_number 	numeric(10,0),
	 money_made		numeric(8,2),
	 publisher_id	numeric(8,0),
	 
	 primary key (publisher_id)
	);


create table store_order
	(order_number		numeric(8,0),
	 billing_info		varchar(16),
	 shipping_info		varchar(40),
	 user_id			varchar(20),
	 tracking_info		varchar(16), 
	 primary key (order_number),
	 foreign key (user_id) references store_user
	);





create table book
	(title		varchar(64),
	 author		varchar(32),
	 retail_cost numeric(6,2),
	 genre		varchar(16),
	 sold		numeric(8,0),
	 available  numeric(8,0),
	 ISBN		numeric(8,0),
	 bulk_cost	numeric(6,2),
	 publisher_percentage numeric(4,2),
	 publisher_id numeric(8,0),
	 sold_last_month numeric(8,0),
	 pages		numeric(5,0),
	 
	 primary key (ISBN),
	 foreign key (publisher_id) references publisher
	);


create table ordered_books
	(order_number		numeric(8,0),
	 ISBN				numeric(8,0),
	 primary key (order_number, ISBN),
	 foreign key (ISBN) references book,
	 foreign key (order_number) references store_order
	);