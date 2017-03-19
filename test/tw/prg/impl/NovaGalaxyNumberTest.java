package tw.prg.impl;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NovaGalaxyNumberTest {

  @Test
  public void testInit() {
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
    input.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
    
    NovaGalaxyNumber ngm = new NovaGalaxyNumber();
    ngm.init(input);
    System.out.println("Number of symbols for Nova Galaxy:"+ngm.getBaseNumberMap().size());
    Assert.assertEquals(ngm.getBaseNumberMap().size(), 4);
  }
}
