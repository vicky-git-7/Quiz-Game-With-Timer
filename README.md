# Quiz-Game-With-Timer
A simple and interactive multiple-choice quiz application built using Java Swing.  
The game loads questions from a text file, displays one question at a time, and includes a 15-second countdown timer for each question. When the timer ends or the user submits an answer, the game automatically moves to the next question. At the end, the final score is shown with an option to restart the quiz.

---

## Features

- Load questions dynamically from **questions.txt**
- Multiple-choice questions with four options
- **15-second countdown timer** using `javax.swing.Timer`
- Auto-move to next question when timer expires
- Score calculation and result popup
- Restart game option
- Simple and user-friendly Java Swing GUI
- Easy-to-understand code structure

---

## Project Structure

Quiz-Game-With-Timer-Java/
│── Main.java
│── questions.txt
│── screenshots/
│── README.md

yaml
Copy code

---

## System Requirements

### Hardware
- Processor: Intel Pentium 4 or above
- RAM: Minimum 1 GB
- Display: 1024×768 or higher

### Software
- Java JDK 17 or above
- Any IDE: IntelliJ IDEA / Eclipse / NetBeans / VS Code
- Java Swing (built-in library)

---

## How to Run

### Step 1: Clone the repository
```bash
git clone https://github.com/your-username/Quiz-Game-With-Timer-Java.git
Step 2: Navigate to the project directory
bash
Copy code
cd Quiz-Game-With-Timer-Java
Step 3: Compile the program
bash
Copy code
javac Main.java
Step 4: Run the application
bash
Copy code
java Main
Question File Format (questions.txt)
vbnet
Copy code
Question: Which planet is known as the Red Planet?
A) Earth
B) Mars
C) Jupiter
D) Venus
Answer: B

Question: What is the capital of France?
A) Berlin
B) Rome
C) Paris
D) Madrid
Answer: C
✔ Make sure one blank line exists between each question block.

Testing
Timer decreases accurately every second

Score updates correctly

GUI auto-refreshes for each question

Wrong/no selection handled correctly

Restart button resets game fully

Future Enhancements
Add difficulty levels

Add categories (GK, Science, History, etc.)

Add sound effects or animations

Store scores in a file or database

Leaderboards & ranking system

Convert to Android app version

Developer
Vignesh K
B.Tech – Information Technology
Java & Full-Stack Developer
