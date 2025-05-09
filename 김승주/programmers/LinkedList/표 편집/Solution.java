import java.util.*;

class Solution {
    static Stack<Node> buffer = new Stack<>();
    static boolean[] wasDeleted;
    public String solution(int n, int k, String[] cmd) {
        wasDeleted = new boolean[n];
        Node selected = Node.makeLinkedList(n, k);
        for (String row : cmd) {
            if (row.length() > 1) {
                selected = selected.selectNewNode(row);
            } else if (row.charAt(0) == 'C') {
                selected = selected.removeNode();
            } else {
                Node.restoreRemovedNode();
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(wasDeleted[i] ? 'X' : 'O');
        }
        return sb.toString();
    }
    
    static class Node {
        int index;
        Node prev;
        Node next;
        
        Node (int index) {
            this.index = index;
        }
        
        private static Node makeLinkedList(int n, int startIdx) {
            Node prev = new Node(1);
            Node start = prev;
            for (int i = 0; i < n; i++) {
                Node curr = new Node(i);
                curr.prev = prev;
                prev.next = curr;
                prev = curr;
                if (startIdx == i) {
                    start = curr;
                }
            }
            return start;
        }
        
        private Node selectNewNode(String cmd) {
            int moveCnt = Integer.parseInt(cmd.substring(2, cmd.length()));
            Node curr = this;
            if (cmd.charAt(0) == 'U') {
                for (int i = 0; i < moveCnt; i++) {
                    if (curr.prev == null) {
                        break;
                    }
                    curr = curr.prev;
                }
            } else {
                for (int i = 0; i < moveCnt; i++) {
                    if (curr.next == null) {
                        break;
                    }
                    curr = curr.next;
                }
            }
            return curr;
        }
        
        private Node removeNode() {
            buffer.push(this);
            wasDeleted[this.index] = true;
            
            if (this.prev == null) {
                this.next.prev = null;
            } else {
                this.prev.next = this.next;
            }
            
            if (this.next == null) {
                this.prev.next = null;
            } else {
                this.next.prev = this.prev;
            }

            return (this.next == null)? this.prev : this.next;
        }
        
        private static void restoreRemovedNode() {
            Node recentRemoved = buffer.pop();
            wasDeleted[recentRemoved.index] = false;
            if (recentRemoved.prev != null) {
                recentRemoved.prev.next = recentRemoved;
            }
            
            if (recentRemoved.next != null) {
                recentRemoved.next.prev = recentRemoved;
            }
        }
    }
}