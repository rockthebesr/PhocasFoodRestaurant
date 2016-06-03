create table item(
          itemName varchar(30) primary key,
          stock integer,
          price number(10, 4)); 
          
          
create table menu(
	menuID integer primary key,
	serveStartTime integer,
	serveEndTime integer);


create table dayTimeMenu(
	menuID integer primary key,
	foreign key (menuID) references menu);

create table breakfastMenu(
	menuID integer primary key,
	foreign key (menuID) references menu);

create table drinkMenu(
	menuID integer primary key,
	foreign key (menuID) references menu);
    
create table employee(
	empID integer primary key,
	ename varchar(20),
	gender varchar(20));
    
create table manager(
	empID integer primary key,
	foreign key (empID) references employee(empID) );
    
    
create table store(
	storeID integer primary key,
	city varchar(30),
	province varchar(30),
	location varchar(30),
	empID integer,
Foreign key (empID) references manager(empID)); 

create table regularEmployee(
	empID integer primary key,
	storeID integer,
	managerID integer,
	Foreign key (empID) references employee,
	Foreign key (storeID) references store,
	Foreign key (managerID) references manager);

create table manages(
	empID integer primary key,
	storeID integer not null,
	foreign key (empID) references manager,
	foreign key (storeID) references store );


create table allOrder(
	orderID integer primary key,
	storeID integer not null,
	orderDate date,
	price number(10,4),
	orderStatus varchar(20) CHECK (orderStatus IN ('in preparation', 'out on delivery', 'delivered', 'finished', 'cancelled')),
	empID integer not null,
	foreign key (storeID) references store,
	foreign key (empID) references employee );

create table inStoreOrder(
	orderID integer primary key,
	foreign key (orderID) references allOrder);

create table onlineOrder(
	orderID integer primary key,
	address varchar(20),
	customerName varchar(20),
	phoneNumber integer,
	foreign key (orderID) references allOrder);

create table delivery(
          deliveryID integer primary key,
          deliveryDate date,
          deliveryStatus varchar(3)); 

create table deliveryHasItems(
          deliveryID integer,
          itemName varchar(30),
          primary key (deliveryID, itemName),
          foreign key (deliveryID) references delivery(deliveryID),
          foreign key (itemName) references item(itemName)); 
          
create table delivers(
	deliveryID integer primary key,
	orderID integer not null,
	foreign key (deliveryID) references delivery,
	foreign key (orderID) references allOrder);


create table orders(
          orderID integer,
          itemName varchar(30),
          primary key (orderID, itemName),
          foreign key (orderID) references allOrder(orderID),
          foreign key (itemName) references item(itemName)) ; 

create table serves(
          menuID integer,
          itemName varchar(30),
          primary key (menuID, itemName),
          foreign key (menuID) references menu(menuID),
          foreign key (itemName) references item(itemName)) ; 

create table storeHasMenus(
	storeID integer,
	menuID integer,
	Foreign key (storeID) references store,
	Foreign key (menuID) references menu,
	primary key (storeID, menuID) );


