package org.slava.company.leetCode.listNodes.addTwoNumbers;

public class Solution {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode ();
        ListNode head = result;
        int reminder = 0;
        while(l1!=null || l2!=null || reminder !=0){
            if(l1 != null){
                reminder += l1.val;
                l1 = l1.next;
            }

            if(l2 != null)  {
                reminder += l2.val;
                l2 = l2.next;
            }
            head.next = new ListNode(reminder%10);
            reminder /= 10;
            head = head.next;
        }
        return result.next;
    }

    public static void main(String[] args) {
        int i = 1;
        {
            ListNode leftList = new ListNode(1);
            leftList.next = new ListNode(2);
            leftList.next.next = new ListNode(4);

            ListNode rightList = new ListNode(1);
            rightList.next = new ListNode(3);
            rightList.next.next = new ListNode(4);

            ListNode newList = addTwoNumbers(leftList, rightList);
            System.out.println("Result of test " + i);
            while (newList != null){
                System.out.print(newList.val + ", ");
                newList = newList.next;
            }
        }
    }
}
