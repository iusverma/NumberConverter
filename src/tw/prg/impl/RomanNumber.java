package tw.prg.impl;

import java.util.List;

import tw.prg.base.Number;

/**
 * @author Ayush Verma
 * Class: RomanNumber
 * Description: NovaGalaxyNumber is representation of NovaGalaxyNumber
 */
public class RomanNumber extends Number {
    
    /**
     * Creates new object of RomanNumber and initializes it
     */
    public RomanNumber() {
        getBaseNumberMap().put("I", "1");
        getBaseNumberMap().put("V", "5");
        getBaseNumberMap().put("X", "10");
        getBaseNumberMap().put("L", "50");
        getBaseNumberMap().put("C", "100");
        getBaseNumberMap().put("D", "500");
        getBaseNumberMap().put("M", "1000");
    }
    
    /**
     * This is just an override function, however since RomanNumbers
     * are well defined they are already initialized in constructor.
     */
    @Override
    public void init(List<String> data) {
        /**
         * No initialization required
         */
    }
    
    /**
     * Return true if a character in Roman numeral
     * @param literal : a single character
     * @return true/false
     */
    public static boolean isRomanNumber(char literal){
        if(literal == 'I' || 
                literal == 'V' || 
                literal == 'X' || 
                literal == 'L' || 
                literal == 'C' || 
                literal == 'D' ||  
                literal == 'M' ){
            return true;
        }
        return false;
    }
}
