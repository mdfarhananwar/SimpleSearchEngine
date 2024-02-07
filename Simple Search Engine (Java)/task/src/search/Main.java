package search;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;


public class Main {
    private static final int COMMAND_EXIT = 0;
    private static final int COMMAND_SEARCH_A_PERSON = 1;
    private static final int COMMAND_PRINT_ALL_PEOPLE = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> peopleDetails = inputPeopleDetails(scanner, args);
        printMenu();
        menu(scanner, peopleDetails);
    }
    public static void menu(Scanner scanner, List<String> peopleDetails) {
        while(true) {
            int command = -1;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Incorrect option! Try again.");
            }
            if (command == COMMAND_SEARCH_A_PERSON) {
                searchQuery(scanner, peopleDetails);
            } else if (command == COMMAND_PRINT_ALL_PEOPLE) {
                printAllPeople(peopleDetails);
            } else if (command == COMMAND_EXIT) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.println("Incorrect option! Try again.");
            }
        }
    }

    public static List<String> inputPeopleDetails(Scanner scanner, String[] args) {
        List<String> peopleDetails = new ArrayList<>();
        if (args[0].equals("--data")) {
            String filePath = args[1]; // Provide the path to your text file
            try (Scanner scannerFile = new Scanner(new File(filePath))) {
                while (scannerFile.hasNextLine()) {
                    String line = scannerFile.nextLine();
                    // Process each line here
                    peopleDetails.add(line);
                    //System.out.println(line);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Fail");
            }
        } else {

            System.out.println("Enter the number of people:");
            int numberOfPeople = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter all people:");
            for (int i = 0; i < numberOfPeople; i++) {

                peopleDetails.add(scanner.nextLine());
            }
        }
        //searchQuery(scanner, peopleDetails);
        return peopleDetails;
    }

    public static void searchQuery(Scanner scanner, List<String> peopleDetails) {
        System.out.println("Enter a name or email to search all suitable people.");
        boolean isPersonFound = false;
        String query = scanner.nextLine().trim();
        for (String people : peopleDetails) {
            if(people.toLowerCase().contains(query.toLowerCase())) {
                if(!isPersonFound) {
                    isPersonFound = true;
                }
                System.out.println(people);
            }
        }
        if (!isPersonFound) {
            System.out.println("No matching people found.");
        }
    }

    public static void printAllPeople(List<String> peopleDetails) {
        System.out.println("=== List of people ===");
        for (String person : peopleDetails) {
            System.out.println(person);
        }
    }
    public static void printMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }
}


