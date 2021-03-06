package org.slava.company.leetCode.arrays.plusOne;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class Solution2Test {

    @Test
    public void plusOne0() {
        Solution2 solution = new Solution2();
        int[] digits = {0};
        assertArrayEquals(new int[]{1}, solution.plusOne(digits));
    }

    @Test
    public void plusOne9() {
        Solution2 solution = new Solution2();
        int[] digits = {9};
        assertArrayEquals(new int[]{1, 0}, solution.plusOne(digits));
    }

    @Test
    public void plusOne123() {
        Solution2 solution = new Solution2();
        int[] digits = {1,2,3};
        assertArrayEquals(new int[]{1, 2, 4}, solution.plusOne(digits));
    }

    @Test
    public void plusOne129() {
        Solution2 solution = new Solution2();
        int[] digits = {1,2,9};
        assertArrayEquals(new int[]{1, 3, 0}, solution.plusOne(digits));
    }

    @Test
    public void plusOne999() {
        Solution2 solution = new Solution2();
        int[] digits = {9,9,9};
        assertArrayEquals(new int[]{1, 0, 0, 0}, solution.plusOne(digits));
    }
}