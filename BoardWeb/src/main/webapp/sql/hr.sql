select *
from tab;

--데이터 조회 ( 테이블의 해당 컬럼명을 조회 )
select * -- 컬럼명
from TBL_EMPLOYEES; -- 테이블 명

select *
from TBL_EMPLOYEES
where emp_name = nvl('박사장', emp_name) -- nvl 조회하는 조건(박사장)의 값이 null이면 emp_name조건을 활용해서 조회
order by emp_no; -- 정렬기준;


-- insert into 테이블 values ();
insert into tbl_employees (emp_no, emp_name, tel_no, hire_date, salary)
values(1003, '김과장', '654-0103', '2025-02-20', 400);
commit;
-- update 테이블 set 컬럼=값;
update tbl_employees
set tel_no = nvl('654-0109', tel_no), -- 값이있으면 그 값으로 변경하고, ''의 값이 비어있다면(null값이라면) tel_no의 값에 원래 있던 값을 유지준다(변동x)
    hire_date = case to_date('1900-01-01', 'yyyy-mm-dd') when to_date('1900-01-01', 'yyyy-mm-dd') then hire_date
                                                         else to_date('1900-01-01', 'yyyy-mm-dd')
                end,
    -- hire_Date = '2025-02-20', -- 문자열 -> date타입으로 자동 변환 
    -- hire_date = to_date('20250109','yyyymmdd') -- 직접 변환
    salary = case 234 when 0 then salary
                      else 234 
             end
             
where emp_no = 1003;

update tbl_employees 
set    tel_no = nvl('', tel_no), 
       hire_date = case 'Mon Jan 01 00:00:00 KST 1900' when '1900-01-01' then hire_date                         
                   else 'Mon Jan 01 00:00:00 KST 1900'                 
                   end,   
       salary = case 350 when 0 then salary                      
                else 350             
                end 
where emp_no = 1003;

delete from tbl_employees
where emp_no =1003;
rollback;


select *
from employees
where department_id = 80 -- 부서가 Sales인...
and salary >= 10000 -- 급여가 10,000이상인...
and employee_id >= 150 -- 사원번호가 150 이상인...
order by first_name desc; -- 기준으로 정렬(기본값 오름차순) (desc 내림차순)
-- SA_REP 직무에 대한 정보를 담고 있는 테이블
select *
from jobs;
--90
select *
from departments;

--사원정보 테이블 생성
create table tbl_employees (
  emp_no number primary key, --중복금지
  emp_name varchar(100) not null, --null금지
  tel_no varchar2(20),
  hire_date date,
  salary number
  );
  
-- 모든 조건 넣을거면 뒤에 안적어도 됨 
insert into tbl_employees
values (1001, '박사장', '654-0101', sysdate, 500);

insert into tbl_employees
values (1002, '최이사', '654-0102', sysdate, 450);

commit;

select *
from tbl_employees;

--테이블 생성
create table tbl_student (
  student_no varchar2(10),
  student_name varchar2(100),
  phone varchar2(20),
  address varchar2(100)
);

--데이터 생성 
--insert into table이름
--valuse('데이터' , '데이터');
insert into tbl_student(student_no, student_name)
values ('S001', '홍길동');
-- 학번:S002, 이름:김민서, 연락처:010-1234-2323, 주소: x
insert into tbl_student(student_no, student_name, phone)
values ('S002', '김민서', '010-1234-2323');
-- 학번:S003, 이름:박항서, 연락처:010-9191-1010, 주소: 대구 북구 200
insert into tbl_student(student_no, student_name, address, phone)
values ('S003', '박항서', '010-9191-1010','대구 북구 200');
commit; --db 반영

--삭제 (where문 없이 from tbl_student를 하면 tbl_student에 있는 자료 모두 삭제된다)
delete from tbl_student
where student_name = '김민서';

--수정 update
update tbl_student
set    phone = '010-6789-6789',
       address = '대구 중구 300'
where student_name = '박항서'
and   phone = '대구 북구 200';

--수정 update
update tbl_student
set    student_no = 'S004'
where student_name = '박항서'
and   address = '대구 중구 300';

-- 학생번호: S005, 이름:신용권 추가
-- 신용권의 연락처: 010-0987-1234, 주소:  대구 서구 400
insert into tbl_student(student_no, student_name)
values('S005','신용권');

update tbl_student
set phone = '010-0987-1234',
    address = '대구 서구 400'
where student_name = '신용권';

-- 제약조건 설정/ 중복된 값 설정 불가, not null 등
-- student_no 컬럼에 중복값을 허용하지 않음
alter table tbl_student add constraint student_pk primary key(student_no);
-- 제약조건 설정/ null 값을 허용하지 않음
alter table tbl_student modify student_name not null;

-- 제약조건에 의해 오류 발생 (student_name  null)
insert into tbl_student(student_no)
values('S006');


--데이터 조회 ( 테이블의 해당 컬럼명을 조회 )
select * -- 컬럼명 == student_no, student_name, phone, address
from tbl_student -- 테이블 명
order by student_no;

select student_no
      ,student_name
      ,phone
      ,address
from tbl_student -- 테이블 명
order by student_no; -- student_no 기준으로 정렬

-- 게시판 (tbl_board)
-- 게시글번호, 제목, 내용, 작성자, 작성일시, 조회수
create table tbl_board (
 board_no number primary key, -- 키역할
 title    varchar2(100) not null, -- 제목 가변형 문자열 100byte
 content  varchar2(1000) not null, -- 내용
 writer   varchar2(100) not null, -- 작성자
 write_date date default sysdate, -- 작성일시 값을 넣지 않으면 기본값으로 현재 시스템의 날짜 반환
 view_cnt number default 0 -- 조회수
 );
 -- 시퀀스
create sequence board_seq; 

-- 테이블의 col 추가 (img의 파일이름을 넣어 놓을...)
alter table tbl_board add img varchar(100);

insert into tbl_board (board_no, title, content, writer)
values (board_seq.nextval, '첫번째 글', '서블릿 재밌네요', 'user01');

insert into tbl_board (board_no, title, content, writer)
values (board_seq.nextval, '열심히', 'jsp를 공부합시다', 'user01');

select board_seq.nextval -- 순차적으로 1씩 증가되는.. 그래서 중복되지 않는 값을 생성
from dual; -- 더미테이블 구문의 규칙에 따라 만들기 위해서 사용하는..

-- 수정
update tbl_board
set    title = 'test'
      ,content = 'testing'
where board_no = 22;

insert into tbl_board
select board_seq.nextval, title, content, writer, sysdate, 0
from tbl_board;

select count(1)
from tbl_board;

-- 조회
select *
from tbl_board
order by board_no desc
;

-- 조회2
select tbl_b.*
from (select rownum rn, tbl_a.* -- rownum : 가지고 온 데이터(from)에 순번을 붙이는 것
      from (select board_no, title, content, writer, write_date, view_cnt
            from tbl_board
            order by board_no) tbl_a) tbl_b
where tbl_b.rn >= (:page - 1)* 5 + 1 -- :page -> page라는 파라메타
and   tbl_b.rn <= :page * 5;

-- where tbl_b.rn >= 6
-- and   tbl_b.rn <= 10;

-- 조회3
select *
from tbl_board
where title like '%등록%'; -- %위치에 어떠한 값이 있더라도 록이라는 글자가 포함되어있다면 ...


-- 회원관리 회원테이블(tbl_member): 아이디, 비밀번호, 이름, 권한(User, Admin)
create table tbl_member (
 member_id varchar2(20) primary key,
 passwd    varchar2(20) not null,
 member_name varchar2(100) not null,
 responsibility varchar(10) default 'User' -- 권한 (User, Admin)
);

-- 테이블삭제
drop table tbl_member;

-- 회원 샘플 데이터
insert into tbl_member (member_id, passwd, member_name)
values('user01','1111','홍길동');
insert into tbl_member (member_id, passwd, member_name)
values('user02','1111','김치즈');
insert into tbl_member (member_id, passwd, member_name)
values('user03','1111','정망고');
insert into tbl_member (member_id, passwd, member_name, responsibility)
values('user99','1111','김땅콩', 'Admin');

-- 회원 테이블 조회
select *
from tbl_member;

-- 댓글 테이블 생성
-- tbl_reply(댓글번호, 댓글내용, 작성자, 원본글번호, 작성일시)
create table tbl_reply (
  reply_no number -- 댓글번호(시퀀스)
 ,reply    varchar2(500) not null -- 댓글내용
 ,replyer  varchar2(30) not null -- 작성자
 ,board_no number not null -- 원본글번호
 ,reply_date date default sysdate -- 작성일시
);

-- primary key 제약조건 추가( 제약조건의 이름 : pk_reply ) (reply_no 속성에 primary key 제약조건을 추가)
alter table tbl_reply add constraint pk_reply primary key (reply_no);
 
create sequence reply_seq;

select reply_seq.nextval from dual;


-- 185번 글에 대한 댓글.
insert into tbl_reply (reply_no, reply, replyer, board_no)
values(reply_seq.nextval, '언럭키자나..', 'user50', 185);
insert into tbl_reply (reply_no, reply, replyer, board_no)
values(reply_seq.nextval, '아냐 햅삐해', 'user51', 185);

insert into tbl_reply (reply_no, reply, replyer, board_no)
values(reply_seq.nextval, '184번에 댓글입니다', 'user01', 184);
insert into tbl_reply (reply_no, reply, replyer, board_no)
values(reply_seq.nextval, '이미지등록 잘되네요', 'user01', 184);

-- 반복생성
insert into tbl_reply 
select reply_seq.nextval
      ,reply
      ,replyer
      ,board_no
      ,sysdate
from tbl_reply
where board_no = 185;

-- n번째 게시글에 대한 댓글 조회
select reply_no
      ,reply
      ,replyer
      ,reply_date
from tbl_reply
where board_no = 185;

-- 댓글번호n을 조회
select reply_no
      ,reply
      ,replyer
      ,reply_date
from tbl_reply
where reply_no = 13;

-- 페이징을 위한 댓글 조회
-- 인덱스(pk_reply)기준으로 조회
-- tbl_reply라는 테이블에 r이라는 별칭을 주고 r테이블의 pk_reply를 따라서 INDEX를 활용해서 데이터를 가져오세요
-- 인덱스를 활용하면 오름차순으로 정렬됨..
-- 역순으로 하고싶으면 INDEX_DESC
select /*+ INDEX_DESC (r pk_reply) */
       reply_no, reply, replyer, board_no, reply_date
from tbl_reply r
where board_no = 185;

-- 185번에 대한 데이터를 모두 가져와서 그 다음 각각의 값을 reply_no를 기준으로 다시 정렬(더 시간이 오래 걸림)
select reply_no, reply, replyer, board_no, reply_date
from tbl_reply r
where board_no = 185
order by reply_no;

-- 페이지에 따른 댓글 검색
select tbl_a.* --tbl_a의 모든 컬럼
from (select /*+ INDEX_DESC (r pk_reply) */
             rownum rn, reply_no, reply, replyer, board_no, reply_date
      from tbl_reply r
      where board_no = :board_no) tbl_a
where tbl_a.rn > (:page - 1) * 5
and   tbl_a.rn <= :page * 5;
      
      
-- hr계정 사원정보 Employees
select emp.department_id, dept.department_name, count(1) cnt
from employees emp
join departments dept
on   emp.department_id = dept.department_id
group by emp.department_id, dept.department_name;

select *
from departments;

-- full캘린더 테이블 생성
create table tbl_calendar (
 title varchar2(100) not null,
 start_date varchar2(20) not null,
 end_date varchar2(20)
 );
 
-- full캘린더 기초데이터 생성
 insert into tbl_calendar (title, start_date, end_date)
 values('여름휴가', '2025-08-01', '2025-08-10');
 insert into tbl_calendar (title, start_date, end_date)
 values('제주도출장', '2025-02-10', '2025-02-14');
 insert into tbl_calendar (title, start_date, end_date)
 values('기관평가', '2025-02-16', null);
 
 insert into tbl_calendar (title, start_date, end_date)
 values('간담회', '2025-02-28T18:00:00', '2025-02-28T21:00:00');
 
-- full 캘린더 일정 조회
 select *
 from tbl_calendar;
 