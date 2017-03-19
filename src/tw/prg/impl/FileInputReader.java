/**
 * 
 */
package tw.prg.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import tw.prg.base.InputInterface;

/**
 * @author Ayush Verma
 * Class: FileInputReader
 * Description: FileInputReader is responsible for reading file from disc
 */
public class FileInputReader implements InputInterface {
    
    /**
     * Input file path
     */
    private static String INPUT_FILE = "/input/input.txt";
    /**
     * Holds BufferedReader
     */
    private BufferedReader br = null;
    /**
     * Holds FileReader
     */    
    private FileReader fr = null;

    /**
     * Creates a new FileInputReader object, initializes FileReader and BufferedReader
     */
    public FileInputReader() {
        try {
            String path = Paths.get(".").toAbsolutePath().normalize().toString();
            System.out.println("[FileInputReader] Absolute Path: "+path);
            File params = new File(path, INPUT_FILE);
            fr = new FileReader(params);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            System.out.println("[FileInputReader] "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Reads input data
     */
    @Override
    public void getInput(List<String> input) {
        getData(input);
        System.out.println("[FileInputReader.getInput] Total Line Read: "+input.size());
    }

    /**
     * Reads data from an input source file and populate list of string
     * @param input : list of string
     */
    private void getData(List<String> input){
        if(br != null){
            String sCurrentLine;
             try {
                while ((sCurrentLine = br.readLine()) != null) {
                    System.out.println(sCurrentLine);
                    input.add(sCurrentLine);
                }
            } catch (IOException e) {
                System.out.println("[FileInputReader.getData] "+e.getMessage());
                e.printStackTrace();
            }finally {
                try {
                    if (br != null)
                        br.close();
                    if (fr != null)
                        fr.close();
                } catch (IOException ex) {
                    System.out.println("[FileInputReader.getData] "+ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }
}
