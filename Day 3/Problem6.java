import java.util.*;

class BinaryTreeNode<T> {
    T data;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;

    public BinaryTreeNode(T data) {
        this.data = data;
    }
}

public class Problem6 {
    private static BinaryTreeNode<Integer> burningSourceNode = null;

    public static int timeToBurnTree(BinaryTreeNode<Integer> root, int start) {
        HashMap<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> childToParentMap = new HashMap<>();

        // map each child to parent node
        mapChildrenToParents(root, childToParentMap,null, start);

        Queue<BinaryTreeNode<Integer>> burningNodes = new LinkedList<>();
        HashSet<Integer> burntValues = new HashSet<>();

        burntValues.add(burningSourceNode.data);
        burningNodes.add(burningSourceNode);

        int time = 0;
        while (!burningNodes.isEmpty()){
            int numberOfBurningNodes = burningNodes.size();

            // burn the surrounding nodes of each burning node
            for (int i=0; i < numberOfBurningNodes; i++){
                // get (and also remove) the last burning node
                BinaryTreeNode<Integer> burningNode = burningNodes.poll();

                // add the parent, left and right nodes of the current burning node
                if (childToParentMap.get(burningNode) != null && !burntValues.contains(childToParentMap.get(burningNode).data)){
                    burntValues.add(childToParentMap.get(burningNode).data);
                    burningNodes.add(childToParentMap.get(burningNode));
                }
                if (burningNode.left != null && !burntValues.contains(burningNode.left.data)) {
                    burntValues.add(burningNode.left.data);
                    burningNodes.add(burningNode.left);
                }
                if (burningNode.right != null && !burntValues.contains(burningNode.right.data)){
                    burntValues.add(burningNode.right.data);
                    burningNodes.add(burningNode.right);
                }
            }
            time++;
        }
        return time-1;
    }

    private static void mapChildrenToParents(BinaryTreeNode<Integer> root, HashMap<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> childToParentMap, BinaryTreeNode<Integer> parentNode, int start){
        // recursively maps each child node to its parent node
        if (root == null) return;

        childToParentMap.put(root, parentNode);

        if (root.data == start) burningSourceNode = root;

        mapChildrenToParents(root.left, childToParentMap, root, start);
        mapChildrenToParents(root.right, childToParentMap, root, start);
    }

    // For Testing
    private static BinaryTreeNode<Integer> convertListToBinaryList(int[] elements) {
        ArrayList<BinaryTreeNode<Integer>> prevNodes = new ArrayList<>();

        if (elements.length == 0) {
            return null;
        }

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(elements[0]);
        prevNodes.add(root);

        for (int i=1; i < elements.length; i += 2) {
            BinaryTreeNode<Integer> prevCorrParent = prevNodes.remove(0);

            if (elements[i] != -1) {
                prevCorrParent.left = new BinaryTreeNode<>(elements[i]);
                prevNodes.add(prevCorrParent.left);
            }
            if ((i+1) < elements.length && elements[i+1] != -1) {
                prevCorrParent.right = new BinaryTreeNode<>(elements[i+1]);
                prevNodes.add(prevCorrParent.right);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root; int start;

        // testcase 1
        root = convertListToBinaryList(new int[]{1, 2, 3, 4, -1, -1, 5, -1, -1, -1, -1});
        start = 2;
        System.out.println(timeToBurnTree(root, start));

        // testcase 2
        root = convertListToBinaryList(new int[]{3, 1, 2, 5, 6, -1, -1, -1, -1, -1, -1});
        start = 3;
        System.out.println(timeToBurnTree(root, start));

        // testcase 3
        root = convertListToBinaryList(new int[]{1, 2, -1, 3, -1, -1, 4, 5, -1, -1, 6, -1, -1});
        start = 6;
        System.out.println(timeToBurnTree(root, start));
    }
}