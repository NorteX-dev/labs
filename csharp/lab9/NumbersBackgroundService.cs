public class NumbersBackgroundService : BackgroundService
{
    private readonly ILogger<NumbersBackgroundService> _logger;
    public NumbersBackgroundService(ILogger<NumbersBackgroundService> logger)
    {
        _logger = logger;
    }
    protected override async Task ExecuteAsync(CancellationToken stoppingToken)
    {
        while (!stoppingToken.IsCancellationRequested)
        {
            _logger.LogInformation($"Worker running at: {DateTimeOffset.Now} - Numbers {Data.Numbers}");
            await Task.Delay(10000, stoppingToken);
            Data.Numbers++;
        }
    }
}
