public class MoveMaker {
    
    /*
     * This method is used to apply a previously verified and converted move to the previously defined game board
     * @position This parameter indicates user or AI specified position. (integer [2]; 0 <= int[0] < 8 && 0 <= int[1] <= 8) 
     * @color This parameter refers to the player who played the turn. (char; 'X' || 'O')
     * @return: void
     * */
    
    
    public static void makeMove(char[][] board, int[] position, char color){
        board[position[0]][position[1]] = color; //Adds a piece to the board in the position.
        for (int[] h: MoveChecker.directions){ //Checks possible flips in every direction.
            boolean flag = false;
            boolean path = false;
            int a =position[0] + h[0]; 
            int b = position[1] + h[1];
            try{ //throws the error that position[0] + h[0] or position[1] + h[1] exceeds 7 or 0 bounds. In that case, //we don't need to check because the piece path is closed from that direction.
                while (board[a][b] == MoveChecker.otherColor(color)){ //keeps following the opponent piece sequence until gets to some empty space, wall or self piece.
                    flag = true;
                    a += h[0];
                    b += h[1];
                    if (a >= 8 || a < 0){
                        flag = false;
                        break;
                    }
                    if (b >= 8 || a < 0){
                        flag = false;
                        break;
                    }
                }
            }
            catch (Exception e){
                flag = false;
            }

            if (flag){
                if (board[a][b] == color){//the cause of breaking of the while should be only self piece in order to that path be good.
                   path = true; 
                }
            }

            if (path){//if the path is good, everything in that path gets flipped.
                a =position[0] + h[0];
                b = position[1] + h[1];
                while (board[a][b] == MoveChecker.otherColor(color)){
                    board[a][b] = color;
                    a += h[0];
                    b += h[1];
                    if (a >= 8 || a < 0){
                        break;
                    }
                    if (b >= 8 || b < 0){
                        break;
                    }
                }
            }
        }

    }
}
