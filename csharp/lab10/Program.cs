using System.Reflection;
using lab10.Services;

var builder = WebApplication.CreateBuilder(args);

builder.Configuration.AddConfiguration(
    new ConfigurationBuilder()
        .SetBasePath(Path.GetDirectoryName(Assembly.GetEntryAssembly()?.Location) ?? ".")
        .AddJsonFile("settings1.json", true)
        .Build()
);

builder.Services.AddSingleton<IFilterService, FilterService>();

// Add services to the container.
builder.Services.AddControllersWithViews();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapControllerRoute(
    "default",
    "{controller=Home}/{action=Index}/{id?}");

app.Run();