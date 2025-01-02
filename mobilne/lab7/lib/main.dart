import 'package:flutter/material.dart';
import 'dart:math';
import 'dart:async';

void main() {
  runApp(const MemoryGameApp());
}

class MemoryGameApp extends StatelessWidget {
  const MemoryGameApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Memory Game',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const MenuScreen(),
    );
  }
}

class MenuScreen extends StatefulWidget {
  const MenuScreen({super.key});

  @override
  _MenuScreenState createState() => _MenuScreenState();
}

class _MenuScreenState extends State<MenuScreen> {
  int _selectedGridSize = 4;

  final List<int> _gridSizeOptions = [4, 6, 8];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Memory Game'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            DropdownButton<int>(
              value: _selectedGridSize,
              items: _gridSizeOptions.map((size) {
                return DropdownMenuItem(
                  value: size,
                  child: Text('$size x $size Grid'),
                );
              }).toList(),
              onChanged: (value) {
                setState(() {
                  _selectedGridSize = value!;
                });
              },
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) =>
                        GameScreen(gridSize: _selectedGridSize),
                  ),
                );
              },
              child: const Text('Start Game'),
            ),
          ],
        ),
      ),
    );
  }
}

class GameScreen extends StatefulWidget {
  final int gridSize;

  const GameScreen({super.key, required this.gridSize});

  @override
  _GameScreenState createState() => _GameScreenState();
}

class _GameScreenState extends State<GameScreen> {
  late int gridSize;
  late List<Color> colors;
  late List<bool> isRevealed;
  late List<bool> isMatched;
  List<int> selectedCards = [];
  int moves = 0;
  DateTime? startTime;
  Duration gameTime = Duration.zero;
  Timer? _timer;
  bool _isProcessing = false;

  Color _generateColor() {
    final Random random = Random();

    int r = random.nextInt(130) + 75;
    int g = random.nextInt(130) + 75;
    int b = random.nextInt(130) + 75;

    return Color.fromRGBO(r, g, b, 1);
  }

  @override
  void initState() {
    super.initState();
    gridSize = widget.gridSize;
    _initializeGame();
  }

  void _initializeGame() {
    int requiredUniqueColors = (gridSize * gridSize) ~/ 2;

    Set<Color> uniqueColors = {};
    while (uniqueColors.length < requiredUniqueColors) {
      uniqueColors.add(_generateColor());
    }

    colors = uniqueColors.expand((color) => [color, color]).toList()..shuffle();

    isRevealed = List.filled(gridSize * gridSize, false);
    isMatched = List.filled(gridSize * gridSize, false);
    moves = 0;
    _isProcessing = false;

    startTime = DateTime.now();
    _startTimer();
  }

  void _startTimer() {
    _timer = Timer.periodic(const Duration(seconds: 1), (timer) {
      setState(() {
        gameTime = DateTime.now().difference(startTime!);
      });
    });
  }

  void _handleCardTap(int index) {
    if (isMatched[index] || isRevealed[index] || _isProcessing) return;

    setState(() {
      isRevealed[index] = true;
      selectedCards.add(index);
    });

    if (selectedCards.length == 2) {
      _isProcessing = true;
      moves++;
      _checkForMatch();
    }
  }

  void _checkForMatch() {
    Future.delayed(const Duration(seconds: 1), () {
      if (colors[selectedCards[0]] == colors[selectedCards[1]]) {
        setState(() {
          isMatched[selectedCards[0]] = true;
          isMatched[selectedCards[1]] = true;
        });

        if (isMatched.every((matched) => matched)) {
          _timer?.cancel();
          _showWinDialog();
        }
      } else {
        setState(() {
          isRevealed[selectedCards[0]] = false;
          isRevealed[selectedCards[1]] = false;
        });
      }

      setState(() {
        selectedCards.clear();
        _isProcessing = false;
      });
    });
  }

  void _showWinDialog() {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Gratulacje!'),
          content: Text('Wygrałeś w ${gameTime.inSeconds} sekund!\n'
              'Ruchy: $moves'),
          actions: [
            TextButton(
              child: const Text('Zagraj ponownie'),
              onPressed: () {
                Navigator.of(context).pop();
                setState(() {
                  _initializeGame();
                });
              },
            ),
            TextButton(
              child: const Text('Powrót do menu'),
              onPressed: () {
                Navigator.of(context).popUntil((route) => route.isFirst);
              },
            ),
          ],
        );
      },
    );
  }

  @override
  void dispose() {
    _timer?.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Czas: ${gameTime.inSeconds} s | Ruchy: $moves'),
      ),
      body: Center(
        child: GridView.builder(
          padding: const EdgeInsets.all(16),
          gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: gridSize,
            crossAxisSpacing: 10,
            mainAxisSpacing: 10,
          ),
          itemCount: gridSize * gridSize,
          itemBuilder: (context, index) {
            return GestureDetector(
              onTap: () => _handleCardTap(index),
              child: Container(
                decoration: BoxDecoration(
                  color: isRevealed[index] || isMatched[index]
                      ? colors[index]
                      : Colors.grey[300],
                  borderRadius: BorderRadius.circular(10),
                ),
              ),
            );
          },
        ),
      ),
    );
  }
}
