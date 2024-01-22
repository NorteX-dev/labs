function changeColor() {
	const color = document.querySelector("input").value;
	if (["#000000", "#ffffff"].includes(color)) {
		alert("Nieprawidłowy kolor");
		return;
	}
	document.querySelector("button").style.backgroundColor = color;
}
