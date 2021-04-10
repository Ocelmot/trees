import java.util.Iterator;

abstract public class Tree<K extends Comparable<K>, V> {
    public abstract void add(K key, V value);
    public abstract V get(K key);
    public abstract boolean contains(K key);
    public abstract Iterator inorder();
}
