class Board {
    public static char[][] board;

    public Board() {//according to AI prioritied and starting locations, initialize the board[][] array
        String a = "18244281";
        String b = "89766798";
        String c = "27355372";
        String d = "465XO564";
        String e = "465OX564";
        board = new char[8][8];
        board[0] = a.toCharArray();
        board[1] = b.toCharArray();
        board[2] = c.toCharArray();
        board[3] = d.toCharArray();
        board[4] = e.toCharArray();
        board[5] = c.toCharArray();
        board[6] = b.toCharArray();
        board[7] = a.toCharArray();
    }
    public static void printBoard(int[][] availableMoves, char turn) {//print the board with user friendly notations and arg symbol
        for (int h = 0; h < 8; h++) {
            System.out.println("   +---+---+---+---+---+---+---+---+");
            System.out.print((8 - h) + "  ");
            
            for (int i = 0; i < 8; i++) {
                System.out.print("| ");
                
                
                if (board[h][i] == 'X') {
                    System.out.print("X");
                } else if (board[h][i] == 'O') {
                    System.out.print("O");
                } 
                else if (contains(availableMoves, new int[]{h, i})){
                    System.out.print(turn);
                }
                else {
                    System.out.print(" ");
                }
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println("   +---+---+---+---+---+---+---+---+    AI:" + count(Main.AIChar) + "   You: " + count(MoveChecker.otherColor(Main.AIChar)));
        System.out.println("     a   b   c   d   e   f   g   h      Last Move:" + Main.lastMove);
    }

    

    public static boolean contains(int[][] a, int[] b){//if a location array is in a 2d array return true
        for (int[] h: a){
            if (h[0]==b[0] && h[1] == b[1]){
                return true;
            }
        }
        return false;
    }

    public static int count(char clr){//count every piece of the given color on the board
        int cnt = 0;
        for (int h = 0; h < 8; h++){
            for (int i = 0; i < 8; i++){
                if (board[h][i] == clr){
                    cnt ++;
                }
            }
        }
        return cnt;
    }

}       