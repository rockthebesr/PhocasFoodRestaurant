insert into item values ('cheeseburger', 2, 6.5);
insert into item values ('hashbrown', 5, 2.0);
insert into item values ('chocolate pudding', 1, 2.0);
insert into item values ('fries', 10, 2.0);
insert into item values ('chicken sandwich', 3, 5.5);
insert into item values ('ice cream', 5, 2.0);
insert into item values ('fish and chips', 0, 13.5);
insert into item values ('tuna', 2, 15.0);
insert into item values ('tomatoe soup', 10, 5.0);
insert into item values ('yogurt parfait', 17, 3.0);
insert into item values ('coke', 20, 1.75);
insert into item values ('sprite', 20, 1.75);
insert into item values ('smoothie', 10, 3.75);
insert into item values ('corona', 5, 4.0);
insert into item values ('guiness', 7, 5.75);

insert into menu values (1, 8, 11);
insert into menu values (2, 8, 11);
insert into menu values (3, 8, 11);
insert into menu values (4, 8, 11);
insert into menu values (5, 8, 11);
insert into menu values (6, 8, 24);
insert into menu values (7, 8, 24);
insert into menu values (8, 8, 24);
insert into menu values (9, 8, 24);
insert into menu values (10, 8, 24);
insert into menu values (11, 8, 24);
insert into menu values (12, 8, 24);
insert into menu values (13, 8, 24);
insert into menu values (14, 8, 24);
insert into menu values (15, 8, 24);

insert into dayTimeMenu values (6);
insert into dayTimeMenu values (7);
insert into dayTimeMenu values (8);
insert into dayTimeMenu values (9);
insert into dayTimeMenu values (10);

insert into breakfastMenu values (1);
insert into breakfastMenu values (2);
insert into breakfastMenu values (3);
insert into breakfastMenu values (4);
insert into breakfastMenu values (5);

insert into drinkMenu values (11);
insert into drinkMenu values (12);
insert into drinkMenu values (13);
insert into drinkMenu values (14);
insert into drinkMenu values (15);


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

insert into manager values(1);
insert into manager values(2);
insert into manager values(3);
insert into manager values(4);
insert into manager values(5);

insert into store values(1, 'Vancouver', 'British Columbia', '111 Granville St', 1);
insert into store values(2, 'Richmond', 'British Columbia', '4567 Alexandra St', 2);
insert into store values(3, 'Surrey', 'British Columbia', '240 1st Ave', 3);
insert into store values(4, 'Toronto', 'Ontario', '3901 Dundas St', 4);
insert into store values(5, 'Toronto', 'Ontario', '1203 Spadina St', 5);

insert into regularEmployee values(6, 1, 1);
insert into regularEmployee values(7, 2, 2);
insert into regularEmployee values(8, 3, 3);
insert into regularEmployee values(9, 4, 4);
insert into regularEmployee values(10, 5, 5);
insert into regularEmployee values(11, 5, 5);

insert into manages values(1, 1);
insert into manages values(2, 2);
insert into manages values(3, 3);
insert into manages values(4, 4);
insert into manages values(5, 5);


insert into allOrder values
(1, 1, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 10.5, 'finished', 1);
insert into allOrder values
(2, 1, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 8.0, 'cancelled', 1);
insert into allOrder values
(3, 2, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 5.5, 'finished', 7);
insert into allOrder values
(4, 2, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 5.5, 'in preparation', 7);
insert into allOrder values
(5, 3, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 6.0, 'cancelled', 3);
insert into allOrder values
(6, 3, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 6.0, 'out on delivery', 3);
insert into allOrder values
(7, 4, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 20.0, 'delivered', 9);
insert into allOrder values
(8, 4, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 17.0, 'delivered', 9);
insert into allOrder values
(9, 5, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 9.75, 'finished', 11);
insert into allOrder values
(10, 5, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 1.75, 'finished', 11);

insert into inStoreOrder values(1);
insert into inStoreOrder values(2);
insert into inStoreOrder values(3);
insert into inStoreOrder values(4);
insert into inStoreOrder values(5);

insert into onlineOrder values(6, '8295 Scott Road', 'Simon',  6045079393);
insert into onlineOrder values(7, '579 Yonge Street', 'Claire',  6473442637);
insert into onlineOrder values(8, '13 Baldwin Street', 'Marcus',  4167928858);
insert into onlineOrder values(9, '120 Lombard Avenue', 'Sameer',  6478961774);
insert into onlineOrder values(10, '92 Front Street E', 'Ruth',  4163927219);

insert into orders values(1, 'hashbrown');
insert into orders values(2, 'cheeseburger');
insert into orders values(3, 'coke');
insert into orders values(4, 'fries');
insert into orders values(5, 'tuna');

insert into serves values(1, 'hashbrown');
insert into serves values(6, 'cheeseburger');
insert into serves values(11, 'coke');
insert into serves values(7, 'fries');
insert into serves values(8, 'tuna');

insert into delivery values
(1, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 'delivered');
insert into delivery values
(2, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 'delivered');
insert into delivery values
(3, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 'delivered');
insert into delivery values
(4, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 'delivered');
insert into delivery values
(5, TO_DATE('22/April/2016 8:30:00AM','DD/MON/YY HH:MI:SSAM'), 'delivered');

insert into deliveryHasItems values
(1, 'cheeseburger');
insert into deliveryHasItems values
(2, 'chocolate pudding');
insert into deliveryHasItems values
(3, 'chocolate pudding');
insert into deliveryHasItems values
(4, 'fish and chips');
insert into deliveryHasItems values
(5, 'fish and chips');

insert into delivers values
(1, 6);
insert into delivers values
(2, 7);
insert into delivers values
(3, 8);
insert into delivers values
(4, 9);
insert into delivers values
(5, 10);

insert into storeHasMenus values
(1,1);
insert into storeHasMenus values
(2,2);
insert into storeHasMenus values
(3,3);
insert into storeHasMenus values
(4,4);
insert into storeHasMenus values
(5,5);
insert into storeHasMenus values
(1,6);
insert into storeHasMenus values
(2,7);
insert into storeHasMenus values
(3,8);
insert into storeHasMenus values
(4,9);
insert into storeHasMenus values
(5,10);
insert into storeHasMenus values
(1,11);
insert into storeHasMenus values
(2,12);
insert into storeHasMenus values
(3,13);
insert into storeHasMenus values
(4,14);
insert into storeHasMenus values
(5,15);