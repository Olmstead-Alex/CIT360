package ACP4;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JSONParser {
    public static final int S_INIT = 0;
    public static final int S_IN_FINISHED_VALUE = 1;
    public static final int S_IN_OBJECT = 2;
    public static final int S_IN_ARRAY = 3;
    public static final int S_PASSED_PAIR_KEY = 4;
    public static final int S_IN_PAIR_VALUE = 5;
    public static final int S_END = 6;
    public static final int S_IN_ERROR = -1;
    public static final int FIRST_JSON_CHAR_TYPE_UNSET = -515;
    private Yylex lexer = new Yylex((Reader)null);
    private Yytoken token = null;
    private int status = 0;
    private int firstCharType = -515;
    private int numUnmatchedCharTypeCount = 0;
    private Reader in;

    private int peekStatus(LinkedList statusStack) {
        if (statusStack.size() == 0) {
            return -1;
        } else {
            Integer status = (Integer)statusStack.getFirst();
            return status;
        }
    }

    public JSONParser(Reader in) {
        this.in = in;
        this.reset(this.in);
    }

    public void setFirstCharType(int firstCharType) {
        this.firstCharType = firstCharType;
    }

    public void closeReader() throws IOException {
        this.in.close();
    }

    public void setNumUnmatchedCharTypeCount(int numUnmatchedCharTypeCount) {
        this.numUnmatchedCharTypeCount = numUnmatchedCharTypeCount;
    }

    public void reset() {
        this.firstCharType = -515;
        this.numUnmatchedCharTypeCount = 0;
        this.token = null;
        this.status = 0;
    }

    public void reset(Reader in) {
        this.firstCharType = -515;
        this.numUnmatchedCharTypeCount = 0;
        this.lexer.yyreset(in);
        this.reset();
    }

    public int getPosition() {
        return this.lexer.getPosition();
    }

    public Object parse() throws IOException, ParseException {
        return this.parse((ContainerFactory)null);
    }

    public Object parse(ContainerFactory containerFactory) throws IOException, ParseException {
        LinkedList statusStack = new LinkedList();
        LinkedList valueStack = new LinkedList();

        try {
            do {
                if (this.status != 1) {
                    this.nextToken();
                }

                String key;
                Map parent;
                List newArray;
                label111:
                switch(this.status) {
                    case -1:
                        throw new ParseException(this.getPosition(), 1, this.token);
                    case 0:
                        switch(this.token.type) {
                            case 0:
                                this.status = 1;
                                statusStack.addFirst(new Integer(this.status));
                                valueStack.addFirst(this.token.value);
                                break label111;
                            case 1:
                                if (this.firstCharType == -515) {
                                    this.firstCharType = 1;
                                }

                                if (this.firstCharType == 1) {
                                    ++this.numUnmatchedCharTypeCount;
                                }

                                this.status = 2;
                                statusStack.addFirst(new Integer(this.status));
                                valueStack.addFirst(this.createObjectContainer(containerFactory));
                                break label111;
                            case 2:
                            default:
                                this.status = -1;
                                break label111;
                            case 3:
                                if (this.firstCharType == -515) {
                                    this.firstCharType = 3;
                                }

                                if (this.firstCharType == 3) {
                                    ++this.numUnmatchedCharTypeCount;
                                }

                                this.status = 3;
                                statusStack.addFirst(new Integer(this.status));
                                valueStack.addFirst(this.createArrayContainer(containerFactory));
                                break label111;
                        }
                    case 1:
                        if (this.token.type != -1 && this.numUnmatchedCharTypeCount != 0) {
                            throw new ParseException(this.getPosition(), 1, this.token);
                        }

                        this.firstCharType = -515;
                        this.status = 0;
                        return valueStack.removeFirst();
                    case 2:
                        switch(this.token.type) {
                            case 0:
                                if (this.token.value instanceof String) {
                                    key = (String)this.token.value;
                                    valueStack.addFirst(key);
                                    this.status = 4;
                                    statusStack.addFirst(new Integer(this.status));
                                } else {
                                    this.status = -1;
                                }
                                break label111;
                            case 2:
                                if (this.firstCharType == 1) {
                                    --this.numUnmatchedCharTypeCount;
                                }

                                if (valueStack.size() > 1) {
                                    statusStack.removeFirst();
                                    valueStack.removeFirst();
                                    this.status = this.peekStatus(statusStack);
                                } else {
                                    this.status = 1;
                                }
                            case 5:
                                break label111;
                            default:
                                this.status = -1;
                                break label111;
                        }
                    case 3:
                        List val;
                        switch(this.token.type) {
                            case 0:
                                val = (List)valueStack.getFirst();
                                val.add(this.token.value);
                                break label111;
                            case 1:
                                if (this.firstCharType == 1) {
                                    ++this.numUnmatchedCharTypeCount;
                                }

                                val = (List)valueStack.getFirst();
                                parent = this.createObjectContainer(containerFactory);
                                val.add(parent);
                                this.status = 2;
                                statusStack.addFirst(new Integer(this.status));
                                valueStack.addFirst(parent);
                                break label111;
                            case 2:
                            default:
                                this.status = -1;
                                break label111;
                            case 3:
                                if (this.firstCharType == 3) {
                                    ++this.numUnmatchedCharTypeCount;
                                }

                                val = (List)valueStack.getFirst();
                                newArray = this.createArrayContainer(containerFactory);
                                val.add(newArray);
                                this.status = 3;
                                statusStack.addFirst(new Integer(this.status));
                                valueStack.addFirst(newArray);
                                break label111;
                            case 4:
                                if (this.firstCharType == 3) {
                                    --this.numUnmatchedCharTypeCount;
                                }

                                if (valueStack.size() > 1) {
                                    statusStack.removeFirst();
                                    valueStack.removeFirst();
                                    this.status = this.peekStatus(statusStack);
                                } else {
                                    this.status = 1;
                                }
                            case 5:
                                break label111;
                        }
                    case 4:
                        switch(this.token.type) {
                            case 0:
                                statusStack.removeFirst();
                                key = (String)valueStack.removeFirst();
                                parent = (Map)valueStack.getFirst();
                                parent.put(key, this.token.value);
                                this.status = this.peekStatus(statusStack);
                                break;
                            case 1:
                                if (this.firstCharType == 1) {
                                    ++this.numUnmatchedCharTypeCount;
                                }

                                statusStack.removeFirst();
                                key = (String)valueStack.removeFirst();
                                parent = (Map)valueStack.getFirst();
                                Map newObject = this.createObjectContainer(containerFactory);
                                parent.put(key, newObject);
                                this.status = 2;
                                statusStack.addFirst(new Integer(this.status));
                                valueStack.addFirst(newObject);
                                break;
                            case 2:
                            case 4:
                            case 5:
                            default:
                                this.status = -1;
                                break;
                            case 3:
                                if (this.firstCharType == 3) {
                                    ++this.numUnmatchedCharTypeCount;
                                }

                                statusStack.removeFirst();
                                key = (String)valueStack.removeFirst();
                                parent = (Map)valueStack.getFirst();
                                newArray = this.createArrayContainer(containerFactory);
                                parent.put(key, newArray);
                                this.status = 3;
                                statusStack.addFirst(new Integer(this.status));
                                valueStack.addFirst(newArray);
                            case 6:
                        }
                }

                if (this.status == -1) {
                    throw new ParseException(this.getPosition(), 1, this.token);
                }
            } while(this.token.type != -1);
        } catch (IOException var8) {
            throw var8;
        }

        throw new ParseException(this.getPosition(), 1, this.token);
    }

    private void nextToken() throws ParseException, IOException {
        this.token = this.lexer.yylex();
        if (this.token == null) {
            this.token = new Yytoken(-1, (Object)null);
        }

    }

    private Map createObjectContainer(ContainerFactory containerFactory) {
        if (containerFactory == null) {
            return new HashMap();
        } else {
            Map m = containerFactory.createObjectContainer();
            return (Map)(m == null ? new HashMap() : m);
        }
    }

    private List createArrayContainer(ContainerFactory containerFactory) {
        if (containerFactory == null) {
            return new ArrayList();
        } else {
            List l = containerFactory.creatArrayContainer();
            return (List)(l == null ? new ArrayList() : l);
        }
    }
}