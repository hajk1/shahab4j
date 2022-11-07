package ir.hajk1;

import static ir.hajk1.PersonType.CORPORATE;
import static ir.hajk1.PersonType.REAL;
import static ir.hajk1.PersonType.REAL_FOREIGNER;

import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.apache.commons.validator.routines.checkdigit.VerhoeffCheckDigit;

/**
 * Author: <a href="mailto:k1.tehrani@gmail.com">Kayvan Tehrani</a>
 *
 * Description: <the description of the class for java doc by those that might use it, please use html if possible>
 */
public class ShahabCalculator {

  public static final Short REAL_PERSON_NATIONAL_LEN = 10;
  public static final Short LEGAL_PERSON_NATIONAL_LEN = 11;
  public static final Short REAL_FOREIGNER_LEN = 12;
  public static final Short LEGAL_FOREIGNER_LEN = 12;
  public static final String REAL_SHAHAB_PREFIX = "100000";
  public static final String LEGAL_SHAHAB_PREFIX = "20000";
  public static final String REAL_FOREIGNER_SHAHAB_PREFIX = "30000";
  public static final String LEGAL_FOREIGNER_SHAHAB_PREFIX = "400";
  public static final short NATIONAL_CODE_START = 0;
  public static final short NATIONAL_CODE_END = 9;
  public static final short NATIONAL_ID_START = 0;
  public static final short NATIONAL_ID_END = 10;
  public static final short FOREIGN_ID_START = 0;
  public static final short FOREIGN_ID_END = 10;
  public static final short LEGAL_FOREIGN_ID_START = 0;
  public static final short LEGAL_FOREIGN_ID_END = 12;
  private final String nationalCode;
  private final PersonType personType;


  public ShahabCalculator(String nationalCode) throws ShahbException {
    this.nationalCode = nationalCode;
    if (nationalCode == null) {
      throw new ShahbException("Null value is not acceptable");
    }
    short len = (short) nationalCode.length();
    if (len == REAL_PERSON_NATIONAL_LEN) {
      personType = REAL;
    } else if (len == LEGAL_PERSON_NATIONAL_LEN) {
      personType = CORPORATE;
    } else if (len == REAL_FOREIGNER_LEN) {
      throw new ShahbException("Foreign person type must be determined");
    } else {
      throw new ShahbException("Unable to calculate shahab for nationalCode len :" + len);
    }
  }
  public ShahabCalculator(String nationalCode,PersonType type) throws ShahbException {
    this.nationalCode = nationalCode;
    if (nationalCode == null) {
      throw new ShahbException("Null value is not acceptable");
    }
    if (type == null) {
      throw new ShahbException("Null value is not acceptable");
    }
 personType = type;
  }

  public String getShahabCode() throws Exception {
    switch (personType) {
      case REAL:
        return realPersonCalculate();
      case CORPORATE:
        return legalPersonCalculate();
      case REAL_FOREIGNER:
        return realForeignerCalculate();
      case LEGAL_FOREIGNER:
        return legalForeignerCalculate();
    }
    throw new Exception("Unknown person type found:" + personType);
  }

  private String legalForeignerCalculate() throws CheckDigitException {
    String legalForeignPersonShahab =
            LEGAL_FOREIGNER_SHAHAB_PREFIX + nationalCode.substring(LEGAL_FOREIGN_ID_START, LEGAL_FOREIGN_ID_END);
    String checkDigit = new VerhoeffCheckDigit().calculate(legalForeignPersonShahab);
    return legalForeignPersonShahab + checkDigit;
  }

  private String realForeignerCalculate() throws CheckDigitException {
    String foreignPersonShahab =
        REAL_FOREIGNER_SHAHAB_PREFIX + nationalCode.substring(FOREIGN_ID_START, FOREIGN_ID_END);
    String checkDigit = new VerhoeffCheckDigit().calculate(foreignPersonShahab);
    return foreignPersonShahab + checkDigit;

  }

  private String legalPersonCalculate() throws CheckDigitException {
    String realPersonShahab = LEGAL_SHAHAB_PREFIX + nationalCode.substring(NATIONAL_ID_START, NATIONAL_ID_END);
    String checkDigit = new VerhoeffCheckDigit().calculate(realPersonShahab);
    return realPersonShahab + checkDigit;
  }

  private String realPersonCalculate() throws CheckDigitException {
    String realPersonShahab = REAL_SHAHAB_PREFIX + nationalCode.substring(NATIONAL_CODE_START, NATIONAL_CODE_END);
    String checkDigit = new VerhoeffCheckDigit().calculate(realPersonShahab);
    return realPersonShahab + checkDigit;
  }
}
