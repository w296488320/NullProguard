package proguard.obfuscate;

import java.util.Random;

public class ONameFactory implements NameFactory {
    private static final Random RANDOM = new Random();

    private char c = '×þ';
    private ONameFactory parent = null;
    private boolean mark = RANDOM.nextBoolean();

    ONameFactory() {
    }

    @Override
    public void reset() {
        c = '×þ';
        parent = null;
    }

    @Override
    public String nextName() {
        String name = getName();
        next();
        return name;
    }

    private String getName() {
        return parent == null ? String.valueOf(c) : parent.getName() + c;
    }

    private void next() {
        if (mark)
            switch (c) {
                case '×þ':
                    c = '×û';
                    break;
                case '×û':
                    c = '×ý';
                    break;
                case '×ý':
                    c = '×þ';
                    if (parent == null)
                        parent = new ONameFactory();
                    else
                        parent.next();
                    break;
            }
        else
            switch (c) {
            case '×þ':
                c = '×û';
                break;
            case '×û':
                c = '×ý';
                break;
            case '×ý':
                c = '×þ';
                    if (parent == null)
                        parent = new ONameFactory();
                    else
                        parent.next();
                    break;
            }
    }

}
