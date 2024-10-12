import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    public void playGame() {
        List<WordAndPlayer> wordAndPlayerList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the game. Please enter the number of players.");
        String numberOfPlayers = scanner.nextLine();
        if (isPlayerNumberValidInput(numberOfPlayers)) {
            for (int i = 1; i <= Integer.parseInt(numberOfPlayers); i++) {
                System.out.println("Player " + i + ", enter your name!");
                wordAndPlayerList.add(new WordAndPlayer(new Player(scanner.nextLine()), new Word()));
            }
        } else {
            System.exit(0);
        }

        while (true) {

            for (WordAndPlayer wap: wordAndPlayerList) {

                while (true) {
                    System.out.println("Guess a new letter, " + wap.getPlayer().getName() + "!");
                    wap.getWord().printCurrentLetters();
                    String input = scanner.nextLine();
                    if (wap.getWord().isValidInput(input) && !wap.getWord().didUserAlreadyEnterThisLetter(input)) {
                        if (wap.getWord().checkIfLetterIsPresentInWordAndUpdateBooleanArray(input)) {
                            System.out.println("Congrats, " + wap.getPlayer().getName() + ", you guessed correctly!");
                            wap.getWord().printCurrentLetters();
                            wap.getWord().checkPlayerWin();
                            break;
                        }
                        System.out.println("The letter you guessed was not part of the word :(");
                        wap.getWord().playerFails();
                        break;
                    }
                }
            }
        }
    }

    public boolean isPlayerNumberValidInput(String input) {
        String playerNumber = "^[1-9]$";
        Pattern pattern = Pattern.compile(playerNumber);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            System.out.println("The input is not valid.");
            return false;
        }
        return true;
    }
}


// kick player from the list if he is dead
// you are the last one standing if only one player is left
// players can only win after every player had the same amount of tries
//
