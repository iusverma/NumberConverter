package tw.prg.trade;

import org.testng.annotations.Test;

public class NovaGalaxyTradeDealingTest {

  @Test
  public void testTradeDealing() {
      NovaGalaxyTradeDealing td = new NovaGalaxyTradeDealing();
      td.trade();
  }
}
