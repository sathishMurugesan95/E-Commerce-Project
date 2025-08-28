const API_BASE = "http://localhost:8084/api/e_commerce";

document.getElementById("loginForm").addEventListener("submit", async (event) => {
  event.preventDefault();

  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value.trim();

  try {
    const res = await fetch(`${API_BASE}/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password })
    });

    const result = await res.json();

    if (!res.ok) throw new Error(result.message || "Login failed");

    // Save user info in localStorage
    localStorage.setItem("user", JSON.stringify(result));

    // Redirect based on userType
    if (result.userType === "ADMIN") {
      window.location.href = "users.html"; // Admin goes to user management
    } else {
      window.location.href = "home.html"; // Other users go to home
    }

  } catch (err) {
    console.error(err);
    document.getElementById("message").textContent = err.message;
  }
});
