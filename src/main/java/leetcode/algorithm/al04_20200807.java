package leetcode.algorithm;

import java.text.DecimalFormat;

/**
 * @author：Cheng.
 * @since： 20200807
 */
public class al04_20200807 {
    public static void main(String[] args) {
        Solution04 solution04 = new Solution04();
        int[] nums1 = new int[] {1,2};
        int[] nums2 = new int[] {2};
        double middle = solution04.findMedianSortedArrays(nums1, nums2);
        System.out.println("middle = " + middle);
    }
}

/** 寻找两个正序数组的中位数 1.将两个数组进行组合 2.将整合数组进行排序 3.找出中位数 */
class Solution04 {
    public double findMedianSortedArrays(int[] arrayA, int[] arrayB) {
        int m = arrayA.length;
        int n = arrayB.length;
        //首先使较短的数组放在前面，以防确定了i之后，再确定j的时候，会发生数组越界
        if(m>n){
            int[] temp = arrayA;
            arrayA = arrayB;
            arrayB = temp;

            int tempVlaue = m;
            m = n;
            n = tempVlaue;
        }

        //i的起始边界
        int start = 0, end = m;
        //为了让大数组分界线左右两边元素相等，大数组的分界线为mid, 且mid = i+j,i为数组A的分界线，j为数组B的分界线
        int mid =(m+n+1)/2;
        while (start<= end){
            //首先通过二分法确定i的合适位置
            int i = (start+end)/2;
            int j = mid-i;
            //正确的应该是arrayA[i]>arrayB[j-1] arrayB[j]>arrayA[i-1]
            if(i<end && arrayA[i]<arrayB[j-1]){
                //i偏小，i应该右移
                start = i+1;
            }else if(i>start && arrayA[i-1]>arrayB[j]){
                //i偏大，应该左移
                end = i-1;
            }else{
                //i取值合适了
                int maxLeft;
                if(i==0){
                    //arrayA中的元素都大于arrayB中的元素
                    maxLeft = arrayB[j-1];
                }else if(j==0){
                    //arrayA中的元素都小于arrayB中的元素
                    maxLeft = arrayA[i-1];
                }else{
                    maxLeft = Math.max(arrayA[i-1],arrayB[j-1]);
                }

                if((m+n)%2==1){
                    //大数组的长度为奇数，则中位数即为maxLeft
                    return maxLeft;
                }else{
                    //大数组长度为偶数，中位数为 （maxLeft+minRight）/2
                    int minRight;

                    if(i==m){
                        //arrayA中的元素都小于arrayB中的元素
                        minRight = arrayB[j];
                    }else if(j==n){
                        //arrayB中的元素都小于arrayA中的元素
                        minRight = arrayA[i];
                    }else{
                        minRight = Math.min(arrayA[i],arrayB[j]);
                    }
                    return (maxLeft+minRight)/2.0;
                }
            }

        }
        return 0.0;
    }
}
