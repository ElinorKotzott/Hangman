import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word {
    List<String> wordInLetters;
    List<Boolean> isLetterGuessed;
    List<String> guessedLetters = new ArrayList<>();
    String word;
    private int failedAttempts = 0;

    public Word() {
        if (MyFileReader.randomWordsList() != null) {
            List<String> randomWords = MyFileReader.randomWordsList();
            word = chooseWordFromList(randomWords);
            fromStringToStringListByCharacter(word);
            initializeBooleanListToFalse();
            String revealThisLetter = String.valueOf(word.charAt((int) (Math.random() * wordInLetters.size())));
            checkIfLetterIsPresentInWordAndUpdateBooleanArray(revealThisLetter);
        } else {
            System.out.println("There has been an error. The program can't continue.");
            System.exit(0);
        }
    }

    private String chooseWordFromList(List<String> randomWords) {
        return randomWords.get((int) (Math.random() * randomWords.size()));
    }

    private void fromStringToStringListByCharacter(String wordToBeGuessed) {
        wordInLetters = new ArrayList<>();
        for (int i = 0; i < wordToBeGuessed.length(); i++) {
            wordInLetters.add(String.valueOf(wordToBeGuessed.charAt(i)));
        }
    }

    private void initializeBooleanListToFalse() {
        isLetterGuessed = new ArrayList<>();
        for (int i = 0; i < wordInLetters.size(); i++) {
            isLetterGuessed.add(Boolean.FALSE);
        }

    }

    public boolean checkIfLetterIsPresentInWordAndUpdateBooleanArray(String guessedLetter) {
        guessedLetters.add(guessedLetter);

        boolean isPresent = false;
        for (int i = 0; i < wordInLetters.size(); i++) {
            if (guessedLetter.equalsIgnoreCase(wordInLetters.get(i))) {
                isLetterGuessed.set(i, Boolean.TRUE);
                isPresent = true;
            }
        }
        return isPresent;
    }

    public boolean didUserAlreadyEnterThisLetter (String guessedLetter) {
        if (guessedLetters.contains(guessedLetter)) {
            System.out.println("You already guessed this letter.");
            return true;
        }
        return false;
    }

    public void printCurrentLetters() {
        for (int i = 0; i < wordInLetters.size(); i++) {
            if (isLetterGuessed.get(i)) {
                System.out.print(wordInLetters.get(i));
            } else {
                System.out.print("_");
            }
        }
        System.out.println(" ");
    }

    public void checkPlayerWin() {
        for (int i = 0; i < isLetterGuessed.size(); i++) {
            if (!isLetterGuessed.get(i)) {
                return;
            }
        }
        System.out.println("Congrats, you won!");
        System.exit(0);
    }

    public boolean isValidInput(String input) {
        String letters = "^[a-zA-Z]$";
        Pattern pattern = Pattern.compile(letters);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            System.out.println("The input is not valid.");
            return false;
        }
        return true;
    }


    private void drawHangman(int failedAttempts) {
        switch (failedAttempts) {
            case 1:
                System.out.println(" +---+");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("    ===");
                break;
            case 2:
                System.out.println(" +---+");
                System.out.println(" O   |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("    ===");
                break;
            case 3:
                System.out.println(" +---+");
                System.out.println(" O   |");
                System.out.println(" |   |");
                System.out.println("     |");
                System.out.println("    ===");
                break;
            case 4:
                System.out.println(" +---+");
                System.out.println(" O   |");
                System.out.println("/|   |");
                System.out.println("     |");
                System.out.println("    ===");
                break;
            case 5:
                System.out.println(" +---+");
                System.out.println(" O   |");
                System.out.println("/|\\  |");
                System.out.println("     |");
                System.out.println("    ===");
                break;
            case 6:
                System.out.println(" +---+");
                System.out.println(" O   |");
                System.out.println("/|\\  |");
                System.out.println("/    |");
                System.out.println("    ===");
                break;
            case 7:
                System.out.println(" +---+");
                System.out.println(" O   |");
                System.out.println("/|\\  |");
                System.out.println("/ \\  |");
                System.out.println("    ===");
                break;
            default:
                System.out.println("Invalid number of attempts.");
        }
    }

    public void playerFails() {
        failedAttempts++;
        drawHangman(failedAttempts);
        if (failedAttempts == 7) {
            System.out.println("You died. Your word was '" + word + "'.");
        }
    }


}
