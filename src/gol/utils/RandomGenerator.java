package gol.utils;

import java.util.Random;

public class RandomGenerator {
  private static final Random RANDOM = new Random();

  private RandomGenerator() {}

  public static int generateRandomNumber(int min, int max) {
    return RANDOM.nextInt(max) + min;
  }
}
