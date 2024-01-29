package src.tic.tac.toe;

import java.net.*;
import java.io.*;

// PLAYER = false = O

public class SimpleClient {
    private static void clearBuffer(BufferedReader reader) throws IOException {
        while (reader.ready()) {
            reader.readLine();
        }
    }
    public static void main(String[] args) {      
        InetAddress addr;
        
        try {
            // addr = InetAddress.getByName(null);
            addr = InetAddress.getByName("localhost");
        } catch (UnknownHostException ex) {
            System.out.println("Unknown host");
            return;
        }
        try (Socket socket = new Socket(addr, 6666);
             InputStream inStream = socket.getInputStream();
             OutputStream outStream = socket.getOutputStream()) {

            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            // String consoleLine;
            String serverOutput = "";
            String consoleOutput = "";
            
            int size = Integer.parseInt(args[0]);
            int toWin = Integer.parseInt(args[1]);
            
            boolean player = true;
            TicTacToe game = new TicTacToe(size, toWin, player);
            game.printPlayGround(); System.out.println();
            boolean myTurn = false;
            boolean done = false;
            boolean tie = false;
            
            while(!done && !tie){
                if(myTurn){
                    clearBuffer(consoleReader);
                    System.out.println("Your move : ");
                    while(!game.free(consoleOutput = consoleReader.readLine())); // consoleOutput = consoleReader.readLine();
                    System.out.println("Valid move played : "  + consoleOutput);
                    done = game.makeMove(consoleOutput, player);
                    out.println(consoleOutput);
                }
                else if ((serverOutput = in.readLine()) != null){
                    System.out.println("Opponents move : " + serverOutput);
                    done = game.makeMove(serverOutput, player);                    
                }
                player = !player;
                myTurn  = !myTurn;
                game.printPlayGround();
                System.out.println();
                tie = game.tie();

                if (serverOutput.equals("strasna nadavka ale nemozem ju napisat")) done = !done;
            }
            
            if(tie) System.out.println("Tie!");
            else if(player) System.out.println("You won!");
            else System.out.println("You lost!");

            // out.println("END");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
