package water;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaterMap {

  public static void main(String[] args) throws IOException {
    List<int[]> mapOfHeights = getMapOfHeights("src/main/resources/maps/map3.txt");

    System.out.println("Map of heights:");

    for (int[] line: mapOfHeights) {
      System.out.println(Arrays.toString(line));
    }

    List<int[]> mapOfWater = new ArrayList<>();

    for (int i = 0; i < mapOfHeights.size(); i++) {
      int[] line = new int[mapOfHeights.get(0).length];
      for (int j = 0; j < mapOfHeights.get(0).length; j++) {
        line[j] = checkCell(i, j, mapOfHeights);
      }
      mapOfWater.add(line);
    }

    System.out.println("\nWater map:");

    for (int[] line: mapOfWater) {
      System.out.println(Arrays.toString(line));
    }
  }

  public static int checkCell(int height, int width, List<int[]> map) {
    // Check if cell on the edge
    if(height == 0 || width == 0 || height == map.size() - 1 || width == map.get(0).length - 1) {
      return 0;
    }

    int cellHeight = map.get(height)[width];

    int up = map.get(height - 1)[width];
    int down = map.get(height + 1)[width];
    int left = map.get(height)[width - 1];
    int right = map.get(height)[width + 1];

    if(up == 0 || down == 0 || left == 0 || right == 0) {
      return 0;
    }

    int waterLevel = getWaterLevel(cellHeight, up, down, left, right);

    if(waterLevel > 0) {
      return waterLevel + cellHeight;
    } else {
      return waterLevel;
    }
  }

  public static int getWaterLevel(int cellHeight, int up, int down, int left, int right) {
    int upHeight = up - cellHeight;
    int downHeight = down - cellHeight;
    int leftHeight = left - cellHeight;
    int rightHeight = right - cellHeight;

    if(upHeight > 0 && downHeight > 0 && leftHeight > 0 && rightHeight > 0) {
      return Math.min(Math.min(upHeight, downHeight), Math.min(leftHeight, rightHeight));
    }

    return 0;
  }

  public static List<int[]> getMapOfHeights(String fileName) throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    String currentLine = reader.readLine();

    ArrayList<int[]> map = new ArrayList<>();

    while(currentLine != null) {
      String[] splittedLine = currentLine.split("");

      int[] line = new int[splittedLine.length];

      for (int i = 0; i < splittedLine.length; i++) {
        line[i] = Integer.parseInt(splittedLine[i]);
      }

      map.add(line);

      currentLine = reader.readLine();
    }

    reader.close();

    return map;
  }
}
