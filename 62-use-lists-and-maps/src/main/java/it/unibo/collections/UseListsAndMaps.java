package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

  private UseListsAndMaps() {
  }

  /**
   * @param s
   *          unused
   */
  public static void main(final String... s) {
    /*
     * 1) Create a new ArrayList<Integer>, and populate it with the numbers
     * from 1000 (included) to 2000 (excluded).
     */
    final List<Integer> arrList = new ArrayList<>();
    for (int i = 1000; i < 2000; i++) {
      arrList.add(i);
    }
    /*
     * 2) Create a new LinkedList<Integer> and, in a single line of code
     * without using any looping construct (for, while), populate it with
     * the same contents of the list of point 1.
     */
    final List<Integer> linkedList = new LinkedList<>(arrList);
    /*
     * 3) Using "set" and "get" and "size" methods, swap the first and last
     * element of the first list. You can not use any "magic number".
     * (Suggestion: use a temporary variable)
     */
    final int temp = linkedList.get(0);
    linkedList.set(0, linkedList.get(linkedList.size() - 1));
    linkedList.set(linkedList.size() - 1, temp);
    /*
     * 4) Using a single for-each, print the contents of the arraylist.
     */

    for (final int integer : linkedList) {
      System.out.print(integer + " ");
    }
    /*
     * 5) Measure the performance of inserting new elements in the head of
     * the collection: measure the time required to add 100.000 elements as
     * first element of the collection for both ArrayList and LinkedList,
     * using the previous lists. In order to measure times, use as example
     * TestPerformance.java.
     */
    long time = System.nanoTime();

    for (int i = 0; i < 100000; i++) {
      arrList.add(0, i);
    }

    time = System.nanoTime() - time;
    var millis = TimeUnit.NANOSECONDS.toMillis(time);
    System.out.println("\nTime elapsed for arrList (add in head pos):" + millis + "ms");

    time = System.nanoTime();

    for (int i = 0; i < 100000; i++) {
      linkedList.add(0, i);
    }

    time = System.nanoTime() - time;
    millis = TimeUnit.NANOSECONDS.toMillis(time);
    System.out.println("\nTime elapsed for linkedList (add in head pos):" + millis + "ms");

    /*
     * 6) Measure the performance of reading 1000 times an element whose
     * position is in the middle of the collection for both ArrayList and
     * LinkedList, using the collections of point 5. In order to measure
     * times, use as example TestPerformance.java.
     */

    time = System.nanoTime();

    for (int i = 0; i < 100000; i++) {
      arrList.get(arrList.size() / 2);
    }

    time = System.nanoTime() - time;
    millis = TimeUnit.NANOSECONDS.toMillis(time);
    System.out.println("\nTime elapsed for arrList (read center elem)" + millis + "ms");

    time = System.nanoTime();

    for (int i = 0; i < 100000; i++) {
      linkedList.get(linkedList.size() / 2);
    }

    time = System.nanoTime() - time;
    millis = TimeUnit.NANOSECONDS.toMillis(time);
    System.out.println("\nTime elapsed for linkedList (read center elem):" + millis + "ms");
    /*
     * 7) Build a new Map that associates to each continent's name its
     * population:
     *
     * Africa -> 1,110,635,000
     *
     * Americas -> 972,005,000
     *
     * Antarctica -> 0
     *
     * Asia -> 4,298,723,000
     *
     * Europe -> 742,452,000
     *
     * Oceania -> 38,304,000
     */
    final Map<String, Long> populationMap = new HashMap<>();
    populationMap.put("Asia", 4298723000l);
    populationMap.put("Africa", 1110635000l);
    populationMap.put("Americas", 972005000l);
    populationMap.put("Antartica", 0l);
    populationMap.put("Europe", 742452000l);
    populationMap.put("Oceania", 38304000l);

    /*
     * 8) Compute the population of the world
     */
    for (final var pair : populationMap.entrySet()) {
      System.out.println(pair.getKey() + " : " + pair.getValue());
    }
  }
}
