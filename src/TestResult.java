import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TestResult {
    String name;
    TreeMap<String, Object> data;
    HashMap<String, Long> timers;

    public TestResult(){
        this.name = "";
        this.data = new TreeMap<>();
        this.timers = new HashMap<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public void enterData(String name, Object data){
        this.data.put(name, data);
    }

    public void startTimer(String name){
        timers.put(name, java.lang.System.currentTimeMillis());
    }
    public void recordTimer(String name){
        enterData(name, java.lang.System.currentTimeMillis()-timers.get(name)+"ms");
    }

    @Override
    public String toString() {
        String result = "TestResult: " + this.name+" {\n";
        for (Map.Entry<String, Object> entry: this.data.entrySet()) {
            result += "\t"+entry.getKey()+": "+entry.getValue()+"\n";
        }

        result += "}";
        return result;
    }
}
