package pas.huffman;

/**
 * .
 *
 * @author Aiden Smith
 * @version 1.0
 */

public abstract class HuffBaseNode implements Comparable<HuffBaseNode> {

  private int weight;

  public HuffBaseNode(int weight) {
    this.weight = weight;
  }

  public int getWeight() {
    return weight;
  }

  @Override
  public int compareTo(HuffBaseNode other) {
    if (getWeight() < other.getWeight()) {
      return -1;
    } else if (getWeight() == other.getWeight()) {
      // NEEDS TO BE FIXED TO GET DETERMINISTIC TIE-BREAKING!
      int thisSym = this.getLowestSymbol();
      int otherSym = other.getLowestSymbol();
      return Integer.compare(thisSym, otherSym); // compares the nodes byte values and returns
    } else {
      return 1;
    }

  }

  /**
   * Gets the lowest byte value in the subtree.
   *
   * @return The byte value.
   */
  public abstract byte getLowestSymbol();

}
