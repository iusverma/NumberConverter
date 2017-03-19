package tw.prg.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NovaGalaxyToRomanConverterTest {

  @Test
  public void testConvert() {
      List<String> input = new ArrayList<>();
      input.add("glob is I");
      input.add("prok is V");
      input.add("pish is X");
      input.add("tegj is L");
      input.add("glob glob Silver is 34 Credits");
      input.add("glob prok Gold is 57800 Credits");
      input.add("pish pish Iron is 3910 Credits");
      input.add("how much is pish tegj glob glob ?");
      input.add("how many Credits is glob prok Silver ?");
      input.add("how many Credits is glob prok Gold ?");
      input.add("how many Credits is glob prok Iron ?");
      input.add("how much is pish tegj glob prok ?");
      input.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
      
      List<String> output = new ArrayList<>();
      
      NovaGalaxyNumber ngNumber = new NovaGalaxyNumber();
      NovaGalaxyToRomanConverter ngToRoamConverter = new NovaGalaxyToRomanConverter(ngNumber, new RomanNumber());

      output = ngToRoamConverter.convert(input);
      System.out.println("Number of propositions for Nova Galaxy:"+output.size());
      Assert.assertEquals(output.size(), 5);
      
      Iterator<String> iter = output.iterator();
      while(iter.hasNext()){
          System.out.println(iter.next());
      }
  }
  
  @Test
  public void testNGtoRomanNumber() {
      List<String> input = new ArrayList<>();
      input.add("glob is I");
      input.add("prok is V");
      input.add("pish is X");
      input.add("tegj is L");
      input.add("glob glob Silver is 34 Credits");
      input.add("glob prok Gold is 57800 Credits");
      input.add("pish pish Iron is 3910 Credits");
      input.add("how much is pish tegj glob glob ?");
      input.add("how many Credits is glob prok Silver ?");
      input.add("how many Credits is glob prok Gold ?");
      input.add("how many Credits is glob prok Iron ?");
      input.add("how much is pish tegj glob prok ?");
      input.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
      
      NovaGalaxyNumber ngNumber = new NovaGalaxyNumber();
      ngNumber.init(input);
      NovaGalaxyToRomanConverter ngToRoamConverter = new NovaGalaxyToRomanConverter(ngNumber, new RomanNumber());

      Assert.assertEquals(ngToRoamConverter.convert("glob glob"),"II");
      Assert.assertEquals(ngToRoamConverter.convert("glob prok"),"IV");
      Assert.assertEquals(ngToRoamConverter.convert("pish glob prok"),"XIV");
      Assert.assertEquals(ngToRoamConverter.convert("tegj pish prok glob"),"LXVI");
  }
}
