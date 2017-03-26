package tw.prg.trade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import tw.prg.base.Converter;
import tw.prg.base.InputInterface;
import tw.prg.base.OutputInterface;
import tw.prg.base.TradeDealing;
import tw.prg.factory.ConverterFactory;
import tw.prg.impl.FileInputReader;
import tw.prg.impl.FileOutputWriter;
import tw.prg.impl.NovaGalaxyNumber;
import tw.prg.impl.RomanNumber;
import tw.prg.impl.RomanToDecimalConverter;

/**
 * @author Ayush Verma
 * Class: NovaGalaxyTradeDealing
 * This class is responsible for end to end trading with NovaGalaxy, it takes their
 * cost evaluation, and then provides trade in their units.
 */
public class NovaGalaxyTradeDealing extends TradeDealing{

    /**
     * Holds Nova Galaxy Numbers data from input data
     */
    private List<String> numbersData = null;

    /**
     * Holds Nova Galaxy Cost data from input data
     */
    private List<String> costData = null;

    /**
     * Holds Nova Galaxy trade data from input data
     */
    private List<String> tradeData = null;

    /**
     * Holds Nova Galaxy trade data for output data
     */
    private List<String> ngToRomanConvertedNumbers = null;

    /**
     * Holds material and price details Nova Galaxy from input data
     */
    private Map<String,Double> materialPerUnitPrice = null;

    /**
     * Holds a Nova Galaxy number
     */
    private NovaGalaxyNumber novaGalaxyNumber = null;

    /**
     * This create NovaGalaxyTradeDealing object and
     * initializes necessary members
     */
    public NovaGalaxyTradeDealing(){
        if(input==null){
            input = new FileInputReader();
        }
        if(inputData != null){
            inputData = new ArrayList<>();
        }
        if(output==null){
            output = new FileOutputWriter();
        }
        numbersData = new ArrayList<>();
        costData = new ArrayList<>();
        tradeData = new ArrayList<>();        
        materialPerUnitPrice = new HashMap<String, Double>();
        ngToRomanConvertedNumbers = new ArrayList<>();

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
    protected void readInput() {
        System.out.println("[TradeDealing.getInput]");
        input.getInput(inputData);
        System.out.println("[TradeDealing.getInput] Total Line Read: "+inputData.size());
    }

    /**
     * Based on inputData numberData, costData & tradeData are categorized
     */
    protected void categorizeInputData() {
        System.out.println("[TradeDealing.categorizeInputData]");
        Iterator<String> iter = inputData.iterator();
        while(iter.hasNext()){
            String content = iter.next();
            if(content.charAt(content.length()-1) == '?'){
                /* Assumption: if a sentence is finishing with ?, it is a request 
                 * for quotation. E.g. How much is xyz gold cost ?
                 */
                tradeData.add(content);
            }else if(RomanNumber.isRomanNumber(content.charAt(content.length()-1))){
                /* Assumption: for Nova Galaxy dealing, since we know that numbers
                 * of this galazxy are same as roman numbers, so we assume that if
                 * there is any roman number at the end of sentence, then it is 
                 * mapping from galaxy number to roman number.
                 */
                numbersData.add(content);
            }else{
                /* Assumption: if a sentence does not fall in above two categories,
                 * then it must be giving an estimate of some material price.
                 */
                costData.add(content);
            }
        }
        System.out.println("[TradeDealing.categorizeInputData] Number of data items for numbers: " +numbersData.size());
        System.out.println("[TradeDealing.categorizeInputData] Number of data items for cost: " +costData.size());
        System.out.println("[TradeDealing.categorizeInputData] Number of data items for trade: " +tradeData.size());
    }

    /**
     * Initializes novaGalaxyNumber, taking data from inputData
     */
    protected void initializeNumber() {
        System.out.println("[TradeDealing.initializeNumber]");
        novaGalaxyNumber = new NovaGalaxyNumber();
        novaGalaxyNumber.init(inputData);
    }

    /**
     * Prepare Nova Galaxy to Roman Number Converter
     */
    protected void prepareConverter() {
        System.out.println("[TradeDealing.getConverter]");
        converter = ConverterFactory.getConverter(ConverterFactory.NOVA_GALAXY_TO_ROMAN);
    }

    /**
     * Request for converting Nova Galaxy numbers to Roman numbers
     */
    protected void convert() {
        System.out.println("[TradeDealing.convert]");
        if(converter != null){
            /* TODO: This is call even though doesn't seem to be necessary as per flow,
             * however internally it is initializing NovaGalaxyNumber, so if we don't 
             * make this call, we will not get the final numbers.
             * Need to figure out why NovaGalaxyNumber was not initialized in 
             * initializeNumber() call.
             */
            ngToRomanConvertedNumbers = converter.convert(inputData);
        }
        System.out.println("[TradeDealing.convert] Total Line Converted: "+ngToRomanConvertedNumbers.size());
        // populating cost value
        populateCostEstimation();
        
        // preparing new price proposal
        translateCostEstimation();
    }

    /**
     * Once covnersion started to take place, we need to identify the cost and material
     * in Nova Galaxy, this estimation is done here.
     */
    private void populateCostEstimation(){
        System.out.println("[TradeDealing.populateCostEstimation]");
        Iterator<String> iter = costData.iterator();
        while(iter.hasNext()){
            String content = iter.next();
            String quantity = "";
            String material = "";
            double price = 0;
            StringTokenizer st = new StringTokenizer(content, " ");
            while(st.hasMoreTokens()){
                String token = st.nextToken();
                if(token.equalsIgnoreCase("is") || token.equalsIgnoreCase("Credits")){
                    // do nothing
                }else if(novaGalaxyNumber.getBaseNumberMap().containsKey(token)){
                    quantity += token;
                    quantity += " ";
                }else if(hasNumber(token)){
                    price = Double.parseDouble(token);
                }else{
                    material = token;
                }
                if(!quantity.isEmpty() && !material.isEmpty() && price > 0){
                    String romanNumber = converter.convert(quantity);
                    int quanityInt = RomanToDecimalConverter.covertToInteger(romanNumber);
                    if(!materialPerUnitPrice.containsKey(material)){
                        materialPerUnitPrice.put(material, new Double(price/quanityInt));
                    }
                }
            }
        }
        System.out.println("[TradeDealing.populateCostEstimation] Matrial available: " +materialPerUnitPrice.size());
    }

    /**
     * System know, what is the material required and what is their price per unit.
     * Here system is giving the price for request number of units for each material.
     */
    private void translateCostEstimation(){
        System.out.println("[TradeDealing.translateCostEstimation]");
        ngToRomanConvertedNumbers.clear();
        Iterator<String> iter = tradeData.iterator();
        while(iter.hasNext()){
            String content = iter.next();
            String quantity = "";
            String material = "";
            boolean linePrcessedCompletely = false;
            System.out.println("[TradeDealing.translateCostEstimation] Trade requested: " +content);
            StringTokenizer st = new StringTokenizer(content, " ");
            while(st.hasMoreTokens()){
                String token = st.nextToken();
                if(token.equalsIgnoreCase("is") 
                        || token.equalsIgnoreCase("how")
                        || token.equalsIgnoreCase("many")
                        || token.equalsIgnoreCase("much")
                        || token.equalsIgnoreCase("credits")
                        || hasNumber(token)){
                    /**
                     * These words are just to increase communication and
                     * at current will not serve any purpose for us.
                     * hence do nothing
                     */
                }else if(token.equalsIgnoreCase("?")){
                    /**
                     * We can only check for final statement, once the full line is parsed,
                     * which is when last character is already reached
                     */
                    linePrcessedCompletely = true;
                }else if(novaGalaxyNumber.getBaseNumberMap().containsKey(token)){
                    quantity += token;
                    quantity += " ";
                }else{
                    /**
                     * We have eliminated all possibilities and
                     * now the remaining word seems to be a material.
                     */
                    if(materialPerUnitPrice.containsKey(token)){
                        material = token;
                    }
                }
                
                /**
                 * Once whole statement is processed, we prepare the final offer.
                 */
                if(linePrcessedCompletely){
                    if(!quantity.isEmpty() && !material.isEmpty()){
                        String romanNumber = converter.convert(quantity);
                        int quanityInt = RomanToDecimalConverter.covertToInteger(romanNumber);
                        printFinalDetails("[TradeDealing.translateCostEstimation]",quantity, romanNumber, quanityInt, material);
                        String tradeProposal = quantity + " " +material +" is ";
                        Double finalCost = materialPerUnitPrice.get(material) * quanityInt;
                        tradeProposal += String.valueOf(finalCost);
                        tradeProposal += " Credits";
                        insertInTradeOffer(tradeProposal);
                   } else if(!quantity.isEmpty() && material.isEmpty()){
                        String romanNumber = converter.convert(quantity);
                        int quanityInt = RomanToDecimalConverter.covertToInteger(romanNumber);
                        printFinalDetails("[TradeDealing.translateCostEstimation]",quantity, romanNumber, quanityInt, "");
                        String tradeProposal = quantity + " is " +String.valueOf(quanityInt);
                        insertInTradeOffer(tradeProposal);
                    } else if(quantity.isEmpty() && material.isEmpty()){
                        insertInTradeOffer("I have no idea what you are talking about");
                    }
                }
            }
        }
    }
    
    /**
     * Providing the final proposal for all requested trades in desired location
     */
    protected void offerTrade(){
        System.out.println("[TradeDealing.offerTrade]");
        output.offerTrade(ngToRomanConvertedNumbers);
        System.out.println("[TradeDealing.getInput] Total Trade Converted: "+ngToRomanConvertedNumbers.size());
    }

    /**
     * Returns true is a string has any number in it,
     * returns false otherwise.
     * @param str : string where number is expected in between
     * @return true/false
     */
    private boolean hasNumber(final String str) {                
        if(str == null || str.isEmpty()) return false;

        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for(char c : str.toCharArray()){
            if(Character.isDigit(c)){
                sb.append(c);
                found = true;
            } else if(found){
                // If we already found a digit before and this char is not a digit, stop looping
                break;                
            }
        }

        return found;
    }

    /**
     * Populate trade proposal list ngToRomanConvertedNumbers
     * @param tradeProposal : string which mentions one proposed trade
     */
    private void insertInTradeOffer(String tradeProposal){
        System.out.println("[TradeDealing.insertInTradeOffer]");
        if(!tradeProposal.isEmpty() && !ngToRomanConvertedNumbers.contains(tradeProposal)){
            ngToRomanConvertedNumbers.add(tradeProposal);
        }
    }
    
    private void printFinalDetails(String operationName, String quantity, String romanNumber, int quanityInt, String material){
        System.out.println(operationName +" Quantity in Nova Galaxy: "+quantity);
        System.out.println(operationName +" Quantity in Roman number:"+romanNumber);
        System.out.println(operationName +" Quantiy in Decimal: "+quanityInt);
        if(!material.isEmpty()){
            System.out.println(operationName +" Material: "+material); 
        }
    }
}
