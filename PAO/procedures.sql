DELIMITER //
create procedure insertDessert(
INOUT name varchar(25),
IN price double,
IN alergens varchar(100),
IN sugar int)
BEGIN
insert into desserts(name, price, alergens, sugar)drinks
values(name, price, alergens, sugar);
END //

DELIMITER //
create procedure insertDrink(
INOUT name varchar(25),
IN price double,
IN alergens varchar(100),
IN temperature varchar(5),
IN flavour varchar(15))
BEGIN
insert into drinks(name, price, alergens, temperature, flavour)
values(name, price, alergens, temperature, flavour);
END //

DELIMITER //
create procedure insertMaindish(
INOUT name varchar(25),
IN price double,
IN alergens varchar(100),
IN ingredients varchar(100),
IN spicy int)
BEGIN
insert into maindishes(name, price, alergens, ingredients, spicy)
values(name, price, alergens, ingredients, spicy);
END //

