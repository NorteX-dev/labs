namespace lab5;

public class Trabka : Instrument
{
    public override string Opis => "Trąbka to instrument dęty blaszany o wysokim brzmieniu. ";

    public override void Graj()
    {
        Console.WriteLine("Trąbka gra ... ");
    }

    public override string ToString()
    {
        return "trąbka";
    }
}