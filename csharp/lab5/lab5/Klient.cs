namespace lab5;

public class Klient
{
    private Instrument instrument;

    public int Menu()
    {
        Console.Clear();
        Console.WriteLine("Twój aktualny wybór to {0}\n", instrument);
        Console.WriteLine("1 - Przykładowe diwięki wydawane przez instrument");
        Console.WriteLine("2 - Krótka charakterystyka instrumentu");
        Console.WriteLine("3 - Powrót do menu głównego");
        int i;
        bool b;
        do
        {
            b = int.TryParse(Console.ReadLine(), out i);
        } while (!b);

        return i;
    }

    public void Uruchom()
    {
        int i, j;
        while (true)
        {
            i = Fabryka.Menu();
            if (i == 0)
                break;
            instrument = Fabryka.Utworz(i);
            do
            {
                j = Menu();
                Console.Clear();
                switch (j)
                {
                    case 1:
                        instrument.Graj();
                        Console.ReadKey();
                        break;
                    case 2:
                        Console.WriteLine(instrument.Opis);
                        Console.ReadKey();
                        break;
                }
            } while (j != 3);
        }
    }
}