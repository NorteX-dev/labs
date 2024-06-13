namespace lab10.Services;

public class FilterService : IFilterService
{
    public FilterService(IConfiguration configuration)
    {
        configuration.Bind("FilterService", this);
    }

    public bool IsAscendingByType { get; set; }

    public string FilterName { get; set; }

    public IEnumerable<Data.Product> Filter()
    {
        IEnumerable<Data.Product> result = Data.Items;
        if (!string.IsNullOrEmpty(FilterName))
            result = Data.Items.Where(x => x.Name.Contains(FilterName));
        if (IsAscendingByType)
            result = result.OrderBy(x => x.Type);
        return result;
    }
}