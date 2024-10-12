public class WordAndPlayer {
    private Word word;
    private Player player;

    public WordAndPlayer(Player player, Word word) {
        this.word = word;
        this.player = player;
    }

    public Word getWord() {
        return word;
    }

    public Player getPlayer() {
        return player;
    }
}
