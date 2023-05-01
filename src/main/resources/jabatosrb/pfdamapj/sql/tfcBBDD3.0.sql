create database if not exists ClubDeportivo ;
use ClubDeportivo ;

create table if not exists Club(
Club_id int primary key auto_increment,
Club_nombre varchar (100),
Club_telefono varchar (10),
Club_identidadFiscal varchar (11),
Club_gastos double,
Club_ingresos double,
Club_resultadoTotal double

);

create table if not exists  Socio(
Soc_id int  primary key auto_increment,
Soc_nombre varchar (100),
Soc_apellido varchar (100),
Soc_mote varchar (100),
Soc_cuentaBancaria varchar (100),
Soc_email varchar (100) unique,
Soc_pwd varchar (90),
Soc_numTelefono varchar (100) unique,
Soc_aporte double,
Soc_adeudo double,
Soc_fechaNacimiento date,
Soc_fechaAlta date,
Soc_id_Club int,

foreign key (Soc_id_Club) references Club(Club_id)
);

create table if not exists  Jugadores(
Jug_id int  primary key auto_increment,
Jug_temporada varchar (100),
Jug_numFicha varchar (100) unique,
Jug_Seccion varchar (100),
Jug_aporte double,
Jug_federadoPrevio boolean,
Jug_id_Socio int,
Jug_id_Club int,
Jug_adeudo double,

foreign key (Jug_id_Socio) references Socio(Soc_id),
foreign key (Jug_id_Club) references Club(Club_id)
);
create table if not exists Materiales(
Mat_id int primary key auto_increment,
Mat_nombre varchar (100),
Mat_stock int,
Mat_descripcion varchar (500),
Mat_Beneficiario varchar(2),
Mat_id_Club int ,

foreign key (Mat_id_Club) references Club(Club_id)
);
create table if not exists Escuela(
Esc_id int  primary key auto_increment,
Esc_nombre varchar (100),
Esc_Apellidos varchar (100),
Esc_Genero varchar(100),
Esc_numeroFicha varchar(100),
Esc_Tutor_email  varchar(100) unique,
Esc_Tutor_nombre varchar(100),
Esc_Tutor_apellidos  varchar(100),
Esc_Tutor_numTelefono  varchar(10),
Esc_FechaNacimiento date,
Esc_FechaAlta date,
Esc_aporte double,
Esc_id_Club int,
Esc_cuentaBancaria varchar (12),
Esc_adeudo double,

foreign key (Esc_id_Club) references Club(Club_id)
);



create table if not exists Entrenadores(
Entrenadores_id int primary key auto_increment,
Entrenadores_nombre varchar(100),
Entrenadores_apellidos varchar(100),
Entrenadores_telefono varchar (10),
Entrenadores_salario double,
Entrenadores_fechaAlta date,
Entrenadores_fechaBaja date,
Entrenadores_fechaNacimiento date,
Entrenadores_categoria varchar(100),
Entrenadores_Dni varchar(9),
Entrenadores_id_Club int,
Entrenadores_cuentaBancaria varchar(12),

foreign key (Entrenadores_id_Club) references Club(Club_id)
);

create table if not exists Administrador(
Administrador_id int primary key auto_increment,
Administrador_Nombre varchar(100),
Administrador_Apellidos varchar(100),
Administrador_Email varchar (100) unique,
Administrador_pwd varchar (90),
Administrador_Area varchar (100),
Administrador_DNI varchar (100),
Administrador_fechaAlta date,
Administrador_fechaBaja date,
Administrador_fechaNacimiento date,
Administrador_Salario double,
Administrador_cuentaBancaria varchar (12),
Administrador_Club_id int,

foreign key (Administrador_Club_id) references Club(Club_id)
);
create table if not exists Futuro_socio(
futuro_socio_id int primary key auto_increment,
futuro_socio_nombre varchar(100),
futuro_socio_apellidos varchar(100),
futuro_socio_telefono varchar (10),
Futuro_socio_descripcion varchar (200),
Futuro_socio_fechaAlta date,
Futuro_socio_fechaBaja date,
Futuro_socio_fechaNacimiento date,
Futuro_socio_cuentaBancaria varchar (12),
Futuro_socio_id_club int,

foreign key (Futuro_socio_id_club) references Club(Club_id)
);
create table if not exists Sugerencias(
Sugerencia_id int primary key auto_increment,
Sugerencia_nombre varchar(100),
Sugerencia_mote varchar(100),
Sugerencia_descripcion varchar(300),
Sugerencia_Socio_id int,
Sugerencia_Club_id int,

foreign key (Sugerencia_Socio_id) references Socio(Soc_id),
foreign key (Sugerencia_Club_id) references Club(Club_id)
);

create table if not exists Patrocinador(
Patrocinador_id int primary key auto_increment,
Patrocinador_nombre varchar(100),
Patrocinador_descripcion varchar(300),
Patrocinador_aporte double,
Patrocinador_cuentaBancaria varchar (12),
Patrocinador_Club_id int,
foreign key (Patrocinador_Club_id) references Club(Club_id)
);

INSERT INTO Club VALUES (null, "Jabatos RB", "999999999", "xxxxxxxxx", 0.0, 0.0, 0.0);

INSERT INTO Administrador (Administrador_id, Administrador_Nombre, Administrador_Email, Administrador_pwd, Administrador_Club_id) 
	VALUES (null, "MASTER", "master@master.com", "UW4Pp5ZMpPJ6dOzXTfEeZA==", 1); 

INSERT INTO Socio(Soc_id ,Soc_nombre ,Soc_apellido,Soc_mote ,Soc_cuentaBancaria ,Soc_email ,Soc_pwd ,Soc_aporte ,Soc_adeudo ,Soc_fechaNacimiento ,Soc_fechaAlta ,Soc_id_Club )
VALUES (null,"Jose","Gordon Alonso","Leti","X","xxx@gmail.com","UW4Pp5ZMpPJ6dOzXTfEeZA==",123,123,"1993-08-04","2023-04-13",1);

INSERT INTO Jugadores(Jug_id ,Jug_temporada ,Jug_numFicha   ,Jug_Seccion  ,Jug_aporte ,Jug_federadoPrevio  ,Jug_id_Club ,Jug_adeudo, Jug_id_Socio) 
VALUES (null,"2022-2023",1,"masculino",123,true,1,123, 1);

INSERT INTO Materiales(Mat_id ,Mat_nombre,Mat_stock ,Mat_Beneficiario,Mat_id_Club) 
VALUES(null,"Balon",12,"x",1);
INSERT INTO Escuela(Esc_id  ,Esc_nombre,Esc_Apellidos  ,
Esc_Genero ,
Esc_numeroFicha ,
Esc_Tutor_email   ,
Esc_Tutor_nombre ,
Esc_Tutor_apellidos  ,
Esc_Tutor_numTelefono  ,
Esc_FechaNacimiento ,
Esc_FechaAlta ,
Esc_aporte ,
Esc_id_Club ,
Esc_cuentaBancaria  ,
Esc_adeudo ) VALUES(null,"Rigoberto","Gonzalez Martinez","m","3","rigobertotutor@gmail.com","tutor","tutorA1 tutorA2","x","1993-08-04","1993-08-04",1234,1,"x",123);
INSERT INTO Entrenadores(
Entrenadores_id  ,
Entrenadores_nombre ,
Entrenadores_apellidos ,
Entrenadores_telefono  ,
Entrenadores_salario ,
Entrenadores_fechaAlta ,
Entrenadores_fechaBaja ,
Entrenadores_fechaNacimiento ,
Entrenadores_categoria ,
Entrenadores_Dni,
Entrenadores_id_Club ,
Entrenadores_cuentaBancaria ) VALUES(null,"Francisco","Bas Cordobes","xxxxxxxxxx",1234,"1993-08-04","1993-08-04","1993-08-04","masc","2354363",1,"xx");
INSERT INTO Administrador(
Administrador_id,
Administrador_Nombre ,
Administrador_Apellidos ,
Administrador_Email  ,
Administrador_pwd  ,
Administrador_Area  ,
Administrador_DNI  ,
Administrador_fechaAlta ,
Administrador_fechaBaja ,
Administrador_fechaNacimiento ,
Administrador_Salario ,
Administrador_cuentaBancaria,
Administrador_Club_id ) VALUES (null,"Maria","Bascones Mas","mariabmas@hotmail.com","UW4Pp5ZMpPJ6dOzXTfEeZA==","Tesoreria","xxx","2023-04-13",null,"1993-02-15",0,"x",1);
INSERT INTO  Sugerencias(
Sugerencia_id ,
Sugerencia_nombre,
Sugerencia_mote ,
Sugerencia_descripcion ,
Sugerencia_Socio_id ,
Sugerencia_Club_id ) VALUES(null,"Jose","Leti","Falta cerveza",null,1);
INSERT INTO Patrocinador(
Patrocinador_id,
Patrocinador_nombre,
Patrocinador_descripcion ,
Patrocinador_aporte ,
Patrocinador_cuentaBancaria ,
Patrocinador_Club_id )
VALUES (null,"Kappa","x",123,"xx",1);





