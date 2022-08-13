-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE IF NOT EXISTS `t_project` (
    `id`          int      NOT NULL AUTO_INCREMENT COMMENT '项目Id',
    `name`        varchar(100) DEFAULT NULL COMMENT '项目名称',
    `code`        bigint   NOT NULL COMMENT '项目编号',
    `description` varchar(200) DEFAULT NULL COMMENT '描述',
    `user_id`     int          DEFAULT NULL COMMENT '创建者ID',
    `flag`        tinyint      DEFAULT '1' COMMENT '0:不可用，1:可用',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT ='项目表';