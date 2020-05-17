package interviews.amazon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class CopyListWithRandomPointer {

    /*
        //leetcode solution
*/
    public class Solution {
        // HashMap which holds old nodes as keys and new nodes as its values.
        HashMap<Nodee, Nodee> visitedHash = new HashMap<Nodee, Nodee>();

        public Nodee copyRandomList(Nodee head) {

            if (head == null) {
                return null;
            }

            List<String> st = new ArrayList<>();
            st.sort(Comparator.naturalOrder());
            // If we have already processed the current node, then we simply return the cloned version of
            // it.
            if (this.visitedHash.containsKey(head)) {
                return this.visitedHash.get(head);
            }

            // Create a new node with the value same as old node. (i.e. copy the node)
            Nodee node = new Nodee(head.val);

            // Save this value in the hash map. This is needed since there might be
            // loops during traversal due to randomness of random pointers and this would help us avoid
            // them.
            this.visitedHash.put(head, node);

            // Recursively copy the remaining linked list starting once from the next pointer and then from
            // the random pointer.
            // Thus we have two independent recursive calls.
            // Finally we update the next and random pointers for the new node created.
            node.next = this.copyRandomList(head.next);
            node.random = this.copyRandomList(head.random);

            return node;
        }
    }
}

class Nodee {
    int val;
    Nodee next;
    Nodee random;

    public Nodee(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}