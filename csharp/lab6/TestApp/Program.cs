using Macierze;
using TestApp;

Macierz a = new Macierz(3, 2),
    b = new Macierz(3, 2);
a.InicjalizujMacierz();
Console.WriteLine("Macierz A: ");
a.WypiszMacierz();
Console.WriteLine("\nMacierz B: ");
b.InicjalizujMacierz();
b.WypiszMacierz();
Console.WriteLine("\nMacierz A + B: ");
(a + b).WypiszMacierz();
Console.WriteLine("\nMacierz A - B: ");
(a - b).WypiszMacierz();
Console.WriteLine("\nMacierz 2 • A: ");
(2 * a).WypiszMacierz();
Macierz c = new Macierz(2, 1);
c.InicjalizujMacierz();
Console.WriteLine("\nMacierz C: ");
c.WypiszMacierz();
Console.WriteLine("\nMacierz A * C: ");
(a * c).WypiszMacierz();
Macierz d = (Macierz)33;
Console.WriteLine("\nMacierz D: ");
d.WypiszMacierz();
