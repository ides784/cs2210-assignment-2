import java.util.*;

/**
 * @author Derek Liu
 *         A class that inplements the dictionary data structure framework.
 */

public class Dictionary implements DictionaryADT{
    //array of linkedlists for the separate chaining method
    private final LinkedList[] hashtable;

    //size of the table
    private final int hashtablesize;

    //number of elements
    private int numelements;

    //constructor
    public Dictionary(int length) {
        //set size of table as parameter
        this.hashtablesize = length;
        hashtable =new LinkedList[hashtablesize];
        //iterate over the table and fill it with the beginning of linked lists
        for (int i=0;i< hashtablesize; i++) {
            hashtable[i]=new LinkedList();
        }
    }

    /**
     * @param x the string to hash
     *  suggested hash function template taken from vid lectures
     */
    private int hashfunction(String x){
        int sum= x.charAt(0);
        for (int i=0;i<x.length();i++){
            //as suggested from the vid lectures, a value of 33 was used
            sum =(sum*(33)+((int) x.charAt(i)))% hashtablesize;
        }
        return (sum%hashtablesize);
    }

    /**
     * @param pair the record to be inserted
     *  method to insert the given record pair into the dicitonary, throwing an excpetion if nessasary
     */
    @Override
    public int insert(Record pair) throws DictionaryException {
        int item = hashfunction(pair.getConfig());
        Record tempnode;
        LinkedList<Record> p = hashtable[item];
        if (!p.isEmpty()){
            for (Record record : p) {
                tempnode = record;
                if (tempnode.getConfig().equals(pair.getConfig())) {
                    throw new DictionaryException();
                }
            }
            p.add(pair);
            numelements = numElements() + 1;
            //collision
            return 1;
        }

        else{//if successful
            hashtable[item].add(pair);
            numelements = numElements() + 1;
            return 0;
        }

    }

    /**
     * @param config the entry to be removed
     *  method that removes the entry with the given config from the dictionary
     */
    @Override
    public void remove(String config) throws DictionaryException {
        LinkedList<Record> p = hashtable[hashfunction(config)];
        Record tempnode;
        if (!p.isEmpty()){
            //iterator to check if config exists
            for (int i = 0; i<p.size(); i++){
                tempnode = p.get(i);
                if (tempnode.getConfig().equals(config)) {
                    p.remove();
                    numelements = numElements() - 1;
                    return;
                }
            }
        }

        //if not found
        throw new DictionaryException();

    }


    /**
     * @param config the entry to be returned
     *  method that removes the score stored in the dict for the given config, or -1 if not found
     */
    @Override
    public int get(String config) {
        LinkedList<Record> p = hashtable[hashfunction(config)];
        Record tempnode;
        //iterator to check if config exists
        for (Record record : p) {
            tempnode = record;
            if (tempnode.getConfig().equals(config)) {
                return tempnode.getScore();
            }
        }
        //if not found
        return -1;
     }

    /**
     *  method that returns the number of record objects stored in the dictionary.
     */
    @Override
    public int numElements() {
        //returns the number of recorded elements in dictionary
        return numelements;
    }
}
