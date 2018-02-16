// Made by Arta (Alex Kim) , using resources from UWO Department of Computer Science
// BlockedTicTacToe.java implements a feature used for a new type of Tic-Tac-Toe
// It is a standard tic-tac-toe, except the game is played with one of the tiles "blocked", unable to play that block
// Uses TTTDictionary as a data type to keep track of everything
// PlayTTT.java is used to execute

public class BlockedTicTacToe { // construct
    private int size;
    private int line;
    private TTTDictionary dict;
    private char [][] board;
    // setting constants
    private final char HUMAN = 'x';
    private final char COMPUTER = 'o';
    private final char BLOCKED = 'b';
    private final char EMPTY = ' ';
    private final int MAX_LEVELS;

    public BlockedTicTacToe (int size, int line, int max_levels) { // initialize
        this.size = size;
        this.line = line;
        this.MAX_LEVELS = max_levels;
        board = new char[size][size];
        // Making an empty board
        for(int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                board [x][y] = ' ';
            }
        }
    }

    public TTTDictionary createDictionary() { // create a new TTTDictionary
        dict = new TTTDictionary(size);
        return dict;
    }

    public int repeatedConfig(TTTDictionary config) { // checks if config is currently existing in the dictionary
        String element = "";
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                element += board[x][y];
            }
        }
        if(config.get(element) != null) { // if config exists, finds the score of the element
            return config.get(element).getScore();
        }
        else { // if not found, returns -1 always
            return -1;
        }
    }

    public void insertConfig (TTTDictionary config, int score, int level) { // insert a config into the dictionary
        String element = "";
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                element += board[x][y];
            }
        }
        try {
            TTTRecord newRecord = new TTTRecord(element, score, level);
            config.put(newRecord);
        }
        catch (DuplicatedKeyException e) {
            System.out.println("Element already exists");
        }
    }

    public void storePlay(int x, int y, char symbol) { // stores symbol in the board
        board[x][y] = symbol;
    }

    public boolean squareIsEmpty(int x, int y) {
        return (board[x][y] == EMPTY);
    }

    public boolean wins (char symbol) { // checks if won, using horizontal, vertical, and diagonal searches
        int k;
        for(int x = 0; x < size; x++) { // find the number of symbols horizontally
            k = 0;
            for(int y = 0; y < size; y++) {
                if(board[x][y] == symbol) {
                    k++;
                    if(k == line){ // if k is equal to the number needed for a win, return true
                        return true;
                    }
                }
                else { // reset back to 0 for next iteration
                    k = 0;
                }
            }
        }
        for(int y = 0; y < size; y++) { // find the number of symbols vertically
            k = 0;
            for(int x = 0; x < size; x++) {
                if(board[x][y] == symbol) {
                    k++;
                    if(k == line){
                        return true;
                    }
                }
                else { // reset back to 0 for next iteration
                    k = 0;
                }
            }
        }
        for (int y = 0; y < size; y++){ // finding the number of symbols diagonally, first way
            for (int x = 0; x < size;x++){
                if (board[y][x] == symbol){
                    k = 0;
                    int horizontal = x;
                    int vertical = y;
                    while ((vertical < size) && (horizontal >= 0)){
                        if(board[vertical][horizontal] == symbol){
                            k ++;
                        }
                        else{
                            k = 0;
                        }
                        if(k ==line){
                            return true;
                        }
                        else{
                            vertical++;
                            horizontal--;
                        }
                    }
                }
            }
        }
        for(int y = 0; y < size; y ++) { // find the number of symbols diagonally, second way
            for (int x = 0; x < size; x++) {
                if (board[x][y] == symbol) {
                    k = 0;
                    int horizontal = x;
                    int vertical = y;
                    while ((vertical < size) && (horizontal < size)) {
                        if (board[horizontal][vertical] == symbol) {
                            k++;
                        } else {
                            k = 0;
                        }
                        if (k == line) {
                            return true;
                        }
                        else {
                            horizontal++;
                            vertical++;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isDraw() { // checks if it is a draw
        if(wins(HUMAN) && evalBoard() == 1) {
            return false;
        }
        if(wins(COMPUTER) && evalBoard() == 1) {
            return false;
        }
        else {
            for(int x = 0; x < size; x++) { // checks if the board is empty
                for(int y = 0; y < size; y ++) {
                    if(board[x][y] == ' ') {
                        return false;
                    }
                }
            }
        }
        return true; // only reached if nobody won
    }

    public int evalBoard() { // evaluates the board and returns an integer corresponding
        if (wins(HUMAN)) {
            return 0;
        }
        if (wins(COMPUTER)) {
            return 3;
        }
        if (isDraw()) {
            return 2;
        }
        else
            return 1;
    }
}
