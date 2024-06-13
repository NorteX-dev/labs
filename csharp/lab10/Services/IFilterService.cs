namespace lab10.Services;

public interface IFilterService
{
    IEnumerable<Data.Product> Filter();
}