// Search functionality for tables
document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchCustomer');
    if (searchInput) {
        searchInput.addEventListener('input', function() {
            const filter = this.value.toLowerCase();
            const rows = document.querySelectorAll('#customersTable tbody tr');
            rows.forEach(row => {
                const text = row.textContent.toLowerCase();
                row.style.display = text.includes(filter) ? '' : 'none';
            });
        });
    }

    // Add animation to form inputs
    const inputs = document.querySelectorAll('.form-control, .form-select');
    inputs.forEach(input => {
        input.addEventListener('focus', function() {
            this.classList.add('animated', 'fadeInUp');
        });
        input.addEventListener('blur', function() {
            this.classList.remove('animated', 'fadeInUp');
        });
    });

    // Confirm actions for critical buttons
    const actionButtons = document.querySelectorAll('.btn-outline-danger, .btn-danger');
    actionButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            if (!confirm('Are you sure you want to perform this action?')) {
                e.preventDefault();
            }
        });
    });
});