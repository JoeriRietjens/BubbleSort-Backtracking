package entity;

import org.w3c.dom.ls.LSOutput;

public class Sudoku {
    public static int[][] Grid_To_Solve = {
            /*{0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},*/

            /*{9,0,0,1,0,0,0,0,5},
            {0,0,5,0,9,0,2,0,1},
            {8,0,0,0,4,0,0,0,0},
            {0,0,0,0,8,0,0,0,0},
            {0,0,0,7,0,0,0,0,0},
            {0,0,0,0,2,6,0,0,9},
            {2,0,0,3,0,0,0,0,6},
            {0,0,0,2,0,0,9,0,0},
            {0,0,1,9,0,4,5,7,0},*/

            {8,0,0,0,0,0,0,0,0 },
            {0,0,3,6,0,0,0,0,0 },
            {0,7,0,0,9,0,2,0,0 },
            {0,5,0,0,0,7,0,0,0 },
            {0,0,0,0,4,5,7,0,0 },
            {0,0,0,1,0,0,0,3,0 },
            {0,0,1,0,0,0,0,6,8 },
            {0,0,8,5,0,0,0,1,0 },
            {0,9,0,0,0,0,4,0,0 }
    };
  private int[][] board;
  public static final int EMPTY = 0; //lege cell
  public static final int SIZE = 9; //grootte van het bord
    public static final int sqr = (int) Math.sqrt((double)SIZE);


    public Sudoku(int[][] board){
        this.board = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                this.board[i][j] = board[i][j];
            }
        }
    }
    //Check if possible number is already in row
    private boolean isInRow(int row, int number){
        for (int i = 0; i < SIZE; i++)
            if (board[row][i] == number){
                return true;
            }
        return false;
    }
    //Check if possible number is already in column
    private boolean isInColumn(int column, int number){
        for (int i = 0; i < SIZE; i++)
            if (board[i][column] == number){
                return true;
            }
        return false;
    }
    //Check if possible number is already in sub box
    private boolean isInSubBox(int row, int column, int number){
        int r = row - row % sqr;
        int c = column - column % sqr;

        for (int i = r; i < r + sqr; i++){
            for (int j = c; j < c + sqr; j++){
                if (board[i][j] == number)
                    return true;
            }
        }
        return false;
    }
    //Combined method to check if all are ok.
    private boolean isOk(int row, int column, int number){
       return !isInRow(row, number) && !isInColumn(column, number) && !isInSubBox(row, column, number);
    }

    public boolean solver(){
        for (int row = 0; row < SIZE; row++){
            for (int column = 0; column < SIZE; column++){
                //zoeken naar lege cell
                if (board[row][column] == EMPTY){
                    //we proberen hier mogelijke nummers
                    for (int number = 1; number <= SIZE; number++){
                        if (isOk(row, column, number)){
                            board[row][column] = number;

                            if (solver()) { //Start van het backtracken
                                return true;
                            }
                            else{ //als geen oplossing gevonden wordt dan wordt de cell leeggemaakt en gaan we verder
                                board[row][column] = EMPTY;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void display(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print("" + board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku(Grid_To_Solve);
        System.out.println("Sudoku to solve:");
        sudoku.display();

        long start = System.currentTimeMillis();

        if (sudoku.solver()){
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println("Solved in " + timeElapsed + "ms");
            sudoku.display();
        }
        else {
            System.out.println("Unsolvable sudoku");
        }
    }
}