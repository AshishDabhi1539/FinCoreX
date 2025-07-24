-- 1.Write a function get_student_label that takes a student ID and returns a string in the format:
-- 'Roll: [rollnumber] - Name: [name]' from the students table.
SELECT get_student_label(1);
 
-- 2. Calculate Percentage Grade
-- Write a function get_grade that takes a percentage as input and returns:
--  
-- 'A' if percentage ≥ 90
--  
-- 'B' if 75–89
--  
-- 'C' if 50–74
--  
-- 'D' otherwise
 SELECT get_grade(92) AS grades;
 SELECT get_grade(45)AS grades;
 
 -- 3. Get Age Category
-- Write a function get_age_category that takes a student's age and returns 'Teen', 'Adult', or 'Senior' based on:
--  
-- Teen: age < 20
--  
-- Adult: age 20–40
--  
-- Senior: age > 40
SELECT get_age_category(45) AS student_age;
--  
-- 4. Check Pass or Fail
-- Write a function is_passed that takes a percentage and returns 'Pass' if ≥ 40, otherwise 'Fail'.
--  
SELECT is_passed(39);

-- 5. Get Subject Count for a Student
-- Write a function subject_count that takes a student ID and returns how many subjects the student is enrolled in (from the subjects table).
--  
SELECT subject_count(1) AS subjectNumbers;

-- 6. Get Course Count
-- Write a function course_count that takes a student ID and returns the number of courses the student is enrolled in (from the student_course table).
--  
SELECT course_count(1) AS coursesNumbers;

-- 7. Get Mobile Number
-- Write a function get_mobile_by_student that takes a student ID and returns their mobile number from the profile table.
--  
SELECT get_mobile_by_student(1) AS studentMobileNumber;

-- 8. Average Percentage by City
-- Write a function average_percentage_by_city that takes a city name and returns the average percentage of all students living in that city (using join with profile).
--  
SELECT average_percentage_by_city('Pune');

--  
-- 9. Get Highest Percentage Among All Students
-- Write a function get_top_percentage that returns the highest percentage score from the students table.
--  
SELECT get_top_percentage();

-- 10. Get Student Status
-- Write a function get_student_status that takes a student ID and returns:
--  
-- 'Excellent' if percentage ≥ 90
--  
-- 'Good' if between 75–89
--  
-- 'Average' if between 40–74
--  
-- 'Poor' if below 40
-- (Use a SELECT with conditional logic)

SELECT get_student_status(1) as studentCondition;