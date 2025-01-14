import 'package:flutter/material.dart';
import 'dart:math' as math;
import 'package:shared_preferences/shared_preferences.dart';
import 'package:audioplayers/audioplayers.dart';

void main() {
  runApp(const GameApp());
}

class GameApp extends StatelessWidget {
  const GameApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primarySwatch: Colors.blue,
        useMaterial3: true,
      ),
      home: const MenuScreen(),
    );
  }
}

// Klasa każdego obiektu fizycznego (abstract, bo jest tylko używana do dziedziczenia klas Ball i Square)
abstract class GameObject {
  double x, y;
  double vx, vy;
  Color color;
  bool isActive;

  GameObject({
    required this.x,
    required this.y,
    this.vx = 0,
    this.vy = 0,
    required this.color,
    this.isActive = true,
  });

  void update(double gravity, double airResistance, Size size) {
    if (!isActive) return;

    // Grawitacja
    vy += gravity;

    // Opór
    vx *= (1 - airResistance);
    vy *= (1 - airResistance);

    x += vx;
    y += vy;

    if (y > size.height - 20) {
      y = size.height - 20;
      vy = -vy * 0.8; // Wytracenie energii
    }

    if (x < 0 || x > size.width) {
      vx = -vx * 0.8;
      x = x < 0 ? 0 : size.width;
    }
  }

  void draw(Canvas canvas);
}

class Ball extends GameObject {
  final double radius;

  Ball({
    required super.x,
    required super.y,
    required this.radius,
    required super.color,
  });

  @override
  void draw(Canvas canvas) {
    final paint = Paint()..color = color;
    canvas.drawCircle(Offset(x, y), radius, paint);
  }
}

class Square extends GameObject {
  final double size;

  Square({
    required super.x,
    required super.y,
    required this.size,
    required super.color,
  });

  @override
  void draw(Canvas canvas) {
    final paint = Paint()..color = color;
    canvas.drawRect(
      Rect.fromCenter(center: Offset(x, y), width: size, height: size),
      paint,
    );
  }
}

// Widok gry
class GameView extends StatefulWidget {
  final GameSettings settings;

  const GameView({super.key, required this.settings});

  @override
  GameViewState createState() => GameViewState();
}

class GameViewState extends State<GameView>
    with SingleTickerProviderStateMixin {
  late List<GameObject> objects;
  late AnimationController _controller;
  final AudioPlayer _audioPlayer = AudioPlayer();
  bool isPaused = false;

  @override
  void initState() {
    super.initState();
    _initGame();
    _controller = AnimationController(
      vsync: this,
      duration: const Duration(seconds: 1),
    )..repeat();
    _controller.addListener(_updateGame);
  }

  void _initGame() {
    objects = [];
    final random = math.Random();

    for (int i = 0; i < widget.settings.objectCount; i++) {
      if (random.nextBool()) {
        // double width = MediaQuery.of(context).size.width;
        objects.add(Ball(
          x: random.nextDouble() * 400,
          y: random.nextDouble() * 400,
          radius: 20,
          color: Colors.blue,
        ));
      } else {
        objects.add(Square(
          x: random.nextDouble() * 300,
          y: random.nextDouble() * 300,
          size: 40,
          color: Colors.red,
        ));
      }
    }
  }

  void _updateGame() {
    if (isPaused) return;

    setState(() {
      for (var object in objects) {
        object.update(
          widget.settings.gravity,
          widget.settings.airResistance,
          MediaQuery.of(context).size,
        );
      }
    });
  }

  bool _isPointInObject(Offset point, GameObject object) {
    if (object is Ball) {
      return (point - Offset(object.x, object.y)).distance <= object.radius;
    } else if (object is Square) {
      return point.dx >= object.x - object.size / 2 &&
          point.dx <= object.x + object.size / 2 &&
          point.dy >= object.y - object.size / 2 &&
          point.dy <= object.y + object.size / 2;
    }
    return false;
  }

  // Dotykanie elementów
  void _handleTap(TapDownDetails details) {
    final tapPosition = details.localPosition;
    bool objectHit = false;

    for (var object in objects) {
      if (_isPointInObject(tapPosition, object)) {
        object.vy = -40; // Ta ilość vy określa wysokość skoku po dotknięciu
        objectHit = true;
      }
    }

    if (!objectHit) {
      setState(() {
        isPaused = !isPaused;
        if (isPaused) {
          _showPauseMenu();
        }
      });
    }
  }

  void _showPauseMenu() {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Pauza'),
        actions: [
          TextButton(
            onPressed: () {
              Navigator.pushReplacement(
                context,
                MaterialPageRoute(builder: (context) => const MenuScreen()),
              );
            },
            child: const Text('Menu'),
          ),
          TextButton(
            onPressed: () {
              setState(() {
                isPaused = false;
                Navigator.pop(context);
              });
            },
            child: const Text('Wznów'),
          ),
        ],
      ),
    );
  }

  @override
  void dispose() {
    _controller.dispose();
    _audioPlayer.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: GestureDetector(
        onTapDown: _handleTap,
        child: CustomPaint(
          painter: GamePainter(objects),
          child: Container(),
        ),
      ),
    );
  }
}

class GamePainter extends CustomPainter {
  final List<GameObject> objects;

  GamePainter(this.objects);

  @override
  void paint(Canvas canvas, Size size) {
    for (var object in objects) {
      object.draw(canvas);
    }
  }

  @override
  bool shouldRepaint(GamePainter oldDelegate) => true;
}

// Ustawienia
class GameSettings {
  final double gravity;
  final double airResistance;
  final int objectCount;

  GameSettings({
    this.gravity = 0.5,
    this.airResistance = 0.01,
    this.objectCount = 5,
  });
}

class SettingsManager {
  static const String _gravityKey = 'gravity';
  static const String _airResistanceKey = 'airResistance';
  static const String _objectCountKey = 'objectCount';

  static Future<GameSettings> loadSettings() async {
    final prefs = await SharedPreferences.getInstance();
    return GameSettings(
      gravity: prefs.getDouble(_gravityKey) ?? 0.5,
      airResistance: prefs.getDouble(_airResistanceKey) ?? 0.01,
      objectCount: prefs.getInt(_objectCountKey) ?? 5,
    );
  }

  static Future<void> saveSettings(GameSettings settings) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setDouble(_gravityKey, settings.gravity);
    await prefs.setDouble(_airResistanceKey, settings.airResistance);
    await prefs.setInt(_objectCountKey, settings.objectCount);
  }
}

// Menu
class MenuScreen extends StatelessWidget {
  const MenuScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () async {
                final settings = await SettingsManager.loadSettings();
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => GameView(settings: settings),
                  ),
                );
              },
              child: const Text('Rozpocznij'),
            ),
            ElevatedButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                      builder: (context) => const SettingsScreen()),
                );
              },
              child: const Text('Ustawienia'),
            ),
            ElevatedButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => const AboutScreen()),
                );
              },
              child: const Text('O aplikacji'),
            ),
          ],
        ),
      ),
    );
  }
}

class SettingsScreen extends StatefulWidget {
  const SettingsScreen({super.key});

  @override
  SettingsScreenState createState() => SettingsScreenState();
}

class SettingsScreenState extends State<SettingsScreen> {
  double gravity = 0.5;
  double airResistance = 0.01;
  int objectCount = 5;

  @override
  void initState() {
    super.initState();
    _loadSettings();
  }

  Future<void> _loadSettings() async {
    final settings = await SettingsManager.loadSettings();
    setState(() {
      gravity = settings.gravity;
      airResistance = settings.airResistance;
      objectCount = settings.objectCount;
    });
  }

  Future<void> _saveSettings() async {
    await SettingsManager.saveSettings(GameSettings(
      gravity: gravity,
      airResistance: airResistance,
      objectCount: objectCount,
    ));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Ustawienia'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back),
          onPressed: () async {
            await _saveSettings();
            Navigator.pop(context);
          },
        ),
      ),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          Text('Grawitacja: ${gravity.toStringAsFixed(2)}'),
          Slider(
            value: gravity,
            min: 0,
            max: 2,
            onChanged: (value) => setState(() => gravity = value),
          ),
          Text('Opór powietrza: ${airResistance.toStringAsFixed(2)}'),
          Slider(
            value: airResistance,
            min: 0,
            max: 0.1,
            onChanged: (value) => setState(() => airResistance = value),
          ),
          Text('Ilość obiektów: $objectCount'),
          Slider(
            value: objectCount.toDouble(),
            min: 1,
            max: 20,
            divisions: 19,
            onChanged: (value) => setState(() => objectCount = value.round()),
          ),
        ],
      ),
    );
  }
}

class AboutScreen extends StatelessWidget {
  const AboutScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('O aplikacji')),
      body: const Center(
        child: Text('Wersja 1.0'),
      ),
    );
  }
}
