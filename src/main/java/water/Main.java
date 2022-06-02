package water;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {
    String fileName = "src/main/resources/maps/map2.txt";

    List<int[]> waterMap = WaterMap.getWaterMap(fileName);

    System.out.println("\nMap of water after time:");
    for (int[] line : waterMap) {
      System.out.println(Arrays.toString(line));
    }
  }
}
