-- Person A give person B and C
insert into kudo_point_tracking(point,time,user_id,pointed_user_id,kudo_id)
values(10,CURRENT_TIMESTAMP,1,2,1);
insert into kudo_point_tracking(point,time,user_id,pointed_user_id,kudo_id)
values(20,CURRENT_TIMESTAMP,1,2,1);
insert into kudo_point_tracking(point,time,user_id,pointed_user_id,kudo_id)
values(50,CURRENT_TIMESTAMP,1,3,1);

-- Person B give person A and C
insert into kudo_point_tracking(point,time,user_id,pointed_user_id,kudo_id)
values(20,CURRENT_TIMESTAMP,2,1,1);
insert into kudo_point_tracking(point,time,user_id,pointed_user_id,kudo_id)
values(30,CURRENT_TIMESTAMP,2,1,1);
insert into kudo_point_tracking(point,time,user_id,pointed_user_id,kudo_id)
values(40,CURRENT_TIMESTAMP,2,3,1);