import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    int key, height;
    Node left = null, right = null;

    Node(int d) {
        key = d;
        height = 1;
    }
}

class BinarySearchTree {
    Node root;

    Node search(Node x, int t) {
        if (x == null || t == x.key)
            return x;
        if (t < x.key)
            return search(x.left, t);
        else
            return search(x.right, t);
    }

    Node next(int t) {
        Node current = root, successor = null;
        while (current != null)
            if (current.key > t) {
                successor = current;
                current = current.left;
            } else
                current = current.right;
        return successor;
    }

    Node prev(int t) {
        Node current = root, successor = null;
        while (current != null)
            if (current.key < t) {
                successor = current;
                current = current.right;
            } else
                current = current.left;
        return successor;
    }

    int height(Node x) {
        if (x == null)
            return 0;
        return x.height;
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node r = x.right;

        x.right = y;
        y.left = r;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node l = y.left;

        y.left = x;
        x.right = l;

          Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalance(Node x) {
        if (x == null)
            return 0;
        return height(x.left) - height(x.right);
    }

    Node insert(Node x, int key) {
        if (x == null)
            return (new Node(key));

        if (key < x.key)
            x.left = insert(x.left, key);
        else if (key > x.key)
            x.right = insert(x.right, key);
        else
            return x;

        x.height = 1 + Math.max(height(x.left),
                height(x.right));
        int balance = getBalance(x);
        if (balance > 1 && key < x.left.key)
            return rightRotate(x);
        if (balance < -1 && key > x.right.key)
            return leftRotate(x);
        if (balance > 1 && key > x.left.key) {
            x.left = leftRotate(x.left);
            return rightRotate(x);
        }
        if (balance < -1 && key < x.right.key) {
            x.right = rightRotate(x.right);
            return leftRotate(x);
        }
        return x;
    }

    Node minValueNode(Node x) {
        Node current = x;
        while (current.left != null)
            current = current.left;

        return current;
    }

    Node deleteNode(Node x, int key) {
        if (x == null)
            return x;
        if (key < x.key)
            x.left = deleteNode(x.left, key);
        else if (key > x.key)
            x.right = deleteNode(x.right, key);
        else {
            if ((x.left == null) || (x.right == null)) {
                Node temp = null;
                if (temp == x.left)
                    temp = x.right;
                else
                    temp = x.left;
                if (temp == null) {
                    temp = x;
                    x = null;
                } else
                    x = temp;
            } else {

                Node temp = minValueNode(x.right);

                x.key = temp.key;
                x.right = deleteNode(x.right, temp.key);
            }
        }
        if (x == null)
            return x;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        int balance = getBalance(x);
        if (balance > 1 && getBalance(x.left) >= 0)
            return rightRotate(x);
        if (balance > 1 && getBalance(x.left) < 0) {
            x.left = leftRotate(x.left);
            return rightRotate(x);
        }

        if (balance < -1 && getBalance(x.right) <= 0)
            return leftRotate(x);
        if (balance < -1 && getBalance(x.right) > 0) {
            x.right = rightRotate(x.right);
            return leftRotate(x);
        }
        return x;
    }

}

public class SimpleBST {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BinarySearchTree tree = new BinarySearchTree();
        String str;
        while ((str = reader.readLine()) != null) {
            if (str.trim().equals("")) break;
            String[] input = str.split(" ");
            String s = input[0];
            int x = Integer.parseInt(input[1]);
            switch (s) {
                case "insert":
                    tree.root = tree.insert(tree.root, x);
                    break;
                case "exists":
                    Node res = tree.search(tree.root, x);
                    System.out.println(res != null);
                    break;
                case "next":
                    Node node = tree.next(x);
                    System.out.println(node == null ? "none" : node.key);
                    break;
                case "prev":
                    node = tree.prev(x);
                    System.out.println(node == null ? "none" : node.key);
                    break;
                case "delete":
                    if (tree.search(tree.root, x) != null)
                        tree.root = tree.deleteNode(tree.root, x);
                    break;
                default:
                    break;
            }
        }
    }

}
