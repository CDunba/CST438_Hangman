package com.company;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Hangman {
    private Random num;
    private char state='Y';//Y to play
    private StringBuffer displayWord;
    Scanner reader = new Scanner(System.in);
    public char guess='a';
    String word="computer";//default word
    public int t=0;//counts tries

    //constructor
    public Hangman() {
        createDisplayWord();
    }

    public void setState(){
        System.out.print("Would you like to play Hangman? Y or N only input allowed.");
        state = reader.next().charAt(0);
    }

    public int getState(){
        return state;
    }

    private boolean updateDisplayWord(char guess) {
        boolean right = false;
        for (int i = 1; i <= word.length(); i++) {
            if (word.charAt(i) == guess) {
                displayWord.replace(2 * i, 2 * i + 1, Character.toString(guess));
                right = true;
            }
        }
        return right;
    }

    private void createDisplayWord() {
        displayWord = new StringBuffer("_");
        System.out.println(" ");
        for (int i = 1; i < word.length(); i++) {

            displayWord.append(" _");
        }
    }

    public String getDisplayWord(){
        System.out.println(displayWord.toString());
        return displayWord.toString();
    }

    public boolean test(char guess){
        for (int i=0; i<word.length(); i++) {
            if (word.charAt(i)==guess) {
                displayWord.replace(2 * i, 2 * i + 1, Character.toString(guess));
                return true;
            }
        }
        return false;
    }

    public void playGame() {
        getDisplayWord();

        System.out.print(" Guess a letter ");
        guess = reader.next().charAt(0);

        boolean right = test(guess);

        if (right == false) {
            if (t == 6) {
                System.out.print("You've lost.");
                setState();
            }
            t=t++;
            System.out.println("Not in word");

            playGame();

        }
        else if (displayWord.indexOf(" _ ") >= 0) {
            System.out.println(right);
            state = 'Y';
            playGame();
        }
        else {
            System.out.print("You won!");
            System.exit(0);
        }
    }

    private void startNewGame() {
        setState();
        System.out.print(state);
        if (state=='Y'){
            playGame();
        }
    }
    public static void main(String[] args) {

        Hangman h = new Hangman();
        h.startNewGame();
    }
}

