package dataStructureAlgorithm.hashtable.linkedHashMapTest;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        // accessOrder determine the access order
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16,0.75f,true);
        linkedHashMap.put("1", "1");
        linkedHashMap.put("2", "1");
        linkedHashMap.put("3", "1");
        linkedHashMap.put("4", "1");
        print(linkedHashMap);
        linkedHashMap.get("1");
        print(linkedHashMap);
    }

    public static void print(LinkedHashMap<String, String> linkedHashMap) {
        for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
            System.out.print(entry.getKey() + "\t");
        }
        System.out.println();
    }
}
