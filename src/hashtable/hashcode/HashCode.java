package hashtable.hashcode;

import java.util.HashMap;

public class HashCode {

    static class Student {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("Mike");
        Student s2 = new Student();
        s2.setName("Sherry");
        //System.out.println(s1.equals(s2));

        HashMap<Student, Integer> map = new HashMap<>();
        map.put(s1, 3);
        System.out.println(map.get(s2));
    }
}
