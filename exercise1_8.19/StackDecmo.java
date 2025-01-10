
/**
 * This class implements a stack
 *
 * @author (Andrew Bae)
 * @version (8/26/24)
 */

import java.util.*;

public class StackDecmo
{
    public static void main(String[] args) {
        Stack st = new Stack();
        System.out.println("Stack: " + st);
        showpush(st, 42);
        showpush(st, 66);
        showpush(st, 99);
        System.out.println("Size of stack --> " + st.size());
        showpop(st);
        System.out.println("Stack: " + st);
        showpop(st);
        showpop(st);
        try {
            showpop(st);
        } catch (EmptyStackException e) {
            System.out.println("The stack is empty");
        }
    }
    
    public static void showpush(Stack st, int a) {
        st.push(new Integer(a));
        System.out.println("push (" + a + ")");
        System.out.println("stack: " + st);
        
    }
    
    public static void showpop(Stack st) {
        System.out.println("pop -->");
        Integer a = (Integer)st.pop();
        System.out.println(a);
    }
}
