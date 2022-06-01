package collection;

/** Represents a node of singly linked list.
 * @author Stepan Karakhanov
 * @author stepan.karakhanov@mail.ru (for angry messages)
 * @version 1.0
 */
public class Node<T> {
  private Node<T> nextElement;
  private T value;

  public Node(T value) {
    this.value = value;
    this.nextElement = null;
  }

  public Node<T> getNextElement() {
    return nextElement;
  }

  public void setNextElement(Node<T> nextElement) {
    this.nextElement = nextElement;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
