import 'dart:async';

import 'package:flutter/material.dart';
import 'dart:math';
import 'package:audioplayers/audioplayers.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Piętnastka',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        fontFamily: 'Roboto',
      ),
      home: const SplashScreen(),
    );
  }
}

class SplashScreen extends StatefulWidget {
  const SplashScreen({super.key});

  @override
  SplashScreenState createState() => SplashScreenState();
}

class SplashScreenState extends State<SplashScreen> {
  @override
  void initState() {
    super.initState();
    Future.delayed(const Duration(seconds: 3), () {
      Navigator.pushReplacement(
        context,
        MaterialPageRoute(builder: (context) => const MenuScreen()),
      );
    });
  }

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      backgroundColor: Colors.blue,
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(
              Icons.extension,
              size: 100,
              color: Colors.white,
            ),
            SizedBox(height: 20),
            Text(
              'Piętnastka',
              style: TextStyle(
                fontSize: 32,
                color: Colors.white,
                fontWeight: FontWeight.bold,
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class MenuScreen extends StatelessWidget {
  const MenuScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Menu Główne'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => const GameScreen()),
                );
              },
              child: const Text('Nowa Gra'),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                      builder: (context) => const OptionsScreen()),
                );
              },
              child: const Text('Opcje'),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => const AboutScreen()),
                );
              },
              child: const Text('O Grze'),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => Navigator.pop(context),
              child: const Text('Wyjście'),
            ),
          ],
        ),
      ),
    );
  }
}

class GameScreen extends StatefulWidget {
  final bool testMode;

  const GameScreen({super.key, this.testMode = false});

  @override
  GameScreenState createState() => GameScreenState();
}

class GameScreenState extends State<GameScreen> {
  late List<int> tiles;
  int moves = 0;
  int seconds = 0;
  bool isPlaying = false;
  Timer? timer;
  bool isSoundEnabled = true;

  late AudioPlayer clickPlayer;
  late AudioPlayer winPlayer;

  String get formattedTime {
    int minutes = seconds ~/ 60;
    int remainingSeconds = seconds % 60;
    return '${minutes.toString().padLeft(2, '0')}:${remainingSeconds.toString().padLeft(2, '0')}';
  }

  @override
  void dispose() {
    timer?.cancel();
    clickPlayer.dispose();
    winPlayer.dispose();
    super.dispose();
  }

  @override
  void initState() {
    super.initState();
    tiles = List.generate(16, (index) => index);

    clickPlayer = AudioPlayer();
    winPlayer = AudioPlayer();

    loadSounds();
    startNewGame();
  }

  Future<void> loadSounds() async {
    await clickPlayer.setSource(AssetSource('click.mp3'));
    await winPlayer.setSource(AssetSource('win.mp3'));
  }

  Future<void> playSound(String type) async {
    await clickPlayer.release();
    try {
      switch (type) {
        case 'click':
          await clickPlayer.resume();
          break;
        case 'win':
          await winPlayer.resume();
          break;
      }
    } catch (e) {
      debugPrint('Error playing sound: $e');
    }
  }

  void startTimer() {
    timer?.cancel();
    timer = Timer.periodic(const Duration(seconds: 1), (timer) {
      if (isPlaying) {
        setState(() {
          seconds++;
        });
      }
    });
  }

  void startNewGame() {
    setState(() {
      if (widget.testMode) {
        tiles = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 15];
      } else {
        tiles = List.generate(16, (index) => index);
        shuffleTiles();
      }
      moves = 0;
      seconds = 0;
      isPlaying = true;
    });
    startTimer();
  }

  void shuffleTiles() {
    final random = Random();
    for (int i = tiles.length - 1; i > 0; i--) {
      final j = random.nextInt(i + 1);
      final temp = tiles[i];
      tiles[i] = tiles[j];
      tiles[j] = temp;
    }
  }

  bool canMoveTile(int tileIndex) {
    final emptyIndex = tiles.indexOf(0);
    final row = tileIndex ~/ 4;
    final emptyRow = emptyIndex ~/ 4;
    final col = tileIndex % 4;
    final emptyCol = emptyIndex % 4;

    return (row == emptyRow && (col - emptyCol).abs() == 1) ||
        (col == emptyCol && (row - emptyRow).abs() == 1);
  }

  void moveTile(int tileIndex) {
    if (canMoveTile(tileIndex)) {
      setState(() {
        final emptyIndex = tiles.indexOf(0);
        tiles[emptyIndex] = tiles[tileIndex];
        tiles[tileIndex] = 0;
        moves++;
      });

      playSound('click');

      if (checkWin()) {
        isPlaying = false;
        timer?.cancel();
        playSound('win');
        showWinDialog();
      }
    }
  }

  bool checkWin() {
    for (int i = 0; i < tiles.length - 1; i++) {
      if (tiles[i] != i + 1) return false;
    }
    return tiles.last == 0;
  }

  void showWinDialog() {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Gratulacje!'),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text('Wygrałeś w $moves ruchach!'),
              Text('Czas: $formattedTime'),
            ],
          ),
          actions: [
            TextButton(
              child: const Text('Nowa Gra'),
              onPressed: () {
                Navigator.of(context).pop();
                startNewGame();
              },
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Piętnastka'),
        actions: [
          IconButton(
            icon: Icon(isSoundEnabled ? Icons.volume_up : Icons.volume_off),
            onPressed: () {
              setState(() {
                isSoundEnabled = !isSoundEnabled;
              });
            },
          ),
          IconButton(
            icon: const Icon(Icons.refresh),
            onPressed: startNewGame,
          ),
        ],
      ),
      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                Text(
                  'Ruchy: $moves',
                  style: const TextStyle(fontSize: 20),
                ),
                Text(
                  'Czas: $formattedTime',
                  style: const TextStyle(fontSize: 20),
                ),
              ],
            ),
          ),
          Expanded(
            child: GridView.builder(
              padding: const EdgeInsets.all(16.0),
              gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 4,
                childAspectRatio: 1.0,
                crossAxisSpacing: 8.0,
                mainAxisSpacing: 8.0,
              ),
              itemCount: tiles.length,
              itemBuilder: (context, index) {
                return tiles[index] != 0
                    ? ElevatedButton(
                        onPressed: () => moveTile(index),
                        child: Text(
                          '${tiles[index]}',
                          style: const TextStyle(fontSize: 24),
                        ),
                      )
                    : const SizedBox.shrink();
              },
            ),
          ),
        ],
      ),
    );
  }
}

class OptionsScreen extends StatefulWidget {
  const OptionsScreen({super.key});

  @override
  OptionsScreenState createState() => OptionsScreenState();
}

class OptionsScreenState extends State<OptionsScreen> {
  bool testMode = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Opcje'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              children: [
                const Text('Tryb testowy: '),
                Switch(
                  value: testMode,
                  onChanged: (bool value) {
                    setState(() {
                      testMode = value;
                    });
                  },
                ),
              ],
            ),
            const SizedBox(height: 16),
            ElevatedButton(
              onPressed: () {
                Navigator.pushReplacement(
                  context,
                  MaterialPageRoute(
                    builder: (context) => GameScreen(testMode: testMode),
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

class AboutScreen extends StatelessWidget {
  const AboutScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('O Grze'),
      ),
      body: const Padding(
        padding: EdgeInsets.all(16.0),
        child: Text(
          'Piętnastka to klasyczna gra logiczna polegająca na układaniu numerowanych kafelków w odpowiedniej kolejności. '
          'Celem gry jest ułożenie kafelków w kolejności od 1 do 15, zostawiając puste pole w prawym dolnym rogu.',
        ),
      ),
    );
  }
}
