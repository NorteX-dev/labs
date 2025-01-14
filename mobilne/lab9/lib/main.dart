import 'package:flutter/material.dart';
import 'package:sensors_plus/sensors_plus.dart';
import 'package:fl_chart/fl_chart.dart';
import 'dart:async';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primarySwatch: Colors.blue,
        useMaterial3: true,
      ),
      home: const SensorDashboard(),
    );
  }
}

class SensorDashboard extends StatefulWidget {
  const SensorDashboard({super.key});

  @override
  State<SensorDashboard> createState() => _SensorDashboardState();
}

class _SensorDashboardState extends State<SensorDashboard> {
  final Map<String, bool> _selectedSensors = {
    'Akcelerometr': false,
    'Żyroskop': false,
    'Magnetometr': false,
  };

  final Map<String, List<FlSpot>> _sensorData = {
    'Akcelerometr': [],
    'Żyroskop': [],
    'Magnetometr': [],
  };

  final Map<String, double> _thresholds = {
    'Akcelerometr': 15.0,
    'Żyroskop': 10.0,
    'Magnetometr': 50.0,
  };

  int _measurementInterval = 100; // ms
  int _graphUpdateInterval = 500; // ms
  List<StreamSubscription<dynamic>> _sensorSubscriptions = [];
  Timer? _graphUpdateTimer;

  @override
  void initState() {
    super.initState();
    _setupSensorListeners();
    _setupGraphTimer();
  }

  void _setupSensorListeners() {
    _sensorSubscriptions = [
      accelerometerEvents.listen((event) {
        if (_selectedSensors['Akcelerometr']!) {
          _processData(
              'Akcelerometr', event.x.abs() + event.y.abs() + event.z.abs());
        }
      }),
      gyroscopeEvents.listen((event) {
        if (_selectedSensors['Żyroskop']!) {
          _processData(
              'Żyroskop', event.x.abs() + event.y.abs() + event.z.abs());
        }
      }),
      magnetometerEvents.listen((event) {
        if (_selectedSensors['Magnetometr']!) {
          _processData(
              'Magnetometr', event.x.abs() + event.y.abs() + event.z.abs());
        }
      }),
    ];
  }

  void _processData(String sensor, double value) {
    setState(() {
      final time = DateTime.now().millisecondsSinceEpoch / 1000;
      _sensorData[sensor]!.add(FlSpot(time, value));

      // Keep last 50 measurements
      if (_sensorData[sensor]!.length > 50) {
        _sensorData[sensor]!.removeAt(0);
      }
    });
  }

  void _setupGraphTimer() {
    _graphUpdateTimer?.cancel();
    _graphUpdateTimer = Timer.periodic(
      Duration(milliseconds: _graphUpdateInterval),
      (timer) => setState(() {}),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Panel Sensorów'),
        actions: [
          IconButton(
            icon: const Icon(Icons.settings),
            onPressed: _showSettings,
          )
        ],
      ),
      body: ListView(
        children: [
          for (var sensor in _selectedSensors.entries)
            if (sensor.value)
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: Card(
                  child: Padding(
                    padding: const EdgeInsets.all(16.0),
                    child: Column(
                      children: [
                        Text(sensor.key,
                            style: Theme.of(context).textTheme.titleLarge),
                        const SizedBox(height: 8),
                        SizedBox(
                          height: 200,
                          child: LineChart(
                            LineChartData(
                              lineBarsData: [
                                LineChartBarData(
                                  spots: _sensorData[sensor.key]!,
                                  isCurved: true,
                                  color: Colors.blue,
                                ),
                              ],
                              gridData: const FlGridData(show: true),
                              titlesData: const FlTitlesData(show: false),
                              borderData: FlBorderData(show: true),
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ),
        ],
      ),
    );
  }

  void _showSettings() {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Ustawienia'),
        content: SingleChildScrollView(
          child: StatefulBuilder(
            builder: (context, setState) => Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                for (var sensor in _selectedSensors.entries)
                  CheckboxListTile(
                    title: Text(sensor.key),
                    value: sensor.value,
                    onChanged: (value) {
                      setState(() => _selectedSensors[sensor.key] = value!);
                    },
                  ),
                const Divider(),
                ListTile(
                  title: const Text('Odstęp pomiarów (ms)'),
                  subtitle: Slider(
                    value: _measurementInterval.toDouble(),
                    min: 100,
                    max: 1000,
                    divisions: 9,
                    label: _measurementInterval.toString(),
                    onChanged: (value) {
                      setState(() => _measurementInterval = value.toInt());
                    },
                  ),
                ),
                ListTile(
                  title: const Text('Odstęp aktualizacji wykresu (ms)'),
                  subtitle: Slider(
                    value: _graphUpdateInterval.toDouble(),
                    min: 100,
                    max: 1000,
                    divisions: 9,
                    label: _graphUpdateInterval.toString(),
                    onChanged: (value) {
                      setState(() {
                        _graphUpdateInterval = value.toInt();
                        _setupGraphTimer();
                      });
                    },
                  ),
                ),
                for (var threshold in _thresholds.entries)
                  ListTile(
                    title: Text('Próg: ${threshold.key}'),
                    subtitle: Slider(
                      value: threshold.value,
                      min: 0,
                      max: 100,
                      divisions: 100,
                      label: threshold.value.toString(),
                      onChanged: (value) {
                        setState(() => _thresholds[threshold.key] = value);
                      },
                    ),
                  ),
              ],
            ),
          ),
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: const Text('Zamknij'),
          ),
        ],
      ),
    );
  }

  @override
  void dispose() {
    for (var subscription in _sensorSubscriptions) {
      subscription.cancel();
    }
    _graphUpdateTimer?.cancel();
    super.dispose();
  }
}
