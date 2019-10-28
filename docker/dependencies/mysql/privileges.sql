use mysql;
-- new user
set password for root@localhost = password('smallrain_mysql_password_10086');
-- important
grant all on *.* to root@'%' identified by 'smallrain_mysql_password_10086' with grant option;
-- use privileges
flush privileges;