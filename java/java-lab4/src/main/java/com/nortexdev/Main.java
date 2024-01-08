package com.nortexdev;

public class Main {
	public static void main(String[] args) {
		// --- Tworzenie instancji klas potomnych
		Student student = new Student();
		Profesor profesor = new Profesor();
		Rektor rektor = new Rektor();

		// --- Rzutowanie w górę
		// Z racji że każda z tych klas rozszerza klasę "Osoba" to możemy je
		// umieścić w tablicy typu Osoba.
		Osoba[] osoby = new Osoba[3];
		osoby[0] = student;
		osoby[1] = profesor;
		osoby[2] = rektor;

		System.out.println("1.");
		for (Osoba osoba : osoby) {
			System.out.println("Imię: " + osoba.toString());
		}

		System.out.println("2.");
		// --- Klasa student ma własną metodę getAverage():
		System.out.println("Średnia studenta: " + student.getAverage());

		// W tym przypadku iteruję po tablicy i sprawdzam czy dana instancja
		// jest typu Student. Jeśli tak to rzutuję ją na typ Student i wywołuję getAverage().
		// Bez tego sprawdzania instanceof, kod by wyrzucił błąd, ponieważ rektor i profesor
		// nie mają metody getAverage().
		System.out.println("3.");
		for (Osoba osoba : osoby) {
			System.out.println("Imię: " + osoba.toString());
			if (osoba instanceof Student) {
				System.out.println("Średnia studenta: " + ((Student) osoba).getAverage());
			}
		}
	}
}
