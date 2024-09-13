import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class CustomList<T> extends AbstractList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private class Node<T>{
        T value;
        Node<T> next;
        public Node(T value){
            this.value = value;
            this.next = null;
        }

    }
    private class ListIterator implements Iterator<T>{

        private Node<T> curr;
        @Override
        public boolean hasNext() {

            return curr != null;
        }

        @Override
        public T next() {
            if(curr == null){
                throw new NoSuchElementException();
            }
            T value = curr.value;
            curr = curr.next;
            return curr.value;
        }
    }

    public void addLast(T value){
        Node<T> node = new Node<>(value);
        if(head == null){
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }
    public T getLast(){
        if(tail == null){
            return null;
        }else {
            return tail.value;
        }
    }
    public void addFirst(T value){
        Node<T> newNode = new Node<>(value);
        if(head == null){
            head = tail = newNode;

        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public T getFirst(){
        if(head == null){
            return null;
        } else {
            return head.value;
        }
    }

    public T removeFirst(){
        if(head == null){
            return null;
        } else {
           T temp = head.value;
           head = head.next;
            if(head == null){
                tail = null;
            }
            size--;
           return temp;

        }
    }

    public T removeLast(){
        if(tail == null){
            return null;
        } else if(head == tail){
            T temp = tail.value;
            head = tail = null;
            return temp;
        } else {
            Node<T> curr = head;
            while(curr.next != tail){
                curr = curr.next;
            }
            T temp = tail.value;
            tail = curr;
            tail.next = null;
            size--;
            return temp;
        }
    }


    @Override
    public boolean add(T t){
        addLast(t);
        return true;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size){ throw new IndexOutOfBoundsException(); }
        Node<T> curr = head;
        for(int i = 0; i < index; i++){
            curr = curr.next;
        }
        return curr.value;
    }
    public Iterator<T> iterator(){
        return new ListIterator();
    }

    public static <T> CustomList<T> filterByClass(CustomList<T> list, Class<?> clazz) {
        CustomList<T> filteredList = new CustomList<>();
        list.stream()
                .filter(obj -> obj != null && obj.getClass().equals(clazz))
                .forEach(filteredList::addLast);
        return filteredList;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public Stream<T> stream(){
        Stream.Builder<T> builder = Stream.builder();
        for(T i : this){
            builder.accept(i);
        }
        return builder.build();
    }
    //    @Override
//    public Stream<T> stream() {
//        Iterable<T> iterable = this::iterator;
//        return StreamSupport.stream(iterable.spliterator(), false);
//    }ma menu kontekstowe

}