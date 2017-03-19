package tw.prg.impl;

import java.util.Iterator;
import java.util.List;

import tw.prg.base.Number;

/**
 * @author Ayush Verma
 * Class: NovaGalaxyNumber
 * Description: NovaGalaxyNumber is representation of NovaGalaxyNumber
 */
public class NovaGalaxyNumber extends Number {

    /**
     * Initializes the number map for NovaGalaxyNumber object
     * using the list of string passed as input parameter
     * @param data : list of string as input data
     */
    @Override
    public void init(List<String> data) {
        System.out.println("[NovaGalaxyNumber.init]");
        Iterator<String> iter = data.iterator();
        /**
         * Iterate through input list and parses each statement
         */
        while(iter.hasNext()){
            String currentStatement = iter.next();
            System.out.println("[NovaGalaxyNumber.init] Parsing statement: "+currentStatement);
            parseStatement(currentStatement);
            System.out.println("[NovaGalaxyNumber.init] Base numbers for Nova Galaxy initalized.");
        }
    }

    /**
     * Takes a " " separated string and identifies and populate
     * mapping to Nova Galaxy numbers to Roman numbers
     * @param statement : " " separated string
     */
    private void parseStatement(String statement){
        System.out.println("[NovaGalaxyNumber.parseStatement]");
        if(statement.charAt(statement.length()-1) != '?'){
            /**
             * If statement has ? at end, it is not giving you a mapping or
             * cost estimation
             */
            String arr[] = statement.split(" ", 3);
            if(arr[1].equals("is") && arr[2] != null){
                /**
                 * A statement is giving you a mapping, if
                 * it has like "tegj is L", hence we map 
                 * 1st and 3rd word of statement
                 */
                getBaseNumberMap().put(arr[0], arr[2]);
            }else{
                /**
                 * A statement is giving you a cost estimation,
                 * if doesn't have ? and has more than 3 words.
                 */
                System.out.println("[NovaGalaxyNumber.parseStatement] Invalid statement: "+statement);
            }
        }else{
            System.out.println("[NovaGalaxyNumber.parseStatement] This is a proposition and not a statement."+statement);
        }
    }
}