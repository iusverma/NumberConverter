/**
 * 
 */
package tw.prg.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import tw.prg.base.Converter;
import tw.prg.base.Number;

/**
 * @author Ayush Verma
 * Class NovaGalaxyToRomanConverter
 * This class converts a NovaGalaxyNumber to RomanNumber
 */
public class NovaGalaxyToRomanConverter implements Converter{
    
    /**
     * Base number for current object, here it is NovaGalaxyNumber
     */
    Number fromNumber = null;
    /**
     * Target number for base number, here it is RomanNumber
     */
    Number toNumber = null;
    
    /**
     * Create object for NovaGalaxyToRomanConverter and
     * initializes the fromNumber and toNumber objects
     * @param from
     * @param to
     */
    public NovaGalaxyToRomanConverter(Number from, Number to){
        this.fromNumber = from;
        this.toNumber = to; // This seems to be note required
    }

    /**
     * Takes a list of string, and if there is any NovaGalaxyNumber in
     * input, converts it to RomanNumber
     * @param inputData : list of String
     * @return List of String : RomanNumber converted values
     *                          for NovaGalaxyNumber in input
     */
    public List<String> convert(List<String> inputData) {
        /*
         * For now it's only purpose is to initialize baseNumberMap for NovaGalaxyNumber,
         * This will be used later in convert(String)
         */
        fromNumber.init(inputData);
        List<String> convertedValue = new ArrayList<>();
        return convertedValue;
    }
    
    /**
     * Convert a Nova Galaxy number to Roman Number
     * @param novaGalaxyNumber : number which is to be converted
     * @return String : converted number
     */
    public String convert(String novaGalaxyNumber){
        String ngNumber = "";
        String equivalentRoman = "";
        if(!novaGalaxyNumber.isEmpty()){
            StringTokenizer st = new StringTokenizer(novaGalaxyNumber, " ");
            while(st.hasMoreTokens()){
                String word = st.nextToken();
                if(fromNumber.getBaseNumberMap().containsKey(word)){
                    ngNumber += word + " ";
                    equivalentRoman+=fromNumber.getBaseNumberMap().get(word);
                }else{
                    System.out.println("[NovaGalaxyToRomanConverter.convert] Invalid String: "+novaGalaxyNumber);
                    return "";
                }
            }
            if(!ngNumber.isEmpty() && !equivalentRoman.isEmpty()){
                ngNumber.trim();
                equivalentRoman.trim();
                System.out.println("[NovaGalaxyToRomanConverter.convert] Nova Galaxy Number: "+ngNumber);
                System.out.println("[NovaGalaxyToRomanConverter.convert] Roman Number: "+equivalentRoman);
                return equivalentRoman;
            }    
        }
        return equivalentRoman;
    }
}
