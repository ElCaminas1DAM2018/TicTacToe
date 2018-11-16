public class Board {
	
	public static final int NUM_ROWS = 3;
	public static final char PLAYER1 = 'X';
	public static final char PLAYER2 = 'O';
	public static final char VOID = '.';
	
	private char[][] board;
	private int turn;
	private GameState gameState;
	
	public Board() {
		board = new char[NUM_ROWS][NUM_ROWS];
		for (int i=0; i<NUM_ROWS; i++) {
			for (int j=0; j<NUM_ROWS; j++) {
				board[i][j] = VOID;
			}
		}
		turn = 1;
		gameState = GameState.PLAYING;
	}
	
	public boolean shoot(int row, int col) {
		if (row <0 || row>= NUM_ROWS || col <0 || col >= NUM_ROWS) {
			return false;
		}
		if (board[row][col] != VOID) {
			return false;
		}
		if (turn == 1) {
			board[row][col] = PLAYER1;
		} else {
			board[row][col] = PLAYER2;
		}
		return true;
	}
	
	public void changeTurn() {
		if (turn == 1) {
			turn = 2;
		} else {
			turn = 1;
		}
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int row = 0; row < NUM_ROWS; row ++) {
			for (int col = 0; col < NUM_ROWS; col ++) {
				s = s + board[row][col] + " ";				
			}
			s = s + "\n";
		}
		return s;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public char getPlayerSymbol() {
		if (turn == 1) {
			return PLAYER1;
		} else {
			return PLAYER2;
		}
	}
	
	public GameState calculateGameState() {		
		checkRows();
		checkColumns();
		checkDiagonals();
		if (gameState != GameState.WINNER1 && 
				gameState != GameState.WINNER2) {
			checkDraw();
		}
		return gameState;		
	}

	private void checkDraw() {
	
		for (int row = 0; row < NUM_ROWS; row ++) {
			for (int col = 0; col < NUM_ROWS; col ++) {
				if (board[row][col] == VOID) {
					gameState = GameState.PLAYING;
					return;
				}
			}
		}
		gameState = GameState.DRAW;
		
	}

	private void checkDiagonals() {
		int count = 0;
		char symbol = getPlayerSymbol();
		for (int i = 0; i < NUM_ROWS; i++) {
			if (board[i][i] == symbol) {
				count ++;
			}
		}
		if (count == NUM_ROWS) {
			gameState = turn == 1 ? 
					GameState.WINNER1 : 
					GameState.WINNER2;
			return;
		}
		
		count = 0;
		int	col = NUM_ROWS -1;
		for (int row = 0; row < NUM_ROWS; row ++) {
			if (board[row][col] == symbol) {
				count ++;
			}	
			col --;
		}
		if (count == NUM_ROWS) {
			gameState = turn == 1 ? 
					GameState.WINNER1 : 
					GameState.WINNER2;
		}		
	}

	private void checkRows() {
		int count = 0;
		char symbol = getPlayerSymbol();
		
		for (int row = 0; row < NUM_ROWS; row ++) {
			count = 0;
			for (int col = 0; col < NUM_ROWS; col ++) {
				if (board[row][col] == symbol) {
					count ++;
				}
			}
			if (count == NUM_ROWS) {
				gameState = turn == 1 ? 
						GameState.WINNER1 : 
						GameState.WINNER2;
			}
		}
	}
	
	private void checkColumns() {
		int count = 0;
		char symbol = getPlayerSymbol();
		
		for (int col = 0; col < NUM_ROWS; col ++) {
			count = 0;
			for (int row = 0; row < NUM_ROWS; row ++) {
				if (board[row][col] == symbol) {
					count ++;
				}
			}
			if (count == NUM_ROWS) {
				gameState = turn == 1 ? 
						GameState.WINNER1 : 
						GameState.WINNER2;
			}
		}
		
		
	}
	
}
