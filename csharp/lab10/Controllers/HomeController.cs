using System.Diagnostics;
using lab10.Models;
using lab10.Services;
using Microsoft.AspNetCore.Mvc;

namespace lab10.Controllers;

public class HomeController : Controller
{
    private readonly IFilterService _filterService;
    private readonly ILogger<HomeController> _logger;

    public HomeController(ILogger<HomeController> logger, IFilterService filterService)
    {
        _logger = logger ?? throw new ArgumentNullException(nameof(logger));
        _filterService = filterService ?? throw new
            ArgumentNullException(nameof(filterService));
    }

    public IActionResult Index()
    {
        var products = _filterService.Filter();
        return View(products);
    }

    public IActionResult Privacy()
    {
        return View();
    }

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error()
    {
        return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
    }
}