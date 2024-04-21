namespace lab5;

public class Fabryka
{
    public static Instrument Utworz(int i)
    {
        Instrument instr = null;
        switch (i)
        {
            case 1:
                instr = new Instrument();
                break;
            case 2:
                instr = new Trabka();
                break;
        }

        return instr;
    }

    public static int Menu()
    {
        Console.Clear();
        Console.WriteLine("1 - Informacje ogólne o instrumentach");
        Console.WriteLine("2 - Informacje o trąbce");
        Console.WriteLine("0 - Koniec");
        int i;
        bool b;
        do
        {
            do
            {
                b = int.TryParse(Console.ReadLine(), out i);
            } while (!b);
        } while (0 > i || i > 2);

        return i;
    }
}