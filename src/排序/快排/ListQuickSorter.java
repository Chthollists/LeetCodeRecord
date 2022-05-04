package 排序.快排;

import common.ListNode;

/**
 * @author Chthollist email:
 * @create 2022-05-04 14:19
 */
public class ListQuickSorter {
    static ListNode dummy = new ListNode(-1);

    public static void main(String[] args) {
        ListNode head = new ListNode(7);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(8);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(9);
        ListNode node6 = new ListNode(3);
        ListNode node7 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        dummy.next = head;

        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        quickSort(dummy, 0, n - 1);

        cur = dummy.next;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
    }

    private static void quickSort(ListNode dummy, int l, int r) {
        if (l < r) {
            int idx = partition(dummy.next, l, r);
//            System.out.println("idx : " + idx);
            quickSort(dummy, l, idx - 1);
            quickSort(dummy, idx + 1, r);
        }
    }

    private static int partition(ListNode head, int l, int r) {
        ListNode cur = head;
        int cnt = 0;
        while (cur != null && cnt < l) {
            cnt++;
            cur = cur.next;
        }
        assert cur != null;
//        System.out.println("Pivot : " + cur.val);
        int pivot = cur.val;
        cur = cur.next;
        int mark = l;
        for (int i = l + 1; i <= r && cur != null; i++) {
            if (cur.val < pivot) {
                mark++;
                swap(head, mark, i);
            }
            cur = getNode(head, i);
            cur = cur.next;
        }
        swap(head, l, mark);
        return mark;
    }

    private static void swap(ListNode head, int i, int j) { // i <= j
        if (Math.abs(j - i) == 1) {
            int l = Math.min(i, j);
            ListNode prev = getNode(head, l - 1);
            ListNode left = prev.next;
            ListNode right = left.next;
            ListNode next = right.next;
            prev.next = right;
            right.next = left;
            left.next = next;
            return;
        }
        ListNode lPrev = getNode(head, i - 1);
        ListNode rPrev = getNode(head, j - 1);
        ListNode left = lPrev.next;
        ListNode right = rPrev.next;
        ListNode lNext = left.next;
        ListNode rNext = right.next;

        lPrev.next = right;
        right.next = lNext;
        rPrev.next = left;
        left.next = rNext;

//        System.out.println(i + " --- " + j);
        printList(dummy);
    }

    private static ListNode getNode(ListNode head, int idx) {
        if (idx == -1) return dummy;
        ListNode cur = head;
        int cnt = 0;
        while (cnt < idx && cur != null) {
            cnt++;
            cur = cur.next;
        }
        return cur;
    }

    private static void printList(ListNode head) {
        ListNode cur = head;
        System.out.print("Print : ");
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}
