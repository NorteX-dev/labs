using System.Diagnostics;
using Interfaces;
using Microsoft.AspNetCore.Mvc;
using MVCApp.Models;

namespace MVCApp.Controllers;

public class HomeController : Controller
{
    private readonly ILogger<HomeController> _logger;
    private readonly IDataInterface _dataInterface;

    public HomeController(ILogger<HomeController> logger, IDataInterface dataInterface)
    {
        logger = logger ?? throw new ArgumentNullException(nameof(logger));
        _dataInterface = dataInterface ?? throw new ArgumentNullException(nameof(dataInterface));
    }

    public IActionResult Index()
    {
        ViewData["JsonData"] = _dataInterface.GetJson();
        return View();
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
