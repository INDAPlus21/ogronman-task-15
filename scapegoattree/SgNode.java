/**
 *
 * @author oskar Grönman
 */
public class SgNode {

    private int value;

    private SgNode left, right, parent;

    public SgNode() {
        value = 0;
        left = null;
        right = null;
        parent = null;
    }

    public SgNode(int v) {
        value = v;
        left = null;
        right = null;
        parent = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public SgNode getLeft() {
        return left;
    }

    public void setLeft(SgNode left) {
        this.left = left;
    }

    public SgNode getRight() {
        return right;
    }

    public void setRight(SgNode right) {
        this.right = right;
    }

    public SgNode getParent() {
        return parent;
    }

    public void setParent(SgNode parent) {
        this.parent = parent;
    }
    
}
