namespace csharp;

public class Konto
{
    public Osoba Wlasciciel { get; set; }
    public decimal Saldo { get; set; }
    public int Pin { get; set; }

    public bool SprawdzPin(int pin)
    {
        return pin == Pin;
    }

    public void DokonajWplaty(decimal kwota)
    {
        if (kwota < 0) throw new ArgumentException("Wpłacana kwota nie moie byt mniejsza od zera");

        Saldo += kwota;
    }

    public string DokonajWyplaty(decimal kwota, int pin)
    {
        if (!SprawdzPin(pin) || Saldo < kwota) return "Operacja anulowana 0";

        Saldo -= kwota;
        return "Operację zakończona pomyślnie";
    }

    public bool ZmienPin(int nowy, int stary)
    {
        if (SprawdzPin(stary))
        {
            Pin = nowy;
            return true;
        }

        return false;
    }

    public string PobierzInformacje(int pin)
    {
        if (SprawdzPin(pin)) return $"Pan/i {Wlasciciel.Imie} {Wlasciciel.Nazwisko} posiada na koncie: {Saldo}.";
        return "Brak dostepu";
    }
}