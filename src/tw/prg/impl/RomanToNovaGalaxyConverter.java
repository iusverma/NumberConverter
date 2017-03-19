package tw.prg.impl;

import java.util.List;

import tw.prg.base.Converter;
import tw.prg.base.Number;

/**
 * @author Ayush Verma
 * Class RomanToNovaGalaxyConverter
 * This class is for converting a RomanNumber to NovaGalaxyNumber,
 * not implemented yet
 */
public class RomanToNovaGalaxyConverter implements Converter {
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
    public RomanToNovaGalaxyConverter(Number from, Number to){
        this.fromNumber = from;
        this.toNumber = to;
    }

    /**
     * Takes a list of string, and if there is any RomanNumber in
     * input, converts it to NovaGalaxyNumber
     * @param inputData : list of String
     * @return List of String : NovaGalaxyNumber converted values
     *                          for RomanNumber in input
     */
    public List<String> convert(List<String> inputData) {
        throw new RuntimeException("Method Not Implemented");
    }

    /**
     * Convert a Roman number to Nova Galaxy Number
     * @param romanNumber : number which is to be converted
     * @return String : converted number
     */
    public String convert(String romanNumber){
        throw new RuntimeException("Method Not Implemented");
    }
}
