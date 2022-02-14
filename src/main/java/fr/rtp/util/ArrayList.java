package fr.rtp.util;

public class ArrayList {
    private Object[] elements = new Object[10];
    private boolean readOnly;
    private int size = 0;

    public void add(Object child) {
        // <1>
        if (!readOnly) {
            int newSize = size + 1;
        // <2>
            if (newSize > elements.length) {
                Object[] newElements = new Object[elements.length + 10];
                for (int i = 0; i < size; i++) {
                    newElements[i] = elements[i];
                }
                elements = newElements;
            }
        // <3>
            elements[size] = child;
            size++;
        }
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public boolean contains(Object child) {
        for (int i = 0; i < size; i++) {
            if (child.equals(elements[i])) return true;
        }
        return false;
    }
}
