const API_BASE = "http://localhost:8084/api/e_commerce";

async function addCartItem() {
  const data = {
    user: { id: parseInt(document.getElementById("userId").value) },
    product: { id: parseInt(document.getElementById("productId").value) },
    quantity: parseInt(document.getElementById("quantity").value)
  };

  try {
    const res = await fetch(`${API_BASE}/addCart`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data)
    });
    const result = await res.json();
    document.getElementById("response").textContent = result.description;
  } catch (err) {
    document.getElementById("response").textContent = "Error: " + err;
  }
}

async function addMultipleCartItems() {
  const data = [
    {
      user: { id: parseInt(document.getElementById("userId").value) },
      product: { id: parseInt(document.getElementById("productId").value) },
      quantity: parseInt(document.getElementById("quantity").value)
    },
    {
      user: { id: parseInt(document.getElementById("userId").value) },
      product: { id: parseInt(document.getElementById("productId").value) },
      quantity: parseInt(document.getElementById("quantity").value)
    }
  ];

  try {
    const res = await fetch(`${API_BASE}/addCart`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data)
    });
    const result = await res.json();
    document.getElementById("response").textContent = result.description;
  } catch (err) {
    document.getElementById("response").textContent = "Error: " + err;
  }
}
