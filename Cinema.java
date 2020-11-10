package cinema;

import java.util.Scanner;
import java.util.Arrays;

public class Cinema {
	static int rows;
	static int seatsInRow;

	static int rowNumber;
	static int seatNumber;

	static char[][] seatState;
	static int seats;
	static int purchasedTickets = 0;
	static int currentIncome = 0;
	static int totalIncome;

	static final Scanner SC = new Scanner(System.in);

	public static void main(String[] args) {
		initializeSeats();
		showMenu();
	}

	static void initializeSeats() {
		System.out.println("Enter the number of rows:");
		rows = SC.nextInt();

		System.out.println("Enter the number of seats in each row:");
		seatsInRow = SC.nextInt();

		seats = rows * seatsInRow;
		seatState = new char[rows][seatsInRow];
		calculateTotalIncome();

		for (char[] seat : seatState) {
			Arrays.fill(seat, 'S');
		}
	}

	static void showMenu() {
		System.out.println("1. Show the seats");
		System.out.println("2. Buy a ticket");
		System.out.println("3. Statistics");
		System.out.println("0. Exit");
		chooseAction();
	}

	static void chooseAction() {
		int action = SC.nextInt();
		switch (action) {
			case 1:
				printSeats();
				showMenu();
				break;
			case 2:
				buyTicket();
				showMenu();
				break;
			case 3:
				showStatistics();
				showMenu();
			case 0:
				System.exit(0);
				break;
			default:
				showMenu();
				break;
		}
	}

	static void chooseSeat() {
		boolean availableSeat;
		do {
			availableSeat = true;
			System.out.println("Enter a row number:");
			rowNumber = SC.nextInt();
			System.out.println("Enter a seat number in that row:");
			seatNumber = SC.nextInt();

			if (rowNumber > rows || seatNumber > seatsInRow || rowNumber < 1 || seatNumber < 1) {
				availableSeat = false;
				System.out.println("Wrong input!");
			} else if (seatState[rowNumber - 1][seatNumber - 1] == 'B') {
				availableSeat = false;
				System.out.println("That ticket has already been purchased!");
			}
		} while (!availableSeat);
	}

	static void printSeats() {
		System.out.println("Cinema:");

		System.out.print("  ");
		for (int i = 1; i <= seatsInRow; i++) {
			System.out.print(i);
			if (i < seatsInRow) {
				System.out.print(" ");
			}
		}
		System.out.println();

		for (int i = 1; i <= rows; i++) {
			System.out.print(i + " ");
			for (int j = 1; j <= seatsInRow; j++) {
				System.out.print(seatState[i - 1][j - 1]);
				if (j < seatsInRow) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	static void buyTicket() {
		chooseSeat();
		seatState[rowNumber - 1][seatNumber - 1] = 'B';
		int ticketPrice;

		if (seats < 60) {
			ticketPrice = 10;
		} else {
			ticketPrice = rowNumber <= rows / 2 ? 10 : 8;
		}

		System.out.println("Ticket price: $" + ticketPrice);
		purchasedTickets += 1;
		currentIncome += ticketPrice;
	}

	static void showStatistics() {
		System.out.printf("Number of purchased tickets: %d%n", purchasedTickets);
		System.out.printf("Percentage: %.2f%%%n", (float) purchasedTickets / seats * 100);
		System.out.printf("Current income: $%d%n", currentIncome);
		System.out.printf("Total income: $%d%n", totalIncome);
	}

	static void calculateTotalIncome() {
		if (seats < 60) {
			totalIncome = seats * 10;
		} else {
			totalIncome = (rows / 2) * seatsInRow * 10 + (rows - rows / 2) * seatsInRow * 8;
		}
	}
}
