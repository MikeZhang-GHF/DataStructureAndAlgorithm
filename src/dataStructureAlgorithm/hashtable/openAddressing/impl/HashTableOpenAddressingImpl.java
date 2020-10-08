package dataStructureAlgorithm.hashtable.openAddressing.impl;

import dataStructureAlgorithm.hashtable.IHashTable;

public class HashTableOpenAddressingImpl<K, V> implements IHashTable<K, V> {
    private int capacity;
    private int size;
    private HashNode [] hashNodes;

    public HashTableOpenAddressingImpl() {
        this.capacity = 5;
        hashNodes = new HashNode[capacity];
    }

    public HashTableOpenAddressingImpl(int capacity) {
        this.capacity = capacity;
        hashNodes = new HashNode[capacity];
    }

    private int hash(K key) {
        return key.hashCode() % capacity;
    }

    @Override
    public void put(K key, V val) {
        HashNode hashNode = new HashNode(key, val);
        int index = hash(key);
        int startIndex = index;

        /**
         * 1. Check if the key collide with the position in the bucket,
         * 2. if there is collision, check the keys are equal or not.
         *    * equal: update the value
         *    * not equal: increase the capacity.
         * if not, just add the k,v in the bucket.
         * */
        while (hashNodes[index] != null) {
            // Collision happens, update the value for the key
            if (hashNodes[index].key.equals(key)) {
                hashNodes[index].value = val;
                return;
            }
            // Collision happens, to check if we need to increase the capacity
            ++index;
            index %= capacity;
            // increase the capacity
            if(index == startIndex) {
                resize();
                index = hash(key);
                startIndex = index;
            }
        }
        // No collision, add the key and value in the bucket
        hashNodes[index] = hashNode;
        size++;
    }

    private void resize() {
        capacity *= 2;
        HashNode[] oldHashNodes = hashNodes;
        hashNodes = new HashNode[capacity];
        size = 0;

        //Recalculate the hashcode and put into the new bucket
        for (int i = 0; i < oldHashNodes.length; i++) {
            if (oldHashNodes[i] != null) {
                put((K)oldHashNodes[i].key, (V)oldHashNodes[i].value);
            }
        }
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        /**
         *  What I write is different with csip, we need to test it
         *  if (hashNodes[index] != null) {
         *      return (V)hashNodes[index].value;
         *  }
         */
        while (hashNodes[index] != null) {
            if (hashNodes[index].key.equals(key)) {
                return (V)hashNodes[index].value;
            }
            index++;
            index %= capacity;
        }
        return null;
    }

    @Override
    public V delete(K key) {
        int index = hash(key);
        V val;
        while (hashNodes[index] != null) {
            if (hashNodes[index].key.equals(key)) {
                val = (V)hashNodes[index].value;
                hashNodes[index] = null;
                size--;
                return val;
            }
            index++;
            index %= capacity;
        }
        return null;
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
        for (HashNode<K, V> hashNode : hashNodes) {
            if (hashNode != null) {
                System.out.println("key : " + hashNode.key + " value : " + hashNode.value);
            }
        }
        System.out.println("size: " + size);
        System.out.println("capacity: " + capacity);
    }
}
