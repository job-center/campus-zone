rename table `group` to groups;

alter table student change mather_id mather_id int(11) null;
alter table student change mother_id mother_id int(11) null;