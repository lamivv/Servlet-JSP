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

insert into tbl_board (board_no, title, content, writer)
values (board_seq.nextval, '첫번째 글', '서블릿 재밌네요', 'user01');

insert into tbl_board (board_no, title, content, writer)
values (board_seq.nextval, '열심히', 'jsp를 공부합시다', 'user01');

select board_seq.nextval -- 순차적으로 1씩 증가되는.. 그래서 중복되지 않는 값을 생성
from dual; -- 더미테이블 구문의 규칙에 따라 만들기 위해서 사용하는..

-- 조회
select *
from tbl_board;