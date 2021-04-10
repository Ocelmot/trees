public class ThreeNode<K extends Comparable<K>, V> extends Node<K, V>{
    private K right_key;
    private V right_value;
    private Node<K, V> center;

    public ThreeNode(K key, V value, K right_key, V right_value) {
        super(key, value);
        this.right_key = right_key;
        this.right_value = right_value;
    }

    public K getRightKey() {
        return right_key;
    }
    public void setRightKey(K right_key) {
        this.right_key = right_key;
    }

    public V getRightValue() {
        return right_value;
    }
    public void setRightValue(V right_value) {
        this.right_value = right_value;
    }

    public Node<K, V> getCenter(){
        return this.center;
    }
    public void setCenter(Node<K, V> center) {
        this.center = center;
    }

    @Override
    public void replaceChild(Node<K, V> child, Node<K, V> newChild) {
        if (child == this.center){
            this.center = newChild;
        }else{
            super.replaceChild(child, newChild);
        }
    }

    @Override
    public String toString() {
        return "ThreeNode{" +
                this.getKey()+"\":\"" + this.getValue() + "\"" +
                "\", " + right_key + "\":\"" + right_value + "\""+
                ", left=" + this.getLeft() +
                ", center=" + center +
                ", right=" + this.getRight() +
                "} ";
    }
}

//key+"\":\"" + value + "\"" +
//        ", right=" + right +
//
//        '}';
