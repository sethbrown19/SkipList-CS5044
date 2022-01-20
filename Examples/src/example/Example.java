package example;

public class Example {

    public static void main(String[] args) {
        int[] test = { 1, 4, 8 };
        int target = 10;
        int start = 0;

        System.out.println(subsetSum(start, test, target));
    }


    public static boolean subsetSum(int start, int[] nums, int target) {
        if (target == 0) {
            return true;
        }
        if(target < 0) {
            return false;
        }
        
        if(nums.length == start && target != 0) {
            return false;
        }
        return subsetSum(start + 1, nums, target) || subsetSum(start + 1, nums,
                target - nums[start]);
        }
       

}
