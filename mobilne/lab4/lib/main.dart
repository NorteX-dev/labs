import 'package:flutter/material.dart';
import 'package:audioplayers/audioplayers.dart';
import 'dart:math';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Wisielec',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const HangmanGame(),
    );
  }
}

class HangmanGame extends StatefulWidget {
  const HangmanGame({super.key});

  @override
  State<HangmanGame> createState() => _HangmanGameState();
}

class _HangmanGameState extends State<HangmanGame> {
  final List<String> words = [
    "PROGRAMOWANIE",
    "KOMPUTER",
    "TELEFON",
    "SAMOCHÓD",
    "KSIĄŻKA",
    "TELEWIZOR",
    "RODZINA",
    "PRZYJACIEL",
    "SZKOŁA",
    "UNIWERYTET",
    "KLAWIATURA",
    "MYSZ",
    "MONITOR",
    "DRUKARKA",
    "INTERNET",
    "APLIKACJA",
    "SŁUCHAWKI",
    "ŁADOWARKA",
    "BATERIA",
    "PAMIĘĆ",
    "PROCESOR",
    "KAMERA",
    "MIKROFON",
    "GŁOŚNIK",
    "EKRAN",
    "WIADOMOŚĆ",
    "ZDJĘCIE",
    "DOKUMENT",
    "PRZEGLĄDARKA",
    "SYSTEM"
  ];

  late String wordToGuess;
  String displayWord = "";
  Set<String> guessedLetters = {};
  int remainingTries = 9;
  bool gameOver = false;
  final TextEditingController letterController = TextEditingController();
  String message = "";

  final AudioPlayer _audioPlayer = AudioPlayer();
  bool _audioInitialized = false;

  String getRandomWord() {
    final random = Random();
    return words[random.nextInt(words.length)];
  }

  @override
  void initState() {
    super.initState();
    initializeGame();
    initializeAudio();
  }

  void initializeGame() {
    wordToGuess = getRandomWord();
    displayWord = "_" * wordToGuess.length;
  }

  void initializeAudio() async {
    // await _audioPlayer.play(AssetSource('start.mp3'));
    _audioInitialized = true;
  }

  void checkLetter(String letter) {
    if (letter.isEmpty || gameOver) return;

    letter = letter.toUpperCase();
    if (guessedLetters.contains(letter)) {
      setState(() {
        message = "Ta litera była już użyta!";
      });
      return;
    }

    setState(() {
      guessedLetters.add(letter);

      if (wordToGuess.contains(letter)) {
        String newDisplay = "";
        for (int i = 0; i < wordToGuess.length; i++) {
          if (wordToGuess[i] == letter) {
            newDisplay += letter;
          } else {
            newDisplay += displayWord[i];
          }
        }
        displayWord = newDisplay;
        message = "Litera '$letter' jest w słowie.";
      } else {
        remainingTries--;
        message = "Niestety, litera '$letter' nie występuje w słowie.";
        if (!_audioInitialized) return;
        _audioPlayer.play(AssetSource('wrong_letter.mp3'));
      }

      if (displayWord == wordToGuess) {
        message = "Gratulacje! Wygrałeś! Słowo to: $wordToGuess";
        gameOver = true;
        if (!_audioInitialized) return;
        _audioPlayer.play(AssetSource('win.mp3'));
      } else if (remainingTries <= 0) {
        message = "Przegrałeś! Słowo to było: $wordToGuess";
        gameOver = true;
        if (!_audioInitialized) return;
        _audioPlayer.play(AssetSource('loss.mp3'));
      }
    });

    letterController.clear();
  }

  void restartGame() {
    setState(() {
      initializeGame();
      guessedLetters.clear();
      remainingTries = 9;
      gameOver = false;
      message = "";
    });
    if (!_audioInitialized) return;
    _audioPlayer.play(AssetSource('start.mp3'));
  }

  String getImage() {
    int imageIndex = 10 - remainingTries;
    if (gameOver && displayWord != wordToGuess) {
      imageIndex = 10;
    }
    return 'image$imageIndex.png';
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Gra w Wisielca'),
        centerTitle: true,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Image.asset(
              getImage(),
              height: 200,
              fit: BoxFit.contain,
            ),
            const SizedBox(height: 20),
            Text(
              'Pozostałe próby: $remainingTries',
              style: const TextStyle(fontSize: 20),
            ),
            const SizedBox(height: 20),
            Text(
              displayWord,
              style: const TextStyle(fontSize: 40, letterSpacing: 8),
            ),
            const SizedBox(height: 20),
            if (!gameOver)
              Row(
                children: [
                  Expanded(
                    child: TextField(
                      controller: letterController,
                      maxLength: 1,
                      textCapitalization: TextCapitalization.characters,
                      decoration: const InputDecoration(
                        labelText: 'Wpisz literę',
                        border: OutlineInputBorder(),
                      ),
                      onSubmitted: (value) => checkLetter(value),
                    ),
                  ),
                  const SizedBox(width: 10),
                  ElevatedButton(
                    onPressed: () => checkLetter(letterController.text),
                    child: const Text('Sprawdź'),
                  ),
                ],
              ),
            const SizedBox(height: 20),
            Text(
              message,
              style: const TextStyle(fontSize: 18),
              textAlign: TextAlign.center,
            ),
            if (gameOver)
              ElevatedButton(
                onPressed: restartGame,
                child: const Text('Zagraj ponownie'),
              ),
          ],
        ),
      ),
    );
  }
}
