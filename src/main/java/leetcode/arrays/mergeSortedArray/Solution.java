package leetcode.arrays.mergeSortedArray;

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int r = m + n;
        int resultArray[] = new int[r];
        int j = 0;
        int k = 0;
        if (n == 0)
            return;
        for (int i = 0; i < r; i++) {
            if (k >= n && j >= m)
                break;
            if (k >= n) {
                resultArray[i] = nums1[j];
                j++;
                continue;
            }
            if (j >= m) {
                resultArray[i] = nums2[k];
                k++;
                continue;
            }
            if (nums1[j] <= nums2[k]) {
                resultArray[i] = nums1[j];
                j++;
            } else {
                resultArray[i] = nums2[k];
                k++;
            }
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = resultArray[i];
        }
    }
}
