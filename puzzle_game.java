import java.io.*;

/*
    The following is the JavaScript code for a 'Puzzle World' game in which the user must pass different levels to win.
    The following code was originally created and compiled on Code.org, now transferred to VSCode/Git.
    @author Michael Puglise 
 */

public class puzzle_game {
    //Start of Game
    onEvent("startButton", "click", function(event) {
        setScreen("levelOne");
    });

    //Level 1: Spot the Difference
    onEvent("spotDifferenceDrop", "change", function(event) {
    if (getText("spotDifferenceDrop")=="Leg") {
        setScreen("GameOver");
        setText("spotDifferenceDrop", "Select the Difference");
    } else if (getText("spotDifferenceDrop")=="Eyes") {
        setScreen("GameOver");
        setText("spotDifferenceDrop", "Select the Difference");
    } else if (getText("spotDifferenceDrop")=="Nose") {
        setScreen("GameOver");
        setText("spotDifferenceDrop", "Select the Difference");
    } else if (getText("spotDifferenceDrop")=="Ear") {
        setScreen("GameOver");
        setText("spotDifferenceDrop", "Select the Difference");
    } else if (getText("spotDifferenceDrop")=="Nose and Ear") {
        setScreen("levelTwo");
        setText("spotDifferenceDrop", "Select the Difference");
    } 
    });

    //Level 2: Guess the secret number!
    numberGuesser();
    function numberGuesser() {
    var lives = 5;
    var secretNumber = randomNumber(1, 25);
    setText("lifecount", lives);
    onEvent("guessingBox", "change", function() {
    var userGuess = getNumber("guessingBox");
    if (secretNumber==userGuess) {
        setScreen("transition");
        setText("text_area4", "\nYou guessed it!\n The number was " + secretNumber);
        reset();
    } else if (userGuess<1||userGuess>25){
        setText("text_area1", "Try Guessing Between 1 and 25!");
        lives--;
        setText("lifecount", lives);
        checkLives();
    } else if (secretNumber<userGuess) {
        setText("text_area1", "Try Guessing Lower!");
        lives--;
        setText("lifecount", lives);
        checkLives();
    } else if (secretNumber>userGuess) {
        setText("text_area1", "Try Guessing Higher!");
        lives--;
        setText("lifecount", lives);
        checkLives();
        }  
    });
    }

    function checkLives() {
    if (lives==0) {
        setScreen("GameOver");
        reset(); }
    }
    function reset() {
        setText("guessingBox", "");
        setText("text_area1", "Try and guess the mystery number to reach the next level! The number is between 1 and 25!");
        secretNumber = randomNumber(1, 25);
        lives = 5;
        setText("lifecount", lives);
    } 

    onEvent("nextLevel", "click", function() {
        setScreen("levelThree");
    });

    //Level 3: Sudoku!
    sudoku();
    function sudoku() {
    var letterA = 2;
    var letterB = 2;
    var letterC = 3;
    var letterD = 4;
    var letterE = 1;
    onEvent("submit", "click", function(event) {
        var letterAguess = getNumber("text_input1");
        var letterBguess = getNumber("text_input2");
        var letterCguess = getNumber("text_input3");
        var letterDguess = getNumber("text_input4");
        var letterEguess = getNumber("text_input5");
        if (letterAguess==letterA && letterBguess==letterB && letterCguess==letterC 
        && letterDguess==letterD && letterEguess==letterE) {
        setScreen("levelFour");
        reset();
        } else {
            setScreen("GameOver");
            reset(); }
    });
    function reset() {
        setText("text_input1", "");
        setText("text_input2", "");
        setText("text_input3", "");
        setText("text_input4", "");
        setText("text_input5", "");
    } 
    }

    //Level 4: Solve the Riddle!
    riddles();
    function riddles() {
    var lives = 3;
    var easyAnswer1 = "hole";
    var easyAnswer2 = "ditch";
    var mediumAnswer="darkness";
    var hardAnswer = "echo";
    onEvent("easy", "click", function(event) {
        easyRiddle();
    });
    onEvent("medium", "click", function(event) {
        mediumRiddle();
    });
    onEvent("hard", "click", function(event) {
        hardRiddle();
    });
    function easyRiddle() {
        setProperty("levelFour", "background-color", rgb(0,255,0,0.4));
        setText("text_area6", "Easy Riddle:\n\nThe more you take away from me, the bigger I get.");

        onEvent("submitAnswer", "click", function() {
        checkEasyAnswer();
        });
    }

    function mediumRiddle() {
        setProperty("levelFour", "background-color", "orange");
        setText("text_area6", "Medium Riddle:\n\nThe more of me there is, the less you see. What am I?");

        onEvent("submitAnswer", "click", function() {
        checkMediumAnswer();
    }); 
    }

    function hardRiddle() {
        setProperty("levelFour", "background-color", "red");
        setText("text_area6", "Hard Riddle:\n\nI speak without a mouth and hear without ears?");
        onEvent("submitAnswer", "click", function() {
        checkHardAnswer();
    }); 
    }

    function checkEasyAnswer() {
        var userGuess = getText("text_input6");
        userGuess = userGuess.toLowerCase();
        if (userGuess==easyAnswer1||userGuess==easyAnswer2) {
        setScreen("levelFive");
        lives = 3;
        reset();
        } else {
        lives--;
        setText("label17", lives);
        if (lives==0) {
            setScreen("GameOver");
            lives = 3;
            reset();
        } 
    }
    }
    function checkMediumAnswer() {
        var userGuess = getText("text_input6");
        userGuess = userGuess.toLowerCase();
        if (userGuess==mediumAnswer) {
            setScreen("levelFive");
            lives = 3;
            reset();
        } else {
            lives--;
            setText("label17", lives);
        if (lives==0) {
            setScreen("GameOver");
            lives = 3;
            reset();
        } 
    }
    }
    function checkHardAnswer() {
        var userGuess = getText("text_input6");
        userGuess = userGuess.toLowerCase();
        if (userGuess==hardAnswer) {
            setScreen("levelFive");
            lives = 3;
            reset();
        } else {
            lives--;
            setText("label17", lives);
        if (lives==0) {
            setScreen("GameOver");
            lives = 3;
            reset();
        } 
    }
    }
    function reset() {
        setText("label17", lives);
        setText("text_input6", "");
    }
    }

    //Level Five: The Dice Game!
    var score = 0;
    setText("label22", score);
    diceGame();
    function diceGame() {
        onEvent("dropdown1", "change", function() {
        roll(1);
    });
    function roll(points) {
        onEvent("rollbutton", "click", function() {
        var userGuess = getNumber("dropdown1");
        var dieValue = randomNumber(1, 6);
        setText("label20", dieValue);
        if (userGuess == dieValue) {
        score = score + points;
        if (score>=2) {
            setScreen("winscreen");
            setText("label20", " ");
            score = 0;
        }
        } else if (userGuess!=dieValue) {
        score = score - points;
        setText("label22", score);
        if (score<0) {
            score = 0; }
        }
        setText("label22", score);
        });
    } }

    //Game Over
    onEvent("playAgain", "click", function(event) {
        setScreen("HomeScreen");
    }); 
}   