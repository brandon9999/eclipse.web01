--delete from SERVICE;

insert into SERVICE values('0000000000000001', '게시판목록', '/board/list.do', 'F', 2, '게시판 목록을 보여준다.');
insert into SERVICE values('0000000000000002', '게시판글읽기', '/board/read.do', 'F', 2, '게시판 글을 보여준다.');
insert into SERVICE values('0000000000000003', '게시판글쓰기폼', '/board/writeForm.do', 'F', 2, '게시판 글쓰기 폼을 보여준다.');
insert into SERVICE values('0000000000000004', '게시판글쓰기', '/board/write.do', 'F', 2, '게시판에 새로운 글을 추가한다..');
insert into SERVICE values('0000000000000005', '게시판글삭제', '/board/delete.do', 'F', 2, '게시판의 글을 삭제한다.');
insert into SERVICE values('0000000000000006', '게시판글수정폼', '/board/updateForm.do', 'F', 2, '게시판 글의 수정 폼을 보여준다.');
insert into SERVICE values('0000000000000007', '게시판글수정', '/board/update.do', 'F', 2, '게시판 글을 수정한다.');

insert into SERVICE values('0000000000000008', '공지사항목록', '/notice/list.do', 'F', 1, '공지사항 목록을 보여준다.');
insert into SERVICE values('0000000000000009', '공지사항글읽기', '/notice/read.do', 'F', 1, '공지사항 글을 보여준다.');
insert into SERVICE values('0000000000000010', '공지사항글쓰기폼', '/notice/writeForm.do', 'F', 4, '공지사항 글쓰기 폼을 보여준다.');
insert into SERVICE values('0000000000000011', '공지사항글쓰기', '/notice/write.do', 'F', 4, '공지사항에 새로운 글을 추가한다..');
insert into SERVICE values('0000000000000012', '공지사항글삭제', '/notice/delete.do', 'F', 4, '공지사항의 글을 삭제한다.');
insert into SERVICE values('0000000000000013', '공지사항글수정폼', '/notice/updateForm.do', 'F', 4, '공지사항 글의 수정 폼을 보여준다.');
insert into SERVICE values('0000000000000014', '공지사항글수정', '/notice/update.do', 'F', 4, '공지사항 글을 수정한다.');

--delete from ROLE;

insert into ROLE values ('AM20030620090000', '관리자', 'F', 'KILOS 커뮤니티 사이트의 관리자');
insert into ROLE values ('UR20030620090000', '일반회원', 'F', 'KILOS 커뮤니티 사이트의 일반 회원');
insert into ROLE values ('UR20030620090030', '특별회원', 'F', 'KILOS 커뮤니티 사이트를 운영하는데 도움을 주는 회원');

--delete from AUTHORITY

insert into AUTHORITY values ('AM20030620090000', '0000000000000010');
insert into AUTHORITY values ('AM20030620090000', '0000000000000011');
insert into AUTHORITY values ('AM20030620090000', '0000000000000012');
insert into AUTHORITY values ('AM20030620090000', '0000000000000013');
insert into AUTHORITY values ('AM20030620090000', '0000000000000014');


