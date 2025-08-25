# Banking Application - Enterprise Edition

A comprehensive Java web-based banking management system built with Servlet/JSP technology, following enterprise-level patterns and best practices.

## 🏦 Features

### Core Banking Features
- **User Management**: Registration, authentication, and profile management
- **Account Management**: Create, view, and manage bank accounts
- **Transaction Processing**: Deposit, withdraw, and transfer operations
- **Loan Management**: Apply for and manage loans with EMI calculations
- **Admin Dashboard**: Comprehensive admin panel for bank operations
- **Customer Dashboard**: User-friendly customer interface

### Security Features
- **Password Hashing**: Secure password storage with salt
- **Session Management**: Robust session handling and timeout
- **Input Validation**: Comprehensive input sanitization and validation
- **Role-based Access Control**: Admin and customer role separation
- **XSS Protection**: Cross-site scripting prevention

### Enterprise Features
- **Exception Handling**: Global exception handling with custom error pages
- **Logging System**: Comprehensive request/response logging
- **Configuration Management**: Centralized application configuration
- **Database Connection Pooling**: Efficient database connections
- **Audit Trail**: Transaction and user activity tracking

## 🛠️ Technology Stack

- **Backend**: Java Servlets, JSP
- **Database**: MySQL
- **Server**: Apache Tomcat 9.0
- **Frontend**: Bootstrap 5, Chart.js, JSTL
- **Build Tool**: Maven
- **Security**: Custom authentication and authorization

## 📁 Project Structure

```
bank_application/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/banking/
│   │   │       ├── controller/     # Servlet controllers
│   │   │       ├── dao/           # Data Access Objects
│   │   │       ├── model/         # Entity classes
│   │   │       ├── service/       # Business logic
│   │   │       ├── util/          # Utility classes
│   │   │       ├── config/        # Configuration
│   │   │       ├── exception/     # Custom exceptions
│   │   │       └── filter/        # Servlet filters
│   │   ├── resources/
│   │   │   └── application.properties
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml
│   │       ├── admin/            # Admin JSP pages
│   │       ├── customer/         # Customer JSP pages
│   │       ├── auth/             # Authentication pages
│   │       ├── css/              # Stylesheets
│   │       ├── js/               # JavaScript files
│   │       └── images/           # Static images
│   └── test/                     # Test files
├── database/                     # Database scripts
├── docs/                         # Documentation
└── README.md
```

## 🚀 Quick Start

### Prerequisites
- Java JDK 8 or higher
- Apache Tomcat 9.0
- MySQL 8.0 or higher
- Maven 3.6 or higher

### Installation Steps

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd bank_application
   ```

2. **Database Setup**
   ```bash
   # Create database
   mysql -u root -p
   CREATE DATABASE banking_app;
   USE banking_app;
   
   # Run database scripts
   source database/schema.sql
   source database/data.sql
   ```

3. **Configure Database Connection**
   - Edit `src/main/java/com/banking/db/DBConnection.java`
   - Update database URL, username, and password

4. **Build the Project**
   ```bash
   mvn clean package
   ```

5. **Deploy to Tomcat**
   - Copy the generated WAR file to Tomcat's `webapps` directory
   - Start Tomcat server
   - Access the application at `http://localhost:8080/bank_application`

## 🔧 Configuration

### Application Properties
The application uses a centralized configuration system. Key settings can be modified in `application.properties`:

```properties
# Database Configuration
app.db.url=jdbc:mysql://localhost:3306/banking_app
app.db.username=root
app.db.password=password

# Security Configuration
app.max.login.attempts=3
app.session.timeout=30

# Transaction Limits
app.transaction.limit.deposit=100000
app.transaction.limit.withdraw=50000
app.transaction.limit.transfer=100000
```

### Environment Variables
- `DB_URL`: Database connection URL
- `DB_USERNAME`: Database username
- `DB_PASSWORD`: Database password
- `APP_ENV`: Application environment (dev/prod)

## 👥 User Roles

### Admin
- Manage all customer accounts
- Approve/reject loan applications
- View transaction reports
- System configuration
- User management

### Customer
- View account balance and transactions
- Perform banking operations
- Apply for loans
- Update profile information
- View notifications

## 🔐 Security Features

### Authentication
- Secure password hashing with salt
- Session-based authentication
- Automatic session timeout
- Login attempt limiting

### Authorization
- Role-based access control
- URL-based security filtering
- Admin-only resource protection

### Data Protection
- Input sanitization
- SQL injection prevention
- XSS protection
- Sensitive data masking

## 📊 Database Schema

### Core Tables
- `users`: User information and authentication
- `accounts`: Bank account details
- `transactions`: Transaction history
- `loans`: Loan applications and details
- `notifications`: System notifications

### Key Relationships
- Users can have multiple accounts
- Transactions are linked to accounts
- Loans are associated with users
- Notifications are user-specific

## 🧪 Testing

### Unit Tests
```bash
mvn test
```

### Integration Tests
```bash
mvn verify
```

### Manual Testing
1. **Admin Login**: Use default admin credentials
2. **Customer Registration**: Create new customer accounts
3. **Transaction Testing**: Perform various banking operations
4. **Loan Application**: Test loan approval workflow

## 📈 Monitoring and Logging

### Application Logs
- Request/response logging
- Error tracking
- Performance monitoring
- Security event logging

### Database Monitoring
- Connection pool statistics
- Query performance
- Transaction monitoring

## 🔄 Deployment

### Development
```bash
mvn tomcat7:run
```

### Production
1. Build the application
2. Configure production database
3. Set environment variables
4. Deploy to production Tomcat server
5. Configure SSL certificates
6. Set up monitoring and logging

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Verify database credentials
   - Check database server status
   - Ensure database exists

2. **Session Issues**
   - Clear browser cookies
   - Check session timeout settings
   - Verify session configuration

3. **Transaction Failures**
   - Check account balance
   - Verify transaction limits
   - Review error logs

### Debug Mode
Enable debug mode by setting `app.debug=true` in `application.properties`

## 📝 API Documentation

### Authentication Endpoints
- `POST /login` - User login
- `POST /register` - User registration
- `GET /logout` - User logout

### Customer Endpoints
- `GET /customer/dashboard` - Customer dashboard
- `POST /customer/deposit` - Deposit money
- `POST /customer/withdraw` - Withdraw money
- `POST /customer/transfer` - Transfer money

### Admin Endpoints
- `GET /admin/dashboard` - Admin dashboard
- `POST /admin/approve-user` - Approve user
- `POST /admin/reject-user` - Reject user
- `GET /admin/reports` - View reports

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new features
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Contact the development team
- Check the documentation

## 🔄 Version History

### v1.0.0 (Current)
- Initial release
- Core banking features
- Admin and customer interfaces
- Security implementation
- Database integration

### Planned Features
- Mobile app integration
- API endpoints
- Advanced reporting
- Multi-currency support
- Real-time notifications

---

**Note**: This is a demonstration application. For production use, additional security measures, compliance checks, and thorough testing are required.
