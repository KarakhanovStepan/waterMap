package collection;

public class Main {

  public static void main(String[] args) {
    LinkedList<String> list = new LinkedList<>();

//    System.out.println("\nAdd section:");
//    list.add("first");
//    list.add("third");
//    list.addTo(1, "second");
//
//    System.out.println("List size: " + list.size());
//    System.out.println(list);
//
//    System.out.println(list.contains("fourth"));
//    System.out.println(list.contains("first"));
//
//    System.out.println("\nGet section:");
//    System.out.println(list.get(0));
//    System.out.println(list.get(1));
//    System.out.println(list.get(2));
//    System.out.println(list.get(3));
//    System.out.println(list.getIndexOfElement("first"));
//    System.out.println(list.getIndexOfElement("second"));
//    System.out.println(list.getIndexOfElement("third"));
//    System.out.println(list.getIndexOfElement("fourth"));
//
//    System.out.println("\nDelete section:");
//    System.out.println(list.delete(3));
//    System.out.println(list.delete(1));
//    System.out.println(list.delete(1));
//    System.out.println(list.delete(0));
//    System.out.println(list);


    list.add("a");
    list.add("b");
    list.add("c");
    list.add("d");
    list.add("e");

    System.out.println(list.getElementsWithHighestValue(3));
  }
}
