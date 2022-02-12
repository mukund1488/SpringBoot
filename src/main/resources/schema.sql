create table personal_details (id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
address varchar(255),
current_location varchar(255),
email varchar(255),
first_name varchar(255),
last_name varchar(255),
mobile bigint,
primary key (id));
