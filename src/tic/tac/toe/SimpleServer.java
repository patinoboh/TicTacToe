package src.tic.tac.toe;

import java.net.*;
import java.io.*;

// PLAYER = true = X



public class SimpleServer {
    private static void clearBuffer(BufferedReader reader) throws IOException {
        while (reader.ready()) {
            reader.readLine();
        }
    }
    public static void main(String[] args) {

        int port = Integer.parseInt(args[0]);
        int size = Integer.parseInt((args[1]));
        int toWin = Integer.parseInt((args[2]));

        System.out.println("port : " + port + " size : " + size + " toWin : " + toWin);
        
        try (ServerSocket s = new ServerSocket(port, 50, InetAddress.getByName("0.0.0.0"))) {
            System.out.println("Server ready");
            while(true){
                try (Socket socket = s.accept()) {
                    System.out.println("Accepting connections");

                    PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

                    String clientOutput = ""; 
                    String consoleOutput = "";
                    
                    boolean done = false;                            
                    boolean myTurn = true;
                    boolean tie = false;
                    boolean player = true;

                    TicTacToe game = new TicTacToe(size, toWin, player);
                    game.printPlayGround(); System.out.println();
                    
                    while (!done && !tie) {
                        if(myTurn){
                            clearBuffer(consoleReader);
                            System.out.println("Your move : ");
                            while(!game.free(consoleOutput = consoleReader.readLine()));
                            System.out.println("Valid move played : " + consoleOutput);
                            done = game.makeMove(consoleOutput, player);
                            out.println(consoleOutput);
                        }
                        else if ((clientOutput = in.readLine()) != null){
                            System.out.println("Opponents played : " + clientOutput);
                            done = game.makeMove(clientOutput, player);
                        }
                        player = !player;
                        myTurn = !myTurn;                    
                        game.printPlayGround();
                        System.out.println();
                        tie = game.tie();

                        if (consoleOutput.equals("strasna nadavka ale nemozem ju napisat")) done = !done;
                    }

                    if(tie) System.out.println("Tie!");
                    else if(player) System.out.println("You lost!");
                    else System.out.println("You won!");

                    System.out.println("Closing connection");
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
