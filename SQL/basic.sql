use tss_students_db;

insert into students values
(1, 101, 'Anjali Sharma', 18, 92.50),
(2, 102, 'Ravi Verma', 19, 85.00),
(3, 103, 'Priya Deshmukh', 20, 74.00),
(4, 104, 'Amit Patel', 17, 38.00),
(5, 105, 'Sneha Joshi', 21, 64.50),
(6, 106, 'Arjun Mehta', 22, 49.00),
(7, 107, 'Akshay Kumar', 18, 55.50),
(8, 108, 'Anaya Nair', 19, 88.00),
(9, 109, 'Bhavna Iyer', 20, 91.00),
(10, 110, 'Chetan Salunkhe', 18, 33.00),
(11, 111, 'Deepak Rawat', 20, 85.00),
(12, 112, 'Asha Pawar', 19, 67.00),
(13, 113, 'Ganesh Jadhav', 22, 75.00),
(14, 114, 'Aditya Jain', 21, 92.50),
(15, 115, 'Raj Thakur', 17, 40.00);

select * from students;

Select name,roll_number
from students
where percentage>75;

Select * from students
where age>18 and percentage<50;

select * from students
order by percentage desc;

select count(*) from students;

select avg(percentage)
from students
where age<20;

select * from students 
where percentage = (select max(percentage) from students);

select age, count(*) as count from students group by age;

select * from students where name like 'a%';

select name, percentage from students
where percentage >(select avg(percentage) from students);

select name ,percentage,
case when (percentage>=90)
then 'A'
when (percentage between 75 and 89)
then 'B'
when (percentage between 74 and 60)
then 'C'

else 'D'
end as grade
from students;

select max(percentage)from students
where percentage <(select max(percentage)from students);

create view fail as
select * from students where percentage < 40;

select * from fail;

select name, percentage,
dense_rank() over (order by percentage desc) as ranked
from students;

select * from (
select *, dense_rank() over (order by percentage desc) as student_rank
from students
) as ranked_students
where student_rank <=3;


INSERT INTO profiles VALUES
(1, 'Pune', '9876543210', 1),
(2, 'Mumbai', '9823456780', 2),
(3, 'Nagpur', '9988776655', 3),
(4, 'Delhi', '9911223344', 4),
(5, 'Pune', '9876501234', 5);

select * from profiles;

INSERT INTO subjects VALUES
(1,  'Mathematics', 1),
(2,  'Physics', 1),
(3,  'Biology', 2),
(4,  'Chemistry', 3),
(5,  'Mathematics', 3),
(6,  'History', 4),
(7,  'Economics', 5);

select * from subjects;

INSERT INTO Course VALUES
(1, 'BSc Computer Science'),
(2, 'BSc Mathematics'),
(3, 'BA History'),
(4, 'BCom Finance');

select * from course;

INSERT INTO student_course VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 1),
(3, 3),
(4, 3),
(5, 4),
(2, 4);

select * from student_course;

select name from students 
inner join profiles on students.student_id = profiles.student_id
where city = 'pune';

select profiles.city, count(profiles.city) as student_in_each_city
from students
inner join profiles on students.student_id = profiles.student_id
group by profiles.city;

select * from students 
where percentage >80;

SELECT s.student_id, s.name
FROM students s
JOIN (
    SELECT student_id
    FROM student_course
    GROUP BY student_id
    HAVING COUNT(course_id) > 1
) sc ON s.student_id = sc.student_id;


select name, sub_name
from students 
inner join subjects on students.student_id = subjects.subject_id;

select * from students
left join profiles
on students.student_id = profiles.profile_id
where profiles.profile_id Is null;

select  name, city, mobile_number from students
left join profiles 
on students.student_id = profiles.profile_id
where profiles.profile_id Is Not Null;

select sub_name from subjects 
join profiles on profiles.student_id = subjects.student_id
where profiles.city = 'Mumbai';

select avg(percentage) as average_percentage, profiles.city
from students
join profiles on profiles.profile_id = students.student_id
group by profiles.city;



SELECT * 
FROM students
JOIN profiles ON profiles.student_id = students.student_id
JOIN student_course ON student_course.student_id = students.student_id
JOIN course ON student_course.course_id = course.course_id
WHERE profiles.city = 'pune' AND course.course_name = 'BSc Mathematics';



select * from course;
use tss_students_db;

#11. Get names of students who have taken both 'Physics' and 'Mathematics'.
select s.name
from students s
join subjects subj on subj.student_id = s.student_id
where subj.sub_name in ('Physics', 'Mathematics')
group by s.student_id, s.name
having count(distinct subj.sub_name) = 2;

 
#12. Show students who are not enrolled in any course.
select * from students
left join student_course sc on sc.student_id = students.student_id
where sc.course_id is null;
 
 
#13. Display city-wise count of students enrolled in more than one subject.
select s.student_id, s.name, profiles.city, count( distinct s.student_id) as student_enrolled from students s
join subjects on subjects.student_id = s.student_id
join profiles on profiles.student_id = s.student_id
group by profiles.city, s.student_id
having count(*)>1;
 
#14. For each student, show their name, city, all course names (comma separated if possible), and subjects. Number them.
select students.name, profiles.city, group_concat(distinct course.course_name order by course.course_name) as course_names
from students
join profiles on profiles.student_id = students.student_id
join student_course on student_course.student_id = students.student_id
join course on course.course_id = student_course.course_id
join subjects on subjects.student_id = students.student_id
group by students.student_id, students.name, profiles.city;


 
 
#15. Find the top 3 students with the highest percentage in each city.
select * from (
    select s.name, p.city, s.percentage, row_number() over (partition by p.city order by s.percentage desc) as rank_in_city
    from students s
    join profiles p on p.student_id = s.student_id
) as ranked
where rank_in_city <= 3;


 
#16. List students who have taken exactly 3 subjects.
select s.student_id, s.name, count( *) as student_count from students s
join subjects on subjects.student_id = s.student_id
group by s.name, s.student_id 
having count(*)=3;

#17. Show courses that no student has enrolled in.
select c.course_id, c.course_name
from course c
left join student_course sc on sc.course_id = c.course_id
where sc.student_id is null;



#18. List students who share the same percentage.
select s1.*
from students s1
join students s2 on s1.percentage = s2.percentage
where s1.student_id != s2.student_id
order by s1.percentage;

 
#19. Display the number of courses and subjects each student is enrolled in.
select s.name, s.student_id, count(distinct sc.course_id) as course_count, count(distinct sub.sub_name) as subject_count
from students s
left join student_course sc on s.student_id = sc.student_id
left join subjects sub on s.student_id = sub.student_id
group by s.student_id;

call Inser_and_update_records();
select*from students;

call Get_Scholar_students(90);

call Calculate_Average(@average_percentage, @average_age);

select @average_percentage as average_percentage, @average_age as avergae_age;

CALL Get_Student_Profile_phoneNumber(@result, '9876543210');

select @result;

CALL InsertStudent(43 ,'102', 'Dharmi', 21, 88.75);

CALL GetStudentByRollNumber('102');

CALL UpdateStudentPercentage(1, 91.50);

CALL GetSubjectsByStudentID(1);

SET @name := '';
SET @percentage := 0;

CALL GetNameAndPercentage(1, @name, @percentage);
SELECT @name AS student_name, @percentage AS student_percentage;
