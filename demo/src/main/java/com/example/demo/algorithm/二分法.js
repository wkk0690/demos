/**
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 * 提示：
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */

let res = searchRange([1, 1, 2, 3, 4], 3)
console.log(res);

function searchRange (nums, target) {
  function search(x) {
    let left = 0,
      right = nums.length;
    while (left < right) {
      const mid = (left + right) >> 1;
      if (nums[mid] >= x) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }
  const l = search(target);
  const r = search(target + 1);
  return l == r ? [-1, -1] : [l, r - 1];
}
