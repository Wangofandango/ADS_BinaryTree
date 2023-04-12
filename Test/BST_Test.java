import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


public class BST_Test<T> {

    BinarySearchTree testTree = new BinarySearchTree();
    BinarySearchTree emptyTestTree;
    BinarySearchTreeNode testNode;
    BinaryTreePrint binaryTreePrint;

    @BeforeEach
    public void setUp()
    {
        testTree.setRoot(new BinarySearchTreeNode(4));
        testTree.insert(2);
        testTree.insert(1);
        testTree.insert(3);
        testTree.insert(6);
        testTree.insert(5);
        testTree.insert(7);

        binaryTreePrint = new BinaryTreePrint();
        emptyTestTree = new BinarySearchTree();
        testNode = new BinarySearchTreeNode(9);
    }

    @Test
    public void canIEvenRun()
    {
        binaryTreePrint.printTree(testTree.getRoot());
    }


    /*
    BINARY_TREE_NODE TESTS
     */
    @Test
    public void getChildrenTest()
    {
        assertEquals(4,testTree.getRoot().getElement());
        assertEquals(2,testTree.getRoot().getLeftChild().getElement());
        assertEquals(3,testTree.getRoot().getLeftChild().getRightChild().getElement());
        assertEquals(5, testTree.getRoot().getRightChild().getLeftChild().getElement());
    }

    @Test
    public void addChildLeft()
    {
        testTree.getRoot().addRightChild(testNode);

        assertEquals(9,testTree.getRoot().getRightChild().getElement());
    }

    @Test
    public void addChildRight()
    {
        testTree.getRoot().getLeftChild().getLeftChild().addLeftChild(testNode);

        assertEquals(9,testTree.getRoot().getLeftChild().getLeftChild().getLeftChild().getElement());
    }

    @Test
    public void setElement()
    {
        testTree.getRoot().setElement(11);
        assertEquals(11, testTree.getRoot().getElement());
    }


    /*
    BINARY_TREE TEST
     */

    //GetRoot samt SetRoot er tested igennem tidligere tests

    @Test
    public void isEmpty()
    {
        assertEquals(true,emptyTestTree.isEmpty());
        assertEquals(false,testTree.isEmpty());
    }

    @Test
    public void size()
    {
        assertEquals(0,emptyTestTree.getSize());
        assertEquals(7,testTree.getSize());
    }

    @Test
    public void contains()
    {
        assertEquals(true, testTree.contains(7));
        assertEquals(true, testTree.contains(4));
        assertEquals(true, testTree.contains(3));
        assertEquals(false, testTree.contains(11));
    }

    @Test
    public void inOrder()
    {
        ArrayList<T> inOrderList = testTree.inOrder();

        ArrayList<Integer> confirmationList = new ArrayList<>();
        Integer[] otherList = new Integer[]{1,2,3,4,5,6,7};
        Collections.addAll(confirmationList,otherList);

        assertEquals(inOrderList,confirmationList);

    }

    @Test
    public void preOrder()
    {
        ArrayList<T> preOrderList = testTree.preOrder();

        ArrayList<Integer> confirmationList = new ArrayList<>();
        Integer[] otherList = new Integer[]{4,2,1,3,6,5,7};
        Collections.addAll(confirmationList,otherList);

        assertEquals(preOrderList,confirmationList);
    }

    @Test
    public void postOrder()
    {
        ArrayList<T> postOrder = testTree.postOrder();

        ArrayList<Integer> confirmationList = new ArrayList<>();
        Integer[] otherList = new Integer[]{1,3,2,5,7,6,4};
        Collections.addAll(confirmationList,otherList);

        assertEquals(postOrder,confirmationList);
    }

    @Test
    public void levelOrder()
    {
        ArrayList<T> levelOrder = testTree.levelOrder();

        ArrayList<Integer> confirmationList = new ArrayList<>();
        Integer[] otherList = new Integer[]{4,2,6,1,3,5,7};
        Collections.addAll(confirmationList,otherList);

        assertEquals(levelOrder,confirmationList);
    }


    /*
    BINARY_SEARCH_TREE TESTS
     */

    //Insert + Contains er testet ved hhv. setup og andre tests

    @Test
    public void removeElement()
    {
        assertEquals(true,testTree.removeElement(5));
        assertEquals(false,testTree.contains(5));

        assertEquals(true,testTree.removeElement(2));
        assertEquals(false,testTree.contains(2));
        assertEquals(3,testTree.getRoot().getLeftChild().getElement());

        testTree.getRoot().getRightChild().getRightChild().addRightChild(testNode);
        assertEquals(true,testTree.removeElement(7));
        assertEquals(false,testTree.contains(7));
        assertEquals(9,testTree.getRoot().getRightChild().getRightChild().getElement());
    }

    @Test
    public void findMin()
    {
        assertEquals(1,testTree.findMin());
    }

    @Test
    public void findMax()
    {
        assertEquals(7,testTree.findMax());
    }

    @Test
    public void rebalanceUnbalancedRight()
    {
        emptyTestTree.setRoot(new BinarySearchTreeNode(1));
        emptyTestTree.insert(2);
        emptyTestTree.insert(3);
        emptyTestTree.insert(4);
        emptyTestTree.insert(5);
        emptyTestTree.insert(6);
        emptyTestTree.insert(7);
        emptyTestTree.rebalance();

        assertEquals(emptyTestTree.inOrder(),testTree.inOrder());
    }

    @Test
    public void rebalanceUnbalanced()
    {
        emptyTestTree.setRoot(new BinarySearchTreeNode(2));
        emptyTestTree.insert(1);
        emptyTestTree.insert(4);
        emptyTestTree.insert(3);
        emptyTestTree.insert(5);
        emptyTestTree.insert(6);
        emptyTestTree.insert(7);
        emptyTestTree.rebalance();

        assertEquals(emptyTestTree.inOrder(),testTree.inOrder());
    }

}
