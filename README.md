# the-city-game
#### Domain: 
interactive logic game design/ city database  analysis
#### Software Specification:

#### Uer Stories:
(Core): Eddie chooses to be a guesser for this round and picks the category of information he wants his clue to be based on- he picks "Geography". Then the computer gives him the clue "This city located near the border of Asia, Europe and Africa". Eddie thinks he needs one more clue, and he picks the "Pictures" category this time. Then a picture of the city is shown and now Eddie is ready to guess. He enters "Istanbul" on his keyboard and it turns out he is correct.  

#### Entities
I. Player
- Attributes:
  1. Name: String
  2. Score: int
  3. Roles: Arraylist[Role]

II. Role (Interface)
1. Guesser
2. Chooser

III. PlayerAnswer
- Attributes:
  1. Answer: String

IV. Match
- Attributes:
  1. CorrectAnswer: City
  2. UserAnswers: List[PlayerAnswer]

V. City
- Attributes
  1. Name: String
  2. Facts: HashMap[String, Fact]

VI. Fact (Interface)
1. WordFact
    1. Category: String
   2. Value: String
2. NumFact
    1. Category: String
   2. Value: Integer


## for the group
We need to finish four things before the next lab:
- [X] A git repository shared with everyone and uploaded on quercus. 
- [X] An UI for the core use case.
- [ ] An UML diagram for the core use case. 
- [ ] A sequence diagram for the core use case.
- [ ] We need to actually divide the work among the group now and actually start coding.

Notice I deliberately leave out some features to focus on the core and most basic things an user should be able to do, and postpone features like choosing a difficulty level or multiplayer or letting the computer guess. This user interaction will be the basis of our codes and we start from there. 

