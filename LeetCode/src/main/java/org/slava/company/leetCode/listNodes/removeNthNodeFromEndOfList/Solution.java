package org.slava.company.leetCode.listNodes.removeNthNodeFromEndOfList;

public class Solution {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        int i = 1;
        {
            ListNode list = new ListNode(1);
            list.next = new ListNode(2);
            list.next.next = new ListNode(3);
            list.next.next.next= new ListNode(4);
            list.next.next.next.next = new ListNode(5);
            System.out.println("Test " + i + " result: ");
            ListNode result = removeNthFromEnd(list, 1);
            System.out.println("First list");
            while (list != null){
                System.out.print(list.val + ", ");
                list = list.next;

            }
            System.out.println("Result list");
            while (result != null){
                System.out.print(result.val + ", ");
                result = result.next;
            }
        }
    }
}
