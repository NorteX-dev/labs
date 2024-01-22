const statusText = document.getElementById("status");
statusText.innerText = "Rozpocznij pomiar";

function stoper(isStart) {
	if (isStart == 1) {
		// Start
		start = new Date();
		statusText.innerText = "Pomiar trwa";
		document.getElementById("label").innerText = "";
	}
	if (isStart == 0 && start > 0) {
		// Stop i wyświetlenie czasu
		stop = new Date();
		sekundy = Math.abs(stop - start) / 1000;
		start = 0;
		statusText.innerText = "Pomiar zakończony";
		document.getElementById("label").innerText = sekundy;
	}
}
