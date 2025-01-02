// ignore_for_file: constant_identifier_names

import 'dart:async';
import 'package:flutter/material.dart';
// import 'package:audioplayers/audioplayers.dart';

class ArkanoidGame extends StatefulWidget {
  const ArkanoidGame({super.key});

  @override
  _ArkanoidGameState createState() => _ArkanoidGameState();
}

class _ArkanoidGameState extends State<ArkanoidGame> {
  // Game dimensions
  static const double GAME_WIDTH = 400;
  static const double GAME_HEIGHT = 600;
  static const double PADDLE_WIDTH = 100;
  static const double PADDLE_HEIGHT = 20;
  static const double BALL_RADIUS = 10;
  static const double BLOCK_WIDTH = 50;
  static const double BLOCK_HEIGHT = 20;

  // Game objects
  Offset _ballPosition =
      const Offset(GAME_WIDTH / 2, GAME_HEIGHT - PADDLE_HEIGHT - BALL_RADIUS);
  Offset _paddlePosition = const Offset(
      GAME_WIDTH / 2 - PADDLE_WIDTH / 2, GAME_HEIGHT - PADDLE_HEIGHT);
  List<Rect> _blocks = [];
  int _score = 0;
  int _lives = 3;

  // Game state
  bool _isGameRunning = false;
  Offset _ballVelocity = const Offset(200, 200);

  // Audio
  // final _audioPlayer = AudioPlayer();

  @override
  void initState() {
    super.initState();
    _setupBlocks();
  }

  void _setupBlocks() {
    // Create blocks
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 5; j++) {
        _blocks.add(
          Rect.fromLTWH(
            i * BLOCK_WIDTH,
            j * BLOCK_HEIGHT,
            BLOCK_WIDTH,
            BLOCK_HEIGHT,
          ),
        );
      }
    }
  }

  void _startGame() {
    _isGameRunning = true;
    _updateGame();
  }

  void _updateGame() {
    if (!_isGameRunning) return;

    // Update ball position
    double dx = _ballVelocity.dx * (1.0 / 60);
    double dy = _ballVelocity.dy * (1.0 / 60);
    _ballPosition = Offset(_ballPosition.dx + dx, _ballPosition.dy + dy);

    // Check for collisions
    _checkBallCollisions();
    _checkBlockCollisions();

    // Check for game over
    if (_ballPosition.dy > GAME_HEIGHT - PADDLE_HEIGHT - BALL_RADIUS) {
      _lives--;
      if (_lives == 0) {
        _isGameRunning = false;
        _showGameOverDialog();
      } else {
        _resetBall();
      }
    }

    setState(() {});
    if (_isGameRunning) {
      Future.delayed(const Duration(milliseconds: 16), _updateGame);
    }
  }

  void _checkBallCollisions() {
    // Check for collisions with walls
    if (_ballPosition.dx < BALL_RADIUS ||
        _ballPosition.dx > GAME_WIDTH - BALL_RADIUS) {
      _ballVelocity = Offset(-_ballVelocity.dx, _ballVelocity.dy);
      _playSound('wall');
    }
    if (_ballPosition.dy < BALL_RADIUS) {
      _ballVelocity = Offset(_ballVelocity.dx, -_ballVelocity.dy);
      _playSound('wall');
    }

    // Check for collision with paddle
    if (_ballPosition.dy > GAME_HEIGHT - PADDLE_HEIGHT - BALL_RADIUS &&
        _ballPosition.dx >= _paddlePosition.dx &&
        _ballPosition.dx <= _paddlePosition.dx + PADDLE_WIDTH) {
      double difference =
          (_paddlePosition.dx + PADDLE_WIDTH / 2) - _ballPosition.dx;
      double direction = (difference / (PADDLE_WIDTH / 2)) * 3;
      _ballVelocity = Offset(_ballVelocity.dx + direction, -_ballVelocity.dy);
      _playSound('paddle');
    }
  }

  void _checkBlockCollisions() {
    for (int i = 0; i < _blocks.length; i++) {
      Rect block = _blocks[i];
      if (block.contains(_ballPosition)) {
        _blocks.removeAt(i);
        _score++;
        _ballVelocity = Offset(_ballVelocity.dx, -_ballVelocity.dy);
        _playSound('block');
        break;
      }
    }
  }

  void _resetBall() {
    _ballPosition =
        const Offset(GAME_WIDTH / 2, GAME_HEIGHT - PADDLE_HEIGHT - BALL_RADIUS);
    _ballVelocity = const Offset(200, 200);
  }

  void _showGameOverDialog() {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Game Over'),
        content: Text('Your score: $_score'),
        actions: [
          TextButton(
            onPressed: () {
              Navigator.of(context).pop();
              _resetGame();
            },
            child: const Text('Play Again'),
          ),
        ],
      ),
    );
  }

  void _resetGame() {
    setState(() {
      _ballPosition = const Offset(
          GAME_WIDTH / 2, GAME_HEIGHT - PADDLE_HEIGHT - BALL_RADIUS);
      _paddlePosition = const Offset(
          GAME_WIDTH / 2 - PADDLE_WIDTH / 2, GAME_HEIGHT - PADDLE_HEIGHT);
      _blocks = [];
      _score = 0;
      _lives = 3;
      _isGameRunning = false;
      _setupBlocks();
    });
  }

  void _playSound(String name) {
    switch (name) {
      case 'wall':
        // _audioPlayer.play(AssetSource('sounds/wall.mp3'));
        break;
      case 'paddle':
        // _audioPlayer.play(AssetSource('sounds/paddle.mp3'));
        break;
      case 'block':
        // _audioPlayer.play(AssetSource('sounds/block.mp3'));
        break;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Arkanoid'),
      ),
      body: GestureDetector(
        onTap: _startGame,
        child: Container(
          width: GAME_WIDTH,
          height: GAME_HEIGHT,
          color: Colors.black,
          child: Stack(
            children: [
              // Paddle
              Positioned(
                left: _paddlePosition.dx,
                bottom: _paddlePosition.dy,
                child: Container(
                  width: PADDLE_WIDTH,
                  height: PADDLE_HEIGHT,
                  color: Colors.white,
                ),
              ),
              // Ball
              Positioned(
                left: _ballPosition.dx - BALL_RADIUS,
                top: _ballPosition.dy - BALL_RADIUS,
                child: Container(
                  width: BALL_RADIUS * 2,
                  height: BALL_RADIUS * 2,
                  decoration: const BoxDecoration(
                    shape: BoxShape.circle,
                    color: Colors.white,
                  ),
                ),
              ),
              // Blocks
              for (Rect block in _blocks)
                Positioned(
                  left: block.left,
                  top: block.top,
                  child: Container(
                    width: BLOCK_WIDTH,
                    height: BLOCK_HEIGHT,
                    color: Colors.blue,
                  ),
                ),
              // Score and Lives
              Positioned(
                top: 10,
                left: 10,
                child: Text(
                  'Score: $_score, Lives: $_lives',
                  style: const TextStyle(
                    color: Colors.white,
                    fontSize: 16,
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
