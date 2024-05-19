using BibliotekaA;
using BibliotekaB;
using Interfaces;
using Microsoft.Extensions.DependencyInjection;

var services = new ServiceCollection()
 .AddSingleton<IDataInterface, KlientB>()
 .BuildServiceProvider();
var klient = services.GetRequiredService<IDataInterface>();
Console.WriteLine(klient.GetJson());