const date1 = document.getElementById("date1");
const date2 = document.getElementById("date2");
const result = document.getElementById("result");

function calcDiff() {
	const d1 = new Date(date1.value);
	const d2 = new Date(date2.value);
	const diff = Math.abs(d1 - d2);
	result.innerText = diff / 1000;
}
