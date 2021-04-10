import java.util.*;

public class TreeTest {
    public static final String alphanum;
    private static final Random rand;

    static {
        rand = new Random();
        alphanum = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    }

    public static void main(String[] args) {
//        Tree<String, Integer> t = new BinTree<>();
        Tree<String, Integer> t = new TwoThreeTree<>();
        t.add("A", 1);
        t.add("B", 2);
        t.add("M", 123);
        t.add("D", 1234);
        t.add("C", 123);

        System.out.println("A: "+t.get("A"));
        System.out.println("B: "+t.get("B"));
        System.out.println("A1: "+t.get("A1"));
        System.out.println("M: "+t.get("M"));
        System.out.println("D: "+t.get("D"));
        System.out.println("C: "+t.get("C"));


//        Tree<Integer, String> bt = new BinTree<>();
//        TestResult bttr = testTree(bt, 1000000, false);
//        bttr.setName("Binary Tree");
//
//
//        Tree<Integer, String> ttt = new TwoThreeTree<>();
//        TestResult ttttr = testTree(ttt, 1000000, false);
//        ttttr.setName("Two Three Tree");
//
//        System.out.println(bttr);
//        System.out.println(ttttr);
    }
    public static String randString(int len){
        String result = "";
        for (int i = 0; i < len; i++) {
            result += TreeTest.alphanum.charAt(rand.nextInt(alphanum.length()));
        }

        return result;
    }
    public static TestResult testTree(Tree<Integer, String> tree, int test_size){
        return testTree(tree, test_size, false);
    }
    public static TestResult testTree(Tree<Integer, String> tree, int test_size, boolean shuffle){
        TestResult result = new TestResult();
        result.enterData("Test Size", test_size);
        result.enterData("Shuffled", shuffle);
        //build test data
        System.out.println("Creating test data...");
        List<Integer> keys = new ArrayList<>(test_size);
        List<String> values = new ArrayList<>(test_size);
        for (int i = 0; i < test_size; i++) {
            keys.add(i);
            values.add(randString(5));
        }

        if (shuffle){
            System.out.println("Shuffling...");
            Collections.shuffle(keys);
            Collections.shuffle(values);
        }

        System.out.println("Inserting...");
        result.startTimer("Insertion Time");
        for (int i = 0; i < keys.size(); i++) {
            tree.add(keys.get(i), values.get(i));
        }
        result.recordTimer("Insertion Time");
        System.out.println("Testing...");
        result.startTimer("Access Time");
        for (int i = 0; i < keys.size(); i++) {
            String value = tree.get(keys.get(i));
            assert value.equals(values.get(i)) : "Key did not return correct value!";
        }
        result.recordTimer("Access Time");
        System.out.println("Done.");

        return result;
    }
}