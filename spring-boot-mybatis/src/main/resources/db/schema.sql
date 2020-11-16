DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`         bigint(20) AUTO_INCREMENT NOT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name`  varchar(255) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;