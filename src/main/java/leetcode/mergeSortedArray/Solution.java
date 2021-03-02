package leetcode.mergeSortedArray;

class Solution {
    public void merge(int[] arr1, int end1, int[] arr2, int end2) {
        end1--;
        end2--;
        int carriage = arr1.length - 1;
        while (carriage >= 0) {
            if (end1>=0 && end2 >=0){
                arr1[carriage--] = arr1[end1] >= arr2[end2] ? arr1[end1--] : arr2[end2--];
            }
            else{
                arr1[carriage--] = end1 >= 0 ? arr1[end1--] : arr2[end2--];
            }
        }
    }
}
