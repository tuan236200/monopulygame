package Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Player extends JPanel {

	private int playerNumber;
	JLabel lblPlayerNumber;
	static int totalPlayers = 0; // we might need this number later on
	static HashMap<Integer, Integer> ledger= new HashMap<>();
	private int currentSquareNumber = 0; // where player is currently located on (0 - 31). initially zero
	private ArrayList<Integer> titleDeeds = new ArrayList<Integer>(); // squares that the player has
	private int wallet = 1000; // initial money

	public ArrayList<Integer> getTitleDeeds() {
		return titleDeeds;
	}
	
	public int getWallet() {
		return wallet;
	}

	public void withdrawFromWallet(int withdrawAmount) {
		if(withdrawAmount > wallet) {
			setVisible(false);
			System.out.println("Player "+ playerNumber + " went bankrupt!");
		} else {
			wallet -= withdrawAmount;
		}
	}

	public void depositToWallet(int depositAmount) {
		wallet += depositAmount;
		System.out.println("Payday for player "+getPlayerNumber()+". You earned $200!");
	}

	public int getCurrentSquareNumber() {
		return currentSquareNumber;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public boolean hasTitleDeed(int squareNumber) {
		return titleDeeds.contains(squareNumber) ? true : false;
	}

	public void buyTitleDeed(int squareNumber) {
		if(ledger.containsKey(squareNumber)) {
			System.out.println("It's already bought by someone. You cannot by here.");
		} else {
			titleDeeds.add(this.getCurrentSquareNumber());
			ledger.put(squareNumber, this.getPlayerNumber()); // everytime a player buys a title deed, it is written in ledger, for example square 1 belongs to player 2
		
		}
	}

	public Player(int xCoord, int yCoord, int width, int height) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(xCoord, yCoord, 20, 20);
		this.setLayout(null);
	}

	public Player(int playerNumber, Color color) {
		this.playerNumber = playerNumber;
		this.setBackground(color);
		lblPlayerNumber = new JLabel(""+playerNumber);
		lblPlayerNumber.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblPlayerNumber.setForeground(Color.WHITE);
		this.add(lblPlayerNumber); 
		this.setBounds(playerNumber*30, 33, 20, 28); // need to fix here for adjustable player numbers
		totalPlayers++;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	int x_top = 6;
	int y_top = 6;
	int x_right =  706;
    int y_right = 106;
	int x_bot = 606;
	int y_bot = 666;
	int x_left = 16;
	int y_left = 586;

	int[] xLocationsOfPlayer1 = {16 + 25,
		126 + 25,
		206 + 25,
		306 + 25,
		386 + 25,
		466 + 25,
		546 + 25,
		626 + 25,
		706 + 25,
		x_right + 25,
		x_right + 25,
		x_right + 25,
		x_right + 25,
		x_right + 25,
		x_right + 25,
		x_right + 25,
		x_right + 25,
		626 + 25,
		546 + 25,
		466 + 25,
		386 + 25,
		306 + 25,
		206 + 25,
		126 + 25,
		16 + 25,
		x_left + 25,
		x_left + 25,
		x_left + 25,
		x_left + 25,
		x_left + 25,
		x_left + 25,
		x_left + 25};

	int[] yLocationsOfPlayer1 = {y_top + 27,
		y_top + 27,
		y_top + 27,
		y_top + 27,
		y_top + 27,
		y_top + 27,
		y_top + 27,
		y_top + 27,
		y_top + 27,
		106 + 27,
		186 + 27,
		266 + 27,
		346 + 27,
		426 + 27,
		506 + 27,
		586 + 27,
		666 + 27,
		y_bot + 27,
		y_bot + 27,
		y_bot + 27,
		y_bot + 27,
		y_bot + 27,
		y_bot + 27,
		y_bot + 27,
		y_bot + 27,
		586 + 27,
		506 + 27,
		426 + 27,
		346 + 27,
		266 + 27,
		186 + 27,
		106 + 27};
	
	int[] xLocationsOfPlayer2 = {16 + 55,
		126 + 55,
		206 + 55,
		306 + 55,
		386 + 55,
		466 + 55,
		546 + 55,
		626 + 55,
		706 + 55,
		x_right + 55,
		x_right + 55,
		x_right + 55,
		x_right + 55,
		x_right + 55,
		x_right + 55,
		x_right + 55,
		x_right + 55,
		626 + 55,
		546 + 55,
		466 + 55,
		386 + 55,
		306 + 55,
		206 + 55,
		126 + 55,
		16 + 55,
		x_left + 55,
		x_left + 55,
		x_left + 55,
		x_left + 55,
		x_left + 55,
		x_left + 55,
		x_left + 55};

		int[] yLocationsOfPlayer2 = {y_top + 27,
			y_top + 27,
			y_top + 27,
			y_top + 27,
			y_top + 27,
			y_top + 27,
			y_top + 27,
			y_top + 27,
			y_top + 27,
			106 + 27,
			186 + 27,
			266 + 27,
			346 + 27,
			426 + 27,
			506 + 27,
			586 + 27,
			666 + 27,
			y_bot + 27,
			y_bot + 27,
			y_bot + 27,
			y_bot + 27,
			y_bot + 27,
			y_bot + 27,
			y_bot + 27,
			y_bot + 27,
			586 + 27,
			506 + 27,
			426 + 27,
			346 + 27,
			266 + 27,
			186 + 27,
			106 + 27};
	

	public void move(int dicesTotal) {
		if(currentSquareNumber + dicesTotal > 31) {
			depositToWallet(200);
		}
		int targetSquare = (currentSquareNumber + dicesTotal) % 32; //%20
		currentSquareNumber = targetSquare;
		
		if(Board.nowPlaying == 0) {
			if(targetSquare == 24) {
				this.setLocation(xLocationsOfPlayer1[8], yLocationsOfPlayer1[8]);
				currentSquareNumber = 8;
			} else 
				this.setLocation(xLocationsOfPlayer1[targetSquare], yLocationsOfPlayer1[targetSquare]);
		} else {
			if(targetSquare == 24) {
				this.setLocation(xLocationsOfPlayer2[8], yLocationsOfPlayer2[8]);
				currentSquareNumber = 8;
			} else
				this.setLocation(xLocationsOfPlayer2[targetSquare], yLocationsOfPlayer2[targetSquare]);
		}
		
		if(ledger.containsKey(this.getCurrentSquareNumber())) {
			Board.infoConsole.setText("This property belongs to player "+ledger.get(this.getCurrentSquareNumber()));
		}
		//ledger.put(this.getCurrentSquareNumber(), this.getPlayerNumber());
	}
}
