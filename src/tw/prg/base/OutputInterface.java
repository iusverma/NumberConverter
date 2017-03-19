package tw.prg.base;

import java.util.List;

/**
 * @author Ayush Verma
 * Interface: OutputInterface
 * This is for defining the basic interface for all output variants
 */
public interface OutputInterface {

    /**
     * Provide result of translating trade from one unit
     * to another and send it to desired location
     * @param output : List of string
     */
    public void offerTrade(List<String> output);
}
