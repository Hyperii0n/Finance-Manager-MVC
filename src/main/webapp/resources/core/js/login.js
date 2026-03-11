import { validateLoginFields } from "./login_functions.js";

const loginBtn = document.getElementById("btn-login");
const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");

emailInput.addEventListener("keyup", (event) => {
  const emailValue = event.target.value;
  const passwordValue = passwordInput.value;
  loginBtn.disabled = !validateLoginFields(emailValue, passwordValue);
});

passwordInput.addEventListener("keyup", (event) => {
  const emailValue = emailInput.value;
  const passwordValue = event.target.value;
  loginBtn.disabled = !validateLoginFields(emailValue, passwordValue);
});