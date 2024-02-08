package search;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static final int COMMAND_EXIT = 0;
    private static final int COMMAND_SEARCH_A_PERSON = 1;
    private static final int COMMAND_PRINT_ALL_PEOPLE = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Set<Integer>> invertedIndex = new HashMap<>();
        List<String> peopleDetails = inputPeopleDetails(scanner, args, invertedIndex);
        printMenu();
        menu(scanner, peopleDetails, invertedIndex);
    }

    public static void menu(Scanner scanner, List<String> peopleDetails, Map<String, Set<Integer>> invertedIndex) {
        while (true) {
            int command = -1;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Incorrect option! Try again.");
            }
            if (command == COMMAND_SEARCH_A_PERSON) {
                searchQuery(scanner, peopleDetails, invertedIndex);
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

    public static List<String> inputPeopleDetails(Scanner scanner, String[] args, Map<String, Set<Integer>> invertedIndex) {
        List<String> peopleDetails = new ArrayList<>();
        if (args[0].equals("--data")) {
            String filePath = args[1]; // Provide the path to your text file
            try (Scanner scannerFile = new Scanner(new File(filePath))) {
                int documentId = 0;
                while (scannerFile.hasNextLine()) {
                    String line = scannerFile.nextLine();
                    peopleDetails.add(line);
                    updateInvertedIndex(line, ++documentId, invertedIndex);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Fail");
            }
        } else {
            System.out.println("Enter the number of people:");
            int numberOfPeople = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter all people:");
            for (int i = 0; i < numberOfPeople; i++) {
                String line = scanner.nextLine();
                peopleDetails.add(line);
                updateInvertedIndex(line, i + 1, invertedIndex);
            }
        }
        return peopleDetails;
    }

    public static void updateInvertedIndex(String line, int documentId, Map<String, Set<Integer>> invertedIndex) {
        String[] terms = line.split("\\s+");
        for (String term : terms) {
            invertedIndex.putIfAbsent(term.toLowerCase(), new HashSet<>());
            invertedIndex.get(term.toLowerCase()).add(documentId);
        }
    }

    public static void searchQuery(Scanner scanner, List<String> peopleDetails, Map<String, Set<Integer>> invertedIndex) {
        System.out.println("Enter a name or email to search all suitable people:");
        String query = scanner.nextLine().trim().toLowerCase();
        Set<Integer> matchingDocuments = invertedIndex.getOrDefault(query, Collections.emptySet());
        if (!matchingDocuments.isEmpty()) {
            System.out.println("Found people:");
            for (int documentId : matchingDocuments) {
                System.out.println(peopleDetails.get(documentId - 1)); // Print document details
            }
        } else {
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
