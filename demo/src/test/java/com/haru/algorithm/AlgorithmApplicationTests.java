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
     * ---------------------------- 位图 ----------------------------
     */

    /**
     * 使用位图来节约存储，比如存0~31数，通常需要长度32的数组，使用位图只需要一个int型就可以了。
     */
    class Bitmap {
        private int[] bitmap;

        /**
         * 要存储多少是个数值
         */
        private int count;

        public Bitmap(int count) {
            // 初始化位图数组个数，bitmap[i]存储的是i * 32 ~ (i + 1) * 32 - 1，小于32的count / 32得到的是0，为了数组有大小，所以+ 1
            this.bitmap = new int[count / 32 + 1];
            this.count = count;
        }

        public void set(int value) {
            if (value > count) {
                return;
            }
            int bitmapIndex = value >> 5;   // value / 32
            int bitIndex = value & 31;  // value % 32
            bitmap[bitmapIndex] |= 1 << bitIndex;   // 找到当前数值所在的数组下标，在相应的二进制位上设为1，保证其他位不变
        }

        public boolean isExist(int value) {
            if (value > count) {
                return false;
            }
            int bitmapIndex = value >> 5;
            int bitIndex = value & 31;
            return (bitmap[bitmapIndex] & (1 << bitIndex)) == 1;
        }
    }


    /**
     * ############################# 排序 #############################
     */

    /**
     * ---------------------------- 冒泡排序 ----------------------------
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

    /**
     * ---------------------------- KMP ----------------------------
     */

    /**
     * 模式串从前向后遍历和源串比较，当发现有个字符不相等时，看前面有多少个匹配的字符，称为好串，若在这个好串中，模式串的前缀子串和源串的好串后缀子串有匹配的，
     * 我们可以向后移动模式串，让其对齐重新比较
     */
    public int kmp(char[] origin, int originLength, char[] pattern, int patternLength) {
        int[] suffixMatchPrefixLastIndexArray = getSuffixMatchPrefixLastIndexArray(pattern, patternLength);
        int currentPatternIndex = 0;
        for (int currentOriginIndex = 0; currentOriginIndex < originLength; currentOriginIndex++) {
            // 一直找到源串和模式串不相符的一个字符，那么前面的就是好串，currentPatternIndex回到0时结束循环，因为模式串已经没有好前缀子串了，没法再用suffixMatchPrefixLastIndexArray查找了
            while (currentPatternIndex > 0 && origin[currentOriginIndex] != pattern[currentPatternIndex]) {
                // 将模式串好前缀子串很源串的前缀好子串的后缀子串对齐，也相当于回退currentPatternIndex下标，
                // currentPatternIndex - 1就是当前模式串好前缀子串的最大子串长度，从suffixMatchPrefixLastIndexArray中找当前最大匹配子串的下标，
                // +1让最大匹配前缀子串的下一个字符对齐源串当前位置
                currentPatternIndex = suffixMatchPrefixLastIndexArray[currentPatternIndex - 1] + 1;
            }
            // 同样这里可能是currentPatternIndex被设回了0，上面的while就没有进行源串和模式串字符等值判断，所以这里再判断一次有可能是相等的，特别处理下模式串的第一个字符
            if (origin[currentOriginIndex] == pattern[currentPatternIndex]) {
                // 继续判断模式串下一个字符
                currentPatternIndex++;
            }
            if (currentPatternIndex == patternLength) {
                return currentOriginIndex - patternLength + 1;
            }
        }
        return -1;
    }

    /**
     * 源串的好前缀的最长可匹配前缀和后缀子串可以用模式串来替代，用一个数组suffixMatchPrefixLastIndexArray来提前存储，
     * suffixMatchPrefixLastIndexArray的下标表示模式串每个前缀子串的终止下标也可以说是前缀子串长度-1，
     * 值是这个前缀子串的后缀子串在模式串上所能匹配的最长的前缀子串的终止下标，没有设为-1。
     * suffixMatchPrefixLastIndexArray[i] = k 也可以表示为pattern[0,i]，使得前k个字符恰等于后k个字符的最大的长度
     * 求suffixMatchPrefixLastIndexArray[i]所能匹配的最大前缀子串的下标要先求suffixMatchPrefixLastIndexArray[i-1]的；
     * 比如suffixMatchPrefixLastIndexArray[i-1]=y,也就是说pattern[0,y]仅和pattern[r,i-1]相等，比r小的下标已经不和0下标相等了，
     * 所以现在求suffixMatchPrefixLastIndexArray[i]的值也只能是pattern[0,y+1]和pattern[r,i]相等，也就是判断pattern[y+1]==pattern[i]即可；
     * 当pattern[y+1]!=pattern[i]时，我们只能找其他办法；假设pattern[0,i]最长匹配后缀子串是pattern[x,i]，
     * 同时这也可以算做是pattern[0,i-1]一个可匹配的后缀子串，但不一定是最大的，叫做次长可匹配的后缀子串，pattern[x,i-1]和pattern[0,i-1-x]匹配，
     * 那么只要pattern[i-x]==pattern[i]，得到pattern[x,i]就是pattern[0,i]最长匹配后缀子串；此外，次长的可匹配的后缀子串肯定被最长可匹配的后缀子串包含了，
     * 最长可匹配后缀子串对应最长可匹配前缀子串是pattern[0, y]，suffixMatchPrefixLastIndexArray[i-1]=y，意思是说次长的后缀子串等于次长前缀子串，
     * 也就是说在pattern[0, y]=pattern[r, i-1]相等，要找pattern[0, 次<y]=pattern[次>r, i-1]，
     * 而且还发现了pattern[0, 次<y]、pattern[次>r, i-1]分别被pattern[0, y]、pattern[r, i-1]包含，这也就变成了在pattern[0, y]找前后缀相同的长度s，
     * s也可以说是可匹配的前缀子串的终止下标，
     * 假设suffixMatchPrefixLastIndexArray[i-1] = j，j就是我们现在要的y；
     * 然后就变成了在pattern[0, j]中查找最长匹配前后缀子串，也就是suffixMatchPrefixLastIndexArray[suffixMatchPrefixLastIndexArray[i-1]]
     */
    private int[] getSuffixMatchPrefixLastIndexArray(char[] pattern, int patternLength) {
        int[] suffixMatchPrefixLastIndexArray = new int[patternLength];
        suffixMatchPrefixLastIndexArray[0] = -1; // 长度为1的子串不存在子串了，直接设为-1
        int suffixMatchPrefixLastIndex = -1;    // 这个值承担了三个角色
        for (int currentPatternIndex = 1; currentPatternIndex < patternLength - 1; currentPatternIndex++) {
            // suffixMatchPrefixLastIndex在循环前就是suffixMatchPrefixLastIndexArray[i-1]的值，-1表示i的前一个i-1没有匹配到前缀子串；
            // 当suffixMatchPrefixLastIndexArray[i-1] > -1时，可进行判断pattern[y+1]==pattern[i]，这里的y就是suffixMatchPrefixLastIndexArray[i-1]，也就是suffixMatchPrefixLastIndex
            while (suffixMatchPrefixLastIndex != -1 && pattern[suffixMatchPrefixLastIndex + 1] != pattern[currentPatternIndex]) {
                // 如果pattern[y+1]!=pattern[i]，找pattern[0,i-1]次长的可匹配的后缀子串，次长的也按照比较下一个位置字符来确定i的最长匹配后缀子串
                suffixMatchPrefixLastIndex = suffixMatchPrefixLastIndexArray[suffixMatchPrefixLastIndex];
            }
            // 按理说下个字符相等的话就算找到了匹配的子串，suffixMatchPrefixLastIndex应该就加1，但是有可能是因为suffixMatchPrefixLastIndex=-1到这里的，
            // 所有这里要特殊的和模式串下标是0的值比较一下；特殊处理的就是最长匹配子串是1的情况。
            if (pattern[suffixMatchPrefixLastIndex + 1] == pattern[currentPatternIndex]) {
                suffixMatchPrefixLastIndex++;
            }
            suffixMatchPrefixLastIndexArray[currentPatternIndex] = suffixMatchPrefixLastIndex;
        }
        return suffixMatchPrefixLastIndexArray;
    }

    /**
     * ---------------------------- Trie树 ----------------------------
     */

    public class TrieTreeACNode {
        private char data;
        /**
         * 后续字符
         */
        private TrieTreeACNode[] children = new TrieTreeACNode[26];
        private boolean isEndingChar = false;

        /**
         * 模式串长度
         */
        private int length;

        /**
         * 失败指针
         */
        private TrieTreeACNode failTrieTreeACNode;

        public TrieTreeACNode(char data) {
            this.data = data;
        }
    }

    public class Trie {
        // 根节点
        private TrieTreeACNode root = new TrieTreeACNode('/');

        public void insert(char[] pattern) {
            TrieTreeACNode currentTrieTreeACNode = root;
            for (char aText : pattern) {
                int index = aText - 'a';
                if (currentTrieTreeACNode.children[index] == null) {
                    // 没有该字符，就新增一个节点
                    currentTrieTreeACNode.children[index] = new TrieTreeACNode(aText);
                }
                // 更新当前节点到子节点上
                currentTrieTreeACNode = currentTrieTreeACNode.children[index];
            }
            // 字符串末尾字符标识
            currentTrieTreeACNode.isEndingChar = true;
            currentTrieTreeACNode.length = pattern.length;
        }

        public void buildFailTrieTreeACode() {
            // 队列用于按层遍历
            Queue<TrieTreeACNode> queue = new LinkedList<>();
            root.failTrieTreeACNode = null;
            queue.add(root);
            while (!queue.isEmpty()) {
                TrieTreeACNode currentNode = queue.remove();
                // 遍历当前节点所有子节点
                for (int i = 0; i < 26; i++) {
                    TrieTreeACNode currentChildrenNode = currentNode.children[i];
                    if (currentChildrenNode == null) {
                        continue;
                    }
                    if (currentNode == root) {
                        // 根节点的子节点的失败指针指向根节点
                        currentChildrenNode.failTrieTreeACNode = root;
                    } else {
                        TrieTreeACNode currentNodeFail = currentNode.failTrieTreeACNode;
                        // 遍历到了根节点的fail了就结束
                        while (currentNodeFail != null) {
                            // 判断当前节点的失败指针指向的节点的子节点是否有当前子节点相同的值，就是取当前子节点的上层去找
                            TrieTreeACNode failNode = currentNodeFail.children[currentChildrenNode.data - 'a'];
                            if (failNode != null) {
                                // 有相同的，就为该子节点的fail
                                currentChildrenNode.failTrieTreeACNode = failNode;
                                break;
                            }
                            // 没有相同的，继续向上上层找
                            currentNodeFail = currentNodeFail.failTrieTreeACNode;
                        }
                        if (currentNodeFail == null) {
                            // 遍历到根节点的fail也没有找到，那设置根节点为当前子节点的fail
                            currentChildrenNode.failTrieTreeACNode = root;
                        }
                    }
                    queue.add(currentChildrenNode);
                }
            }
        }

        public boolean find(char[] pattern) {
            TrieTreeACNode currentTrieTreeACNode = root;
            for (char aPattern : pattern) {
                int index = aPattern - 'a';
                // 若当前的字符不存在该节点的子字符散列表中，匹配失败
                if (currentTrieTreeACNode.children[index] == null) {
                    return false;
                }
                // 继续比较下一个字符
                currentTrieTreeACNode = currentTrieTreeACNode.children[index];
            }
            // isEndingChar为false的话就表示只是前缀字符串
            return currentTrieTreeACNode.isEndingChar;
        }

        /**
         *                  root
         *                 / | \
         *               s   h  e
         *             /    |    \
         *           h    ②e     k
         *         /       |
         *     ①e       ③k
         *     /
         *   j
         *   假设有如上Trie树，当前的节点在①出，但是它的子节点K没能和源串此时的字符匹配上。
         *   但是可以发现she已经是匹配上的，所以he和e也是匹配上的，那么可以用这个后缀去其他树枝上查找出其他的模式串，
         *   要得到此时的she的最大后缀匹配模式串的位置，应该已经知道he的了，同理也知道e的了；
         *   用一个指针来指向此时节点最大后缀匹配的其他模式串的位置叫做fail；还可以认为根节点的fail指向null，一方面是为了条件终止，
         *   另一方面也可以代表没有找到匹配的模式串位置；根节点的直接子节点的fail都是根节点；
         *   由回溯思想可以得到此时的fail，也就是说he的fail指向的节点的子节点要有等于此时节点的，那么就找到当前节点的fail了。
         *   那当我们进行匹配时，在①处发生了失配，我们就去找它的fail指向的节点，也就是②处，若②处的子节点有等于当前源串字符的，
         *   我们就重新设置当前位置到③处；然后从这个位置开始：1、判断它是不是终止节点，是就可以进行替换；2、继续找更小的后缀字串匹配的模式串。
         */
        public void match(char[] text) {
            int textLength = text.length;
            TrieTreeACNode currentNode = root;
            for (int i = 0; i < textLength; i++) {
                int index = text[i] - 'a';
                // 因为根节点时无意义的，所以每次匹配都是拿源串当前字符和当前节点的子节点比较的；
                // 如果当前节点和源串当前字符相等就不用去找fail了，直接进行后续的替换或是判断源下个字符
                while (currentNode.children[index] == null && currentNode != root) {
                    // 当前源串的某个字符没有被匹配上，那就要用fail去寻找最大可匹配的后缀字串对应的模式串，
                    // 如果有相应的模式串，就将currentNode设置到此处，之后跳出循环，从该处再判断子节点是否和源串当前位置字符是否相等，
                    // 如果没有相应的模式串，那么循环不会终止，要继续找更小的最大可匹配的后缀字串对应的模式串，
                    // 除非fail指向了root，root不会有fail的，也会终止循环
                    currentNode = currentNode.failTrieTreeACNode;
                }
                // 如果源串当前字符和当前位置的子节点匹配上的，那么必定不会continue，直接更新此时的currentNode到子节点上；
                // 除非是currentNode==root或者是进过while回到了root,那么就有可能匹配不上源串当前字符，所以会直接进行下个字符判断
                currentNode = currentNode.children[index];
                if (currentNode == null) {
                    currentNode = root;
                    continue;
                }
                // currentNode此时就保持不变了，从当前的为再创建一个指针用来进行fail移动，currentNode记录的位置为的是源串的下个字符从该位置匹配
                TrieTreeACNode tempFail = currentNode;
                // 要一直遍历到fail指向了root，因为指向root的该模式串后缀字串是没有相应的其他模式串匹配的，所以没必要再查找能匹配的模式串了
                while (tempFail != root) {
                    // 如果fail指向的节点时个终节点，那么久可以认为是匹配到了一个模式串
                    if (tempFail.isEndingChar) {
                        for (int j = i - 1; j >= i - tempFail.length; j--) {
                            text[j] = '*';
                        }
                    }
                    // 以fail当前的节点位置，继续查找最大后缀字串匹配的模式串
                    tempFail = tempFail.failTrieTreeACNode;
                }
            }
        }
    }

    /**
     * ############################# 思想 #############################
     */

    /**
     * ---------------------------- 回溯 ----------------------------
     */

    /**
     * 八皇后。回溯思想在于列举出所有的途径，每个途径分别尝试一次来得到结果；
     * 通常可以通过递归的方式将途径分成许多小步，每一小步的选择都会对后面有影响；
     * 当某个路径没法得到结果时，要回退，选择其他的路径，直至得到最终结果。
     * 回溯只要得到一个合适的途径即可，其他条件下的流程也就无需进行了，递归体现在只要得到了结果就可以向上返回了，
     * 若是有遍历的话，其他值也就可以不用执行了；当某次执行未能得到结果时，就选择其他的路径，
     * 若是在该步骤下的所有条件都没法得到结果，就要回退上一步，遍历的其他条件就是用在这个时候的。
     */

    /**
     * 下标表示皇后所在的行，值表示皇后所在的列
     */
    private int[] queens = new int[8];

    public Boolean eightQueen(int row) {
        if (row == 8) {
            return true;
        }
        for (int column = 0; column < 8; column++) {
            if (canNextRow(row, column)) {
                queens[row] = column;
                if (eightQueen(row++)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean canNextRow(int row, int column) {
        int leftDiagonal = column - 1;
        int rightDiagonal = column + 1;
        // 向上比较每一行
        for (int previous = row - 1; previous >= 0; previous--) {
            if (queens[previous] == column) {
                // 有在同一列的
                return false;
            }
            if (queens[previous] == leftDiagonal) {
                // 有在左对角线的
                return false;
            }
            if (queens[previous] == rightDiagonal) {
                // 有在右对角线的
                return false;
            }
            leftDiagonal--;
            rightDiagonal++;
        }
        return true;
    }

    /**
     * ---------------------------- 动态规划 ----------------------------
     */


    /**
     * 0-1背包
     */
    public static int knapsack(int[] weight, int[] value, int itemCount, int backpackMaxWeight) {
        int[][] states = new int[itemCount][backpackMaxWeight + 1];

        for (int currentItemIndex = 0; currentItemIndex < itemCount; currentItemIndex++) {
            for (int currentItemWeight = 0; currentItemWeight < backpackMaxWeight + 1; currentItemWeight++) {
                states[currentItemIndex][currentItemWeight] = -1;
            }
        }

        // 第一个物品特殊处理
        states[0][0] = 0;
        if (weight[0] <= backpackMaxWeight) {
            states[0][weight[0]] = value[0];
        }
        // 遍历每一个物品
        for (int currentItemIndex = 1; currentItemIndex < itemCount; currentItemIndex++) { //动态规划，状态转移
            int currentItemWeight = 0;
            // 不放当前的物品，沿用上一次的价值
            for (; currentItemWeight <= backpackMaxWeight; currentItemWeight++) {
                if (states[currentItemIndex - 1][currentItemWeight] > -1) {  // 减少循环体执行次数
                    states[currentItemIndex][currentItemWeight] = states[currentItemIndex - 1][currentItemWeight];
                }
            }
            // 放当前的物品
            for (currentItemWeight = 0; currentItemWeight + weight[currentItemIndex] <= backpackMaxWeight; currentItemWeight++) {
                if (states[currentItemIndex - 1][currentItemWeight] > -1) {
                    int currentValue = states[currentItemIndex - 1][currentItemWeight] + value[currentItemIndex];
                    // 在决策当前物品时，选择相同重量下，更大价值的那个选择
                    if (currentValue > states[currentItemIndex][currentItemWeight + weight[currentItemIndex]]) {
                        states[currentItemIndex][currentItemWeight + weight[currentItemIndex]] = currentValue;
                    }
                }
            }
        }
        // 找出最大价值和重量
        int maxValue = states[itemCount - 1][0];
        int totalWeight = 0;
        for (int currentItemWeight = 1; currentItemWeight <= backpackMaxWeight; ++currentItemWeight) {
            if (states[itemCount - 1][currentItemWeight] > -1) {
                totalWeight = currentItemWeight;
                if (states[itemCount - 1][currentItemWeight] > maxValue) {
                    maxValue = states[itemCount - 1][currentItemWeight];
                }
            }
        }

        // 反向输出所有物品
        for (int currentItemIndex = itemCount - 1; currentItemIndex >= 1; currentItemIndex--) {
            int previousWeight = totalWeight - weight[currentItemIndex];
            // 在不超过背包称重的情况下，放了当前物品，唯一可能就是相同重量情况下价值更大
            if (previousWeight >= 0 && states[currentItemIndex - 1][previousWeight] > -1 &&
                    states[currentItemIndex - 1][previousWeight] + value[currentItemIndex] > states[currentItemIndex - 1][totalWeight]) {
                System.out.print(weight[currentItemIndex] + " ");
                totalWeight = previousWeight;
            }
            if (totalWeight == 0) {
                break;
            }
        }
        // 第一个物品放入时，必定有重量
        if (totalWeight != 0) {
            System.out.print(weight[0]);
        }

        return maxValue;
    }


    /**
     * 最短路径
     */
    public int minDist(int[][] distArray, int count) {
        int[][] states = new int[count][count];
        int sumDist = 0;
        for (int columnIndex = 0; columnIndex < count; columnIndex++) { // 初始化states的第一行数据
            sumDist += distArray[0][columnIndex];
            states[0][columnIndex] = sumDist;
        }
        sumDist = 0;
        for (int rowIndex = 0; rowIndex < count; rowIndex++) { // 初始化states的第一列数据
            sumDist += distArray[rowIndex][0];
            states[rowIndex][0] = sumDist;
        }

        for (int rowIndex = 1; rowIndex < count; rowIndex++) {
            for (int columnIndex = 1; columnIndex < count; columnIndex++) {
                states[rowIndex][columnIndex] = distArray[rowIndex][columnIndex] + Math.min(states[rowIndex][columnIndex - 1], states[rowIndex - 1][columnIndex]);
            }
        }

        return states[count - 1][count - 1];
    }


}
