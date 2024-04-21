namespace lab5;

public class Instrument
{
    public virtual void Graj()
    {
        Console.WriteLine("Instrumenty wydają dźwięki o różnej częstotliwości i barwie... ");
    }
    
    public virtual string Opis
    {
        get
        {
            return "Instrument muzyczny to przyrząd, któr służy do wydawania dźwięków. ";
        }
    }
    
    public override string ToString()
    {
        return "instrument";
    }
}
