package pas.huffman;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A utility to compete with applications file compression algorithms like 7-Zip, gzip, WinZip etc.
 *
 * @author Aiden Smith
 * @version 1.0
 */
public class MadZip {

  /**
   * Compresses the specified file and saves the compressed version at the specified location.
   *
   * @param source The file to be compressed
   * @param destination The location where the compressed file will be saved
   * @throws IOException If the source file cannot be read or the destination file cannot be written
   */
  public static void zip(File source, File destination) throws IOException {
    // TODO
    // step 1: build the Huff tree from source
    HuffTree huffTree = new HuffTree(source);

    // step 2: encode the source with Huffman codes
    BitSequence encodedData = huffTree.encode(source);

    // step 3: make the HuffmanSave object with the data and map
    HuffmanSave huffSave = new HuffmanSave(encodedData, huffTree.getFrequencies());

    // step 4: Serialize and write to the destination
    try (FileOutputStream fos = new FileOutputStream(destination);
          BufferedOutputStream bos = new BufferedOutputStream(fos);
          ObjectOutputStream oos = new ObjectOutputStream(bos)) {

      oos.writeObject(huffSave);
    }
  }

  /**
   * Decompresses the specified compressed file and saves the uncompressed version at the specified
   * location.
   *
   * @param source The compressed file to be decompressed
   * @param destination The location where the uncompressed file will be saved
   * @throws IOException If the source file cannot be read or the destination file cannot be written
   * @throws ClassNotFoundException If deserialization exception occurs during reconstruction of the
   *         Huffman tree
   */
  public static void unzip(File source, File destination) throws
      IOException, ClassNotFoundException {
    // TODO
    // step 1: deserialize the HuffmanSave from source
    HuffmanSave huffSave;
    try (FileInputStream fis = new FileInputStream(source);
          BufferedInputStream bis = new BufferedInputStream(fis);
          ObjectInputStream ois = new ObjectInputStream(bis)) {

      huffSave = (HuffmanSave) ois.readObject();
    }

    // step 2: construct the Huffman tree using the frequency map
    HuffTree huffTree = new HuffTree(huffSave.getFrequencies());

    // step 3: Decode the data, then write to the destination
    huffTree.decode(huffSave.getEncoding(), destination);
  }
}
