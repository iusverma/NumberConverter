package tw.prg.impl;

import java.util.List;

import tw.prg.base.Converter;

/**
 * @author Ayush Verma
 * Class RomanToDecimalConverter
 * This class converts the logic from a converting 
 * Roman number to Decimal number 
 */
public class RomanToDecimalConverter implements Converter {

    /**
     * Method given by Converter interface, not required for now
     */
    public List<String> convert(List<String> inputData) {
        throw new RuntimeException("Method not implemented");
    }
 
    /**
     * Method given by Converter interface, not required for now
     */
    public String convert(String proposition){
        throw new RuntimeException("Method Not Implemented");
    }

    /**
     * Converts a roman number to decimal numbers
     * @param romanNumber : a string which is a roman number
     * @return int : converted decimal value
     */
    public static int covertToInteger(String romanNumber){
        int decimal = 0;

        String romanNumeral = romanNumber.toUpperCase();
        /**
         * Stage 1: First we will parse each character and
         * create a decimal number as it is, ignoring double
         * characters numeral e.g. 'IV' or 'IX'.
         * Such character will be taken care in next stage
         */
        for(int x = 0;x<romanNumeral.length();x++)
        {
            char convertToDecimal = romanNumber.charAt(x);
            switch (convertToDecimal)
            {
            case 'M':
                decimal += 1000;
                break;
            case 'D':
                decimal += 500;
                break;
            case 'C':
                decimal += 100;
                break;
            case 'L':
                decimal += 50;
                break;
            case 'X':
                decimal += 10;
                break;
            case 'V':
                decimal += 5;
                break;
            case 'I':
                decimal += 1;
                break;
            }
        }
        /**
         * Stage 2: Now start considering all double characters
         */
                
        /**
         * Since IV = 4 but we add it as 1+5 = 6 in while parsing string,
         * we will not subtract 2 from total number
         */
        if (romanNumeral.contains("IV"))
        {
            decimal-=2;
        }
        /**
         * Since IX = 9 but we add it as 1+10 = 11 in while parsing string,
         * we will not subtract 2 from total number
         */
        if (romanNumeral.contains("IX"))
        {
            decimal-=2;
        }
        /**
         * Since XL = 40 but we add it as 10+50 = 60 in while parsing string,
         * we will not subtract 20 from total number
         */
        if (romanNumeral.contains("XL"))
        {
            decimal-=20;
        }
        /**
         * Since XL = 90 but we add it as 10+90 = 110 in while parsing string,
         * we will not subtract 20 from total number
         */
        if (romanNumeral.contains("XC"))
        {
            decimal-=20;
        }
        /**
         * Since CD = 400 but we add it as 100+500 = 600 in while parsing string,
         * we will not subtract 200 from total number
         */
        if (romanNumeral.contains("CD"))
        {
            decimal-=200;
        }
        /**
         * Since XL = 900 but we add it as 100+900 = 1100 in while parsing string,
         * we will not subtract 200 from total number
         */
        if (romanNumeral.contains("CM"))
        {
            decimal-=200;
        }
        return decimal;
    }
}
