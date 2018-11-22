create table guestbook(
	seq number primary key,
	name varchar2(50) not null,
	content varchar2(4000) not null);

create sequence guestbook_seq;