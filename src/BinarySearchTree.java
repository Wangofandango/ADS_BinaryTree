import java.util.ArrayList;

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
    BinarySearchTreeNode curr = root;
    BinarySearchTreeNode prev = null;

    // Check if the key is actually
    // present in the BST.
    // the variable prev points to
    // the parent of the key to be deleted.
    while (curr != null && curr.getElement() != element) {
      prev = curr;

      if (curr.compareTo(element) > 0)
        curr = (BinarySearchTreeNode) curr.getLeftChild();
      else
        curr = (BinarySearchTreeNode) curr.getRightChild();
    }

    if (curr == null) {

      return false;
    }

    // Check if the node to be
    // deleted has atmost one child.
    if (curr.getLeftChild() == null || curr.getRightChild() == null) {

      // newCurr will replace
      // the node to be deleted.
      BinarySearchTreeNode newCurr;

      // if the left child does not exist.
      if (curr.getLeftChild() == null)
        newCurr = (BinarySearchTreeNode) curr.getRightChild();
      else
        newCurr = (BinarySearchTreeNode) curr.getLeftChild();

      // check if the node to
      // be deleted is the root.
      if (prev == null)
        return true;

      // check if the node to be deleted
      // is prev's left or right child
      // and then replace this with newCurr
      if (curr == prev.getLeftChild())
        prev.addLeftChild(newCurr);
      else
        prev.addRightChild(newCurr);
    }

    // node to be deleted has
    // two children.
    else {
      BinarySearchTreeNode p = null;
      BinarySearchTreeNode temp;

      // Compute the inorder successor
      temp = (BinarySearchTreeNode) curr.getRightChild();
      while (temp.getLeftChild() != null) {
        p = temp;
        temp = (BinarySearchTreeNode) temp.getLeftChild();
      }


      if (p != null)
        p.addLeftChild(temp.getRightChild());


      else
        curr.addRightChild(temp.getRightChild());

      curr.setElement(temp.getElement());
    }
    return true;
  }

  public T findMin()
  {
    BinarySearchTreeNode current = root;
    while (current.getLeftChild() != null)
    {
      current = (BinarySearchTreeNode) current.getLeftChild();
    }
    return (T) current.getElement();
  }

  public T findMax()
  {
    BinarySearchTreeNode current = root;
    while (current.getRightChild() != null)
    {
      current = (BinarySearchTreeNode) current.getRightChild();
    }
    return (T) current.getElement();
  }

  public boolean contains(T element){
    BinarySearchTreeNode current = root;
    while (current != null)
    {
      int comparedValue = current.compareTo(element);
      if (comparedValue > 0)
      {
        current = (BinarySearchTreeNode) current.getLeftChild();
      }
      else if (comparedValue < 0)
      {
        current = (BinarySearchTreeNode) current.getRightChild();
      }
      else
      {
        return true;
      }
    }
    return false;
  }

  public void rebalance(){
    ArrayList<T> inOrderList = inOrder();
    //Make a new tree
    BinarySearchTree<T> newTree = new BinarySearchTree<>();

    //Check if the arraylist size is event or odd
    if (inOrderList.size() % 2 == 0)
    {
      //If the size is even, make the root the element in the middle of the arraylist -1
      newTree.setRoot(new BinarySearchTreeNode(inOrderList.get(inOrderList.size()/2 - 1)));
    }
    else
    {
      //Make the root of the tree the center of the arraylist
      newTree.setRoot(
          new BinarySearchTreeNode(inOrderList.get(inOrderList.size() / 2)));
    }

  }
}
