public class BinarySearchTreeNode<T> extends BinaryTreeNode<T> implements Comparable<T>
{
  public BinarySearchTreeNode(T element)
  {
    super(element);
  }

  @Override public int compareTo(T o)
  {
    Comparable<T> comparable = (Comparable<T>) super.getElement();
    return comparable.compareTo(o);
  }


}
