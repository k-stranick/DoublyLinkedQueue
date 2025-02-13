package dtcc.itn262;

import java.util.Scanner;

public class AirportController {

	private Deque<String> runway1;
	private Deque<String> runway2;
	private int runway;
	private boolean running;

	public AirportController() {
		runway1 = new MyDeque<>();
		runway2 = new MyDeque<>();
		runway = 1; // Start with Runway 1
		running = true;

		// Initialize runways with planes
		runway1.offerLast("AA1111");
		runway1.offerLast("AA2222");
		runway1.offerLast("AA3333");
		runway1.offerLast("AA4444");

		runway2.offerLast("AA5555");
		runway2.offerLast("AA6666");
	}

	public void startSimulation() {
		Scanner scanner = new Scanner(System.in);

		while (running) {
			// Display the current status
			displayRunwayStatus();

			// Display the menu
			System.out.println("\n### MENU ###");
			System.out.println("1. Check First and Last plane in Current Runway");
			System.out.println("2. Approve next plane");
			System.out.println("3. Emergency override from Runway 1");
			System.out.println("4. Emergency override from Runway 2");
			System.out.println("5. Add plane to back of Queue (selected runway)");
			System.out.println("6. Add plane to front of Queue (selected runway)");
			System.out.println("7. Exit simulation");
			System.out.print("\nEnter choice (1-7): ");
			String choice = scanner.nextLine();

			switch (choice) {
				case "1":
					checkPlaneInQueue(true);
					checkPlaneInQueue(false);
					break;
				case "2":
					approveNextPlane();
					break;
				case "3":
					emergencyOverride(runway1, 1);
					break;
				case "4":
					emergencyOverride(runway2, 2);
					break;
				case "5":
					addPlaneToSelectedRunwayEnd();
					break;
				case "6":
					addPlaneToSelectedRunwayFront();
					break;
				case "7":
					running = false;
					System.out.println("Exiting simulation.");
					break;
				default:
					System.out.println("Invalid choice. Please enter a number between 1 and 4.");
			}

			// Check if both runways are empty
			if (runway1.size() == 0 && runway2.size() == 0) {
				System.out.println("\nBoth runways are empty. All planes have taken off!");
				running = false;
			}
		}
		scanner.close();
	}

	private void checkPlaneInQueue(boolean checkFirst) {
		String plane = null;
		if (runway == 1 && runway1.size() > 0) {
			plane = checkFirst ? runway1.peekFirst() : runway1.peekLast();
			System.out.println("\n" + (checkFirst ? "Next" : "Last") + " plane in queue on Runway 1: " + plane);
		} else if (runway == 2 && runway2.size() > 0) {
			plane = checkFirst ? runway2.peekFirst() : runway2.peekLast();
			System.out.println("\n" + (checkFirst ? "Next" : "Last") + " plane in queue on Runway 2: " + plane);
		} else {
			System.out.println("\nNo planes are waiting to take off.");
		}
	}

	private void approveNextPlane() {
		String plane = null;
		if (runway == 1 && runway1.size() > 0) {
			plane = runway1.pollFirst();
			System.out.println("\nTaking off from Runway 1: " + plane);
			toggleNextRunway();
		} else if (runway == 2 && runway2.size() > 0) {
			plane = runway2.pollFirst();
			System.out.println("\nTaking off from Runway 2: " + plane);
			toggleNextRunway();
		} else if (runway1.size() > 0) {
			plane = runway1.pollFirst();
			System.out.println("\nTaking off from Runway 1: " + plane);
			runway = 2;
		} else if (runway2.size() > 0) {
			plane = runway2.pollFirst();
			System.out.println("\nTaking off from Runway 2: " + plane);
			runway = 1;
		} else {
			System.out.println("\nNo planes are waiting to take off.");
		}
	}

	private void toggleNextRunway() {
		if (runway1.size() > 0 && runway2.size() > 0) {
			runway = (runway == 1) ? 2 : 1;
		} else if (runway1.size() == 0 && runway2.size() > 0) {
			runway = 2;
		} else if (runway2.size() == 0 && runway1.size() > 0) {
			runway = 1;
		}
	}

	private void emergencyOverride(Deque<String> runway, int runwayNumber) {
		if (runway.size() == 0) {
			System.out.println("\nRunway " + runwayNumber + " is empty. No planes to take off.");
			return;
		}
		String plane = runway.pollLast();
		System.out.println("\nEmergency takeoff from Runway " + runwayNumber + ": " + plane);
	}

	private void displayRunwayStatus() {
		System.out.println("\n### AIRPORT STATUS ###");
		System.out.println("Current runway: " + runway);
		System.out.println("Runway1: " + (runway1.size() > 0 ? runwayToString(runway1) : "No planes waiting"));
		System.out.println("Runway2: " + (runway2.size() > 0 ? runwayToString(runway2) : "No planes waiting"));
	}

	private String runwayToString(Deque<String> runway) {
		if (runway instanceof MyDeque) {
			return runway.toString();
		} else {
			return "Unknown runway status";
		}
	}

	public void addPlaneToSelectedRunwayEnd() {
		Scanner scanner = new Scanner(System.in);

		// Prompt the user to select a runway
		System.out.print("Enter the runway number (1 or 2) to add a plane: ");
		String runwayChoice = scanner.nextLine();

		// Determine the runway based on user input
		Deque<String> selectedRunway;
		int runwayNumber;
		if (runwayChoice.equals("1")) {
			selectedRunway = runway1;
			runwayNumber = 1;
		} else if (runwayChoice.equals("2")) {
			selectedRunway = runway2;
			runwayNumber = 2;
		} else {
			System.out.println("Invalid runway number. Please enter 1 or 2.");
			return; // Exit the method if the choice is invalid
		}

		// Prompt the user to enter the plane ID
		System.out.print("Enter plane ID to add to Runway " + runwayNumber + ": ");
		String newPlane = scanner.nextLine();
		selectedRunway.offerLast(newPlane);
		System.out.println("Plane " + newPlane + " added to Runway " + runwayNumber + ".");
	}

	public void addPlaneToSelectedRunwayFront() {
		Scanner scanner = new Scanner(System.in);

		// Prompt the user to select a runway
		System.out.print("Enter the runway number (1 or 2) to add a plane: ");
		String runwayChoice = scanner.nextLine();

		// Determine the runway based on user input
		Deque<String> selectedRunway;
		int runwayNumber;
		if (runwayChoice.equals("1")) {
			selectedRunway = runway1;
			runwayNumber = 1;
		} else if (runwayChoice.equals("2")) {
			selectedRunway = runway2;
			runwayNumber = 2;
		} else {
			System.out.println("Invalid runway number. Please enter 1 or 2.");
			return; // Exit the method if the choice is invalid
		}

		// Prompt the user to enter the plane ID
		System.out.print("Enter plane ID to add to Runway " + runwayNumber + ": ");
		String newPlane = scanner.nextLine();
		selectedRunway.offerFirst(newPlane);
		System.out.println("Plane " + newPlane + " added to Runway " + runwayNumber + ".");
	}

}

