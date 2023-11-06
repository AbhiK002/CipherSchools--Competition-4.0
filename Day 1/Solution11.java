class Node {
    public int data;
    public Node next;
    Node(int data) {
        this.data = data;
        this.next = null;
    }

    // Only defined to print the linked list in a pretty way
    @Override
    public String toString() {
        String output = "";
        Node ptr = this;

        while (ptr != null) {
            output += (ptr.data + " -> ");
            ptr = ptr.next;
        }
        output += " null";
        return output;
    }
}

public class Solution11 {
    public static Node getListAfterReverseOperation(Node head, int n, int b[]) {
        Node root = new Node(0);
        root.next = head;

        Node prev = root;
        Node ptr = root;

        for (int groupSize : b) {
            if (groupSize == 0) continue;
            if (prev.next == null) break;

            int elementCount = 0;

            while (ptr.next != null && elementCount < groupSize) {
                ptr = ptr.next;
                elementCount++;
            }

            reversePart(prev, ptr);
            for (int i=0; i<groupSize; i++) {
                if (prev.next == null) break;
                prev = prev.next;
            }
            ptr = prev;
        }

        return root.next;
    }

    public static void reversePart(Node beforeFirst, Node second) {
        if (beforeFirst == second) return;
        Node ptr = null, temp;

        while (beforeFirst.next != second) {
            ptr = beforeFirst.next;
            beforeFirst.next = ptr.next;

            temp = second.next;
            ptr.next = temp;
            second.next = ptr;
        }
    }

    // Below functions are defined for testing the solution
    public static Node convertToLinkedList(int[] elements) {
        Node head = new Node(0);
        Node ptr = head;

        for (int i : elements) {
            ptr.next = new Node(i);
            ptr = ptr.next;
        }

        return head.next;
    }

    public static void main(String[] args) {
        Node head; int n, b[];

        // Testcase 1
        head = convertToLinkedList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1});
        n = 3;
        b = new int[]{2, 3, 4};
        System.out.println(
                Solution11.getListAfterReverseOperation(head, n, b)
        );

        // Testcase 2
        head = convertToLinkedList(new int[]{0, 6, 1, 5, -1});
        n = 2;
        b = new int[]{2, 3};
        System.out.println(
                Solution11.getListAfterReverseOperation(head, n, b)
        );

        // Testcase 3
        head = convertToLinkedList(new int[]{5, 7, 8, 2, 4, -1});
        n = 3;
        b = new int[]{1, 0, 2};
        System.out.println(
                Solution11.getListAfterReverseOperation(head, n, b)
        );
    }
}