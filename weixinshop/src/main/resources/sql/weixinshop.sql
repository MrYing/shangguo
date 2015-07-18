/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015-06-17 23:22:27                          */
/*==============================================================*/




DROP TABLE IF EXISTS t_address;

DROP TABLE IF EXISTS t_admin;

DROP TABLE IF EXISTS t_coupon;

DROP TABLE IF EXISTS t_goods_gategory;

DROP TABLE IF EXISTS t_order;

DROP TABLE IF EXISTS t_orderlist;

DROP TABLE IF EXISTS t_shoppingcart;
DROP TABLE IF EXISTS T_GOODS;

DROP TABLE IF EXISTS T_USER;
/*==============================================================*/
/* Table: T_GOODS                                               */
/*==============================================================*/
CREATE TABLE T_GOODS
(
   goods_id             INT(10) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
   gategory_id          INT(5) COMMENT '类别编号',
   goods_name           VARCHAR(128) COMMENT '商品名称',
   goods_order          INT(5) DEFAULT 0 COMMENT '排序',
   original_price       DECIMAL(5,2) DEFAULT 0 COMMENT '原价',
   present_price        DECIMAL(5,2) DEFAULT 0 COMMENT '现价',
   order_price          DECIMAL(5,2) DEFAULT 0 COMMENT '预定价格',
   amount               DECIMAL(5,2) DEFAULT 0 COMMENT '数量',
   STATUS               VARCHAR(8) COMMENT '状态',
   inventory            DECIMAL(5,2) DEFAULT 0 COMMENT '库存',
   introduce            VARCHAR(1024) COMMENT '商品简介',
   image_url            VARCHAR(128) COMMENT '小图url',
   creat_time           DATETIME COMMENT '创建时间',
   product_text         LONGTEXT COMMENT '商品详情',
   times                INT(10) DEFAULT 0 COMMENT '浏览次数',
   PRIMARY KEY (goods_id)
);

ALTER TABLE T_GOODS COMMENT '商品信息表';

/*==============================================================*/
/* Table: T_USER                                                */
/*==============================================================*/
CREATE TABLE T_USER
(
   user_id              INT(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
   user_name            VARCHAR(128) COMMENT '用户名称',
   join_time            DATETIME COMMENT '注册时间',
   user_nickname        VARCHAR(128) COMMENT '用户昵称',
   phone                VARCHAR(16) COMMENT '手机',
   address              VARCHAR(256) COMMENT '地址',
   gender               VARCHAR(8) COMMENT '性别',
   score                INT(8) DEFAULT 0 COMMENT '积分',
   icon_url             VARCHAR(256) COMMENT '头像地址',
   creat_time           DATETIME COMMENT '创建时间',
   PRIMARY KEY (user_id)
);

ALTER TABLE T_USER COMMENT '用户信息表';

/*==============================================================*/
/* Table: t_address                                             */
/*==============================================================*/
CREATE TABLE t_address
(
   adress_id            INT NOT NULL AUTO_INCREMENT COMMENT '地址编号',
   user_id              INT(10) NOT NULL COMMENT '用户编号',
   address_order        INT(3) DEFAULT 0 COMMENT '优先级别',
   address              VARCHAR(256) COMMENT '地址',
   phone                VARCHAR(16) COMMENT '联系方式',
   receiver             VARCHAR(128) COMMENT '收货人',
   delivery_time        DATETIME COMMENT '收货时间',
   creat_time           DATETIME COMMENT '创建时间',
   STATUS               VARCHAR(8) COMMENT '状态(使用，作废)',
   PRIMARY KEY (adress_id)
);

ALTER TABLE t_address COMMENT '用户配送地址表';

/*==============================================================*/
/* Table: t_admin                                               */
/*==============================================================*/
CREATE TABLE t_admin
(
   admin_id             INT(10) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
   login_account        VARCHAR(32) NOT NULL COMMENT '登录账号',
   NAME                 VARCHAR(128) COMMENT '名称',
   PASSWORD             VARCHAR(256) NOT NULL COMMENT '密码',
   STATUS               VARCHAR(8) COMMENT '状态',
   creat_time           DATETIME COMMENT '创建时间',
   PRIMARY KEY (admin_id)
);

ALTER TABLE t_admin COMMENT '管理员信息';

/*==============================================================*/
/* Table: t_coupon                                              */
/*==============================================================*/
CREATE TABLE t_coupon
(
   coupon_id            INT(10) NOT NULL AUTO_INCREMENT COMMENT '优惠券编码',
   user_id              INT(10) NOT NULL COMMENT '用户编号',
   TYPE                 VARCHAR(128) COMMENT '优惠方式',
   introduce            DATETIME COMMENT '创建时间',
   STATUS               VARCHAR(8) COMMENT '状态（未使用，使用，过期）',
   creat_time           DATETIME COMMENT '创建时间',
   PRIMARY KEY (coupon_id)
);

ALTER TABLE t_coupon COMMENT '用户优惠券';

/*==============================================================*/
/* Table: t_goods_gategory                                      */
/*==============================================================*/
CREATE TABLE t_goods_gategory
(
   gategory_id          INT(5) NOT NULL AUTO_INCREMENT COMMENT '类别编号',
   NAME                 VARCHAR(128) COMMENT '类别名称',
   introduce            VARCHAR(512) COMMENT '类别描述',
   creat_time           DATETIME COMMENT '创建时间',
   PRIMARY KEY (gategory_id)
);

ALTER TABLE t_goods_gategory COMMENT '商品类别';

/*==============================================================*/
/* Table: t_order                                               */
/*==============================================================*/
CREATE TABLE t_order
(
   order_id             INT(10) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
   order_type           INT(2) COMMENT '订单类型',
   user_id              INT(10) NOT NULL COMMENT '用户编号',
   user_name            VARCHAR(128) COMMENT '用户名称',
   order_status         INT(2) DEFAULT 0 COMMENT '订单状态（下单，支付，作废）',
   order_time           DATETIME COMMENT '下单时间',
   pay_type             INT(2) DEFAULT 0 COMMENT '支付方式',
   carry_type           INT(2) DEFAULT 0 COMMENT '送货方式',
   address              VARCHAR(256) COMMENT '送货地址',
   phone                VARCHAR(16) COMMENT '联系电话',
   receiver             VARCHAR(128) COMMENT '收货人',
   delivery_time        DATETIME COMMENT '送达时间',
   monny                DECIMAL(5,2) DEFAULT 0 COMMENT '订单总额',
   activity_type        INT(2) DEFAULT 0 COMMENT '优惠方式',
   remark               VARCHAR(256) COMMENT '备注',
   PRIMARY KEY (order_id)
);

ALTER TABLE t_order COMMENT '订单信息表';

/*==============================================================*/
/* Table: t_orderlist                                           */
/*==============================================================*/
CREATE TABLE t_orderlist
(
   order_id             INT(10) NOT NULL COMMENT '订单编号',
   good_id              INT(10) NOT NULL COMMENT '商品编号',
   good_name            DECIMAL(5,2) DEFAULT 0 COMMENT '商品名称',
   original_price       DECIMAL(5,2) DEFAULT 0 COMMENT '商品原价',
   present_price        DECIMAL(5,2) DEFAULT 0 COMMENT '商品现价',
   order_price          DECIMAL(5,2) DEFAULT 0 COMMENT '商品预定价',
   buy_count            DECIMAL(5,2) DEFAULT 0 COMMENT '购买数量',
   money                DECIMAL(5,2) DEFAULT 0 COMMENT '小计金额'
);

ALTER TABLE t_orderlist COMMENT '订单明细表';

/*==============================================================*/
/* Table: t_shoppingcart                                        */
/*==============================================================*/
CREATE TABLE t_shoppingcart
(
   goods_id             INT(10) DEFAULT 0 COMMENT '商品编号',
   buy_count            DECIMAL(5,2) DEFAULT 0 COMMENT '购买数量',
   user_id              INT(10) DEFAULT 0 COMMENT '用户编号'
);

ALTER TABLE t_shoppingcart COMMENT '购物车';

ALTER TABLE T_GOODS ADD CONSTRAINT FK_Reference_goods_gategory FOREIGN KEY (gategory_id)
      REFERENCES t_goods_gategory (gategory_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_address ADD CONSTRAINT FK_Reference_address FOREIGN KEY (user_id)
      REFERENCES T_USER (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_coupon ADD CONSTRAINT FK_Reference_coupon FOREIGN KEY (user_id)
      REFERENCES T_USER (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_orderlist ADD CONSTRAINT FK_Reference_order FOREIGN KEY (order_id)
      REFERENCES t_order (order_id) ON DELETE RESTRICT ON UPDATE RESTRICT;


ALTER TABLE `t_user`  ADD COLUMN `openID` VARCHAR(64) ; 
ALTER TABLE `t_user`  ADD COLUMN `unionID` VARCHAR(64) ; 