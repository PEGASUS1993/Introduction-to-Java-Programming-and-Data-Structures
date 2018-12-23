import java.util.*;

public class Exercise25_01 {
  public static void main(String[] args) {
    new Exercise25_01();
  }

  public Exercise25_01() {
    BST<String> tree = new BST<>(); 
    System.out.print("The height of tree is " + tree.height());
    
    Scanner input = new Scanner(System.in);
    System.out.print("\nEnter strings: ");
    for (int i = 0; i < 6; i++) {
      String s = input.next();
      tree.insert(s.trim());
    }
    System.out.print("\nThe height of tree is " + tree.height());

    tree.insert("Green");
    System.out.print("\nThe height of tree is " + tree.height());

    System.out.println();
    tree.breadthFirstTraversal(); // Traverses the tree
    
    BST<String> tree1 = new BST<>(new String[]
      {"Tom", "George", "Jean", "Jane", "Kevin", "Peter", "Susan", 
        "Jen", "Kim", "Michael", "Michelle"});
    System.out.print("\nThe breadth-first traversal is ");
    tree1.breadthFirstTraversal();
    System.out.print("\nThe height of tree1 is " + tree1.height());
    
    BST<Integer> tree2 = 
      new BST<>(new Integer[]{50, 45, 35, 48, 59, 51, 58});    
    System.out.print("\nThe breadth-first traversal for tree2 is ");
    tree2.breadthFirstTraversal();
    System.out.print("\nThe height of tree2 is " + tree2.height());   
  }

  public class BST<E extends Comparable<E>> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;

    /** Create a default binary tree */
    public BST() {
    }

    /** Create a binary tree from an array of objects */
    public BST(E[] objects) {
      for (int i = 0; i < objects.length; i++)
        insert(objects[i]);
    }


    /**
     * Returns the height of this binary tree. 
     */
    public int height() {
      return height(root);
    }

    private int height(TreeNode root) {
      if (root == null) {
        return -1;
      } 
      else {
        return 1 + Math.max(height(root.left), height(root.right));
      }
    }

    /** Displays the nodes in breadth-first traversal */
    public void breadthFirstTraversal() {
      java.util.LinkedList<TreeNode<E>> queue = 
        new java.util.LinkedList<TreeNode<E>>();
      
      if (root == null) 
        return;
      
      queue.add(root);
      
      while (!queue.isEmpty()) {
        TreeNode<E> node = queue.removeFirst();
        
        System.out.print(node.element + " ");
        if (node.left != null)
          queue.add(node.left);
        if (node.right != null)
          queue.add(node.right);
      }
    }

    /** Returns true if the element is in the tree */
    public boolean search(E e) {
      TreeNode<E> current = root; // Start from the root

      while (current != null) {
        if (e.compareTo(current.element) < 0) {
          current = current.left;
        } else if (e.compareTo(current.element) > 0) {
          current = current.right;
        } else
          // element matches current.element
          return true; // Element is found
      }

      return false;
    }

    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully
     */
    public boolean insert(E e) {
      if (root == null)
        root = createNewNode(e); // Create a new root
      else {
        // Locate the parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null)
          if (e.compareTo(current.element) < 0) {
            parent = current;
            current = current.left;
          } else if (e.compareTo(current.element) > 0) {
            parent = current;
            current = current.right;
          } else
            return false; // Duplicate node not inserted

        // Create the new node and attach it to the parent node
        if (e.compareTo(parent.element) < 0)
          parent.left = createNewNode(e);
        else
          parent.right = createNewNode(e);
      }

      size++;
      return true; // Element inserted
    }

    protected TreeNode<E> createNewNode(E e) {
      return new TreeNode<E>(e);
    }

    /** Inorder traversal from the root */
    public void inorder() {
      inorder(root);
    }

    /** Inorder traversal from a subtree */
    protected void inorder(TreeNode<E> root) {
      if (root == null)
        return;
      inorder(root.left);
      System.out.print(root.element + " ");
      inorder(root.right);
    }

    /** Postorder traversal from the root */
    public void postorder() {
      postorder(root);
    }

    /** Postorder traversal from a subtree */
    protected void postorder(TreeNode<E> root) {
      if (root == null)
        return;
      postorder(root.left);
      postorder(root.right);
      System.out.print(root.element + " ");
    }

    /** Preorder traversal from the root */
    public void preorder() {
      preorder(root);
    }

    /** Preorder traversal from a subtree */
    protected void preorder(TreeNode<E> root) {
      if (root == null)
        return;
      System.out.print(root.element + " ");
      preorder(root.left);
      preorder(root.right);
    }

    /** Inner class tree node */
    public class TreeNode<E extends Comparable<E>> {
      E element;

      TreeNode<E> left;

      TreeNode<E> right;

      public TreeNode(E e) {
        element = e;
      }
    }

    /** Get the number of nodes in the tree */
    public int getSize() {
      return size;
    }

    /** Returns the root of the tree */
    public TreeNode getRoot() {
      return root;
    }

    /** Returns a path from the root leading to the specified element */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
      java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<TreeNode<E>>();
      TreeNode<E> current = root; // Start from the root

      while (current != null) {
        list.add(current); // Add the node to the list
        if (e.compareTo(current.element) < 0) {
          current = current.left;
        } else if (e.compareTo(current.element) > 0) {
          current = current.right;
        } else
          break;
      }

      return list; // Return an array of nodes
    }

    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     */
    public boolean delete(E e) {
      // Locate the node to be deleted and also locate its parent node
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null) {
        if (e.compareTo(current.element) < 0) {
          parent = current;
          current = current.left;
        } else if (e.compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        } else
          break; // Element is in the tree pointed by current
      }

      if (current == null)
        return false; // Element is not in the tree

      // Case 1: current has no left children
      if (current.left == null) {
        // Connect the parent with the right child of the current node
        if (parent == null) {
          root = current.right;
        } else {
          if (e.compareTo(parent.element) < 0)
            parent.left = current.right;
          else
            parent.right = current.right;
        }
      } else {
        // Case 2: The current node has a left child
        // Locate the rightmost node in the left subtree of
        // the current node and also its parent
        TreeNode<E> parentOfRightMost = current;
        TreeNode<E> rightMost = current.left;

        while (rightMost.right != null) {
          parentOfRightMost = rightMost;
          rightMost = rightMost.right; // Keep going to the right
        }

        // Replace the element in current by the element in rightMost
        current.element = rightMost.element;

        // Eliminate rightmost node
        if (parentOfRightMost.right == rightMost)
          parentOfRightMost.right = rightMost.left;
        else
          // Special case: parentOfRightMost == current
          parentOfRightMost.left = rightMost.left;
      }

      size--;
      return true; // Element inserted
    }

    /** Obtain an iterator. Use inorder. */
    public java.util.Iterator<E> iterator() {
      return inorderIterator();
    }

    /** Obtain an inorder iterator */
    public java.util.Iterator<E> inorderIterator() {
      return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {
      // Store the elements in a list
      private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

      private int current = 0; // Point to the current element in list

      public InorderIterator() {
        inorder(); // Traverse binary tree and store elements in list
      }

      /** Inorder traversal from the root */
      private void inorder() {
        inorder(root);
      }

      /** Inorder traversal from a subtree */
      private void inorder(TreeNode<E> root) {
        if (root == null)
          return;
        inorder(root.left);
        list.add(root.element);
        inorder(root.right);
      }

      /** Next element for traversing? */
      public boolean hasNext() {
        if (current < list.size())
          return true;

        return false;
      }

      /** Get the current element and move cursor to the next */
      public Object next() {
        return list.get(current++);
      }

      /** Remove the current element and refresh the list */
      public void remove() {
        delete(list.get(current)); // Delete the current element
        list.clear(); // Clear the list
        inorder(); // Rebuild the list
      }
    }

    /** Remove all elements from the tree */
    public void clear() {
      root = null;
      size = 0;
    }
  }

  public interface Tree<E> extends java.util.Collection<E> {
    /** Return true if the element is in the tree */
    public boolean search(E e);

    /** Insert element o into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E e);

    /** Delete the specified element from the tree
     * Return true if the element is deleted successfully */
    public boolean delete(E e);
    
    /** Get the number of nodes in the tree */
    public int getSize();
    
    /** Inorder traversal from the root*/
    public default void inorder() {
    }

    /** Postorder traversal from the root */
    public default void postorder() {
    }

    /** Preorder traversal from the root */
    public default void preorder() {
    }
    
    @Override /** Return true if the tree is empty */
    public default boolean isEmpty() {
      return size() == 0;
    };

    @Override
    public default boolean contains(Object e) {
      return search((E)e);
    }
    
    @Override
    public default boolean add(E e) {
      return insert(e);
    }
    
    @Override
    public default boolean remove(Object e) {
      return delete((E)e);
    }
    
    @Override
    public default int size() {
      return getSize();
    }
    
    @Override
    public default boolean containsAll(Collection<?> c) {
      // Left as an exercise
      return false;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {
      // Left as an exercise
      return false;
    }

    @Override
    public default boolean removeAll(Collection<?> c) {
      // Left as an exercise
      return false;
    }

    @Override
    public default boolean retainAll(Collection<?> c) {
      // Left as an exercise
      return false;
    }

    @Override
    public default Object[] toArray() {
      // Left as an exercise
      return null;
    }

    @Override
    public default <T> T[] toArray(T[] array) {
      // Left as an exercise
      return null;
    }
  }
}
