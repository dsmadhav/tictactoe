import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();//creating a static for further usage of lists storing player positions
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();////creating a static for further usage of lists storing cpu positions

	public static void main(String[] args) {
		
		//Design of the gameboard
		
		char[][] gameBoard = {{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '}};
		
		//game loop
		
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter position(1-9):");
			int playerPos=scan.nextInt();
			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {   //checking that the player position does'nt overwrite
				System.out.println("Enter a correct number.Position is already taken");
				playerPos = scan.nextInt();
			}
			
		//player input	
			
			placePiece(gameBoard, playerPos, "player");
			String result = checkWinner();
			if (result.length() > 0) {
				System.out.print(result);
				break;
			}
		
		//cpu input
			
			Random rand = new Random();//random position generator
			int cpuPos = rand.nextInt(9) + 1;
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {		//checking that the cpu position does'nt overwrite	
				cpuPos = rand.nextInt(9) + 1;
			}
			placePiece(gameBoard, cpuPos, "cpu");
			
			printGameBoard(gameBoard);
			
			result = checkWinner();
			if (result.length() > 0) {
				System.out.print(result);
				break;
			}
		
			
			
		}
		
	}
	
	public static void printGameBoard(char[][] gameBoard) {                    //printing gameboard
		for (char[] row : gameBoard) {
			for(char c:row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(char[][] gameBoard, int pos, String user) {   //placing symbols in the board
		
		char symbol = ' ';
		
		if(user.equals("player")) {        //printing x for player
			symbol = 'X';
			playerPositions.add(pos);
		} else if(user.equals("cpu")) {    //printing o for player   
			symbol = 'O';
			cpuPositions.add(pos);
		}
			
		switch(pos) {                      //entering the symbol in position according to number
			case 1:
				gameBoard[0][0]=symbol;
				break;
			case 2:
				gameBoard[0][2]=symbol;
				break;
			case 3:
				gameBoard[0][4]=symbol;
				break;
			case 4:
				gameBoard[2][0]=symbol;
				break;
			case 5:
				gameBoard[2][2]=symbol;
				break;
			case 6:
				gameBoard[2][4]=symbol;
				break;
			case 7:
				gameBoard[4][0]=symbol;
				break;
			case 8:
				gameBoard[4][2]=symbol;
				break;
			case 9:
				gameBoard[4][4]=symbol;
				break;
				
	}
	}
	
	//checking winner
	
	public static String checkWinner() {         
		
		//entering winning positions of the symbols
		
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftcol = Arrays.asList(1, 4, 7);
		List midcol = Arrays.asList(2, 5, 8);
		List rightcol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(3, 5, 7);
		
		//creating a list for checking if the positions of symbols are in winning positions
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftcol);
		winning.add(midcol);
		winning.add(rightcol);
		winning.add(cross1);
		winning.add(cross2);
		
		//loop for checking if any list elements hold 'TRUE' for positions of user and player symbols
		
		for (List l : winning) {
			if(playerPositions.containsAll(l)){
				return "congratulations!";
			} else if(cpuPositions.containsAll(l)) {
				return "you lost";
			} else if ( playerPositions.size()+ cpuPositions.size() == 9 ) {
				return "tie";
			}
		}
		return "";
	}
}
//something