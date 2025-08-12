<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.tss.model.Feedback" %>
<!DOCTYPE html>
<html>
<head>
    <title>Feedback Result</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f9fafb;
            padding: 40px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .container {
            background: #fff;
            padding: 30px 40px;
            border-radius: 8px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
            max-width: 500px;
            width: 100%;
        }
        h2 {
            color: #2c3e50;
            text-align: center;
            margin-bottom: 25px;
        }
        p {
            font-size: 16px;
            color: #444;
            margin: 8px 0;
        }
        .error {
            color: #e74c3c;
            font-weight: 600;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <%
            HttpSession sessionObj = request.getSession(false);
            if (sessionObj != null) {
                String msg = (String) sessionObj.getAttribute("msg");
                Feedback fb = (Feedback) sessionObj.getAttribute("feedback");
        %>
            <h2><%= msg %></h2>
            <%
                if (fb != null && msg != null && msg.startsWith("Your")) {
            %>
                <p><strong>Name:</strong> <%= fb.getName() %></p>
                <p><strong>Session Date:</strong> <%= fb.getSessionDate() %></p>
                <p><strong>Session Content:</strong> <%= fb.getSessionContent() %></p>
                <p><strong>Query Resolution/Feedback:</strong> <%= fb.getQueryResolution() %></p>
                <p><strong>Interactivity/Engagement:</strong> <%= fb.getInteractivity() %></p>
                <p><strong>Impactful Learning:</strong> <%= fb.getImpactfulLearning() %></p>
                <p><strong>Content Delivery Skills:</strong> <%= fb.getDeliverySkills() %></p>
            <%
                }
            } else {
            %>
                <p class="error">Error: No session found.</p>
            <%
            }
        %>
    </div>
</body>
</html>
