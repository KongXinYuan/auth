CREATE TABLE IF NOT EXISTS `kss_admin` (
  `id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parentid` smallint(5) NOT NULL,
  `role` varchar(10) NOT NULL COMMENT '管理员级别',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(60) NOT NULL,
  `addtime` datetime NOT NULL,
  `lastlogintime` datetime NOT NULL,
  `lastloginip` int(11) NOT NULL DEFAULT '0',
  `money` decimal(10,2) NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `pid` (`parentid`),
  KEY `status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员';



INSERT INTO `kss_admin` (`id`, `parentid`, `role`, `username`, `password`, `addtime`, `lastlogintime`, `lastloginip`, `money`, `status`) VALUES
(1, 1, 'OWNER', 'root', '$2a$09$jwbt.074xQHy4/TfKgVsS.spaDSxmsnbm54cU9nUzfpuIb/NoDx9O', '2016-03-29 10:41:51', '2016-03-29 10:41:51', 0, '0.00', 'ACTIVE');



CREATE TABLE IF NOT EXISTS `kss_finance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminid` int(11) NOT NULL,
  `isin` tinyint(1) NOT NULL,
  `relatedid` int(11) NOT NULL,
  `moneynow` decimal(10,2) NOT NULL,
  `moneybefore` decimal(10,2) NOT NULL,
  `moneychange` decimal(10,2) NOT NULL,
  `addtime` datetime NOT NULL,
  `disc` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `adminid` (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='财务记录';


CREATE TABLE IF NOT EXISTS `kss_keyset` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `softid` smallint(5) UNSIGNED NOT NULL,
  `keyname` varchar(100) NOT NULL,
  `cday` decimal(6,2) NOT NULL,
  `prefix` char(4) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `retailprice` decimal(8,2) NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`),
  UNIQUE KEY `keyname` (`softid`, `keyname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `kss_log_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminid` smallint(5) UNSIGNED NOT NULL,
  `loginip` int(11) NOT NULL DEFAULT '0',
  `logintime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `adminid` (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录记录';

CREATE TABLE IF NOT EXISTS `kss_log_pubuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `softid` smallint(5) UNSIGNED NOT NULL,
  `pccode` varchar(128) NOT NULL DEFAULT '',
  `ntimes` smallint(5) UNSIGNED NOT NULL,
  `starttime` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `lasttime` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `soft` (`softid`, `pccode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公用账号';


CREATE TABLE IF NOT EXISTS `kss_options` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `value` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='选项';


CREATE TABLE IF NOT EXISTS `kss_order` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ordernum` varchar(32) NOT NULL,
  `softid` smallint(5) UNSIGNED NOT NULL,
  `adminid` smallint(5) UNSIGNED NOT NULL,
  `keysetid` int(10) UNSIGNED NOT NULL,
  `keycount` int(4) UNSIGNED NOT NULL,
  `beginid` int(11) NOT NULL DEFAULT '0',
  `addtime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `adminid` (`softid`,`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单';


CREATE TABLE IF NOT EXISTS `kss_power` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `adminid` int(5) NOT NULL,
  `keysetid` int(5) NOT NULL,
  `sellprice` decimal(6,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `adminid` (`adminid`,`keysetid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限结构';


CREATE TABLE IF NOT EXISTS `kss_soft` (
  `id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT,
  `softkey` varchar(40) NOT NULL,
  `softname` varchar(40) NOT NULL,
  `intervaltime` smallint(5) UNSIGNED NOT NULL DEFAULT '120',
  `privkey` varchar(1024) NOT NULL DEFAULT '',
  `status` varchar(10) NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`),
  KEY `softname` (`softname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='软件';
