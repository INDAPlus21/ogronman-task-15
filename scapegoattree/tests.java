
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;


/**
 *
 * @author oskar gröönman
 */
public class tests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ScapegoatTree t = new ScapegoatTree();
        t.insert(2);
        t.insert(6);
        t.insert(3);
        t.insert(8);
        t.preorder();
        System.out.println(t.size());
        System.out.println("Inorder it is: ");
        t.inorder();
    }

    @Test
    public void testttt() {
        System.out.println("IS the thing doing things?");
        ScapegoatTree instance = new ScapegoatTree();
        instance.insert(5);
        instance.insert(0);
        instance.insert(8);
        instance.insert(3);
        instance.insert(6);
        instance.insert(2);
        instance.insert(9);

        ArrayList<Integer> arrayNum = new ArrayList<Integer>();

        arrayNum.add(5);
        arrayNum.add(0);
        arrayNum.add(8);
        arrayNum.add(3);
        arrayNum.add(6);
        arrayNum.add(2);
        arrayNum.add(9);

        Collections.sort(arrayNum);
        printArrayList(arrayNum);
        int[] arrayIn = arrayNum.stream().mapToInt(i -> i).toArray();;
        int[] arrayOut = instance.inorder().stream().mapToInt(i -> i).toArray();

        assertArrayEquals(arrayIn, arrayOut);

    }
    
    public static void printArrayList(ArrayList l){
        for (int i = 0; i < l.size(); i++){
            System.out.print(l.get(i) + ", ");
        }
        System.out.print("\n");
    }

}
