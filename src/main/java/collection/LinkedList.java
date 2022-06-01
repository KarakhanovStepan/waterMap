package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Represents a singly linked list.
 * @author Stepan Karakhanov
 * @author stepan.karakhanov@mail.ru (for angry messages)
 * @version 1.0
 */
public class LinkedList<T extends Comparable> {

  private Node<T> firstElement;
  private Node<T> lastElement;
  private int elementsCount;

  /**
   * Adds new element to the list.
   * @param  newElement Value of new element
   */
  public void add(T newElement) {
    Node<T> newNode = new Node<>(newElement);

    if (this.lastElement == null) {
      this.firstElement = newNode;
    } else {
      this.lastElement.setNextElement(newNode);
    }
    this.lastElement = newNode;

    this.elementsCount++;
  }

  /**
   * Inserts an element at a specific location in the list
   * @param  index Index of new element
   * @param  newElement Value of new element
   */
  public void addTo(int index, T newElement) {
    if(index > this.elementsCount || index < 0) {
      throw new IllegalArgumentException("Index can not be below zero, or greater than list size.");
    }

    Node<T> newNode = new Node<>(newElement);

    //If the list is empty, just add the new value to the list
    if(this.elementsCount == 0) {
      this.firstElement = newNode;
      this.lastElement = newNode;

      elementsCount++;
      return;
    }

    //Insertion at the beginning of the list.
    if(index == 0) {
      newNode.setNextElement(this.firstElement);
      this.firstElement = newNode;

      elementsCount++;
      return;
    }

    //Insertion at the end of the list.
    if(index == elementsCount) {
      lastElement.setNextElement(newNode);

      elementsCount++;
      return;
    }

    Node<T> previousElement = this.firstElement;
    Node<T> currentElement = this.firstElement;

    for(int i = 0; i < index; i++) {
      previousElement = currentElement;
      currentElement = currentElement.getNextElement();
    }

    previousElement.setNextElement(newNode);
    newNode.setNextElement(currentElement);

    elementsCount++;
  }

  /**
   * Returns an element by the index
   * or null if list is empty or index is incorrect
   * @param  indexOfElement Index of element
   * @return Element
   */
  public T get(int indexOfElement){
    if(this.elementsCount == 0) {
      return null;
    }

    if(indexOfElement >= this.elementsCount || indexOfElement < 0) {
      return null;
    }

    Node<T> result = this.firstElement;

    for(int i = 0; i < indexOfElement; i++) {
      result = result.getNextElement();
    }

    return result.getValue();
  }

  /**
   * Returns index of element if it is exists.
   * Returns -1 if no such element.
   * @param  searchElement Desired value
   * @return Index of element
   */
  public int getIndexOfElement(T searchElement) {
    if(this.elementsCount == 0) {
      return -1;
    }

    Node<T> currentElement = this.firstElement;

    for(int i = 0; i < this.elementsCount; i++) {
      if (currentElement.getValue().equals(searchElement)) {
        return i;
      }
      currentElement = currentElement.getNextElement();
    }

    return -1;
  }

  /**
   * Checks if list contains element
   * @param  searchElement Desired value
   * @return Is list contains element
   */
  public boolean contains(T searchElement) {
    if(this.elementsCount == 0) {
      return false;
    }

    Node<T> currentElement = this.firstElement;

    for(int i = 0; i < this.elementsCount; i++) {
      if (currentElement.getValue().equals(searchElement)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Deletes element by index and return it
   * @param  index Index of element
   * @return Deleted element or null if index is incorrect
   */
  public T delete(int index) {
    if(this.elementsCount == 0) {
      return null;
    }

    if(index >= this.elementsCount || index < 0) {
      return null;
    }

    Node<T> deletedElement;

    if(index == 0) {
      deletedElement = this.firstElement;

      if (this.firstElement == this.lastElement) {
        this.firstElement = null;
        this.lastElement = null;
      } else {
        this.firstElement = this.firstElement.getNextElement();
      }

      this.elementsCount--;

      return deletedElement.getValue();
    }

    Node<T> previousElement = this.firstElement;
    deletedElement = this.firstElement;

    for(int i = 0; i < index; i++) {
      previousElement = deletedElement;
      deletedElement = deletedElement.getNextElement();
    }

    previousElement.setNextElement(deletedElement.getNextElement());

    this.elementsCount--;

    return deletedElement.getValue();
  }

  /**
   * @return Current size of list
   */
  public int size() {
    return this.elementsCount;
  }

  /**
   * Returns a list of elements with the highest value
   *
   * @param  numberOfElements the location of the image, relative to the url argument
   * @return      list of elements
   */
  public List<T> getElementsWithHighestValue(int numberOfElements) {
    if(this.elementsCount == 0 || numberOfElements < 0) {
      return Collections.emptyList();
    }

    ArrayList<T> array = new ArrayList<>(numberOfElements);

    Node<T> currentNode = firstElement;

    if(numberOfElements > this.elementsCount) {
      while (currentNode.getNextElement() != null) {
        array.add(currentNode.getValue());
        currentNode = currentNode.getNextElement();
      }

      array.add(currentNode.getValue());
    } else {
      for(int i = 0; i < this.elementsCount; i++) {

        if(array.size() < numberOfElements) {
          array.add(currentNode.getValue());
        } else {

          //This part searches for the smallest element in the array
          //Maybe better to make separate method
          int indexOfSmallestElement = -1;
          T valueOfTheSmallestElement = null;

          for(int j = 0; j < array.size(); j++) {
            T currentElement = array.get(j);

            if (currentElement.compareTo(currentNode.getValue()) < 0) {
              if(valueOfTheSmallestElement == null) {
                valueOfTheSmallestElement = currentElement;
                indexOfSmallestElement = j;
              } else {
                if(currentElement.compareTo(valueOfTheSmallestElement) < 0) {
                  valueOfTheSmallestElement = currentElement;
                  indexOfSmallestElement = j;
                }
              }
            }
          }
          if(valueOfTheSmallestElement != null) {
            array.remove(indexOfSmallestElement);
            array.add(indexOfSmallestElement, currentNode.getValue());
          }
        }

        currentNode = currentNode.getNextElement();
      }
    }

    return array;
  }

  @Override
  public String toString() {
    if(this.elementsCount == 0) {
      return "[]";
    }

    StringBuilder builder = new StringBuilder("[");

    Node<T> currentElement = firstElement;

    while(currentElement.getNextElement() != null) {
      builder.append(currentElement.getValue()).append(", ");
      currentElement = currentElement.getNextElement();
    }

    builder.append(currentElement.getValue()).append("]");

    return builder.toString();
  }
}
