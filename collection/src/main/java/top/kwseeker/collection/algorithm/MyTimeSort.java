package top.kwseeker.collection.algorithm;

import java.util.Arrays;

public class MyTimeSort {

    //每次都分成两组分别进行排序
    public static int[] sort(int[] src) {
        int srcLen = src.length;
        if(src.length > 2) {
            //拆分逻辑
            int halfLen = srcLen / 2;
            int[] leftAfterSortArray = sort(Arrays.copyOf(src, halfLen));
            int[] rightAfterSortArray = sort(Arrays.copyOfRange(src, halfLen, srcLen));
            //排序
            return doSort(leftAfterSortArray, rightAfterSortArray);
        } else {
            //比较逻辑
            if (src.length == 2) {
                if(src[0] > src[1]) {
                    int temp = src[0];
                    src[0] = src[1];
                    src[1] = temp;
                }
            }
            return src;
        }
    }

    private static int[] doSort(int[] left, int[] right) {
        int len = left.length + right.length;
        int[] ret = new int[len];
        int leftCursorIndex = 0, rightCursorIndex = 0, index = 0;
        for(; leftCursorIndex < left.length && rightCursorIndex < right.length ;) {
            if(left[leftCursorIndex] > right[rightCursorIndex]) {
                ret[index++] = right[rightCursorIndex];
                rightCursorIndex ++;
            } else {
                ret[index++] = left[leftCursorIndex];
                leftCursorIndex ++;
            }
        }
        while (leftCursorIndex < left.length) {
            ret[index++] = left[leftCursorIndex++];
        }
        while (rightCursorIndex < right.length) {
            ret[index++] = right[rightCursorIndex++];
        }
        return ret;
    }
}
