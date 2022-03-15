package com.jabin.da.offer;


import com.sun.source.tree.Tree;

import javax.swing.*;
import java.security.Principal;
import java.util.*;

public class Solution {

    //03
    class Solution03 {
        public int findRepeatNumber(int[] nums) {
            HashSet<Integer> set = new HashSet<>();
            for (Integer i :
                    nums) {
                if (!set.contains(i)) {
                    set.add(i);
                } else {
                    return i;
                }
            }
            return -1;
        }
    }

    //04
    class Solution04 {
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            int m = matrix.length;
            int n = matrix[0].length;
            int row = 0;
            int col = n - 1;
            while (row < m && col >= 0) {
                if (matrix[row][col] == target) {
                    return true;
                } else if (matrix[row][col] < target) {
                    row++;
                } else {
                    col--;
                }
            }
            return false;
        }
    }

    //05
    class Solution05 {
        public String replaceSpace(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }
            int length = s.length();
            char[] chars = new char[3 * length];
            int index = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    chars[index++] = '%';
                    chars[index++] = '2';
                    chars[index++] = '0';
                } else {
                    chars[index++] = s.charAt(i);
                }
            }

            return new String(chars, 0, index);
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    //06
    class Solution06 {
        public int[] reversePrint(ListNode head) {
            Deque<Integer> stack = new ArrayDeque<>();
            if (head == null) {
                return new int[]{};
            }
            ListNode cur = head;
            while (cur != null) {
                stack.push(cur.val);
                cur = cur.next;
            }
            int[] res = new int[stack.size()];
            int index = 0;
            while (!stack.isEmpty()) {
                res[index++] = stack.pop();
            }
            return res;
        }
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //07
    class Solution07 {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0 || inorder == null ||
                    inorder.length != preorder.length) {
                return null;
            }
            return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        private TreeNode buildTree(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
            if (ps > pe) {
                return null;
            }
            int root = preorder[ps];
            int leftSize = -1;
            for (int i = is; i <= ie; i++) {
                if (inorder[i] == root) {
                    leftSize = i - is;
                    break;
                }
            }

            TreeNode node = new TreeNode(root);
            node.left = buildTree(preorder, ps + 1, ps + leftSize, inorder, is, is + leftSize - 1);
            node.right = buildTree(preorder, ps + leftSize + 1, pe, inorder, is + leftSize + 1, ie);
            return node;
        }
    }


    //09
    static class CQueue {
        private Stack<Integer> stack1 = new Stack<>();
        private Stack<Integer> stack2 = new Stack<>();

        public CQueue() {

        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.isEmpty() ? -1 : stack2.pop();
        }
    }

    //10
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }


    public int fib1(int n) {

        int[] res = new int[n + 1];
        Arrays.fill(res, -1);
        return fib(n, res);
    }

    private int fib(int i, int[] res) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        if (res[i] == -1) {
            res[i] = (fib(i - 1, res) + fib(i - 2, res)) % 1000000007;
        }
        return res[i];
    }

    //10
    public int numWays(int n) {
        int[] res = new int[n + 1];
        Arrays.fill(res, -1);
        return climbWay(n, res);
    }

    private int climbWay(int i, int[] res) {
        if (i == 0 || i == 1) {
            return 1;
        }
        if (res[i] == -1) {
            res[i] = (climbWay(i - 1, res) + climbWay(i - 2, res)) % 1000000007;
        }
        return res[i];
    }

    //11
    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return -1;
        }
        int l = 0;
        int r = numbers.length - 1;
        while (l <= r) {
            int mid = l + (r - 1) / 2;
            if (numbers[mid] > numbers[r]) {
                l = mid + 1;
            } else if (numbers[mid] < numbers[r]) {
                r = mid;
            } else {
                r--;
            }

        }
        return numbers[l];
    }

    //12
    class Solution12 {
        private int[][] D = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        private int m;
        private int n;
        private boolean[][] visited;

        private boolean isArea(int x, int y) {
            return (x >= 0 && x < m) && (y >= 0 && y < n);
        }

        public boolean exist(char[][] board, String word) {
            m = board.length;
            n = board[0].length;
            visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (searchWordDFS(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        //word[0...i]
        private boolean searchWordDFS(char[][] board, int startX, int startY, String word, int index) {

            if (index == word.length() - 1) {
                return board[startX][startY] == word.charAt(index);
            }
            if (board[startX][startY] == word.charAt(index)) {
                visited[startX][startY] = true;
                for (int i = 0; i < 4; i++) {
                    int newX = startX + D[i][0];
                    int newY = startY + D[i][1];
                    if (board[startX][startY] == word.charAt(index) && isArea(newX, newY) && !visited[newX][newY]) {
                        if (searchWordDFS(board, newX, newY, word, index + 1)) {
                            return true;
                        }
                    }
                }
                visited[startX][startY] = false;
            }
            return false;
        }
    }

    //13
    class Solution13 {
        private int[][] D = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        private boolean[][] visited;
        private int res = 0;

        public int movingCount(int m, int n, int k) {
            visited = new boolean[m][n];
            tryMovingDFS(0, 0, m, n, k);
            return res;
        }

        //[0,0]..[i,j]
        private void tryMovingDFS(int i, int j, int m, int n, int k) {
            if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) {
                return;
            }
            int sum = i % 10 + j % 10 + i / 10 + j / 10;
            visited[i][j] = true;
            if (sum <= k) {
                res++;
            } else {
                return;
            }
            for (int l = 0; l < 4; l++) {
                int x = i + D[l][0];
                int y = j + D[l][1];
                tryMovingDFS(x, y, m, n, k);
            }
        }
    }

    //14
    class Solution14 {
        private int[] memo;

        public int cuttingRope(int n) {
            memo = new int[n + 1];
            Arrays.fill(memo, -1);
            return tryCuttingRope(n);
        }

        //将n至少分割成两个部分，获取乘积最大值
        private int tryCuttingRope(int n) {
            if (n == 1) {
                return 1;
            }
            if (memo[n] != -1) {
                return memo[n];
            }
            int res = 0;
            for (int i = 1; i <= n - 1; i++) {
                res = max3(res, i * (n - i), i * tryCuttingRope(n - i));
            }
            memo[n] = res;
            return res;
        }

        private int max3(int a, int b, int c) {
            return Math.max(a, Math.max(b, c));
        }
    }

    class Solution14DP {
        private int[] memo;

        public int cuttingRope(int n) {
            memo = new int[n + 1];
            memo[1] = 1;
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= i - 1; j++) {
                    memo[i] = max3(memo[i], j * (i - j), j * memo[i - j]);
                }
            }
            return memo[n];
        }

        private int max3(int a, int b, int c) {
            return Math.max(a, Math.max(b, c));
        }
    }

    //15
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n &= (n - 1);
            res++;
        }
        return res;
    }

    //18
    class Solution18 {
        public ListNode deleteNode(ListNode head, int val) {
            while (head != null && head.val == val) {
                head = head.next;
            }
            if (head == null) {
                return head;
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
    }

    //19
    class Solution19 {
        public boolean isMatch(String s, String p) {

        }
    }

    //20
    class Solution20 {
        public boolean isNumber(String s) {

        }
    }

    //21
    class Solution21 {
        public int[] exchange(int[] nums) {
            int i = 0;
            int j = nums.length - 1;
            while (i < j) {
                while (i < j && (nums[i] & 1) == 1) {
                    i++;
                }
                while (i < j && (nums[j] & 1) == 0) {
                    j--;
                }
                swap(nums, i, j);
            }
            return nums;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    //22
    class Solution22 {
        public ListNode getKthFromEnd(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            ListNode p1 = head;
            ListNode p2 = head;
            for (int i = 0; i < k; i++) {
                p2 = p2.next;
            }
            while (p2 != null) {
                p2 = p2.next;
                p1 = p1.next;
            }
            return p1;
        }
    }

    //24
    class Solution24 {
        public ListNode reverseList(ListNode head) {
            if (head == null) {
                return head;
            }
            ListNode pre = null;
            ListNode cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            head = pre;
            return head;
        }
    }

    //25
    class Solution25 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode dummyHead = new ListNode(0);
            ListNode cur = dummyHead;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    cur.next = l1;
                    l1 = l1.next;
                } else {
                    cur.next = l2;
                    l2 = l2.next;
                }
                cur = cur.next;
            }
            cur.next = l1 != null ? l1 : l2;
            return dummyHead.next;
        }
    }

    //26
    class Solution26 {
        public boolean isSubStructure(TreeNode A, TreeNode B) {
            if (A == null || B == null) {
                return false;
            }
            return findSub(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
        }

        private boolean findSub(TreeNode A, TreeNode B) {
            if (B == null) {
                return true;
            }
            if (A == null || A.val != B.val) {
                return false;
            }
            return findSub(A.left, B.left) && findSub(A.right, B.right);
        }
    }

    //27
    class Solution27 {
        public TreeNode mirrorTree(TreeNode root) {
            if (root == null) {
                return root;
            }
            TreeNode tmp = root.left;
            root.left = mirrorTree(root.right);
            root.right = mirrorTree(tmp);
            return root;
        }
    }

    //28
    class Solution28 {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return isSymmetric(root.left, root.right);
        }

        private boolean isSymmetric(TreeNode l, TreeNode r) {
            if (l == null && r == null) return true;
            if (l == null || r == null || l.val != r.val) return false;
            return isSymmetric(l.left, r.right) && isSymmetric(l.right, r.left);
        }
    }

    //29
    class Solution29 {
        public int[] spiralOrder(int[][] matrix) {
            if (matrix.length == 0) return new int[0];
            int l = 0;
            int r = matrix[0].length - 1;
            int t = 0;
            int b = matrix.length - 1;
            int x = 0;
            int[] res = new int[(r + 1) * (b + 1)];
            while (true) {
                for (int i = l; i <= r; i++) {
                    res[x++] = matrix[t][i];
                }
                if (++t > b) break;
                for (int i = t; i <= b; i++) {
                    res[x++] = matrix[i][r];
                }
                if (--r < l) break;
                for (int i = r; i >= l; i--) {
                    res[x++] = matrix[b][i];
                }
                if (--b < t) break;
                for (int i = b; i >= t; i--) {
                    res[x++] = matrix[i][l];
                }
                if (++l > r) break;
            }
            return res;
        }
    }

    //30
    class MinStack {
        private Deque<Integer> stack = new ArrayDeque<>();
        private Deque<Integer> minStack = new ArrayDeque<>();

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int x) {
            stack.push(x);
            minStack.push(Math.min(minStack.peek(), x));
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return minStack.peek();
        }
    }

    //31
    class Solution31 {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            Deque<Integer> stack = new ArrayDeque<>();
            int index = 0;
            for (Integer i :
                    pushed) {
                stack.push(i);
                while (!stack.isEmpty() && stack.peek() == popped[index]) {
                    stack.pop();
                    index++;
                }
            }
            return stack.isEmpty();
        }
    }

    //32
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[]{};
        Deque<TreeNode> q = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        q.addLast(root);
        while (!q.isEmpty()) {
            TreeNode node = q.removeFirst();
            list.add(node.val);
            if (node.left != null) {
                q.addLast(node.left);
            }
            if (node.right != null) {
                q.addLast(node.right);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public List<List<Integer>> levelOrder1(TreeNode root) {
        if (root == null) return Collections.emptyList();
        Deque<TreeNode> q = new LinkedList<>();
        List<List<Integer>> list = new LinkedList<>();
        q.addLast(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = q.removeFirst();
                level.add(node.val);
                if (node.left != null) {
                    q.addLast(node.left);
                }
                if (node.right != null) {
                    q.addLast(node.right);
                }
            }
            list.add(level);
        }
        return list;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Deque<TreeNode> q = new LinkedList<>();
        List<List<Integer>> list = new ArrayList<>();
        q.addLast(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = q.removeFirst();
                level.add(node.val);
                if (node.left != null) {
                    q.addLast(node.left);
                }
                if (node.right != null) {
                    q.addLast(node.right);
                }
            }
            if ((list.size() & 1) == 1) {
                Collections.reverse(level);
            }
            list.add(level);
        }
        return list;
    }

    //33
    class Solution33 {
        public boolean verifyPostorder(int[] postorder) {
            if (postorder == null || postorder.length < 2) {
                return true;
            }
            return verifyPostOrder(postorder, 0, postorder.length - 1);
        }

        private boolean verifyPostOrder(int[] postorder, int start, int end) {
            if (start >= end) {
                return true;
            }
            int index = start;
            while (index < end && postorder[index] < postorder[end]) {
                index++;
            }
            int right = index;
            while (index < end && postorder[index] > postorder[end]) {
                index++;
            }
            if (index != end) {
                return false;
            }
            return verifyPostOrder(postorder, start, right - 1) && verifyPostOrder(postorder, right, end - 1);
        }
    }

    //34
    class Solution34 {
        private List<List<Integer>> res = new ArrayList<>();

        private LinkedList<Integer> path = new LinkedList<>();

        public List<List<Integer>> pathSum(TreeNode root, int target) {
            if (root == null) {
                return res;
            }

            findPath(root, target);
            return res;
        }

        private void findPath(TreeNode root, int target) {
            if (root == null) {
                return;
            }
            path.add(root.val);
            target -= root.val;
            if (root.left == null && root.right == null && target == 0) {
                res.add(new LinkedList<>(path));
            }
            findPath(root.left, target);
            findPath(root.right, target);
            path.removeLast();
        }

    }

    //35
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    class Solution35 {
        public Node copyRandomList(Node head) {
            if (head == null)
                return null;
            Node cur = head;
            while (cur != null) {
                Node copyNode = new Node(cur.val);
                copyNode.next = cur.next;
                cur.next = copyNode;
                cur = copyNode.next;
            }
            cur = head;
            while (cur != null) {
                if (cur.random != null) {
                    cur.next.random = cur.random.next;
                }
                cur = cur.next.next;
            }
            Node pre = head;
            Node res = head.next;
            cur = head.next;
            while (cur.next != null) {
                pre.next = pre.next.next;
                cur.next = cur.next.next;
                pre = pre.next;
                cur = cur.next;
            }
            pre.next = null;
            return res;
        }
    }

    //36
    class Solution36 {

        class Node {
            public int val;
            public Node left;
            public Node right;

            public Node() {
            }

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, Node _left, Node _right) {
                val = _val;
                left = _left;
                right = _right;
            }
        }

        private Node prev;
        private Node head;

        public Node treeToDoublyList(Node root) {
            if (root == null) {
                return null;
            }
            inorder(root);
            prev.right = head;
            head.left = prev;
            return head;
        }

        private void inorder(Node cur) {
            if (cur == null) {
                return;
            }
            inorder(cur.left);
            if (prev != null) {
                prev.right = cur;
            } else {
                head = cur;
            }
            cur.left = prev;
            prev = cur;

            inorder(cur.right);
        }
    }

    //37
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "[]";
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node != null) {
                    sb.append(node.val).append(",");
                    queue.add(node.left);
                    queue.add(node.right);
                } else {
                    sb.append("null").append(",");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.equals("[]")) {
                return null;
            }
            String[] nodeArray = data.substring(1, data.length() - 1).split(",");
            TreeNode root = new TreeNode(Integer.parseInt(nodeArray[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int i = 1;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (!nodeArray[i].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(nodeArray[i]));
                    queue.add(node.left);
                }
                i++;
                if (!nodeArray[i].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(nodeArray[i]));
                    queue.add(node.right);
                }
                i++;

            }
            return root;
        }
    }

    //38
    class Solution38 {
        private Set<String> res;
        private boolean[] used;

        public String[] permutation(String s) {
            res = new HashSet<>();
            if (s == null || s.length() == 0) {
                return new String[]{};
            }
            used = new boolean[s.length()];
            find(s.toCharArray(), 0, new StringBuilder());
            return res.toArray(new String[res.size()]);
        }

        private void find(char[] array, int index, StringBuilder path) {
            if (index == array.length) {
                res.add(path.toString());
            }
            for (int i = 0; i < array.length; i++) {
                if (!used[i]) {
                    path.append(array[i]);
                    used[i] = true;
                    find(array, index + 1, path);
                    used[i] = false;
                    path.deleteCharAt(path.length() - 1);
                }
            }
        }
    }


    //39
    class Solution39 {
        public int majorityElement(int[] nums) {
            int x = 0, votes = 0;
            for (Integer i :
                    nums) {
                if (votes == 0) {
                    x = i;
                }
                votes += (i == x ? 1 : -1);
            }
            return x;
        }
    }

    //40
    class Solution40 {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (arr == null || arr.length == 0 || k == 0) {
                return new int[]{};
            }
            int[] res = new int[k];
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            for (Integer i :
                    arr) {
                if (priorityQueue.size() < k) {
                    priorityQueue.offer(i);
                } else {
                    if (i <= priorityQueue.peek()) {
                        priorityQueue.poll();
                        priorityQueue.offer(i);
                    }
                }
            }
            for (int i = 0; i < k; i++) {
                res[i] = priorityQueue.poll();
            }
            return res;
        }
    }

    //41
    class MedianFinder {
        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (maxHeap.size() != minHeap.size()) {
                minHeap.add(num);
                maxHeap.add(minHeap.poll());
            } else {
                maxHeap.add(num);
                minHeap.add(maxHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() != minHeap.size()) {
                return minHeap.peek();
            } else {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
        }
    }

    //42
    class Solution42 {
        public int maxSubArray(int[] nums) {
            int[] memo = new int[nums.length];
            memo[0] = nums[0];
            int res = memo[0];
            for (int i = 1; i < nums.length; i++) {
                memo[i] = Math.max(memo[i - 1], 0) + nums[i];
                res = Math.max(res, memo[i]);
            }
            return res;
        }
    }


    //43
    class Solution43 {
        public int countDigitOne(int n) {

        }
    }

    //44
    class Solution44 {
        public int findNthDigit(int n) {

        }
    }

    //45
    class Solution45 {
        public String minNumber(int[] nums) {
            String[] strs = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                strs[i] = String.valueOf(nums[i]);
            }
            quickSort(strs, 0, strs.length - 1);
            StringBuilder sb = new StringBuilder();
            for (String s :
                    strs) {
                sb.append(s);
            }
            return sb.toString();
        }

        private void quickSort(String[] strs, int start, int end) {
            if (start >= end) {
                return;
            }
            int p = partition(strs, start, end);
            quickSort(strs, start, p - 1);
            quickSort(strs, p + 1, end);
        }

        private int partition(String[] strs, int l, int r) {
            String v = strs[l];
            int i = l + 1;
            int j = r;
            while (true) {
                while (i <= r && (strs[i] + v).compareTo(v + strs[i]) < 0) {
                    i++;
                }
                while (j >= l + 1 && (strs[j] + v).compareTo(v + strs[j]) > 0) {
                    j--;
                }
                if (i > j) {
                    break;
                }
                swap(strs, i, j);
                i++;
                j--;
            }
            swap(strs, l, j);
            return j;
        }

        private void swap(String[] strs, int i, int j) {
            String tmp = strs[j];
            strs[j] = strs[i];
            strs[i] = tmp;
        }
    }

    class Solution45_1 {
        public String minNumber(int[] nums) {
            String[] strs = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                strs[i] = String.valueOf(nums[i]);
            }
            Arrays.sort(strs, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return (o1 + o2).compareTo(o2 + o1);
                }
            });
            StringBuilder sb = new StringBuilder();
            for (String s :
                    strs) {
                sb.append(s);
            }
            return sb.toString();
        }

    }

    //46
    class Solution46 {
        public int translateNum(int num) {
            String s = String.valueOf(num);
            int dp1 = 1;
            int dp2 = 1;
            for (int i = 0; i <= s.length() - 2; i++) {
                String tmp = s.substring(i, i + 2);
                int c = tmp.compareTo("10") >= 0 &&
                        tmp.compareTo("25") <= 0 ? dp1 + dp2 : dp1;
                dp2 = dp1;
                dp1 = c;
            }
            return dp1;
        }
    }


    //47
    class Solution47 {


        public int maxValue(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int[][] dp = new int[m][n];
            dp[0][0] = grid[0][0];
            for (int i = 1; i < n; i++) {
                dp[0][i] = dp[0][i - 1] + grid[0][i];
            }
            for (int i = 1; i < m; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
                }
            }
            return dp[m - 1][n - 1];
        }

    }


    //48
    class Solution48 {
        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> map = new HashMap<>();
            int i = -1;
            int res = 0;
            for (int j = 0; j < s.length(); j++) {
                if (map.containsKey(s.charAt(j))) {
                    i = Math.max(i, map.get(s.charAt(j)));
                }
                map.put(s.charAt(j), j);
                res = Math.max(res, j - i);
            }

            return res;
        }
    }

    //49
    class Solution49 {
        public int nthUglyNumber(int n) {
            int[] dp = new int[n];
            dp[0] = 1;
            int a = 0, b = 0, c = 0;

            for (int i = 1; i < n; i++) {
                int n2 = 2 * dp[a];
                int n3 = 3 * dp[b];
                int n5 = 5 * dp[c];
                dp[i] = Math.min(n2, Math.min(n3, n5));
                if (dp[i] == n2) {
                    a++;
                }
                if (dp[i] == n3) {
                    b++;
                }
                if (dp[i] == n5) {
                    c++;
                }
            }
            return dp[n - 1];
        }
    }


    //50
    class Solution50 {
        public char firstUniqChar(String s) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            }
            for (int i = 0; i < s.length(); i++) {
                if (map.get(s.charAt(i)) == 1) {
                    return s.charAt(i);
                }
            }
            return ' ';
        }
    }

    //51
    static class Solution51 {

        private static int res;

        public static int reversePairs(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            mergeSort(nums, 0, nums.length - 1);
            return res;
        }

        private static void mergeSort(int[] nums, int l, int r) {
            if (l >= r) {
                return;
            }
            int mid = l + (r - l) / 2;
            mergeSort(nums, l, mid);
            mergeSort(nums, mid + 1, r);
            merge(nums, l, mid, r);
        }

        private static void merge(int[] nums, int l, int mid, int r) {
            int[] aux = Arrays.copyOfRange(nums, l, r + 1);
            int i = l;
            int j = mid + 1;
            for (int k = l; k <= r; k++) {
                if (i > mid) {
                    nums[k] = aux[j - l];
                    j++;
                } else if (j > r) {
                    nums[k] = aux[i - l];
                    i++;
                } else if (aux[i - l] < aux[j - l]) {
                    nums[k] = aux[i - l];
                    i++;
                } else {
                    nums[k] = aux[j - l];
                    j++;
                    res += mid - i + 1;
                }

            }
        }
    }

    //52
    public class Solution52 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode cur1 = headA;
            ListNode cur2 = headB;
            while (cur1 != cur2) {
                cur1 = cur1 == null ? headB : cur1.next;
                cur2 = cur2 == null ? headA : cur2.next;
            }
            return cur1;
        }
    }

    //53
    class Solution53 {
        public int search(int[] nums, int target) {
            int res = 0;
            int l = 0;
            int r = nums.length - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] <= target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            int right = r;

            l = 0;
            r = nums.length - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] >= target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            int left = l;
            return right - left + 1;
        }
    }

    //53
    class Solution53_2 {
        public int missingNumber(int[] nums) {
            int l = 0, r = nums.length - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (nums[m] == m) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            return l;
        }
    }

    //54
    class Solution54 {
        int res = 0;
        private int index = 0;

        public int kthLargest(TreeNode root, int k) {
            dfs(root, k);
            return res;
        }

        private void dfs(TreeNode root, int k) {
            if (root == null) {
                return;
            }
            dfs(root.right, k);
            index++;
            if (k == index) {
                res = root.val;
            }
            dfs(root.left, k);
        }
    }

    //55
    class Solution55 {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }

    //55
    class Solution55_2 {
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }

            return Math.abs(depth(root.left) - depth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }

        private int depth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return Math.max(depth(root.left), depth(root.right)) + 1;
        }
    }

    //56
    class Solution56 {
        public int[] singleNumbers(int[] nums) {

        }
    }

    //57
    class Solution57 {
        public int[] twoSum(int[] nums, int target) {
            int i = 0;
            int j = nums.length - 1;
            while (i < j) {
                if (nums[i] + nums[j] < target) {
                    i++;
                } else if (nums[i] + nums[j] > target) {
                    j--;
                } else {
                    return new int[]{nums[i], nums[j]};
                }
            }
            return new int[]{};
        }
    }

    //57
    class Solution57_2 {
        public int[][] findContinuousSequence(int target) {

        }
    }

    //58
    class Solution58_1 {
        public String reverseWords(String s) {
            s = s.strip();
            int i = s.length() - 1, j = i;
            StringBuilder sb = new StringBuilder();
            while (i >= 0) {
                while (i >= 0 && s.charAt(i) != ' ') {
                    i--;
                }
                sb.append(s.substring(i + 1, j + 1)).append(" ");
                while (i >= 0 && s.charAt(i) == ' ') {
                    i--;
                }
                j = i;
            }
            return sb.toString().strip();
        }
    }

    //59
    class Solution59 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return new int[]{};
            }
            int n = nums.length;
            int[] res = new int[n - k +1];
            Deque<Integer> deque = new LinkedList<>();
            for (int i = 0; i < k; i++) {
                while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                    deque.pollLast();
                }
                deque.offerLast(nums[i]);
            }
            res[0] = deque.peekFirst();
            for (int i = k; i < n ; i++) {
                if (deque.peekFirst() == nums[i-k]) {
                    deque.pollFirst();
                }
                while (!deque.isEmpty() && deque.peekLast() < nums[i]){
                    deque.pollLast();
                }
                deque.offerLast(nums[i]);
                res[i-k+1] = deque.peekFirst();
            }
            return res;
        }
    }

    class MaxQueue {
        private final Queue<Integer> q = new LinkedList<>();
        private final Deque<Integer> d = new LinkedList<>();

        public MaxQueue() {

        }

        public int max_value() {
            if (d.isEmpty()) {
                return -1;
            }
            return d.peekFirst();
        }

        public void push_back(int value) {
            q.offer(value);
            while (!d.isEmpty() && d.peekLast() < value) {
                d.pollLast();
            }
            d.offerLast(value);
        }

        public int pop_front() {
            if (q.isEmpty()) {
                return -1;
            }
            if (d.peekFirst().equals(q.peek())) {
                d.pollFirst();
            }
            return q.poll();
        }
    }

    //60
    class Solution60 {
        public double[] dicesProbability(int n) {

        }
    }

    //61
    class Solution61 {
        public boolean isStraight(int[] nums) {
            Set<Integer> set = new HashSet<>();
            int max = 0, min = 14;
            for (Integer i:
                 nums) {
                if (i == 0) continue;
                if (set.contains(i)){
                    return false;
                }
                set.add(i);
                max = Math.max(max, i);
                min = Math.min(min, i);
            }
            return max - min < 5;
        }
    }

    //62
    class Solution62 {
        public int lastRemaining(int n, int m) {

        }
    }

    //63
    class Solution63 {
        public int maxProfit(int[] prices) {
            int cost = Integer.MAX_VALUE;
            int profit = 0;
            for (Integer i:
                 prices) {
                cost = Math.min(cost, i);
                profit = Math.max(profit, i - cost);
            }
            return profit;
        }
    }

    //64
    class Solution64 {
        public int sumNums(int n) {

        }
    }

    //65
    class Solution65 {
        public int add(int a, int b) {

        }
    }

    //66
    class Solution66 {
        public int[] constructArr(int[] a) {

        }
    }

    //67
    class Solution67 {
        public int strToInt(String str) {
            String s = str.trim();
            if (s == null || s.length() == 0) {
                return 0;
            }
            int res =0;
            int t = Integer.MAX_VALUE/10;

            char[] chars = s.toCharArray();
            int i = 1, sign =1;
            if (chars[0] == '-') {
                sign = -1;
            }else if (chars[0] != '+') {
                i = 0;
            }
            for (int j = i; j < chars.length; j++) {
                if (chars[j] < '0' || chars[j] > '9'){
                    break;
                }
                if (res > t || res ==t && chars[j] > '7') {
                    return  sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                res = res *10 + (chars[j] - '0');
            }
            return sign * res;
        }
    }

    //68
    class Solution68 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || p == root || q == root) return root;
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left == null ) return right;
            if (right == null) return left;
            return root;
        }
    }


}
