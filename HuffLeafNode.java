package pas.huffman;

/**
 * .
 *
 * @author Aiden Smith
 * @version 1.0
 */

public class HuffLeafNode extends HuffBaseNode {

  private byte symbol;

  public HuffLeafNode(byte symbol, int weight) {
    super(weight);
    this.symbol = symbol;
  }

  public byte getSymbol() {
    return symbol;
  }

  /**
   * Leaf nodes are already at the lowest symbol.
   *
   * @return Leaf node sym.
   */
  @Override
  public byte getLowestSymbol() {
    return symbol;
  }

}
