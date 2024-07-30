## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


## Commands To Compile and Run The File 

```bash
javac -cp ".;lib/flatlaf-2.0.jar" -d bin src/main/*.java src/game1/*.java src/game2/*.java src/game3/*.java src/game4/*.java
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
│   │   └── Game2.java
│   ├── game3/
│   │   └── HangmanGame.java
│   └── game4/
│       └── TennisGame.java
├── bin/
│   ├── main/
│   │   ├── Game.class
│   │   └── MainApp.class
│   ├── game1/
│   │   └── Game1.class
│   ├── game2/
│   │   └── Game2.class
│   ├── game3/
│   │   └── HangmanGame.class
│   └── game4/
│       └── TennisGame.class
├── lib/
│   └── flatlaf-2.0.jar
└── README.md
```