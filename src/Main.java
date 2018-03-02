import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main (String[]args)throws Exception {
        Main instanceForSorter= new Main();
        //initialize
        Game newGame =new Game();
        System.out.println("Choose a movie List(ar for Arabic/en for English)");
        Scanner Ar_En=new Scanner(System.in);
        if(Ar_En.nextLine().equals("ar")){
            newGame.setEnMovie(false);
        }else if(Ar_En.nextLine().equals("en")){
            newGame.setEnMovie(true);
        }else{
            System.out.println("Wrong Input, I'm going for English instead");
        }
        newGame.Game_init();
        //declare moviePicker and remaining Guesses
        String moviePicked=newGame.getMovie();
        int remainingGuesses=newGame.getLeft();
        String lettersGuessed="";
        //String Builders for Encap and the pickedMovie Proccessed
        StringBuilder reveal=new StringBuilder(newGame.getEncap());
        StringBuilder pickerProc=new StringBuilder(moviePicked);



        while (!newGame.gameOver&&remainingGuesses>=0) {
            remainingGuesses = newGame.getLeft();
            System.out.println("___________________________________");
            System.out.println("You have guessed(" + (9 - remainingGuesses) + ") wrong letters.");
            System.out.println("Guess a Letter:");
            Scanner scanner = new Scanner(System.in);
            String ipLet = scanner.nextLine();
            if (ipLet.length()>1){
                System.out.println("One letter at a time please!");
                continue;
            }
            if (lettersGuessed.contains(ipLet)){
                System.out.println("You already guessed that letter, try again");
                System.out.println("You have guessed "+instanceForSorter.sorter(lettersGuessed));
                continue;
            }
            lettersGuessed+=(ipLet.charAt(0));
            System.out.println("You have guessed "+instanceForSorter.sorter(lettersGuessed));
            //now we have INPUT (iplet) AND REMAINING GUESSES (remainingGuesses)
            //------------Game Logic--------------
            boolean firstCheck = false;
            boolean stillHasIt = true;
            if (pickerProc.toString().contains(ipLet)) {
                firstCheck = true;
            }else{
                System.out.println("WRONG GUESS");
            }
            while (firstCheck && stillHasIt) {
                reveal.setCharAt(pickerProc.indexOf(ipLet), ipLet.charAt(0));
                pickerProc.setCharAt(pickerProc.indexOf(ipLet), '-');
                if (pickerProc.toString().contains(ipLet)) {
                    stillHasIt = true;
                } else {
                    stillHasIt = false;
                }
            }
            System.out.println(reveal);
            if (!firstCheck) {
                newGame.setLeft(remainingGuesses - 1);
            }
            if (newGame.getEncap().equals(pickerProc.toString())){
                newGame.gameOver=true;
                newGame.won=true;
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Game Over!");
        if (newGame.won){
            System.out.println("You Won! and guessed the movie with spare lives ("+(remainingGuesses+1)+").");
        }else {
            System.out.println("Sorry You Lost :(");
            System.out.println("The movie was: '"+moviePicked+"'.");
        }

            //------------------------------------


    }
    private String sorter(String input){
        char [] tempArray=input.toCharArray();
        Arrays.sort(tempArray);
        String sorted= new String(tempArray);
        return sorted.toUpperCase().replace("", " ").trim();
    }

}