import java.util.ArrayList;

/**
 *
 * @author oskar grönman
 */
public class ScapegoatTree {

    private SgNode root;
    private int nmr_nodes; //Size of tree
    private int thing;

    public ScapegoatTree() {
        root = null;
        nmr_nodes = 0;
    }
    
    public ArrayList<Integer> inorder(){
        ArrayList<Integer> array = new ArrayList<>();
        inorder(root, array);
        return array;
    }
    
    private void inorder(SgNode n, ArrayList array){
        if(n != null){
            inorder(n.getLeft(), array);
            array.add(n.getValue());
            System.out.println("The value is: " + n.getValue());
            inorder(n.getRight(), array);
        }
    }
    
    public void preorder(){
        preorder(root);
    }
    
    private void preorder(SgNode n){
        if(n != null){
            System.out.println("The value is: " + n.getValue());
            preorder(n.getLeft());
            preorder(n.getRight());
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
        nmr_nodes = 0;
    }

    public int size() {
        return nmr_nodes;
    }

    //Size for inserting new elements into the tree thing
    private int size(SgNode n) {
        if (n == null) {
            return 0;
        } else {
            int l = 1;
            l += size(n.getLeft());
            l += size(n.getRight());
            return l;
        }
    }

    private static final int log32(int v) { //it is actually 3/2 not 32
        final double log23 = 2.4663034623764317;    //This is 2/3 not 23
        return (int) Math.ceil(log23 * Math.log(v));
    }

    public void insert(int x) {

        SgNode newN = new SgNode(x);

        int height = insertDepthThing(newN);

        if (height > log32(nmr_nodes)) {
            SgNode w = newN.getParent();
            while (3 * size(w) <= 2 * size(w.getParent())) {
                w = w.getParent();
                rebuild(w.getParent());
            }
        }
    }

    private void rebuild(SgNode n) {
        int ns = size(n);
        SgNode parent = n.getParent();
        SgNode[] array = new SgNode[ns];
        intoArray(n, array, 0);
        if (parent == null) {
            root = intoBalanced(array, 0, ns);
            root.setParent(null);
        } else if (parent.getRight() == n) {
            parent.setRight(intoBalanced(array, 0, ns));
            parent.getRight().setParent(parent);
        } else {
            parent.setLeft(intoBalanced(array, 0, ns));
            parent.getLeft().setParent(parent);
        }

    }

    public SgNode intoBalanced(SgNode[] array, int i, int ns) {
        if (ns == 0) {
            return null;
        } else {
            int m = ns / 2;
            array[i + m].setLeft(intoBalanced(array, i, m));
            if (array[i + m].getLeft() != null) {
                array[i + m].getLeft().setParent(array[i + m]);
            }
            array[i + m].setRight(intoBalanced(array, i + m, ns - m - 1));
            if (array[i + m].getRight() != null) {
                array[i + m].getRight().setParent(array[i + m]);
            }
            return array[i + m];
        }
    }

    public int intoArray(SgNode n, SgNode[] array, int i) {
        if (n == null) {
            return i;
        } else {
            i = intoArray(n.getLeft(), array, i);
            array[i++] = n;
            return intoArray(n.getRight(), array, i);
        }
    }

    private int insertDepthThing(SgNode n) {
        SgNode w = root;
        if (w == null) {
            root = n;
            nmr_nodes++;
            thing++;
            return 0;
        } else {
            boolean done = false;
            int depth = 0;
            do {

                if (n.getValue() < w.getValue()) {
                    if (w.getLeft() == null) {
                        w.setLeft(n);
                        n.setParent(w);
                        done = true;
                    } else {
                        w = w.getLeft();
                    }
                } else if (n.getValue() > w.getValue()) {
                    if (w.getRight() == null) {
                        w.setRight(n);
                        n.setParent(w);
                        done = true;
                    } else {
                        w = w.getRight();
                    }
                } else {
                    return -1;
                }
                depth++;
            } while (!done);
            nmr_nodes++;
            thing++;
            return depth;
        }
    }

}
