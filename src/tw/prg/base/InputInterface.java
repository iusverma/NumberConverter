package tw.prg.base;

import java.util.List;

/**
 * @author Ayush Verma
 * Interface: InputInterface
 * This is for defining the basic interface for all input variants
 */
public interface InputInterface {

    /**
     * Fetches input for translating trade from one unit
     * to another from a desired location
     * @param output : List of string
     */
    public void getInput(List<String> input);
}