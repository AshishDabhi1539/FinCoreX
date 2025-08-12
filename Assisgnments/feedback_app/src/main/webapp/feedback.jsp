<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Feedback Form</title>
<style>
  body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: #f5f7fa;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
  }

  form {
    background: #fff;
    padding: 30px 40px;
    border-radius: 8px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.1);
    max-width: 400px;
    width: 100%;
  }

  form h2 {
    margin-bottom: 20px;
    color: #333;
    text-align: center;
  }

  label {
    display: block;
    margin-bottom: 6px;
    font-weight: 600;
    color: #555;
  }

  input[type="text"],
  input[type="date"],
  input[type="number"] {
    width: 100%;
    padding: 8px 10px;
    margin-bottom: 18px;
    border: 1.8px solid #ccc;
    border-radius: 4px;
    font-size: 14px;
    transition: border-color 0.3s ease;
    box-sizing: border-box;
  }

  input[type="text"]:focus,
  input[type="date"]:focus,
  input[type="number"]:focus {
    border-color: #007bff;
    outline: none;
  }

  input[type="submit"] {
    width: 100%;
    padding: 10px 0;
    background-color: #007bff;
    color: white;
    font-size: 16px;
    font-weight: 700;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.25s ease;
  }

  input[type="submit"]:hover {
    background-color: #0056b3;
  }

  /* Responsive */
  @media (max-width: 450px) {
    form {
      padding: 20px;
    }
  }
</style>
</head>
<body>
  <form action="submitFeedback" method="post">
    <h2>Submit Your Feedback</h2>

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required>

    <label for="sessionDate">Session Date:</label>
    <input type="date" id="sessionDate" name="sessionDate" required>

    <label for="sessionContent">Session Content:</label>
    <input type="number" id="sessionContent" name="sessionContent" min="1" max="10" required>

    <label for="queryResolution">Query Resolution/Feedback:</label>
    <input type="number" id="queryResolution" name="queryResolution" min="1" max="10" required>

    <label for="interactivity">Interactivity/Engagement:</label>
    <input type="number" id="interactivity" name="interactivity" min="1" max="10" required>

    <label for="impactfulLearning">Impactful Learning:</label>
    <input type="number" id="impactfulLearning" name="impactfulLearning" min="1" max="10" required>

    <label for="deliverySkills">Content Delivery Skills:</label>
    <input type="number" id="deliverySkills" name="deliverySkills" min="1" max="10" required>

    <input type="submit" value="Submit Feedback">
  </form>
</body>
</html>
