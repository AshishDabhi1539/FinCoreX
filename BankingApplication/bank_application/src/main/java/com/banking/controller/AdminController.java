package com.banking.controller;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.model.User;
import com.banking.model.Transaction;
import com.banking.service.AdminService;
import com.banking.service.UserService;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminService adminService = new AdminService();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User adminUser = (User) session.getAttribute("user");
        
        if (adminUser == null || !"ADMIN".equalsIgnoreCase(adminUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
            return;
        }

        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/dashboard";
        }

        // Don't intercept direct JSP requests
        if (pathInfo.endsWith(".jsp")) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            return;
        }

        switch (pathInfo) {
            case "/dashboard":
                showDashboard(request, response);
                break;
            case "/customers":
                showAllCustomers(request, response);
                break;
            case "/pending":
                showPendingApprovals(request, response);
                break;
            case "/transactions":
                showAllTransactions(request, response);
                break;
            case "/reports":
                showReports(request, response);
                break;
            case "/user-details":
                showUserDetails(request, response);
                break;
            case "/create-user":
                showCreateUserForm(request, response);
                break;
            case "/loan-management":
                showLoanManagement(request, response);
                break;
            case "/account-management":
                showAccountManagement(request, response);
                break;
            case "/search":
                searchUsers(request, response);
                break;
            case "/export":
                exportData(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User adminUser = (User) session.getAttribute("user");
        
        if (adminUser == null || !"ADMIN".equalsIgnoreCase(adminUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
            return;
        }

        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            return;
        }

        switch (pathInfo) {
            case "/approve":
                approveUser(request, response);
                break;
            case "/reject":
                rejectUser(request, response);
                break;
            case "/delete":
                deleteUser(request, response);
                break;
            case "/update":
                updateUser(request, response);
                break;
            case "/create-user":
                createUser(request, response);
                break;
            case "/create-account":
                createAccount(request, response);
                break;
            case "/close-account":
                closeAccount(request, response);
                break;
            case "/freeze-account":
                freezeAccount(request, response);
                break;
            case "/unfreeze-account":
                unfreezeAccount(request, response);
                break;
            case "/process-loan":
                processLoan(request, response);
                break;
            case "/bulk-approve":
                bulkApproveUsers(request, response);
                break;
            case "/bulk-reject":
                bulkRejectUsers(request, response);
                break;
            case "/send-notification":
                sendNotification(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                break;
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get comprehensive dashboard statistics
        long totalCustomers = adminService.getTotalCustomers();
        long pendingApprovals = adminService.getPendingApprovals();
        long activeAccounts = adminService.getActiveAccounts();
        double totalBalance = adminService.getTotalBalance();
        double totalDeposits = adminService.getTotalDeposits();
        double totalWithdrawals = adminService.getTotalWithdrawals();
        double totalTransfers = adminService.getTotalTransfers();
        
        // Get recent activity
        List<Transaction> recentTransactions = adminService.getRecentTransactions(5);
        List<User> recentUsers = adminService.getRecentUsers(5);
        
        request.setAttribute("totalCustomers", totalCustomers);
        request.setAttribute("pendingApprovals", pendingApprovals);
        request.setAttribute("activeAccounts", activeAccounts);
        request.setAttribute("totalBalance", totalBalance);
        request.setAttribute("totalDeposits", totalDeposits);
        request.setAttribute("totalWithdrawals", totalWithdrawals);
        request.setAttribute("totalTransfers", totalTransfers);
        request.setAttribute("recentTransactions", recentTransactions);
        request.setAttribute("recentUsers", recentUsers);
        
        request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
    }

    private void showAllCustomers(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String searchTerm = request.getParameter("search");
        String statusFilter = request.getParameter("status");
        
        List<User> customers;
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            customers = adminService.searchUsers(searchTerm);
        } else if (statusFilter != null && !statusFilter.equals("all")) {
            customers = adminService.getUsersByStatus(statusFilter);
        } else {
            customers = adminService.getAllCustomers();
        }
        
        request.setAttribute("customers", customers);
        request.setAttribute("searchTerm", searchTerm);
        request.setAttribute("statusFilter", statusFilter);
        request.getRequestDispatcher("/admin/customers.jsp").forward(request, response);
    }

    private void showPendingApprovals(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<User> pendingUsers = adminService.getPendingUsers();
        request.setAttribute("pendingUsers", pendingUsers);
        request.getRequestDispatcher("/admin/pending.jsp").forward(request, response);
    }

    private void showAllTransactions(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String typeFilter = request.getParameter("type");
        
        List<Transaction> transactions;
        if (startDate != null && endDate != null && !startDate.isEmpty() && !endDate.isEmpty()) {
            transactions = adminService.getTransactionsByDateRange(startDate, endDate);
        } else {
            transactions = adminService.getAllTransactions();
        }
        
        request.setAttribute("transactions", transactions);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.setAttribute("typeFilter", typeFilter);
        request.getRequestDispatcher("/admin/transactions.jsp").forward(request, response);
    }

    private void showReports(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get comprehensive reports data
        List<Transaction> recentTransactions = adminService.getRecentTransactions(20);
        double monthlyDeposits = adminService.getMonthlyDeposits();
        double monthlyWithdrawals = adminService.getMonthlyWithdrawals();
        double totalDeposits = adminService.getTotalDeposits();
        double totalWithdrawals = adminService.getTotalWithdrawals();
        double totalTransfers = adminService.getTotalTransfers();
        
        // Get user statistics
        long totalUsers = adminService.getTotalCustomers();
        long activeUsers = adminService.getActiveAccounts();
        long frozenUsers = adminService.getUsersByStatus("FROZEN").size();
        long closedUsers = adminService.getUsersByStatus("CLOSED").size();
        
        request.setAttribute("recentTransactions", recentTransactions);
        request.setAttribute("monthlyDeposits", monthlyDeposits);
        request.setAttribute("monthlyWithdrawals", monthlyWithdrawals);
        request.setAttribute("totalDeposits", totalDeposits);
        request.setAttribute("totalWithdrawals", totalWithdrawals);
        request.setAttribute("totalTransfers", totalTransfers);
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("activeUsers", activeUsers);
        request.setAttribute("frozenUsers", frozenUsers);
        request.setAttribute("closedUsers", closedUsers);
        
        request.getRequestDispatcher("/admin/reports.jsp").forward(request, response);
    }

    private void showUserDetails(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        long userId = Long.parseLong(request.getParameter("userId"));
        User user = adminService.getUserById(userId);
        List<Transaction> userTransactions = adminService.getUserTransactions(userId);
        
        request.setAttribute("user", user);
        request.setAttribute("userTransactions", userTransactions);
        request.getRequestDispatcher("/admin/user-details.jsp").forward(request, response);
    }

    private void showCreateUserForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.getRequestDispatcher("/admin/create-user.jsp").forward(request, response);
    }

    private void showLoanManagement(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<User> loanApplicants = adminService.getLoanApplicants();
        request.setAttribute("loanApplicants", loanApplicants);
        request.getRequestDispatcher("/admin/loan-management.jsp").forward(request, response);
    }

    private void showAccountManagement(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<User> accounts = adminService.getAllCustomers();
        request.setAttribute("accounts", accounts);
        request.getRequestDispatcher("/admin/account-management.jsp").forward(request, response);
    }

    private void searchUsers(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String searchTerm = request.getParameter("q");
        List<User> searchResults = adminService.searchUsers(searchTerm);
        
        request.setAttribute("searchResults", searchResults);
        request.setAttribute("searchTerm", searchTerm);
        request.getRequestDispatcher("/admin/search-results.jsp").forward(request, response);
    }

    private void exportData(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String exportType = request.getParameter("type");
        String format = request.getParameter("format");
        
        // Set response headers for file download
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=" + exportType + "_" + 
                          LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".csv");
        
        // Export logic would go here
        // For now, redirect back to reports
        response.sendRedirect(request.getContextPath() + "/admin/reports");
    }

    // POST Methods
    private void approveUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            long userId = Long.parseLong(request.getParameter("userId"));
            boolean success = adminService.approveUser(userId);
            
            if (success) {
                request.getSession().setAttribute("success", "✅ User approved successfully!");
            } else {
                request.getSession().setAttribute("error", "❌ Failed to approve user. Please try again.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "❌ Invalid user ID provided.");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "❌ An error occurred while approving the user. Please try again.");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/pending");
    }

    private void rejectUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            long userId = Long.parseLong(request.getParameter("userId"));
            String reason = request.getParameter("reason");
            
            if (reason == null || reason.trim().isEmpty()) {
                reason = "No specific reason provided";
            }
            
            boolean success = adminService.rejectUser(userId, reason);
            
            if (success) {
                request.getSession().setAttribute("success", "✅ User rejected successfully!");
            } else {
                request.getSession().setAttribute("error", "❌ Failed to reject user. Please try again.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "❌ Invalid user ID provided.");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "❌ An error occurred while rejecting the user. Please try again.");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/pending");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            long userId = Long.parseLong(request.getParameter("userId"));
            boolean success = adminService.deleteUser(userId);
            
            if (success) {
                request.getSession().setAttribute("success", "✅ User deleted successfully!");
            } else {
                request.getSession().setAttribute("error", "❌ Failed to delete user. Please try again.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "❌ Invalid user ID provided.");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "❌ An error occurred while deleting the user. Please try again.");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/customers");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            long userId = Long.parseLong(request.getParameter("userId"));
            String status = request.getParameter("status");
            String role = request.getParameter("role");
            
            boolean success = adminService.updateUser(userId, status, role);
            
            if (success) {
                request.getSession().setAttribute("success", "✅ User updated successfully!");
            } else {
                request.getSession().setAttribute("error", "❌ Failed to update user. Please try again.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "❌ Invalid user ID provided.");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "❌ An error occurred while updating the user. Please try again.");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/customers");
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // Extract user data from request parameters
            String fullName = request.getParameter("fullName");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String accountType = request.getParameter("accountType");
            double initialDeposit = Double.parseDouble(request.getParameter("deposit"));
            
            boolean success = adminService.createUserDirectly(fullName, username, email, phone, accountType, initialDeposit);
            
            if (success) {
                request.getSession().setAttribute("success", "✅ User created successfully!");
            } else {
                request.getSession().setAttribute("error", "❌ Failed to create user. Please try again.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "❌ Invalid deposit amount provided.");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "❌ An error occurred while creating the user. Please try again.");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/create-user");
    }

    private void createAccount(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            long userId = Long.parseLong(request.getParameter("userId"));
            String accountType = request.getParameter("accountType");
            double initialDeposit = Double.parseDouble(request.getParameter("initialDeposit"));
            
            boolean success = adminService.createAccount(userId, accountType, initialDeposit);
            
            if (success) {
                request.getSession().setAttribute("success", "✅ Account created successfully!");
            } else {
                request.getSession().setAttribute("error", "❌ Failed to create account. Please try again.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "❌ Invalid user ID or deposit amount provided.");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "❌ An error occurred while creating the account. Please try again.");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/account-management");
    }

    private void closeAccount(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            long userId = Long.parseLong(request.getParameter("userId"));
            boolean success = adminService.closeAccount(userId);
            
            if (success) {
                request.getSession().setAttribute("success", "✅ Account closed successfully!");
            } else {
                request.getSession().setAttribute("error", "❌ Failed to close account. Please try again.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "❌ Invalid user ID provided.");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "❌ An error occurred while closing the account. Please try again.");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/account-management");
    }

    private void freezeAccount(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            long userId = Long.parseLong(request.getParameter("userId"));
            boolean success = adminService.freezeAccount(userId);
            
            if (success) {
                request.getSession().setAttribute("success", "✅ Account frozen successfully!");
            } else {
                request.getSession().setAttribute("error", "❌ Failed to freeze account. Please try again.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "❌ Invalid user ID provided.");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "❌ An error occurred while freezing the account. Please try again.");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/account-management");
    }

    private void unfreezeAccount(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            long userId = Long.parseLong(request.getParameter("userId"));
            boolean success = adminService.unfreezeAccount(userId);
            
            if (success) {
                request.getSession().setAttribute("success", "✅ Account unfrozen successfully!");
            } else {
                request.getSession().setAttribute("error", "❌ Failed to unfreeze account. Please try again.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "❌ Invalid user ID provided.");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "❌ An error occurred while unfreezing the account. Please try again.");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/account-management");
    }

    private void processLoan(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            long userId = Long.parseLong(request.getParameter("userId"));
            String action = request.getParameter("action"); // "approve" or "reject"
            String reason = request.getParameter("reason");
            double amount = Double.parseDouble(request.getParameter("amount"));
            
            boolean success = adminService.processLoan(userId, action, amount, reason);
            
            if (success) {
                request.getSession().setAttribute("success", "✅ Loan " + action + "ed successfully!");
            } else {
                request.getSession().setAttribute("error", "❌ Failed to " + action + " loan. Please try again.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "❌ Invalid user ID or loan amount provided.");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "❌ An error occurred while processing the loan. Please try again.");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/loan-management");
    }

    private void bulkApproveUsers(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            String[] userIds = request.getParameterValues("userIds");
            if (userIds == null || userIds.length == 0) {
                request.getSession().setAttribute("error", "❌ No users selected for bulk approval.");
                response.sendRedirect(request.getContextPath() + "/admin/pending");
                return;
            }
            
            // Convert String array to List<Long>
            List<Long> userIdList = new ArrayList<>();
            for (String userIdStr : userIds) {
                try {
                    userIdList.add(Long.parseLong(userIdStr));
                } catch (NumberFormatException e) {
                    // Skip invalid user IDs
                }
            }
            
            int updatedCount = adminService.bulkUpdateUserStatus(userIdList, "ACTIVE");
            
            if (updatedCount > 0) {
                request.getSession().setAttribute("success", "✅ " + updatedCount + " users approved successfully!");
            } else {
                request.getSession().setAttribute("error", "❌ No users were approved. Please try again.");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("error", "❌ An error occurred during bulk approval. Please try again.");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/pending");
    }

    private void bulkRejectUsers(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            String[] userIds = request.getParameterValues("userIds");
            if (userIds == null || userIds.length == 0) {
                request.getSession().setAttribute("error", "❌ No users selected for bulk rejection.");
                response.sendRedirect(request.getContextPath() + "/admin/pending");
                return;
            }
            
            // Convert String array to List<Long>
            List<Long> userIdList = new ArrayList<>();
            for (String userIdStr : userIds) {
                try {
                    userIdList.add(Long.parseLong(userIdStr));
                } catch (NumberFormatException e) {
                    // Skip invalid user IDs
                }
            }
            
            int updatedCount = adminService.bulkUpdateUserStatus(userIdList, "REJECTED");
            
            if (updatedCount > 0) {
                request.getSession().setAttribute("success", "✅ " + updatedCount + " users rejected successfully!");
            } else {
                request.getSession().setAttribute("error", "❌ No users were rejected. Please try again.");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("error", "❌ An error occurred during bulk rejection. Please try again.");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/pending");
    }

    private void sendNotification(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            String[] userIds = request.getParameterValues("userIds");
            String message = request.getParameter("message");
            String type = request.getParameter("type");
            
            if (userIds == null || userIds.length == 0) {
                request.getSession().setAttribute("error", "❌ No users selected for notification.");
                response.sendRedirect(request.getContextPath() + "/admin/customers");
                return;
            }
            
            if (message == null || message.trim().isEmpty()) {
                request.getSession().setAttribute("error", "❌ Notification message is required.");
                response.sendRedirect(request.getContextPath() + "/admin/customers");
                return;
            }
            
            if (type == null || type.trim().isEmpty()) {
                type = "EMAIL";
            }
            
            int sentCount = adminService.sendNotification(userIds, message, type);
            
            if (sentCount > 0) {
                request.getSession().setAttribute("success", "✅ " + sentCount + " notifications sent successfully!");
            } else {
                request.getSession().setAttribute("error", "❌ No notifications were sent. Please try again.");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("error", "❌ An error occurred while sending notifications. Please try again.");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/customers");
    }
}
