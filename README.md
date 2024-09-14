# GameGalaxy

Welcome to **GameGalaxy**, a Java mini-game application featuring a collection of fun and interactive mini-games. This project showcases several classic games with a modern touch and organized structure.

## Getting Started

To get started with **GameGalaxy**, follow these steps to set up your development environment and run the application.

### Prerequisites

- **Java Development Kit (JDK)**: Make sure you have JDK 8 or higher installed.
- **Visual Studio Code**: Set up with the Java Extension Pack.

### Folder Structure

The workspace contains the following folders:

- `src`: Contains the source code files.
- `lib`: Contains external libraries or dependencies.
- `bin`: Contains compiled `.class` files.

> You can customize the folder structure by updating the settings in `.vscode/settings.json`.

### Dependency Management

Manage your project dependencies through the `JAVA PROJECTS` view in Visual Studio Code. More information can be found in the [Visual Studio Code Java Dependency Documentation](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

### Commands to Compile and Run the Application

To compile and run the application, use the following commands:

```bash
avac -cp ".;lib/flatlaf-2.0.jar" -d bin src/main/*.java src/game1/*.java src/game2/*.java src/game3/*.java src/game4/*.java src/game5/*.java
```

```bash
java -cp "bin;lib/flatlaf-2.0.jar" main.MainApp
```
 

## Separation of Source and Compiled Files


- **Organization**:
Keeping source code in the src directory and compiled class files in the bin directory makes the project structure more organized and easier to manage. Developers can quickly find source files and know exactly where to look for compiled files.

- **Build Process**:
During the build process, it's common to compile all the .java files into .class files. By directing the output of the compilation process to the bin directory, the build process doesn't overwrite or mix with the source files.

- **Deployment**:
When deploying a Java application, only the .class files are needed. Having them in a separate bin directory simplifies packaging and deployment.

- **Version Control**:
Source files are usually version-controlled, but compiled files are not. Keeping them in separate directories ensures that only the necessary files are tracked by version control systems like Git.

## Project structure

```
JavaMiniGames/
├── src/
│   ├── main/
│   │   ├── Game.java
│   │   └── MainApp.java
│   ├── game1/
│   │   └── Game1.java
│   ├── game2/
│   |   ├── BrickBreakerGame.java
│   |   ├── Game2.java
│   |   ├── GamePlay.java
│   │   └── MapGenerator.java
│   ├── game3/
│   │   └── HangmanGame.java
│   ├── game4/
│   |    └── TennisGame.java
│   └── game5/
│       └── SudokuGame.java
├── bin/
│   ├── main/
│   │   ├── Game.class
│   │   └── MainApp.class
│   ├── game1/
│   │   └── Game1.class
│   ├── game2/
│   │   └── BrickBreakerGame.class
│   ├── game3/
│   │   └── HangmanGame.class
│   └── game4/
│       └── TennisGame.class
│   └── game5/
│       └── SudokuGame.class
├── lib/
│   └── flatlaf-2.0.jar
└── README.md
```

## Features

- Tic-Tac-Toe
- Brick Breaker
- Hangman
- Tennis
- Sudoku
- Modern UI with FlatLaf look and feel

## How to Play

- **Tic-Tac-Toe**: The classic game where you try to get three in a row before your opponent.
- **Brick Breaker**: Control the paddle to break all the bricks while keeping the ball in play.
- **Hangman**: Guess the word before the man gets "hanged."
- **Tennis**: Use the paddle to keep the ball in the air as long as possible.
- **Sudoku**: Fill the grid so that every row, column, and 3x3 box contains the digits 1-9 without repetition.


## **Future Enhancements**
  Ideas for future improvements or planned features.

```markdown
## Future Enhancements

- Add multiplayer functionality for Tic-Tac-Toe.
- Implement a leaderboard system for high scores.
- Add new mini-games (e.g., connect4, Checkers).
```
## Contributing

Feel free to contribute to GameGalaxy by opening issues or submitting pull requests. Your contributions are greatly appreciated!
