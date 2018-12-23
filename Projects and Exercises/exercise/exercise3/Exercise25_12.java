public class Exercise25_12 {
  private BST<Integer> tree = new BST<>();

  public Exercise25_12() {
    tree.insert(50);
    tree.insert(25);
    tree.insert(75);
    tree.insert(12);
    tree.insert(30);
    tree.insert(70);
    tree.insert(80);
    tree.insert(110);
    tree.insert(10);
    
    tree.inorder();
    System.out.println();
    
    tree.preorder();
    System.out.println();
    
    tree.postorder();
    System.out.println();
       
    tree.delete(75);
    tree.inorder();
    System.out.println();
    
    tree.preorder();
    System.out.println();
    
    tree.postorder();
    System.out.println();
        
    tree.delete(50);
    tree.inorder();
    System.out.println();
    
    tree.preorder();
    System.out.println();
    
    tree.postorder();
    System.out.println();
  }
  
  public static void main(String[] args) {
    new Exercise25_12();
  }
}