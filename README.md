<p align="center">
  <img src="https://github.com/user-attachments/assets/ff978f19-2fe2-4cb6-88f4-17f9d444f624"
       alt="Togyzqumalaq Banner"
       style="width: 1000px; height: 400px; border-radius: 12px; object-fit: cover;" />
</p>

# Togyzqumalaq

## Description
Togyzqumalaq is a desktop implementation of the traditional Kazakh mancala game, built in Java with a clean, modular architecture and a JavaFX user interface. The game supports two players on a single device and leverages design patterns (Strategy, MVC, Singleton, etc.) for flexible, extensible game logic instead of a classic AI engine.

## Features
- **Traditional Gameplay**  
  Faithful implementation of Togyzqumalaq rules, including Tuzdyk and capture mechanics.  
- **Local Multiplayer**  
  Two-player mode on the same machine—no network required.  
- **Modular Architecture**  
  Clear separation of concerns with MVC; game logic isolated into interchangeable components.  
- **Design Patterns**  
  Utilizes Strategy (move algorithms), Command (player actions), Facade (board API), Observer (state updates), Singleton (core manager), Factory (object creation).  
- **JavaFX Interface**  
  Responsive, interactive board view with hole highlighting, score panels, turn indicators, confirmation dialogs, and win overlays.  
- **Easy Restart & Replay**  
  One-click reset and replay without restarting the application.  
- **Cross-Platform**  
  Runs on any OS with Java 11+ and JavaFX 11+.

## Technologies
- **Java 11+**  
- **JavaFX 11+**  
- **Maven** for build and dependency management

## Installation & Run
1. **Clone the repository**  
   ```bash
   git clone https://github.com/yourusername/togyzqumalaq.git
   cd togyzqumalaq


2. **Run with Maven**

   ```bash
   mvn clean javafx:run
   ```



## Design Patterns

- **MVC (Architectural)**  
  - **Model:** `ToguzBoard`, `BoardState`, `Holes`, `Kazans`, `Tuzdyks`, `CurrentPlayer`  
  - **View:** `MainMenuView`, `MainView`, UI components (`HolePane`, `ScoreBoard`, `PlayerPanel`, `TurnIndicator`, `ConfirmModal`, `WinOverlay`)  
  - **Controller:** `GameController`, `IGameController`  

- **Command (Behavioral)**  
  - `ICommand`, `MoveCommand`, `ResetCommand`  

- **Facade (Structural)**  
  - `ToguzBoard`  

- **Observer (Behavioral)**  
  - `IStateObserver`, `GameController` (subscriber)  

- **Strategy (Behavioral)**  
  - `IMoveStrategy`, `StandardMoveStrategy`, `MoveStrategyFactory`  

- **Factory (Creational)**  
  - `PlayerFactory`, `MoveStrategyFactory`
    

## Project Structure

```

src/
└─ main/
└─ java/
└─ org/
└─ example/
├─ application/
│   └─ App.java
├─ controller/
│   ├─ GameController.java
│   ├─ IGameController.java
│   └─ BotMoveScheduler.java
├─ domain/
│   ├─ board/
│   │   ├─ BoardState.java
│   │   ├─ Holes.java
│   │   ├─ Kazans.java
│   │   ├─ Tuzdyks.java
│   │   └─ CurrentPlayer.java
│   ├─ command/
│   │   ├─ ICommand.java
│   │   ├─ MoveCommand.java
│   │   └─ ResetCommand.java
│   ├─ facade/
│   │   └─ ToguzBoard.java
│   ├─ game/
│   │   ├─ IGameState.java
│   │   ├─ GameStateImpl.java
│   │   └─ GameResultChecker.java
│   ├─ observer/
│   │   └─ IStateObserver.java
│   ├─ player/
│   │   ├─ IPlayer.java
│   │   ├─ BotPlayer.java
│   │   ├─ HumanPlayer.java
│   │   └─ PlayerFactory.java
│   ├─ rules/
│   │   ├─ IRules.java
│   │   └─ StandardRules.java
│   └─ strategy/
│       ├─ IMoveStrategy.java
│       ├─ StandardMoveStrategy.java
│       └─ MoveStrategyFactory.java
└─ view/
   ├─  components/
   │   ├─ ConfirmModal.java
   │   ├─ HolePane.java
   │   ├─ PlayerPanel.java
   │   ├─ ScoreBoard.java
   │   ├─ TurnIndicator.java
   │   ├─ WinOverlay.java
    └─ screens/
      ├─ MainMenuView\.java
      └─ MainView\.java


```


## How to Play

1. Launch the game and choose player modes (Human vs. Human).
2. Each player takes turns clicking one of their nine holes to distribute seeds counterclockwise.
3. If the last seed lands in an opponent’s hole containing exactly three seeds (Tuzdyk), those seeds move to your kazan.
4. Captured seeds accumulate in each player’s kazan; the first to reach 81 wins.
5. Use the reset button at any time to start a new game.

## Game Components & Rules

### Qumalaq (Seeds)
- The basic playing pieces—small stones called qumalaq.
- **Total:** 162 qumalaq (9 in each of the 18 holes at start).

### Holes (Pits)
- The board has **2 rows × 9 holes**.
- Each player “owns” the 9 holes on their side.
- On your turn you pick up all qumalaq from one of your holes and sow them one by one into subsequent holes counter-clockwise (skipping your own kazan).

### Qazans (Stores)
- Each player has a **kazan** (store) at the right end of their row.
- Whenever you capture qumalaq (see Tuzdyq rule below), they go into your kazan.
- You never sow into your opponent’s kazan.

### Tuzdyq (Special Capture Hole)
- A hole becomes a **tuzdyq** (literally “salt pit”) when **all** these conditions are met during your turn:
  1. Your last sown qumalaq lands in one of **opponent’s holes**.  
  2. That hole then contains **exactly 3** qumalaq.  
  3. You do **not** already have a tuzdyq.  
  4. The hole is **not** the 9th hole (a common rule to prevent end-game locks).  
- When formed, the 3 qumalaq in that hole immediately move to your kazan.  
- That hole remains marked as your tuzdyq for the rest of the game; any qumalaq landing there in future turns also go straight to your kazan.

### Turn Sequence & Winning
1. **Choose a hole** on your side that has at least one qumalaq.  
2. **Pick up** all qumalaq and sow them one per hole counter-clockwise (skipping kazans).  
3. **If** your last qumalaq creates a tuzdyq, **capture** them.  
4. **If** it lands in one of your own existing tuzdyq holes, **capture** it immediately.  
5. **End turn.** Opponent goes next.  
6. **Game ends** when one player cannot move (all their holes are empty). Remaining qumalaq on board go to the other player’s kazan.  
7. **Victory** goes to whoever has ** ≥ 82** qumalaq in their kazan (half of 162 plus one).

---

This modular rule set (holes, qumalaq, qazans, tuzdyq) creates a rich strategic depth while remaining true to the traditional Togyzqumalaq game.
##  License
This project is licensed under the **MIT License**.  
See [LICENSE](LICENSE) for details.
##  Acknowledgements
- Thanks to Narxoz University for supporting this project.  
- Inspired by the traditional rules of Togyzqumalaq as documented in Kazakh cultural archives.  
- JavaFX community and the OpenJFX project for the UI framework.  
- Open-source design pattern literature and guides that helped shape the architecture.

*This project was developed by two team members as part of the Design Project discipline for the Digital Engineering II course at Narxoz University.*






