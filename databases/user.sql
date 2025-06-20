drop table if exists chat138db.chat138_user;

create table chat138db.chat138_user
(
    id           bigint auto_increment comment '主键ID'
        primary key,
    userCode     varchar(15)  null comment '用户编码',
    userName     varchar(15)  null comment '用户名称（唯一）',
    userPassword varchar(255) null comment '用户密码',
    gender       int(10)      null comment '性别（0:未知、1:女、2:男）',
    birthday     date         null comment '出生日期',
    phone        varchar(15)  null comment '手机',
    address      varchar(30)  null comment '地址',
    userRole     int(10)      null comment '用户角色（0:管理员、1:普通用户）',
    createdBy    bigint       null comment '创建者（userId）',
    creationDate datetime     null comment '创建时间',
    modifyBy     bigint       null comment '更新者（userId）',
    modifyDate   datetime     null comment '更新时间'
) collate = utf8mb4_unicode_ci;


INSERT INTO chat138db.chat138_user (userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate) VALUES ('admin', '系统管理员', 'admin', 0, '1983-10-10', '13688889999', '北京市海淀区成府路207号', 0, 1, '2013-03-21 16:52:07', 1, '2025-01-04 21:15:10');
INSERT INTO chat138db.chat138_user (userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate) VALUES ('liming', '李明', '123', 1, '1983-12-10', '13688884457', '北京市东城区前门东大街9号', 1, 1, '2014-12-31 19:52:09', 1, '2025-01-04 21:15:10');
INSERT INTO chat138db.chat138_user (userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate) VALUES ('hanlubiao', '韩路彪', '123', 1, '1984-06-05', '18567542321', '北京市朝阳区北辰中心12号', 1, 1, '2014-12-31 19:52:09', 1, '2025-01-04 21:15:10');
INSERT INTO chat138db.chat138_user (userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate) VALUES ('zhanghua', '张华', '123', 2, '1983-06-15', '13544561111', '北京市海淀区学院路61号', 1, 1, '2013-02-11 10:51:17', 1, '2025-01-04 21:15:10');
INSERT INTO chat138db.chat138_user (userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate) VALUES ('wangyang', '王洋', '123', 2, '1982-12-31', '13444561124', '北京市海淀区西二旗辉煌国际16层', 1, 1, '2014-06-11 19:09:07', 1, '2025-01-04 21:15:10');
INSERT INTO chat138db.chat138_user (userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate) VALUES ('zhaoyan', '赵燕', '123', 1, '1986-03-07', '18098764545', '北京市海淀区回龙观小区10号楼', 1, 1, '2016-04-21 13:54:07', 1, '2025-01-04 21:15:11');
INSERT INTO chat138db.chat138_user (userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate) VALUES ('sunlei', '孙磊', '123', 1, '1981-01-04', '13387676765', '北京市朝阳区管庄新月小区12楼', 1, 1, '2015-05-06 10:52:07', 1, '2025-01-04 21:15:11');
INSERT INTO chat138db.chat138_user (userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate) VALUES ('sunxing', '孙兴', '123', 2, '1978-03-12', '13367890900', '北京市朝阳区建国门南大街10号', 1, 1, '2016-11-09 16:51:17', 1, '2025-01-04 21:15:11');
INSERT INTO chat138db.chat138_user (userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate) VALUES ('zhangchen', '张晨', '123', 2, '1986-03-28', '18098765434', '朝阳区管庄路口北柏林爱乐三期13号楼', 1, 1, '2016-08-09 05:52:37', 1, '2025-01-04 21:15:11');
INSERT INTO chat138db.chat138_user (userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate) VALUES ('dengchao', '邓超', '123', 1, '1981-11-04', '13689674534', '北京市海淀区北航家属院10号楼', 1, 1, '2016-07-11 08:02:47', 1, '2025-01-04 21:15:11');
INSERT INTO chat138db.chat138_user (userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate) VALUES ('yangguo', '杨过', '123', 2, '1980-01-01', '13388886623', '北京市朝阳区北苑家园茉莉园20号楼', 1, 1, '2015-02-01 03:52:07', 1, '2025-01-04 21:15:11');
INSERT INTO chat138db.chat138_user (userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate) VALUES ('zhaomin', '赵敏', '123', 1, '1987-12-04', '18099897657', '北京市昌平区天通苑3区12号楼', 1, 1, '2015-09-12 12:02:12', 1, '2025-01-04 21:15:11');
