public abstract class AbstractTree<E extends Comparable<E>>
    implements Tree<E> {
  @Override /** Inorder traversal from the root*/
  public void inorder() {
  }

  @Override /** Postorder traversal from the root */
  public void postorder() {
  }

  @Override /** Preorder traversal from the root */
  public void preorder() {
  }

  @Override /** Return true if the tree is empty */
  public boolean isEmpty() {
    return getSize() == 0;
  }
//
//  @Override /** Return an iterator for the tree */
//  public java.util.Iterator<E> iterator() {
//    return null;
//  }
}
