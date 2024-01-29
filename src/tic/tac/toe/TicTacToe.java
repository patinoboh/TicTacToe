package src.tic.tac.toe;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToe {
    ArrayList<ArrayList<Character>> playGround;
    ArrayList<String> columns;
    boolean player;
    Integer toWin;

    public void printPlayGround(){
        System.out.print(" ");
        for(int i = 0; i < playGround.size(); ++i){
            System.out.print(columns.get(i));
        }
        System.out.println();
        for(int row = 0; row < playGround.size(); ++row){
            System.out.print(row + 1);
            for(int col = 0; col < playGround.size(); ++col){
                System.out.print(playGround.get(row).get(col));
            }
            System.out.println(" ");
        }
    }

    public boolean inPlayground(int col, int row){
        return (col >= 0 && row >= 0 && col < playGround.size() && row < playGround.size());
    }

    public boolean free(int col, int row){
        return (inPlayground(col, row) && playGround.get(row).get(col).equals(' '));
    }
    
    public void changeAt(int col, int row, Character player){
        playGround.get(row).set(col, player);
    }

    
    public boolean free(String move){
        try{
            char row = move.charAt(0);
            int rowInt = (int)row - (int)'A';
            int col = (int)move.charAt(1) - (int)'1';
            if(!free(rowInt, col)) {System.out.println("You cannot play here : " + move); return false;};
            return true;
        } catch (Exception e){
            System.out.println("Not a valid position : " + move);
            return false;
        }
    }
    
    public boolean tie(){
        // return true if every position is filled
        for(int i = 0; i < playGround.size(); ++i){
            for(int j = 0; j < playGround.size(); ++j){
                if(playGround.get(i).get(j).equals(' ')) return false;
            }
        }
        return true;
    }

    public boolean win(int row, int col, boolean player){
        // return true if the player won
        int count = 0;
        for(int i = 0; i < playGround.size(); ++i){
            if(playGround.get(row).get(i).equals(player ? 'X' : 'O')) count++;
            else count = 0;
            if(count == toWin) return true;
        }
        // check col
        count = 0;
        for(int i = 0; i < playGround.size(); ++i){
            if(playGround.get(i).get(col).equals(player ? 'X' : 'O')) count++;
            else count = 0;
            if(count == toWin) return true;
        }

        // TODO diagonals
        // check diagonal
        count = 0;
        for(int i = 0; i < playGround.size(); ++i){
            if(playGround.get(i).get(i).equals(player ? 'X' : 'O')) count++;
            else count = 0;
            if(count == toWin) return true;
        }
        // check anti-diagonal
        count = 0;
        for(int i = 0; i < playGround.size(); ++i){
            if(playGround.get(i).get(playGround.size() - i - 1).equals(player ? 'X' : 'O')) count++;
            else count = 0;
            if(count == toWin) return true;
        }
        return false;
    }

    public boolean makeMove(String where, boolean player){
        // returns boolean whether the player won
        char p = player ? 'X' : 'O';
        try{
            char row = where.charAt(0);
            int rowInt = (int)row - (int)'A';
            int col = (int)where.charAt(1) - (int)'1';
            // System.out.println("row : " + rowInt + " col : " + col);
            if(free(rowInt, col)) changeAt(rowInt, col, p);
            return win(col, rowInt, player);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void makeMove(int row, int col, boolean player){
        changeAt(col, row, player ? 'X' : 'O');
    }


    public TicTacToe(int size, int toWin, boolean player){
        this.toWin = toWin;
        this.player = player;
        this.playGround = new ArrayList<ArrayList<Character>>();
        for(int row = 0; row < size; row++){
            ArrayList<Character> rowList = new ArrayList<Character>();
            for(int col = 0; col < size; col++){
                rowList.add(' ');
            }
            playGround.add(rowList);
        }
        this.columns = new ArrayList<String>(Arrays.asList("A","B","C","D","E","F","G","H","I","J"));
    }

    public static void main(String[] args){
        System.out.println("Hello from Game");
        TicTacToe game = new TicTacToe(9, 3,true);
//        game.changeAt(0,1,'0');
//        game.changeAt(0,2,'0');
//        game.changeAt(0,3,'0');
//
//        game.changeAt(1,0,'X');
//        game.changeAt(1,1,'X');
//        game.changeAt(1,2,'X');
        game.makeMove("A1", true);
        game.makeMove("A2", true);
        game.makeMove("A3", true);

        game.makeMove("B1", false);
        game.makeMove("B2", false);
        game.makeMove("B3", false);
        game.printPlayGround();
    }
}
