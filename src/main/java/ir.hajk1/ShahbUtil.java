package ir.hajk1;

/**
 * Author: <a href="mailto:k1.tehrani@gmail.com">Kayvan Tehrani</a>
 *
 * Description: <the description of the class for java doc by those that might use it, please use html if possible>
 */
public class ShahbUtil {

  /**
   * @param nationalNumber National Code for real person or National Id for the corporate person
   * @return Shahab Code
   * @
   */
  static String calculate(String nationalNumber) throws Exception {
    return new ShahabCalculator(nationalNumber).getShahabCode();
  }

}
