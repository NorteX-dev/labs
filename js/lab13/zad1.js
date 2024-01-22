function doUpload(uploader) {
	var file = uploader.files[0];
	console.log(file.type);
	// Dodany kod sprawdzający typ:
	if (!file.type.match("text.*")) {
		alert("Plik nie jest tekstem");
		return;
	}

	var reader = new FileReader();
	reader.readAsText(uploader.files[0], "UTF-8");
	reader.onprogress = function (evt) {
		console.log("Postęp wczytywania", evt);
	};
	reader.onload = function (evt) {
		document.getElementById("editor").innerHTML = evt.target.result;
	};
	reader.onerror = function (evt) {
		alert("Błąd wczytywania pliku!");
	};
}
