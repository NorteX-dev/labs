using lab2;

var os1 = new Osoba(1, "Jan", "Nowak", 23, 12, "Kwiatowa", "97-300", "Piotrków Tryb");

var klon1 = os1.Klonuj();
Console.WriteLine($"Oryginał {os1.ZwrocinformacjeOsobowe()}");
Console.WriteLine($"Klon: {klon1.ZwrocinformacjeOsobowe()}");

Console.WriteLine("***Zmieniamy klona: ***");
klon1.ZmienDaneOsobowe();
Console.WriteLine($"\nOryginal: {os1.ZwrocinformacjeOsobowe()}");
Console.WriteLine($"Klon: {klon1.ZwrocinformacjeOsobowe()}");

Console.WriteLine("***Kopiowanie głębokie ***");
var adr = new Adres(13);
var os2 = new Osoba(10, "Jacek", "Wiśniewski", adr);
var klon2 = new Osoba(os2);
Console.WriteLine($"Oryginal drugi: {os2.ZwrocinformacjeOsobowe()}");
Console.WriteLine($"Klon drugi: {klon2.ZwrocinformacjeOsobowe()}");

Console.WriteLine("***Zmieniamy drugiego klona:***");
klon2.ZmienDaneOsobowe();
Console.WriteLine($"Oryginal drugi: {os2.ZwrocinformacjeOsobowe()}");
Console.WriteLine($"Klon drugi : {klon2.ZwrocinformacjeOsobowe()}");