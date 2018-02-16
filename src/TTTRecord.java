// Set up TTTRecord class for further implementation
// TTTRecord is a simple storing system for the game
// It stores Config, Score, and Level
// Has only getter functions

public class TTTRecord {
    private String savedConfig;
    private int savedScore;
    private int savedLevel;

    public TTTRecord(String config, int score, int level){ // Constructor
        savedConfig = config;
        savedScore = score;
        savedLevel = level;
    }
    // getters
    public String getConfiguration(){
        return savedConfig;
    }

    public int getScore(){
        return savedScore;
    }

    public int getLevel(){
        return savedLevel;
    }

}