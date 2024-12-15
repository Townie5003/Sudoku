
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Board {

    public String[][] createBoard(){
       String[][] board = new String[9][9];
       for (int i = 0; i <= 8; i++){
        for (int j = 0; j <= 8; j++){
            board[i][j] = " 0 ";
        }
       }
       return board;
    }

    
    public void printBoard(String[][] board){
        for (int i = 0; i <= 8; i++){
            for (int j = 0; j <= 8; j++){
                if (j % 3 == 0 && j != 0){
                    System.out.print("|");
                }
                System.out.print(board[i][j] + ",");
            }
            System.out.println();
            if (i == 2 || i == 5){
                System.out.println("-----------------------------");
                


            }
        }
    }

    public boolean SudokuGenerator(String[][] board) {
        // Find the next empty cell in the board (represented by "0")
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j].trim().equals("0")) {
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(board, i, j, num)) {
                            board[i][j] = " " + num + " ";

                            if (SudokuGenerator(board)) {
                                return true;
                            }
    
                            board[i][j] = "0";
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isSafe(String[][] board, int row, int col, int num) {
        for (int x = 0; x < 9; x++) {
            if (board[row][x].trim().equals(String.valueOf(num)) || 
                board[x][col].trim().equals(String.valueOf(num))) {
                return false;
            }
        }
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol].trim().equals(String.valueOf(num))) {
                    return false;
                }
            }
        }
    
        return true;
    }

    public void clearBoard(String[][] board){
        Random random = new Random();
        for (int k = 0; k <= 58; k ++){
            int i = random.nextInt(9);
            int j = random.nextInt(9);
            board[i][j] = " 0 ";
        }
    }

    public void givenSudoku(String[][] board){
        board[0][0] = " 1 "; board[0][3] = " 6 "; board[0][8] = " 4 ";
        board[1][2] = " 6 "; board[1][4] = " 8 "; board[1][6] = " 2 ";
        board[2][0] = " 9 "; board[2][1] = " 5 "; board[2][3] = " 1 ";
        board[2][4] = " 3 "; board[2][7] = " 8 "; board[2][8] = " 6 ";
        board[3][8] = " 1 "; board[3][6] = " 8 "; board[3][5] = " 4 ";
        board[4][7] = " 6 "; board[4][6] = " 9 "; board[4][1] = " 4 ";
        board[4][2] = " 5 "; board[5][0] = " 8 "; board[5][2] = " 1 ";
        board[5][3] = " 9 "; board[6][0] = " 5 "; board[6][1] = " 1 ";
        board[6][8] = " 2 "; board[6][7] = " 7 "; board[6][4] = " 4 ";
        board[6][5] = " 9 "; board[7][2] = " 3 "; board[7][4] = " 5 ";
        board[7][6] = " 1 "; board[8][0] = " 4 "; board[8][8] = " 3 ";
        board[8][5] = " 1 ";
    }


    private HashMap<String, String[]> createBlocks(String[][] board){
        HashMap<String, String[]> blocks = new HashMap<>();
        blocks.put("block1", new String[]{
            board[0][0], board[0][1], board[0][2],
            board[1][0], board[1][1], board[1][2],
            board[2][0], board[2][1], board[2][2]
        });
        blocks.put("block2", new String[]{
            board[0][3], board[0][4], board[0][5],
            board[1][3], board[1][4], board[1][5],
            board[2][3], board[2][4], board[2][5]
        });
        blocks.put("block3", new String[]{
            board[0][6], board[0][7], board[0][8],
            board[1][6], board[1][7], board[1][8],
            board[2][6], board[2][7], board[2][8]
        });
        blocks.put("block4", new String[]{
            board[3][0], board[3][1], board[3][2],
            board[4][0], board[4][1], board[4][2],
            board[5][0], board[5][1], board[5][2]
        });
        blocks.put("block5", new String[]{
            board[3][3], board[3][4], board[3][5],
            board[4][3], board[4][4], board[4][5],
            board[5][3], board[5][4], board[5][5]
        });
        blocks.put("block6", new String[]{
            board[3][6], board[3][7], board[3][8],
            board[4][6], board[4][7], board[4][8],
            board[5][6], board[5][7], board[5][8]
        });
        blocks.put("block7", new String[]{
            board[6][0], board[6][1], board[6][2],
            board[7][0], board[7][1], board[7][2],
            board[8][0], board[8][1], board[8][2]
        });
        blocks.put("block8", new String[]{
            board[6][3], board[6][4], board[6][5],
            board[7][3], board[7][4], board[7][5],
            board[8][3], board[8][4], board[8][5]
        });
        blocks.put("block9", new String[]{
            board[6][6], board[6][7], board[6][8],
            board[7][6], board[7][7], board[7][8],
            board[8][6], board[8][7], board[8][8]
        });

        return blocks;
        
        
    }

    public String getBlock(int i, int j){
        int blockRow = i / 3;
        int blockCol = j / 3;
        int blockNumber = blockRow * 3 + blockCol + 1;
        return "block" + blockNumber;
    }

    private boolean numberInRow(String[][] board, int j, int number) {
        for (int i = 0; i < 9; i++){
            if (board[i][j].equals(" " + String.valueOf(number) + " ")){
                return true;
            }
        }
        return false;
    }
    
    private boolean numberInCol(String[][] board, int i, int number) {
        for (int j = 0; j < 9; j++) {
            if (board[i][j].equals(" " + String.valueOf(number) + " ")){
                return true;
            }
        }
        return false;
    }
    

    public void autoNotes(String[][] board){
        HashMap<String, String[]> blocks = createBlocks(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j].equals(" 0 ")){
                    int counter = 0;
                    List<String> numbers = new ArrayList<>();
    
                    for (int r = 1; r <= 9; r++) {
                        String working_block = getBlock(i, j);
                        String[] numbers_in_block = blocks.get(working_block);
                        if (!Arrays.asList(numbers_in_block).contains(String.valueOf(r)) && 
                            !numberInCol(board, i, r) && 
                            !numberInRow(board, j, r)){
                            counter += 1;
                            numbers.add(String.valueOf(r));
                        }
                    }
                    assert counter > 0 : "There should be more than one possible value for this cell: " + i + " " + j;
                    if (counter == 1){
                        String purpleText = "\u001B[35m" + numbers.get(0) + "\u001B[0m";
                        board[i][j] = " " + purpleText + " ";
                    } else if (counter == 2){
                        String RedText1 = "\u001B[31m" + numbers.get(0) + "\u001B[0m";
                        String RedText2 = "\u001B[31m" + numbers.get(1) + "\u001B[0m";
                        board[i][j] = RedText1 + " " + RedText2;
                    } else if (counter == 3){
                        String OtherRedText1 = "\u001B[34m" + numbers.get(0) + "\u001B[0m";
                        String OtherRedText2 = "\u001B[34m" + numbers.get(1) + "\u001B[0m";
                        String OtherRedText3 = "\u001B[34m" + numbers.get(2) + "\u001B[0m";
                        board[i][j] = OtherRedText1 + OtherRedText2 + OtherRedText3;
                    }
                }
            }
        }
    
    
    }

    public void removeZeros(String[][] board){
        for (int i = 0; i <9; i++){
            for (int j = 0; j < 9; j ++){
                if (board[i][j].equals(" 0 ")){
                    board[i][j] = "   ";
                }
            }
        }
    }

}

    

