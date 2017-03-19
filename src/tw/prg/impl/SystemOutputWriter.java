package tw.prg.impl;

import java.util.List;

import tw.prg.base.OutputInterface;

/**
 * @author Ayush Verma
 * Class: SystemOutputWriter
 * Description: SystemOutputWriter is responsible for printing results on console
 */
public class SystemOutputWriter implements OutputInterface {

    /**
     * Prints output data to console
     * @param input : list of String
     */
    @Override
    public void offerTrade(List<String> output) {
        throw new RuntimeException("Method not implemented."); 
    }

}
