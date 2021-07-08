public class LinkedList extends AbstractList {

    private Node first;
    private Node last;

    private static class Node {
        Node prev;
        Node next;
        Object data;
    }

    @Override
    public void add(Object value) {
        Node node = new Node();
        node.data = value;

        if (size == 0) {
            first = node;
            last = first;
        } else {
            last.next = node;
            node.prev = last;
            last = node;
        }
        size++;
    }

    @Override
    public void add(Object value, int index) {
        checkIndex(index, 0, size);
        Node curr = findByIndex(index);
        if (curr == null) {
            // structure error
            return;
        }
        Node prev = curr.prev;

        Node node = new Node();
        node.data = value;

        prev.next = node;
        node.prev = prev;
        node.next = curr;
        curr.prev = node;

        if (index == 0) {
            first = node;
        }
        if (index == size) {
            last = node;
        }

        size++;
    }

    @Override
    public Object remove(int index) {
        checkIndex(index, 0, size - 1);
        Node curr = findByIndex(index);
        if (curr == null) {
            // structure error
            return null;
        }
        Node prev = curr.prev;
        Node next = curr.next;

        prev.next = curr.next;
        next.prev = curr.prev;

        if (index == 0) {
            first = curr.next;
        }
        if (index == size - 1) {
            last = curr.prev;
        }

        size--;

        return curr.data;
    }

    @Override
    public Object get(int index) {
        checkIndex(index, 0, size - 1);
        Node curr = findByIndex(index);
        if (curr == null) {
            // structure error
            return null;
        }
        return curr.data;
    }

    @Override
    public Object set(Object value, int index) {
        checkIndex(index, 0, size - 1);
        Node curr = findByIndex(index);
        if (curr == null) {
            // structure error
            return null;
        }
        curr.data = value;
        return curr.data;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public int indexOf(Object value) {
        if (size == 0) {
            return -1;
        }
        return findByFirstValue(value);
    }

    @Override
    public int lastIndexOf(Object value) {
        if (size == 0) {
            return -1;
        }
        return findByLastValue(value);
    }

    ////

    protected Node findByIndex(int index) {
        if (first == null) {
            return null;
        }
        Node curr =  first;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return curr;
            }
            if (curr == null) {
                // structure error
                return null;
            }
            curr = curr.next;
        }
        return null;
    }

    protected int findByFirstValue(Object value) {
        if (first == null) {
            return -1;
        }
        Node curr = first;
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (curr == null) {
                    // structure error
                    return -1;
                }
                if (curr.data == null) {
                    return i;
                }
                curr = curr.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (curr == null) {
                    // structure error
                    return -1;
                }
                if (value.equals(curr.data)) {
                    return i;
                }
                curr = curr.next;
            }
        }
        return -1;
    }

    protected int findByLastValue(Object value) {
        if (last == null) {
            return -1;
        }
        Node curr = last;
        if (value == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (curr == null) {
                    // structure error
                    return -1;
                }
                if (curr.data == null) {
                    return i;
                }
                curr = curr.prev;
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (curr == null) {
                    // structure error
                    return -1;
                }
                if (value.equals(curr.data)) {
                    return i;
                }
                curr = curr.prev;
            }
        }
        return -1;
    }

}
