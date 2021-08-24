package com.haru.algorithm;

import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AlgorithmApplicationTests {

    @Test
    void contextLoads() {
        LinkStack linkStack = new LinkStack();
        linkStack.push("x");
        linkStack.push("y");
        linkStack.push("z");
        System.out.println(linkStack.pop());
        System.out.println(linkStack.pop());
        System.out.println(linkStack.pop());
    }

    /**
     * ---------------------------- 时间复杂度 ----------------------------
     */
    int n;
    int[] arrays = new int[n];
    int count = 0;

    public void insert(int value) {
        if (count == arrays.length) {
            int sum = 0;
            for (int array : arrays) {
                sum += array;
            }
            arrays[0] = sum;
            count = 1;
        }
        arrays[count++] = value;
    }

    /**
     * ---------------------------- 数组 ----------------------------
     */

    /**
     * ---------------------------- 链表 ----------------------------
     */
    @ToString
    @Data
    class LinkNode {
        private String value;
        private LinkNode next;

        public LinkNode(String value, LinkNode next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 单向链表判断回文字符串
     *
     * @param listNode
     * @return
     */
    public Boolean isPalindrome(LinkNode listNode) {
        if (listNode == null || listNode.next == null) {
            return false;
        }

        LinkNode slow = listNode;
        LinkNode fast = listNode;
        LinkNode reverse = null;

        // 链表长度可能是偶数，可能是奇数；偶数fast会为null
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            // 单向链表反转
            LinkNode next = slow.next;
            slow.next = reverse;
            reverse = slow;
            slow = next;
        }

        // 链表长度为奇数，slow要向前进一个，抛弃最中间的
        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            assert reverse != null;
            if (!slow.value.equals(reverse.value)) {
                return false;
            }
            slow = slow.next;
            reverse = reverse.next;
        }

        return true;
    }

    /**
     * ---------------------------- 栈 ----------------------------
     */

    /**
     * 数组实现栈 数组要注意满的情况
     */
    class Stack {
        private String[] items;
        private int size;
        private int count;

        public Stack(int size) {
            this.items = new String[size];
            this.size = size;
            this.count = 0;
        }

        public Boolean push(String value) {
            if (count == size) {
                return false;
            }
            items[count++] = value;
            return true;
        }

        public String pop() {
            if (count == 0) {
                return null;
            }
            return items[count-- - 1];
        }
    }

    /**
     * 链表实现栈 链表要注意空的清空
     */
    @ToString
    class LinkStack {
        private LinkNode top;

        public void push(String value) {
            if (top == null) {
                top = new LinkNode(value, null);
            } else {
                top = new LinkNode(value, top);
            }
        }

        public String pop() {
            if (top == null) {
                return null;
            }
            String value = top.getValue();
            top = top.next;
            return value;
        }


    }

    /**
     * ---------------------------- 队列 ----------------------------
     */
    /**
     * 数组实现队列
     */
    class ArrayQueue {
        private String[] items;
        private int tail;
        private int head;

        public ArrayQueue(int size) {
            this.items = new String[size];
            this.tail = 0;
            this.head = 0;
        }

        public void enqueue(String value) {
            if (tail == items.length) {
                if (head != 0) {
                    for (int i = head; i < tail; head++) {
                        items[i - head] = items[i];
                    }
                    tail = tail - head;
                    head = 0;
                }
            }
            items[tail++] = value;
        }

        public String dequeue() {
            if (head == tail) {
                return null;
            }
            return items[head++];
        }
    }

    /**
     * 循环队列
     */
    class CycleQueue {
        private String[] items;
        private int tail;
        private int head;

        public CycleQueue(int size) {
            this.items = new String[size];
            this.tail = 0;
            this.head = 0;
        }

        public void enqueue(String value) {
            if ((tail + 1) % items.length == head) {
                return;
            }
            items[tail] = value;
            tail = (tail + 1) % items.length;
        }

        public String dequeue() {
            if (head == tail) {
                return null;
            }
            String item = items[head];
            head = (head + 1) % items.length;
            return item;
        }

    }

    /**
     * 链表实现队列
     */
    class LinkQueue {
        private LinkNode head;
        private LinkNode tail;

        public void enqueue(String value) {
            LinkNode linkNode = new LinkNode(value, null);
            if (tail == null) {
                head = linkNode;
                tail = linkNode;
            }
            // 只有一个结点，tail和head都指向这一个结点
            tail.next = linkNode;
            tail = linkNode;
        }

        public String dequeue() {
            if (head == null) {
                return null;
            }
            String value = head.value;
            head = head.next;
            // 出最后一个结点，要取消tail的指向
            if (head == null) {
                tail = null;
            }
            return value;
        }
    }

    /**
     * ---------------------------- 冒泡排序 ----------------------------
     */

    /**
     * 优化版
     */
    public void bubbleSort(int[] items, int count) {
        if (count <= 1) {
            return;
        }
        // 最后一次交换的下标，lastExchange后面的已经有序
        int lastExchange = 0;
        int border = count - 1;

        for (int i = 0; i < count - 1; i++) {
            // 这一次执行冒泡是否没有变换，没有变换不在进行后续的冒泡了
            boolean hasExchange = false;
            for (int j = 0; j < border; j++) {
                if (items[j + 1] < items[j]) {
                    int temp = items[j + 1];
                    items[j + 1] = items[j];
                    items[j] = temp;
                    hasExchange = true;
                    lastExchange = j;
                }
                border = lastExchange;
            }
            if (!hasExchange) {
                break;
            }
        }
    }

    /**
     * ---------------------------- 插入排序 ----------------------------
     */

    /**
     * 尾插入法
     */
    public void insertSort(int[] items, int count) {
        if (count <= 1) {
            return;
        }
        for (int i = 1; i < count; i++) {
            int temp = items[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (items[j] > temp) {
                    items[j + 1] = items[j];
                } else {
                    break;
                }
            }
            items[j + 1] = temp;
        }
    }

    /**
     * ---------------------------- 希尔排序 ----------------------------
     */
    public void shellSort(int[] items, int count) {
        if (count <= 1) {
            return;
        }
        int step = count / 2;
        while (step >= 1) {
            for (int i = step; i < count; i++) {
                int temp = items[i];
                int j = i - step;
                for (; j >= 0; j -= step) {
                    if (items[j] > temp) {
                        items[j + step] = items[j];
                    } else {
                        break;
                    }
                }
                items[j + step] = temp;
            }
            step = step / 2;
        }
    }

    /**
     * 头插入法
     */
    public void insertHeadSort(int[] items, int count) {
        if (count <= 1) {
            return;
        }

    }

    /**
     * ---------------------------- 选择排序 ----------------------------
     */
    public void selectSort(int[] items, int count) {
        if (count <= 1) {
            return;
        }
        for (int i = 0; i < count - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < count; j ++) {
                if (items[j] < items[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = items[minIndex];
            items[minIndex] = items[i];
            items[i] = temp;
        }
    }
}
