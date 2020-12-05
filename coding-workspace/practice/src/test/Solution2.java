package test;

/**
 * @author yangwei
 * @date 2020-07-20 12:47
 */
public class Solution2 {
    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
//        int num = searchInsert(nums, 7);
//        int num = searchInsert(nums, 2);
        int num = searchInsert(nums, 0);
        System.out.println(num);
    }

    private static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
