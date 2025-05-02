class BSTNode<T> {
    public String key;
    public T data;
    public BSTNode<T> left, right;

    public BSTNode(String key, T data) {
        this.key = key;
        this.data = data;
        left = right = null;
    }
}

public class BST<T> {
    private BSTNode<T> root, current;

    public BST() {
        current = root = null;
    }

    public void clear() {
        current = root = null;
    }

    public boolean empty() {
        return root == null;
    }

    public boolean full() {
        return false;
    }

    public T retrieve() {
        return current.data;
    }

    public boolean findKey(String k) {
        BSTNode<T> p = root;
        while (p != null) {
            current = p;
            if (k.compareTo(p.key) == 0) {
                return true;
            } else if (k.compareTo(p.key) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return false;
    }

    public boolean insert(String k, T val) {
        if (root == null) {
            current = root = new BSTNode<T>(k, val);
            return true;
        }

        BSTNode<T> p = root;
        while (p != null) {
            current = p;
            if (k.compareTo(p.key) == 0) {
                return false;
            } else if (k.compareTo(p.key) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        BSTNode<T> tmp = new BSTNode<T>(k, val);
        if (k.compareTo(current.key) < 0) {
            current.left = tmp;
        } else {
            current.right = tmp;
        }
        current = tmp;
        return true;
    }

    public boolean removeKey(String k) {
        String k1 = k;
        BSTNode<T> p = root;
        BSTNode<T> q = null;

        while (p != null) {
            if (k1.compareTo(p.key) < 0) {
                q = p;
                p = p.left;
            } else if (k1.compareTo(p.key) > 0) {
                q = p;
                p = p.right;
            } else {
                if ((p.left != null) && (p.right != null)) {
                    BSTNode<T> min = p.right;
                    q = p;
                    while (min.left != null) {
                        q = min;
                        min = min.left;
                    }
                    p.key = min.key;
                    p.data = min.data;
                    k1 = min.key;
                    p = min;
                }
                BSTNode<T> child;
                if (p.left != null) {
                    child = p.left;
                } else {
                    child = p.right;
                }

                if (q == null) {
                    root = child;
                } else {
                    if (k1.compareTo(q.key) < 0) {
                        q.left = child;
                    } else {
                        q.right = child;
                    }
                }
                current = root;
                return true;
            }
        }
        return false;
    }

    public void inOrder() {
        if (root == null) {
            System.out.println("empty");
            return;
        }
        inOrder(root);
    }

    private void inOrder(BSTNode<T> p) {
        if (p == null) return;
        inOrder(p.left);
        System.out.println(p.key);
        System.out.println(p.data);
        inOrder(p.right);
    }
}