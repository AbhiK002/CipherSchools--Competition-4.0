class DoublyNode {
    public int value;
    public DoublyNode next, prev;
    
    DoublyNode(int value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    // Only defined to print the linked list in a pretty way
    @Override
    public String toString() {
        String output = "";
        DoublyNode ptr = this;

        while (ptr != null) {
            output += (ptr.value + " <-> ");
            ptr = ptr.next;
        }
        output += " null";
        return output;
    }
}


public class Problem2 {
    public static DoublyNode quickSort(DoublyNode head) {
        if (head == null || head.next == null)
            return head;

        DoublyNode root = new DoublyNode(0);
        root.next = head;
        head.prev = root;

        DoublyNode endOfList = head;
        while (endOfList.next != null) {
            endOfList = endOfList.next;
        }

        partitionAndSort(head, endOfList);

        return root.next;
    }

    static void partitionAndSort(DoublyNode start, DoublyNode end) {
        if (start == null || end == null || start == end || start.prev == end) return;

        int pivotElement = end.value;
        DoublyNode tracer = start.prev, ptr = start;

        while (!ptr.equals(end)) {
            if (ptr.value < pivotElement) {
                tracer = tracer.next;

                int temp = tracer.value;
                tracer.value = ptr.value;
                ptr.value = temp;
            }

            ptr = ptr.next;
        }

        tracer = tracer.next;

        int temp = tracer.value;
        tracer.value = end.value;
        end.value = temp;

        partitionAndSort(start, tracer.prev);
        partitionAndSort(tracer.next, end);
    }

    public static DoublyNode convertToDoublyLinkedList(int[] elements) {
        DoublyNode head = new DoublyNode(0);
        DoublyNode ptr = head;

        for (int i : elements) {
            DoublyNode node = new DoublyNode(i);
            node.prev = ptr;
            ptr.next = node;
            ptr = ptr.next;
        }

        head.next.prev = null;
        return head.next;
    }

    public static void main(String[] args) {
        DoublyNode head;
        System.out.println("PROBLEM 2");

        head = convertToDoublyLinkedList(new int[]{4, 2, -3, 4});
        System.out.println(
                quickSort(head)
        );

        head = convertToDoublyLinkedList(new int[]{3, 3, 4, 2, 4});
        System.out.println(
                quickSort(head)
        );

        head = convertToDoublyLinkedList(new int[]{45, -2, 42, 5, -11});
        System.out.println(
                quickSort(head)
        );

        head = convertToDoublyLinkedList(new int[]{-2, 12, -1, 1, 20, 1});
        System.out.println(
                quickSort(head)
        );
    }
}

