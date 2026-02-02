# W26-LectureExamples
Repository containing demos for the CSCI2020U course during the Winter 2026 term.

by Mariana Shimabukuro

## Module 2 - HelloWorldTestJar
IntelliJ Project.

Simple IntelliJ Java project using Maven to build a `.jar` file.
#### To test
1. Open folder as IntelliJ project
2. Go to the Main class and run the code; see prints on console.
3. Go to `pom.xml`: right-click> + add as Maven project.
4. Build the JAR: In IntelliJ, open the Maven side panel, go to Lifecycle, and double-click package (or `clean` then `package`).
5. Check the Target: Look in the target/ folder in your project view. You should see a new .jar file generated there.
6. Test the `.jar`: in the terminal at the `.jar` file location, run `java -jar <NameOfYourFile>.jar`


## Module 4 Short Examples
Collection of Java programs linked to the Module 4 videos/slides. 
#### To test
1. Compile the classes: In the terminal at this folder, run `javac *.java`
2. Run the different demos: `java <mainClass>`
   - `java FirstGUI` 
   - `java CounterController`
   - `java DynamicTreeExample`
   - `java ColorChooserDemo`
   - `java DisplayGraphics`
   - `java AnimatedRectangule`

## Module 4 - SwingDemo
Java examples creating UI components and events.
#### To test
1. Open folder as IntelliJ project
2. Go to the Main class and run the code.

## Module 5 - examples `FindAverage` and `WordCounter`
Intellij Project with demos for Module 5 on Files IO.
#### To test
1. Open folder as IntelliJ project
2. Go to the Main class and run the code.

## Module 6 - Client-Server + OpenData
Java examples creating basic Socket server and client, and using API.
#### To test
1. Open folder as IntelliJ project
2. Run the Server and Client Applications:
   - Run the Server first: Go to the `SocketServer` class and run the code.
   - Go to Run → Edit Configurations…
   - Click the + (top-left) → choose Application.
   - Give it a name (ie Client), select the `SocketClient` Class and run.
4. To run the YouTube example:
  - First create an API key (see the documented code) and add your key into the code
  - Run the program
6. To run the `BikeShare` class:
  - Go to the BikeShare class and run it

## Module 7 - examples `HorseRaceDemo`, `TestRunnableDemo`, `TestThreadDemo`, and `SwingWorkerDemo`
Intellij Project with demos for Module 7 using threads.
#### To test
1. Open folder as IntelliJ project
2. Go to the desired main class and run the code.

## Week 2 Activity - FirstGUI
You will find the Java files created in class which:
- Create a simple window application
- Create and display button, text field, and label
- Programs button click events
- Display error dialog message

## Week 3 Activity - Chat App using MVC
You will find the Java files modified in class which completes all the TODO items.
- ChatView.java
- ChatController.java
- ChatModel.java

## Week 4 Activity - Loading Resources from Resource Directory
You will find the Java files and TODO items.
- This is an IntelliJ project; once you clone this repository, open the `Week4-Activity` folder as an IntelliJ project.
- ResourceView.java


