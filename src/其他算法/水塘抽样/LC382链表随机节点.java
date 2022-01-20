package 其他算法.水塘抽样;

import common.ListNode;

import java.util.Random;
/**
 * @author Chthollist email:
 * @create 2022-01-20 0:05
 */
public class LC382链表随机节点 {

    // 方法一：水塘抽样
    ListNode head;
    Random random;

    public LC382链表随机节点(ListNode head) {
        this.head = head;
        random = new Random();
    }

    public int getRandom() {
        int i = 0;
        int res = 0;
        ListNode cur = head;
        while(cur != null) {
            i++;
            if(random.nextInt(i) == 0){
                res = cur.val;
            }
            cur = cur.next;
        }
        return res;
    }


    // 方法二：遍历链表
    public int getRandom2() {
        int len = 0;
        ListNode cur = head;
        while(cur != null) {
            cur = cur.next;
            len++;
        }
        int index = (int) random.nextInt(len);
        cur = head;
        while(index > 0) {
            index--;
            cur = cur.next;
        }
        return cur.val;
    }

}
