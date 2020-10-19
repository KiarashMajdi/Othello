
public class MoveChecker{
    public static int[][] availableMoves;

    public static int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public static void vectorCheck(char[][] board, char color){//looks for possible moves for whose turn it is
        availableMoves = new int[][]{};//initialize the moves array
        for (int h = 0; h < 8; h++){//through the board
            for (int i = 0; i < 8; i++){
                if (board[h][i] == color){//every position that is possible ends in my piece
                    for (int[] j: directions){
                        boolean flag = false;
                        
                        int a = h + j[0];
                        int b = i + j[1];
                        try{//run into wall exception
                            while (board[a][b] == otherColor(color)){//there should be enemy piece at some direction
                                flag = true;
                                a += j[0];
                                b += j[1];
                                if (a >= 8 || a < 0){//run into wall breaks
                                    flag = false;
                                    break;
                                }
                                if (b >= 8 || a < 0){
                                    flag = false;
                                    break;
                                }
                            }
                        }
                        catch (Exception e){//if it runs into a wall
                            flag = false;
                        }

                        if (flag){//when there's no enemy piece anymore
                            if (board[a][b] != color && !contains(availableMoves, new int[]{a, b})){//if it's empty and this house has not been checked before
                                availableMoves = addToArray(availableMoves, new int[]{a, b});//it's available
                            }
                        }

                    }
                }
            }
        }
        
    }
    
    

    public static char otherColor(char color){//o -> x and x -> o
        if (color == 'X'){
            return 'O';
        }
        else if (color=='O'){
            return 'X';
        }
        else{
            return color;
        }
    }

    public static int[][] addToArray(int[][] array, int[] a){//add a position to a 2d array
        int[][] brray = array;
        array = new int[brray.length + 1][2];
        for (int h = 0; h < brray.length; h++){
            array[h] = brray[h];
        }
        array[brray.length] = a;
        return array;
    }

    public static boolean contains(int[][] a, int[] b){//check if some location is in a 2d array
        for (int[] h: a){
            if (h[0]==b[0] && h[1] == b[1]){
                return true;
            }
        }
        return false;
    }

    public static boolean isValidMove(String move){//is the move in the available moves array?
        if (contains(availableMoves, convertMoves(move))){
            return true;
        }
        return false;

    }

    public static int[] convertMoves(String move){//convert a user-friendly move into location array a3 -> {5, 0}
        char[] order = move.toCharArray();
        int[] ord = new int[]{((int) order[0] - 96), Integer.parseInt(String.valueOf(order[1]))};
        return new int[]{8 - ord[1], ord[0] - 1};
    }

}