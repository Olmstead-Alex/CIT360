package ACP4;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;

public class JSONInputStream extends JSONStream {
    JSONParser aParser;

    public JSONInputStream(InputStream theByteStream) {
        if (theByteStream == null) {
            throw new NullPointerException();
        } else {
            InputStreamReader inReader = new InputStreamReader(theByteStream);
            this.aParser = new JSONParser(inReader);
        }
    }

    public Object readObject() throws JSONException, ParseException {
        if (this.theProtector != null) {
            try {
                this.theProtector.claim();
            } catch (InterruptedException var3) {
                throw new JSONException("Calling Thread interupted", var3);
            }
        }

        try {
            Object parsedObject = this.aParser.parse();
            if (this.theProtector != null) {
                this.theProtector.free();
            }

            return parsedObject;
        } catch (IOException var2) {
            throw new JSONException("Unable to read JSON. ", var2);
        }
    }

    public void close() throws IOException {
        this.aParser.closeReader();
    }
}
