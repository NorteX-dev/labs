namespace lab2;

public class Osoba
{
    public Osoba(int numerEwidencyjny, string imie, string nazwisko, int numerDomu,
        int? numerMieszkania, string nazwaUlicy, string kod, string miejscowosc)
    {
        NumerEwidencyjny = numerEwidencyjny;
        Imie = imie;
        Nazwisko = nazwisko;
        AdresZamieszkania = new Adres(numerDomu, numerMieszkania, nazwaUlicy, kod,
            miejscowosc);
    }

    public Osoba(int numerEwidencyjny, string imie, string nazwisko, Adres adres) :
        this(numerEwidencyjny, imie, nazwisko, adres.ZwrocNumerDomu(),
            adres.ZwrocNumerMieszkania(), adres.ZwrocNazweUlicy(), adres.ZwrocKod(),
            adres.ZwrocMiejscowosc())
    {
    }

    public Osoba(int numerEwidencyjny, string imie, int numerDomu) :
        this(numerEwidencyjny, imie, "Kowalski", numerDomu, null, "Aleje Jerozolimskie", "02-222", "Warszawa")
    {
    }

    public Osoba(Osoba osoba)
    {
        NumerEwidencyjny = osoba.NumerEwidencyjny;
        Imie = osoba.Imie;
        Nazwisko = osoba.Nazwisko;
        // AdresZamieszkania = osoba.AdresZamieszkania;
        AdresZamieszkania = new Adres(osoba.AdresZamieszkania);
    }

    private string Nazwisko { get; set; }
    private string Imie { get; set; }
    private int NumerEwidencyjny { get; set; }
    private Adres AdresZamieszkania { get; }

    public string ZwrocinformacjeOsobowe()
    {
        return
            $"Pan(i) {Imie} {Nazwisko} o numerze ewidencyjnym {NumerEwidencyjny} zamieszkały(a): {AdresZamieszkania.ZwrocinformacjeAdresowe()}";
    }

    public Osoba Klonuj()
    {
        return (Osoba)MemberwiseClone();
    }

    public void ZmienDaneOsobowe()
    {
        Console.Write("Podaj imię: ");
        Imie = Console.ReadLine();
        Console.Write("Podaj nazwisko: ");
        Nazwisko = Console.ReadLine();
        int numerEwidencyjny;
        do
        {
            Console.Write("Podaj numer ewidencyjny: ");
        } while (!int.TryParse(Console.ReadLine(), out numerEwidencyjny));

        NumerEwidencyjny = numerEwidencyjny;
        AdresZamieszkania.ZmienAdres();
    }
}

public class Adres
{
    public Adres(int numerDomu, int? numerMieszkania, string nazwaUlicy, string kod,
        string miejscowosc)
    {
        NumerDomu = numerDomu;
        NumerMieszkania = numerMieszkania;
        NazwaUlicy = nazwaUlicy;
        Kod = kod;
        Miejscowosc = miejscowosc;
    }

    public Adres(int numerDomu, int? numerMieszkania, string nazwaUlicy) :
        this(numerDomu, numerMieszkania, nazwaUlicy, "02-222", "Warszawa")
    {
    }

    public Adres(int numerDomu, int? numerMieszkania) : this(numerDomu,
        numerMieszkania, "Aleje Jerozolimskie")
    {
    }

    public Adres(int numerDomu) : this(numerDomu, null)
    {
    }

    public Adres(Adres adres) : this(adres.NumerDomu, adres.NumerMieszkania,
        adres.NazwaUlicy, adres.Kod, adres.Miejscowosc)
    {
    }

    private string Miejscowosc { get; set; }
    private string Kod { get; set; }
    private string NazwaUlicy { get; set; }
    private int NumerDomu { get; set; }
    private int? NumerMieszkania { get; set; }


    public string ZwrocinformacjeAdresowe()
    {
        return NumerMieszkania != null
            ? $"{Kod} {Miejscowosc} ul. {NazwaUlicy} {NumerDomu} m. {NumerMieszkania}"
            : $"{Kod} {Miejscowosc} ul. {NazwaUlicy} {NumerDomu}";
    }

    public string ZwrocMiejscowosc()
    {
        return Miejscowosc;
    }

    public string ZwrocNazweUlicy()
    {
        return NazwaUlicy;
    }

    public string ZwrocKod()
    {
        return Kod;
    }

    public int ZwrocNumerDomu()
    {
        return NumerDomu;
    }

    public int? ZwrocNumerMieszkania()
    {
        return NumerMieszkania;
    }

    public void ZmienAdres()
    {
        Console.Write("Podaj nazwę miejscowości: ");
        Miejscowosc = Console.ReadLine();
        Console.Write("Podaj kod: ");
        Kod = Console.ReadLine();
        Console.Write("Podaj nazwę ulicy: ");
        NazwaUlicy = Console.ReadLine();
        int numerDomu; // <-- DODANO
        do
        {
            Console.Write("Podaj numer domu: ");
        } while (!int.TryParse(Console.ReadLine(), out numerDomu));

        NumerDomu = numerDomu; // <-- DODANO
        Console.Write("Czy jest numer mieszkania <t/n>: ");
        var c = Console.ReadKey().KeyChar;
        if (c == 't')
        {
            Console.WriteLine();
            int i;
            do
            {
                Console.Write("Podaj numer meszkania: ");
            } while (!int.TryParse(Console.ReadLine(), out i));

            NumerMieszkania = i;
        }
        else
        {
            NumerMieszkania = null;
        }
    }
}