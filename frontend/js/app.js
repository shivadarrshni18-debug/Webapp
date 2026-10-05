document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("registration-form");
  const statusElement = document.getElementById("js-status");

  form.addEventListener("submit", async (e) => {
    e.preventDefault(); // This stops the data from going into the URL!
    statusElement.textContent = "Registering...";
    statusElement.style.color = "black";

    const requestData = {
      name: document.getElementById("name").value,
      phone: document.getElementById("phone").value,
      email: document.getElementById("email").value,
      password: document.getElementById("password").value,
    };

    try {
      const response = await fetch("http://localhost:8080/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(requestData),
      });

      if (response.ok) {
        const responseData = await response.json();
        statusElement.textContent = responseData.message;
        statusElement.style.color = "green";
        form.reset();
      } else {
        statusElement.textContent =
          "Registration failed. (Status: " + response.status + ")";
        statusElement.style.color = "red";
      }
    } catch (error) {
      statusElement.textContent = "Error connecting to the server.";
      statusElement.style.color = "red";
      console.error("Error:", error);
    }
  });
});
