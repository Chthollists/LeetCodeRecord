package common;

/**
 * @author Chthollist email:
 * @create 2022-01-20 0:06
 */
public class ListNode {
    public int val;

    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}