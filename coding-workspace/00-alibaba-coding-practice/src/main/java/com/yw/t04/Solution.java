package com.yw.t04;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by hp on 2019/5/6.
 */
public class Solution {
    public static void main(String[] args){
    }
}
class LockFreeQueue<V> {
    private class Node<V> {
        public V value = null;
        public AtomicReference<Node<V>> next = null;

        public Node(V value, Node next) {
            this.value = value;
            this.next = new AtomicReference<Node<V>>(next);
        }
    }

    private AtomicReference<Node<V>> head = null;
    private AtomicReference<Node<V>> tail = null;
    private AtomicInteger queueSize = new AtomicInteger(0);

    public LockFreeQueue(){
        Node<V> dummy = new Node<V>(null,null);

        head = new AtomicReference<Node<V>>(dummy);
        tail = new AtomicReference<Node<V>>(dummy);
    }

    public void enQueue(V value) {
        Node<V> node = new Node<V>(value,null);
        Node<V> oTail = null;
        while(true){
            oTail = tail.get();
            AtomicReference<Node<V>> nextNode = oTail.next;
            if(nextNode.compareAndSet(null, node)){
                break;
            }else{
                tail.compareAndSet(oTail, oTail.next.get());
            }
        }
        queueSize.getAndIncrement();
        tail.compareAndSet(oTail,oTail.next.get());
    }

    public V deQueue() {
        while(true){
            Node<V> oHead = head.get();
            Node<V> oTail = tail.get();
            AtomicReference<Node<V>> next = oHead.next;

            if(next.get() == null){
                return null;
            }

            if(oHead == tail.get()){
                tail.compareAndSet(oTail, oTail.next.get());
                continue;
            }

            if(head.compareAndSet(oHead,oHead.next.get())){
                queueSize.getAndDecrement();
                return oHead.next.get().value;
            }
        }
    }

    public int size() {
        return queueSize.get();
    }

    public boolean isEmpty() {
        return queueSize.get() == 0;
    }
}
