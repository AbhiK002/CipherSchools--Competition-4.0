import java.util.*;

public class Problem4 {
    public static int largestRectangle(ArrayList <Integer> heights) {
        int maxArea = 0, n = heights.size();

        int[] left = new int[n];

        // Stack implemented using array for faster runtime
        int[] stack = new int[n+1];
        int top = -1;

        for (int i=0; i<n; i++) {
            while (top != -1 && heights.get(stack[top]) >= heights.get(i)) top--;

            if (top == -1) {
                left[i] = -1;
            }
            else left[i] = stack[top];

            stack[++top] = i;
        }

        int[] right = new int[n];
        top = -1;

        for (int i=n-1; i>=0; i--) {
            while (top != -1 && heights.get(stack[top]) >= heights.get(i)) top--;

            if (top == -1) {
                right[i] = n;
            }
            else right[i] = stack[top];

            stack[++top] = i;
        }

        for (int i=0; i<n; i++) {
            maxArea = Math.max(maxArea, heights.get(i) * (right[i] - left[i] - 1));
        }

        return maxArea;
    }

    public static void main(String[] args) {
        ArrayList<Integer> heights;

        heights = convertToArrayList(new int[]{1, 0, 1, 2, 2, 2, 2, 1, 0, 2});
        System.out.println(largestRectangle(heights));

        heights = convertToArrayList(new int[]{1, 2, 1, 0, 1, 1, 0, 0, 2, 2});
        System.out.println(largestRectangle(heights));

        heights = convertToArrayList(new int[]{8, 6, 3, 5, 0, 0, 4, 10, 2, 5});
        System.out.println(largestRectangle(heights));

        heights = convertToArrayList(new int[]{6, 1, 8, 10, 5, 7, 0, 4, 5, 8});
        System.out.println(largestRectangle(heights));
    }

    private static ArrayList<Integer> convertToArrayList(int[] nums) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i : nums) arr.add(i);
        return arr;
    }
}