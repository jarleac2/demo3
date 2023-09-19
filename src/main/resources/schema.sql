CREATE TABLE IF NOT EXISTS tipo_documento(id INT NOT NULL AUTO_INCREMENT,
tipo_documento VARCHAR2(20),descripcion VARCHAR2(200),PRIMARY KEY(id));

INSERT into tipo_documento (tipo_documento,descripcion) values('CC','Cedula de ciudadania');
INSERT into tipo_documento (tipo_documento,descripcion) values('CE','Cedula de extranjeria');
INSERT into tipo_documento (tipo_documento,descripcion) values('PA','Pasaporte');
INSERT into tipo_documento (tipo_documento,descripcion) values('RC','Registro civil');
INSERT into tipo_documento (tipo_documento,descripcion) values('TI','Tarjeta identidad');
INSERT into tipo_documento (tipo_documento,descripcion) values('D','Diplomatico');
INSERT into tipo_documento (tipo_documento,descripcion) values('TT','PPT');
INSERT into tipo_documento (tipo_documento,descripcion) values('N','NIT');
INSERT into tipo_documento (tipo_documento,descripcion) values('D','Diplomatico');
INSERT into tipo_documento (tipo_documento,descripcion) values('NIP','Numero de identificacion personal');

CREATE TABLE IF NOT EXISTS cliente(id INT NOT NULL AUTO_INCREMENT,
id_tipo_documento INT,
numero_identificacion VARCHAR2(15),
nombre VARCHAR2(50),
apellido VARCHAR2(50),
telefono VARCHAR2(10),
correo VARCHAR2(50),
activo BOOLEAN default true,
PRIMARY KEY(id));

INSERT into cliente (id_tipo_documento,numero_identificacion,nombre,apellido,telefono)
values(1,'1098123123','Jhair','Leal','3163133133');
INSERT into cliente (id_tipo_documento,numero_identificacion,nombre,apellido,telefono)
values(2,'1098124124','Sofia','Leal','3164123122');
INSERT into cliente (id_tipo_documento,numero_identificacion,nombre,apellido,telefono)
values(1,'1098121121','Juan','Pedraza','3163153153');
INSERT into cliente (id_tipo_documento,numero_identificacion,nombre,apellido,telefono)
values(2,'1098122122','Jose','Cubillos','3163143133');
INSERT into cliente (id_tipo_documento,numero_identificacion,nombre,apellido,telefono)
values(3,'1098125125','Saul','Gonzalez','3163173133');
INSERT into cliente (id_tipo_documento,numero_identificacion,nombre,apellido,telefono)
values(1,'1098126126','Edgar','Paredes','3163133933');
INSERT into cliente (id_tipo_documento,numero_identificacion,nombre,apellido,telefono)
values(3,'1098127127','Carlos','Pinto','3163132233');
INSERT into cliente (id_tipo_documento,numero_identificacion,nombre,apellido,telefono)
values(1,'1098128128','Joaquin','Jimenez','3163177133');
INSERT into cliente (id_tipo_documento,numero_identificacion,nombre,apellido,telefono)
values(9,'1098129129','Gustavo','Leal','3163133883');
INSERT into cliente (id_tipo_documento,numero_identificacion,nombre,apellido,telefono)
values(2,'1092124124','Lina','Cerati','3163999133');


CREATE TABLE IF NOT EXISTS vehiculo(id INT NOT NULL AUTO_INCREMENT,
id_cliente INT,
marca VARCHAR2(50),
placa VARCHAR2(10),
modelo VARCHAR2(4),
linea VARCHAR2(50),
PRIMARY KEY(id));

INSERT into vehiculo (id_cliente,marca,placa,modelo,linea)
values(1,'Renault','XVX123','2021','Logan');
INSERT into vehiculo (id_cliente,marca,placa,modelo,linea)
values(2,'Chevrolet','KXK123','2022','Sail');
INSERT into vehiculo (id_cliente,marca,placa,modelo,linea)
values(3,'Volkswagen','KLL722','2012','Gol');
INSERT into vehiculo (id_cliente,marca,placa,modelo,linea)
values(4,'Volkswagen','KLL725','2015','Jetta');
INSERT into vehiculo (id_cliente,marca,placa,modelo,linea)
values(5,'BMW','MVO322','2019','M3');
INSERT into vehiculo (id_cliente,marca,placa,modelo,linea)
values(6,'Renault','XVX124','2018','Sandero');
INSERT into vehiculo (id_cliente,marca,placa,modelo,linea)
values(7,'Chevrolet','KLL212','2012','Cruze');
INSERT into vehiculo (id_cliente,marca,placa,modelo,linea)
values(8,'Kia','TOX123','2019','Rio');
INSERT into vehiculo (id_cliente,marca,placa,modelo,linea)
values(9,'Renault','SXA123','2019','Duster');
INSERT into vehiculo (id_cliente,marca,placa,modelo,linea)
values(10,'Mazda','PLO123','2017','2');

CREATE TABLE IF NOT EXISTS mantenimiento(id INT NOT NULL AUTO_INCREMENT,
id_vehiculo INT,
codigo_revision NUMERIC,
cambio_aceite BOOLEAN default false,
cambio_filtros BOOLEAN default false,
revision_frenos BOOLEAN default false,
precio NUMERIC(8,2),
otros VARCHAR2(200),
PRIMARY KEY(id));

INSERT into mantenimiento (id_vehiculo,codigo_revision,cambio_aceite,cambio_filtros,revision_frenos,precio)
values(1,101,TRUE,TRUE,TRUE,150000);
INSERT into mantenimiento (id_vehiculo,codigo_revision,cambio_aceite,cambio_filtros,revision_frenos,precio)
values(2,102,TRUE,TRUE,TRUE,160000);
INSERT into mantenimiento (id_vehiculo,codigo_revision,cambio_aceite,cambio_filtros,revision_frenos,precio)
values(3,103,TRUE,TRUE,FALSE,110000);
INSERT into mantenimiento (id_vehiculo,codigo_revision,cambio_aceite,cambio_filtros,revision_frenos,precio)
values(4,104,TRUE,TRUE,TRUE,190000);
INSERT into mantenimiento (id_vehiculo,codigo_revision,cambio_aceite,cambio_filtros,revision_frenos,precio)
values(5,105,TRUE,TRUE,FALSE,110000);
INSERT into mantenimiento (id_vehiculo,codigo_revision,cambio_aceite,cambio_filtros,revision_frenos,precio)
values(6,106,TRUE,TRUE,TRUE,200000);
INSERT into mantenimiento (id_vehiculo,codigo_revision,cambio_aceite,cambio_filtros,revision_frenos,precio)
values(7,107,TRUE,FALSE,TRUE,125000);
INSERT into mantenimiento (id_vehiculo,codigo_revision,cambio_aceite,cambio_filtros,revision_frenos,precio)
values(8,108,TRUE,FALSE,FALSE,100000);
INSERT into mantenimiento (id_vehiculo,codigo_revision,cambio_aceite,cambio_filtros,revision_frenos,precio)
values(9,109,TRUE,TRUE,FALSE,155000);
INSERT into mantenimiento (id_vehiculo,codigo_revision,cambio_aceite,cambio_filtros,revision_frenos,precio)
values(10,110,TRUE,TRUE,FALSE,165000);








