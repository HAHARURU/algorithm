package com.haru.algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.Queue;

@SpringBootTest
class AlgorithmTests {

    @Test
    void contextLoads() {

    }

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
         * 失败指针，
         */
        private TrieTreeACNode failTrieTreeACNode;

        public TrieTreeACNode(char data) {
            this.data = data;
        }
    }

    public class Trie {
        // 根节点
        private TrieTreeACNode root = new TrieTreeACNode('/');

        public void buildFail() {
            Queue<TrieTreeACNode> queue = new LinkedList<>();
            root.failTrieTreeACNode = null;
            queue.add(root);
            while (!queue.isEmpty()) {
                TrieTreeACNode currentNode = queue.peek();
                for (TrieTreeACNode childrenNode : currentNode.children) {
                    if (childrenNode == null) {
                        continue;
                    }
                    if (currentNode == root) {
                        childrenNode.failTrieTreeACNode = root;
                    } else {
                        TrieTreeACNode currentFailTrieTreeACNode = currentNode.failTrieTreeACNode;
                        while (currentFailTrieTreeACNode != null) {
                            TrieTreeACNode failNode = currentFailTrieTreeACNode.children[childrenNode.data - 'a'];
                            if (failNode != null) {
                                childrenNode.failTrieTreeACNode = failNode;
                                break;
                            }
                            currentFailTrieTreeACNode = currentFailTrieTreeACNode.failTrieTreeACNode;
                        }
                        if (currentFailTrieTreeACNode == null) {
                            childrenNode.failTrieTreeACNode = root;
                        }
                    }
                    queue.add(childrenNode);
                }
            }
        }

        public void match(char[] text) {

        }
    }
}
