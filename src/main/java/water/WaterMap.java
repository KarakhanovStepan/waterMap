package water;

import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaterMap {

  public static List<int[]> getWaterMap(String fileName) throws IOException {
    List<int[]> heightsMap = getMapOfHeights(fileName);

    System.out.println("Map of heights:");

    for (int[] line : heightsMap) {
      System.out.println(Arrays.toString(line));
    }

    int maxHeight = getMaxHeight(heightsMap);

    List<int[]> waterMap = new ArrayList<>();

    // Set water level to max on all map
    heightsMap.forEach(line -> {
      int[] waterLine = new int[line.length];
      Arrays.fill(waterLine, maxHeight);
      waterMap.add(waterLine);
    });

    System.out.println("\nWater map:");
    for (int[] line : waterMap) {
      System.out.println(Arrays.toString(line));
    }

    checkIsCellOnEdgeOrHole(heightsMap, waterMap);

    // Number of cells on the map
    int count = heightsMap.size() * heightsMap.get(0).length;

    // This cycle is needed for the "spreading" of water
    for (int i = 0; i < count; i++) {
      setWaterLevel(heightsMap, waterMap);
    }

    return waterMap;
  }

  public static void checkIsCellOnEdgeOrHole(List<int[]> heightsMap, List<int[]> waterMap) {
    for (int i = 0; i < heightsMap.size(); i++) {
      for (int j = 0; j < heightsMap.get(i).length; j++) {
        // If edge, hole or height == waterLevel
        if (i == 0 ||
            j == 0 ||
            i == heightsMap.size() - 1 ||
            j == heightsMap.get(i).length - 1 ||
            heightsMap.get(i)[j] == 0 ||
            heightsMap.get(i)[j] >= waterMap.get(i)[j]) {
          waterMap.get(i)[j] = 0;
        }
      }
    }
  }

  public static void setWaterLevel(List<int[]> heightsMap, List<int[]> waterMap) {
    for (int i = 0; i < heightsMap.size(); i++) {
      for (int j = 0; j < heightsMap.get(i).length; j++) {
        if (waterMap.get(i)[j] != 0) {
          setCellWaterLevel(i, j, heightsMap, waterMap);
        }
      }
    }
  }

  public static void setCellWaterLevel(int height, int width, List<int[]> heightsMap, List<int[]> waterMap) {
    int upHeight = heightsMap.get(height - 1)[width];
    int downHeight = heightsMap.get(height + 1)[width];
    int leftHeight = heightsMap.get(height)[width - 1];
    int rightHeight = heightsMap.get(height)[width + 1];

    int upWater = waterMap.get(height - 1)[width];
    int downWater = waterMap.get(height + 1)[width];
    int leftWater = waterMap.get(height)[width - 1];
    int rightWater = waterMap.get(height)[width + 1];

    // If water all around cell
    if (upWater != 0 && downWater != 0 && leftWater != 0 && rightWater != 0) {
      waterMap.get(height)[width] = min(min(upWater, downWater), min(leftWater, rightWater));
      return;
    }

    int minNearHeight = getMinHeightWhereWaterLevelZero(
        upHeight, upWater, downHeight, downWater,
        leftHeight, leftWater, rightHeight, rightWater);

    int minNearWaterLevel = getMinWaterLevel(upWater, downWater, leftWater, rightWater);

    int waterLevel = min(minNearWaterLevel, minNearHeight);

    if(waterLevel <= heightsMap.get(height)[width]) {
      waterLevel = 0;
    }

    waterMap.get(height)[width] = Math.max(waterLevel, 0);
  }

  public static int getMinHeightWhereWaterLevelZero(
      int upHeight, int upWater,
      int downHeight, int downWater,
      int leftHeight, int leftWater,
      int rightHeight, int rightWater) {
    ArrayList<Integer> list = new ArrayList<>();

    if (upWater == 0)
      list.add(upHeight);

    if (downWater == 0)
      list.add(downHeight);

    if (leftWater == 0)
      list.add(leftHeight);

    if (rightWater == 0)
      list.add(rightHeight);

    return list.stream().min(Integer::compareTo).get();
  }

  public static int getMinWaterLevel(int upWater, int downWater, int leftWater, int rightWater) {
    ArrayList<Integer> list = new ArrayList<>();

    if (upWater != 0)
      list.add(upWater);

    if (downWater != 0)
      list.add(downWater);

    if (leftWater != 0)
      list.add(leftWater);

    if (rightWater != 0)
      list.add(rightWater);

    if(list.isEmpty()) {
      return 0;
    }

    return list.stream().min(Integer::compareTo).get();
  }

  public static int getMaxHeight(List<int[]> map) {
    int maxHeight = 0;

    for (int[] line: map) {
      for (int j : line) {
        if (j > maxHeight) {
          maxHeight = j;
        }
      }
    }

    return maxHeight;
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