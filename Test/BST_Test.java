import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BST_Test {

    BinarySearchTree testTree = new BinarySearchTree();
    BinarySearchTreeNode node;
    BinaryTreePrint binaryTreePrint;

    @BeforeEach
    public void setUp()
    {
        testTree.setRoot(new BinarySearchTreeNode(4));
        testTree.insert(1);
        testTree.insert(2);
        testTree.insert(3);
        testTree.insert(5);
        testTree.insert(6);
        testTree.insert(7);

        binaryTreePrint = new BinaryTreePrint();

        node = new BinarySearchTreeNode(9);
    }

    @Test
    public void canIEvenRun()
    {
        testTree.rebalance();
        binaryTreePrint.printTree(testTree.getRoot());
    }

    @Test
    public void getChildrenTest()
    {
        assertEquals(4,testTree.getRoot().getElement());
        assertEquals(1,testTree.getRoot().getLeftChild().getElement());
    }



}
