package ir.hajk1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Author: <a href="mailto:k1.tehrani@gmail.com">Kayvan Tehrani</a>
 *
 * Description: <the description of the class for java doc by those that might use it, please use html if possible>
 */
public class ShahabCalculatorTest {

  String nationalCode1 = "0068245300", realShahabCode1 = "1000000068245303";
  String nationalCode2 = "0062091298", realShahabCode2 = "1000000062091299";
  String nationalId = "10100978129", legalShahabCode = "2000010100978125";


  @Test
  public void realPersonCalculator() throws Exception {
    ShahabCalculator shahabCalculator = new ShahabCalculator(nationalCode1);
    Assertions.assertEquals(shahabCalculator.getShahabCode(), realShahabCode1);
    shahabCalculator = new ShahabCalculator(nationalCode2);
    Assertions.assertEquals(shahabCalculator.getShahabCode(), realShahabCode2);
  }

  @Test
  public void legalPersonCalculator() throws Exception {
    ShahabCalculator shahabCalculator = new ShahabCalculator(nationalId);
    Assertions.assertEquals(shahabCalculator.getShahabCode(), legalShahabCode);
  }

}
