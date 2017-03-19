package tw.prg.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import tw.prg.base.OutputInterface;

/**
 * @author Ayush Verma
 * Class: FileOutputWriter
 * Description: FileOutputWriter is responsible for writing file on disc
 */
public class FileOutputWriter implements OutputInterface {
    
    /**
     * Output file path
     */
    private static String OUTPUT_FILE = "/output/output.txt";
    /**
     * Holds BufferedWriter
     */
    private BufferedWriter bw = null;
    /**
     * Holds FileWriter
     */
    private FileWriter fw = null;
    
    /**
     * Creates a new FileOutputWriter object, initializes FileWriter and BufferedWriter
     */
    public FileOutputWriter() {
        try {
            String path = Paths.get(".").toAbsolutePath().normalize().toString();
            System.out.println("[FileInputReader] Absolute Path: "+path);
            File file = new File(path, OUTPUT_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
        } catch (IOException e) {
            System.out.println("[FileOutputWriter] "+e.getMessage());
            e.printStackTrace();
        }
    }   

    /**
     * Prints list of string to disc
     */
    @Override
    public void offerTrade(List<String> output) {
        System.out.println("[FileOutputWriter.offerTrade]");
        putData(output);
    }
    
    /**
     * Takes a list of string and put it into a file on specified location in disc
     * @param output: list of string
     */
    private void putData(List<String> output){
        System.out.println("[FileOutputWriter.putData]");
        Iterator<String> iter = output.iterator();
        if(bw!=null){
            try {
                while(iter.hasNext()){
                    String content = iter.next();
                    System.out.println("[FileOutputWriter.putData] Writing to file: "+content);
                    bw.write(content);
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println("[FileOutputWriter.putData] "+e.getMessage());
                e.printStackTrace();
            }finally {
                System.out.println("[FileOutputWriter.putData] Update to file done.");
                try {
                    if (bw != null)
                        bw.close();
                    if (fw != null)
                        fw.close();
                } catch (IOException ex) {
                    System.out.println("[FileOutputWriter.putData] "+ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }

}
