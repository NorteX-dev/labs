const pwd1 = document.querySelector("#pwd1");
const pwd2 = document.querySelector("#pwd2");
const form = document.querySelector("#password-form");
const errorLabel = document.querySelector("#error-label");

function validateIfMatch() {
	errorLabel.innerText = "";
	errorLabel.style.display = "none";
	if (pwd1.value !== pwd2.value) {
		errorLabel.innerText = "Hasła nie są identyczne";
		errorLabel.style.display = "block";
		return;
	}
}

form.addEventListener("submit", (e) => {
	e.preventDefault();
	validateIfMatch();
	// min. 1 duża litera i min. 8 znaków
	const regex = /^(?=.*[A-Z]).{8,}$/;
	console.log(regex.test(pwd1.value));
	if (!regex.test(pwd1.value)) {
		errorLabel.innerText = "Hasło nie spełnia wymagań";
		errorLabel.style.display = "block";
		return;
	}
	alert("Hasło poprawne");
});
