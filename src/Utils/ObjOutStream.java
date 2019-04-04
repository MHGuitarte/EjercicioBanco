package Utils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author M.H. Guitarte <guimhur@gmail.com>
 */
public class ObjOutStream extends ObjectOutputStream {

    public ObjOutStream(OutputStream out) throws IOException {
        super(out);
    }

    protected ObjOutStream() throws IOException, SecurityException {
        super();
    }

    protected void writeStreamHeader() throws IOException {
    }

}
