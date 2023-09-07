package gol.utils;

public class Logger {
  private Logger() {}

  public static void logConfigurationValue(String message, String value) {
    System.out.println(message + ':' + value);
  }

  public static void logConfigurationValue(String message, int value) {
    System.out.println(message + ':' + value);
  }

  public static void logGridCellValue(int value) {
    System.out.println('|' + value + '|');
  }

  public static void logNewLine() {
    System.out.println();
  }
}
