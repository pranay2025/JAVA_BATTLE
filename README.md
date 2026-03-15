⚔️ Java Battle – Multiplayer Quiz Game

Java Battle is an interactive multiplayer quiz game where players compete in real-time by answering questions from various topics. The game creates a competitive environment where players test their knowledge, earn points, and aim for the highest score on the leaderboard.

The project is designed to make learning engaging and fun while demonstrating concepts such as client-server communication, real-time interaction, and game logic implementation in Java.

🚀 Key Features
🎮 Multiplayer Functionality

Players can join the game and compete against multiple participants simultaneously in real time.

❓ Dynamic Questioning

The game presents a variety of questions from different categories, ensuring a diverse and engaging quiz experience.

🏆 Scoring System

Players earn points for correct answers, and scores are updated and tracked in real time.

🖥️ User Interface

A visually appealing graphical user interface (GUI) allows players to easily navigate through questions and submit their answers.

⚡ Instant Feedback

Players receive immediate feedback on their responses, helping them learn while playing.

🧩 System Components
1️⃣ Game Server

The Game Server is responsible for managing the core functionality of the game.

Responsibilities:

Manage player connections.

Handle game sessions.

Distribute questions to connected players.

Collect player answers.

Calculate scores.

Broadcast results and updates to all players.

2️⃣ Game Client

The Game Client provides the interface through which players interact with the game.

Features:

Displays quiz questions.

Allows players to submit answers.

Shows real-time scores and leaderboard updates.

Connects to the game server for communication.

3️⃣ Question Management

The Question Management module handles all quiz content.

Functions:

Retrieve quiz questions from storage.

Present questions to players.

Support multiple question types such as:

Multiple Choice Questions (MCQ)

True/False

Category-based questions

🛠️ Technologies Used

Java – Core game logic and server-client communication

Socket Programming – Real-time multiplayer connectivity

Java Swing / GUI – Interactive user interface

MySQL (optional) – Question and score storage

🎯 Learning Objectives

This project demonstrates:

Client-Server Architecture

Real-Time Multiplayer Systems

Java GUI Development

Game Logic Implementation

Networking using Java Sockets


▶️ How to Run the Project
1️⃣ Start the Server
javac GameServer.java
java GameServer
2️⃣ Start the Client
javac GameClient.java
java GameClient
3️⃣ Join the Game

Multiple players can run the client.

Connect to the server.

Start answering quiz questions.

🌟 Future Enhancements

Online matchmaking system

Timer-based question rounds

Leaderboard and ranking system

Category selection for quizzes

Web-based interface

👨‍💻 Author

Lakshmi Pranay Nuligonda

🔗 GitHub: https://github.com/pranay2025

🔗 LinkedIn: https://www.linkedin.com/in/lakshmi-pranay-nuligonda
