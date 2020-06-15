drop database DBHospitalInfectologiaOw2018480;
create database DBHospitalInfectologiaOw2018480;
use DBHospitalInfectologiaOw2018480;

create table Medicos(
	codMedico int not null primary key auto_increment,
	licenciaMedica int (10),
	nombre varchar (100),
	apellidos varchar (100),
	horaEntrada datetime,
	horaSalida datetime,
	turnoMaximo int default 0,
	sexo varchar (20)
);


create table TelefonosMedicos(
	codTelefonoMedico int not null primary key auto_increment,
	telefonoPersonal varchar (15),
	telefonoTrabajo varchar (15),
	codMedico int,
    foreign key (codMedico) references Medicos(codMedico) on delete cascade
); 

create table Horarios(
	codHorario int not null primary key auto_increment,
	horarioInicio datetime,
	horarioSalida datetime,
	lunes tinyint,
    martes tinyint,
    miercoles tinyint,
    jueves tinyint,
    viernes tinyint
);

create table Pacientes(
	codPaciente int not null primary key auto_increment,
	dpi varchar (20),
	apellidos varchar (100),
	nombre varchar (100),
	fechaNacimiento varchar (50),
	edad int default 0,
	direccion varchar (150),
	ocupacion varchar (50),
	sexo varchar (15)
);
select * from pacientes;

create table Especialidades(
	codEspecialidad int not null primary key auto_increment,
	nomEspecialidad varchar (45)
);

create table Areas(
	codArea int not null primary key auto_increment,
	nomArea varchar (45)
);

create table Cargos(
	codCargo int not null primary key auto_increment,
	nomCargo varchar (45)
);

create table ResponsableTurno(
	codResponsableTurno int not null primary key auto_increment,
	nomResponsable varchar (75),
	apellidosResponsable varchar (45),
	telefonoPersonal varchar (10),
	codArea int,
	codCargo int,
    foreign key (codArea) references Areas(codArea)on delete cascade,
    foreign key (codCargo) references Cargos(codCargo)on delete cascade
); 

create table MedicoEspecialidad(
	codMedicoEspecialidad int not null primary key auto_increment,
	codMedico int,
	codHorario int,
    codEspecialidad int,
    foreign key (codEspecialidad) references Especialidades(codEspecialidad)on delete cascade,
    foreign key (codHorario) references Horarios(codHorario)on delete cascade,
    foreign key (codMedico) references Medicos(codMedico)on delete cascade
);

create table Turno(
	codTurno int not null primary key auto_increment,
	fechaTurno date,
	fechaCita date,
	valorCita decimal (10,2),
	codMedicoEspecialidad int,
	codResponsableTurno int,
	codPaciente int,
    foreign key (codMedicoEspecialidad) references MedicoEspecialidad(codMedicoEspecialidad) on delete cascade,
	foreign key (codResponsableTurno) references ResponsableTurno(codResponsableTurno) on delete cascade,
	foreign key (codPaciente) references Pacientes(codPaciente) on delete cascade
);

create table ContactoUrgencia(
	codContactoUrgencia int not null primary key auto_increment,
	nombres varchar (100),
	apellidos varchar (100),
	numContacto varchar (10),
	codPaciente int,
    foreign key (codPaciente) references Pacientes(codPaciente) on delete cascade
);


create table usuarios(
	codUsuario int not null primary key auto_increment,
    usuarioLogin varchar (45),
    usuarioContrasena varchar (45),
    usuarioEstado tinyint,
    usuarioFecha date,
    usuarioHora time,
    codTipoUsuario int,
    foreign key (codTipoUsuario) references tipoUsuario(codTipoUsuario) on delete cascade
);

create table tipoUsuario(
	codTipoUsuario int not null primary key auto_increment,
	descripcion varchar (150)
);

-- Procedimientos Almacenados

delimiter $$
create procedure sp_addTipoUsuario(p_descripcion varchar (150))
begin
	insert into tipoUsuario (descripcion)
    values (p_descripcion);
end$$
delimiter ;


-- Procedimientos Almacenados
delimiter $$

create procedure sp_addUsuarios (p_usuarioLogin varchar (45), p_usuarioContrasena varchar (45), p_usuarioEstado tinyint , p_usuarioFecha date, p_usuarioHora time, p_codTipoUsuario int)
begin
	insert into usuarios (usuarioLogin, usuarioContrasena, usuarioEstado, usuarioFecha, usuarioHora, codTipoUsuario)
    values(p_usuarioLogin, p_usuarioContrasena, p_usuarioEstado, p_usuarioFecha, p_usuarioHora, p_codTipoUsuario);
end$$
delimiter ;

-- Procedimiento Eliminar
delimiter $$
create procedure sp_deleteUsuarios (p_codUsuario int)
begin
delete from usuarios
where codusuario = p_codUsuario;
end$$
delimiter ;

-- Procedimiento Listar

delimiter $$
create procedure sp_listarUsuarios()
begin
select M.codUsuario as codUsuario, usuarioLogin, usuarioContrasena, usuarioEstado, usuarioFecha, usuarioHora
from usuarios M;
end$$
delimiter ;

call sp_listarUsuarios();

-- Procedimientos Almacenados


DELIMITER $$
create procedure sp_addMedicos(p_licenciaMedica int, p_nombre varchar(100), p_apellidos varchar(100),p_horaEntrada datetime, p_horaSalida datetime, p_sexo varchar(15))
begin
	insert into Medicos (licenciaMedica, nombre, apellidos, horaEntrada, horaSalida,  sexo)
	values(p_licenciaMedica, p_nombre, p_apellidos, p_horaEntrada, p_horaSalida,  p_sexo);
end$$
delimiter ;
call sp_addMedicos(2054987321, 'Zoe', 'Rodriguez', '2019-05-09 07:00:00', '2019-05-09 17:00:00', 'Masculino');
call sp_addMedicos(2058973214, 'Karla', 'Sacarias', '2019-05-09 07:00:00', '2019-05-09 17:00:00', 'Femenino');
call sp_addMedicos(1569875362, 'Zed', 'Orozco', '2019-05-09 07:00:00', '2019-05-09 17:00:00', 'Masculino');
call sp_addMedicos(1897536985, 'Micolash', 'Orellana', '2019-05-09 07:00:00', '2019-05-09 17:00:00',  'Femenino');
call sp_addMedicos(2098756325, 'Vladimir', 'Tarantino', '2019-05-09 07:00:00', '2019-05-09 17:00:00',  'Masculino');

select * from Medicos;
--  Procedimiento Editar 

delimiter $$
create procedure sp_updateMedicos(p_codMedico int, p_licenciaMedica int, p_nombre varchar(100), p_apellidos varchar(100),p_horaEntrada datetime, p_horaSalida datetime,  p_sexo varchar(15))
begin 
update Medicos
   set  licenciaMedica = p_licenciaMedica, 
   nombre = p_nombre, 
   apellidos = p_apellidos, 
   horaEntrada = p_horaEntrada, 
   horaSalida = p_horaSalida, 
   sexo = p_sexo 
        where codMedico = p_codMedico;
end$$
delimiter ;
call sp_updateMedicos(1, 1963547896, 'Isaac', 'Sacarias', '2019-05-09 07:00:00', '2019-05-09 17:00:00', 'Masculino');

select * from Medicos;

--  Procedimiento Eliminar
delimiter $$
 create procedure sp_DeleteMedicos(p_codMedico int)
 begin
 delete from Medicos
 where codMedico = p_codMedico;
 end$$
delimiter ;
call sp_DeleteMedicos(5);

select * from Medicos;

-- Procedimiento Listar 
delimiter $$
 create procedure sp_ListarMedicos()
 begin
 select M.codMedico as codMedico, licenciaMedica, nombre, apellidos, horaEntrada, horaSalida, turnoMaximo, sexo
 from Medicos M;
 end$$
 delimiter ;
call sp_ListarMedicos();

-- Procedimiento Buscar
DELIMITER $$
create procedure sp_BuscarMedico (in p_codMedico int)
begin
		select codMedico, licenciaMedica, nombre, apellidos, horaEntrada, horaSalida, turnoMaximo, sexo from Medicos
		where p_codMedico = codMedico;
end$$
delimiter ;

call sp_BuscarMedico (1);

-- Procedimientos Almacenados 
DELIMITER $$
create procedure sp_addTelefonosMedico(p_telefonoPersonal varchar(15), p_telefonoTrabajo varchar(15), p_codMedico int)
begin
	insert into telefonosMedicos(telefonoPersonal, telefonoTrabajo, codMedico)
    values(p_telefonoPersonal, p_telefonoTrabajo, p_codMedico);
end$$
delimiter ;

call sp_addTelefonosMedico('54789536', '23598756', 1);
call sp_addTelefonosMedico('48793261', '22245987', 2);
call sp_addTelefonosMedico('47856963', '28596354', 3);
call sp_addTelefonosMedico('53698754', '21245798', 4);


-- Procedimiento Editar
DELIMITER $$
create procedure sp_updateTelefonosMedicos(p_codTelefonoMedico int, p_telefonoPersonal varchar (15), p_telefonoTrabajo varchar (15), p_codMedico int)
begin
		update TelefonosMedicos 
        set telefonoPersonal = p_telefonoPersonal,
		telefonoTrabajo = p_telefonoTrabajo,
        codMedico = p_codMedico
        where codTelefonoMedico = p_codTelefonoMedico;
end$$
delimiter ;
 
call  sp_updateTelefonosMedicos(1, '54236985', '24569863', 4)

-- Procedimiento Eliminar

delimiter $$
 create procedure sp_deleteTelefonosMedicos(p_codTelefonoMedico int)
 begin
 delete from TelefonosMedicos
 where codTelefonoMedico = p_codTelefonoMedico;
 end$$
delimiter ;

call sp_deleteTelefonosMedicos(5);

select * from TelefonosMedicos;

--  Procedimiento Listar 

delimiter $$
 create procedure sp_ListarTelefonosMedicos()
 begin
 select T.codTelefonoMedico as codTelefonoMedico, telefonoPersonal, telefonoTrabajo, codMedico
 from TelefonosMedicos T;
 end$$
 delimiter ;
 
call sp_ListarTelefonosMedicos();

-- Procedimiento Buscar 

DELIMITER $$
create procedure sp_buscarTelefonoMedico (in p_codTelefonoMedico int)
begin
		select codTelefonoMedico, telefonoPersonal, telefonoTrabajo, codMedico from TelefonosMedicos
		where p_codTelefonoMedico = codTelefonoMedico;
end$$
delimiter ;

call sp_buscarTelefonoMedico (2);

--  PROCEDIMIENTOS ALMACENADOS
DELIMITER $$
create procedure sp_addHorarios(p_horarioInicio datetime, p_horarioSalida datetime, p_lunes TINYINT, p_martes TINYINT, p_miercoles TINYINT, p_jueves TINYINT, p_viernes TINYINT)
begin
	insert into Horarios(horarioInicio, horarioSalida, lunes, martes, miercoles, jueves, viernes)
    values(p_horarioInicio, p_horarioSalida, p_lunes, p_martes, p_miercoles, p_jueves, p_viernes);
end$$
delimiter ;
 
call sp_addHorarios('2019-05-09 07:00:00', '2019-05-09 17:00:00', 0, 1, 1, 0, 1);
call sp_addHorarios('2019-05-09 07:00:00', '2019-05-09 17:00:00', 0, 1, 0, 0, 1);
call sp_addHorarios('2019-05-09 07:00:00', '2019-05-09 17:00:00', 1, 1, 0, 1, 1);
call sp_addHorarios('2019-05-09 07:00:00', '2019-05-09 17:00:00', 0, 1, 0, 1, 1);
call sp_addHorarios('2019-05-09 07:00:00', '2019-05-09 17:00:00', 1, 0, 0, 1, 0);

-- Procedimiento Editar 

DELIMITER $$
create procedure sp_updateHorarios(p_codHorario int, p_horarioInicio datetime, p_horarioSalida datetime, p_lunes TINYINT, p_martes TINYINT, p_miercoles TINYINT, p_jueves TINYINT, p_viernes TINYINT)
begin
		update Horarios
        set horarioInicio = p_horarioInicio,
		horarioSalida = p_horarioSalida,
        lunes = p_lunes, 
        martes = p_martes, 
        miercoles = p_miercoles, 
        jueves = p_jueves, 
        viernes = p_viernes
        where codHorario = p_codHorario;
end$$
delimiter ;
 
call sp_updateHorarios(1, '2019-05-09 07:00:00', '2019-05-09 17:00:00', 1, 0, 1, 1, 0);

-- Procedimiento Eliminar 

delimiter $$
 create procedure sp_deleteHorarios(p_codHorario int)
 begin
 delete from Horarios
 where codHorario = p_codHorario;
 end$$
delimiter ;

call sp_deleteHorarios(5);

select * from Horarios;

-- Procedimiento Listar

delimiter $$
 create procedure sp_ListarHorarios()
 begin
 select H.codHorario as codHorario, horarioInicio, horarioSalida, lunes, martes, miercoles, jueves, viernes
 from Horarios H;
 end$$
 delimiter ;
 
call sp_ListarHorarios();

-- Procedimietno Buscar 

DELIMITER $$
create procedure sp_buscarHorarios(in p_codHorario int)
begin
		select codHorario, horarioInicio, horarioSalida, lunes, martes, miercoles, jueves, viernes from Horarios
		where p_codHorario = codHorario;
end$$
delimiter ;

call sp_buscarHorarios (2); 
 
-- PROCEDIMIENTOS ALMACENADOS 

DELIMITER $$
create procedure sp_addEspecialidades(p_nomEspecialidad varchar(45))
begin
	insert into Especialidades(nomEspecialidad)
    values(p_nomEspecialidad);
end$$
delimiter ;

call sp_addEspecialidades ('Pediatria');
call sp_addEspecialidades ('Infectologia');
call sp_addEspecialidades ('Psiquiatria');
call sp_addEspecialidades ('Rehabilitacion');
call sp_addEspecialidades ('Reumatologia');

-- Procedimiento Editar 

DELIMITER $$
create procedure sp_updateEspecialidades(p_codEspecialidad int, p_nomEspecialidad varchar (45))
begin
		update Especialidades
        set nomEspecialidad = p_nomEspecialidad
        where codEspecialidad = p_codEspecialidad;
end$$
delimiter ;
 
call sp_updateEspecialidades(1, 'Cirujano');

-- Procedimiento Eliminar 

delimiter $$
 create procedure sp_deleteEspecialidades(p_codEspecialidad int)
 begin
 delete from Especialidades
 where codEspecialidad = p_codEspecialidad;
 end$$
delimiter ;

call sp_deleteEspecialidades(5);

select * from Especialidades;

-- Procedimiento Listar

delimiter $$
 create procedure sp_ListarEspecialidades()
 begin
 select E.codEspecialidad as codEspecialidad, nomEspecialidad
 from Especialidades E;
 end$$
 delimiter ;
 
call sp_ListarEspecialidades();

-- procedimiento Buscar 

DELIMITER $$
create procedure sp_BuscarEspecialidad (in p_codEspecialidad int)
begin
		select codEspecialidad, nomEspecialidad from Especialidades
		where p_codEspecialidad = codEspecialidad;
end$$
delimiter ;

call sp_BuscarEspecialidad (2); 


--  PROCEDIMIENTOS ALMACENADOS 

DELIMITER $$
create procedure sp_addMedicoEspecialidad(p_codMedico int, p_codEspecialidad int, p_codHorario int)
begin
	insert into MedicoEspecialidad(codMedico, codEspecialidad, codHorario)
    values(p_codMedico, p_codEspecialidad, p_codHorario);
end$$
delimiter ;

call sp_addMedicoEspecialidad (1, 1, 1);
call sp_addMedicoEspecialidad (2, 2, 2);
call sp_addMedicoEspecialidad (3, 3, 3);
call sp_addMedicoEspecialidad (4, 4, 4);


-- Procedimiento Editar
DELIMITER $$
create procedure sp_updateMedicoEspecialidad(p_codMedicoEspecialidad int, p_codMedico int, p_codHorario int, p_codEspecialidad int)
begin
		update MedicoEspecialidad
        set codMedico = p_codMedico,
        codHorario = p_codHorario,
        codEspecialidad = p_codEspecialidad
        where codMedicoEspecialidad = p_codMedicoEspecialidad;
end$$
delimiter ;
 
call sp_updateMedicoEspecialidad(1, 1, 1, 1);

-- Procedimiento Eliminar 
delimiter $$
 create procedure sp_deleteMedicoEspecialidad(p_codMedicoEspecialidad int)
 begin
 delete from MedicoEspecialidad
 where codMedicoEspecialidad = p_codMedicoEspecialidad;
 end$$
delimiter ;

call sp_deleteMedicoEspecialidad(5);

select * from MedicoEspecialidad;

-- PROCEDIMIENTOS Listar

delimiter $$
 create procedure sp_ListarMedicoEspecialidad()
 begin
 select M.codMedicoEspecialidad as codMedicoEspecialidad, codMedico, codHorario, codEspecialidad
 from MedicoEspecialidad M;
 end$$
 delimiter ;
 
call sp_ListarHorarios();

-- PROCEDIMIENTOS Buscar

DELIMITER $$
create procedure sp_BuscarMedicoEspecialidad (in p_codMedicoEspecialidad int)
begin
		select codMedicoEspecialidad, codMedico, codHorario, codEspecialidad from MedicoEspecialidad
		where p_codMedicoEspecialidad = codMedicoEspecialidad;
end$$
delimiter ;

call sp_BuscarMedicoEspecialidad (2); 

-- PROCEDIMIENTOS ALMACENADOS
DELIMITER $$
create procedure sp_addPacientes(p_dpi varchar(20),p_apellidos varchar(100), p_nombre varchar(100),  p_fechaNacimiento varchar(50), p_direccion varchar(150), p_ocupacion varchar(150), p_sexo varchar(15))
begin
	insert into Pacientes(dpi,apellidos, nombre,  fechaNacimiento, direccion, ocupacion, sexo)
    values(p_dpi,p_apellidos, p_nombre,  p_fechaNacimiento, p_direccion, p_ocupacion, p_sexo);
end$$
delimiter ;

call sp_addPacientes('3256 35698 0101', 'Velasquez Avila', 'Diego', '1996/02/06',  '3ra calle 6-50 zona6', 'Ingeniero en sistemas', 'Masculino');
call sp_addPacientes('5647 23698 0101', 'Escamilla Toledo', 'Hector', '1991/01/01',  '2da calle 7-75 zona8', 'Juez', 'Masculino');
call sp_addPacientes('2346 78956 0101', 'Castro Reyes', 'Derica', '1999/04/15',  '4ta calle 2-64 zona2', 'Secretaria', 'Femenina');
call sp_addPacientes('5789 63256 0101', 'Castillo Lora ', 'Carlos', '1976/04/23', '5ta calle 3-79 zona3', 'Gerente Empresarial', 'Masculino');
call sp_addPacientes('3256 35698 0101', 'Gonzales Ferrer ', 'Gustavo', '1985/03/20', '7ma calle 4-48 zona12', 'Abogado', 'Masculino');

-- PROCEDIMIENTOS Editar 

delimiter $$
create procedure sp_updatePacientes(p_codPaciente int, p_dpi varchar(20),p_apellidos varchar(100), p_nombre varchar(100),  p_fechaNacimiento varchar (50),  p_direccion varchar(150), p_ocupacion varchar(150), p_sexo varchar(15))
begin 
update Pacientes
   set   dpi = p_dpi, 
   apellidos = p_apellidos, 
   nombre = p_nombre, 
   fechaNacimiento = p_fechaNacimiento, 
   direccion = p_direccion, 
   ocupacion = p_ocupacion, 
   sexo = p_sexo
        where codPaciente = p_codPaciente;
end$$
delimiter ;

call sp_updatePacientes(1, '4235 56987 0101', 'Lopez Aguilar', 'Estuardo', '1993/03/20', '4ta calle 7-32 zona3', 'Doctor', 'Masculino');

select * from Pacientes;

--  PROCEDIMIENTOS Eliminar

delimiter $$
 create procedure sp_deletePacientes(p_codPaciente int)
 begin
 delete from Pacientes
 where codPaciente = p_codPaciente;
 end$$
delimiter ;

call sp_deletePacientes(6);

select * from Pacientes;

-- PROCEDIMIENTOS Listar

delimiter $$
 create procedure sp_ListarPacientes()
 begin
 select P.codPaciente as  codPaciente, dpi, apellidos, nombre, fechaNacimiento, edad, direccion, ocupacion, sexo
 from Pacientes P;
 end$$
 delimiter ;
 
call sp_ListarPacientes();

-- PROCEDIMIENTOS Buscar

DELIMITER $$
create procedure sp_BuscarPacientes(p_codPaciente int)
begin
		select codPaciente, dpi, apellidos, nombre, fechaNacimiento, edad, direccion, ocupacion, sexo from Pacientes
		where codPaciente = p_codPaciente;
end$$
delimiter ;

call sp_BuscarPacientes(1)

-- PROCEDIMIENTOS ALMACENADOS 
DELIMITER $$
create procedure sp_addContactoUrgencia(p_nombres varchar(100), p_apellidos varchar(100), p_numContacto varchar(10), p_codPaciente int)
begin
	insert into contactoUrgencia(nombres, apellidos, numContacto, codPaciente)
    values(p_nombres, p_apellidos, p_numContacto, p_codPaciente);
end$$
delimiter ;

call sp_addContactoUrgencia ('Alberto', 'Rodriguez', '56988736', 1);
call sp_addContactoUrgencia ('Guillermo', 'Leon', '59632658', 2);
call sp_addContactoUrgencia ('Manuel', 'Albocado', '45693678', 3);
call sp_addContactoUrgencia ('Jose', 'Castillo', '53263956', 4);


--  PROCEDIMIENTOS Editar 

delimiter $$
create procedure sp_updateContactoUrgencia(p_codContactoUrgencia int, p_nombres varchar(100), p_apellidos varchar(100), p_numContacto varchar(10), p_codPaciente int)
begin 
update ContactoUrgencia
   set  nombres = p_nombres,  
   apellidos = p_apellidos, 
   numContacto =p_numContacto, 
   codPaciente = p_codPaciente
        where codContactoUrgencia = p_codContactoUrgencia;
end$$
delimiter ;

call sp_updateContactoUrgencia(1, 'Victor', 'Poncio', '32435088', 1);

select * from ContactoUrgencia;

-- PROCEDIMIENTOS Eliminar

delimiter $$
 create procedure sp_deleteContactoUrgencia(p_codContactoUrgencia int)
 begin
 delete from ContactoUrgencia
 where codContactoUrgencia = p_codContactoUrgencia;
 end$$
delimiter ;

call sp_deleteContactoUrgencia(5);

select * from ContactoUrgencia;

--  PROCEDIMIENTOS Listar 
delimiter $$
 create procedure sp_ListarContactoUrgencia()
 begin
 select C.codContactoUrgencia as codContactoUrgencia, nombres, apellidos, numContacto, codPaciente
 from ContactoUrgencia C;
 end$$
 delimiter ;
 
call sp_ListarContactoUrgencia();

-- PROCEDIMIENTOS Buscar 
DELIMITER $$
create procedure sp_BuscarContactoUrgencia(p_codContactoUrgencia int)
begin
		select codContactoUrgencia, nombres, apellidos, numContacto, codPaciente from ContactoUrgencia
		where codContactoUrgencia = p_codContactoUrgencia;
end$$
delimiter ;

call sp_BuscarContactoUrgencia(1)

--  PROCEDIMIENTOS ALMACENADOS 

DELIMITER $$
create procedure sp_addAreas(p_nomArea varchar(45))
begin
	insert into Areas(nomArea)
    values(p_nomArea);
end$$
delimiter ;

call sp_addAreas ('Infectologia');
call sp_addAreas ('Pediatria');
call sp_addAreas ('Psiquiatria');
call sp_addAreas ('Rehabilitacion');
call sp_addAreas ('Reumatologia');

--  PROCEDIMIENTOS Editar 

delimiter $$
create procedure sp_updateAreas(p_codArea int, p_nomArea varchar(45))
begin 
update Areas
   set  nomArea = p_nomArea
        where codArea = p_codArea;
end$$
delimiter ;

call sp_updateAreas(1, 'Cirugia');

select * from Areas;

--  PROCEDIMIENTOS Eliminar ==========================================


delimiter $$
 create procedure sp_deleteAreas(p_codArea int)
 begin
 delete from Areas
 where codArea = p_codArea;
 end$$
delimiter ;

call sp_deleteAreas(5);

select * from Areas;

-- = PROCEDIMIENTOS Listar

delimiter $$
 create procedure sp_ListarAreas()
 begin
 select A.codArea as codArea, nomArea
 from Areas A;
 end$$
 delimiter ;
 
call sp_ListarAreas();

-- ========================================== PROCEDIMIENTOS Buscar ==========================================


DELIMITER $$
create procedure sp_BuscarAreas(p_codArea int)
begin
		select codArea, nomArea from Areas
		where codArea = p_codArea;
end$$
delimiter ;

call sp_BuscarAreas(1)

-- ========================================== PROCEDIMIENTOS ALMACENADOS 

DELIMITER $$
create procedure sp_addCargos(p_nomCargo varchar(45))
begin
	insert into Cargos(nomCargo)
    values(p_nomCargo);
end$$
delimiter ; 

call sp_addCargos ('Medico');
call sp_addCargos ('Enfermera');
call sp_addCargos ('Medico');
call sp_addCargos ('Enfermera');
call sp_addCargos ('Medico');

-- ========================================== PROCEDIMIENTOS Editar ==========================================
-- 

delimiter $$
create procedure sp_updateCargos(p_codCargo int, p_nomCargo varchar(45))
begin 
update Cargos
   set  nomCargo = p_nomCargo
        where codCargo = p_codCargo;
end$$
delimiter ;

call sp_updateCargos(1, 'Enfermero');

select * from Cargos;

-- ========================================== PROCEDIMIENTOS Eliminar ==========================================
-- 

delimiter $$
 create procedure sp_deleteCargos(p_codCargo int)
 begin
 delete from Cargos
 where codCargo = p_codCargo;
 end$$
delimiter ;

call sp_deleteCargos(5);

select * from Cargos;

-- ========================================== PROCEDIMIENTOS Listar ==========================================

delimiter $$
 create procedure sp_ListarCargos()
 begin
 select C.codCargo as codCargo, nomCargo
 from Cargos C;
 end$$
 delimiter ;
 
call sp_ListarCargos();

-- ========================================== PROCEDIMIENTOS Buscar ==========================================
-- 

DELIMITER $$
create procedure sp_BuscarCargos(p_codCargo int)
begin
		select codCargo, nomCargo from Cargos
		where codCargo = p_codCargo;
end$$
delimiter ;

call sp_BuscarCargos(1)

-- ========================================== PROCEDIMIENTOS ALMACENADOS 

DELIMITER $$
create procedure sp_addResponsableTurno(p_nomResponsable varchar(75), p_apellidosResponsable varchar(45),p_telefonoPersonal varchar(10), p_codArea int, p_codCargo int)
begin
	insert into ResponsableTurno(nomResponsable, apellidosResponsable, telefonoPersonal, codArea, codCargo)
    values(p_nomResponsable, p_apellidosResponsable, p_telefonoPersonal, p_codArea, p_codCargo);
end$$
delimiter ;

call sp_addResponsableTurno ('Diego', 'Avila', '54789536', 1, 1);
call sp_addResponsableTurno ('Karla', 'Rodas', '48793261', 2, 2);
call sp_addResponsableTurno ('Juan', 'Soria', '47856963', 3, 3);
call sp_addResponsableTurno ('Luisa', 'Leon', '53698754', 4, 4);

-- ========================================== PROCEDIMIENTOS Editar 

delimiter $$
create procedure sp_updateResponsableTurno(p_codResponsableTurno int, p_nomResponsable varchar(75), p_apellidosResponsable varchar(45),p_telefonoPersonal varchar(10), p_codArea int, p_codCargo int)
begin 
update ResponsableTurno
   set  nomResponsable = p_nomResponsable,
   apellidosResponsable = p_apellidosResponsable,
   telefonoPersonal = p_telefonoPersonal, 
   codArea = p_codArea, 
   codCargo = p_codCargo
        where codResponsableTurno = p_codResponsableTurno;
end$$
delimiter ;

call sp_updateResponsableTurno(1, 'Julio', 'Castillo', '53236987', 1, 1);

select * from ResponsableTurno;

-- ========================================== PROCEDIMIENTOS Eliminar 
delimiter $$
 create procedure sp_deleteResponsableTurno(p_codResponsableTurno int)
 begin
 delete from ResponsableTurno
 where codResponsableTurno = p_codResponsableTurno;
 end$$
delimiter ;

call sp_deleteResponsableTurno(5);

select * from ResponsableTurno;

-- ========================================== PROCEDIMIENTOS Listar
delimiter $$
 create procedure sp_ListarResponsableTurno()
 begin
 select R.codResponsableTurno as codResponsableTurno, nomResponsable, apellidosResponsable, telefonoPersonal, codArea, codCargo
 from ResponsableTurno R;
 end$$
 delimiter ;
 
call sp_ListarResponsableTurno();

-- ========================================== PROCEDIMIENTOS Buscar

DELIMITER $$
create procedure sp_BuscarResponsableTurno(p_codResponsableTurno int)
begin
		select codResponsableTurno, nomResponsable, apellidosResponsable, telefonoPersonal, codArea, codCargo from ResponsableTurno
		where codResponsableTurno = p_codResponsableTurno;
end$$
delimiter ;

call sp_BuscarResponsableTurno(1)

-- ========================================== PROCEDIMIENTOS ALMACENADOS 
DELIMITER $$
create procedure sp_addTurno(p_fechaTurno DATE, p_fechaCita DATE, p_valorCita decimal(10,2), p_codMedicoEspecialidad int, p_codResponsableTurno int, p_codPaciente int)
begin
	insert into Turno(fechaTurno, fechaCita, valorCita, codMedicoEspecialidad, codResponsableTurno, codPaciente)
    values(p_fechaTurno, p_fechaCita, p_valorCita, p_codMedicoEspecialidad, p_codResponsableTurno, p_codPaciente);
end$$ 
delimiter ;

call sp_addTurno ('2019/03/23', '2019/03/26', 50.50, 1, 1, 1);
call sp_addTurno ('2019/02/05', '2019/02/10', 45.25, 2, 2, 2);
call sp_addTurno ('2019/04/12', '2019/04/13', 90.80, 3, 3, 3);
call sp_addTurno ('2019/05/20', '2019/03/24', 50.50, 4, 4, 4);


-- ========================================== PROCEDIMIENTOS Editar

delimiter $$
create procedure sp_updateTurno(p_codTurno int, p_fechaTurno DATE, p_fechaCita DATE, p_valorCita decimal(10,2), p_codMedicoEspecialidad int, p_codResponsableTurno int, p_codPaciente int)
begin 
update Turno
   set  fechaTurno = p_fechaTurno, 
   fechaCita = p_fechaCita, 
   valorCita = p_valorCita, 
   codMedicoEspecialidad = p_codMedicoEspecialidad, 
   codResponsableTurno = p_codResponsableTurno, 
   codPaciente = p_codPaciente
        where codTurno = p_codTurno;
end$$
delimiter ;

call sp_updateTurno(1, '2019/05/10', '2019/05/12', 45.25, 1, 1, 1);

select * from Turno;

-- ========================================== PROCEDIMIENTOS Eliminar

delimiter $$
 create procedure sp_deleteTurno(p_codTurno int)
 begin
 delete from Turno
 where codTurno = p_codTurno;
 end$$
delimiter ;

call sp_deleteTurno(5);

select * from Turno;

-- ========================================== PROCEDIMIENTOS Listar 

delimiter $$
 create procedure sp_ListarTurno()
 begin
 select T.codTurno as codTurno, fechaTurno, fechaCita, valorCita, codMedicoEspecialidad, codResponsableTurno, codPaciente
 from Turno T;
 end$$
 delimiter ;
 
call sp_ListarTurno();

-- ========================================== PROCEDIMIENTOS Buscar
DELIMITER $$
create procedure sp_BuscarTurno(p_codTurno int)
begin
		select codTurno, fechaTurno, fechaCita, valorCita, codMedicoEspecialidad, codResponsableTurno, codPaciente from Turno
		where codTurno = p_codTurno;
end$$
delimiter ;

call sp_BuscarTurno(1)

-- ========================================== TRIGGERS 
delimiter $$
Create trigger tr_Pacientes 
	before insert on Pacientes
    for each row 
    Begin
    
    set new.edad = timestampdiff(year, new.fechaNacimiento, now());
    
    end$$  
delimiter;


delimiter $$
Create trigger tr_Pacientes_Update
	before update on Pacientes
    for each row 
    Begin
    
    set new.edad = timestampdiff(year, new.fechaNacimiento, now());
    
    end $$
    delimiter; 

delimiter $$
create trigger tr_Turno_Insert
before insert on MedicoEspecialidad
for each row 
begin
		Declare suma int;
			select sum(Horarios.lunes + Horarios.martes + Horarios.miercoles + Horarios.jueves + Horarios.viernes)
			into suma From Horarios 
            where codHorario = new.codHorario;
            update Medicos 
            set turnoMaximo = suma 
            where codMedico = new.codMedico;
end$$
delimiter ;

	
delimiter $$
create trigger tr_Turno_Update
before update on MedicoEspecialidad
for each row 
begin
		Declare suma int;
			select sum(Horarios.lunes + Horarios.martes + Horarios.miercoles + Horarios.jueves + Horarios.viernes)
			into suma From Horarios 
            where codHorario = new.codHorario;
            update Medicos 
            set turnoMaximo = suma 
            where codMedico = new.codMedico;
end$$
delimiter ;


-- ========================================== INNER JOIN  ===========================================================

delimiter $$
create procedure sp_MedicoHorario(in p_codMedico int)
begin
	select  h. * from Horarios h
    inner join MedicoEspecialidad me
    on h.codHorario = me.codMedicoEspecialidad
    where me.codMedico = p_codMedico;
end $$
delimiter ;

delimiter $$
create procedure sp_MedicoEspecialidad(in p_codMedico int)
	begin
    select e. * from Especialidades e
    Inner join MedicoEspecialidad me
    on e.codEspecialidad = me.codMedicoEspecialidad
    Where me.codMedico = p_codMedico;
    end $$
delimiter ;

Delimiter $$
create procedure sp_PacienteTurno(in p_codMedico int)
	begin
    select p. * from ((Pacientes p
    Inner join Turno tu on p.codPaciente = tu.codTurno)
    inner join MedicoEspecialidad me on p.codPaciente = me.codMedicoEspecialidad)
    where me.codMedico = p_codMedico;
end $$    
delimiter ;

delimiter $$
	create procedure sp_ResponsableTurno(in p_codMedico int)
	begin
    select r. * from ((ResponsableTurno r
    inner join Turno tu on r.codResponsableTurno = tu.codTurno)
    inner join MedicoEspecialidad me on r.codResponsableTurno = me.codMedicoEspecialidad)
    where me.codMedico = p_codMedico;
    end $$
delimiter ;

delimiter $$
	create procedure sp_ResponsableArea(in p_codMedico int)
    begin
    select a. * from (((Areas a
    Inner join ResponsableTurno re
    on a.codArea = re.codResponsableTurno)
    inner join Turno tu
    on re.codResponsableTurno = tu.codTurno)
    Inner join MedicoEspecialidad me
    on a.codArea = me.codMedicoEspecialidad)
    where me.codMedico = p_codMedico;
    end $$
    
delimiter ;

delimiter $$
	create procedure sp_ResponsableCargo(in p_codMedico int)
    begin
    select c. * from (((Cargos c
    Inner join ResponsableTurno re
    on c.codCargo = re.codResponsableTurno)
    Inner join Turno tu
    on re.codResponsableTurno = tu.codTurno)
    Inner Join MedicoEspecialidad me
    on c.codCargo = me.codMedicoEspecialidad)
    where me.codMedico = p_codMedico;
end $$
delimiter ;