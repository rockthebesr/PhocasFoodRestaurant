//drop tables
drop table manages;
drop table delivers;
drop table storeHasMenus;
drop table dayTimeMenu;
drop table breakfastMenu;
drop table drinkMenu;
drop table regularEmployee;
drop table serves;
drop table deliveryHasItems;
drop table inStoreOrder;
drop table onlineOrder;
drop table item;
drop table employee;
drop table store;
drop table menu;
drop table allOrder;
drop table delivery;
drop table orders;
drop table manager;

//table creation
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
	foreign key (menuID) references menu ON DELETE CASCADE);

create table breakfastMenu(
	menuID integer primary key,
	foreign key (menuID) references menu ON DELETE CASCADE);

create table drinkMenu(
	menuID integer primary key,
	foreign key (menuID) references menu ON DELETE CASCADE);
    
create table employee(
	empID integer primary key,
	ename varchar(20),
	gender varchar(20));
    
create table manager(
	empID integer primary key,
	foreign key (empID) references employee(empID) ON DELETE CASCADE );
    
    
create table store(
	storeID integer primary key,
	city varchar(30),
	province varchar(30),
	location varchar(30),
	empID integer,
Foreign key (empID) references manager(empID) ON DELETE SET NULL); 

create table regularEmployee(
	empID integer primary key,
	storeID integer,
	managerID integer,
	Foreign key (empID) references employee ON DELETE CASCADE,
	Foreign key (storeID) references store ON DELETE SET NULL,
	Foreign key (managerID) references manager ON DELETE SET NULL);

create table manages(
	empID integer primary key,
	storeID integer not null,
	foreign key (empID) references manager ON DELETE CASCADE,
	foreign key (storeID) references store ON DELETE CASCADE);


create table allOrder(
	orderID integer primary key,
	storeID integer not null,
	orderDate date,
	price number(10,4),
	orderStatus varchar(20) CHECK (orderStatus IN ('in preparation', 'out on delivery', 'delivered', 'finished', 'cancelled')),
	empID integer not null,
	foreign key (storeID) references store ON DELETE CASCADE,
	foreign key (empID) references employee ON DELETE SET NULL);

create table inStoreOrder(
	orderID integer primary key,
	foreign key (orderID) references allOrder ON DELETE CASCADE);

create table onlineOrder(
	orderID integer primary key,
	address varchar(20),
	customerName varchar(20),
	phoneNumber integer,
	foreign key (orderID) references allOrder ON DELETE CASCADE);

create table delivery(
          deliveryID integer primary key,
          deliveryDate date,
          deliveryStatus varchar(30)); 

create table deliveryHasItems(
          deliveryID integer,
          itemName varchar(30),
          primary key (deliveryID, itemName),
          foreign key (deliveryID) references delivery(deliveryID) ON DELETE CASCADE,
          foreign key (itemName) references item(itemName) ON DELETE CASCADE); 
          
create table delivers(
	deliveryID integer primary key,
	orderID integer not null,
	foreign key (deliveryID) references delivery ON DELETE CASCADE,
	foreign key (orderID) references allOrder ON DELETE CASCADE);


create table orders(
          orderID integer,
          itemName varchar(30),
          primary key (orderID, itemName),
          foreign key (orderID) references allOrder(orderID) ON DELETE CASCADE,
          foreign key (itemName) references item(itemName) ON DELETE CASCADE); 

create table serves(
          menuID integer,
          itemName varchar(30),
          primary key (menuID, itemName),
          foreign key (menuID) references menu(menuID) ON DELETE CASCADE,
          foreign key (itemName) references item(itemName) ON DELETE CASCADE); 

create table storeHasMenus(
	storeID integer,
	menuID integer,
	Foreign key (storeID) references store ON DELETE CASCADE,
	Foreign key (menuID) references menu ON DELETE CASCADE,
	primary key (storeID, menuID) );

//drinks
insert into item values('molson canadian', 7, 10);
insert into item values('rootbeer float', 4, 3.5);
insert into item values('guiness', 7, 10);
insert into item values('iced tea', 10, 3);
insert into item values('apple juice', 15, 3);
insert into item values('pina colada', 8, 5);
insert into item values('mango smoothie;', 7, 4);
insert into item values('lemonade', 12, 3);
insert into item values('white peach sangria', 5, 6);
//breakfast
insert into item values('big breakfast', 20, 8);
insert into item values('BLT bagel', 45, 5);
insert into item values('bacon egg english muffin', 13, 5);
insert into item values('hashbrown', 69, 1);
insert into item values('pancake', 15, 6);
//daytime
insert into item values('buffalo chicken sandwich', 2, 8);
insert into item values('philly cheese steak', 3, 8);
insert into item values('chesseburger', 12, 8);
insert into item values('chicken strip', 4, 8);
insert into item values('chispy chicken sandwich', 13, 8);
insert into item values('hot wings', 3, 8);
insert into item values('ceasar salad', 4, 5);
insert into item values('coleslaw', 24, 3);
insert into item values('fish taco', 23, 6);
insert into item values('fries', 23, 4);
insert into item values('onion rings' 23, 4);
insert into item values('ice cream', 32, 4); 
insert into item values('cheesecake', 34, 5);
insert into item values('molten lava cake', 5, 6);
insert into item values('jalapeno popper', 14, 4);

//menus
insert into menus values(1, 6, 9);
insert into menus values(2, 6, 9);
insert into menus values(3, 6, 9);
insert into menus values(4, 9, 22);
insert into menus values(5, 9, 22);
insert into menus values(6, 9, 22);
insert into menus values(7, 9, 22);
insert into menus values(8, 9, 22);
insert into menus values(9, 9, 22);

//daytime
insert into dayTimeMenu values (4);
insert into dayTimeMenu values (5);
insert into dayTimeMenu values (6);
//breakfast
insert into breakfastMenu values(1); 
insert into breakfastMenu values(2);
insert into breakfastMenu values(3); 
//drinks
insert into drinkMenu values(7);
insert into drinkMenu values(8);
insert into drinkMenu values(9);    

//store
insert into store values(1, 'Vancouver', 'British Columbia', '111 Granville St', 1);
insert into store values(2, 'Richmond', 'British Columbia', '4567 Alexandra St', 2);
insert into store values(3, 'Surrey', 'British Columbia', '240 1st Ave', 3);
insert into store values(4, 'Toronto', 'Ontario', '3901 Dundas St', 4);
insert into store values(5, 'Toronto', 'Ontario', '1203 Spadina St', 5);
insert into store values(6, 'Vancouver', 'British Columbia', '6133 University Blvd', 6);

//manager
insert into manager values(1);
insert into manager values(2);
insert into manager values(3);
insert into manager values(4);
insert into manager values(5);
insert into manager values(6);
//regular emp
insert into regularEmployee values(7, 1, 1);
insert into regularEmployee values(8, 1, 1);
insert into regularEmployee values(9, 1, 1);
insert into regularEmployee values(10, 2, 2);
insert into regularEmployee values(11, 2, 2);
insert into regularEmployee values(12, 2, 2);
insert into regularEmployee values(13, 3, 3);
insert into regularEmployee values(14, 3, 3);
insert into regularEmployee values(15, 3, 3);
insert into regularEmployee values(16, 4, 4);
insert into regularEmployee values(17, 4, 4);
insert into regularEmployee values(18, 4, 4);
insert into regularEmployee values(19, 5, 5);
insert into regularEmployee values(20, 5, 5);
insert into regularEmployee values(21, 5, 5);
insert into regularEmployee values(22, 5, 5);
insert into regularEmployee values(23, 6, 6);
insert into regularEmployee values(24, 6, 6);
insert into regularEmployee values(25, 6, 6);
insert into regularEmployee values(26, 6, 6);
//emp
insert into employee values(1, 'James', 'Male');
insert into employee values(2, 'Lily', 'Female');
insert into employee values(3, 'Monica', 'Female');
insert into employee values(4, 'Roy', 'Male');
insert into employee values(5, 'Paul', 'Male');
insert into employee values(6, 'George', 'Male');
insert into employee values(7, 'Joanna', 'Female');
insert into employee values(8, 'David', 'Male');
insert into employee values(9, 'Ivy', 'Female');
insert into employee values(10, 'Steven', 'Male');
insert into employee values(11, 'Joy', 'Female');
insert into employee values(12, 'Phoebe', 'Female');
insert into employee values(13, 'Alexandra', 'Female');
insert into employee values(14, 'Jack', 'Male');
insert into employee values(15, 'Lex', 'Male');
insert into employee values(16, 'Tony', 'Male');
insert into employee values(17, 'Amelia', 'Female');
insert into employee values(18, 'Matt', 'Male');
insert into employee values(19, 'Jerry', 'Male');
insert into employee values(20, 'Longinus', 'Male');
insert into employee values(21, 'Larry', 'Male');
insert into emoloyee values(22, 'Ginnifer', 'Female');
insert into employee values(23, 'Catherina', 'Female');
insert into employee values(24, 'Kathy', 'Female');
insert into employee values(25, 'Izzy', 'Female');
insert into employee values(26, 'Ali', 'Female');

//manages
insert into manages values(1, 1);
insert into manages values(2, 2);
insert into manages values(3, 3);
insert into manages values(4, 4);
insert into manages values(5, 5);
insert into manages values(6, 6);

//serves
insert into serves values(1, 'big breakfast');
insert into serves values(1, 'BLT bagel');
insert into serves values(1, 'hashbrown');
insert into serves values(1, 'pancake');
insert into serves values(2, 'big breakfast');
insert into serves values(2, 'bacon egg english muffin');
insert into serves values(2, 'pancake');
insert into serves values(3, 'big breakfast');
insert into serves values(3, 'BLT bagel');
insert into serves values(3, 'pancake');
insert into serves values(4, 'buffalo chicken sandwich');
insert into serves values(4, 'philly cheese steak');
insert into serves values(4, 'coleslaw');
insert into serves values(4, 'fries');
insert into serves values(4, 'cheesecake');
insert into serves values(4, 'jalapeno popper');
insert into serves values(5, 'buffalo chicken sandwich');
insert into serves values(5, 'chicken strip');
insert into serves values(5, 'hot wings');
insert into serves values(5, 'ceasar salad');
insert into serves values(5, 'molten lava cake');
insert into serves values(5, 'cheesecake');
insert into serves values(5, 'jalapeno popper');
insert into serves values(6, 'buffalo chicken sandwich');
insert into serves values(6, 'philly cheese steak');
insert into serves values(6, 'chesseburger');
insert into serves values(6, 'chispy chicken sandwich');
insert into serves values(6, 'ceasar salad');
insert into serves values(6, 'fish taco');
insert into serves values(6, 'onion rings');
insert into serves values(6, 'ice cream');
insert into serves values(7, 'molson canadian');
insert into serves values(7, 'rootbeer float');
insert into serves values(7, 'iced tea');
insert into serves values(7, 'lemonade');
insert into serves values(8, 'guiness');
insert into serves values(8, 'apple juice');
insert into serves values(8, 'pina colada');
insert into serves values(8, 'lemonade');
insert into serves values(9, 'white peach sangria');
insert into serves values(9, 'mango smoothie');
insert into serves values(9, 'pina colada');
insert into serves values(9, 'lemonade');

//storehasmenu
insert into storeHasMenus values(1, 1);
insert into storeHasMenus values(1, 4);
insert into storeHasMenus values(1, 7);
insert into storeHasMenus values(2, 2);
insert into storeHasMenus values(2, 5);
insert into storeHasMenus values(2, 8);
insert into storeHasMenus values(3, 3);
insert into storeHasMenus values(3, 6);
insert into storeHasMenus values(3, 9);
insert into storeHasMenus values(4, 1);
insert into storeHasMenus values(4, 5);
insert into storeHasMenus values(4, 9);
insert into storeHasMenus values(5, 2);
insert into storeHasMenus values(5, 6);
insert into storeHasMenus values(5, 7);
insert into storeHasMenus values(6, 3);
insert into storeHasMenus values(6, 4);
insert into storeHasMenus values(6, 8);

//allorder
insert into allOrder values(1, 1, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 8,'delivered', 1);
insert into allOrder values(2, 1, TO_DATE('22/April/2016 9:30:00AM','DD/MON/YY HH:MI:SSAM'), 12,'in preparation', 7);
insert into allOrder values(3, 1, TO_DATE('22/April/2016 12:30:00PM','DD/MON/YY HH:MI:SSAM'), 5,'finished', 8);
insert into allOrder values(4, 1, TO_DATE('22/April/2016 6:30:00PM','DD/MON/YY HH:MI:SSAM'), 10,'finished', 9);
insert into allOrder values(5, 2, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 5,'out on delivery',2);
insert into allOrder values(6, 2, TO_DATE('22/April/2016 9:30:00AM','DD/MON/YY HH:MI:SSAM'), 8,'finished',10);
insert into allOrder values(7, 2, TO_DATE('22/April/2016 12:30:00PM','DD/MON/YY HH:MI:SSAM'), 11,'cancelled',11);
insert into allOrder values(8, 2, TO_DATE('22/April/2016 6:30:00PM','DD/MON/YY HH:MI:SSAM'), 8,'finished',12);
insert into allOrder values(9, 3, TO_DATE('22/April/2016 6:30:00AM','DD/MON/YY HH:MI:SSAM'), 6,'delivered', 3);
insert into allOrder values(10, 3, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 8,'finished', 13);
insert into allOrder values(11, 3, TO_DATE('22/April/2016 12:30:00PM','DD/MON/YY HH:MI:SSAM'), 6,'finished',14);
insert into allOrder values(12, 3, TO_DATE('22/April/2016 6:30:00PM','DD/MON/YY HH:MI:SSAM'), 12,'finished', 15);
insert into allOrder values(13, 4, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 1,'out on delivery',4);
insert into allOrder values(14, 4, TO_DATE('22/April/2016 11:30:00AM','DD/MON/YY HH:MI:SSAM'), 6,'finished',16);
insert into allOrder values(15, 4, TO_DATE('22/April/2016 3:30:00PM','DD/MON/YY HH:MI:SSAM'), 6,'finished',17);
insert into allOrder values(16, 4, TO_DATE('22/April/2016 8:30:00PM','DD/MON/YY HH:MI:SSAM'), 14,'cancelled',18);
insert into allOrder values(17, 5, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 6,'delivered',19);
insert into allOrder values(18, 5, TO_DATE('22/April/2016 9:30:00AM','DD/MON/YY HH:MI:SSAM'), 8,'finished',20);
insert into allOrder values(19, 5, TO_DATE('22/April/2016 10:30:00AM','DD/MON/YY HH:MI:SSAM'), 4,'cancelled',21);
insert into allOrder values(20, 5, TO_DATE('22/April/2016 12:30:00AM','DD/MON/YY HH:MI:SSAM'), 10,'finished',22);
insert into allOrder values(21, 6, TO_DATE('22/April/2016 8:31:00PM','DD/MON/YY HH:MI:SSAM'), 8,'out on delivery',23);
insert into allOrder values(22, 6, TO_DATE('22/April/2016 8:32:00PM','DD/MON/YY HH:MI:SSAM'), 12,'in preparation',24);
insert into allOrder values(23, 6, TO_DATE('22/April/2016 8:33:00PM','DD/MON/YY HH:MI:SSAM'), 3,'finished',25);
insert into allOrder values(24, 6, TO_DATE('22/April/2016 8:34:00PM','DD/MON/YY HH:MI:SSAM'), 5,'finished',26);

//instore order
insert into inStoreOrder values(2);
insert into inStoreOrder values(3);
insert into inStoreOrder values(4);
insert into inStoreOrder values(6);
insert into inStoreOrder values(7);
insert into inStoreOrder values(8);
insert into inStoreOrder values(10);
insert into inStoreOrder values(11);
insert into inStoreOrder values(12);
insert into inStoreOrder values(14);
insert into inStoreOrder values(15);
insert into inStoreOrder values(16);
insert into inStoreOrder values(18);
insert into inStoreOrder values(19);
insert into inStoreOrder values(20);
insert into inStoreOrder values(22);
insert into inStoreOrder values(23);
insert into inStoreOrder values(24);

//online order
insert into onlineOrder values(1, '8295 Scott Road', 'Simon',  6045079393);
insert into onlineOrder values(5, '579 Yonge Street', 'Claire',  6473442637);
insert into onlineOrder values(9, '13 Baldwin Street', 'Marcus',  4167928858);
insert into onlineOrder values(13, '120 Lombard Avenue', 'Sameer',  6478961774);
insert into onlineOrder values(17, '92 Front Street E', 'Ruth',  4163927219);
insert into onlineOrder values(21, '92 Front Street E', 'Ruby',  7783927219);

//delivery
insert into delivery values(1, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 'delivered');
insert into delivery values(2, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 'out on delivery');
insert into delivery values(3, TO_DATE('22/April/2016 6:30:00AM','DD/MON/YY HH:MI:SSAM'), 'delivered');
insert into delivery values(4, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 'out on delivery');
insert into delivery values(5, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 'delivered');
insert into delivery values(6, TO_DATE('22/April/2016 8:31:00PM','DD/MON/YY HH:MI:SSAM'), 'out on delivery');

//delivers
insert into delivers values(1, 1);
insert into delivers values(1, 5);
insert into delivers values(1, 9);
insert into delivers values(1, 13);
insert into delivers values(1, 17);
insert into delivers values(1, 21);

//deliveryHasItems
insert into deliveryHasItems values(1, 'big breakfast');
insert into deliveryHasItems values(2, 'bacon egg english muffin');
insert into deliveryHasItems values(3, 'pancake');
insert into deliveryHasItems values(4, 'hashbrown');
insert into deliveryHasItems values(5, 'pancake');
insert into deliveryHasItems values(6, 'buffalo chicken sandwich');

//orders
insert into orders values(1, 'big breakfast');
insert into orders values(2, 'buffalo chicken sandwich');
insert into orders values(2, 'jalapeno popper');
insert into orders values(3, 'cheesecake');
insert into orders values(4, 'molson canadian');
insert into orders values(5, 'bacon egg english muffin');
insert into orders values(6, 'chicken strip');
insert into orders values(7, 'molten lava cake');
insert into orders values(7, 'ceasar salad');
insert into orders values(8, 'buffalo chicken sandwich');
insert into orders values(9, 'pancake');
insert into orders values(10, 'big breakfast');
insert into orders values(11, 'fish taco');
insert into orders values(12, 'buffalo chicken sandwich');
insert into orders values(12, 'onion rings');
insert into orders values(13, 'hashbrown');
insert into orders values(14, 'molten lava cake');
insert into orders values(15, 'molten lava cake');
insert into orders values(16, 'buffalo chicken sandwich');
insert into orders values(16, 'molten lava cake');
insert into orders values(17, 'pancake');
insert into orders values(18, 'philly cheese steak');
insert into orders values(19, 'onion rings');
insert into orders values(20, 'molson canadian');
insert into orders values(21, 'buffalo chicken sandwich');
insert into orders values(22, 'buffalo chicken sandwich');
insert into orders values(22, 'jalapeno popper');
insert into orders values(23, 'coleslaw');
insert into orders values(24, 'pina colada');


