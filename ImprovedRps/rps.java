import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class rps{

    private static int computerMove = 0;
    private static boolean redo = false;
    private static int playerMove = 0;

    public static void main(String[] args){

        stopwatch stopwatch = new stopwatch();
        String result = "";
        int i = 0;

        do{

            stopwatch.StopWatchThread.start();

            if(i!=0) {clearConsole();}
            playerMove = getInput();

            if (playerMove == -1) return;

            computerMove = makeComputerMove(3);

            System.out.println("\nComputer moved " + compString(computerMove));

            result = checkWin(playerMove, computerMove);

            System.out.println("Result: " + result + "\n");

            i++;

        } while(redo == true);

        System.out.println("Playtime: " + stopwatch.getTime() + "\n");

        stopwatch.stopThread();

    }

    private static void clearConsole(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();      
    }

    private static int getInput(){

        BufferedReader br = null;

        if(System.console().charset() == null){
            br = new BufferedReader(new InputStreamReader(System.in));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in, System.console().charset()));
        }

        System.out.print("\nEnter your move, 1 for rock 2 for paper and 3 for scissorrs: ");

        try {
            playerMove = Integer.parseInt(br.readLine());

            if(playerMove!=1&&playerMove!=2&&playerMove!=3)throw new IOException("Invalid Input");

        } catch (NumberFormatException | IOException e) {
            System.out.println("\nMust Enter a Valid Input 1 - 3\n");
            return -1;
        }

        return playerMove;

    }

    private static int makeComputerMove(int range){
        Random random = new Random();
        return random.nextInt(range) + 1;
    }

    private static String compString(int comp){
        if(computerMove == 1) return "Rock";
        else if(computerMove == 2) return "Paper";
        else if(computerMove == 3) return "Scissorrs";
        return "";
    }

    private static String checkWin(int playerMove, int compMove){

        //1 = rock   2 = paper   3 = scissorrs

        if(playerMove == compMove){
            //Player and Computer Tie
            redo = false;
            return "You Tied!";
        } else if(playerMove == 1 && compMove == 3){
            //Player Wins
            redo = false;
            return "Congrats, You Won!";
        } else if(playerMove == 2 && compMove == 1){
            //Player Wins
            redo = false;
            return "Congrats, You Won!";
        } else if(playerMove == 3 && compMove == 2){
            //Player Wins
            redo = false;
            return "Congrats, You Won!";
        } else if(playerMove == 1 && compMove == 2){
            //Computer Wins
            redo = false;
            return "Computer has won, Sorry!";
        } else if(playerMove == 2 && compMove == 3){
            //Computer Wins
            redo = false;
            return "Computer has won, Sorry!";
        } else if(playerMove == 3 && compMove == 1){
            //Computer Wins
            redo = false;
            return "Computer has won, Sorry!";
        }

        return "";
        
    }
}

class stopwatch implements Runnable{

    Thread StopWatchThread = new Thread(this, "Stop Watch");

    static boolean running = true;
    static long minutes = 0;
    static long seconds = 0;
    static long hours = 0;
    private long startTime;

    private void start() {
        startTime = System.currentTimeMillis();
    }

    private long stop() {
        return System.currentTimeMillis() - startTime;
    }

    public void run() {
        start();

        while (running){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            long millis = stop();
            seconds = millis / 1000;
            minutes = seconds / 60;
            seconds %= 60;
            hours = minutes / 60;
            minutes %= 60;
        }
    }

    public void stopThread(){
        running = false;
    }

    public String getTime(){
        return hours + " hours " + minutes + " minutes " + seconds + " seconds"; 
    }
}