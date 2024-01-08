// Funkcja walidująca tekst, wykonywana po podniesieniu klawisza ("onkeyup")
function validateText() {
	// Pobiera wartość z pola tekstowego o id="textfield"
	var val1 = document.forms["formularz"].tekst.value;
	// val2 ma taką samą wartość jak val1, po prostu inaczej pobrana
	var val2 = document.getElementById("textfield").value;
	// definicja elementu <label id="valLabel">
	var label = document.getElementById("valLabel");
	// reg to wyrażenie regularne, które sprawdza,
	// czy w val1 znajdują się tylko litery a-z lub A-Z, i czy jest ich co najmniej 3
	const reg = /^[a-zA-Z]{3,}$/g;
	// jeżeli nie spełnia warunków, to wyświetla "niepoprawne!"
	if (!reg.test(val1)) label.innerHTML = "niepoprawne!";
	// w przeciwnym wypadku czyści label
	else label.innerHTML = "";
}

// Funkcja validateCheckBox wywoływana po zmianie zaznaczenia checkboxa
function validateCheckBox() {
	// pobiera element #valLabel, czyli element <label>
	var label = document.getElementById("valLabel");

	// Jeżeli zaznaczono checkbox, to zmienia kolor tła labela na żółty,
	if (document.forms.formularz.elements["zaznacz"].checked) {
		label.style.backgroundColor = "#ffff00";
	} else {
		// w przeciwnym wypadku na biały.
		label.style.backgroundColor = "#ffffff";
	}
}
