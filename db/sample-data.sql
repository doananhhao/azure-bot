insert into users(skype_id, skype_name) values ('skype_id1', 'A');
insert into users(skype_id, skype_name) values ('skype_id2', 'B');
insert into users(skype_id, skype_name) values ('skype_id3', 'C');

insert into Kudo(month,year,maximum_point) values(11,2021,1000);
insert into Kudo(month,year,maximum_point) values(10,2021,1000);

-- Person 1 give person 2 and 3
insert into kudo_point_tracking(point,time,skype_id,pointed_skype_id,kudo_id)
values(10,CURRENT_TIMESTAMP,'skype_id1','skype_id2',1);
insert into kudo_point_tracking(point,time,skype_id,pointed_skype_id,kudo_id)
values(20,CURRENT_TIMESTAMP,'skype_id1','skype_id3',1);
insert into kudo_point_tracking(point,time,skype_id,pointed_skype_id,kudo_id)
values(50,CURRENT_TIMESTAMP,'skype_id1','skype_id3',1);

-- Person 2 give person 1 and 3
insert into kudo_point_tracking(point,time,skype_id,pointed_skype_id,kudo_id)
values(20,CURRENT_TIMESTAMP,'skype_id2','skype_id1',1);
insert into kudo_point_tracking(point,time,skype_id,pointed_skype_id,kudo_id)
values(30,CURRENT_TIMESTAMP,'skype_id2','skype_id1',1);
insert into kudo_point_tracking(point,time,skype_id,pointed_skype_id,kudo_id)
values(40,CURRENT_TIMESTAMP,'skype_id2','skype_id3',1);