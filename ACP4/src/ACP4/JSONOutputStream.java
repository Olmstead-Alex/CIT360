package ACP4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JSONOutputStream extends JSONStream {
    private BufferedWriter theWriter;
    private int levelCountLimit;
    private boolean isAndroid = false;

    public JSONOutputStream(HttpURLConnection aConnection) throws ProtocolException {
        aConnection.setRequestMethod("POST");
        aConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        aConnection.setUseCaches(false);
        aConnection.setRequestProperty("Content-Language", "en-US");
        aConnection.setUseCaches(false);
        aConnection.setDoInput(true);
        aConnection.setDoOutput(true);
    }

    public JSONOutputStream(OutputStream aStream) {
        if (aStream == null) {
            throw new NullPointerException();
        } else {
            this.theWriter = new BufferedWriter(new OutputStreamWriter(aStream));
            this.levelCountLimit = 30;

            try {
                Object aContainer = Class.forName("java.awt.Container");
                if (aContainer == null) {
                    this.isAndroid = true;
                }
            } catch (Exception var3) {
                this.isAndroid = true;
            }

        }
    }

    public void writeObject(Serializable aSerializableObject) throws JSONException, IOException {
        if (this.theProtector != null) {
            try {
                this.theProtector.claim();
            } catch (InterruptedException var3) {
                throw new JSONException("Calling Thread interupted");
            }
        }

        this.writeObject(aSerializableObject, 0);
        if (this.theProtector != null) {
            this.theProtector.free();
        }

    }

    private void writeObject(Serializable aSerializableObject, int levelCount) throws JSONException, IOException {
        Class primitiveArrayClass;
        try {
            if (aSerializableObject == null) {
                return;
            }

            primitiveArrayClass = aSerializableObject.getClass();
            if (!this.isAndroid) {
                while(true) {
                    if ((primitiveArrayClass = primitiveArrayClass.getSuperclass()) == null || primitiveArrayClass.getName().equals("java.awt.Container")) {
                        if (primitiveArrayClass != null && primitiveArrayClass.getName().equals("java.awt.Container")) {
                            return;
                        }
                        break;
                    }
                }
            }
        } catch (Throwable ignored) {
        }

        ++levelCount;
        if (levelCount > 30) {
            throw new JSONException("Depth limit of " + this.levelCountLimit + " exceeded in object " + aSerializableObject + " of class " + aSerializableObject.getClass().getName() + ".");
        } else {
            if (aSerializableObject instanceof Boolean) {
                this.theWriter.write(((Boolean)aSerializableObject).toString());
            } else if (aSerializableObject instanceof Date) {
                Timestamp aStamp = new Timestamp(((Date)aSerializableObject).getTime());
                this.theWriter.write("\"" + aStamp.toString() + "\"");
            } else {
                int aValue;
                if (aSerializableObject instanceof Map) {
                    Map aMap = (Map)aSerializableObject;
                    this.theWriter.write("{");
                    Set keys = aMap.keySet();
                    Iterator keyIt = keys.iterator();
                    aValue = 0;

                    while(keyIt.hasNext()) {
                        Object key = keyIt.next();
                        Object value = aMap.get(key);
                        if (value != null) {
                            if (aValue != 0) {
                                this.theWriter.write(",");
                            }

                            if (!(value instanceof Serializable)) {
                                throw new JSONException("Unable to JSON non-serializable object (" + value + ") of type " + value.getClass().toString() + ".");
                            }

                            this.theWriter.write("\"" + key.toString() + "\":");
                            this.writeObject((Serializable)value, levelCount);
                            ++aValue;
                        }
                    }

                    this.theWriter.write("}");
                } else {
                    Object anObject;
                    if (aSerializableObject instanceof List) {
                        List aList = (List)aSerializableObject;
                        Iterator keyIt = aList.iterator();
                        this.theWriter.write("[");

                        while(keyIt.hasNext()) {
                            anObject = keyIt.next();
                            if (anObject == null) {
                                anObject = "null";
                            }

                            if (!(anObject instanceof Serializable)) {
                                throw new JSONException("Unable to JSON non-serializable object (" + anObject + ") of type " + anObject.getClass().toString() + ".");
                            }

                            this.writeObject((Serializable)anObject, levelCount);
                            if (keyIt.hasNext()) {
                                this.theWriter.write(",");
                            }
                        }

                        this.theWriter.write("]");
                    } else if (aSerializableObject instanceof String) {
                        String appendString = (String)aSerializableObject;
                        if (!appendString.equals("null")) {
                            appendString = "\"" + this.escapeStringForJSON((String)aSerializableObject) + "\"";
                        }

                        this.theWriter.append(appendString);
                    } else if (aSerializableObject instanceof Number) {
                        this.theWriter.append(aSerializableObject.toString());
                    } else if (aSerializableObject instanceof Object[]) {
                        this.theWriter.append('[');
                        Object[] theArray = (Object[])((Object[])aSerializableObject);

                        for(int i = 0; i < theArray.length; ++i) {
                            anObject = theArray[i];
                            if (anObject == null) {
                                anObject = "null";
                            } else if (!(anObject instanceof Serializable)) {
                                continue;
                            }

                            this.writeObject((Serializable)anObject, levelCount);
                            if (i < theArray.length - 1) {
                                this.theWriter.append(',');
                            }
                        }

                        this.theWriter.append(']');
                    } else if (aSerializableObject.getClass().isArray()) {
                        primitiveArrayClass = aSerializableObject.getClass();
                        int i;
                        if (int[].class == primitiveArrayClass) {
                            this.theWriter.append('[');
                            int[] theArray = (int[])((int[])aSerializableObject);

                            for(i = 0; i < theArray.length; ++i) {
                                aValue = theArray[i];
                                this.theWriter.append(Integer.toString(aValue));
                                if (i < theArray.length - 1) {
                                    this.theWriter.append(',');
                                }
                            }

                            this.theWriter.append(']');
                        } else if (short[].class == primitiveArrayClass) {
                            this.theWriter.append('[');
                            short[] theArray = (short[])((short[])aSerializableObject);

                            for(i = 0; i < theArray.length; ++i) {
                                short aValue = theArray[i];
                                this.theWriter.append(Short.toString(aValue));
                                if (i < theArray.length - 1) {
                                    this.theWriter.append(',');
                                }
                            }

                            this.theWriter.append(']');
                        } else if (long[].class == primitiveArrayClass) {
                            this.theWriter.append('[');
                            long[] theArray = (long[])((long[])aSerializableObject);

                            for(i = 0; i < theArray.length; ++i) {
                                long aValue = theArray[i];
                                this.theWriter.append(Long.toString(aValue));
                                if (i < theArray.length - 1) {
                                    this.theWriter.append(',');
                                }
                            }

                            this.theWriter.append(']');
                        } else if (double[].class == primitiveArrayClass) {
                            this.theWriter.append('[');
                            double[] theArray = (double[])((double[])aSerializableObject);

                            for(i = 0; i < theArray.length; ++i) {
                                double aValue = theArray[i];
                                this.theWriter.append(Double.toString(aValue));
                                if (i < theArray.length - 1) {
                                    this.theWriter.append(',');
                                }
                            }

                            this.theWriter.append(']');
                        } else if (float[].class == primitiveArrayClass) {
                            this.theWriter.append('[');
                            float[] theArray = (float[])((float[])aSerializableObject);

                            for(i = 0; i < theArray.length; ++i) {
                                float aValue = theArray[i];
                                this.theWriter.append(Float.toString(aValue));
                                if (i < theArray.length - 1) {
                                    this.theWriter.append(',');
                                }
                            }

                            this.theWriter.append(']');
                        } else if (char[].class == primitiveArrayClass) {
                            this.theWriter.append('[');
                            char[] theArray = (char[])((char[])aSerializableObject);

                            for(i = 0; i < theArray.length; ++i) {
                                char aValue = theArray[i];
                                this.theWriter.append("\"" + aValue + "\"");
                                if (i < theArray.length - 1) {
                                    this.theWriter.append(',');
                                }
                            }

                            this.theWriter.append(']');
                        } else if (byte[].class == primitiveArrayClass) {
                            this.theWriter.append('[');
                            byte[] theArray = (byte[])((byte[])aSerializableObject);

                            for(i = 0; i < theArray.length; ++i) {
                                byte aValue = theArray[i];
                                this.theWriter.append("\"" + aValue + "\"");
                                if (i < theArray.length - 1) {
                                    this.theWriter.append(',');
                                }
                            }

                            this.theWriter.append(']');
                        }
                    } else {
                        this.theWriter.write(123);
                        this.writeAllAttributesOf(aSerializableObject, aSerializableObject.getClass(), levelCount);
                        this.theWriter.write(125);
                    }
                }
            }

            this.theWriter.flush();
        }
    }

    private void writeAllAttributesOf(Serializable aSerializableObject, Class<?> aClass, int levelCount) throws JSONException, IOException {
        Field[] theFields = aClass.getDeclaredFields();
        boolean previousFieldWasWritten = false;

        try {
            for(int i = 0; i < theFields.length; ++i) {
                Field aField = theFields[i];
                aField.setAccessible(true);
                String fieldName = aField.getName();
                Object value = aField.get(aSerializableObject);
                if (value instanceof Serializable) {
                    if (previousFieldWasWritten) {
                        this.theWriter.write(",");
                    }

                    int modifiers = aField.getModifiers();
                    if (!Modifier.isFinal(modifiers) && value != null && !fieldName.equals("this$0")) {
                        this.theWriter.write("\"" + fieldName + "\":");
                        this.writeObject((Serializable)value, levelCount);
                        previousFieldWasWritten = true;
                    }
                }
            }
        } catch (IllegalAccessException var11) {
            throw new JSONException("Unable to access one of the attributes of " + aSerializableObject);
        }

        if (aClass.getSuperclass() != null && aClass.getSuperclass() != Object.class) {
            this.theWriter.write(",");
            this.writeAllAttributesOf(aSerializableObject, aClass.getSuperclass(), levelCount);
        }

    }

    public void close() throws IOException {
        this.theWriter.close();
    }

    private String escapeStringForJSON(String text) {
        text = text.replaceAll("(\\r\\n?|\\n)", "\\\\n").replaceAll("([^\\\\]?)\\\"", "$1\\\\\"").replaceAll("(\\/)", "\\\\/").replaceAll("(\\f)", "\\\\f").replaceAll("(\\t)", "\\\\t").replaceAll("([^\\\\])\\\\([^\\\\ntfb\\/\\\"])", "$1\\\\\\\\$2");
        return text;
    }
}
