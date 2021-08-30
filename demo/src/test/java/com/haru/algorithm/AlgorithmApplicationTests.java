package com.haru.algorithm;

import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.Queue;

@SpringBootTest
class AlgorithmApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * ############################# 时间复杂度 #############################
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
     * ############################# 数据结构 #############################
     */

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
            top = new LinkNode(value, top);
        }

        public LinkNode pop() {
            if (top == null) {
                return null;
            }
            LinkNode topResult = top;
            top = top.next;
            return topResult;
        }

        public boolean isEmpty() {
            return top == null;
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
     * ---------------------------- 二叉搜索树 ----------------------------
     */

    class BinarySearchTree {

        private TreeNode tree;

        public void insert(int value) {
            TreeNode insertTreeNode = new TreeNode();
            insertTreeNode.setValue(value);
            TreeNode treeNode = tree;
            if (tree == null) {
                tree = insertTreeNode;
            } else {
                while (true) {
                    if (treeNode.value <= value) {
                        if (treeNode.right == null) {
                            treeNode.right = insertTreeNode;
                            return;
                        }
                        treeNode = treeNode.right;
                    } else {
                        if (treeNode.left == null) {
                            treeNode.left = insertTreeNode;
                            return;
                        }
                        treeNode = treeNode.left;
                    }
                }
            }
        }

        public TreeNode find(int value) {
            if (tree == null) {
                return null;
            }
            TreeNode treeNode = tree;
            while (treeNode != null) {
                if (treeNode.value < value) {
                    treeNode = tree.right;
                } else if (treeNode.value > value) {
                    treeNode = treeNode.left;
                } else {
                    return treeNode;
                }
            }
            return null;
        }

        public void delete(int value) {
            if (tree == null) {
                return;
            }

            if (tree.value == value) {
                // 删除的是根节点
                tree = null;
                return;
            }

            TreeNode deleteTreeNode = tree;
            TreeNode deleteTreeNodeParent = null;

            // 查找要删除的节点和其父节点
            while (deleteTreeNode != null) {
                deleteTreeNodeParent = deleteTreeNode;
                if (deleteTreeNode.value < value) {
                    deleteTreeNode = tree.right;
                } else if (deleteTreeNode.value > value) {
                    deleteTreeNode = deleteTreeNode.left;
                } else {
                    break;
                }
            }

            // 没有找到
            if (deleteTreeNode == null) {
                return;
            }

            // 被删除的节点有左右节点，要从右子树找最小的节点值替换被删除的节点，之后去删除找到的最小节点；从右子树找是为了尽量想完全二叉树靠拢
            if (deleteTreeNode.left != null && deleteTreeNode.right != null) {
                TreeNode minTreeNode = deleteTreeNode.right;
                TreeNode minTreeNodeParent = deleteTreeNode;
                while (minTreeNode.left != null) {
                    // 左子树肯定比当前的minTreeNode小
                    minTreeNodeParent = minTreeNode;
                    minTreeNode = minTreeNode.left;
                }
                deleteTreeNode.value = minTreeNode.value;
                deleteTreeNode = minTreeNode;
                deleteTreeNodeParent = minTreeNodeParent;
            }

            // 查找删除节点的子节点
            TreeNode childTreeNode = null;
            if (deleteTreeNode.left != null) {
                childTreeNode = deleteTreeNode.left;
            } else if (deleteTreeNode.right != null) {
                childTreeNode = deleteTreeNode.right;
            }

            // 判断删除的节点时父节点的左节点还是右节点
            if (deleteTreeNodeParent.left == deleteTreeNode) {
                deleteTreeNodeParent.left = childTreeNode;
            } else {
                deleteTreeNodeParent.right = childTreeNode;
            }
        }
    }

    /**
     * ---------------------------- 跳表 ----------------------------
     */

    /**
     * * ---->  * -------------------------------> NULL
     * * ---->  * -------------------------------> NULL
     * -1* ----> 4* ---------------------->  * ----> NULL
     * * ---->  * -------------> 8* ----> 9* ----> NULL
     * * ---->  * ----> 6* ---->  * ---->  * ----> NULL
     */
    class SkipList {
        class SkipNode {
            private int value;

            /**
             * 当前节点占几层
             */
            private int maxLevel;
            /**
             * 每一层的后继节点
             */
            private SkipNode[] perLevelNext;

            public SkipNode(int value, int maxLevel) {
                this.value = value;
                this.maxLevel = maxLevel;
                this.perLevelNext = new SkipNode[maxLevel];
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public int getMaxLevel() {
                return maxLevel;
            }

            public void setMaxLevel(int maxLevel) {
                this.maxLevel = maxLevel;
            }

            public SkipNode[] getPerLevelNext() {
                return perLevelNext;
            }

            public void setPerLevelNext(SkipNode[] perLevelNext) {
                this.perLevelNext = perLevelNext;
            }
        }

        /**
         * 用于随机生成节点高度
         */
        private static final float SKIP_LIST_RANDOM = 0.5f;

        /**
         * 控制节点最大高度为16
         */
        private static final int MAX_LEVEL = 16;

        /**
         * 跳表高度
         */
        private int level;

        /**
         * 哨兵头节点
         */
        private SkipNode head = new SkipNode(-1, MAX_LEVEL);

        /**
         * 高度每加一概率就减半。因为理论上一层的节点个数都比下一层少一半。
         *
         * @return 随机高度
         */
        public int randomLevel() {
            int level = 1;
            while (Math.random() < SKIP_LIST_RANDOM && level < MAX_LEVEL) {
                level++;
            }
            return level;
        }

        public void insert(int value) {
            int i;
            int randomLevel = randomLevel();
            SkipNode skipNode = new SkipNode(value, randomLevel);

            SkipNode[] preLevelPreviousSkipNodes = new SkipNode[randomLevel];

            SkipNode forwardSkipNode = head;
            // 每一层从头节点开始向后遍历，找该节点插入的位置，它的前节点比它小，后节点比它大，也就是找第一个比它大的节点的前一个节点
            for (i = randomLevel - 1; i >= 0; i--) {
                while (forwardSkipNode.perLevelNext[i] != null && forwardSkipNode.perLevelNext[i].value < value) {
                    forwardSkipNode = forwardSkipNode.perLevelNext[i];
                }
                // 暂存这一层的前节点
                preLevelPreviousSkipNodes[i] = forwardSkipNode;
            }
            // 然后将该节点在每一层插入
            for (i = 0; i < randomLevel; i++) {
                skipNode.perLevelNext[i] = preLevelPreviousSkipNodes[i].perLevelNext[i];
                preLevelPreviousSkipNodes[i].perLevelNext[i] = skipNode;
            }

            // 更新跳表高度
            if (level < randomLevel) {
                level = randomLevel;
            }
        }

        public void delete(int value) {
            // 删除节点，为了不再去调用find找出节点的最大层数的消耗，直接当成拥有当前跳表的最大层，每层进行判断，通常该节点在某一层不存在时会快速结束
            SkipNode[] preLevelPreviousSkipNodes = new SkipNode[level + 1];
            SkipNode forwardSkipNode = head;
            for (int i = level; i >= 1; i--) {
                // 删除的值大于跳表中所有的值，forwardSkipNode.perLevelNext[i]会落在最后一个节点上，此时不再要向后查找，直接返回了
                while (forwardSkipNode.perLevelNext[i] != null && forwardSkipNode.perLevelNext[i].value < value) {
                    forwardSkipNode = forwardSkipNode.perLevelNext[i];
                }
                preLevelPreviousSkipNodes[i] = forwardSkipNode;
            }
            // 此时forwardSkipNode落在第一层。若删除的值不在跳表中forwardSkipNode.perLevelNext[0].value != value；
            // forwardSkipNode.perLevelNext[0] == null要不跳表为空，要不删除的值大于跳表中所有的值
            if (forwardSkipNode.perLevelNext[0] != null && forwardSkipNode.perLevelNext[0].value == value) {
                for (int i = level; i >= 0; i--) {
                    // 每一层的前节点执行当前节点的下一个
                    preLevelPreviousSkipNodes[i].perLevelNext[i] = preLevelPreviousSkipNodes[i].perLevelNext[i].perLevelNext[i];
                }
            }
            // 更新跳表高度，头节点没一层若是指定NULL的，这一层就是空层
            while (head.perLevelNext[level] == null) {
                level--;
            }
        }

        public SkipNode find(int value) {
            SkipNode forwardSkipNode = head;
            for (int i = level; i >= 0; i++) {
                while (forwardSkipNode.perLevelNext[i] != null && forwardSkipNode.perLevelNext[i].value < value) {
                    forwardSkipNode = forwardSkipNode.perLevelNext[i];
                }
            }
            if (forwardSkipNode.perLevelNext[0] != null && forwardSkipNode.perLevelNext[0].value == value) {
                return forwardSkipNode.perLevelNext[0];
            } else {
                return null;
            }
        }
    }

    /**
     * ---------------------------- 堆 ----------------------------
     *  堆中数据的第一个位置为空，便于操作；用数组表示堆，当前下标为i,则左节点的下标为2i，右节点为2i+1，父节点为i/2；叶子节点从n/2到n，
     *  n表示堆元素个数。
     */
    static class Heap {
        /**
         * 首位为空
         */
        private int[] items;

        /**
         * 数组的大小，不包括空的首位
         */
        private int effectiveCount;

        /**
         * 堆的大小，是动态变化的
         */
        private int heapCount;

        public Heap(int capacity) {
            items = new int[capacity + 1];
            effectiveCount = capacity;
            heapCount = 0;
        }

        // 插入
        public void insert(int value) {
            if (heapCount >= effectiveCount) {
                return;
            }
            items[heapCount++] = value;
            // 循环的终止条件1、到了根节点；2、当前节点不大于父节点
            int currentIndex = heapCount;
            while (currentIndex > 1 && items[currentIndex] > items[currentIndex / 2]) {
                swap(items, currentIndex, currentIndex / 2);
                currentIndex = currentIndex / 2;
            }
        }

        public static void swap(int[] items, int i, int j) {
            int temp = items[i];
            items[i] = items[j];
            items[j] = temp;
        }

        // 删除堆顶
        public void removeTop() {
            if (heapCount == 0) {
                return;
            }
            items[1] = items[heapCount];
            heapCount--;
            sinkNode(items, 1, heapCount);
        }

        /**
         * 从上往下堆化
         *
         * @param items        原数组
         * @param currentIndex 当前的下标
         * @param heapCount    堆的当前大小
         */
        public static void sinkNode(int[] items, int currentIndex, int heapCount) {
            while (true) {
                int maxIndex = currentIndex;
                // 左节点存在且小于左节点
                if (2 * currentIndex <= heapCount && items[currentIndex] < items[2 * currentIndex]) {
                    maxIndex = 2 * currentIndex;
                }
                // 右节点存在且小于右节点
                if (2 * currentIndex + 1 <= heapCount && items[maxIndex] < items[2 * currentIndex + 1]) {
                    maxIndex = 2 * currentIndex + 1;
                }
                if (currentIndex == maxIndex) {
                    // 不再交换就结束了
                    return;
                }
                swap(items, currentIndex, maxIndex);
                currentIndex = maxIndex;
            }
        }

        /**
         * 建堆，要堆化所有非叶子节点
         *
         * @param items     元素数组，首位是空
         * @param heapCount 构建的堆大小，不一定等于数组的实际大小，比如删了几个根节点后排序
         */
        public static void arrayToHeap(int[] items, int heapCount) {
            for (int i = heapCount / 2; i >= 1; i--) {
                sinkNode(items, i, heapCount);
            }
        }
    }

    /**
     * ---------------------------- 图 ----------------------------
     */
    static class Graph {

        /**
         * 邻接表，vertexs数组每一项都是个LinkedList，每个LinkedList的第一个是图的顶点，后面接的是它相邻的顶点；每个邻接叫做一个边
         */
        private static LinkedList<Integer> vertexs[];

        public static int vertexCount;

        @SuppressWarnings("unchecked")
        public Graph(int vertexCount) {
            this.vertexCount = vertexCount;
            this.vertexs = new LinkedList[vertexCount];
            for (int i = 0; i < vertexCount; i++) {
                vertexs[i] = new LinkedList<>();
            }
        }

        /**
         * 无向图。为了便于处理，顶点的值就是顶点的下标；所以我们要就所以顶点都顺序编号，vertexs只存编号，相当于是值的key
         */
        public void insert(int startVertex, int endVertex) {
            vertexs[startVertex].add(endVertex);
            vertexs[endVertex].add(startVertex);
        }
    }

    /**
     * ############################# 递归 #############################
     */

    /**
     * ---------------------------- 全排列 ----------------------------
     */
    public void printPermutations(int[] items, int size, int k) {

    }

    /**
     * ############################# 排序 #############################
     */

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
     * 最外层执行step = step / 2，当step ==1时是最后一次执行，使用说代码执行多少次step会为1呢，step每次循环都除以2，得到n/2^x=1，
     * x代表除以多少次2结果会得到1， 也就是循环体执行的次数，得到x=log₂n，完成的次数还有+1才结束；所以时间复杂度是O(logn)。
     * 现在看for的循环体，最好情况是数组已经有序，相当于最里层的for只会执行一次就break了，也就只有一层循环；假设count无穷大，
     * step即使每次除以2，也可以看做是无穷大， 使用for循环每次可以任务是执行了n次，所以时间复杂度是O(nlogn)。
     * 最坏情况，最里层的for每次都执行个完整的，和外层for一起也就相当于是插入排序了，但for循环受限与step，step每次都是n½，
     * 不是整个数组循环， 时间复杂度可以认为是O(n²/n½)=O(n)，再乘上O(logn)，得到的时间复杂度也就是O(nlogn)。
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
     * ---------------------------- 选择排序 ----------------------------
     */
    public void selectSort(int[] items, int count) {
        if (count <= 1) {
            return;
        }
        for (int i = 0; i < count - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < count; j++) {
                if (items[j] < items[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = items[minIndex];
            items[minIndex] = items[i];
            items[i] = temp;
        }
    }

    /**
     * ---------------------------- 归并排序 ----------------------------
     */
    public void mergeSort(int[] items, int start, int end) {
        if (start >= end) {
            return;
        }
        // 确定中间点
        int mid = (end - start) / 2 + start;
        mergeSort(items, start, mid);
        mergeSort(items, mid + 1, end);
        // 合并
        merge(items, start, mid, end);
    }

    private void merge(int[] items, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        // 临时存储合并排序后的数组
        int[] temp = new int[end - start + 1];
        int k = 0;
        while (i <= mid && j <= end) {
            if (items[i] <= items[j]) {
                temp[k++] = items[i++];
            } else {
                temp[k++] = items[j++];
            }
        }
        // i或j可能有一方面并没有遍历完
        int surplusStart;
        int surplusEnd;
        if (i <= mid) {
            surplusStart = i;
            surplusEnd = mid;
        } else {
            surplusStart = j;
            surplusEnd = end;
        }

        // 将剩余的复制进temp
        while (surplusStart <= surplusEnd) {
            temp[k++] = items[surplusStart++];
        }

        // 替换原数组
        for (i = 0; i < temp.length; i++) {
            items[start + i] = temp[i];
        }
    }

    /**
     * ---------------------------- 快速排序 ----------------------------
     */
    public void quickSort(int[] items, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = findMid(items, start, end);
        quickSort(items, start, mid - 1);
        quickSort(items, mid + 1, end);
    }

    private int findMid(int[] items, int start, int end) {
        int sortEnd = start;  // sortEnd是有序的末尾
        int j = start; // j是为了遍历数组
        int pivot = items[end];
        for (; j < end; j++) {
            // 找比pivot小的
            if (items[j] < pivot) {
                // j遍历处恰好是有序的末尾，不需要交换
                if (sortEnd != j) {
                    // 放到有序的末尾
                    int temp = items[sortEnd];
                    items[sortEnd] = items[j];
                    items[j] = temp;
                }
                // 找到，增加sortEnd
                sortEnd++;
            }
        }
        if (end != sortEnd) {
            // pivot放在有序最后的下一个位置
            int temp = items[end];
            items[end] = items[sortEnd];
            items[sortEnd] = temp;
        }
        return sortEnd;
    }

    /**
     * ---------------------------- 找第K小的 ----------------------------
     */
    public Integer quickFindKth(int[] items, int start, int end, int k) {
        if (end + 1 < k) {
            return null;
        }
        if (start == end) {
            return items[start];
        }
        if (start > end) {
            return null;
        }
        int mid = findMid(items, start, end);
        if (mid + 1 == k) {
            return items[mid];
        } else if (k > mid + 1) {
            return quickFindKth(items, mid + 1, end, k);
        } else {
            return quickFindKth(items, start, mid - 1, k);
        }
    }

    /**
     * ---------------------------- 计数排序 ----------------------------
     */
    public void countingSort(int[] items, int count) {
        if (count < 2) {
            return;
        }
        // 先找到最大的下标
        int max = items[0];
        int i = 0;
        for (; i < count; i++) {
            if (items[i] > max) {
                max = i;
            }
        }
        // 构造一个数组，用于表示每一项加上前面的一项，表示包含这个值items[i]在内前面有多少个数，数组大小就是items中最大值
        int[] frontCountArray = new int[max + 1];
        for (i = 0; i < count; i++) {
            frontCountArray[items[i]]++;
        }
        for (i = 1; i < count; i++) {
            frontCountArray[i] += frontCountArray[i - 1];
        }

        // 用于排序的数组
        int[] sortArray = new int[count];

        // 保证稳定性，从后向前遍历
        for (i = count - 1; i >= 0; i--) {
            int sortArrayIndex = frontCountArray[items[i]] - 1; // 在sortArray中，items[i]应该在哪个位置
            sortArray[sortArrayIndex] = items[i];
            frontCountArray[items[i]]--;
        }

        // 替换原数组
        for (i = 0; i < count; i++) {
            items[i] = sortArray[i];
        }
    }

    /**
     * ---------------------------- 基数排序 ----------------------------
     */
    public void radixSort(int[] items, int count) {
        if (count < 2) {
            return;
        }
        int max = items[0];
        int i = 0;
        for (; i < count; i++) {
            if (items[i] > max) {
                max = i;
            }
        }

        // 确定值最多有多少位，遍历每一位
        for (i = 0; max / i > 0; i *= 10) {
            countingSortByRadix(items, count, i);
        }
    }

    /**
     * ---------------------------- 基数排序 ----------------------------
     */
    public void countingSortByRadix(int[] items, int count, int exponent) {
        // 每一位都是0~9，所以大小时10
        int i;
        int[] frontCountArray = new int[10];
        for (i = 0; i < count; i++) {
            // 计算值每一位上的数
            frontCountArray[items[i] / exponent % 10]++;
        }
        for (i = 1; i < count; i++) {
            frontCountArray[i] += frontCountArray[i - 1];
        }

        // 用于排序的数组
        int[] sortArray = new int[count];

        // 保证稳定性，从后向前遍历
        for (i = count - 1; i >= 0; i--) {
            sortArray[frontCountArray[items[i] / exponent % 10] - 1] = items[i];
            frontCountArray[items[i] / exponent % 10]--;
        }

        // 替换原数组
        for (i = 0; i < count; i++) {
            items[i] = sortArray[i];
        }
    }

    /**
     * ---------------------------- 桶排序 ----------------------------
     */
    public void bucketSort(int[] items, int count, int bucketSize) {
        if (count < 2) {
            return;
        }

        int max = items[0];
        int min = items[0];
        int i = 0;
        int j;
        for (; i < count; i++) {
            if (items[i] > max) {
                max = i;
            }
            if (items[i] < min) {
                min = i;
            }
        }
        // 桶个数
        int bucketCount = (max - min) / bucketSize + 1;
        // 二维数组存放每个桶内元素
        int[][] bucket = new int[bucketCount][bucketSize];
        // 每个桶真正的元素个数，同时还能表示某个桶中元素的下标，只要再使用后递增
        int[] bucketRealSize = new int[bucketCount];

        for (i = 0; i < count; i++) {
            int bucketIndex = (items[i] - min) / bucketSize;
            if (bucketRealSize[bucketIndex] == bucket[bucketIndex].length) {
                // 桶中元素的个数要超过初始设定的每个桶容量，就要扩容
                int[] newBucketItems = new int[bucket[bucketIndex].length * 2];
                for (j = 0; j < bucket[bucketIndex].length; j++) {
                    newBucketItems[j] = bucket[bucketIndex][j];
                }
                bucket[bucketIndex] = newBucketItems;
            }
            bucket[bucketIndex][bucketRealSize[bucketIndex]++] = items[i];
        }

        int k = 0;
        for (i = 0; i < bucketCount; i++) {
            // 桶中只有真正有元素才快排
            if (bucketRealSize[i] == 0) {
                continue;
            }
            quickSort(bucket[i], 0, bucketRealSize[i] - 1);
            // 替换原数组
            for (j = 0; j < bucketRealSize[i]; j++) {
                items[k++] = bucket[i][j];
            }
        }
    }


    /**
     * ############################# 查找 #############################
     */

    /**
     * ---------------------------- 二分查找非递归 ----------------------------
     */
    public int bSearch(int[] items, int value) {
        if (items.length == 0) {
            return -1;
        }
        int low = 0;
        int high = items.length - 1;
        while (low <= high) {
            int mid = low + (high - low) >> 1;
            if (items[mid] == value) {
                return mid;
            } else if (items[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * ---------------------------- 二分查找递归 ----------------------------
     */
    public int bSearchRecursion(int[] items, int low, int high, int value) {
        if (items.length == 0 || low > high) {
            return -1;
        }
        int mid = low + (high - low) >> 1;
        if (items[mid] == value) {
            return mid;
        } else if (items[mid] > value) {
            return bSearchRecursion(items, low, mid - 1, value);
        } else {
            return bSearchRecursion(items, mid + 1, high, value);
        }
    }

    /**
     * ---------------------------- 二分查找第一个等于 ----------------------------
     */
    public int bSearchFirstEq(int[] items, int value) {
        if (items.length == 0) {
            return -1;
        }
        int low = 0;
        int high = items.length - 1;
        while (low <= high) {
            int mid = low + (high - low) >> 1;
            if (items[mid] == value) {
                // 若前一个也是等于value，说明mid不是第一个等于的，要缩到前半部；mid为0是个特殊情况，肯定是第一个了
                if (mid == 0 || items[mid - 1] != value) {
                    return mid;
                }
                high = mid - 1;
            } else if (items[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * ---------------------------- 二分查找最后一个等于 ----------------------------
     */
    public int bSearchLastEq(int[] items, int value) {
        if (items.length == 0) {
            return -1;
        }
        int low = 0;
        int high = items.length - 1;
        while (low <= high) {
            int mid = low + (high - low) >> 1;
            if (items[mid] == value) {
                // 若后一个也是等于value，说明mid不是最后一个等于的，要缩到后半部；mid为数组末尾是个特殊情况，肯定是最后一个了
                if (mid == items.length - 1 || items[mid + 1] != value) {
                    return mid;
                }
                low = mid + 1;
            } else if (items[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * ---------------------------- 二分查找第一个大于等于 ----------------------------
     */
    public int bSearchFirstGt(int[] items, int value) {
        if (items.length == 0) {
            return -1;
        }
        int low = 0;
        int high = items.length - 1;
        while (low <= high) {
            int mid = low + (high - low) >> 1;
            if (items[mid] >= value) {
                // 若前一个也是大于等于value，说明mid不是第一个大于等于的，要缩到前半部；mid为0是个特殊情况，肯定是第一个了
                if (mid == 0 || items[mid - 1] < value) {
                    return mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * ---------------------------- 二分查找最后一个小于等于 ----------------------------
     */
    public int bSearchLastLt(int[] items, int value) {
        if (items.length == 0) {
            return -1;
        }
        int low = 0;
        int high = items.length - 1;
        while (low <= high) {
            int mid = low + (high - low) >> 1;
            if (items[mid] <= value) {
                // 若后一个也是小于等于value，说明mid不是最后一个小于等于的，要缩到后半部；mid为数组末尾是个特殊情况，肯定是最后一个了
                if (mid == items.length - 1 || items[mid + 1] > value) {
                    return mid;
                }
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * ---------------------------- 二叉树遍历 ----------------------------
     */

    @Data
    @ToString
    class TreeNode {
        private int value;

        private TreeNode left;

        private TreeNode right;
    }

    /**
     * 递归前序
     */
    public void preOrderRecursion(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.println(treeNode.value);
        preOrderRecursion(treeNode.left);
        preOrderRecursion(treeNode.right);
    }

    /**
     * 递归中序
     */
    public void inOrderRecursion(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        preOrderRecursion(treeNode.left);
        System.out.println(treeNode.value);
        preOrderRecursion(treeNode.right);
    }

    /**
     * 递归后序
     */
    public void postOrderRecursion(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        preOrderRecursion(treeNode.left);
        preOrderRecursion(treeNode.right);
        System.out.println(treeNode.value);
    }

    /**
     * 深度
     */
    public int deepth(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        return Math.max(deepth(treeNode.left), deepth(treeNode.right));
    }

    /**
     * 非递归前序 遍历栈，当前节点不为空：打印 -> 入栈 -> 取左节点；为空：出栈 -> 取右节点
     */
    public void preOrder(TreeNode treeNode) {
        java.util.Stack<TreeNode> stack = new java.util.Stack<>();
        if (treeNode == null) {
            return;
        }
        while (!stack.isEmpty()) {
            if (treeNode != null) {
                System.out.println(treeNode.value);
                stack.push(treeNode.left);
                treeNode = treeNode.left;
            } else {
                treeNode = stack.pop().right;
            }
        }
    }

    /**
     * 非递归中序 遍历栈，当前节点不为空： 入栈 -> 取左节点；为空：出栈 -> 打印 -> 取右节点
     */
    public void inOrder(TreeNode treeNode) {
        java.util.Stack<TreeNode> stack = new java.util.Stack<>();
        if (treeNode == null) {
            return;
        }
        while (!stack.isEmpty()) {
            if (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            } else {
                treeNode = stack.pop();
                System.out.println(treeNode.value);
                treeNode = treeNode.right;
            }
        }
    }

    /**
     * 非递归后序 遍历栈，至于左右节点都被访问过了或者左右节点都为空才可以打印当前节点，否则子节点按照右左的顺序入栈
     */
    public void postOrder(TreeNode treeNode) {
        java.util.Stack<TreeNode> stack = new java.util.Stack<>();
        if (treeNode == null) {
            return;
        }
        stack.push(treeNode);
        // 记录上次访问的节点
        TreeNode previousTreeNode = null;
        while (!stack.isEmpty()) {
            treeNode = stack.peek();
            if ((treeNode.left == null && treeNode.right == null) ||
                    (previousTreeNode != null && (treeNode.left == previousTreeNode || treeNode.right == previousTreeNode))) {
                System.out.println(treeNode.value);
                previousTreeNode = stack.pop();
            } else {
                if (treeNode.right != null) {
                    stack.push(treeNode.right);
                }
                if (treeNode.left != null) {
                    stack.push(treeNode.left);
                }
            }
        }
    }

    /**
     * 按层 遍历链表，访问当前节点后将左右节点放入链表中，这样链表中必定按层级顺序存储
     */
    public void levelOrder(TreeNode treeNode) {
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(treeNode);
        while (!list.isEmpty()) {
            treeNode = list.poll();
            System.out.println(treeNode.value);
            if (treeNode.left != null) {
                list.push(treeNode.left);
            }
            if (treeNode.right != null) {
                list.push(treeNode.right);
            }
        }
    }

    /**
     * 这里堆排序包含了构建一个堆了，堆排序只要将原数组排成一个有序的就行了
     */
    public void heapSort(int[] items, int heapCount) {
        Heap.arrayToHeap(items, heapCount);
        while (heapCount > 1) {
            // 每次堆顶节点和最后一个节点交换位置，那么后面比是有序的了
            Heap.swap(items, 1, heapCount);
            // 交换完之后，不需要再处理数组尾部元素，也就是堆的大小减小；重新堆化剩下的节点
            Heap.sinkNode(items, heapCount--, heapCount);
        }
    }

    /**
     * ---------------------------- 搜索 ----------------------------
     */

    /**
     * ---------------------------- 广度优先算法 ----------------------------
     */

    /**
     * 此算法只找一个最短的路径
     */
    public void bfs(int start, int end) {
        if (start == end) {
            return;
        }
        // 用于记录访问过的顶点
        boolean[] visitedVertexs = new boolean[Graph.vertexCount];
        // 记录每个顶点的前驱顶点，用于路径回退打印，数组的下标表示当前顶点，值表示当前顶点的前驱顶点
        Integer[] previousVertex = new Integer[Graph.vertexCount];
        // 存放相邻顶点。广度要求相邻顶点访问玩以后才可以接着访问相邻顶点的相邻顶点
        Queue<Integer> adjacentVertexQueue = new LinkedList<>();
        visitedVertexs[start] = true;
        adjacentVertexQueue.add(start);
        // 遍历完所有顶点
        while (adjacentVertexQueue.size() != 0) {
            Integer currentVertex = adjacentVertexQueue.poll();
            // 遍历当前顶点的后续顶点
            for (int i = 0; i < Graph.vertexs[currentVertex].size(); i++) {
                Integer adjacentVertex = Graph.vertexs[currentVertex].get(i);
                // 判读相邻的这个顶点有没有被访问过
                if (!visitedVertexs[adjacentVertex]) {
                    // 记录相邻顶点的前驱顶点
                    previousVertex[adjacentVertex] = currentVertex;
                    if (adjacentVertex == end) {
                        // 如果找到了终止顶点就打印返回
                        print(previousVertex, start, end);
                        return;
                    }
                    // 此时就是被访问了
                    visitedVertexs[adjacentVertex] = true;
                    // 添加到队列中
                    adjacentVertexQueue.add(adjacentVertex);
                }
            }
        }
    }

    /**
     * 递归打印，回退查找
     */
    public void print(Integer[] previousVertex, int start, int end) {
        // 当回退到end等于start时开始打印
        if (start != end) {
            print(previousVertex, start, previousVertex[end]);
        }
        System.out.print(end + " ");
    }

    /**
     * ---------------------------- 深度优先算法 ----------------------------
     */

    /**
     * 是否已经找到了终止顶点，true找到就开始打印
     */
    public boolean found = false;

    boolean[] visitedVertexs = new boolean[Graph.vertexCount];
    Integer[] previousVertex = new Integer[Graph.vertexCount];

    public void dfs(int start, int end) {
        recursionDFS(start, end);
        print(previousVertex, start, end);
    }

    /**
     * 深度使用了回溯的思想，回溯非常适合用递归来实现。深度先顺着一条路走到头，没有找到就要返回找其他路；递归正是这样，一个递归链没有
     * 找到递归就结束，返回递归链时继续选择另一个递归链
     */
    public void recursionDFS(int start, int end) {
        if (found) {
            return;
        }
        // 确定终止顶点，路径每次递归缩短，使用起始顶点每次都发生变化
        if (start == end) {
            found = true;
            return;
        }
        // 遍历相邻顶点
        for (int i = 0; i < Graph.vertexs[start].size(); i++) {
            Integer adjacentVertex = Graph.vertexs[start].get(i);
            // 判断有没有被访问过
            if (!visitedVertexs[adjacentVertex]) {
                // 设置前驱顶点
                previousVertex[adjacentVertex] = start;
                // 设置了前驱顶点之后就算被访问过了
                visitedVertexs[adjacentVertex] = true;
                // 递归
                recursionDFS(adjacentVertex, end);
            }
        }
    }

    /**
     * ############################# 字符串匹配 #############################
     */

    /**
     * ---------------------------- BM ----------------------------
     */

    /**
     * 将字符转成ASCII码，所以又256种不同的值
     */
    private static final int SIZE = 256;

    /**
     *  a b c b d a b c
     *  b d a
     *        b d a
     *
     *  a b c b d a b c
     *  c b d
     *      c b d
     *
     *  d a b c b c a b c
     *  c b c a b c
     *        c b c a b c
     *
     *  b c b c a b c
     *  c a b c
     *        c a b c
     *  从后向前进遍历模式串和源串的字符进行匹配。
     *  从源串上找到的一个坏字符，其后面有好字符串的话，若好字符串是否匹配模式串另一个子串{u*}，则将模式串移动到{u*}和源串好字符串对齐；
     *  若没有{u*}，判断好字符串的后缀子串是否和模式串的前缀子串有匹配，有将模式串移动到前缀子串和好字符串的后缀子串对齐；
     *  若也没有，则找坏字符在模式串上向左查找下一个匹配的下标，将该下标移动到和坏字符对齐；没有则移动模式串到坏字符之后；
     *  重复以上操作直到找到匹配或模式串滑到最后。
     */
    public int bm(char[] origin, int originLength, char[] pattern, int patternLength) {
        int[] patternLastIndexHashTable = generatePatternLastIndexHashTable(pattern, patternLength);
        int[] publicSubStringBackIndex = new int[patternLength];
        boolean[] publicSubStringBackIsMatchPrefix = new boolean[patternLength];
        generatePublicSubString(pattern, patternLength, publicSubStringBackIndex, publicSubStringBackIsMatchPrefix);

        int originCurrentIndex = 0;
        // 源串下标只需遍历到originLength - patternLength的位置，后面遍历时会加上模式串的下标
        while (originCurrentIndex <= originLength - patternLength) {
            int patternCurrentIndex = patternLength - 1;
            // 模式串从后往前匹配
            for (; patternCurrentIndex >= 0; patternCurrentIndex--) {
                // 比较模式串当前位置和对应的源串的位置
                if (origin[originCurrentIndex + patternCurrentIndex] != pattern[patternCurrentIndex]) {
                    // 坏字符就不用再往回遍历模式串了
                    break;
                }
            }
            if (patternCurrentIndex < 0) {
                // 遍历完模式串了匹配成功
                return originCurrentIndex;
            }

            Integer originJumpDistantWithGoodStringRule = null;
            if (patternCurrentIndex < patternLength - 1) {
                // 当前模式串位置不是尾，那么后面必定是已经匹配上的，也就是有好串
                originJumpDistantWithGoodStringRule = getOriginJumpDistantWithGoodStringRule(patternCurrentIndex, patternLength, publicSubStringBackIndex, publicSubStringBackIsMatchPrefix);
            }
            if (originJumpDistantWithGoodStringRule != null) {
                originCurrentIndex += originJumpDistantWithGoodStringRule;
            } else {
                // 计算出的是只有坏字符规则时应该向后滑到到的距离，其实就是操纵源串的当前下标位置；
                // 从patternLastIndexHashTable查出源串上当前字符在模式串上的最后位置，没有找到返回的是-1；
                // 用当前模式串的下标减去上面的值就是源串下标要跳的距离。
                // 相对的来说，假设模式串是固定的，源串的移动的，那么模式串要向右移动的距离就是源串向左移动的距离；
                // 再假设源串是固定的，那么移动的就是originCurrentIndex，也就是向右移动
                originCurrentIndex += patternCurrentIndex - patternLastIndexHashTable[(int) origin[originCurrentIndex + patternCurrentIndex]];
            }
        }
        return -1;
    }

    private Integer getOriginJumpDistantWithGoodStringRule(int patternCurrentIndex, int patternLength, int[] publicSubStringBackIndex, boolean[] publicSubStringBackIsMatchPrefix) {
        int publicSubStringLength = patternLength - 1 - patternCurrentIndex; // 好后缀长度
        // 如果能从模式串找到除当前匹配的公共子串的另一个子串就直接返回
        if (publicSubStringBackIndex[publicSubStringLength] > -1) {
            // 这里加1是将patternCurrentIndex的下一个下标对齐
            return patternCurrentIndex - publicSubStringBackIndex[publicSubStringLength] + 1;
        }
        // 查找后缀子串，要跳过坏字符的下一个，从下下个开始才算是子串
        for (int prefixSubStringIndex = patternCurrentIndex + 2; prefixSubStringIndex <= patternLength - 1; prefixSubStringIndex++) {
            if (publicSubStringBackIsMatchPrefix[patternLength - prefixSubStringIndex]) {
                // 后缀子串匹配模式串前缀子串，返回当前后缀子串的下标；
                // 因为前缀子串是模式串的开头，要将其移动到后缀子串的位置和源串对齐，相当于直接移动了后缀子串下标数值的距离
                return prefixSubStringIndex;
            }
        }
        return null;
    }

    /**
     * 初始化goodStringNextIndexOnPattern和goodsStringIsMatchPrefixOnPattern 源串的好字符串也就是模式串的后缀子串（称为公共子串），所以可以提前构建模式串的后缀子串和往回第一个匹配的子串的位置对于关系
     *
     * @param pattern                          模式串
     * @param patternLength                    模式串长度
     * @param publicSubStringBackIndex         数组的下标是公共子串的长度，用长度是因为，后缀子串是固定的，值是除模式串后缀子串往回找到的第一个字符发起始下标
     * @param publicSubStringBackIsMatchPrefix 公共子串是否匹配模式串的前缀子串
     */
    private void generatePublicSubString(char[] pattern, int patternLength, int[] publicSubStringBackIndex, boolean[] publicSubStringBackIsMatchPrefix) {

        // 遍历模式串每个字符
        for (int i = 0; i < patternLength - 1; ++i) {
            // 默认都是-1，代表找不到
            publicSubStringBackIndex[i] = -1;
            int currentIndex = i;
            int publicSubStringLength = 1;
            //  从模式串当前下标往回遍历每个字符，公共子串是每次重新从模式串尾往回遍历，
            while (currentIndex >= 0 && pattern[currentIndex] == pattern[patternLength - publicSubStringLength]) {
                // 由于尾子串是固定的，使用相同长度的公共子串对应的BackIndex比会被更新成最新的；
                // 且随着迭代增加，公共子串的长度也再增加，所以每次都会产生新的对应值
                publicSubStringBackIndex[publicSubStringLength++] = currentIndex--;
            }
            // currentIndex为-1时表示一直匹配到了模式串的第一个字符了，也就是公共字符能匹配到模式串的前缀子串
            if (currentIndex == -1) {
                publicSubStringBackIsMatchPrefix[publicSubStringLength - 1] = true;
            }
        }
    }

    /**
     * 用散列表记录模式串中每个字符ASCII码最后出现在模式串的位置，用于坏字符快速查找到其在模式串匹配的下个位置
     */
    private int[] generatePatternLastIndexHashTable(char[] pattern, int patternLength) {
        int[] patternLastIndexHashTable = new int[SIZE];
        for (int i = 0; i < SIZE; ++i) {
            patternLastIndexHashTable[i] = -1;
        }
        for (int i = 0; i < patternLength; ++i) {
            patternLastIndexHashTable[pattern[i]] = i;
        }
        return patternLastIndexHashTable;
    }
}
