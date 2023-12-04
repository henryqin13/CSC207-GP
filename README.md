# CSC207GP
# CityGuesser
#### Overview: 
CityGuesser is a Java-based game designed for players to 
test their knowledge by guessing the correct city using a variety of hints. 
Leveraging the OpenAI API, this game generates random cities and provides players 
with hints of varying difficulty levels to aid their guesses. Players earn points 
based on the difficulty of the hint chosen and the number of guesses it takes to 
correctly identify the city.


#### User Stories:
Eddie signs up for an account and logs in. He clicks "Play Game"
and is given a dropdown of the level of difficulty of hint he wants.
Eddie clicks level 3 as he's quite smart.  Then the computer gives him 
the hint, "This city is where Starbucks was founded". Luckily, Eddie 
is a huge Starbucks fan, and he knows that this is Seattle. He guesses Seattle  
and is awarded with a congratulations screen and a perfect 10 points. 
Eddie is now addicted to the game and hits "Play Again" to continue playing
CityGuesser.

### Features
1. Random City Generation: 
The game utilizes the OpenAI API to randomly select cities for each 
round of gameplay.
2. Hint System: Offers players hints categorized into difficulty levels 
to assist in guessing the city.
3. Scoring Mechanism: Points are awarded to players based on the selected 
hint difficulty and the number of attempts taken.
4. User Authentication: Provides options for users to log in, 
sign up, or play as a guest.
5. Leaderboard: Displays a leaderboard ranking all players based on 
their performance in the game.