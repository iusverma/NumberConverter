package tw.prg.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Number {

    private Map<String,String> baseNumberMap;
    public abstract void init(List<String> data);
    public Map<String,String> getBaseNumberMap() {
        if(baseNumberMap==null){
            baseNumberMap = new HashMap<>();
        }
        return baseNumberMap;
    }
    public void setBaseNumberMap(Map<String,String> baseNumberMap) {
        this.baseNumberMap = baseNumberMap;
    }
}