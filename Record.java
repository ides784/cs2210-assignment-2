/**
 * @author Derek Liu
 *         A class that represents an entry in the dictionary, associating a config with a integer score.
 */
public class Record {
    private final int score;
    private final String config;
    //constructor
    public Record(String config, int score){
        this.config = config;
        this.score = score;
    }
// returns the config stored in this record
    public String getConfig(){
        return config;
    }
//returns the socre in this reocrd
    public int getScore() {
        return score;
    }
}