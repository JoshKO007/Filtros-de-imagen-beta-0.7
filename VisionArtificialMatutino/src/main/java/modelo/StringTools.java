package modelo;


/**
 * A class containing various static utility methods for
 * handling strings.
 *
 * @author Nick Efford
 * @version 1.1 [1999/07/23]
 * @see java.lang.String
 * @see java.lang.StringBuffer
 */

public final class StringTools {

  public static String rightJustify(int value, int fieldWidth) {
    return rightJustify(String.valueOf(value), fieldWidth);
  }

  public static String rightJustify(float value, int fieldWidth) {
    return rightJustify(String.valueOf(value), fieldWidth);
  }

  public static String rightJustify(String string, int fieldWidth) {
    StringBuffer field = new StringBuffer();
    field.setLength(fieldWidth);
    for (int i = 0; i < fieldWidth; ++i)
      field.setCharAt(i, ' ');
    field.replace(fieldWidth-string.length(), fieldWidth, string);
    return field.toString();
  }

}
