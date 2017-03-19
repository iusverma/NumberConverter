package tw.prg.base;

import java.util.List;

/**
 * @author Ayush Verma
 * Interface: Converter
 * This is a common interface for defining the basic operations for all converters
 */
public interface Converter {
    
    /**
     * Takes a list of input string and convert it to desired type
     * @param inputData : List of String
     * @return : List of String
     */
    public List<String> convert(List<String> inputData);

    /**
     * Takes an innut string and convert it to desired type
     * @param proposition : String
     * @return : converted String
     */
    public String convert(String proposition);
}