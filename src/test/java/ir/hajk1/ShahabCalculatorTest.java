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
  String foreignNationalId = "009220569927", foreignShahabCode = "3000000922056994";
  String legalForeignNationalId = "203120758123", legalForeignShahabCode = "4002031207581237";


  @Test
  public void realPersonCalculator() throws Exception {
    ShahabCalculator shahabCalculator = new ShahabCalculator(nationalCode1);
    Assertions.assertEquals(realShahabCode1, shahabCalculator.getShahabCode());
    shahabCalculator = new ShahabCalculator(nationalCode2);
    Assertions.assertEquals(realShahabCode2, shahabCalculator.getShahabCode());
  }

  @Test
  public void legalPersonCalculator() throws Exception {
    ShahabCalculator shahabCalculator = new ShahabCalculator(nationalId);
    Assertions.assertEquals(legalShahabCode, shahabCalculator.getShahabCode());
  }

  @Test
  public void realForeignPersonCalculator() throws Exception {
    ShahabCalculator shahabCalculator = new ShahabCalculator(foreignNationalId,PersonType.REAL_FOREIGNER);
    Assertions.assertEquals(foreignShahabCode, shahabCalculator.getShahabCode());
  }

  @Test
  public void legalForeignPersonCalculator() throws Exception {
    ShahabCalculator shahabCalculator = new ShahabCalculator(legalForeignNationalId,PersonType.LEGAL_FOREIGNER);
    Assertions.assertEquals(legalForeignShahabCode, shahabCalculator.getShahabCode());
  }

  @Test
  public void staticCalculator() throws Exception {
    Assertions.assertEquals(realShahabCode1, ShahabUtils.calculate(nationalCode1));
  }

  @Test
  public void staticForeignerCalculator() throws Exception {
    Assertions.assertEquals(foreignShahabCode, ShahabUtils.calculate(foreignNationalId,PersonType.REAL_FOREIGNER));
  }

}
