public class Node<K extends  Comparable<K>, V>{
    private K key;
    private V value;
    private Node<K, V> right;
    private Node<K, V> left;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.right = null;
        this.left = null;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return value;
    }
    public void setValue(V value){
        this.value = value;
    }

    public Node<K, V> getLeft() {
        return left;
    }
    public void setLeft(Node<K, V> node) {
        this.left = node;
    }

    public Node<K, V> getRight() {
        return right;
    }
    public void setRight(Node<K, V> node){
        this.right = node;
    }

    public void replaceChild(Node<K, V> child, Node<K, V> newChild){
        if (child == this.left){
            this.left = newChild;
        }
        if (child == this.right){
            this.right = newChild;
        }
    }


    @Override
    public String toString() {
        return "Node{\"" +
                key+"\":\"" + value + "\"" +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public String toString(String indent){
        return "Node: "+this.value + "\n"
                + indent+"\tleft:"+((this.left == null)?"null":this.left.toString(indent+"\t"))+"\n"
                + indent+"\tright:"+((this.right==null)?"null":this.right.toString(indent+"\t"))+"";
    }
}
