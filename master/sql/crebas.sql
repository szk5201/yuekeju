/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/7/7 21:45:05                            */
/*==============================================================*/


drop table if exists yuekeju_app_banner;

drop table if exists yuekeju_dept;

drop table if exists yuekeju_note;

drop table if exists yuekeju_note_history;

drop table if exists yuekeju_note_subject;

drop table if exists yuekeju_notes_group;

drop table if exists yuekeju_permission;

drop table if exists yuekeju_punch_clock_record;

drop table if exists yuekeju_role;

drop table if exists yuekeju_role_permission;

drop table if exists yuekeju_role_user;

drop table if exists yuekeju_sys_role_user;

drop table if exists yuekeju_task_down;

drop table if exists yuekeju_task_down_document;

drop table if exists yuekeju_task_down_group;

drop table if exists yuekeju_task_down_template;

drop table if exists yuekeju_task_table;

drop table if exists yuekeju_user;

drop table if exists yuekeju_user_dept;

drop table if exists yuekeju_user_loginpassword;

drop table if exists yuekeju_user_note_private_password;

drop table if exists yuekeju_user_task_table;

drop table if exists yuekeju_withdraw;

/*==============================================================*/
/* Table: yuekeju_app_banner                                    */
/*==============================================================*/
create table yuekeju_app_banner
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   title                varchar(256) not null comment '主题',
   url                  varchar(256) not null comment '主题地址',
   image_url            varchar(256) not null comment '图片地址',
   status               numeric(8,0) not null comment '状态（0：待上线  1上线  2：下线）',
   remark               varchar(256) not null comment '备注',
   modified             varchar(50) not null comment '修改人',
   update_time          timestamp not null comment '修改时间',
   creater              varchar(50) not null comment '创建人',
   create_time          timestamp not null comment '创建时间',
   del_tab_status       numeric(8,0) not null comment '删除标记',
   primary key (id)
);

alter table yuekeju_app_banner comment 'app轮播图';

/*==============================================================*/
/* Table: yuekeju_dept                                          */
/*==============================================================*/
create table yuekeju_dept
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   dept_en_name         varchar(128) not null comment '组织机构英文名称',
   dept_cn_name         varchar(128) not null comment '组织机构中文名称',
   dept_parent_id       varchar(50) not null comment '上级组织机构id',
   dept_order_num       numeric(8,0) not null comment '组织机构排序',
   dept_disable         numeric(8,0) not null comment '组织机构是否启用',
   dept_description     varchar(256) not null comment '组织机构描述',
   dept_code            varchar(256) not null comment '组织机构code码',
   del_tab_status       numeric(8,0) not null comment '删除标记',
   create_time          timestamp not null comment '创建时间',
   update_time          timestamp not null comment '修改时间',
   creater              varchar(50) not null comment '创建人',
   modified             varchar(50) not null comment '修改人',
   primary key (id)
);

alter table yuekeju_dept comment '组织机构表';

/*==============================================================*/
/* Table: yuekeju_note                                          */
/*==============================================================*/
create table yuekeju_note
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   node_content         longtext not null comment '便签内容',
   user_id              varchar(50) not null comment '用户id',
   title                varchar(50) not null comment '便签标题',
   top_status           numeric(8,0) not null comment '置顶',
   subject_id           varchar(50) not null comment '主题id',
   private_status       numeric(8,0) not null comment '设置私密',
   group_id             varchar(50) not null comment '便签组编号',
   del_tab_status       numeric(8,0) not null comment '删除标记',
   creater              varchar(50) not null comment '创建人',
   modified             varchar(50) not null comment '修改人',
   update_time          timestamp not null comment '修改时间',
   create_time          timestamp not null comment '创建时间',
   primary key (id)
);

alter table yuekeju_note comment '便签表';

/*==============================================================*/
/* Table: yuekeju_note_history                                  */
/*==============================================================*/
create table yuekeju_note_history
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   node_content         longtext not null comment '便签内容',
   user_id              varchar(50) not null comment '用户id',
   title                varchar(50) not null comment '便签标题',
   top_status           numeric(8,0) not null comment '置顶',
   subject_id           varchar(50) not null comment '主题id',
   private_status       numeric(8,0) not null comment '设置私密',
   group_id             varchar(50) not null comment '便签组编号',
   del_tab_status       numeric(8,0) not null comment '删除标记',
   creater              varchar(50) not null comment '创建人',
   modified             varchar(50) not null comment '修改人',
   update_time          timestamp not null comment '修改时间',
   create_time          timestamp not null comment '创建时间',
   primary key (id)
);

alter table yuekeju_note_history comment '历史便签表';

/*==============================================================*/
/* Table: yuekeju_note_subject                                  */
/*==============================================================*/
create table yuekeju_note_subject
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   subject_en_name      varchar(256) not null comment '主题英文名称',
   subject_cn_name      varchar(256) not null comment '主题中文名称',
   subject_class        varchar(100) not null comment '主题对应class',
   creater              varchar(50) not null comment '创建人',
   modified             varchar(50) not null comment '修改人',
   create_time          timestamp not null comment '创建时间',
   update_time          timestamp not null comment '修改时间',
   primary key (id)
);

alter table yuekeju_note_subject comment '便签主题';

/*==============================================================*/
/* Table: yuekeju_notes_group                                   */
/*==============================================================*/
create table yuekeju_notes_group
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一表示',
   group_name           varchar(50) not null comment '便签组名称',
   delete_time          timestamp not null comment '删除时间',
   user_id              varchar(50) not null comment '用户id',
   del_tab_status       numeric(8,0) not null comment '删除标记',
   create_time          timestamp not null comment '创建时间',
   update_time          timestamp not null comment '修改时间',
   creater              varchar(50) not null comment '创建人',
   modified             varchar(50) not null comment '修改人',
   primary key (id)
);

alter table yuekeju_notes_group comment '便签组模型';

/*==============================================================*/
/* Table: yuekeju_permission                                    */
/*==============================================================*/
create table yuekeju_permission
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   permission_name      varchar(128) not null comment '权限名称',
   permission_description varchar(256) not null comment '权限描述',
   permission_url       varchar(400) not null comment '权限路径',
   permission_perms     varchar(256) not null comment '权限标识',
   permission_parent_id varchar(50) not null comment '父级权限id',
   permission_type      numeric(8,0) not null comment '权限类型  0：目录   1：菜单   2：按钮',
   pemission_order_num  numeric(8,0) not null comment '权限排序',
   permission_icon      varchar(50) not null comment '权限图标',
   del_tab_status       numeric(8,0) not null comment '权限状态',
   create_time          timestamp not null comment '创建时间',
   update_time          timestamp not null comment '修改时间',
   creater              varchar(50) not null comment '创建人',
   modified             varchar(50) not null comment '修改人',
   primary key (id)
);

alter table yuekeju_permission comment '权限表';

/*==============================================================*/
/* Table: yuekeju_punch_clock_record                            */
/*==============================================================*/
create table yuekeju_punch_clock_record
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   punch_clock_time     varchar(50) not null comment '打卡时间',
   punch_clock_user_id  varchar(50) not null comment '打卡人',
   primary key (id)
);

alter table yuekeju_punch_clock_record comment '打卡记录';

/*==============================================================*/
/* Table: yuekeju_role                                          */
/*==============================================================*/
create table yuekeju_role
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   role_en_name         varchar(128) not null comment '角色英文名称',
   role_cn_name         varchar(128) not null comment '角色中文名称',
   role_description     varchar(128) not null comment '角色描述',
   role_status          numeric(8,0) not null comment '角色状态',
   create_time          timestamp not null comment '创建时间',
   update_time          timestamp not null comment '修改时间',
   creater              varchar(50) not null comment '创建人',
   modified             varchar(50) not null comment '修改人',
   primary key (id)
);

alter table yuekeju_role comment '角色表';

/*==============================================================*/
/* Table: yuekeju_role_permission                               */
/*==============================================================*/
create table yuekeju_role_permission
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   role_id              varchar(50) not null comment '角色id',
   permission_id        varchar(50) not null comment '权限id',
   primary key (id)
);

alter table yuekeju_role_permission comment '角色权限关系表';

/*==============================================================*/
/* Table: yuekeju_role_user                                     */
/*==============================================================*/
create table yuekeju_role_user
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   user_id              varchar(50) not null comment '用户id',
   role_id              varchar(50) not null comment '角色id',
   primary key (id)
);

alter table yuekeju_role_user comment '用户角色关系表';

/*==============================================================*/
/* Table: yuekeju_sys_role_user                                 */
/*==============================================================*/
create table yuekeju_sys_role_user
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   user_id              varchar(50) not null comment '用户id',
   role_id              varchar(50) not null comment '角色id',
   primary key (id)
);

alter table yuekeju_sys_role_user comment '管理用户角色关系表';

/*==============================================================*/
/* Table: yuekeju_task_down                                     */
/*==============================================================*/
create table yuekeju_task_down
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   yuekeju_title        varchar(50) not null comment '笔记标题',
   yuekeju_content      longtext not null comment '笔记内容',
   del_tab_status       numeric(8,0) not null comment '是否删除',
   create_time          timestamp not null comment '创建时间',
   update_time          timestamp not null comment '修改时间',
   primary key (id)
);

alter table yuekeju_task_down comment '笔记表';

/*==============================================================*/
/* Table: yuekeju_task_down_document                            */
/*==============================================================*/
create table yuekeju_task_down_document
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   yuekeju_task_down_code varchar(50) not null comment '笔记表id',
   yuekeju_url          varchar(300) not null comment '附件路径',
   del_tab_status       numeric(8,0) not null comment '是否删除',
   primary key (id)
);

alter table yuekeju_task_down_document comment '笔记附件';

/*==============================================================*/
/* Table: yuekeju_task_down_group                               */
/*==============================================================*/
create table yuekeju_task_down_group
(
   id                   int not null comment 'id',
   task_down_group_name varchar(100) not null comment '分组名称',
   task_down_group_id   int not null comment '父类id',
   task_down_group_code varchar(50) not null comment '分组code',
   task_down_group_order_number numeric(8,0) not null comment '排序',
   task_down_group_display numeric(8,0) not null comment '是否隐藏',
   task_down_group_default_folder numeric(8,0) not null comment '设置默认文件夹',
   task_down_group_folder_icon varchar(300) not null comment '文件夹图标',
   del_tab_status       numeric(8,0) not null comment '是否删除',
   primary key (id)
);

alter table yuekeju_task_down_group comment '笔记表分组';

/*==============================================================*/
/* Table: yuekeju_task_down_template                            */
/*==============================================================*/
create table yuekeju_task_down_template
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   yuekeju_template     longtext not null comment '模板内容',
   yuekeju_is_public    numeric(8,0) not null comment '是否公开',
   create_time          timestamp not null comment '创建时间',
   update_time          timestamp not null comment '修改时间',
   modified             varchar(50) not null comment '修改人',
   creater              varchar(50) not null comment '创建人',
   del_tab_status       numeric(8,0) not null comment '是否删除',
   primary key (id)
);

alter table yuekeju_task_down_template comment '笔记模板';

/*==============================================================*/
/* Table: yuekeju_task_table                                    */
/*==============================================================*/
create table yuekeju_task_table
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(32) not null comment '唯一标识',
   yuekeju_task_en_name varchar(125) not null comment '任务名称英文',
   yuekeju_task_cn_name varchar(125) not null comment '任务名称中文',
   primary key (id)
);

alter table yuekeju_task_table comment '用户完成任务列表';

/*==============================================================*/
/* Table: yuekeju_user                                          */
/*==============================================================*/
create table yuekeju_user
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   login_name           varchar(32) not null comment '登录名',
   name                 varchar(32) not null comment '名称',
   user_password        varchar(256) not null comment '用户密码',
   user_adress          varchar(256) not null comment '用户地址',
   sex                  numeric(8,0) not null comment '性别',
   user_number          varchar(30) not null comment '用户号',
   salt                 varchar(128) not null comment '加密盐值',
   del_tab_status       numeric(8,0) not null comment '标记删除状态',
   disable_status       numeric(8,0) not null comment '禁用启用状态',
   last_login_time      timestamp not null comment '最后一次登录时间',
   create_time          timestamp not null comment '创建时间',
   update_time          timestamp not null comment '修改时间',
   creater              varchar(50) not null comment '创建人',
   modified             varchar(50) not null comment '修改人',
   user_lock_status     numeric(8,0) not null comment '锁定状态',
   user_lock_count      numeric(8,0) not null comment '锁定次数',
   user_lock_time       timestamp not null comment '锁定时间',
   primary key (id)
);

alter table yuekeju_user comment '用户表';

/*==============================================================*/
/* Table: yuekeju_user_dept                                     */
/*==============================================================*/
create table yuekeju_user_dept
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   dept_id              varchar(50) not null comment '组织机构id',
   user_id              varchar(50) not null comment '用户id',
   primary key (id)
);

alter table yuekeju_user_dept comment '组织机构用户中间表';

/*==============================================================*/
/* Table: yuekeju_user_loginpassword                            */
/*==============================================================*/
create table yuekeju_user_loginpassword
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   user_id              varchar(50) not null comment '用户id',
   user_password        varchar(128) not null comment '用户登录密码',
   create_time          timestamp not null comment '创建时间',
   primary key (id)
);

alter table yuekeju_user_loginpassword comment '用户登录密码历史表';

/*==============================================================*/
/* Table: yuekeju_user_note_private_password                    */
/*==============================================================*/
create table yuekeju_user_note_private_password
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(50) not null comment '唯一标识',
   user_id              varchar(50) not null comment '用户id',
   private_password     varchar(256) not null comment '私密密码',
   creater              varchar(50) not null comment '创建人',
   modified             varchar(50) not null comment '修改人',
   primary key (id)
);

alter table yuekeju_user_note_private_password comment '便签笔记私密密码';

/*==============================================================*/
/* Table: yuekeju_user_task_table                               */
/*==============================================================*/
create table yuekeju_user_task_table
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(32) not null comment '唯一标识',
   user_id              varchar(32) not null comment '用户id',
   task_id              varchar(32) not null comment '任务id',
   yuekeju_task_status  numeric(8,0) not null comment '完成状态',
   primary key (id)
);

alter table yuekeju_user_task_table comment '用户任务关系表';

/*==============================================================*/
/* Table: yuekeju_withdraw                                      */
/*==============================================================*/
create table yuekeju_withdraw
(
   id                   int not null comment 'id',
   yuekeju_code         varchar(32) not null comment '唯一标识',
   user_id              varchar(32) not null comment '提现人',
   withdraw_money       decimal(22,2) not null comment '金额',
   yuekeju_type         numeric(8,0) not null comment '提现类型',
   yuekeju_account      varchar(32) not null comment '提现账号',
   yuekeju_account_name varchar(32) not null comment '提现人名',
   yuekeju_status       numeric(8,0) not null comment '提现状态',
   creater              varchar(50) not null comment '创建人',
   modified             varchar(50) not null comment '修改人',
   update_time          timestamp not null comment '修改时间',
   create_time          timestamp not null comment '创建时间',
   primary key (id)
);

alter table yuekeju_withdraw comment '提现审核表';

