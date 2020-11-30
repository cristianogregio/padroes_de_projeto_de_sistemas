create schema condominio;

use condominio;

create table rsv_reserva (
  rsv_id bigint unsigned not null auto_increment primary key,
  srv_data_inicio DATE,
  srv_data_final DATE
);

create table sal_salao (
  sal_id bigint unsigned not null auto_increment,
  sal_nome varchar(30) not null,
  sal_churrasqueira TINYINT(1) not null,
  primary key (sal_id),
  unique key uni_salao_nome (sal_nome)
);

-- Link Reserva e Salao
create table res_reserva_salao (
  rsv_id bigint unsigned not null auto_increment,
  sal_id bigint unsigned not null,
  primary key (rsv_id, sal_id),
  foreign key res_resrva_fk (rsv_id) references rsv_reserva (rsv_id) on delete restrict on update cascade,
  foreign key res_salao_fk (sal_id) references sal_salao (sal_id) on delete restrict on update cascade
);

-- Cria tabela de morador
create table mrd_morador (
  mrd_id bigint unsigned not null auto_increment,
  mrd_nome varchar(30) not null,
  mrd_telefone varchar(12) not null,
  mrd_email varchar(50) not null,
  primary key (mrd_id),
  unique key uni_morador_nome (mrd_nome)
);

-- Link Morador e Salao
create table mos_morador_salao (
  mrd_id bigint unsigned not null,
  sal_id bigint unsigned not null,
  primary key (mrd_id, sal_id),
  foreign key mos_morador_fk (mrd_id) references mrd_morador (mrd_id) on delete restrict on update cascade,
  foreign key mos_salao_fk (sal_id) references sal_salao (sal_id) on delete restrict on update cascade
);

create table usr_usuario (
  usr_id bigint unsigned not null auto_increment,
  usr_nome varchar(20) not null,
  usr_senha varchar(90) not null,
  primary key (usr_id),
  unique key uni_usuario_nome (usr_nome)
);

create table aut_autorizacao (
  aut_id bigint unsigned not null auto_increment,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique key uni_aut_nome (aut_nome)
);

create table uau_usuario_autorizacao (
  usr_id bigint unsigned not null,
  aut_id bigint unsigned not null,
  primary key (usr_id, aut_id),
  foreign key aut_usuario_fk (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key aut_autorizacao_fk (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

insert into usr_usuario(usr_nome, usr_senha) values('morador', concat('{MD5}', md5('morador')));
insert into usr_usuario(usr_nome, usr_senha) values('sindico', concat('{MD5}', md5('sindico')));

insert into aut_autorizacao(aut_nome) values('ROLE_SINDICO');
insert into aut_autorizacao(aut_nome) values('ROLE_MORADOR');

insert into uau_usuario_autorizacao(usr_id, aut_id)
select usr_id, aut_id
from usr_usuario, aut_autorizacao
where usr_nome = 'sindico'
and aut_nome = 'ROLE_SINDICO';

insert into uau_usuario_autorizacao(usr_id, aut_id)
select usr_id, aut_id
from usr_usuario, aut_autorizacao
where usr_nome = 'sindico';

insert into sal_salao (sal_nome, sal_churrasqueira) values ("Salao Gourmet 1", true);

SELECT * from aut_autorizacao