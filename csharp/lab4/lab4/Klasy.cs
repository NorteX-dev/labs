namespace lab4;

public enum Plec
{
    Kobieta,
    Mezczyzna
}

public class Osoba
{
    public Osoba(string imie, string nazwisko, int rokUrodzenia, Plec plec)
    {
        Imie = imie;
        Nazwisko = nazwisko;
        RokUrodzenia = rokUrodzenia;
        Plec = plec;
    }

    public string Imie { set; get; }
    public string Nazwisko { set; get; }
    protected Plec Plec { set; get; }

    public int RokUrodzenia { get; }

    public string ZwrocInformacje()
    {
        if (Plec == Plec.Kobieta)
            return $"Pani {Imie} {Nazwisko} urodzona w {RokUrodzenia}";
        return $"Pan {Imie} {Nazwisko} urodzony w {RokUrodzenia}";
    }
}

public class Student : Osoba
{
    public Student(string imie, string nazwisko, int rokUrodzenia, Plec plec, int numerindeksu) : base(imie, nazwisko,
        rokUrodzenia, plec)
    {
        Numerindeksu = numerindeksu;
    }

    public Student(Student student) : base(student.Imie, student.Nazwisko, student.RokUrodzenia, student.Plec)
    {
        Numerindeksu = student.Numerindeksu;
    }

    public int Numerindeksu { get; }

    public new string ZwrocInformacje()
    {
        return $"{base.ZwrocInformacje()} numer indeksu {Numerindeksu}";
    }
}

public enum Tytuly
{
    dr,
    dr_hab,
    prof
}

public class Wykladowca : Osoba
{
    public Wykladowca() { }
    public Wykladowca(string imie, string nazwisko, int rokUrodzenia, Plec plec, Tytuly tytul) : base(imie, nazwisko,
        rokUrodzenia, plec)
    {
        Tytul = tytul;
    }

    public Tytuly Tytul { set; get; }

    private string zwrocTytul()
    {
        var tytul = "";
        switch (Tytul)
        {
            case Tytuly.dr:
                tytul = "dr";
                break;
            case Tytuly.dr_hab:
                tytul = "dr hab.";
                break;
            case Tytuly.prof:
                tytul = "prof.";
                break;
        }

        return tytul;
    }

    public string Zwrocinformacje()
    {
        if (Plec == Plec.Kobieta)
            return string.Format("Pani {3} {0} {1} urodzona w {2}",
                Imie, Nazwisko, RokUrodzenia, zwrocTytul());
        return string.Format("Pan {3} {0} {1} urodzony w {2}",
            Imie, Nazwisko, RokUrodzenia, zwrocTytul());
    }
}

public class Stypendysta : Student
{
    public Stypendysta(string imie, string nazwisko, int rokUrodzenia,
        Plec plec, int numerindeksu, decimal stypendium)
        : base(imie, nazwisko, rokUrodzenia, plec, numerindeksu)
    {
        {
            Stypendium = stypendium;
        }
    }

    public decimal Stypendium { set; get; }

    public new string ZwrocInformacje()
    {
        return string.Format("{0} ma przyznane stypendium w " +
                             "wysokości {1:C}", base.ZwrocInformacje(), Stypendium);
    }
}