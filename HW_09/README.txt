=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: _______
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Array- For my 2d array, I am implementing the feature of the actual bricks on the screen. 
  Based on the feedback from my proposal, I was told to be careful and specific about what exactly
  I am storing in the 2d array. After much thinking, I decided to store Brick objects which is
  a class I created. Each object in the 2d array has a variable relating to how many hits it takes
  to destroy that brick. This is an appropriate use of the context because the bricks naturally
  form a 2d array and it makes it efficient to iterate to check whether the user has won the game
  or not.

  2. Collections- Originally, I was planning to have a collection of brick objects that store the 
  number of hits for each brick. However, I was given feedback on the proposal that this would not
  be necessary because it can be made a class attribute. So, now I have a tree map that takes
  keys as a String player id which takes the form of "Player X". The value associated with this is
  the highest score that this player has got. IE if he is left with three lives then he has a score
  of three. If you lose the game you have a score of zero. This is an appropriate use of the tree
  map because the keys themselve do not change but the highest score for each key can change and
  thus using a treemap we are able to change the values accordingly. I also print these scores into
  a file called "scores.txt." It is easy to find the highest score for each player because it is 
  in a treemap collection.

  3. FileIO- For my FileIO, I have a file called game_state.txt. Using this txt file I implemented
  a space-separated data format that represent the states of the bricks. This is used in the pause 
  and play functionality of my game. The user has the ability to pause the game and exit, and the
  states of the bricks (how many hits left for each brick) are written to a space-separated file.
  Then, when the user restarts the game, he can leave where he left off as the file io reads the
  file and fills out the bricks in the GUI accordingly. This is an appropriate use of the concept
  because it is a unique format that allows for the functionality of pause and play and it stores
  unique data which is the state of the bricks in the game.

  4. JUnit Tests- For the JUnit tests I chose to test my Brick class which is the bread and butter
  of this game. I made tests where I test each type of brick (different colors) and how they would
  behave when they got hit by the ball. I also tested the edge cases (for example if a brick got
  hit many times even if it had no hits left). This is an appropriate use of the context because
  it checks for all cases for what can occur in the game to the bricks. It ensures that the bricks
  are functioning properly so that the user has a good experience playing the game.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
 The Brick class relates to the physical bricks that are in the GUI. They have an attribute of 
 hits left and color in order to make sure that the brick disappears when it is hit enough times
 and changes color when it is hit.
 
 The Circle class relates to the ball that moves around. It has attributes such as velocity
 and position and extends the GameObj class.
 
 The Direction class relates to things like bounce and hitObj which have important functionality
 regarding movement and animation.
 
 The Game class is the main class that is run.
 
 GameCourt initializes each of the elements such as Circle and Bricks to bring all the elements
 of the game together and refresh according to the timer interval.
 
 GameObj is a parent class that most of the game objects extend that includes things such as
 height, width, etc.
 
 InstructionCourt is a class that controls how the instructions are displayed for the user to see.
 
 Instructions is a class that actually loads the instructions into the InstructionCourt class.
 
 Paddle is a class that relates to the paddle that the users control using the arrow keys. This
 is the main way the user interacts with the program (through the keyboard).

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

I was having difficulty figuring out the pause functionality because I wanted to save the game_state
while ensuring that nothing was happening in the GUI. I realized that I had to be very careful
about setting the focus back to the window in order to get the functionality when the user clicked
play again.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

There is a good separation of functionality and there is good private state encapsulation. If I were
to refactor I would try to split the GameCourt class into multiple classes in order to have cleaner
code. Also, I would try implementing the Model View Controller format that professor had talked
about during class.

========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.

  N/A