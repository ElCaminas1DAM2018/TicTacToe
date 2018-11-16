import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {		
		
		boolean gameOver = false;
		Scanner input = new Scanner(System.in);
		Board board = new Board();
		int row, col;
		boolean isRight = false;
		
		while (! gameOver) {
			
			while (! isRight) {
				System.out.println(board);
				System.out.println("TURN: " + board.getTurn() + "  (" +
									board.getPlayerSymbol() + ")");		
				System.out.print("Enter row: ");
				row = input.nextInt();
				System.out.print("Enter col: ");
				col = input.nextInt();
				isRight = board.shoot(row -1, col -1);
			}
			
			GameState state = board.calculateGameState();
			
			switch (state) {
			case DRAW:
				System.out.println("You draw");
				gameOver = true;
				break;
			case WINNER1:
				System.out.println("Winner player 1 (" 
						+ board.getPlayerSymbol() + ")");
				gameOver = true;
				break;
			case WINNER2:
				System.out.println("Winner player 2(" 
						+ board.getPlayerSymbol() + ")");
				gameOver = true;
				break;
			default:
				break;
			}
			
			
			board.changeTurn();
		}

	}

}
