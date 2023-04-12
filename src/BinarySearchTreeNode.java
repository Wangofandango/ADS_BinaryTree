public class BinarySearchTreeNode<T> extends BinaryTreeNode<T> implements Comparable<T>
{
  private T element;

  public BinarySearchTreeNode(T element)
  {
    super(element);
  }

  @Override public int compareTo(T o)
  {
    Comparable<T> comparable = (Comparable<T>) element;
    return comparable.compareTo(o);
  }


}
