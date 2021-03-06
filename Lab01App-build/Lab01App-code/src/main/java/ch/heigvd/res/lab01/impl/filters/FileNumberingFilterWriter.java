package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  private int linenb = 0;
  private int lastChar;
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      for (int i = 0; i < len && off + i < cbuf.length ; i++) {
          write(cbuf[off + i]);
      }
  }

  @Override
  public void write(int c) throws IOException {
      if (0 == linenb) {
          super.write('1');
          super.write('\t');
          linenb++;
      }
      
      if ('\n' == c) {
          super.write(c);
          
          char cc = ((++linenb) + "").charAt(0);
          super.write(cc); // TODO: number
          super.write('\t');
      } else if ('\r' == lastChar) {
          char cc = ((++linenb) + "").charAt(0);
          super.write(cc); // TODO: number
          super.write('\t');
          
          super.write(c);
          
      } else {
          super.write(c);
      }
      
     
      lastChar = c;
  }

}
