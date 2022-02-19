import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("\nWITAJ W GRZE HANGMAN \n \nWYBÓR TRYBU GRY\n 1 - TRYB JEDNOOSOBOWY\n 2 - TRYB DWUOSOBOWY ");
        String players = keyboard.nextLine();

        String word;

        if (players.equals("1")) {
            Scanner scanner = new Scanner(new File("E:/projekt/slowa.txt"));
            List<String> words = new ArrayList<>();

            while (scanner.hasNext()) {
                words.add(scanner.nextLine());
            }

            Random rand = new Random();
            word = words.get(rand.nextInt(words.size()));
        }
        else {
            System.out.println("Gracz 1 wpisuje swoje hasło");
            word = keyboard.nextLine();

            System.out.println("Gracz 2 odgaduje hasło. Powodzenia!");
        }



        List<Character> playerGuesses = new ArrayList<>();

        Integer wrongCount = 0;

        while(true) {
            printHangedMan(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("Przegrywasz!");
                System.out.println("Twoje hasło brzmiało: " + word);
                break;
            }

            printWordState(word, playerGuesses);
            if (!getPlayerGuess(keyboard, word, playerGuesses)) {
                wrongCount++;

            }

            if(printWordState(word, playerGuesses)) {
                System.out.println("Wygrywasz!!!");
                break;
            }

            System.out.println("Spróbuj odgadnąć całe hasło lub kliknij enter");
            if(keyboard.nextLine().equals(word)) {
                System.out.println("Wygrywasz!!!");
                break;
            }
            else {
                System.out.println("Źle, spróbuj ponownie!");
            }
        }
    }

    private static void printHangedMan(Integer wrongCount) {
        System.out.println(" -------");
        System.out.println(" |     |");
        if (wrongCount >= 1) {
            System.out.println(" O");
        }

        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3) {
                System.out.println("/");
            }
            else {
                System.out.println("");
            }
        }

        if (wrongCount >= 4) {
            System.out.println(" |");

        }

        if (wrongCount >= 5) {
            System.out.print("/ ");
            if (wrongCount >= 6) {
                System.out.println("\\");
            }
            else {
                System.out.println("");

            }
        }
        System.out.println("");
        System.out.println("");
    }

    private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        System.out.println("Prosze wpisać literkę: ");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println();

        return (word.length() == correctCount);
    }
}
