#6. Create a procedure that returns the name, city, and mobile number of all students by joining students and profile tables.
 use tss_students_db;
 CALL procedure6();
#7. Write a procedure that returns all students who live in a specific city (input parameter).
 CALL procedure7('Pune');
 
#8. Write a procedure that takes student ID as input and returns the total number of courses enrolled using an OUT parameter.
 CALL PROCEDURE8(1, @p_student_id);
#9. Write a procedure that returns the average percentage of students grouped by city.
 CALL procedure9();
 
#10. Write a procedure to return the student IDs of students who are enrolled in more than one course.
 CALL PROCEDURE10();
 
#11. Create a procedure that accepts student ID and age as input. Use the age as an INOUT parameter: update the student’s age, then return the updated value back.
 SET @age := 45;
 
 CALL procedure11(101, @age);
 
 SELECT @age;
#12. Write a procedure that uses INOUT parameter to insert a new subject for a student only if it doesn’t already exist. If the subject exists, return a message like "Already exists" via the same parameter.
 SET @subject := 'History';
 CALL procedure12(1, @subject);
 SELECT @SUBJECT;
 
#13. Create a procedure that accepts student and profile details as input and inserts them into the students and profile tables.Ensure the student ID from the first insert is reused for the profile record.
CALL procedure13(45, 'Harshil', 20, '145', 82.50, 'Rajkot', '12345678990');

SELECT * FROM students;
	
#14. Design an audit table percentage_audit(student_id, old_percentage, new_percentage, updated_at) and create a procedure that updates a student’s percentage and logs the old and new value into the audit table.
CALL procedure14(6, 91.75);

SELECT * FROM audit;
 
#15. Write a procedure that deletes a student’s record from all related tables: student_course, subjects, profile, and finally students table.
CALL procedure15(3);

select * from students;

select Check_Passing(4);


 