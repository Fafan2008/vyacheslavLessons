package org.slava.company.leetCode.listNodes.swappingNodesInLinkedList;

public class Solution {
    public static ListNode swapNodes(ListNode head, int k) {
        ListNode result = new ListNode();
        result.next = head;
        ListNode outrunning = result;
        ListNode lagging = result;
        for(int i = 0; i < k; i++){
            outrunning = outrunning.next;
        }
        ListNode leftNode = outrunning;
        while(outrunning != null){
            outrunning = outrunning.next;
            lagging = lagging.next;
        }
        ListNode rightNode = lagging;

        int tmp = leftNode.val;
        leftNode.val = rightNode.val;
        rightNode.val = tmp;

        return result.next;
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
            ListNode result = swapNodes(list, 1);
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
