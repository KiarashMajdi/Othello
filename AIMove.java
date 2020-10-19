public class AIMove {
    public static int[] chooseMove(char[][] board, char color){//just do the highest value move possible in available moves
        MoveChecker.vectorCheck(board, color);
        try{
            int[][] am = MoveChecker.availableMoves;

            char[] values = new char[am.length];
            for (int h = 0; h < am.length; h++){
                values[h] = Board.board[am[h][0]][am[h][1]];
            }
            return am[minIndex(values)];
        }
        catch(Exception e){
            return new int[]{-1};
        }
    }

    public static int minIndex(char[] array){//find the index of minimum element
        int mindex = 0;
        for (int h = 0; h < array.length; h++){
            if (array[h] < array[mindex]){
                mindex = h;
            }
        }
        return mindex;
    }
}
