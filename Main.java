import java.util.Scanner;

public class Main{
    private static void inputHandling(String[][] board, String[][] correctBoard){
        System.out.println("Please input your a number in the form Row, Col, (Number). Type ! first for a note.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        if (parts.length < 3 || parts.length > 6){
            inputHandling(board, correctBoard);
        }
        if (!parts[2].equals("!")){
            if ((Integer.valueOf(parts[0]) > 9 || Integer.valueOf(parts[0]) < 1) || ((Integer.valueOf(parts[1]) > 9 || Integer.valueOf(parts[1]) < 1)) || (Integer.valueOf(parts[2]) > 9 || Integer.valueOf(parts[2]) < 1)){
                inputHandling(board, correctBoard);
        }
        
        }
        if (correctBoard[Integer.valueOf(parts[0]) - 1][Integer.valueOf(parts[1]) - 1].equals( " " + parts[2] + " ")){
            board[Integer.valueOf(parts[0]) - 1][Integer.valueOf(parts[1]) - 1] = " " + parts[2] + " ";
        } 
        if ((!correctBoard[Integer.valueOf(parts[0]) - 1][Integer.valueOf(parts[1]) - 1].equals( " " + parts[2] + " ")) && (!parts[2].equals("!"))){
            System.out.println("Sorry that is incorrect. Try again");
            inputHandling(board, correctBoard);
        }
        if (parts[2].equals("!")){
            try {
                
                if (parts.length == 4){
                    String purpleText = "\u001B[35m" + parts[3] + "\u001B[0m";
                    board[Integer.valueOf(parts[0]) - 1][Integer.valueOf(parts[1]) - 1] = " " + purpleText + " ";
                    if (parts[3].equals("0")){
                        board[Integer.valueOf(parts[0]) - 1][Integer.valueOf(parts[1]) - 1] = "   ";
                    }
                } 
                else if (parts.length == 5){
                    String RedText1 = "\u001B[31m" + parts[3] + "\u001B[0m";
                        String RedText2 = "\u001B[31m" + parts[4] + "\u001B[0m";
                        board[Integer.valueOf(parts[0]) - 1][Integer.valueOf(parts[1]) - 1] = RedText1 + " " + RedText2;
                }
                else if (parts.length == 6){
                    String OtherRedText1 = "\u001B[34m" + parts[3] + "\u001B[0m";
                        String OtherRedText2 = "\u001B[34m" + parts[4] + "\u001B[0m";
                        String OtherRedText3 = "\u001B[34m" + parts[5] + "\u001B[0m";
                        board[Integer.valueOf(parts[0]) - 1][Integer.valueOf(parts[1]) - 1] = OtherRedText1 + OtherRedText2 + OtherRedText3;
                }


            } catch (Exception e) {
                System.out.println("Please enter a valid note");
                inputHandling(board, correctBoard);
            }
        }
        
        
                
    }

    public static void main(String[] args) {
        stateMachine state_machine = new stateMachine("create");
        state_machine.setState("create");
        if ("create".equals(state_machine.returnState())){
            Board gameBoard = new Board();
            String[][] game_board = gameBoard.createBoard();
            gameBoard.givenSudoku(game_board);
            gameBoard.SudokuGenerator(game_board);
            String[][] correctBoard = new String[game_board.length][];
            for (int i = 0; i < game_board.length; i++) {
                correctBoard[i] = game_board[i].clone();
            }
            gameBoard.clearBoard(game_board);
            gameBoard.autoNotes(game_board);
            gameBoard.removeZeros(game_board);
            gameBoard.printBoard(game_board);
            //System.out.println("\n");
            //gameBoard.printBoard(correctBoard);
            while (true) { 
                inputHandling(game_board, correctBoard);
                gameBoard.printBoard(game_board);
            }
            
            
            
        }
    }
}

