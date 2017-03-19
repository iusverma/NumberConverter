package tw.prg.impl;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RomanToDecimalConverterTest {

  @Test
  public void testCovertToInteger() {
    Assert.assertEquals(RomanToDecimalConverter.covertToInteger("MMCCCXLV"), 2345);
    
    Assert.assertEquals(RomanToDecimalConverter.covertToInteger("MMMCCXC"), 3290);
    
    Assert.assertEquals(RomanToDecimalConverter.covertToInteger("CMXCIX"), 999);
    
    Assert.assertEquals(RomanToDecimalConverter.covertToInteger("MCCCXVII"), 1317);
    
    Assert.assertEquals(RomanToDecimalConverter.covertToInteger("DXXI"), 521);
    
    Assert.assertEquals(RomanToDecimalConverter.covertToInteger("LXIII"), 63);
    
    Assert.assertEquals(RomanToDecimalConverter.covertToInteger("DCCVI"), 706);
    
    Assert.assertEquals(RomanToDecimalConverter.covertToInteger("MCMXXX"), 1930);
    
    Assert.assertEquals(RomanToDecimalConverter.covertToInteger("MCXI"), 1111);
    
    Assert.assertEquals(RomanToDecimalConverter.covertToInteger("MMCCXXII"), 2222);
  }
}
