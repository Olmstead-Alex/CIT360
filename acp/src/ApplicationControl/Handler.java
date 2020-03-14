package ApplicationControl;

import java.util.HashMap;

// Handler interface used by the Handler instances.
public interface Handler {
    public Object handleIt(HashMap<String, Object> data);
}