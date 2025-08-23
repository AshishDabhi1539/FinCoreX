# 🏦 Banking Application

A comprehensive web-based banking application built with Java Servlets, JSP, and MySQL following the MVC architecture pattern.

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)
- [Installation](#installation)
- [Database Setup](#database-setup)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Security Features](#security-features)
- [Contributing](#contributing)

## ✨ Features

### Customer Features
- 🔐 Secure user registration and authentication
- 💳 Account management and balance checking
- 💰 Deposit and withdrawal operations
- 🔄 Fund transfers between accounts
- 📊 Transaction history and mini statements
- 👤 Profile management and updates
- 🔔 Notification preferences
- 📱 Responsive design for mobile devices

### Admin Features
- 👥 Complete customer management
- ✅ User approval and rejection system
- 📈 Comprehensive reports and analytics
- 💳 Transaction monitoring
- 🔒 Account freeze/unfreeze functionality
- 📊 Financial statistics and charts
- 📋 Audit logging and tracking

## 🛠️ Technology Stack

- **Backend**: Java Servlets, JSP
- **Database**: MySQL 8.0+
- **Server**: Apache Tomcat 9.0/10.0
- **Frontend**: Bootstrap 5, Chart.js, Font Awesome
- **Build Tool**: Eclipse IDE
- **Java Version**: Java 21
- **Dependencies**: JSTL, MySQL Connector/J

## 🏗️ Architecture

The application follows the **Model-View-Controller (MVC)** pattern:

```
📁 bank_application/
├── 📁 src/main/
│   ├── 📁 java/com/banking/
│   │   ├── 📁 controller/     # Servlets (Controllers)
│   │   ├── 📁 service/        # Business Logic
│   │   ├── 📁 dao/           # Data Access Objects
│   │   ├── 📁 model/         # Entity Classes
│   │   ├── 📁 util/          # Utility Classes
│   │   ├── 📁 db/            # Database Connection
│   │   └── 📁 exception/     # Exception Handling
│   └── 📁 webapp/
│       ├── 📁 admin/         # Admin Interface
│       ├── 📁 customer/      # Customer Interface
│       ├── 📁 auth/          # Authentication Pages
│       ├── 📁 error/         # Error Pages
│       └── 📁 WEB-INF/       # Configuration & Libraries
```

## 🚀 Installation

### Prerequisites
- Java 21 or higher
- Apache Tomcat 9.0/10.0
- MySQL 8.0 or higher
- Eclipse IDE (recommended)

### Steps

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd BankingApplication
   ```

2. **Import into Eclipse**
   - Open Eclipse IDE
   - File → Import → Existing Projects into Workspace
   - Select the `bank_application` folder
   - Click Finish

3. **Configure Tomcat Server**
   - Window → Preferences → Server → Runtime Environments
   - Add Apache Tomcat 9.0 or 10.0
   - Configure the server path

4. **Set up Database**
   - Follow the [Database Setup](#database-setup) section

5. **Configure Database Connection**
   - Update `DBConnection.java` with your database credentials
   - Ensure MySQL server is running

6. **Deploy and Run**
   - Right-click on the project → Run As → Run on Server
   - Select your configured Tomcat server
   - Access the application at `http://localhost:8080/bank_application`

## 🗄️ Database Setup

1. **Create Database**
   ```sql
   CREATE DATABASE banking;
   USE banking;
   ```

2. **Run Schema Script**
   ```bash
   mysql -u root -p banking < database_schema.sql
   ```

3. **Verify Tables**
   ```sql
   SHOW TABLES;
   ```

4. **Check Default Admin User**
   ```sql
   SELECT * FROM users WHERE role = 'ADMIN';
   ```

## ⚙️ Configuration

### Database Configuration
Update `src/main/java/com/banking/db/DBConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/banking";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

### Web Application Configuration
The application uses `web.xml` for configuration:
- Session timeout: 30 minutes
- Character encoding: UTF-8
- Error pages: 404, 500
- Security constraints for admin and customer areas

## 📖 Usage

### Default Admin Credentials
- **Username**: admin
- **Password**: admin123
- **Email**: admin@mybank.com

### Customer Registration Flow
1. Visit the application homepage
2. Click "Register" to create a new account
3. Fill in all required information
4. Submit for admin approval
5. Admin approves/rejects the registration
6. Customer can login once approved

### Admin Operations
1. Login with admin credentials
2. Access pending approvals
3. Approve or reject customer registrations
4. Monitor transactions and generate reports
5. Manage customer accounts

## 🔌 API Documentation

### Authentication Endpoints
- `POST /login` - User login
- `POST /register` - User registration
- `GET /logout` - User logout

### Customer Endpoints
- `POST /customer/deposit` - Deposit money
- `POST /customer/withdraw` - Withdraw money
- `POST /customer/transfer` - Transfer funds
- `GET /customer/miniStatement` - Get mini statement
- `GET /customer/fullHistory` - Get transaction history

### Admin Endpoints
- `GET /admin/dashboard` - Admin dashboard
- `GET /admin/customers` - View all customers
- `GET /admin/pending` - View pending approvals
- `POST /admin/approve` - Approve user
- `POST /admin/reject` - Reject user
- `GET /admin/transactions` - View all transactions
- `GET /admin/reports` - Generate reports

## 🔒 Security Features

### Input Validation
- Comprehensive validation for all user inputs
- SQL injection prevention
- XSS protection
- CSRF protection

### Authentication & Authorization
- Session-based authentication
- Role-based access control (ADMIN/CUSTOMER)
- Account status management
- Secure password handling

### Data Protection
- Encrypted database connections
- Audit logging for sensitive operations
- Input sanitization
- Error handling without information disclosure

## 📊 Database Schema

### Core Tables
- **users** - User accounts and personal information
- **transactions** - Transaction history
- **accounts** - Bank account details
- **customers** - Customer-specific data
- **loans** - Loan information
- **branches** - Branch details
- **beneficiaries** - Beneficiary management
- **audit_logs** - Audit trail
- **notification_preferences** - User notification settings

### Key Relationships
- Users can have multiple transactions
- Users can have multiple accounts
- Transactions are linked to users
- Loans are linked to users
- Audit logs track all sensitive operations

## 🧪 Testing

### Manual Testing
1. **Registration Testing**
   - Test with valid data
   - Test with invalid data
   - Test duplicate username/email

2. **Authentication Testing**
   - Test login with valid credentials
   - Test login with invalid credentials
   - Test logout functionality

3. **Transaction Testing**
   - Test deposit operations
   - Test withdrawal operations
   - Test transfer operations
   - Test insufficient funds scenarios

4. **Admin Testing**
   - Test user approval/rejection
   - Test customer management
   - Test report generation

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Verify MySQL server is running
   - Check database credentials in `DBConnection.java`
   - Ensure database and tables exist

2. **Tomcat Deployment Error**
   - Check Java version compatibility
   - Verify Tomcat configuration
   - Check for port conflicts

3. **JSP Compilation Error**
   - Ensure JSTL library is in WEB-INF/lib
   - Check JSP syntax
   - Verify taglib declarations

4. **Session Issues**
   - Check session timeout configuration
   - Verify session management in servlets
   - Check browser cookie settings

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Support

For support and questions:
- Create an issue in the repository
- Contact the development team
- Check the troubleshooting section

## 🔄 Version History

- **v1.0.0** - Initial release with basic banking features
- **v1.1.0** - Added admin dashboard and reporting
- **v1.2.0** - Enhanced security and validation
- **v1.3.0** - Added comprehensive error handling

---

**Note**: This is a demonstration application. For production use, implement additional security measures, proper password hashing, and comprehensive testing.
