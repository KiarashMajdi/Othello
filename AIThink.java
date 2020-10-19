public class AIThink {
    public static char[][] board;
    public static char[][] eBoard = new char[8][8]; // an instance of the main board
    public static int[] decide(char[][] board, char color){ //the whole thinking thing
        new AIMove();

        int[][] am = MoveChecker.availableMoves;
        if (am.length == 0){//is able to throw the "No Move in this turn" exception
            return AIMove.chooseMove(board, color);
        }
        AIThink.board = board;//main board is made global in the whole class as "board"
        int[][][] opponentMove = new int[9][0][2]; //[value for opponent][locations sorted by value for me][locationx, locationy]

        int value;//for me
        for (int h = 0; h < am.length; h++){//all my possible moves
            boardCopy();//instance becomes same with main
            value = Integer.parseInt(String.valueOf(eBoard[am[h][0]][am[h][1]]));//my move  value

            System.out.println("HisMove: " + am[h][0] + " " + am[h][1]);
            MoveMaker.makeMove(eBoard, am[h], color);//do the move on the instance
            MoveChecker.vectorCheck(eBoard, MoveChecker.otherColor(color));//check opponent moves on the instance after my move

            
            
            try{//if there is a move for opponent
                int[] bestOpp = AIMove.chooseMove(eBoard, MoveChecker.otherColor(color));//the best possible value possible for opponent on the instance board
                int opvalue = Integer.parseInt(String.valueOf(eBoard[bestOpp[0]][bestOpp[1]])); //what is the calue of that move
                if (!MoveChecker.contains(opponentMove[opvalue - 1], am[h]) && value <= opvalue){//if the same move is not found yet
                    opponentMove[opvalue - 1] = add(opponentMove[opvalue - 1], am[h]);//add this move to its correct location
                }
            }
            catch (Exception e){//if not
                opponentMove[8] = add(opponentMove[8], am[h]);//consider it the least value for the opponent
            }
            

        }


        for (int h = 0; h < 9; h++){//sort all opponent move arrays
            try{
                
                bubbleSort(opponentMove[h]);
                
                for (int i = 0; i < opponentMove[h].length; i++){
                    System.out.println(h+ ":"+ i + " " + opponentMove[h][i][0] + " " + opponentMove[h][i][1]);
                }
                System.out.println("There " + opponentMove[h].length);
            }
            catch (Exception e){
                System.out.print("");
            }
        }

        int[] bestValue = AIMove.chooseMove(board, color);//the worst case
        System.out.println(bestValue[0] + " " + bestValue[1]);
        for (int h = 0; h < 9; h++){//find the best move  according to my own priorities..
            for (int i = opponentMove[h].length - 1; i >= 0; i--){

                if (Integer.parseInt(String.valueOf(board[opponentMove[h][i][0]][opponentMove[h][i][1]])) <= 6){
                    System.out.println("Update: " + bestValue[0] + " " + bestValue[1]);
                    bestValue = opponentMove[h][i];

                }
            }
        }

        return bestValue;//return the chosen move
    }

    public static int[][] add(int[][] a, int[] b){//add 1d array to 2d array
        int[][] temp = a;
        a = new int[temp.length + 1][2];

        for (int h = 0; h < temp.length; h++){
            a[h] = temp[h];
        }

        a[temp.length] = b;

        return a;

    }

    public static void bubbleSort(int[][] a){//sort an array of locations based on values
        for (int h = 0; h < a.length; h++){
            for (int i = 0; i < a.length - h; i++){
                if (board[a[i][0]][a[i][1]] > board[a[i+1][0]][a[i+1][1]]){
                    int[] temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                }
            }
        }
    }

    public static void boardCopy(){//update the instance to the main board
        for (int h = 0; h < 8; h++){
            for (int i = 0; i < 8; i++){
                eBoard[h][i] = Board.board[h][i];
            }
        }
    }
    public static void resetAI(){
        board = new char[8][8];
        eBoard = new char[8][8];
    }
}
