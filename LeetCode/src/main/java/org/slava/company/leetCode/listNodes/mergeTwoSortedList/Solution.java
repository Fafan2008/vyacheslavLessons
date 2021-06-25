package org.slava.company.leetCode.listNodes.mergeTwoSortedList;


class Solution {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode resultList = new ListNode();
        ListNode head = resultList;
        while(l1 !=null && l2 != null){
            if(l1.val >= l2.val){
                resultList.next = l2;
                resultList = resultList.next;
                l2 = l2.next;
            }else{
                resultList.next = l1;
                resultList = resultList.next;
                l1 = l1.next;
            }
        }
        if (l1 == null)
            resultList.next = l2;
        else
            resultList.next = l1;
        return head.next;
    }

    public static void main(String[] args) {
        {
            ListNode leftList = new ListNode(1);
            leftList.next = new ListNode(2);
            leftList.next.next = new ListNode(4);

            ListNode rightList = new ListNode(1);
            rightList.next = new ListNode(3);
            rightList.next.next = new ListNode(4);

            ListNode newList = mergeTwoLists(leftList, rightList);
            while (newList != null){
                System.out.println(newList.val);
                newList = newList.next;
            }
            System.out.println("Word");
        }
        {
            ListNode leftList = null;
            ListNode rightList = null;

            ListNode newList = mergeTwoLists(leftList, rightList);
            while (newList != null){
                System.out.println(newList.val);
                newList = newList.next;
            }
            System.out.println("Word");
        }
        {
            ListNode leftList = new ListNode(0);
            ListNode rightList = null;

            ListNode newList = mergeTwoLists(leftList, rightList);
            while (newList != null){
                System.out.println(newList.val);
                newList = newList.next;
            }
            System.out.println("Word");
        }
        {
            ListNode leftList = null;
            ListNode rightList = new ListNode();

            ListNode newList = mergeTwoLists(leftList, rightList);
            while (newList != null){
                System.out.println(newList.val);
                newList = newList.next;
            }
            System.out.println("Word");
        }
        {
            ListNode leftList = new ListNode(1);
            leftList.next = new ListNode(1);
            leftList.next.next = new ListNode(5);

            ListNode rightList = new ListNode(1);
            rightList.next = new ListNode(1);
            rightList.next.next = new ListNode(1);

            ListNode newList = mergeTwoLists(leftList, rightList);
            while (newList != null){
                System.out.println(newList.val);
                newList = newList.next;
            }
            System.out.println("Word");
        }
    }
}
