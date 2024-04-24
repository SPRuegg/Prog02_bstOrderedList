import java.util.Arrays;

/**
 * bstOrderedList Class. A customized data structure similar to a
 * BinarySearchTree
 * 
 * CSC 1351 Programming Project No 2
 * Section 2
 * 
 * @author Shane Ruegg
 * @since 03/27/24
 * 
 */
public class bstOrderedList <T> {

	private Node<T> root; // The head of the tree, in which all other nodes are placed
	
	private int nodes; // The current number of nodes in the tree
	
	private int indexForArray; // The current index for whatever the array specifies
	
	private Comparable<T>[] arr; // The arr that will be in the order the user specifies
	
	private final int SIZEINCREMENTS = 20; // The amount the array will increase for larger sizes of nodes
	
	@SuppressWarnings("unchecked")
	public bstOrderedList() {
		root = null;
		nodes = 0;
		indexForArray = 0;
		arr = new Comparable[SIZEINCREMENTS];
	}
	
	@SuppressWarnings("unchecked")
	void add(Comparable<T> newObject) {
		Node<T> newNode = new Node<T>();
		newNode.data = newObject;
        // Creates a node separate from the tree
        // this is used as a temp holding the value
        // that the user is trying to add
        if(root == null) {
            root = newNode; //If root's value hasn't been set
                            //this sets it
        }
        else {
            Node<T> focusNode = root; //Otherwise, we make another temp
            
            Node<T> parent; //Declared but not set, because we don't
                         //know what to set it to
            
            while(true) {
                parent = focusNode; //
                
                if(newObject.compareTo((T) focusNode.data) < 0) {
                    focusNode = focusNode.leftChild;
                    
                    if(focusNode == null) {
                        parent.leftChild = newNode;
                        nodes++;
                        return;
                    }
                }
                else {
                    focusNode = focusNode.rightChild;
                    
                    if(focusNode == null) {
                        parent.rightChild = newNode;
                        nodes++;
                        return;
                    }
                }
            }
        }
	}
	
	void remove(Comparable<T> data) {
		root = recursiveRemove(root, data);
		nodes--;
	}
	
	@SuppressWarnings("unchecked")
	private Node<T> recursiveRemove(Node<T> root, Comparable<T> data) {
        // Base case
        if (root == null)
            return root;
 
        // Locate Node
        if (root.data.compareTo((T) data) > 0) { // Then Traverse the Left branch
            root.leftChild = recursiveRemove(root.leftChild, data);
            return root;
        } else if (root.data.compareTo((T) data) < 0) { // Then Traverse the Right branch
            root.rightChild = recursiveRemove(root.rightChild, data);
            return root;
        }
        
        // At this point the node is located
 
        // If one or both of the children is null
        if (root.leftChild == null) {
            Node<T> temp = root.rightChild; // If no right child either it will just make the node null
            return temp;
        } else if (root.rightChild == null) {
            Node<T> temp = root.leftChild;
            return temp;
        }
        
        // If both children exist, using predecessors
        else {
        	
        	
 
            Node<T> predParent = root; // Will be the parent of the node that will
                                       // replace the desired deletion node
 
            Node<T> pred = root.leftChild; // The node that will replace the desired
            								// deletion node. (Data must be greater
            								// than or equal to the desired deletion
            								// node.
            
            while (pred.rightChild != null) { // Will set succParent equal to the parent node of the next greatest
            	predParent = pred;			 // node of root, and pred to the next greatest node of root.
            	pred = pred.leftChild;
            }
            
            // At this point the next lowest node has been located and is equal to pred.
            
            if (predParent != root) {
            	predParent.rightChild = pred.leftChild; // If we are at the next highest node then, the next
                										// highest node's predParent's left child will be it's only
                										// remaining grandchild.
            } else {
            	predParent.leftChild = pred.leftChild; // If we didn't traverse down the tree, then the predParent's
            }   	                              	   // right child will now be equal to its right grandchild
            
            
            // The reason this works is because predParent.rightChild & predParent.leftChild are actual grandchildren
            // of root, because the original predParent had the same pointers as root.
            
            
            root.data = pred.data; // Replaces the value of the deletion node, with its just lowest child.
            
            return root;
        }
	}
	
	@SuppressWarnings("unchecked")
	public Node<T> findNode(Comparable<T> data) {
        Node<T> focusNode = root;
        
        while(focusNode.data != data) {
            if(data.compareTo((T) focusNode.data) < 0) {
                focusNode = focusNode.leftChild;
            }
            else {
                focusNode = focusNode.rightChild;
            }
            if(focusNode == null) {
                return null;
            }
        }
        return focusNode;
    }
	
	int size() {
		return nodes;
	}
	
	@SuppressWarnings("unchecked")
	Comparable<T>[] toArray(String sorting) {
		while(nodes >= arr.length) {
			arr = new Comparable[arr.length + SIZEINCREMENTS];
		}
		if(nodes == 0) {
			System.out.println("No nodes");
			return arr;
		}
		switch(sorting.toUpperCase()) {
		case "PREORDER":
			preOrderTraversalTreeArray(root);
			indexForArray = 0; // Reset
			return arr;
		case "INORDER":
			inOrderTraversalTreeArray(root);
			indexForArray = 0; // Reset
			return arr;
		case "POSTORDER":
			postOrderTraversalTreeArray(root);
			indexForArray = 0; // Reset
			return arr;
		}
		
		System.out.println("Sorting Method Invalid;");
		return arr;
	}
	
	private void inOrderTraversalTreeArray(Node<T> focusNode) {
        if(focusNode != null) {
        	inOrderTraversalTreeArray(focusNode.leftChild);
            
            arr[indexForArray++] = focusNode.data;
            
            inOrderTraversalTreeArray(focusNode.rightChild);
        }
    }
	
	private void preOrderTraversalTreeArray(Node<T> focusNode) {
        if(focusNode != null) {
        	arr[indexForArray++] = focusNode.data;
            
        	preOrderTraversalTreeArray(focusNode.leftChild);
            
        	preOrderTraversalTreeArray(focusNode.rightChild);
        }
    }
	
	private void postOrderTraversalTreeArray(Node<T> focusNode) {
        if(focusNode != null) {
        	postOrderTraversalTreeArray(focusNode.leftChild);
            
        	postOrderTraversalTreeArray(focusNode.rightChild);
            
            arr[indexForArray++] = focusNode.data;
        }
    }

	@Override
	public String toString() {
		return Arrays.toString(arr);
	}
	
}

class Node <T> {
	public Node <T> leftChild;
	public Comparable <T> data;
	public Node <T> rightChild;
}
