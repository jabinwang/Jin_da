package com.jabin.da.leetcode;


import com.sun.source.tree.Tree;

import javax.swing.*;
import java.util.*;

public class Solution {


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    //283--> 27 / 26 / 80
    public void moveZeroes(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }
        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    //75 --> 88 / 215
    public void sortColors(int[] nums) {

        int zero = -1;
        int two = nums.length;
        int i = 0;
        while (i < two) {
            if (nums[i] == 2) {
                swap(nums, --two, i);
            } else if (nums[i] == 0) {
                swap(nums, ++zero, i++);
            } else if (nums[i] == 1) {
                i++;
            }
        }
    }

    //167-> 125 345 11
    public int[] twoSum3(int[] numbers, int target) {
        int[] res = new int[2];
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                res[0] = l + 1;
                res[1] = r + 1;
            }
            if (numbers[l] + numbers[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        return res;
    }

    public int[] twoSum4(int[] numbers, int target) {

        for (int i = 0; i < numbers.length; i++) {
            int l = i + 1;
            int r = numbers.length - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > (target - numbers[i])) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }

        return new int[]{};
    }

    //209
    public int minSubArrayLen1(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    ans = Math.min(ans, j - i + 1);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public int minSubArrayLen2(int target, int[] nums) {
        int l = 0;
        int r = -1;
        int res = nums.length + 1;
        int sum = 0;
        while (l < nums.length) {
            if ((r + 1) < nums.length && sum < target) {
                sum += nums[++r];
            } else {
                sum -= nums[l++];
            }
            if (sum >= target) {
                res = Math.min(res, r - l + 1);
            }
        }
        return res == nums.length + 1 ? 0 : res;
    }

    //3 --> 438   76
    public int lengthOfLongestSubstring1(String s) {
        int l = 0;
        int r = -1;
        int res = 0;
        int[] freq = new int[256];
        while (l < s.length()) {
            if ((r + 1) < s.length() && freq[s.charAt(r + 1)] == 0) {
                freq[s.charAt(++r)]++;
            } else {
                freq[s.charAt(l++)]--;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    //454 --> 49
    public int fourSumCount2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer i :
                nums1) {
            for (Integer i2 :
                    nums2) {
                int tmp = i + i2;
                if (!map.containsKey(tmp)) {
                    map.put(tmp, 1);
                } else {
                    map.put(tmp, map.get(tmp) + 1);
                }
            }
        }
        for (Integer i :
                nums3) {
            for (Integer i2 :
                    nums4) {
                int tmp = -(i + i2);
                if (map.containsKey(tmp)) {
                    res += map.get(tmp);
                }
            }
        }
        return res;
    }

    //447 --> 149
    public int numberOfBoomerangs1(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (j != i) {
                    int d = dist1(points[i], points[1]);
                    if (!map.containsKey(d)) {
                        map.put(d, 1);
                    } else {
                        map.put(d, map.get(d) + 1);
                    }
                }
            }
            for (Integer key :
                    map.keySet()) {
                res += (map.get(key) * (map.get(key) - 1));
            }
        }
        return res;
    }

    private int dist1(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }

    //219 --> 217
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        Set<Integer> set = new HashSet<>(k);
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    //220
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.ceiling(nums[i] - t) != null &&
                    set.ceiling(nums[i] - t) <= (nums[i] + t)) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() == k + 1) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    //203 --> 82 21
    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode res = removeElements2(head.next, val);
        if (head.val == val) {
            head = res;
        } else {
            head.next = res;
        }
        return head;
    }

    public ListNode removeElements21(ListNode head, int val) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return dummyHead.next;
    }

    public ListNode removeElements22(ListNode head, int val) {

        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }

        return head;
    }

    //24 --> 25  147  148
    public ListNode swapPairs1(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while (pre.next != null && pre.next.next != null) {
            ListNode n1 = pre.next;
            ListNode n2 = pre.next.next;
            ListNode next = pre.next.next.next;
            n2.next = n1;
            n1.next = next;
            pre.next = n2;
            pre = n1;
        }
        return dummyHead.next;
    }

    //20 --> 150 71
    public boolean isValid1(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' ||
                    s.charAt(i) == '{' ||
                    s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            } else {
                if (stack.size() == 0) {
                    return false;
                }
                Character c = stack.pop();
                Character match = null;
                if (s.charAt(i) == ')') {
                    match = '(';
                } else if (s.charAt(i) == '}') {
                    match = '{';
                } else if (s.charAt(i) == ']') {
                    match = '[';
                }
                if (match != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    //94
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root != null) {
            TreeNode cur = root;
            while (cur != null || !stack.isEmpty()) {
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

    //144
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                res.add(node.val);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        return res;
    }

    //145
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode pre = null;
            TreeNode cur = root;
            while (cur != null || !stack.isEmpty()) {
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
                cur = stack.pop();
                if (cur.right == null || cur.right == pre) {
                    res.add(cur.val);
                    pre = cur;
                    cur = null;
                } else {
                    stack.push(cur);
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    //279 --> 127 126
    public int numSquares(int n) {


    }

    //112
    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum1(root.left, targetSum - root.val) ||
                hasPathSum1(root.right, targetSum - root.val);
    }

    //257 -->113 129
    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> res = new ArrayList<>();
        constructPath(root, "", res);
        return res;
    }

    private void constructPath(TreeNode root, String curPath, List<String> res) {
        if (root != null) {
            StringBuilder sb = new StringBuilder(curPath);
            sb.append(root.val);
            if (root.left == null && root.right == null) {
                res.add(sb.toString());
            } else {
                sb.append("->");
                constructPath(root.left, sb.toString(), res);
                constructPath(root.right, sb.toString(), res);
            }
        }
    }

    //437
    public int pathSum1(TreeNode root, int targetSum) {
        int res = 0;
        if (root == null) {
            return res;
        }
        if (root.val == targetSum) {
            res += 1;
        }
        pathSum1(root.left, targetSum - root.val);
        pathSum1(root.right, targetSum - root.val);
        return res;
    }

    public List<String> binaryTreePaths11(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();
        nodeQueue.offer(root);
        pathQueue.offer(String.valueOf(root.val));
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();
            if (node.left == null && node.right == null) {
                res.add(path);
            }
            if (node.left != null) {
                nodeQueue.offer(node.left);
                pathQueue.offer(new StringBuilder(path).append("->").append(node.left.val).toString());
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                pathQueue.offer(new StringBuilder(path).append("->").append(node.right.val).toString());
            }
        }
        return res;
    }

    //437
    public int pathSum2(TreeNode root, int targetSum) {
        int res = 0;
        if (root == null) {
            return res;
        }
        res += findPath1(root, targetSum);
        res += pathSum2(root.left, targetSum);
        res += pathSum2(root.right, targetSum);
        return res;
    }

    private int findPath1(TreeNode root, int target) {
        int res = 0;
        if (root == null) {
            return res;
        }
        if (root.val == target) {
            res += 1;
        }
        res += findPath1(root.left, target - root.val);
        res += findPath1(root.right, target - root.val);
        return res;
    }

    //450 -->98 108  230  236
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode successor = min(root.right);
            successor.left = root.left;
            successor.right = removeMin(root.right);
            return successor;
        }
    }

    private TreeNode min(TreeNode root) {
        if (root.left == null) {
            return root;
        }
        return min(root.left);
    }

    private TreeNode removeMin(TreeNode root) {
        if (root.left == null) {
            TreeNode rightNode = root.right;
            root.right = null;
            return rightNode;
        }
        root.left = removeMin(root.left);
        return root;
    }

    //17 -->93 131
    public List<String> letterCombinations1(String digits) {
        List<String> res = new ArrayList<>();
        findCombination(digits, 0, "", res);
        return res;
    }

    private static String LETTERS[] = {
            " ",   //0
            "",    //1
            "abc", //2
            "def", //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    private void findCombination(String digits, int index, String curStr, List<String> res) {

        if (index == digits.length()) {
            res.add(curStr);
            return;
        }
        Character c = digits.charAt(index);
        String str = LETTERS[c - '0'];
        for (int i = 0; i < str.length(); i++) {
            findCombination(digits, index + 1, curStr + str.charAt(i), res);
        }
    }


    //46--> 47
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        boolean[] used = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        genPermutation(nums, 0, path, used, res);
        return res;
    }

    private void genPermutation(int[] nums,
                                int index,
                                List<Integer> path,
                                boolean[] used,
                                List<List<Integer>> res) {

        if (index == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                genPermutation(nums, index + 1, path, used, res);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    //77 --> 39 40 216 78 90 401
    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> res = new ArrayList<>();
        if (k > n) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        find(n, k, 1, path, res);
        return res;
    }

    private void find(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {

        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i <= n; i++) {
            path.push(i);
            find(n, k, i + 1, path, res);
            path.pop();
        }
    }

    //79
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (searchDFS(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;

    }

    private int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private boolean isArea(char[][] board, int x, int y) {
        int m = board.length;
        int n = board[0].length;
        return (x >= 0 && x < m) && (y >= 0 && y < n);
    }

    private boolean searchDFS(char[][] board,
                              int startX,
                              int startY,
                              String word,
                              int index,
                              boolean[][] visited) {
        if (index == word.length() - 1) {
            return board[startX][startY] == word.charAt(index);
        }
        if (board[startX][startY] == word.charAt(index)) {
            visited[startX][startY] = true;
            for (int i = 0; i < 4; i++) {
                int newX = startX + d[i][0];
                int newY = startY + d[i][1];
                if (isArea(board, newX, newY) && !visited[newX][newY] &&
                        searchDFS(board, newX, newY, word, index + 1, visited)) {
                    return true;
                }
            }
            visited[startX][startY] = false;
        }
        return false;
    }

    //200 --> 130 417
    public int numIslands(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    res += 1;
                    findIslandsDFS(grid, i, j, visited);
                }
            }
        }
        return res;
    }

    private int[][] D = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private boolean isInArea(char[][] grid, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;
        return (x >= 0 && x < m) && (y >= 0 && y < n);
    }

    private void findIslandsDFS(char[][] grid, int startX, int startY, boolean[][] visited) {

        visited[startX][startY] = true;
        for (int i = 0; i < 4; i++) {
            int newX = startX + D[i][0];
            int newY = startY + D[i][1];
            if (isInArea(grid, newX, newY) && !visited[newX][newY] && grid[newX][newY] == '1') {

                findIslandsDFS(grid, newX, newY, visited);
            }
        }
    }

    //51 --> 37
    class Solution51 {
        private boolean[] cols;
        private boolean[] dia1;
        private boolean[] dia2;
        List<List<String>> res;

        public List<List<String>> solveNQueens(int n) {
            res = new ArrayList<>();
            cols = new boolean[n];
            dia1 = new boolean[2 * n - 1];
            dia2 = new boolean[2 * n - 1];
            List<Integer> rows = new ArrayList<>();
            putQueue(n, 0, rows);
            return res;
        }

        private void putQueue(int n, int index, List<Integer> rows) {
            if (index == n) {
                res.add(genBoard(rows));
                return;
            }

            for (int i = 0; i < n; i++) {
                if (!cols[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
                    rows.add(i);
                    cols[i] = true;
                    dia1[index + i] = true;
                    dia2[index - i + n - 1] = true;
                    putQueue(n, index + 1, rows);
                    cols[i] = false;
                    dia1[index + i] = false;
                    dia2[index - i + n - 1] = false;
                    rows.remove(rows.size() - 1);
                }
            }

        }

        private List<String> genBoard(List<Integer> rows) {
            List<String> bord = new ArrayList<>();
            for (int i = 0; i < rows.size(); i++) {
                char[] charArray = new char[rows.size()];
                Arrays.fill(charArray, '.');
                charArray[rows.get(i)] = 'Q';
                bord.add(new String(charArray));

            }
            return bord;
        }
    }

    //70  --> 120 64
    class Solution70 {

        private int[] memo;

        public int climbStairs(int n) {
            memo = new int[n + 1];
            Arrays.fill(memo, -1);
            return calcWays(n);
        }

        private int calcWays(int i) {
            if (i == 0 || i == 1) {
                return 1;
            }
            if (memo[i] == -1) {
                memo[i] = calcWays(i - 1) + calcWays(i - 2);
            }
            return memo[i];
        }

    }

    //343 -->  279  91  62  63
    class Solution343 {

        private int[] memo;

        public int integerBreak(int n) {
            memo = new int[n + 1];
            Arrays.fill(memo, -1);
            return breakInteger(n);
        }

        private int breakInteger(int n) {
            if (n == 1) {
                return 1;
            }

            if (memo[n] != -1) {
                return memo[n];
            }

            int res = -1;
            for (int i = 1; i <= n - 1; i++) {
                res = max3(res, i * (n - i), i * breakInteger(n - i));
            }
            memo[n] = res;
            return memo[n];
        }

        private int max3(int a, int b, int c) {
            return Math.max(a, Math.max(b, c));
        }

    }

    class Solution343_1 {

        private int[] memo;

        public int integerBreak(int n) {
            memo = new int[n + 1];
            Arrays.fill(memo, -1);
            memo[1] = 1;
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= i - 1; j++) {
                    memo[i] = max3(memo[i], j * (i - j), i * memo[i - j]);
                }
            }
            return memo[n];
        }

        private int max3(int a, int b, int c) {
            return Math.max(a, Math.max(b, c));
        }

    }

    //198 --> 213 337 309
    class Solution198 {
        int[] memo;

        public int rob(int[] nums) {
            memo = new int[nums.length];
            Arrays.fill(memo, -1);
            return tryRob(nums, 0);
        }

        private int tryRob(int[] nums, int index) {
            if (index >= nums.length) {
                return 0;
            }
            if (memo[index] != -1) {
                return memo[index];
            }
            int res = 0;
            for (int i = index; i < nums.length; i++) {
                res = Math.max(res, nums[i] + tryRob(nums, i + 2));
                memo[index] = res;
            }
            return res;
        }
    }

    class Solution198_1 {

        public int rob(int[] nums) {
            int[] memo = new int[nums.length];
            Arrays.fill(memo, -1);
            int n = nums.length;
            memo[0] = nums[0];
            for (int i = 1; i <= n - 1; i++) {
                for (int j = i; j >= 0; j--) {
                    memo[i] = Math.max(memo[i], nums[j] + (j - 2 >= 0 ? memo[j - 2] : 0));
                }
            }
            return memo[n - 1];
        }

    }

    //01 背包
    class Solution01 {
        private int[][] memo;

        public int knapsack01(int[] w, int[] v, int C) {
            int n = w.length;
            memo = new int[n][C + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < C + 1; j++) {
                    memo[i][j] = -1;
                }
            }
            return bestValue(w, v, n - 1, C);
        }

        //用[0...index]的物品来填充
        private int bestValue(int[] w, int[] v, int index, int c) {
            if (index < 0 || c <= 0) {
                return 0;
            }
            if (memo[index][c] != -1) {
                return memo[index][c];
            }
            int res = bestValue(w, v, index - 1, c);
            if (w[index] <= c) {
                res = Math.max(res, bestValue(w, v, index, c - w[index]));
            }
            memo[index][c] = res;
            return res;
        }

    }

    class Solution01DP {
        private int[][] memo;

        public int knapsack01(int[] w, int[] v, int C) {
            int n = w.length;
            memo = new int[n][C + 1];
            for (int i = 0; i < C; i++) {
                memo[0][i] = (i > w[0] ? v[0] : 0);
            }

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < C + 1; j++) {
                    memo[i][j] = memo[i - 1][j];
                    if (w[i] <= j) {
                        memo[i][j] = Math.max(memo[i][j], v[i] + memo[i - 1][j - w[i]]);
                    }
                }
            }
            return memo[n - 1][C];
        }
    }

    //416 --> 322 377 474 139 494
    class Solution416 {
        private int[][] memo;

        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            if (sum % 2 != 0) {
                return false;
            }
            memo = new int[nums.length][sum / 2 + 1];
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j <= sum / 2; j++) {
                    memo[i][j] = -1;
                }
            }
            return tryPartition(nums, nums.length - 1, sum / 2);
        }

        private boolean tryPartition(int[] nums, int index, int c) {
            if (c == 0) {
                return true;
            }
            if (index < 0 || c < 0) {
                return false;
            }
            if (memo[index][c] != -1) {
                return memo[index][c] == 1;
            }
            boolean res =
                    tryPartition(nums, index - 1, c)
                            || tryPartition(nums, index - 1, c - nums[index]);
            memo[index][c] = res ? 1 : 0;
            return res;
        }
    }

    class Solution416DP {

        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            if (sum % 2 != 0) {
                return false;
            }
            int C = sum / 2;
            boolean[] memo = new boolean[C + 1];
            Arrays.fill(memo, false);
            for (int i = 0; i <= C; i++) {
                memo[i] = (nums[0] == i);
            }
            for (int i = 1; i < nums.length; i++) {
                for (int j = C; j >= nums[i]; j--) {
                    memo[j] = memo[j] || memo[j - nums[i]];
                }
            }
            return memo[C];
        }
    }


    //300 --> 376
    class Solution300DP {
        public int lengthOfLIS(int[] nums) {
            int n = nums.length;
            int[] memo = new int[n];
            Arrays.fill(memo, 1);
            for (int i = 1; i < n; i++) {
                for (int j = i; j >= 0; j--) {
                    if (nums[i] > nums[j]) {
                        memo[i] = Math.max(memo[i], 1 + memo[j]);
                    }
                }
            }

            int res = 1;
            for (int i = 0; i < n; i++) {
                res = Math.max(res, memo[i]);
            }
            return res;
        }
    }

    class Solution300 {

        private int[] memo;
        public int lengthOfLIS(int[] nums) {
            int n = nums.length;
            memo = new int[n];
            Arrays.fill(memo, -1);
            int res = 1;
            for (int i = 0; i < n; i++) {
                res = Math.max(res, getMaxLength(nums, i));
            }
            return res;
        }

        private int getMaxLength(int[] nums, int index) {

            if (memo[index] != -1) {
                return memo[index];
            }
            int res = 1;
            for (int i = 0; i < index; i++) {
                if (nums[index] > nums[i]) {
                    res = Math.max(res, 1 + getMaxLength(nums, i));
                }
            }

            return memo[index] = res;
        }
    }

    //283
    public ListNode removeElements(ListNode head, int val) {


        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = delNode.next;
            delNode.next = null;
        }

        if (head == null) {
            return null;
        }


        ListNode prev = head;
        while (prev.next != null) {
            ListNode delNode = prev.next;
            if (delNode.val == val) {
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }

        return head;

    }

    public ListNode removeElements1(ListNode head, int val) {


        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return head;
    }

    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode res = removeElements2(head.next, val);
        if (head.val == val) {
            return res;
        } else {
            head.next = res;
            return head;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //167
    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;

        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l + 1, r + 1};
            } else if (numbers[l] + numbers[r] < target) {
                l++;
            } else {
                r--;
            }
        }

        return null;
    }

    public int[] twoSum2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int j = binarySearch(numbers, i + 1, numbers.length - 1, target - numbers[i]);
            if (j != -1) {
                return new int[]{i + 1, j + 1};
            }
        }
        return null;
    }

    private int binarySearch(int[] nums, int l, int r, int target) {
        int mid = l + (r - l) / 2;
        while (l <= r) {
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    //209
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = -1;
        int sum = 0;
        int res = nums.length + 1;
        while (l < nums.length) {
            if (r + 1 < nums.length && sum < target) {
                sum += nums[++r];
            } else {
                sum -= nums[l++];
            }
            if (sum >= target) {
                res = Math.min(res, r - l + 1);
            }
        }

        if (res == (nums.length + 1)) {
            return 0;
        }
        return res;

    }

    //3
    public int lengthOfLongestSubstring(String s) {
        int[] freq = new int[256];
        int l = 0, r = -1;
        int res = 0;
        while (l < s.length()) {
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                freq[s.charAt(++r)]++;
            } else {
                freq[s.charAt(l++)]--;
            }
            res = Math.max(res, r - l + 1);

        }
        return res;
    }

    //349
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (int num :
                nums1) {
            set1.add(num);
        }
        HashSet<Integer> resultSet = new HashSet<>();
        for (int num :
                nums2) {
            if (set1.contains(num)) {
                resultSet.add(num);
            }
        }

        int[] res = new int[resultSet.size()];
        Iterator<Integer> iterator = resultSet.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            res[index++] = iterator.next();
        }
        return res;
    }

    //350
    public int[] intersect(int[] nums1, int[] nums2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num :
                nums1) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int num :
                nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                arrayList.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        int[] res = new int[map.size()];
        int index = 0;
        for (Integer num :
                arrayList) {
            res[index++] = num;
        }
        return res;

    }

    //1
    public int[] twoSum1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another)) {
                int[] res = {i, map.get(another)};
                return res;
            }
            map.put(nums[i], i);
        }
        return null;
    }

    //454
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int temp = nums1[i] + nums2[j];
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1);
                } else {
                    map.put(temp, 1);
                }
            }
        }

        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                if (map.containsKey(-nums3[i] - nums4[j])) {
                    res += map.get(-nums3[i] - nums4[j]);
                }
            }
        }
        return res;
    }

    //447
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (j != i) {
                    int d = dist(points[i], points[j]);
                    if (map.containsKey(d)) {
                        map.put(d, map.get(d) + 1);
                    } else {
                        map.put(d, 1);
                    }
                }
            }
            for (Integer dist :
                    map.keySet()) {
                res += map.get(dist) * (map.get(dist) - 1);
            }
        }
        return res;
    }

    private int dist(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }


    //219
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() == k + 1) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    //220
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.ceiling((long) nums[i] - (long) t) != null &&
                    set.ceiling((long) nums[i] - (long) t) <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (set.size() == k + 1) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    //206

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //24

    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while (pre.next != null && pre.next.next != null) {
            ListNode n1 = pre.next;
            ListNode n2 = n1.next;
            ListNode next = n2.next;
            //swap
            n2.next = n1;
            n1.next = next;
            pre.next = n2;
            pre = n1;
        }
        return dummyHead.next;
    }

    //19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode p1 = dummyHead, p2 = dummyHead;
        for (int i = 0; i < n; i++) {
            p2 = p2.next;
        }
        while (p2.next != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        p1.next = p1.next.next;
        return dummyHead.next;
    }

    //20
    public boolean isValid(String s) {
        ArrayList<Character> stack = new ArrayList<>();
        if (s.length() == 1) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' ||
                    s.charAt(i) == '{' ||
                    s.charAt(i) == '[') {
                stack.add(s.charAt(i));
            } else {
                if (stack.size() == 0) {
                    return false;
                }
                Character c = stack.remove(stack.size() - 1);
                Character match = null;
                if (s.charAt(i) == ')') {
                    match = '(';
                } else if (s.charAt(i) == '}') {
                    match = '{';
                } else if (s.charAt(i) == ']') {
                    match = '[';
                }
                if (c != match) {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    //94

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> array = new ArrayList<>();

        if (root == null) {
            return array;
        }
        ArrayList<TreeNode> stack = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.remove(stack.size() - 1);
            array.add(cur.val);
            cur = cur.right;
        }
        return array;
    }

    //144
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> array = new ArrayList<>();
        if (root == null) {
            return array;
        }
        ArrayList<TreeNode> stack = new ArrayList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.remove(stack.size() - 1);
            array.add(cur.val);
            if (cur.right != null) {
                stack.add(cur.right);
            }
            if (cur.left != null) {
                stack.add(cur.left);
            }
        }

        return array;
    }


    //145
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> array = new ArrayList<>();
        if (root == null) {
            return array;
        }
        ArrayList<TreeNode> stack = new ArrayList<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.remove(stack.size() - 1);
            if (cur.right != null && pre != cur.right) {
                stack.add(cur);
                cur = cur.right;
            } else {
                pre = cur;
                array.add(cur.val);
                cur = null;
            }
        }
        return array;
    }

    private class Command {
        String s;
        TreeNode node;

        Command(String s, TreeNode node) {
            this.s = s;
            this.node = node;
        }
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> array = new ArrayList<>();
        if (root == null) {
            return array;
        }
        ArrayList<Command> stack = new ArrayList<>();
        stack.add(new Command("go", root));
        while (!stack.isEmpty()) {
            Command command = stack.remove(stack.size() - 1);
            if (command.s.equals("print")) {
                array.add(command.node.val);
            } else {
                stack.add(new Command("print", command.node));
                if (command.node.right != null) {
                    stack.add(new Command("go", command.node.right));
                }
                if (command.node.left != null) {
                    stack.add(new Command("go", command.node.left));
                }
            }
        }
        return array;
    }

    //102

    private class Pair {
        TreeNode node;
        int level;

        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> array = new ArrayList<>();
        if (root == null) {
            return array;
        }
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            TreeNode node = cur.node;
            int level = cur.level;
            if (array.size() == level) {
                ArrayList<Integer> list = new ArrayList<>();
                array.add(list);
            }
            array.get(level).add(node.val);
            if (node.left != null) {
                queue.offer(new Pair(node.left, level + 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, level + 1));
            }
        }
        return array;
    }

    //279
    public int numSquares(int n) {

        return 0;
    }

    //347

    //104　
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    //226
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    //112
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    //257
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> resStr = new ArrayList<>();
        if (root == null) {
            return resStr;
        }

        if (root.left == null && root.right == null) {
            resStr.add(String.valueOf(root.val));
            return resStr;
        }
        List<String> leftPath = binaryTreePaths(root.left);
        for (int i = 0; i < leftPath.size(); i++) {
            resStr.add(root.val + "->" + leftPath.get(i));
        }
        List<String> rightPath = binaryTreePaths(root.right);
        for (int i = 0; i < rightPath.size(); i++) {
            resStr.add(root.val + "->" + rightPath.get(i));
        }

        return resStr;
    }

    //437
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        return findPath(root, targetSum) + pathSum(root.left, targetSum)
                + pathSum(root.right, targetSum);

    }

    private int findPath(TreeNode root, int targetSum) {
        if (root == null)
            return 0;
        int res = 0;

        if (root.val == targetSum) {
            res += 1;
        }
        res += findPath(root.left, targetSum - root.val);
        res += findPath(root.right, targetSum - root.val);
        return res;
    }

    //235
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }


    //17
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.equals("")) {
            return res;
        }
        findCombinations(digits, 0, "", res);
        return res;
    }

    private String[] letterMap = {
            " ",
            "",
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    public void findCombinations(String digits, int index, String s, List<String> res) {

    }

    //46

    public List<List<Integer>> permute(int[] nums) {

    }

    //77
    public List<List<Integer>> combine(int n, int k) {

    }

    //79
    public boolean exist(char[][] board, String word) {

    }

    //200
    public int numIslands(char[][] grid) {

    }


    //51
    public List<List<String>> solveNQueens(int n) {

    }

    //70

    public int climbStairs(int n) {
        int[] mem = new int[n + 1];
        Arrays.fill(mem, -1);
        return climbWay(n, mem);
    }

    private int climbWay(int n, int[] mem) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (mem[n] == -1) {
            mem[n] = climbWay(n - 1, mem) + climbWay(n - 2, mem);
        }
        return mem[n];
    }

    public int climbStairs2(int n) {
        int[] mem = new int[n + 1];
        mem[0] = 1;
        mem[1] = 1;
        for (int i = 2; i <= n; i++) {
            mem[i] = mem[i - 1] + mem[i - 2];
        }
        return mem[n];
    }

    //343
    public int integerBreak(int n) {
        int[] mem = new int[n + 1];
        Arrays.fill(mem, -1);
        return intBreak(n, mem);
    }

    private int intBreak(int n, int[] mem) {
        if (n == 1) {
            return 1;
        }
        int res = -1;
        if (mem[n] != -1) {
            return mem[n];
        }
        for (int i = 1; i <= n - 1; i++) {
            res = max3(res, i * (n - i), i * intBreak(n - i, mem));
        }
        mem[n] = res;
        return res;
    }

    private int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public int integerBreak2(int n) {
        int[] mem = new int[n + 1];
        Arrays.fill(mem, -1);
        mem[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                mem[i] = max3(mem[i], j * (i - j), j * mem[i - j]);
            }
        }
        return mem[n];
    }

    //198
    public int rob(int[] nums) {

    }

    //416
    public boolean canPartition(int[] nums) {

    }

    //743
    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], INF);
        }
        for (int[] t :
                times) {
            int x = t[0] - 1;
            int y = t[1] - 1;
            g[x][y] = t[2];
        }
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            int x = -1;
            for (int y = 0; y < n; y++) {
                if (!visited[y] && (x == -1 || dist[y] < dist[x])) {
                    x = y;
                }
            }
            visited[x] = true;
            for (int y = 0; y < n; y++) {
                dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
            }
        }
        int res = Arrays.stream(dist).max().getAsInt();
        return res == INF ? -1 : res;
    }

    public int networkDelayTime1(int[][] times, int n, int k) {
        int INF = Integer.MAX_VALUE / 2;
        List<int[]>[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] t :
                times) {
            int x = t[0] - 1;
            int y = t[1] - 1;
            g[x].add(new int[]{y, t[2]});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        q.offer(new int[]{0, k - 1});
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int time = tmp[0];
            int x = tmp[1];
            if (dist[x] < time) {
                continue;
            }
            for (int[] e :
                    g[x]) {
                int y = e[0];
                int t = e[1];
                if (dist[x] + t < dist[y]) {
                    dist[y] = dist[x] + t;
                    q.offer(new int[]{dist[y], y});
                }
            }
        }
        int res = Arrays.stream(dist).max().getAsInt();
        return res == INF ? -1 : res;

    }

    //1514
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<List<ProbNode>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            g.get(edge[0]).add(new ProbNode(succProb[i], edge[1]));
            g.get(edge[1]).add(new ProbNode(succProb[i], edge[0]));
        }

        double[] probs = new double[n];
        probs[start] = 1;
        PriorityQueue<ProbNode> q = new PriorityQueue<>();
        q.offer(new ProbNode(1, start));
        while (!q.isEmpty()) {
            ProbNode node = q.poll();
            double pb = node.prob;
            int v = node.v;
            if (probs[v] > pb) {
                continue;
            }
            for (ProbNode probNode :
                    g.get(v)) {
                if (probs[probNode.v] < probNode.prob * probs[v]) {
                    probs[probNode.v] = probNode.prob * probs[v];
                    q.offer(new ProbNode(probs[probNode.v], probNode.v));
                }
            }
        }
        return probs[end];
    }

    private static class ProbNode implements Comparable<ProbNode> {
        private double prob;
        private int v;

        public ProbNode(double prob, int v) {
            this.prob = prob;
            this.v = v;
        }

        @Override
        public int compareTo(ProbNode o) {
            if (this.prob == o.prob) {
                return this.v - o.v;
            } else {
                return this.prob - o.prob > 0 ? -1 : 1;
            }
        }
    }
}

