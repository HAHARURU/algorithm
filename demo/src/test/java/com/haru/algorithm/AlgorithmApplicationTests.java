package com.haru.algorithm;

import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AlgorithmApplicationTests {

    @Test
    void contextLoads() {
        int[] items = new int[]{1, 1, 1, 1, 5};
        System.out.println(quickFindKth(items, 0, items.length - 1, 6));
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
     * ---------------------------- 希尔排序 ---------------------------- 最外层执行step = step / 2，当step ==
     * 1时是最后一次执行，使用说代码执行多少次step会为1呢，step每次循环都除以2，得到n/2^x=1，x代表除以多少次2结果会得到1， 也就是循环体执行的次数，得到x=log₂n，完成的次数还有+1才结束；所以时间复杂度是O(logn)。
     * 现在看for的循环体，最好情况是数组已经有序，相当于最里层的for只会执行一次就break了，也就只有一层循环；假设count无穷大，step即使每次除以2，也可以看做是无穷大， 使用for循环每次可以任务是执行了n次，所以时间复杂度是O(nlogn)。
     * 最坏情况，最里层的for每次都执行个完整的，和外层for一起也就相当于是插入排序了，但for循环受限与step，step每次都是n½，不是整个数组循环， 时间复杂度可以认为是O(n²/n½)=O(n)，再乘上O(logn)，得到的时间复杂度也就是O(nlogn)。
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
     * ############################# 跳表 #############################
     */

    /**
     * -1* ---->  *
     * -1* ---->  *
     * -1* ----> 6* ------------->  * ----> NULL
     * -1* ---->  * -------------> 9* ----> NULL
     * -1* ---->  * ----> 7* ---->  * ----> NULL
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
         * 高度每加一概率就折半。因为理论上一层的节点个数都比下一层少一半。
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

            SkipNode[] prePreviousSkipNodes = new SkipNode[randomLevel];

            SkipNode forwardSkipNode = head;
            // 每一层从头节点开始向后遍历，找该节点插入的位置，它的前节点比它小，后节点比它大，也就是找第一个比它大的节点的前一个节点
            for (i = randomLevel - 1; i >= 0; i--) {
                while (forwardSkipNode.perLevelNext[i] != null && forwardSkipNode.perLevelNext[i].value < value) {
                    forwardSkipNode = forwardSkipNode.perLevelNext[i];
                }
                // 暂存这一层的前节点
                prePreviousSkipNodes[i] = forwardSkipNode;
            }
            // 然后将该节点在每一层插入
            for (i = 0; i < randomLevel; i++) {
                skipNode.perLevelNext[i] = prePreviousSkipNodes[i].perLevelNext[i];
                prePreviousSkipNodes[i].perLevelNext[i] = skipNode;
            }

            // 更新跳表高度
            if (level < randomLevel) {
                level = randomLevel;
            }
        }
    }
}
