public class BinarySearchTree<T> extends BinaryTree<T>
{
  private BinarySearchTreeNode root;


  public boolean insert(T element){
    BinarySearchTreeNode newNode = new BinarySearchTreeNode(element);

    if (isEmpty())
    {
      root = newNode;
      return true;
    }

    BinarySearchTreeNode current = root;
    BinarySearchTreeNode parentToCurrent = null;
    while(true)
    {
      int comparedValue = current.compareTo(element);

      if (comparedValue > 0){

        current = (BinarySearchTreeNode) root.getRightChild();

        if (current == null)
        {
          parentToCurrent.addRightChild(newNode);
          return true;
        }
      }

      else if (comparedValue < 0){
        current = (BinarySearchTreeNode) root.getLeftChild();
        if (current == null)
        {
          parentToCurrent.addLeftChild(newNode);
          return true;
        }
      }

      //comparedValue == 0
      else {
        return false;
      }
      parentToCurrent = current;
    }
  }

  public boolean removeElement(T element){
    if (isEmpty())
    {
      return false;
    }

    BinarySearchTreeNode current = root;
    BinarySearchTreeNode parentToCurrent = null;
    while(true)
    {
      int comparedValue = current.compareTo(element);

      if (comparedValue > 0){

        current = (BinarySearchTreeNode) root.getRightChild();

        if (current == null)
        {
          return false;
        }
      }

      else if (comparedValue < 0){
        current = (BinarySearchTreeNode) root.getLeftChild();
        if (current == null)
        {

          return false;
        }
      }

      //comparedValue == 0
      else {

        if (current.getLeftChild() == null && current.getRightChild() == null)
        {
          current = null;
        }


        //If current is LESS THAN parent, the left child must be replaced
        else if (current.getLeftChild() == null && current.getRightChild() != null)
        {
          //Set parents (either left or right child) to be the right child of current
        }
        else if(current.getLeftChild() != null && current.getRightChild() == null)
        {
          //Set parent (either left or right child) to be the left child of the current
        }


        return true;
      }
      parentToCurrent = current;
    }
  }
}
