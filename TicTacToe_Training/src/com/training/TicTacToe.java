package com.training;


import java.util.Scanner;


public class TicTacToe {
	
	private char[][] board;
	private boolean gameStatus = true;
	private String player1, player2;


	private Scanner scanner = new Scanner(System.in);
	
	
	public TicTacToe() {
		System.out.println("Welcome to Tic Tac Toe.");
		this.board = new char[3][3];
		initializePlayers();
		initializeBoard();
		
	}



	private void initializePlayers() {
		System.out.println("Player 1, please enter your name: ");
		this.player1 = scanner.next();
		System.out.println("Player 2, please enter your name: ");
		this.player2 = scanner.next();
		
		
	}



	public boolean makeMove(char mark, int row, int col) {

		mark = Character.toLowerCase(mark);
		if (mark == 'x' || mark == 'o') {

			if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
				if (board[row][col] != '-') {
					return false;
				} else {
					board[row][col] = mark;
					return true;
				}

			} else {
				return false;
			}

		}else {
			return false;
		}
	}
	


	private void initializeBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '-';		
			}	
		}	
	}
	
	public void printBoard() {
		for (int i = 0; i < board.length; i++) {	
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
				if(j<2) {
					System.out.print(" , ");
				}		
			}
			System.out.println();
		}
	}
	

	public boolean isActive() {
		return gameStatus;
	}



	public void askPlayer(String player) {
		int row, col;
		char move;
		do {
			if(player == "p1") {
				System.out.printf("%s (X) enter where you would like to move?  ", getPlayer1() );
				move = 'X';
			}else {
				System.out.printf("%s (O) enter where you would like to move?  ", getPlayer2() );
				move = 'O';
			}
			
			row = scanner.nextInt();
			col = scanner.nextInt();
			
		}while(notValid(row, col));
		
		makeMove(move, row-1, col-1);
		
	}



	private boolean notValid(int row, int col) {
		if(row < 1 || row > 3 || col < 1 || col > 3 || !isEmpty(row, col))
			return true;
		else
			return false;
	}



	private boolean isEmpty(int row, int col) {
		if(board[row-1][col-1] == '-')
			return true;
		else {
			System.out.println("That position is taken, pick again.");
			return false;
		}
			
	}



	public boolean checkForWinner() {
		for(int row = 0; row < board.length; row++) {
			if(board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != '-') {
				declareWinner(board[row][0]);
			}
		}
		for(int col = 0; col < board[0].length; col++) {
			if(board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != '-') {
				declareWinner(board[0][col]);
			}
		}
		if(board[0][0]==board[1][1] && board[1][1]==board[2][2]  && board[0][0]!= '-') {
			declareWinner(board[1][1]);
		}
		  
		if(board[0][2]==board[1][1] && board[1][1]==board[2][0]  && board[0][2]!= '-') {
			declareWinner(board[1][1]);
		}
		return false;
	}
	



	private void declareWinner(char c) {
		if(c == 'x') {
			System.out.println("The winner is " +getPlayer1()+"!");
		}else
		{
			System.out.println("The winner is " +getPlayer2()+"!");
		}
		
		gameStatus = false;
		
		
	}



	public String getPlayer1() {
		return player1;
	}



	public String getPlayer2() {
		return player2;
	}



	public void play(TicTacToe game) {
		game.printBoard();
		int counter = 1;
		
		while(game.isActive()) {
			if(counter % 2 == 0) {
				game.askPlayer("p2");
			}else
				game.askPlayer("p1");
			counter++;
			
			System.out.println("\n");
			game.printBoard();
			game.checkForWinner();
		}
		
	}






	
}
