class NStack {
    int[] array; // stores the elements of all the N stacks
    int[] tops; // stores the top pointers of all the N stacks
    int[] next; // stores the context of mth stack for each element present in the stacks
    int freeIndex; // next index that's empty in "array"

    public NStack(int N, int S) {
        array = new int[S];
        tops = new int[N];
        next = new int[S];

        for (int i=0; i<N; i++) {
            tops[i] = -1;
        }
        for (int i=0; i<S-1; i++) {
            next[i] = i+1;
        }
        next[S-1] = -1;

        freeIndex = 0;
    }

    public boolean push(int x, int m) {
        if (freeIndex == -1) {
            return false;
        }

        int index = freeIndex;
        array[index] = x;

        freeIndex = next[index];
        next[index] = tops[m-1]; // previous element of the mth stack
        tops[m-1] = index; // point the top of mth stack to the current index

        return true;
    }

    public int pop(int m) {
        if (tops[m-1] == -1) {
            return -1;
        }

        int index = tops[m-1];
        tops[m-1] = next[index]; // point the top of mth stack to prev element

        next[index] = freeIndex; // current free index becomes the next free index
        freeIndex = index; // popped element's index is now the current free index

        return array[index];
    }
}

public class Problem3 {
    public static void main(String[] args) {
        int N, S; NStack nstack;

        N = 3; S = 6;
        nstack = new NStack(N, S);
        System.out.println(nstack.push(10, 1));
        System.out.println(nstack.push(20, 1));
        System.out.println(nstack.push(30, 1));
        System.out.println(nstack.pop(1));
        System.out.println(nstack.pop(1));

        System.out.println();

        N = 1; S = 5;
        nstack = new NStack(N, S);
        System.out.println(nstack.push(15, 1));
        System.out.println(nstack.push(25, 1));
        System.out.println(nstack.pop(1));
        System.out.println(nstack.push(30, 1));
        System.out.println(nstack.pop(1));
    }
}