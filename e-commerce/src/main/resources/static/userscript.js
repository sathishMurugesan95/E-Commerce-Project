const API_BASE = "http://localhost:8084/api/e_commerce";

// Check login
const loggedUser = JSON.parse(localStorage.getItem("user"));
if (!loggedUser) {
  window.location.href = "login.html";
}

// Logout
function logout() {
  localStorage.removeItem("user");
  window.location.href = "login.html";
}

// Load users
async function loadUsers() {
  try {
    const res = await fetch(`${API_BASE}/user`);
    if (!res.ok) throw new Error(`Error: ${res.status}`);
    const users = await res.json();
    const tbody = document.querySelector("#userTable tbody");
    tbody.innerHTML = "";

    users.forEach(user => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.email}</td>
        <td>${user.role}</td>
        <td>${user.userStatus}</td>
        <td>${user.userType}</td>
        <td>
          <button class="action-btn view-btn" onclick="viewUser(${user.id})">View</button>
          <button class="action-btn edit-btn" onclick="prefillEditUser(${user.id})">Edit</button>
          <button class="action-btn delete-btn" onclick="deleteUser(${user.id})">Delete</button>
        </td>
      `;
      tbody.appendChild(row);
    });
  } catch (err) {
    console.error(err);
    alert("Could not load users");
  }
}

// Add user
async function submitUserForm(event) {
  event.preventDefault();
  const userId = document.getElementById("userId").value;

  const userData = {
    username: document.getElementById("username").value.trim(),
    email: document.getElementById("email").value.trim(),
    password: document.getElementById("password").value.trim(),
    role: document.getElementById("role").value.trim(),
    userStatus: document.getElementById("status").value,
    userType: document.getElementById("userType").value
  };
  let isNewUser = false; // ✅ declare it here
  try {
    let res;
    if (userId) {
      // Update
      res = await fetch(`${API_BASE}/updateuser/${userId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(userData)
      });
    } else {
      // Add
      if (!userData.password) {
        alert("Password is required for new user.");
        return;
      }
      userData.userStatus = "ACTIVE";
      res = await fetch(`${API_BASE}/adduser`, {
		
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(userData)
      });
	  isNewUser = true; // ✅ mark as new user
    }

    const result = await res.json();
	
    if (!res.ok) throw new Error(result.description || result.message);

    alert(result.description || (userId ? "User updated successfully" : "User added successfully"));
    document.getElementById("addUserForm").reset();
    document.getElementById("formTitle").textContent = "Add User";
    document.getElementById("submitBtn").textContent = "Add User";
    loadUsers();
	
	
	// ✅ Redirect after new user
	       if (isNewUser) {
	           const newUserId = result.id; // Use 'id' from backend
	           console.log("New User ID:", newUserId); // Check browser console
	           if (!newUserId) {
	               alert("User created, but backend did not return ID");
	           } else {
	               window.location.href = `user-cart.html?userId=${newUserId}`;
	           }
	       }
	
  } catch (err) {
    console.error(err);
    alert(`Failed to submit user: ${err.message}`);
  }
}






// Prefill user for update
async function prefillEditUser(id) {
  try {
    const res = await fetch(`${API_BASE}/user/${id}`);
    if (!res.ok) throw new Error(`Error: ${res.status}`);
    const user = await res.json();

    document.getElementById("userId").value = user.id;
    document.getElementById("username").value = user.username;
    document.getElementById("email").value = user.email;
    document.getElementById("password").value = ""; 
    document.getElementById("role").value = user.role;
    document.getElementById("status").value = user.userStatus;
    document.getElementById("userType").value = user.userType;

    // Change form title and button text
    document.getElementById("formTitle").textContent = "Update User";
    document.getElementById("submitBtn").textContent = "Update User";
  } catch (err) {
    console.error(err);
    alert("Failed to load user for editing");
  }
}

// View user details
async function viewUser(id) {
  try {
    const res = await fetch(`${API_BASE}/user/${id}`);
    if (!res.ok) throw new Error(`Error: ${res.status}`);
    const user = await res.json();
    alert(
      `ID: ${user.id}\nUsername: ${user.username}\nEmail: ${user.email}\nRole: ${user.role}\nStatus: ${user.userStatus}\nUserType: ${user.userType}`
    );
  } catch (err) {
    console.error(err);
    alert("Failed to fetch user");
  }
}

// Delete user
async function deleteUser(id) {
  if (!confirm("Are you sure you want to delete this user?")) return;
  try {
    const res = await fetch(`${API_BASE}/delete/${id}`, { method: "DELETE" });
    const result = await res.json();
    if (!res.ok) throw new Error(result.description || result.message);

    alert(result.description || "User deleted successfully");
    loadUsers();
  } catch (err) {
    console.error(err);
    alert(`Failed to delete user: ${err.message}`);
  }
}

// Event listener
document.getElementById("addUserForm").addEventListener("submit", submitUserForm);
document.addEventListener("DOMContentLoaded", loadUsers);
