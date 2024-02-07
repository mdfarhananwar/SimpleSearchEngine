package search;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //searchIndex(scanner);
        inputPeopleDetails(scanner);

    }
    public static void searchIndex(Scanner scanner) {
        String[] listOfWords = scanner.nextLine().split(" ");
        String wordToSearch = scanner.nextLine();
        int index = -1;
        for (int i = 0; i < listOfWords.length; i++) {
            String word = listOfWords[i];
            if (word.equalsIgnoreCase(wordToSearch)) {
                index = i + 1;
            }
        }
        if (index > 0) {
            System.out.println(index);
        } else {
            System.out.println("Not found");
        }
    }

    public static void inputPeopleDetails(Scanner scanner) {
        System.out.println("Enter the number of people:");
        int numberOfPeople = Integer.parseInt(scanner.nextLine());
        String[] peopleDetails = new String[numberOfPeople];
        System.out.println("Enter all people:");
        for (int i = 0; i < numberOfPeople; i++) {
            peopleDetails[i] = scanner.nextLine();
        }
        searchQuery(scanner, peopleDetails);
    }

    public static void searchQuery(Scanner scanner, String[] peopleDetails) {
        System.out.println("Enter the number of search queries:");

        int numberOfQueries = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfQueries; i++) {
            boolean isPersonFound = false;
            System.out.println("Enter data to search people:");
            String query = scanner.nextLine().trim();
            for (String people : peopleDetails) {
                if(people.toLowerCase().contains(query.toLowerCase())) {
                    if(!isPersonFound) {
                        System.out.println("Found people:");
                        isPersonFound = true;
                    }
                    System.out.println(people);
                }
            }
            if (!isPersonFound) {
                System.out.println("No matching people found.");
            }
        }
    }
}


