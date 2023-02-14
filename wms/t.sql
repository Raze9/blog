CREATE TABLE `user` (
                        id INT AUTO_INCREMENT COMMENT '主键' PRIMARY KEY,
                        account VARCHAR(20) NULL COMMENT '账号',
                        username VARCHAR(100) NOT NULL COMMENT '名字',
                        password VARCHAR(20) NOT NULL COMMENT '密码',
                        age INT NULL,
                        sex INT NULL COMMENT '性别',
                        phone VARCHAR(20) NULL COMMENT '电话',
                        role_id INT NULL COMMENT '角色 0管理员,1管理员,2普通账号'
) CHARSET=utf8;