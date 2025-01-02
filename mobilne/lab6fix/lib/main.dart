import 'package:flutter/material.dart';
import 'dart:async';
import 'dart:math';

void main() {
  runApp(const ArkanoidApp());
}

class ArkanoidApp extends StatelessWidget {
  const ArkanoidApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: MainApp(),
    );
  }
}

class MainApp extends StatefulWidget {
  const MainApp({super.key});

  @override
  _ArkanoidGameState createState() => _ArkanoidGameState();
}

class _ArkanoidGameState extends State<MainApp> {
  // Zmienne stałe
  static const double gameWidth = 400;
  static const double gameHeight = 600;
  static const double paddleWidth = 100;
  static const double paddleHeight = 20;
  static const double ballRadius = 10;
  static const double blockWidth = 50;
  static const double blockHeight = 20;

  late Ball _ball;
  late Paddle _paddle;
  late List<Block> _blocks;

  int _score = 0;
  int _lives = 3;
  bool _isGameRunning = false;
  Timer? _gameTimer;

  @override
  void initState() {
    super.initState();
    _resetGame();
  }

  @override
  void dispose() {
    _gameTimer?.cancel();
    super.dispose();
  }

  void _resetGame() {
    setState(() {
      _paddle = Paddle(
          position: const Offset(
              gameWidth / 2 - paddleWidth / 2, gameHeight - paddleHeight),
          width: paddleWidth,
          height: paddleHeight);

      _ball = Ball(
          position: const Offset(
              gameWidth / 2, gameHeight - paddleHeight - ballRadius),
          radius: ballRadius,
          velocity: const Offset(200, -200));

      _blocks = List.generate(50, (index) {
        int row = index ~/ 10;
        int col = index % 10;
        return Block(
            rect: Rect.fromLTWH(
                col * blockWidth, row * blockHeight, blockWidth, blockHeight));
      });

      _score = 0;
      _lives = 3;
      _isGameRunning = false;
    });
  }

  void _startGame() {
    if (!_isGameRunning) {
      _isGameRunning = true;
      _gameTimer = Timer.periodic(
          const Duration(milliseconds: 16), (_) => _updateGame());
    }
  }

  void _updateGame() {
    setState(() {
      _ball.move();
      _checkCollisions();
    });
  }

  void _checkCollisions() {
    if (_ball.position.dx <= ballRadius ||
        _ball.position.dx >= gameWidth - ballRadius) {
      _ball.bounceHorizontal();
    }

    if (_ball.position.dy <= ballRadius) {
      _ball.bounceVertical();
    }

    if (_ball.wouldIntersectPaddle(_paddle)) {
      _ball.bounceOffPaddle(_paddle);
    }

    if (_ball.position.dy >= gameHeight - ballRadius) {
      _lives--;
      if (_lives <= 0) {
        _gameTimer?.cancel();
        _showGameOverDialog();
      } else {
        _ball.reset();
      }
    }

    _blocks.removeWhere((block) {
      if (block.rect.contains(_ball.position)) {
        _ball.bounceVertical();
        _score++;
        return true;
      }
      return false;
    });

    if (_blocks.isEmpty) {
      _gameTimer?.cancel();
      _showWinDialog();
    }
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

  void _showWinDialog() {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Congratulations!'),
        content: Text('You won! Score: $_score'),
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

  void _movePaddle(DragUpdateDetails details) {
    if (_isGameRunning) {
      setState(() {
        double newX = _paddle.position.dx + details.delta.dx;
        newX = newX.clamp(0, gameWidth - paddleWidth);
        _paddle.position = Offset(newX, _paddle.position.dy);
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Arkanoid'),
      ),
      body: GestureDetector(
        onTapDown: (_) => _startGame(),
        onHorizontalDragUpdate: _movePaddle,
        child: Container(
          width: gameWidth,
          height: gameHeight,
          color: Colors.black,
          child: Stack(
            children: [
              Positioned(
                left: _paddle.position.dx,
                bottom: paddleHeight,
                child: Container(
                  width: _paddle.width,
                  height: _paddle.height,
                  color: Colors.white,
                ),
              ),
              Positioned(
                left: _ball.position.dx - ballRadius,
                top: _ball.position.dy - ballRadius,
                child: Container(
                  width: ballRadius * 2,
                  height: ballRadius * 2,
                  decoration: const BoxDecoration(
                    shape: BoxShape.circle,
                    color: Colors.white,
                  ),
                ),
              ),
              for (var block in _blocks)
                Positioned(
                  left: block.rect.left,
                  top: block.rect.top,
                  child: Container(
                    width: blockWidth,
                    height: blockHeight,
                    color: Colors.blue,
                  ),
                ),
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

class Ball {
  Offset position;
  Offset velocity;
  final double radius;
  final Offset initialPosition;
  final Offset initialVelocity;

  Ball({required this.position, required this.radius, required this.velocity})
      : initialPosition = position,
        initialVelocity = velocity;

  void move() {
    position += velocity * (1.0 / 60);
  }

  void bounceHorizontal() {
    velocity = Offset(-velocity.dx, velocity.dy);
  }

  void bounceVertical() {
    velocity = Offset(velocity.dx, -velocity.dy);
  }

  void bounceOffPaddle(Paddle paddle) {
    double paddleCenter = paddle.position.dx + paddle.width / 2;
    double ballCenter = position.dx;
    double difference = ballCenter - paddleCenter;

    double angleMultiplier = difference / (paddle.width / 2);
    velocity = Offset(velocity.dx + angleMultiplier * 100, -abs(velocity.dy));
  }

  bool wouldIntersectPaddle(Paddle paddle) {
    return position.dy >= paddle.position.dy - radius &&
        position.dy <= paddle.position.dy + paddle.height + radius &&
        position.dx >= paddle.position.dx &&
        position.dx <= paddle.position.dx + paddle.width;
  }

  void reset() {
    position = initialPosition;
    velocity = initialVelocity;
  }

  double abs(double value) => value.abs();
}

class Paddle {
  Offset position;
  final double width;
  final double height;

  Paddle({required this.position, required this.width, required this.height});
}

class Block {
  Rect rect;

  Block({required this.rect});
}
