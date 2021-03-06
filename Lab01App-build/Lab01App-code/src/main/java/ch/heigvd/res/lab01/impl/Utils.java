package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
      String[] result = new String[2];
      int idx = lines.indexOf("\n"); // for \n, \r\n
      if (-1 == idx) {
          idx = lines.indexOf("\r"); // for \r
      }
      if (-1 == idx) {
          result[0] = "";
          result[1] = lines;
          return result;
      } else {
          result[0] = lines.substring(0, idx + 1);
          if (lines.length() < idx + 1) {
              result[1] = "";
          } else {
              result[1] = lines.substring(idx+1);
          }
      }
      return result;
//    throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }
}
