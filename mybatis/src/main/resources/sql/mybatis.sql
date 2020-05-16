create DATABASE test1;
use test1;
create table test1
(
    id   int not null,
    name varchar(50)
);
insert into test1(id, name)
values (2, '李四');

create DATABASE test2;
use test2;
create table test2
(
    id   int not null,
    name varchar(50)
);
insert into test2(id, name)
values (1, '张三');
