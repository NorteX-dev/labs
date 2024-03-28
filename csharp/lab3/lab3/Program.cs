using lab3;

var os1 = new OsobaDlaKonsoli();
// os1.ZmienNazwisko();
// Console.WriteLine("\n•••••••••••••••\n");
// os1.WypiszOsobe();
// Console.WriteLine("\n***************\n");
var osoba2 = new Osoba(123, 1990, "Anna", "Kowalska", 12, 23, "Kwiatowa", "97-350",
    "Piotrków Tryb.");
os1.Osoba = osoba2;
// os1.ZmienAdres();
// Console.WriteLine();
// os1.ZmienNazwisko();
// Console.WriteLine("\n•••••••••••••••\n");
os1.WypiszOsobe();
Console.ReadKey();

namespace lab3
{
    internal class Osoba
    {
        private int rokUrodzenia;

        public Osoba(int numerEwidencyjny, int rokUrodzenia, string imie, string
            nazwisko, Adres adres)
        {
            this.rokUrodzenia = rokUrodzenia;
            NumerEwidencyjny = numerEwidencyjny;
            Imie = imie;
            Nazwisko = nazwisko;
            AdresZamieszkania = adres;
        }

        public Osoba(int numerEwidencyjny, int rokUrodzenia, string imie, string
            nazwisko, int numerDomu, int? numerMieszkania, string nazwaUlicy, string kod, string
            miejscowosc)
            : this(numerEwidencyjny, rokUrodzenia, imie, nazwisko, new
                Adres(numerDomu, numerMieszkania, nazwaUlicy, kod, miejscowosc))
        {
        }

        public Osoba(int numerEwidencyjny, int rokUrodzenia, string imie, int
            numerDomu)
            : this(numerEwidencyjny, rokUrodzenia, imie, "Kowalski", numerDomu, null,
                "Aleje Jerrozolimskie", "02-222", "Warszawa")
        {
        }

        public Osoba(Osoba osoba)
        {
            NumerEwidencyjny = osoba.NumerEwidencyjny;
            Imie = osoba.Imie;
            Nazwisko = osoba.Nazwisko;
            AdresZamieszkania = new Adres(osoba.AdresZamieszkania);
        }

        public string Nazwisko { set; get; }
        public string Imie { set; get; }
        public Adres AdresZamieszkania { set; get; }

        public int NumerEwidencyjny { get; }

        public int RokUrodzenia
        {
            get => rokUrodzenia;
            set
            {
                if (value > DateTime.Now.Year)
                    throw new ArgumentOutOfRangeException(
                        "Rok urodzenia musi być wcześniejszy od bieżącego");
                rokUrodzenia = value;
            }
        }

        public int Wiek => DateTime.Now.Year - rokUrodzenia;
    }

    internal class Adres
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

        public Adres(int numerDomu, int? numerMieszkania, string nazwaUlicy)
            : this(numerDomu, numerMieszkania, nazwaUlicy, "02-222", "Warszawa")
        {
        }

        public Adres(int numerDomu, int? numerMieszkania)
            : this(numerDomu, numerMieszkania, "Aleje Jerozolimskie")
        {
        }

        public Adres(int numerDomu)
            : this(numerDomu, null)
        {
        }

        public Adres(Adres adres)
            : this(adres.NumerDomu, adres.NumerMieszkania, adres.NazwaUlicy, adres.Kod,
                adres.Miejscowosc)
        {
        }

        public string Miejscowosc { get; set; }
        public string Kod { get; set; }
        public string NazwaUlicy { get; set; }
        public int NumerDomu { get; set; }
        public int? NumerMieszkania { get; set; }
    }

    internal class AdresDlaKonsoli
    {
        public AdresDlaKonsoli()
        {
            Console.Write("Podaj nazwę miejscowici: ");
            var miejscowosc =
                Console.ReadLine();
            Console.Write("Podaj kod: ");
            var kod = Console.ReadLine();
            Console.Write("Podaj nazwę ulicy: ");
            var nazwaUlicy = Console.ReadLine();
            int numerDomu;
            do
            {
                Console.Write("Podaj numer domu : ");
            } while (!int.TryParse(Console.ReadLine(), out numerDomu));

            Console.Write("Czy jest numer mieszkania <t/n>: ");
            var c = Console.ReadKey().KeyChar;
            int? numerMieszkania;
            if (c == 't')
            {
                int i;
                Console.WriteLine();
                do
                {
                    Console.Write("Podaj numer meszkania: ");
                } while (!int.TryParse(Console.ReadLine(), out i));

                numerMieszkania = i;
            }
            else
            {
                numerMieszkania = null;
            }

            Adres = new Adres(numerDomu, numerMieszkania, nazwaUlicy, kod, miejscowosc);
        }

        public AdresDlaKonsoli(Adres adres)
        {
            Adres = adres;
        }

        public Adres Adres { get; set; }

        public void ZmienMiejscowosc()
        {
            Console.Write("Podaj nazwę miejscowici: ");
            Adres.Miejscowosc = Console.ReadLine();
        }

        public void ZmienKod()
        {
            Console.Write("Podaj kod: ");
            Adres.Kod = Console.ReadLine();
        }

        public void ZmienUlice()
        {
            Console.Write("Podaj nazwę ulicy: ");
            Adres.NazwaUlicy = Console.ReadLine();
        }

        public void ZmienNumerDomu()
        {
            int numerDomu;
            do
            {
                Console.Write("Podaj numer domu: ");
            } while (!int.TryParse(Console.ReadLine(), out numerDomu));

            Adres.NumerDomu = numerDomu;
        }

        public void ZmienNrMieszkania()
        {
            Console.Write("Czy jest numer mieszkania <t/n>: ");
            var c = Console.ReadKey().KeyChar;
            if (c == 't')
            {
                int i;
                Console.WriteLine();
                do
                {
                    Console.Write("Podaj numer meszkania: ");
                } while (!int.TryParse(Console.ReadLine(), out i));

                Adres.NumerMieszkania = i;
            }
            else
            {
                Adres.NumerMieszkania = null;
            }
        }

        public void ZmienAdres()
        {
            ZmienMiejscowosc();
            ZmienKod();
            ZmienUlice();
            ZmienNumerDomu();
            ZmienNrMieszkania();
        }

        public void WypiszAdres()
        {
            Console.Write($"{Adres.Kod} {Adres.Miejscowosc} ul . {Adres.NazwaUlicy} nr {Adres.NumerDomu}");
            if (Adres.NumerMieszkania != null) Console.Write("/{0}", Adres.NumerMieszkania);
        }
    }

    internal class OsobaDlaKonsoli
    {
        public OsobaDlaKonsoli()
        {
            Console.Write("Podaj imię: ");
            var imie = Console.ReadLine();
            Console.Write("Podaj nazwisko: ");
            var nazwisko = Console.ReadLine();
            int numerEwidencyjny;
            do
            {
                Console.Write("Podaj numer ewidencyjny: ");
            } while (!int.TryParse(Console.ReadLine(), out numerEwidencyjny));

            int rokUrodzenia;
            do
            {
                Console.Write("Podaj rok urodzenia : ");
            } while (!int.TryParse(Console.ReadLine(), out rokUrodzenia));

            Console.WriteLine("Podaj adres zamieszkania:");
            var adr = new AdresDlaKonsoli();
            Osoba = new Osoba(numerEwidencyjny, rokUrodzenia, imie,
                nazwisko, adr.Adres);
        }

        public OsobaDlaKonsoli(Osoba osoba)
        {
            Osoba = osoba;
        }

        public Osoba Osoba { get; set; }

        public void ZmienNazwisko()
        {
            Console.Write("Podaj nazwisko: ");
            Osoba.Nazwisko = Console.ReadLine();
        }

        public void ZmienAdres()
        {
            Console.WriteLine("Podaj adres zamieszkania :");
            var adr = new AdresDlaKonsoli();
            Osoba.AdresZamieszkania = adr.Adres;
        }

        public void WypiszOsobe()
        {
            Console.Write(
                $"Pan(i) {Osoba.Imie} {Osoba.Nazwisko} numer ewidencyjny {Osoba.NumerEwidencyjny} lat {Osoba.Wiek}");
            var adr = new AdresDlaKonsoli(Osoba.AdresZamieszkania);
            adr.WypiszAdres();
        }
    }
}