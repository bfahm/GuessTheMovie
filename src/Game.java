import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
//Fields
    private String movieName;
    private int guessesLeft;
    private ArrayList<String> movieList=new ArrayList<>(0);
    public boolean gameOver;
    public boolean won;
    private boolean ENmovie;
    //private File file;

//Methods
    private int movieSorter()throws Exception{
        File file;
        int numberOfMovies=0;
        if (ENmovie) {
            file = new File("textfile.txt");
        }else{
            file = new File("textfile2.txt");
        }
        Scanner scanner=new Scanner(file);
        while (scanner.hasNextLine()) {
            movieList.add(scanner.nextLine());
            numberOfMovies += 1;
        }
        return numberOfMovies;
    }
    private int randomNum(int numberOfMovies){
        double a=Math.random()*numberOfMovies;
        return (int)a;
        //return 21;     //fixed movie choice
    }
    private void moviePicker()throws Exception{
        int movieIndex=randomNum(movieSorter());
        movieName= movieList.get(movieIndex);
    }
    public String getMovie(){
        return movieName;
    }
    private String encapsulator (String movieName){
        int len=movieName.length();
        String opString="";
        for (int i=0;i<len;i++){
            opString+="-";
        }
        return opString;
    }
    public int getLeft(){
        return guessesLeft;
    }
    public void setLeft(int num){
        guessesLeft=num;
    }
    public String getEncap(){
        return encapsulator(movieName);
    }
    public void setEnMovie(boolean a){
        ENmovie =a;
    }
    Game(){
        guessesLeft=9;
        won=false;
        gameOver=false;
        ENmovie=true;
    }
    public void Game_init()throws Exception{
        moviePicker();
        System.out.println("You are guessing:"+encapsulator(getMovie()));
    }
}
