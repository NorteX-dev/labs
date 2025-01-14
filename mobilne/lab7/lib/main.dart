import 'package:flutter/material.dart';
import 'dart:async';

void main() {
  runApp(const MemoryGameApp());
}

class MemoryGameApp extends StatelessWidget {
  const MemoryGameApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Gra Memory',
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
  State<MenuScreen> createState() => _MenuScreenState();
}

class _MenuScreenState extends State<MenuScreen> {
  int _selectedGridSize = 4;
  final List<int> _gridSizeOptions = [4, 6, 8];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Memory'),
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
                  child: Text('Siatka $size x $size'),
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
              child: const Text('Rozpocznij grę'),
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
  State<GameScreen> createState() => _GameScreenState();
}

class _GameScreenState extends State<GameScreen> {
  final List<Color> colorList = [
    Colors.red,
    Colors.blue,
    Colors.green,
    Colors.yellow,
    Colors.purple,
    Colors.orange,
    Colors.pink,
    Colors.teal,
    Colors.brown,
    Colors.indigo,
    Colors.lime,
    Colors.cyan,
    Colors.amber,
    Colors.deepOrange,
    Colors.lightBlue,
    Colors.lightGreen,
    Colors.red[300]!,
    Colors.red[900]!,
    Colors.redAccent[400]!,
    Colors.pink[300]!,
    Colors.deepOrange[300]!,
    Colors.deepOrange[900]!,
    Colors.blue[300]!,
    Colors.blue[900]!,
    Colors.lightBlue[300]!,
    Colors.lightBlue[900]!,
    Colors.cyan[900]!,
    Colors.indigo[300]!,
    Colors.green[300]!,
    Colors.green[900]!,
    Colors.lightGreen[300]!,
    Colors.lightGreen[900]!,
    Colors.teal[300]!,
    Colors.teal[900]!,
    Colors.yellow[600]!,
    Colors.yellow[900]!,
    Colors.amber[300]!,
    Colors.amber[900]!,
    Colors.orange[300]!,
    Colors.orange[900]!,
    Colors.purple[300]!,
    Colors.purple[900]!,
    Colors.deepPurple[300]!,
    Colors.deepPurple[900]!,
    Colors.indigo[900]!,
    Colors.pink[900]!,
    Colors.brown[300]!,
    Colors.brown[900]!,
    Colors.blueGrey[300]!,
    Colors.blueGrey[900]!,
    Colors.grey[700]!,
    Colors.grey[900]!,
    Colors.redAccent,
    Colors.pinkAccent,
    Colors.purpleAccent,
    Colors.deepPurpleAccent,
    Colors.indigoAccent,
    Colors.blueAccent,
    Colors.lightBlueAccent,
    Colors.cyanAccent,
    Colors.tealAccent,
    Colors.greenAccent,
    Colors.lightGreenAccent,
    Colors.limeAccent,
    Colors.yellowAccent,
    Colors.amberAccent,
    Colors.orangeAccent,
    Colors.deepOrangeAccent,
  ];
  late List<Color> colors; // Wszystkie kolory kart (tj. 2x każdy kolor)
  late List<bool> isRevealed; // Czy karta jest odkryta
  late List<bool> isMatched; // Czy karta jest taka sama

  List<int> selectedCards = [];
  int moves = 0;
  DateTime? startTime;
  Duration gameTime = Duration.zero;
  Timer? _timer;
  bool _isProcessing = false;

  @override
  void initState() {
    super.initState();
    _initializeGame();
  }

  void _initializeGame() {
    int pairsNeeded = (widget.gridSize * widget.gridSize) ~/ 2;
    List<Color> gameColors = colorList.take(pairsNeeded).toList();
    colors = [...gameColors, ...gameColors]..shuffle();

    isRevealed = List.filled(widget.gridSize * widget.gridSize, false);
    isMatched = List.filled(widget.gridSize * widget.gridSize, false);
    moves = 0;
    _isProcessing = false;
    selectedCards.clear();

    startTime = DateTime.now();
    _timer?.cancel();
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
    Future.delayed(const Duration(milliseconds: 800), () {
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
      barrierDismissible: false,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Gratulacje!'),
          content: Text('Ukończyłeś grę w ${gameTime.inSeconds} sekund!\n'
              'Liczba ruchów: $moves'),
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
            crossAxisCount: widget.gridSize,
            crossAxisSpacing: 10,
            mainAxisSpacing: 10,
          ),
          itemCount: widget.gridSize * widget.gridSize,
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
