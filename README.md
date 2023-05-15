Sure, here's an updated README for the project:

# CSCI-2720 Project 2

This is a Java project developed by Michael Le and Bryson Bennett for CSCI-2720 (Data Structures) at the University of Georgia.

## Overview

The goal of this project is to implement a program that converts infix expressions to postfix expressions, evaluates postfix expressions, and provides a command-line interface for the user to interact with these features.

The project consists of the following classes:
- `Driver`: Provides the command-line interface and user input handling.
- `PostfixConversion`: Converts infix expressions to postfix expressions.
- `LinkedListStack`: Implements a stack using a linked list data structure.
- `PostfixEvaluation`: Evaluates postfix expressions.

## Usage

To run the program, first compile the source code by running the following commands in the project root directory:

```sh
javac -d bin src/LinkedListStack.java
javac -d bin -cp bin src/PostfixConversion.java
javac -d bin -cp bin src/PostfixEvaluation.java
javac -d bin -cp bin src/Driver.java
```

Then, execute the program by running:

```sh
java -cp bin src/Driver.java
```

The program will prompt the user to enter an infix expression. It will then convert the expression to postfix notation, evaluate it, and display the result.

## Contributors

- Michael Le (ml10674@uga.edu): Worked on implementing `Driver` class and `PostfixEvaluation` class.
- Bryson Bennett (blb64218@uga.edu): Worked on implementing `LinkedListStack` class and `PostfixConversion` class.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
