import utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    int maxSize;

    Map<String, Node> map = new HashMap<>();
    Node head = null;
    Node tail = null;

    public LRUCache(int maxSize) {
        this.maxSize = maxSize > 1 ? maxSize : 1;

    }

    private void printHead() {
        System.out.println("LIST STATE ");
        Node cur = head;
        System.out.print(cur);
        while (cur.next != null) {
            System.out.print(cur.next);
            cur = cur.next;
        }
        System.out.println("head:"+head+" tail:"+tail );
    }

    public void insertKeyValuePair(String key, int value) {
        if (map.containsKey(key)) {
            System.out.println("insertKeyValuePair: pre-existing " + key + ", " + value);
            map.get(key).setValue(value);
            moveNodeToFront(map.get(key));
        } else {
            if (map.size() == maxSize) {
                map.remove(removeFromTail().key);
            }
            Node node = new Node(key).setValue(value);
            map.put(key, node);
            addToFront(node);
            if (map.size() == 1) {
                tail = node;
            }
        }
    }

    private Node removeFromTail() {
        Node temp = tail;
        Node justBeforeTail = tail.prev;
        if (justBeforeTail == null) return temp;
        justBeforeTail.next = null;
        tail = justBeforeTail;
        System.out.println("removeFromTail: justBeforeTail " + justBeforeTail);
        return temp;
    }

    private void addToFront(Node node) {
        node.next = head;
        if (head != null) head.prev = node;
        head = node;
        System.out.println("addToFront: currentNode " + node);
        printHead();
    }

    private Node moveNodeToFront(Node currentNode) {
        if (head.key.equals(currentNode.key)) return currentNode;

        // remove the node from middle of doubly linked list.
        Node prevOfCur = currentNode.prev;
        Node nextOfCur = currentNode.next;
        prevOfCur.next = nextOfCur;
        if (nextOfCur != null)
            nextOfCur.prev = prevOfCur;
        //handle tail
        if(currentNode.key == tail.key)
            tail = prevOfCur;

        // handle head
        head.prev = currentNode;
        currentNode.next = head;
        currentNode.prev = null;
        head = currentNode;
        System.out.println("moveNodeToFront: currentNode " + currentNode);

        printHead();
        return head;
    }

    public LRUResult getValueFromKey(String key) {
        if (map.containsKey(key)) {
            Node node = moveNodeToFront(map.get(key));
            return new LRUResult(true, node.val);
        }
        return new LRUResult(false, 0);
    }

    public String getMostRecentKey() {
        return head.key;
    }

    static class Node {
        Node prev;
        Node next;
        String key;
        int val;

        public Node(String key) {
            this.key = key;
        }

        public Node setValue(int value) {
            this.val = value;
            return this;
        }

        public String toString() {
            String prev_value = (prev != null) ? prev.val + "" : "null";
            String next_value = (next != null) ? next.val + "" : "null";
            return " <--[" + key + ", " + val + "]--> ";
        }
    }

    static class LRUResult {
        boolean found;
        int value;

        public LRUResult(boolean found, int value) {
            this.found = found;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(4);
        lruCache.insertKeyValuePair("a", 1);
        lruCache.insertKeyValuePair("b", 2);
        lruCache.insertKeyValuePair("c", 3);
        lruCache.insertKeyValuePair("d", 4);
        Utils.assertTrue(lruCache.getValueFromKey("a").value == 1);
        lruCache.insertKeyValuePair("e", 5);

//
//        Utils.assertTrue(lruCache.getMostRecentKey() == "c");
//        Utils.assertTrue(lruCache.getMostRecentKey() == "a");
//        lruCache.insertKeyValuePair("d", 4);
//        var evictedValue = lruCache.getValueFromKey("b");
//        Utils.assertTrue(evictedValue == null || evictedValue.found == false);
//        lruCache.insertKeyValuePair("a", 5);
//        Utils.assertTrue(lruCache.getValueFromKey("a").value == 5);
    }
}

