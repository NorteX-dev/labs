namespace lab10;

public static class Data
{
    public static List<Product> Items = new()
    {
        // donut - pączek
        new Product { Type = "donut", Name = "Cake", Batter = "Regular", Topping = "None" },
        new Product { Type = "donut", Name = "Cake", Batter = "Chocolate", Topping = "None" },
        new Product { Type = "donut", Name = "Cake", Batter = "Devil's Food", Topping = "None" },
        new Product { Type = "donut", Name = "Cake", Batter = "Blueberry", Topping = "None" },
        new Product { Type = "donut", Name = "Old Fashioned", Batter = "Regular", Topping = "Sugar" },
        new Product { Type = "donut", Name = "Old Fashioned", Batter = "Chocolate", Topping = "Glazed" },
        new Product { Type = "donut", Name = "Old Fashioned", Batter = "Blueberry", Topping = "Sugar" },
        new Product
        {
            Type = "donut", Name = "Old Fashioned", Batter = "Devil's Food", Topping = "Glazed"
        },
        new Product { Type = "donut", Name = "Filled", Batter = "Regular", Topping = "Jam" },
        new Product { Type = "donut", Name = "Filled", Batter = "Devil's Food", Topping = "Cream" },
        new Product { Type = "donut", Name = "Filled", Batter = "Chocolate", Topping = "Cream" },
        new Product { Type = "donut", Name = "Filled", Batter = "Blueberry", Topping = "Jam" },
        new Product { Type = "donut", Name = "Raised", Batter = "Chocolate", Topping = "None" },
        new Product { Type = "donut", Name = "Raised", Batter = "Devil's Food", Topping = "None" },
        new Product { Type = "donut", Name = "Raised", Batter = "Regular", Topping = "None" },
        // bar - batonik
        new Product { Type = "bar", Name = "Bar", Batter = "Regular", Topping = "None" },
        new Product { Type = "bar", Name = "Old Fashioned", Batter = "Blueberry", Topping = "Cream" },
        // twist - warkocz
        new Product { Type = "twist", Name = "Twist", Batter = "Regular", Topping = "None" },
        // filled - nadziewaniec
        new Product { Type = "filled", Name = "Filled", Batter = "Regular", Topping = "None" }
    };

    public class Product
    {
        public string Type { get; set; }
        public string Name { get; set; }
        public string Batter { get; set; }
        public string Topping { get; set; }
    }
}