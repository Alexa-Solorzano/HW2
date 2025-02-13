
/*
 * *** Alexa Solorzano COMP 272 001 ***
 *
 * Homework # 2 (Programming Assignment). This Java class defines a few basic
 * manipulation operations of a binary trees.
 *
 * ONLY MODIFY THIS FILE (NOT 'Main.Java')
 *
 */

import java.util.Queue;
import java.util.LinkedList;

/*
 * Class BinaryTree
 *
 * This class defines a binary tree object; it is a tree structure where every
 * node as at most two child nodes, which form the tree branches. That implies
 * that each node within the tree has a degree of 0, 1, or 2. A node of degree
 * zero (0) is called a terminal node, or leaf node.
 *
 * Each non-leaf node is often called a branch node, which will have  either one or
 * two children (a left and right child node). There is no order guarantee within
 * this basic binary tree object. Given that this binary object is NOT a Binary Search Tree (BST), there is
 * no guarantee on order in the tree.
 *
 * As just stated, the insert method does NOT guarantee the order within the tree, but
 * its logic attempts to follow the rules of BSTs -- meaning the insert method will traverse
 * the binary tree searching for a location to insert the new Node using traversal
 * logic similar to BSTs. But again, this is not a BST, so there is no guarantee that
 * the tree's order maintains that defined by a BST.
 *
 * Public methods:
 *  void deleteTree()      - deletes the tree.
 *  Node insert(int data)  - inserts a new node into the tree containing value 'data'.
 *  String preOrder()      - return the tree in 'preorder' traversal in a String object.
 *
 * The following methods you will complete:
 *  void replaceValue(int k, int l) - if data value 'k' is in tree, replace with data
 *                           value 'l'; for simplicity at the moment, do not re-organize
 *                           the tree based on new value which means this operation may
 *                           violate the binary tree definition.
 *  int findMin()          - returns the small data value stored in the tree.
 *  int nodesGT(int val)   - return the number of nodes in the tree that have a data value
 *                           greater than 'val'.
 *  double average()       - return the average data value of all data values stored in
 *                           the tree.
 */

public class BinaryTree {

    // Constructors
    public BinaryTree() {
        root = null;
    }
    public BinaryTree(Node node) {
        root = node;
    }

    /* 
     * Class Node
     *
     * The node object definition for each node of the bin ary tree.
     */

    static class Node {

        Node(int d) {
            data = d;
            left = null;
            right = null;
        }

        Node(int d, Node l, Node r) {
            data = d;
            left = l;
            right = r;
        }

        public int data; //value (integer value) of the current node as we traverse through the tree
        public Node left;
        public Node right;

    }   /* End Class Node */


    public Node root;

    public void deleteTree() {
        root = null;
    }
    
// replaceValue is a public method calling on private helper method replaceValueHelper -- recursion
    public void replaceValue(int oldVal, int newVal) {
        replaceValueHelper(root, oldVal, newVal);
    }
    
// findMin is a public method calling on private helper method findMinHelper -- recursion 
    public int findMin() {
        return findMinHelper(root);
    }

// nodestGT is a public method calling on private helper method nodesGTHelper -- recursion
    public int nodesGT(int val) {
        return nodesGTHelper(root, val);
    }


    /*
     * public method insert
     *
     * The method will insert a node into the binary tree containing the value
     * passed in as a parameter, 'data'. This insert routine maintains the
     * form of the binary tree which maintains the property of a 'complete binary'
     * tree.
     *
     * The property basically implies that for every node in the tree:
     *   1) every node in the tree has 2 children, except for possibly the last level.
     *   2) and on the last level, all nodes are as far left as possible.
     *
     * There are no order properties of a basic binary tree.
     *
     * This method uses a breath first search of the binary tree to locate the
     * location of where to insert the new node. This approach basically starts at
     * the root, and searches level by level until the next free spot for the insertion.
     * This approach maintains the 'complete tree' property of the binary tree.
     */

    Node insert(int data) {

        Node tempNode = new Node(data);

        // If tree is empty, insert new node as the root.
        if (root == null)
            return root = tempNode;

        // Create a queue to do level order traversal
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // Do level order traversal
        while (!queue.isEmpty()) {
            Node front = queue.peek();

            if (front.left == null) {
                front.left = tempNode;
                break;
            } else if (front.right == null) {
                front.right = tempNode;
                break;
            } else {
                // If front node in queue has both left and right
                // children, remove it from the queue.

                queue.remove();
            }

            // Enqueue the left and right children of teh current node
            if (front.left != null)
                queue.add(front.left);

            if (front.right != null)
                queue.add(front.right);
        }

        return tempNode;

    } // End method insert


    /*
     * Public method preOrder()
     *
     * This method will generate a String object containing a copy of the tree's
     * data values in preorder traversal format. If tree is empty, and empty
     * String object (e.g., "") is returned. Else the String object contains
     * the data values, separated by a space.
     *
     * This public method is simply wrapper for the preOrderHelper private method
     * which does the actual work. The public wrapper method simply passes the root
     * of the tree to helper method.
     */
    
    public String preOrder() {
        return preOrderHelper(root);
    }

    public String preOrderHelper(Node node) {
        if (node == null) {
            return "";
        }
        return node.data + " " + preOrderHelper(node.left)
                + preOrderHelper(node.right);
    }


    /***********************************************************
     *
     * YOUR CODE GOES BELOW
     *
     * THERE IS NO NEED TO CHANGE ANY CODE ABOVE. DO NOT FORGET TO PLACE
     * YOUR NAME AND SECTION NUMBER AT THE TOP OF THE FILE.
     *
     * YOU ARE TO WRITE THE METHODS:
     *    - replaceValue
     *    - findMin
     *    - NodesGT
     *    - average
     *
     ***********************************************************/


    /*
     * private method replaceValueHelper
     *
     * This method will traverse the tree using a depth first search
     * approach, and for each node found with the value of 'oldVal',
     * replace it (update the value in place), with the provided 'newVal'.
     *
     * Depth first search of the tree is based on recursion. This will result
     * in very few lines of code.
     *
     * 
     * 
     * Protect against a null node 
     * if the current node matches with the old value
     * replace the current node with the new value 
     *
     * in order to check the entire binary tree, we need to use recursion to check the right and left subtrees 
     * replaceValueHelper(nodes to the left, oldVal, newVal)
     * replaceValueHelper(nodes to the right, oldVal, newVal)
     */

    private void replaceValueHelper(Node node, int oldVal, int newVal) {
      if(node == null){
          return;
      }

      if(node.data == oldVal){ // current node matches old value
           node.data = newVal; //replace it with the new value
      }
      replaceValueHelper(node.left, oldVal, newVal); //DFS: recursively check the tree's left & right subtrees
      replaceValueHelper(node.right, oldVal, newVal);
    }


    /*
     * private method findMinHelper()
     *
     * This method will traverse the tree using depth first search traversal and
     * return the minimum data value in the binary tree. If the tree is empty, the
     * value 'Integer.MAX_VALUE' is returned. Recall that this is not a binary
     * search Tree (BST), so it does not have the additional property that the
     * smaller data values always traverse the left child. So that implies all
     * node is this tree must be traversed.
     *
     * Depth first search of the tree is based on recursion. This will result
     * in very few lines of code.
     *
     *
     * if the tree is empty
     * return Integer.MAX_VALUE;
     *
     * start at the current nodes data
     *
     * recursively find the minimum value of the left & right subtrees 
     *
     * return min value among the current data, the left & right
     */

    private int findMinHelper(Node node) {
      if(node == null){
        return Integer.MAX_VALUE;
      }
      int min = node.data; // start with the current nodes
    // DFS: Recursively find the minimum in the left and right subtrees
      int leftMin = findMinHelper(node.left);
      int rightMin = findMinHelper(node.right);

      return Math.min(min, Math.min(leftMin, rightMin)); //find the smallest value between current nodes and the smallest between the subtrees
    }


    /*
     * private method nodeGTHelper()
     *
     * This method will traverse the tree using depth first search traversal and
     * return a count on the number of nodes that contain a data value larger
     * than the parameter 'val'.
     *
     * If the tree is empty, return 0.
     *
     * Depth first search of the tree is based on recursion. This will result
     * in very few lines of code.
     *
     * if the tree is empty(null)
     * return 0
     *
     * declare variable to keep track on the number of nodes that contain a data value larger than 'val'
     *
     * if current data value is greater than val 
     * incrementally increase the count
     *
     * recursively move through the left & right subtrees, additing to the count 
     * 
     * return count
     */

    private int nodesGTHelper(Node node, int val) {
      if(node == null){
        return 0;
      }
      int count = 0; //keep track of how many nodes are greater than the value val

      if(node.data > val){ // if the current node's value is greater than val
           count++; //increase the counter each time there is a value greater than val
       }
        // DFS: Recursively check the left & right subtrees & addint to the count (tracker)
      count += nodesGTHelper(node.left, val); 
      count += nodesGTHelper(node.right, val);
        
      return count; // return the total amount of nodes that are greater than val
    }


    /*
     * public method average()
     *
     * This method will traverse the tree using depth first search traversal and
     * return the average value contained in the binary tree. To easily perform a depth
     * first traversal, it invokes the helper method, averageHelper(), which is the
     * method that should be called recursively. If the tree is empty, 0 should be
     * returned.
     *
     * IMPORTANT NOTE:
     * The helper method should return an array of two integer values. In index
     * location [0] is the sum of all data values in the tree. And in index
     * location [1] is the count of nodes.
     *
     * As can be seen in the method average() immediately below, the returned average
     * value is calculated as "sum / count".
     *
     * Depth first search of the tree is based on recursion. This will result
     * in very few lines of code within the helper method.
     *
     * if the tree is empty
     * return the array with [0,0]
     *
     * declare a varible to keep track of how many nodes there are 
     * declare a variable to hold the sum of all the nodes 
     * 
     * recursively move through the left & right subtrees while getting the respective sum & count 
     * 
     * add the current nodes data to the sum and increment the count 
     *
     * return sum and count in an array
     */

    public double average() {
        int[] sumAndCount = averageHelper(root);
        return (double) sumAndCount[0] / sumAndCount[1];
    }

    private int[] averageHelper(Node n) {
      if(n == null){
        return new int[]{0, 0};
      }
        
      int sum = 0; //declare variable to keep track of the sum
      int count = 0; //declare variable to keep track of the count
    //recursively traverse the left subtree 
      int[] left = averageHelper(n.left); 
      sum += left[0]; //add the left subtree sum
      count =+ left[1]; //add the left subtree count

    //recursively traverse the right subtree
      int[] right = averageHelper(n.right);
      sum += right[0]; //add the right subtree sum 
      count += right[1]; //add the right subtree count

      sum += n.data; //add current node's data to the sum 
      count++; //increment the count

      return new int[]{sum, count}; //return sum & count in an array
    }
        
}
