import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T> extends BinaryTree<T>
{
  public boolean insert(T element) {
    BinarySearchTreeNode current, parent;

    BinarySearchTreeNode tempNode = new BinarySearchTreeNode(element);

    if (getRoot() == null) {
      setRoot(tempNode);
      return true;
    } else {
      current = (BinarySearchTreeNode) getRoot();
    }

    while (true) {
      parent = current;

      if (current.compareTo(element) > 0) {
        current = (BinarySearchTreeNode) current.getLeftChild();
        if (current == null) {
          parent.addLeftChild(tempNode);
          return true;
        }

      } else if (current.compareTo(element) < 0) {
        current = (BinarySearchTreeNode) current.getRightChild();

        if (current == null) {
          parent.addRightChild(tempNode);
          return true;
        }
      } else
      {
        return false;
      }

    }
  }

  public boolean removeElement(T element){
    BinarySearchTreeNode curr = (BinarySearchTreeNode) getRoot();
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
    BinarySearchTreeNode current = (BinarySearchTreeNode) getRoot();
    while (current.getLeftChild() != null)
    {
      current = (BinarySearchTreeNode) current.getLeftChild();
    }
    return (T) current.getElement();
  }

  public T findMax()
  {
    BinarySearchTreeNode current = (BinarySearchTreeNode) getRoot();
    while (current.getRightChild() != null)
    {
      current = (BinarySearchTreeNode) current.getRightChild();
    }
    return (T) current.getElement();
  }

  public boolean contains(T element){
    BinarySearchTreeNode current = (BinarySearchTreeNode) getRoot();
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
    // if the array is empty return null
    ArrayList<T> inOrderList = inOrder();

    if (inOrderList.size() == 0) {
      return;
    }

    int n = inOrderList.size();
    int mid = n / 2;
    BinarySearchTreeNode root = new BinarySearchTreeNode(inOrderList.get(mid));
    // initializing queue
    Queue<Object[]> q = new LinkedList<>();
    // push the root and its indices to the queue
    q.add(new Object[] { root,
            new int[] { 0, mid - 1 } });
    q.add(new Object[] {
            root, new int[] { mid + 1, n - 1 } });

    while (!q.isEmpty()) {
      // get the front element from the queue
      Object[] curr = q.poll();

      // get the parent node and its indices
      BinarySearchTreeNode parent = (BinarySearchTreeNode) curr[0];
      int[] indices = (int[])curr[1];
      int left = indices[0];
      int right = indices[1];

      // if there are elements to process and parent
      // node is not null
      if (left <= right && parent != null) {
        mid = (left + right) / 2;
        BinarySearchTreeNode child = new BinarySearchTreeNode(inOrderList.get(mid));

        // set the child node as left or right child
        // of the parent node
        if (parent.compareTo(inOrderList.get(mid)) > 0) {
          parent.addLeftChild(child);
        }
        else {
          parent.addRightChild(child);
        }

        // push the left and right child and their
        // indices to the queue
        q.add(new Object[] {
                child, new int[] { left, mid - 1 } });
        q.add(new Object[] {
                child, new int[] { mid + 1, right } });
      }
    }

    setRoot(root);
    }
  }
