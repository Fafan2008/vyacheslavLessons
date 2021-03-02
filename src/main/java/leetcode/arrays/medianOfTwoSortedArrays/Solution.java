package leetcode.arrays.medianOfTwoSortedArrays;

public class Solution {
    // Мой алгоритм можно сократить до середины. Учитывая что необходимо только центральное значение.
    // Так же эта задача решается математически за log(n), но я не смог разобраться https://medium.com/@hazemu/finding-the-median-of-2-sorted-arrays-in-logarithmic-time-1d3f2ecbeb46

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length + nums2.length == 1)
            return nums1.length == 1 ? nums1[0] : nums2[0];
        int[] commonArr = new int[nums1.length + nums2.length];
        int i = 0;
        int in1 = 0;
        int in2 = 0;
        while(in1 < nums1.length && in2 < nums2.length){
            if(nums1[in1] <= nums2[in2]){
                commonArr[i] = nums1[in1];
                in1++;
            }else{
                commonArr[i] = nums2[in2];
                in2++;
            }
            i++;
        }
        if(in1 != nums1.length)
            System.arraycopy(nums1, in1, commonArr, i, nums1.length - in1);

        if(in2 != nums2.length)
            System.arraycopy(nums2, in2, commonArr, i, nums2.length - in2);

        int mid = commonArr.length/2;
        if(commonArr.length%2 == 1)
            return commonArr[mid];
        else
            return (double)(commonArr[mid] + commonArr[mid-1])/2;
    }

    public static void main(String[] args) {
        int i = 1;
        {
            int[] arr1 = new int[]{1,2,3,4,5,6,7};
            int[] arr2 = new int[]{1,2,3,4,5,6,7};
            double result = findMedianSortedArrays(arr1, arr2);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            ++i;
        }
        {
            int[] arr1 = new int[]{1};
            int[] arr2 = new int[]{1};
            double result = findMedianSortedArrays(arr1, arr2);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            ++i;
        }
        {
            int[] arr1 = new int[]{1,2};
            int[] arr2 = new int[]{1};
            double result = findMedianSortedArrays(arr1, arr2);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            ++i;
        }
        {
            int[] arr1 = new int[]{};
            int[] arr2 = new int[]{2};
            double result = findMedianSortedArrays(arr1, arr2);
            System.out.print("Test " + i + " return: ");
            System.out.println(result);
            ++i;
        }
    }
}
