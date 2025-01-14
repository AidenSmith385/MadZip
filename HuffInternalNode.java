package pas.huffman;

/**
 * .
 *
 * @author Aiden Smith
 * @version 1.0
 */

public class HuffInternalNode extends HuffBaseNode {

  private HuffBaseNode left;
  private HuffBaseNode right;

  /**
   * Consturctor method for the internal nodes.
   *
   * @param left left child node
   * @param right right child node
   * @param weight combined frequency
   */
  public HuffInternalNode(HuffBaseNode left, HuffBaseNode right, int weight) {
    super(weight);
    this.left = left;
    this.right = right;
  }

  public HuffBaseNode getLeft() {
    return left;
  }

  public HuffBaseNode getRight() {
    return right;
  }


  /**
   * Get the lowest byte value in the subtree rooted at this  node.
   * (minimum of the left and right).
   *
   * @return The lowest byte value.
   */
  @Override
  public byte getLowestSymbol() {
    int leftSym = left.getLowestSymbol();
    int rightSym = right.getLowestSymbol();
    return (byte) Math.min(leftSym, rightSym);
  }
}
