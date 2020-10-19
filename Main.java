import java.util.Scanner;
import java.util.Random;
public class Main {
    public static char color = 'X';
    public static boolean AI = true;
    public static char AIChar; 
    public static String lastMove = "No last move";
    public static void main(String[] args){
        new Board();
        new MoveChecker();
        if (new Random().nextInt(2) == 1){//AI is black or white
            AI = true;
            AIChar = 'X';
        }
        else{
            AI = false;
            AIChar = 'O';
        }
        boolean AIHasMove = true;//for ending the game if needed
        boolean personHasMove = true;
        
        while (true){//game loop

            MoveChecker.vectorCheck(Board.board, color);//find available moves

            if (color=='X'){//if X turn draw table with this character
                System.out.println("X Turn!");
                Board.printBoard(MoveChecker.availableMoves, ',');
            }
            else{//if o turn draw table with that character
                System.out.println("O Turn!");
                Board.printBoard(MoveChecker.availableMoves, '.');
            }
            

            
            if (AI){//if AI turn
                try{//throws if there is no move
                    if (MoveChecker.availableMoves.length == 0){
                        AIHasMove = false;
                    }
                    else{
                        AIHasMove = true;
                        getAIEasymove();
                        
                    }
                    
                }
                catch(Exception e){
                    AIHasMove = false;
                }
            }
           
            else{//if person turn
                try{//throws no move 
                    if (MoveChecker.availableMoves.length == 0){
                        personHasMove = false;
                    }
                    else{
                        personHasMove = true;
                        getMove();
                    }
                    
                }
                catch(Exception e){
                    System.out.println("BUG: " + e.getMessage());
                    personHasMove = false;
                }
            }
            

            color = MoveChecker.otherColor(color);//change turns
            AI = !AI;

            if (!AIHasMove && !personHasMove){//if no one has had a move this turn, the game ends.
                endings();
                break;
            }
            
        }
    }

    public static void getMove(){//Validate and apply the move from user
        new MoveChecker();
        new MoveMaker();
        MoveChecker.vectorCheck(Board.board, color);
        
        Scanner input = new Scanner(System.in);

        boolean valid = false;
        
        String move = "";

        while (!valid){
            move = input.nextLine();
            try{
                valid =MoveChecker.isValidMove(move);
                if (!valid){
                    System.out.println("The move does not exist!");
                }
            }
            catch (Exception e){
                System.out.println("The move is not valid!");
            }
            
        }
        lastMove = move;
        int[] conmove = MoveChecker.convertMoves(move);
        MoveMaker.makeMove(Board.board, conmove, color);
    }
    public static void getAIEasymove(){//apply the move from AIThink
        int[] move = AIThink.decide(Board.board, color);
        lastMove = backConv(move);
        MoveMaker.makeMove(Board.board, move, color);
    }

    public static void getAIEasiermove(){//apply the move from AIMove
        int[] move = AIMove.chooseMove(Board.board, color);
        lastMove = backConv(move);
        MoveMaker.makeMove(Board.board, move, color);
    }

    public static String backConv(int[] move){//to turn AI move into user friendly coordinates
        char a = (char) (56 - move[0]);
        char b = (char) (move[1] + 97);
        
        return new String(new char[]{b, a});
    }

    public static void endings(){//ends the game

        System.out.println("You: " + Board.count(MoveChecker.otherColor(AIChar)));
        System.out.println("AI: " + Board.count(AIChar));

        if (Board.count(AIChar) < Board.count(MoveChecker.otherColor(AIChar))){
            System.out.println("You win!");
        }
        else if (Board.count(AIChar) > Board.count(MoveChecker.otherColor(AIChar))){
            System.out.println("AI wins!");
        }

        else{
            System.out.println("Draw!");
        }
        
    }

    
}
