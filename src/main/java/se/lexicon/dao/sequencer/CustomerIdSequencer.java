package se.lexicon.dao.sequencer;

public class CustomerIdSequencer {

    private static int currentId = 0;

    // Private constructor to prevent instantiation
    private CustomerIdSequencer(){

    }

    public static int nextId(){
        return ++currentId;
    }


}
