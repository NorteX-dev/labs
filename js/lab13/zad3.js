let cnt = 0;
function increaseNumber() {
	document.querySelector("button").innerText = ++cnt;
}

function resetNumber() {
	document.querySelector("button").innerText = 0;
}
