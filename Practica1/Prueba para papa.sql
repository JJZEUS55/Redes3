-- BASE DE DATOS HECHA EN MYSQL

-- CREAR BASE DE DATOS
create database prueba2;

-- USAR BASE DE DATOS
use prueba2;


-- CREAR VALORES
create table producto(
	idProd int not null primary key,
    nombre varchar(20),
    precio int(10)
);

create table descripcionProd(
	idProd int,
    descripcion varchar(50),
    quejas varchar (30),
    nose varchar(30),
    foreign key (idProd) references producto(idProd)
    on update cascade on delete cascade
);



-- INSERTAR VALORES 

insert into producto values(1, "cocoa", 10);
insert into producto values(2, "pepsi",50);
insert into producto values(3, "alegrias", 5);

insert into descripcionProd values(1, "rica cocoa caliente de santa", "aveces un poco fria", "nose");
insert into descripcionProd values(1, 'mala cocoa', 'fria', 'nose');

insert into descripcionProd values(2, "pepsi fria sabe mejor", "aveces sin gas", "sise");
insert into descripcionProd values(2, "pepsi fria", "con mucho gas", "sise");


insert into descripcionProd values(3, "dulces mexicanos muy buenos", "aveces rancios", "nose");



-- SELECT
select p.idProd, p.nombre, p.precio, d.descripcion, d.quejas
from producto p, descripcionProd d
where  p.idProd = d.idProd
and p.idProd = 2;

SHOW databases;




