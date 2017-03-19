package tw.prg.impl;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import tw.prg.impl.FileInputReader;

public class FileInputReaderTest {

  @Test
  public void testFileInputReader() {
      FileInputReader fir = new FileInputReader();
      Assert.assertNotNull(fir);
  }

  @Test
  public void testGetInput() {
      FileInputReader fir = new FileInputReader();
      
      List<String> input = new ArrayList<>();
      fir.getInput(input);
      Assert.assertEquals(input.size(),13);
  }
}
