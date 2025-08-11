document.getElementById("loadBtn").addEventListener("click", function () {
  fetch("https://dummyjson.com/todos")
    .then(res => res.json())
    .then(data => {
      const todos = data.todos;
      const tableBody = document.getElementById("todoBody");
      const table = document.getElementById("todoTable");

      

      todos.forEach(todo => {
        const row = document.createElement("tr");

        row.innerHTML = `
          <td>${todo.id}</td>
          <td>${todo.todo}</td>
          <td class="${todo.completed ? 'completed' : 'pending'}">
            ${todo.completed ? 'Yes' : 'No'}
          </td>
          <td>${todo.userId}</td>
        `;

        tableBody.appendChild(row);
      });

      table.style.display = "table"; 
    })
    .catch(error => {
      console.error("Error fetching data:", error);
    });
});
