using Interfaces;

namespace BibliotekaA;

public class KlientA : IDataInterface
{
    public string GetJson()
    {
        return "To są dane klienta A";
    }
}
