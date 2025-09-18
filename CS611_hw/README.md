# CS611-Assignment < # >
## < ASSIGNMENT NAME >
---------------------------------------------------------------------------
- Name: Shuxun Zhou
- Email: shuxun@bu.edu
- Student ID: U30247554

## Files
---------------------------------------------------------------------------

This section should be all of the source code files that have a .java extension. You should also include a brief description of what the class does.

Main.java: This class includes all of the game and its operation logic.

## Notes
---------------------------------------------------------------------------
Please explain the cool features of your program. Anything that you feel like you did a good job at or were creative about, explain it in bullets here. Additionally, any design decisions should be made here.

1.When generating a new board, it will avoid the two situations: no solution and completed game.
2.Using the reverse order and the position of the blank to determine whether the game is solvable.

## How to compile and run
---------------------------------------------------------------------------
Your directions on how to run the code. Make sure to be as thorough as possible!

1.Go to my github repository named CS611.
2.Filepath /CS611/CS611_hw/src/Main.java to start and run the game.
3.Related UML graphs are also in /CS611/CS611_hw.

## Input/Output Example
---------------------------------------------------------------------------
Please give us a full execution of what we should see on the screen. Label each text with input and output. For example:

```
Output: 
[+] === Welcome to Sliding Puzzle Game ===
[+] Enter number of rows : 

Input:
[+] 2

Output:
[+] Enter number of cols: 

Input:
[+] 2

Output:
+--+--+
|  |2|
+--+--+
|1|3|
+--+--+
Please choose to continue or quit (c=continue, q=quit)

Input:
1

Output:
+--+--+
|1|2|
+--+--+
|  |3|
+--+--+
Please choose to continue or quit (c=continue, q=quit)

Input:
3

Output:
+--+--+
|1|2|
+--+--+
|3| |
+--+--+
Congratulations !!!
Goodbye!

   ```


