package datastruct;

import java.util.Arrays;

public class MyUnsortedList<E> implements UnsortedList<E> {

    private static class Link<E> {
        final E element;
        Link<E> next;

        private Link(E element) {
            this.element = element;
        }
    }

    private Link<E> head;
    private Link<E> tail;
    private int size;

    private MyUnsortedList() {
        this.head = null;
        this.size = 0;
        this.tail = null;
    }

    @SafeVarargs
    public static <E> MyUnsortedList<E> of(E... elements) {
        return of(Arrays.asList(elements));
    }

    public static <E> MyUnsortedList<E> of(Iterable<E> elements) {
        MyUnsortedList<E> list = new MyUnsortedList<>();
        for (E element : elements) {
            list.append(element);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void prepend(E element) {
        size++;
        Link<E> newHead = new Link<>(element);
        newHead.next = head;
        head = newHead;

        if (size == 1) {
            tail = head;
        }
    }

    @Override
    public void append(E element) {
        Link<E> insertedL = new Link<>(element);
        if (tail != null) {
            tail.next = insertedL;
            tail = insertedL;
        } else {
            if (head == null) {
                prepend(element);
                return;
            }
            Link<E> prevLink = head;
            while (prevLink.next != null) {
                prevLink = prevLink.next;
            }
            prevLink.next = insertedL;
            tail = insertedL;
        }
        size++;

    }

    @Override
    public void insert(E elem, int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException();
        }
        if (pos == 0) {
            prepend(elem);
            return;
        }
        if (pos == size) {
            append(elem);
            return;
        }
        Link<E> prevLink = head;
        while (pos-- > 1) {
            prevLink = prevLink.next;
        }

        size++;
        Link<E> inserted = new Link<>(elem);
        Link<E> nextLink = prevLink.next;
        prevLink.next = inserted;
        inserted.next = nextLink;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        size--;
        Link<E> oldHead = head;
        head = oldHead.next;

        return oldHead.element;
    }

    @Override
    public E popLast() {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        size--;

        if (size == 0) {
            // Si la liste devient vide après la suppression,
            // mettez à jour la référence de la queue à null
            Link<E> removed = head;
            head = null;
            tail = null;
            return removed.element;
        }

        Link<E> prevLink = head;
        while (prevLink.next != tail) {
            prevLink = prevLink.next;
        }

        Link<E> removed = tail;
        prevLink.next = null;
        tail = prevLink;
        return removed.element;
    }

    @Override
    public E remove(int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (pos == 0) {
            return pop();
        }

        if (pos == size -1){
            return popLast();
        }

        Link<E> prevLink = head;
        while (--pos > 0) {
            prevLink = prevLink.next;
        }

        Link<E> removed = prevLink.next;
        prevLink.next = removed.next;
        size --; //Correction de bug
        return removed.element;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyUnsortedList)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        MyUnsortedList<E> that = (MyUnsortedList<E>) obj;
        if (this.size != that.size) {
            return false;
        }

        Link<E> thisLink = this.head;
        Link<E> thatLink = that.head;
        while (thisLink != null) {
            if (thatLink == null || !thisLink.element.equals(thatLink.element)) {
                return false;
            }
            thisLink = thisLink.next;
            thatLink = thatLink.next;
        }

        return thatLink == null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("MyUnsortedList { size = ");
        builder.append(size);
        builder.append(", [");

        MyUnsortedList.Link<E> link = head;
        while (link != null) {
            builder.append(link.element);
            link = link.next;
            if (link != null) {
                builder.append(", ");
            }
        }

        return builder.append("] }").toString();
    }
}
