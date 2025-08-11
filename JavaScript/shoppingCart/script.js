function addToCart() {
  const productSelect = document.getElementById("product");
  const quantityInput = document.getElementById("quantity");
  const cartBody = document.getElementById("cart-body");
  const emptyMessage = document.getElementById("empty-message");
  const cartTable = document.getElementById("cart-table");

  const selectedOption = productSelect.options[productSelect.selectedIndex];
  const productName = selectedOption.text.split(" - ₹")[0];
  const price = parseFloat(selectedOption.dataset.price);
  const stock = parseInt(selectedOption.dataset.stock);
  const newQuantity = parseInt(quantityInput.value);
  const date = new Date().toLocaleDateString();

  if (productSelect.selectedIndex === 0 || !productSelect.value) {
    alert("Please select a product.");
    return;
  }

  cartTable.style.display = "table";
  emptyMessage.style.display = "none";

  const rows = cartBody.getElementsByTagName("tr");
  for (let i = 0; i < rows.length; i++) {
    const cells = rows[i].getElementsByTagName("td");
    if (cells[0].textContent === productName) {
      const existingQty = parseInt(cells[2].textContent);
      const totalQty = existingQty + newQuantity;


      cells[2].textContent = totalQty;
      cells[3].textContent = (price * totalQty).toFixed(2);
      cells[4].textContent = date;
      updateGrandTotal();
      return;
    }
  }

  

  const row = document.createElement("tr");
  row.innerHTML = `
    <td>${productName}</td>
    <td>${price.toFixed(2)}</td>
    <td>${newQuantity}</td>
    <td>${(price * newQuantity).toFixed(2)}</td>
    <td>${date}</td>
    <td><button onclick="removeItem(this)">Remove</button></td>
  `;
  cartBody.appendChild(row);

  updateGrandTotal();
}

function updateGrandTotal() {
  const cartBody = document.getElementById("cart-body");
  const rows = cartBody.getElementsByTagName("tr");
  let grandTotal = 0;

  for (let i = 0; i < rows.length; i++) {
    const totalCell = rows[i].getElementsByTagName("td")[3];
    grandTotal += parseFloat(totalCell.textContent);
  }

  document.getElementById("grand-total").textContent = grandTotal.toFixed(2);
}

function removeItem(button) {
  const row = button.closest("tr");
  const cells = row.getElementsByTagName("td");

  const productName = cells[0].textContent;
  const currentQty = parseInt(cells[2].textContent);
  const price = parseFloat(cells[1].textContent);

  const toRemove = prompt(`How many '${productName}' items do you want to remove? (Max: ${currentQty})`);
  const removeQty = parseInt(toRemove);

  if (isNaN(removeQty) || removeQty <= 0) {
    alert("Please enter a valid positive number.");
    return;
  }

  if (removeQty > currentQty) {
    alert(`You only have ${currentQty} '${productName}' items in the cart.`);
    return; 
  }

  const newQty = currentQty - removeQty;

  if (newQty === 0) {
    row.remove();
  } else {
    cells[2].textContent = newQty;
    cells[3].textContent = (price * newQty).toFixed(2);
    cells[4].textContent = new Date().toLocaleDateString();
  }

  updateGrandTotal();
}


