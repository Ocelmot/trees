import java.util.Iterator;

public class BinTree<K extends Comparable<K>, V> extends Tree<K, V> {
    private Node<K, V> root;

    public BinTree(){
        this.root = null;
    }

    @Override
    public void add(K key, V value) {
        Node<K, V> n = this.root;
        if (n == null) {
            this.root = new Node<K, V>(key, value);
            return;
        }
        while(true) {
            int cmp = n.getKey().compareTo(key);
            if(cmp == 0){
                n.setValue(value);
                return;
            }
            if(cmp < 0){// move to the left
                if(n.getLeft() == null){
                    n.setLeft(new Node<K, V>(key, value));
                    return;
                }else{
                    n = n.getLeft();
                }
            }
            if (cmp > 0){// move to the right
                if(n.getRight() == null){
                    n.setRight(new Node<K, V>(key, value));
                    return;
                }else{
                    n = n.getRight();
                }
            }
        }
    }

    @Override
    public V get(K key) {
        if (this.root == null){
            return null;
        }
        Node<K, V> n = root;
        while(true){
            int cmp = n.getKey().compareTo(key);
            if (cmp == 0){
                return n.getValue();
            }
            if (cmp < 0){
                if (n.getLeft() == null) {
                    return null;
                }else{
                    n = n.getLeft();
                }
            }
            if (cmp > 0){
                if (n.getRight() == null) {
                    return null;
                }else{
                    n = n.getRight();
                }
            }
        }
    }


    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public Iterator<K> inorder() {
        return null;
    }

    @Override
    public String toString() {
        return "BinTree{" +
                "root=" + root.toString("") +
                '}';
    }
}
