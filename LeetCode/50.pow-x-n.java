/*
 * @lc app=leetcode id=50 lang=java
 *
 * [50] Pow(x, n)
 *
 * https://leetcode.com/problems/powx-n/description/
 *
 * algorithms
 * Medium (33.93%)
 * Likes:    8589
 * Dislikes: 8433
 * Total Accepted:    1.4M
 * Total Submissions: 4M
 * Testcase Example:  '2.00000\n10'
 *
 * Implement pow(x, n), which calculates x raised to the power n (i.e.,
 * x^n).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2^-2 = 1/2^2 = 1/4 = 0.25
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * n is an integer.
 * Either x is not zero or n > 0.
 * -10^4 <= x^n <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1.0;

        // logic
        double result = myPow(x, n/2);

        if (n % 2 == 0) {
            return result * result;
        } else {
            if (n > 0) {
                return result * result * x;
            } else {
                return result * result * (1 / x);
            }
        }
    }
}
// @lc code=end