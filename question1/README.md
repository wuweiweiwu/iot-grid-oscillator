#problem #1 grid oscillator

##Coding process

I decided to code this problem in Java because I have done past user interfaces in Java and I am most familiar with it.

I created only 1 class representing the JPanel that I will paint with the grids and then I added it to a JFrame to display it on the screen.
Since we are oscillating each grids at a base frequency of 60Hz I decided to use Threads instead of Timers to implement the switching.
At first, I was stuck at oscillating each grid at a different freqency then I did some math and figured out a way.
I created an 2D array of size 1938 of integers "blockCounter" and "blockDelay" to store the count and delay for each block respectively.

Here is my math:

base freq = 60Hz => period = 1/60 seconds

different freq = 60Hz/x (where x is an integer from 2-1939) => period = x/60 seconds

So I stored the integers from 2-1939 in blockDelay and initialized blockCounter to all 0.
In each loop iteration blockCounter values would increment by 1 and if that value for a block is greater than the value stored in blockDelay then it would be updated (flipped) in the JPanel.
Effectively giving me the desired frequencies for each individual block.

The next hardest thing was getting the base frequency at exactly 60Hz (or really close).
I looked up the Thread.sleep method and decided to use that as the way to delay block updates.
However, each call to repaint()  can take different times since the number of blocks to update changes all the time.
So, I have to account for that in the sleep method. I found really useful code for game loops and recoded to suit my purposes in this problem. 

http://www.java-gaming.org/index.php?topic=24220.0

##Execution and testing

the source code is located in this eclipse project directory. the jar file is also located in this directory. it can be executed in command line by the command java -jar question1executable.jar

the frequency that the blocks are updating is located in the title bar of the program (fps)
