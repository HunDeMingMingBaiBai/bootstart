create database if not exists `start` character set utf8mb4 collate utf8_general_ci;

create table `rbac_user` (
  `id` bigint not null auto_increment comment '编号',
  `user_name` varchar(20) not null comment '用户名',
  `password` varchar(50) not null comment '密码',
  primary key (`id`)
) ENGINE=InnoDB auto_increment=1 comment='用户';