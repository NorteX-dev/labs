package com.nortexdev;

public abstract class Osoba {
	public abstract String getFirstName();

	public abstract String getLastName();

	// Z racji że jest to klasa abstrakcyjna, można definiować metody
	// które posiadają implementację. W tym przypadku jest to metoda toString().
	// W interfejsach nie można definiować metod z implementacją.
	public String toString() {
		return getFirstName() + " " + getLastName();
	}
}

