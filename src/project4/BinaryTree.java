package project4;

/**
 *
 * @author eric
 */
public class BinaryTree implements Treeable {

    private Node root;

    /**
     * Deletes a node from the binary` tree where both children are null and
     * sets the appropriate child of the parent to null
     *
     * @param parent Parent node of the node to be deleted
     * @param leftChild Boolean of whether or not the current node is the left
     * child
     */
    private void deleteNoChildren(Node parent, boolean leftChild) {
        if (parent == root && parent.getLeftChild() == null) {
            root = null;
        } else {
            if (leftChild == true) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
        }
    }

    /**
     * Deletes a node from the binary tree where only one child is null set the
     * appropriate child of the parent to the existing child of the node to
     * delete
     *
     * @param parent Parent of the current node
     * @param leftChild Boolean of whether the current node is the leftchild
     * @param temp Temp node to store the current node
     */
    private void deleteSingleChild(Node parent, boolean leftChild, Node temp) {
        if (temp.getLeftChild() == null) { // Determine which child exists
            if (leftChild) { // Determine path from parent deleted node is on
                parent.setLeftChild(temp.getRightChild()); // Update parent’s left pointer
            } else {
                parent.setRightChild(temp.getRightChild()); // Update parent’s right pointer
            }
        } else {
            if (leftChild) { // Determine path from parent deleted node is on
                parent.setLeftChild(temp.getLeftChild()); // Update parent’s left pointer
            } else {
                parent.setRightChild(temp.getLeftChild()); // Update parent’s right pointer
            }
        }
    }

    /**
     * Deletes a node from the binary tree where both children exist
     *
     * @param parent
     * @param leftChild
     * @param temp
     */
    private void deleteWithChildren(Node parent, boolean leftChild, Node temp) {

    }

    @Override
    public void display(boolean ascending) {
        if (ascending) {
            displayTreeLNR(root);
        } else {
            displayTreeRNL(root);
        }
    }

    private void displayTreeLNR(Node node) {

    }

    private void displayTreeRNL(Node node) {

    }

    /**
     * Inserts the provided state object into the binary tree
     *
     * @param item
     */
    @Override
    public void insert(Node item) {
        if (root == null) {
            root = item;
        } else {
            insert(root, item);
        }
    }

    /**
     * Inserts the provided state object into the binary tree
     *
     * @param current The node within the tree that is being compared against
     * the node to be inserted
     * @param node The node to be inserted
     */
    private void insert(Node current, Node node) {
        //Variable declarations
        int currentNodePopulation, nodePopulation;

        //Variable assignment
        nodePopulation = node.getState().getPopulation();
        currentNodePopulation = current.getState().getPopulation();

        //If nodePopulation is <= to currentNodePopulation, find insertion point
        //on the left branch of currentNode. Else go down the right branch
        if (nodePopulation <= currentNodePopulation) {
            if (current.getLeftChild() == null) {
                current.setLeftChild(node);
            } else {

                //Recursive call
                insert(current.getLeftChild(), node);
            }
        } else {
            if (current.getRightChild() == null) {
                current.setRightChild(node);
            } else {

                //Recursive call
                insert(current.getRightChild(), node);
            }
        }
    }

    /**
     *
     * @return true if the tree is empty
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Removes a State object with the specified population from the Bianry
     * Tree. Note: The isEmpty method should be called first to prevent errors.
     *
     * @param population The population of the State to remove.
     * @return The State object that was removed.
     */
    @Override
    public Node remove(int population) {
        boolean found = false;
        boolean leftChild = false;
        Node currentNode = root;
        int currentNodePopulation = root.getState().getPopulation();

        if (currentNodePopulation == population) {
            found = true;
        }
        while (!found) {
            if (currentNodePopulation == population) {
                found = true;
            } else {
                if (population < currentNodePopulation) {
                    // Value is to the left of current node
                    currentNode = currentNode.getLeftChild();
                    leftChild = true;
                } else {
                    // Value is to the right of current node
                    currentNode = currentNode.getRightChild();
                    leftChild = false;
                }
            }
        }
        if(currentNode.getLeftChild() != null && currentNode.getRightChild() != null){
            //deleteWithChildren(currentNode, leftChild);
        }else if(currentNode.getLeftChild() == null && currentNode.getRightChild() == null){
            deleteNoChildren(currentNode, leftChild);
        }else if(currentNode.getLeftChild() != null || currentNode.getRightChild() != null){
            //deleteSingleChild(currentNode, leftChild);
        }
        return currentNode;
    }
}
