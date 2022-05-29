import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class HangMan {

    public static void main(String[] args) throws Exception {
        ArrayList<Character> lettersGuessed = new ArrayList<Character>();

        // creating new ArrayList<String> to contain all the words
        ArrayList<String> allWords = new ArrayList<String>();
        Scanner myReader = new Scanner(new File("HangMan/lib/Words.txt"));
        // reading Words.txt file into ArrayList<String>
        while (myReader.hasNext()) {
            allWords.add(myReader.nextLine());
        }
        // finished reading so close file
        myReader.close();
        // initialyzing random variable
        Random rand = new Random();
        // variable to check if they want to play again
        int playAgain = 1;
        Scanner guessInput = new Scanner(System.in);
        Scanner anotherGame = new Scanner(System.in);
        // to keep playing hangman
        while (playAgain > 0) {
            lettersGuessed.clear();
            // getting a random number to pick a word
            int randomNum = rand.nextInt(allWords.size());
            // picke the word using the random number
            String randomWord = allWords.get(randomNum);
            // taking the random word and putting in to an array lsit and converting it into
            // underscores
            String[] strSplit = randomWord.split("");
            ArrayList<String> underScores = new ArrayList<String>(Arrays.asList(strSplit));
            // filling the new array list with underscores
            Collections.fill(underScores, "_");
            // // testing if it gets a random word
            // System.out.println(randomNum + " : " + randomWord);
            // testing if it outputs the right amount of underscores

            // scanner variable for user input
            char letter = '-';
            // variable for error message incase user did not input a letter
            String error = "Please enter a valid letter!!";
            // variable for guesses remaining. start with six
            int guessesLeft = 6;
            // variable to make sure the game keeps getting played
            boolean stillPlaying = true;
            // for loop to play one full round of hangman

            while (stillPlaying) {
                // to keep track of guesses left
                // guessesLeft -=i;

                System.out.println(hangmanDrawing(guessesLeft));
                for (String i : underScores)
                    System.out.print(i);
                System.out.println("\nYou have " + guessesLeft + " guesses left");
                // propmting for input
                System.out.print("Enter a letter: ");
                // taking in user's input
                letter = guessInput.next().charAt(0);
                // getting the ascii value to compare
                int letterAscii = letter;
                String newLetter = "";
                int counter = 0;
                if (letterAscii >= 97 && letterAscii <= 122 || letterAscii >= 65 && letterAscii <= 90) {
                    newLetter = Character.toString(letter).toUpperCase();
                    System.out.println("Your letter : " + newLetter);
                    // loop to compare if letter guessed is correct and adjust guesses left
                    // accordingly

                    for (int j = 0; j < randomWord.length(); j++) {

                        if ((int) Character.toUpperCase(letter) == (int) randomWord.charAt(j)) {
                            underScores.set(j, strSplit[j]);
                            counter++;
                        }
                    }
                    if (counter == 0) {
                        guessesLeft--;

                    }
                    lettersGuessed.add(Character.toUpperCase(letter));

                } else if (letterAscii < 97 || letterAscii > 90 || letterAscii < 65 || letterAscii > 122) {
                    System.out.println(error.toUpperCase());

                }
                System.out.print("The letters you have guessed so far: ");
                for (Character l : lettersGuessed)
                    System.out.print(l + " ");
                System.out.println("\n");

                String wholeWord = "";
                for (String eachLetter : underScores)
                    wholeWord += eachLetter;
                // this is just a test
                // System.out.println("["+randomWord+"]["+wholeWord+"]");
                if (randomWord.equals(wholeWord)) {
                    System.out.println(underScores);
                    System.out.println("\nYou won with " + guessesLeft + " guesses left. CONGRATULATIONS!!!");
                    System.out.println(
                            "Are you up for another challenge?\nEnter: a postive number to play or '0' to exit");
                    stillPlaying = false;
                    playAgain = anotherGame.nextInt();

                } else if (guessesLeft == 0) {

                    System.out.println(hangmanDrawing(guessesLeft));
                    System.out.println(
                            "\nYou lost because you have " + guessesLeft + " guesses left.\nThe word was: "
                                    + randomWord);
                    stillPlaying = false;
                    System.out.println("Do you want to play again?\nEnter: a postive number toplay or '0' to exit");

                    playAgain = anotherGame.nextInt();

                } else {
                    stillPlaying = true;
                }
            }
        }
        guessInput.close();
        anotherGame.close();
    }

    static String hangmanDrawing(int guessesLeft) {
        String hangmanStatus = "";
        switch (guessesLeft) {
            // I decided i didnt need this because it doesn't register so i manually do it
            // in the losing condition
            // //// sixth error, add the second leg, whole body game over:
            // // __________
            // // | |
            // // | |
            // // | O
            // // | /|\
            // // | / \
            // // __________________
            case 0:
                hangmanStatus = " __________\n |	 |\n |	 |\n |	 O\n |	/|\\\n |	/ \\\n __________________\n\n";
                break;
            // //// fifth error, add the first leg
            // // __________
            // // | |
            // // | |
            // // | O
            // // | /|\
            // // | /
            // // __________________
            case 1:
                hangmanStatus = " __________\n |	 |\n |	 |\n |	 O\n |	/|\\\n |	/ \n __________________\n\n";
                break;
            // fourth error, add the second arm
            // __________
            // | |
            // | |
            // | O
            // | /|\
            // |
            // __________________
            case 2:
                hangmanStatus = " __________\n |	 |\n |	 |\n |	 O\n |	/|\\\n |	\n __________________\n\n";
                break;
            // third error, add the first arm
            // __________
            // | |
            // | |
            // | O
            // | /|
            // |
            // __________________
            case 3:
                hangmanStatus = " __________\n |	 |\n |	 |\n |	 O\n |	/|\n |	\n __________________\n\n";
                break;
            // second error, head and torso
            // __________
            // | |
            // | |
            // | O
            // | |
            // |
            // __________________
            case 4:
                hangmanStatus = " __________\n |	 |\n |	 |\n |	 O\n |       |\n |	\n __________________\n\n";
                break;
            // first error just the head
            // __________
            // | |
            // | |
            // | O
            // |
            // |
            // __________________
            case 5:
                hangmanStatus = " __________\n |	 |\n |	 |\n |	 O\n |	\n |	\n __________________\n \n";
                break;
            // starting point
            // __________
            // | |
            // | |
            // |
            // |
            // |
            // __________________
            case 6:
                hangmanStatus = " __________\n |	 |\n |	 |\n |	 \n |	\n |	\n __________________\n\n";
                break;
        }
        return hangmanStatus;
    }
}

// __________
// | |
// | |
// | O
// | /|\
// | / \
// __________________
