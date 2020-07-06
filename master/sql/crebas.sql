/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/7/6 23:01:30                            */
/*==============================================================*/


drop table if exists yuekeju_app_banner;



drop table if exists yuekeju_dept;


drop table if exists yuekeju_note;


drop table if exists yuekeju_note2;


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
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   title                varchar(256),
   url                  varchar(256),
   image_url            varchar(256),
   status               numeric(8,0),
   remark               varchar(256),
   modified             varchar(50),
   update_time          timestamp,
   creater              varchar(50),
   create_time          timestamp,
   del_tab_status       numeric(8,0)
);

alter table yuekeju_app_banner
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_dept                                          */
/*==============================================================*/
create table yuekeju_dept
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   dept_en_name         varchar(128),
   dept_cn_name         varchar(128),
   dept_parent_id       varchar(50),
   dept_order_num       numeric(8,0),
   dept_disable         numeric(8,0),
   dept_description     varchar(256),
   dept_code            varchar(256),
   del_tab_status       numeric(8,0),
   create_time          timestamp,
   update_time          timestamp,
   creater              varchar(50),
   modified             varchar(50)
);

alter table yuekeju_dept
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_note                                          */
/*==============================================================*/
create table yuekeju_note
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   node_content         longtext,
   user_id              varchar(50),
   title                varchar(50),
   top_status           numeric(8,0),
   subject_id           varchar(50),
   private_status       numeric(8,0),
   group_id             varchar(50),
   del_tab_status       numeric(8,0),
   creater              varchar(50),
   modified             varchar(50),
   update_time          timestamp,
   create_time          timestamp
);

alter table yuekeju_note
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_note2                                         */
/*==============================================================*/
create table yuekeju_note2
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   node_content         longtext,
   user_id              varchar(50),
   title                varchar(50),
   top_status           numeric(8,0),
   subject_id           varchar(50),
   private_status       numeric(8,0),
   group_id             varchar(50),
   del_tab_status       numeric(8,0),
   creater              varchar(50),
   modified             varchar(50),
   update_time          timestamp,
   create_time          timestamp
);

alter table yuekeju_note2
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_note_subject                                  */
/*==============================================================*/
create table yuekeju_note_subject
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   subject_en_name      varchar(256),
   subject_cn_name      varchar(256),
   subject_class        varchar(100),
   creater              varchar(50),
   modified             varchar(50),
   create_time          timestamp,
   update_time          timestamp
);

alter table yuekeju_note_subject
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_notes_group                                   */
/*==============================================================*/
create table yuekeju_notes_group
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   group_name           varchar(50),
   delete_time          timestamp,
   user_id              varchar(50),
   del_tab_status       numeric(8,0),
   create_time          timestamp,
   update_time          timestamp,
   creater              varchar(50),
   modified             varchar(50)
);

alter table yuekeju_notes_group
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_permission                                    */
/*==============================================================*/
create table yuekeju_permission
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   permission_name      varchar(128),
   permission_description varchar(256),
   permission_url       varchar(400),
   permission_perms     varchar(256),
   permission_parent_id varchar(50),
   permission_type      numeric(8,0),
   pemission_order_num  numeric(8,0),
   permission_icon      varchar(50),
   del_tab_status       numeric(8,0),
   create_time          timestamp,
   update_time          timestamp,
   creater              varchar(50),
   modified             varchar(50)
);

alter table yuekeju_permission
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_punch_clock_record                            */
/*==============================================================*/
create table yuekeju_punch_clock_record
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   punch_clock_time     varchar(50),
   punch_clock_user_id  varchar(50)
);

alter table yuekeju_punch_clock_record
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_role                                          */
/*==============================================================*/
create table yuekeju_role
(
   id                   int not null,
   modified             varchar(50),
   role_en_name         varchar(128),
   role_cn_name         varchar(128),
   role_description     varchar(128),
   role_status          numeric(8,0),
   create_time          timestamp,
   update_time          timestamp,
   creater              varchar(50),
   yuekeju_code         varchar(50) not null
);

alter table yuekeju_role
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_role_permission                               */
/*==============================================================*/
create table yuekeju_role_permission
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   role_id              varchar(50),
   permission_id        varchar(50)
);

alter table yuekeju_role_permission
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_role_user                                     */
/*==============================================================*/
create table yuekeju_role_user
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   user_id              varchar(50),
   role_id              varchar(50)
);

alter table yuekeju_role_user
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_sys_role_user                                 */
/*==============================================================*/
create table yuekeju_sys_role_user
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   user_id              varchar(50),
   role_id              varchar(50)
);

alter table yuekeju_sys_role_user
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_task_down                                     */
/*==============================================================*/
create table yuekeju_task_down
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   yuekeju_title        varchar(50),
   yuekeju_content      longtext,
   del_tab_status       numeric(8,0),
   create_time          timestamp,
   update_time          timestamp
);

alter table yuekeju_task_down
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_task_down_document                            */
/*==============================================================*/
create table yuekeju_task_down_document
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   yuekeju_task_down_code varchar(50),
   yuekeju_url          varchar(300),
   del_tab_status       numeric(8,0)
);

alter table yuekeju_task_down_document
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_task_down_group                               */
/*==============================================================*/
create table yuekeju_task_down_group
(
   id                   int not null,
   task_down_group_name varchar(100),
   task_down_group_id   int,
   task_down_group_code varchar(50),
   task_down_group_order_number numeric(8,0),
   task_down_group_display numeric(8,0),
   task_down_group_default_folder numeric(8,0),
   task_down_group_folder_icon varchar(300),
   del_tab_status       numeric(8,0)
);

alter table yuekeju_task_down_group
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_task_down_template                            */
/*==============================================================*/
create table yuekeju_task_down_template
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   yuekeju_template     longtext,
   yuekeju_is_public    numeric(8,0),
   create_time          timestamp,
   update_time          timestamp,
   modified             varchar(50),
   creater              varchar(50),
   del_tab_status       numeric(8,0)
);

alter table yuekeju_task_down_template
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_task_table                                    */
/*==============================================================*/
create table yuekeju_task_table
(
   id                   int not null,
   yuekeju_code         varchar(32),
   yuekeju_task_en_name varchar(125),
   yuekeju_task_cn_name varchar(125)
);

alter table yuekeju_task_table
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_user                                          */
/*==============================================================*/
create table yuekeju_user
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   login_name           varchar(32),
   name                 varchar(32),
   user_password        varchar(256),
   user_adress          varchar(256),
   sex                  numeric(8,0),
   user_number          varchar(30),
   salt                 varchar(128),
   del_tab_status       numeric(8,0),
   disable_status       numeric(8,0),
   last_login_time      timestamp,
   create_time          timestamp,
   update_time          timestamp,
   creater              varchar(50),
   modified             varchar(50),
   user_lock_status     numeric(8,0),
   user_lock_count      numeric(8,0),
   user_lock_time       timestamp
);

alter table yuekeju_user
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_user_dept                                     */
/*==============================================================*/
create table yuekeju_user_dept
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   dept_id              varchar(50),
   user_id              varchar(50)
);

alter table yuekeju_user_dept
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_user_loginpassword                            */
/*==============================================================*/
create table yuekeju_user_loginpassword
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   user_id              varchar(50),
   user_password        varchar(128),
   create_time          timestamp
);

alter table yuekeju_user_loginpassword
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_user_note_private_password                    */
/*==============================================================*/
create table yuekeju_user_note_private_password
(
   id                   int not null,
   yuekeju_code         varchar(50) not null,
   user_id              varchar(50),
   private_password     varchar(256),
   creater              varchar(50),
   modified             varchar(50)
);

alter table yuekeju_user_note_private_password
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_user_task_table                               */
/*==============================================================*/
create table yuekeju_user_task_table
(
   id                   int not null,
   yuekeju_code         varchar(32),
   user_id              varchar(32),
   task_id              varchar(32),
   yuekeju_task_status  numeric(8,0)
);

alter table yuekeju_user_task_table
   add primary key (id);

/*==============================================================*/
/* Table: yuekeju_withdraw                                      */
/*==============================================================*/
create table yuekeju_withdraw
(
   id                   int not null,
   yuekeju_code         varchar(32),
   user_id              varchar(32),
   withdraw_money       decimal(22,2),
   yuekeju_type         numeric(8,0),
   yuekeju_account      varchar(32),
   yuekeju_account_name varchar(32),
   yuekeju_status       numeric(8,0),
   creater              varchar(50),
   modified             varchar(50),
   update_time          timestamp,
   create_time          timestamp
);

alter table yuekeju_withdraw
   add primary key (id);

