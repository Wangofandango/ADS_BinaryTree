import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<T>
{
  private BinaryTreeNode root;
  private int size;


  public BinaryTree()
  {
    root = null;
    size = 0;
  }

  public BinaryTreeNode getRoot()
  {
    return root;
  }

  public void setRoot(BinaryTreeNode nodeToAdd)
  {
    root = nodeToAdd;
  }

  public boolean isEmpty(){
    return root == null;
  }

  public int getSize()
  {
    return size;
  }

  public boolean contains(T element ){
    return false;
  }

  public ArrayList<T> inOrder(){

      if (isEmpty())
        return null;

      //Stack used for algorithm
      Stack<BinaryTreeNode> s = new Stack<>();

    //Arraylist to return
      ArrayList<T> inOrderList = new ArrayList<>();

      BinaryTreeNode current = root;

      // traverse the tree
      while (current != null || s.size() > 0)
      {

            /* Reach the left most Node of the
            curr Node */
            while (current !=  null)
            {
                /* place pointer to a tree node on
                   the stack before traversing
                  the node's left subtree */
                s.push(current);
                current = current.getLeftChild();
            }

            /* Current must be NULL at this point */
            current = s.pop();

            //Cast to T and add to arraylist
            inOrderList.add((T)current.getElement());



            /* we have visited the node and its
               left subtree.  Now, it's right
               subtree's turn */
        current = current.getRightChild();
      }

      return inOrderList;
  }

  public ArrayList<T> preOrder(){
    //Make an arraylist and print the tree into the arraylist in pre order

    if (isEmpty())
      return null;

    //Stack used for algorithm
    Stack<BinaryTreeNode> s = new Stack<>();

    //Arraylist to return
    ArrayList<T> preOrderList = new ArrayList<>();

    BinaryTreeNode current = root;

    // traverse the tree
    while (current != null || s.size() > 0)
    {

      /* Reach the left most Node of the
      curr Node */
      while (current !=  null)
      {
        /* place pointer to a tree node on
           the stack before traversing
          the node's left subtree */
        s.push(current);
        //Cast to T and add to arraylist
        preOrderList.add((T)current.getElement());
        current = current.getLeftChild();
      }

      /* Current must be NULL at this point */
      current = s.pop();

      /* we have visited the node and its
         left subtree.  Now, it's right
         subtree's turn */
      current = current.getRightChild();
    }

    return preOrderList;
  }

  public ArrayList<T> postOrder(){
    //Make an arraylist and print the tree into the arraylist in post order

    if (isEmpty())
      return null;

    //Stack used for algorithm
    Stack<BinaryTreeNode> s = new Stack<>();

    //Arraylist to return
    ArrayList<T> postOrderList = new ArrayList<>();

    BinaryTreeNode current = root;

    // traverse the tree
    while (current != null || s.size() > 0)
    {
      /* Reach the left most Node of the
      curr Node */
      while (current !=  null)
      {
        /* place pointer to a tree node on
           the stack before traversing
          the node's left subtree */
        s.push(current);
        current = current.getLeftChild();
      }

      /* Current must be NULL at this point */
      current = s.pop();

      /* we have visited the node and its
         left subtree.  Now, it's right
         subtree's turn */
      current = current.getRightChild();

      //Cast to T and add to arraylist
      postOrderList.add((T)current.getElement());
    }

    return postOrderList;
  }

  public ArrayList<T> levelOrder(){
    //make an arraylist and print the tree into the arraylist in level order
    ArrayList<T> levelOrderList = new ArrayList<>();

    if (isEmpty())
      return null;

    // create an empty queue and enqueue the root node
    Queue<BinaryTreeNode> queue = new ArrayDeque<>();
    queue.add(root);

    BinaryTreeNode front = null;

    // loop till queue is empty

    while (!queue.isEmpty())
    {
      // process each node of the current level and enqueue their
      // non-empty left and right child
      front = queue.poll();

      //Cast to T and add to arraylist
      levelOrderList.add((T)front.getElement());

      if (front.getLeftChild() != null) {
        queue.add(front.getLeftChild());
      }

      if (front.getRightChild() != null) {
        queue.add(front.getRightChild());
      }
    }
    return levelOrderList;
  }


  public int height()   {
    // empty tree has a height of 0
    if (isEmpty()) {
      return -1;
    }

    // create an empty queue and enqueue the root node
    Queue<BinaryTreeNode> queue = new ArrayDeque<>();
    queue.add(root);

    BinaryTreeNode front = null;
    int height = 0;

    // loop till queue is empty
    while (!queue.isEmpty())
    {
      // calculate the total number of nodes at the current level
      int size = queue.size();

      // process each node of the current level and enqueue their
      // non-empty left and right child
      while (size-- > 0)
      {
        front = queue.poll();

        if (front.getLeftChild() != null) {
          queue.add(front.getLeftChild());
        }

        if (front.getRightChild() != null) {
          queue.add(front.getRightChild());
        }
      }

      // increment height by 1 for each level
      height++;
    }

    return height;
  }
}
