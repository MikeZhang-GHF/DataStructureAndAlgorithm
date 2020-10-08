package dataStructureAlgorithm.hashtable.seperateChaining.impl;

import dataStructureAlgorithm.hashtable.IHashTable;
import dataStructureAlgorithm.hashtable.seperateChaining.HashNode;

public class HashTableSeperateChainingImpl<K, V> implements IHashTable<K, V> {
    private HashNode[] hashNodes;

    private int capacity;

    private int size;

    public HashTableSeperateChainingImpl(int capacity) {
        this.capacity = capacity;
        hashNodes = new HashNode[capacity];
    }

    public HashTableSeperateChainingImpl() {
        this.capacity = 10;
        hashNodes = new HashNode[capacity];
    }

    private int hash(K key) {
        return key.hashCode() % capacity;
    }

    @Override
    public void put(K key, V val) {
        HashNode<K, V> hashNode = new HashNode<>(key, val);
        int index = hash(key);

        HashNode curNode = hashNodes[index];
        HashNode preNode = null;
        //  check if the key exists
        if (curNode == null) {
            // if the key doesn't exist
            hashNodes[index] = hashNode;
        } else {
            // if the key exists, check if the key exists in the linked list
            while(curNode!= null) {
                if(curNode.key.equals(key)) {
                    //update the value
                    curNode.value = val;
                    return;
                } else {
                    preNode = curNode;
                    curNode = curNode.next;
                }
            }
            preNode.next = hashNode;
        }
        size++;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        HashNode curNode = hashNodes[index];

        // if the key exists in the bucket, check the linked list in the bucket
        while (curNode != null) {
            if (curNode.key.equals(key)) {
                return (V) curNode.value;
            }
            curNode = curNode.next;
        }
        return null;
    }

    @Override
    public V delete(K key) {
        int index = hash(key);
        HashNode curNode = hashNodes[index];
        HashNode preNode = curNode;

        // Check the key if exists in the bucket
        if (curNode == null) {
            return null;
        } else {
            while (curNode != null) {
                if (curNode.key.equals(key)) {
                    preNode.next = curNode.next;
                    size--;
                    return (V)curNode.value;
                }
                preNode = curNode;
                curNode = curNode.next;
            }
            return null;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void print() {
        for (HashNode<K,V> hashNode : hashNodes) {
            if (hashNode != null) {
                while (hashNode != null) {
                    System.out.print("key : " + hashNode.key + " value : " + hashNode.value + " -> " );
                    hashNode = hashNode.next;
                }
                System.out.println("null");
            }
        }
        System.out.println("size : " + size);
    }
}
