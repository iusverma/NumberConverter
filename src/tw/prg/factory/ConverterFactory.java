/**
 * 
 */
package tw.prg.factory;

import tw.prg.base.Converter;
import tw.prg.impl.NovaGalaxyNumber;
import tw.prg.impl.NovaGalaxyToRomanConverter;
import tw.prg.impl.RomanNumber;
import tw.prg.impl.RomanToNovaGalaxyConverter;

/**
 * @author Ayush Verma
 * Class : ConverterFactory
 * This class provides a factory to create converter at run time
 */
public class ConverterFactory {
    
    public static final String ROMAN_TO_NOVA_GALAXY = "RomanToNovaGalaxy";
    public static final String NOVA_GALAXY_TO_ROMAN = "NovaGalaxyToRoman";
  
    /**
     * Create a requested converter type
     * @param converterType : String telling which type of converter is required
     * @return an object of converter type requested, null is converter type is not valid
     */
    public static Converter getConverter(String converterType){
        if(converterType.equalsIgnoreCase(ROMAN_TO_NOVA_GALAXY)){
            return new RomanToNovaGalaxyConverter(new RomanNumber(),new NovaGalaxyNumber());
        }else if(converterType.equalsIgnoreCase(NOVA_GALAXY_TO_ROMAN)){
            return new NovaGalaxyToRomanConverter(new NovaGalaxyNumber(),new RomanNumber());
        }else{
            System.out.println("[ConverterFactory.getConverter] Invalid Request.");
        }
        return null;
    }
}
