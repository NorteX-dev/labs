const form = document.querySelector("form");
const rows = document.querySelector("#rows");
const columns = document.querySelector("#cols");

form.addEventListener("submit", (e) => {
	e.preventDefault();
	const rowsValue = rows.value;
	const columnsValue = columns.value;
	if (rowsValue < 1 || columnsValue < 1) {
		alert("Nieprawidłowe dane");
		return;
	}
	const table = document.querySelector("table");
	table.innerHTML = "";
	for (let i = 0; i < rowsValue; i++) {
		const tr = document.createElement("tr");
		for (let j = 0; j < columnsValue; j++) {
			const td = document.createElement("td");
			td.textContent = `${i + 1}, ${j + 1}`;
			tr.appendChild(td);
		}
		table.appendChild(tr);
	}
});
