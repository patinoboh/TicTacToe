package src.tic.tac.toe;

import src.tic.tac.toe.SimpleClient;
import src.tic.tac.toe.SimpleServer;

public class Program {

    public static void main(String[] args){
        int size;
        int toWin;
        int port = 6666;
        boolean debug = true;

        if(args.length == 2){
            // CLIENT
            size = Integer.parseInt(args[0]);
            toWin = Integer.parseInt(args[0]);

            if(debug) System.out.println("Client, size : " + size + " toWin : " + toWin);
            SimpleClient.main(args);

        }
        else{
            // SERVER
            port = Integer.parseInt(args[0]);
            size = Integer.parseInt(args[1]);
            toWin = Integer.parseInt(args[2]);

            if(debug) System.out.println("Server, port : " + port + " size : " + size + " toWin : " + toWin);

            SimpleServer.main(args);
        }
    }
}
