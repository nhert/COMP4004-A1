# COMP4004-A1

Nathan Hertner, 100894822
GitHub Account Link: https://github.com/nhert

Requirements + Their Tests:

Requirement: Card data for Poker
Tests: All methods in the "TestCard" class test the functionality of a card

Requirement: Hand class holds 5 Cards (represents a player)
Tests: All methods in the "TestHand" class correspond to the functionality of a hand

Requirement: Game class holds the logic for the main loop of the game. Sets up the players and the deck.
Tests: All methods in the "TestGame" class correspond to the functionality of the game controller.

Requirement: Deck class holds the logic for assembling a deck of 52 cards, each unique, as well as giving out a random card to a player.
Tests: All methods in the "TestDeck" class correspond to the functionality of the deck class.

Requirement: Poker class holds the logic for determining what hand a player has
Tests: All methods named after the poker hands in "TestPoker" will test the functionality of determining hands. These come in the format: "test(HandName)" such as "testHasPairs"

Requirement: Poker class resolves the final ranking of players based on hands through a recursive method.
Tests: The "testGameResolution" method in "TestPoker" will output rankings to console between 2 players and one game of 4 players. It tests where there are ties between all the hand types, before checking the kicker.


How to Run code:

-How to run a normal game loop:
-Start the "Game" class, it takes no parameters.
-The console will prompt you for the number of players in the current round. Type a number and press "enter"
-The game will spit out the results for the current round. Each players hand, what hand they had, and the cards in their hand.
-The game will then spit out a rankings board for the players, and continue out to the next round.

-How to run all the tests:
-Start the test suite from executing the "TestSuite" class
-it will run all the tests for all the classes, as well as spit out some resolutions for tie hands in the console.


