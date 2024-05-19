using Macierze;

namespace TestApp
{
    static class MacierzDlaKonsoli
    {
        public static void WypiszMacierz(this Macierz a)
        {
            Console.Write("[ ");
            for (int i = 1; i <= a.LiczbaWierszy; i++)
            {
                Console.Write("<");
                for (int j = 1; j <= a.LiczbaKolumn; j++)
                {
                    Console.Write(a[i, j]);
                    if (j != a.LiczbaKolumn)
                        Console.Write("; ");
                }
                Console.Write("> ");
            }
            Console.Write(")");
        }
        
        private static Random generator = new Random();
        
        public static void InicjalizujMacierz(this Macierz a)
        {
            for (int i = 1; i <= a.LiczbaWierszy; i++)
            {
                for (int j = 1; j <= a.LiczbaKolumn; j++)
                {
                    a[i, j] = generator.Next(0, 11);
                }
            }
        }
    }
}