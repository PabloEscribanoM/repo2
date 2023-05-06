create database if not exists ClubDeportivo ;
use ClubDeportivo ;

create table if not exists Club(
Club_id int primary key auto_increment,
Club_nombre varchar (100),
Club_telefono varchar (10),
Club_identidadFiscal varchar (11),
Club_gastos double,
Club_ingresos double,
Club_resultadoTotal double,
Club_ingresos_previstos double

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
Mat_precio double,
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
Entrenadores_email varchar(50),
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
Administrador_Telefono varchar(10),
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
Futuro_socio_fechaNacimiento date,
Futuro_socio_cuentaBancaria varchar (12),
Futuro_socio_id_club int,
Futuro_socio_email varchar(100),

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

INSERT INTO Club VALUES (null, "Jabatos RB", "999999999", "xxxxxxxxx", 0.0, 0.0, 0.0, 0.0);







