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
     * ---------------------------- 栈 ----------------------------
     */

    /**
     * 链表实现栈
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
}
