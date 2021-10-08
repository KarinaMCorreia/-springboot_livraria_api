create table autores
(
    id              bigint       not null auto_increment,
    nome            varchar(50)  not null,
    email           varchar(60)  not null,
    data_nascimento  date         not null,
    mini_curriculo   varchar(300) not null,
    primary key (id)
);