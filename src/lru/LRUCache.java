package lru;

import java.util.HashMap;

/**
 * Method 1:
 * This problem belongs to design problem. In order to meet the time complexity of
 * O(1) of look up, we use HashMap to store the key and node. LinkedHashMap is a
 * double linked list.
 *
 * Method 2:
 * HashMap: O(1) to lookup the key
 * double linked list to swap the nodes in the linked list
 */
public class LRUCache {

    class Node {
        int key;
        int value;
        Node next;
        Node pre;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Integer, Node> map;
    private int capacity;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = null;
        tail = null;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        // 1. node is in the position of head
        if (node == head) {
            head = head.next;
        } else {
            // 2. node is in the middle of the linked list
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        // 3. node is in the tail
        tail.next = node;
        node.pre = tail;
        node.next = null;
        tail = node;
        return node.value;
    }

    public void put(int key, int value) {
        // 1. check whether the node exists
        Node node = map.get(key);
        if (node != null) {
            // node exists
            node.value = value;
            // relocate all the nodes
            if (node != tail) {
                // 1. node is in the position of head
                if (node == head) {
                    head = head.next;
                } else {
                    // 2. node is in the middle of linked list
                    node.pre.next = node.next;
                    node.next.pre = node.pre;
                }
                // 3. node is in the position of tail or other position, relocate the node to tail
                tail.next = node;
                node.pre = tail;
                node.next = null;
                tail = node;
            }
        } else {
            // if the node does nt exist
            Node newNode = new Node(key, value);
            // check the cache is full
            if (capacity == 0) {
                Node temp = head;
                head = head.next;
                map.remove(temp.key);
                ++capacity;
            }
            // check the linked list is empty
            if (head == null && tail == null) {
                // the linked list is empty
                head = newNode;
            } else{
                // add the node to the list
                tail.next = newNode;
                newNode.pre = tail;
                newNode.next = null;
            }
            tail = newNode;
            map.put(key, tail);
            --capacity;
        }
    }
}
