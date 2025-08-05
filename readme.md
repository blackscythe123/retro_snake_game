# 🐍 Java Snake Game

A simple and classic **Snake** game built using **Java Swing** with clean OOP structure and responsive controls.

---

## 🎮 Controls

| Key            | Action              |
|----------------|---------------------|
| **W / ↑**      | Move Up             |
| **S / ↓**      | Move Down           |
| **A / ←**      | Move Left           |
| **D / →**      | Move Right          |
| **Enter**      | Start / Play Again  |
| **Space**      | Pause / Resume      |
| **Start Game** | On Home Screen      |
| **Pause**      | Button in Game (Toggles to "Resume") |

---

## ▶️ How to Run

### Option 1: Run via Command Line

```bash
cd src
javac SnakeGame.java GamePanel.java Snake.java Apple.java
java SnakeGame
```

### Option 2: Run via IDE

1. Open the `src/` folder in your favorite IDE (IntelliJ, VS Code, Eclipse, etc.)
2. Run `SnakeGame.java` as the main class.

---

## 🧠 Concepts Used

- 🎨 Java Swing GUI (JFrame, JPanel, Graphics)
- 🧱 Object-Oriented Programming:
  - Classes
  - Abstraction
  - Encapsulation
- 🎮 Keyboard Input: `KeyAdapter`
- 🕹️ Button Click Events: `ActionListener`
- 🔄 Game State Management:
  - Home
  - Playing
  - Paused
  - Game Over

---

## 🖼️ Screenshots

**Main Menu:**

![Main Menu](image.png)

**In-Game:**

![In Game](image-1.png)

**Game Over Screen:**

![Game Over](image-2.png)

---

## 📁 Folder Structure

```
snake-game/
├── src/
│   ├── SnakeGame.java    // Main class with window setup
│   ├── GamePanel.java    // Game logic, drawing, and controls
│   ├── Snake.java        // Snake properties and movement
│   └── Apple.java        // Apple generation and collision
├── README.md             // You're reading it!
```

---

> Made with ❤️ in Java for fun and learning.
