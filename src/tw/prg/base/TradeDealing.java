package tw.prg.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ayush Verma
 * Class: TradeDealing
 * This class provides basic flow in any galaxy trade dealing, if a new
 * trade needs to be initiated, this class needs to be extended.
 */
public abstract class TradeDealing{

    /**
     * Holds input data
     */
    protected List<String> inputData = null;

    /**
     * Holds converter for Nova Galaxy
     */
    protected Converter converter= null;

    /**
     * Holds data input interface for Nova Galaxy
     */
    protected InputInterface input = null;

    /**
     * Holds data input interface for Nova Galaxy
     */
    protected OutputInterface output = null;

    /**
     * This create NovaGalaxyTradeDealing object and
     * initializes necessary members
     */
    public TradeDealing(){
        inputData = new ArrayList<>();
    }

    /**
     * This is the start of trade, it runs end to end flow
     */
    public void trade(){
        // Reads input
        readInput();
        
        // categorizing numbers data, cost value, new trade details from input
        categorizeInputData();
        
        // initializing Nova Galaxy number format
        initializeNumber();
        
        // creating Nova Galaxy to Roman number converter
        prepareConverter();
        
        // converting input to Roman format
        convert();
        
        // Writing data to output
        offerTrade();     
    }

    /**
     * This reads a file from disc and initializes inputData
     */
    protected abstract void readInput();

    /**
     * Based on inputData numberData, costData & tradeData are categorized
     */
    protected abstract void categorizeInputData();

    /**
     * Initializes novaGalaxyNumber, taking data from inputData
     */
    protected abstract void initializeNumber();

    /**
     * Prepare Nova Galaxy to Roman Number Converter
     */
    protected abstract void prepareConverter();

    /**
     * Request for converting Nova Galaxy numbers to Roman numbers
     */
    protected abstract void convert();
    
    /**
     * Providing the final proposal for all requested trades in desired location
     */
    protected abstract void offerTrade();

    public Converter getConverter() {
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public OutputInterface getOutput() {
        return output;
    }

    public void setOutput(OutputInterface output) {
        this.output = output;
    }

    public InputInterface getInput() {
        return input;
    }

    public void setInput(InputInterface input) {
        this.input = input;
    }

    public List<String> getInputData() {
        return inputData;
    }

    public void setInputData(List<String> inputData) {
        this.inputData = inputData;
    }
}
