CREATE SCHEMA paciente;
CREATE TABLE paciente.paciente (id bigint not null, nome varchar(200) not null, situacao char(1) not null, data_cadastro timestamp not null default now(), constraint paciente_pk primary key (id));
CREATE SEQUENCE paciente.seq_paciente_id;