import java.util.*;

public class TwoThreeTree<K extends Comparable<K>, V> extends Tree<K, V>{
    Node<K, V> root;

    TwoThreeTree() {
        this.root = null;
    }

    @Override
    public void add(K key, V value) {
        Node<K, V> n = this.root;
        if (n == null) {
            this.root = new Node<K, V>(key, value);
            return;
        }
        LinkedList<Node<K, V>> stack = new LinkedList<>();
        while(true) {
            int cmpL = key.compareTo(n.getKey());
            if (cmpL == 0){
                n.setValue(value);
                return;
            }
            stack.push(n);
            if (cmpL < 0){
                if (n.getLeft()==null){
                    break;
                }else{
                    n = n.getLeft();
                }
            }
            if (cmpL > 0){

                if (n instanceof ThreeNode){ // Its a three node!
                    ThreeNode<K, V> tn = (ThreeNode<K, V>) n;
                    int cmpR = key.compareTo(tn.getRightKey());
                    if (cmpR == 0){
                        tn.setRightValue(value);
                        return;
                    }
                    if (cmpR < 0){ // It must be in the center since cmpL > 0
                        if (tn.getCenter() == null){
                            break;
                        }else{
                            n = tn.getCenter();
                        }
                    }
                    if (cmpR > 0){ // Truly to the right
                        if (tn.getRight() == null){
                            break;
                        }else{
                            n = tn.getRight();
                        }
                    }
                }else{
                    if(n.getRight() == null){
                        break;
                    }else{
                        n = n.getRight();
                    }
                }
            }
        }


        // Now insert the new node
        Node<K, V> subtree = new Node<K, V>(key, value);
        while(true){
            n= stack.pop();
            Node<K, V> parent = stack.peek();
            if (n instanceof ThreeNode){ // need to split the ThreeNode in order to add element
                ThreeNode<K, V> tn = (ThreeNode<K, V>) n;
                // List children in order
                List<Node<K,V>> c = null;
                Node<K, V> new_left;
                Node<K, V> new_right;
                Node<K, V> new_root = null;

                if (subtree.getKey().compareTo(tn.getKey()) < 0 ){
                    new_left = new Node<>(subtree.getKey(), subtree.getValue());
                    new_left.setLeft(subtree.getLeft());
                    new_left.setRight(subtree.getRight());
                    new_right = new Node<>(tn.getRightKey(), tn.getRightValue());
                    new_right.setLeft(tn.getCenter());
                    new_right.setRight(tn.getRight());
                    new_root = new Node<>(tn.getKey(), tn.getValue());
                    new_root.setLeft(new_left);
                    new_root.setRight(new_right);
                } else if(subtree.getKey().compareTo(tn.getRightKey()) < 0){
                    c = Arrays.asList(tn.getLeft(), subtree.getLeft(), subtree.getRight(), tn.getRight());

                    new_left = new Node<>(tn.getKey(), tn.getValue());
                    new_left.setLeft(tn.getLeft());
                    new_left.setRight(subtree.getLeft());
                    new_right = new Node<>(tn.getRightKey(), tn.getRightValue());
                    new_right.setLeft(subtree.getRight());
                    new_right.setRight(tn.getRight());
                    new_root = new Node<>(subtree.getKey(), subtree.getValue());
                    new_root.setLeft(new_left);
                    new_root.setRight(new_right);
                } else {
                    c = Arrays.asList(tn.getLeft(), tn.getCenter(), subtree.getLeft(), subtree.getRight());

                    new_left = new Node<>(tn.getKey(), tn.getValue());
                    new_left.setLeft(tn.getLeft());
                    new_left.setRight(tn.getCenter());
                    new_right = new Node<>(subtree.getKey(), subtree.getValue());
                    new_right.setLeft(subtree.getLeft());
                    new_right.setRight(subtree.getRight());
                    new_root = new Node<>(tn.getRightKey(), tn.getRightValue());
                    new_root.setLeft(new_left);
                    new_root.setRight(new_right);
                }

                if (parent == null) {
                    this.root = new_root;
                    return;
                }else{
                    parent.replaceChild(n, new_root);
                    subtree = new_root;
                }


            }else if(n instanceof Node){ // Replace the TwoNode with a ThreeNode
                int cmp = subtree.getKey().compareTo(n.getKey());
                ThreeNode<K, V> ntn;
                if(cmp < 0){// add to the left
                    ntn = new ThreeNode<>(subtree.getKey(), subtree.getValue(), n.getKey(), n.getValue());
                    ntn.setLeft(subtree.getLeft());
                    ntn.setCenter(subtree.getRight());
                    ntn.setRight(n.getRight());
                }else{ // add to the right
                    ntn = new ThreeNode<>(n.getKey(), n.getValue(), subtree.getKey(), subtree.getValue());
                    ntn.setLeft(n.getLeft());
                    ntn.setCenter(subtree.getLeft());
                    ntn.setRight(subtree.getRight());
                }
                if (parent == null){
                    this.root = ntn;
                }else{
                    parent.replaceChild(n, ntn);
                }
                return;
            }
        }
    }

    @Override
    public V get(K key) {
        Node<K, V> n = this.root;
        if (n == null) {
            return null;
        }
        while(true) {
            int cmpL = key.compareTo(n.getKey());
            if (cmpL == 0){
                return n.getValue();
            }
            if (cmpL < 0){
                if (n.getLeft()==null){
                    break;
                }else{
                    n = n.getLeft();
                }
            }
            if (cmpL > 0){

                if (n instanceof ThreeNode){ // Its a three node!
                    ThreeNode<K, V> tn = (ThreeNode<K, V>) n;
                    int cmpR = key.compareTo(tn.getRightKey());
                    if (cmpR == 0){
                        return tn.getRightValue();
                    }
                    if (cmpR < 0){ // It must be in the center since cmpL > 0
                        if (tn.getCenter() == null){
                            break;
                        }else{
                            n = tn.getCenter();
                        }
                    }
                    if (cmpR > 0){ // Truly to the right
                        if (tn.getRight() == null){
                            break;
                        }else{
                            n = tn.getRight();
                        }
                    }
                }else{
                    if(n.getRight() == null){
                        break;
                    }else{
                        n = n.getRight();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public Iterator inorder() {
        return null;
    }
}
