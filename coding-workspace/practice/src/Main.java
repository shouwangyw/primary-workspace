/**
 * @author yangwei
 * @date 2020-04-30 18:32
 */
public class Main {
    public static void main(String[] args) {
//        System.out.println(new Solution().getNewArr(new int[]{1, 1, 1, 1, 1, 2, 2, 3}));
        System.out.println(new Solution().getNewArr(new int[]{1, 1, 1, 1, 2, 2, 3, 3}));
//        System.out.println(new Solution().getNewArr(new int[]{1, 1, 2, 2, 3, 3, 4, 4}));
    }
}

class Solution {
    public int getNewArr(int[] nums) {
        int length = nums.length;
        if (length <= 2) {
            return length;
        }

        for (int i = 0; i < length - 2; i++) {
            for (int j = 0; j < length - i - 2; j++) {
                if (nums[j] == nums[j + 2]) {
                    if (length == 3) {
                        return 2;
                    }

                    if (nums[j] > nums[j + 1]) {
                        swap(nums, j + 1, j + 2);
                    } else {
                        nums[j + 2] = nums[j + 2] - 1;
                    }
                } else {
                    if (nums[j] > nums[j + 1]) {
                        swap(nums, j + 1, j + 2);
                    }
                }
            }
            print(nums);
        }

        for (int i = 0; i < length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i + 1;
            }
        }

        return length;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private void print(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
