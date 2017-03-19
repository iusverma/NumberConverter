package tw.prg.impl;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FileOutputWriterTest {

    private static String OUTPUT_FILE = "/output/output.txt";

    @Test
    public void testOfferTrade() {
        List<String> output = new ArrayList<>();
        FileOutputWriter fow = new FileOutputWriter();
        fow.offerTrade(output);
        
        //Flow is executed, now we need to validate
        File file = null;
        String path = Paths.get(".").toAbsolutePath().normalize().toString();
        file = new File(path, OUTPUT_FILE);
        Assert.assertNotNull(file, "Output file is not present.");
    }
}
