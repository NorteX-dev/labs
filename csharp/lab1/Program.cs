﻿using csharp;

var konta = new Konto[2];
konta[0] = new Konto();
konta[1] = new Konto();

konta[0].Wlasciciel = new Osoba();
konta[1].Wlasciciel = new Osoba();
konta[0].Wlasciciel.Imie = "Jan";
konta[0].Wlasciciel.Nazwisko = "Kowalski";
konta[1].Wlasciciel.Imie = "Ala";
konta[1].Wlasciciel.Nazwisko = "Kot";
Console.WriteLine("Próba zmiany pinu: {0}", konta[0].ZmienPin(1111, 0));
Console.WriteLine("Próba zmiany pinu: {0}", konta[1].ZmienPin(1111, 1111));
Console.WriteLine("Dokonujemy wpłat:");
konta[0].DokonajWplaty(1200);
konta[1].DokonajWplaty(2200);
Console.WriteLine("Dokonujemy wypłaty: {0}", konta[0].DokonajWyplaty(300, 1111));
Console.WriteLine("Dokonujemy wypłaty: {0}", konta[0].DokonajWyplaty(3000, 1111));
Console.WriteLine("Dokonujemy wypłaty: {0}", konta[1].DokonajWyplaty(200, 1111));
Console.WriteLine("Informacje o koncie: {0}", konta[0].PobierzInformacje(1111));
Console.WriteLine("Informacje o koncie: {0}", konta[1].PobierzInformacje(1111));
Console.WriteLine("Informacje o koncie: {0}", konta[1].PobierzInformacje(0));
Console.ReadKey();

konta[0].Saldo = 2000; // Ustawia saldo
konta[0].Pin = 2345; // Zmiena pin bez potwierdzenia starego
// Metoda SprawdzPin została zamieniona na public
var isCorrect = konta[0].SprawdzPin(1111); // Sprawdza pin, zwraca bool.