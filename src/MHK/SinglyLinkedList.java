// Benjamin Farrah
// 95-771 Data Structure and Algorithms for Information Processing
// Project 1 Part 2

package MHK;

public class SinglyLinkedList {
    ObjectNode iterator;
    // head pointer
        // always points to the head of the list
    ObjectNode head;
    // tail pointer
        // always points to last node on the list
    ObjectNode tail;
    int countNodes;

    // Object initialData, ObjectNode initialLink
    public SinglyLinkedList(){
//        data = initialData;
//        link = initialLink;

        //head = new ObjectNode(data, link);
        //tail = new ObjectNode();
        //countNodes = 0;
        head = new ObjectNode(null,null);
        tail = new ObjectNode(null,null);
        iterator = head;
        //tail = new ObjectNode(null, null);
    }

    public void reset(){
        // reset the iterator to the beginning of the list
        //      That is, set a reference to the head of the list.
        iterator = head;
    }

    public Object next(){
        // return the Object pointed to by the iterator and increment the iterator to the next node in
        //      the list. This reference becomes null if the object returned is the last node on the list.
        ObjectNode nextNode = iterator;
        iterator = iterator.getLink();
        return nextNode.getData();

    }

    public boolean hasNext(){
        // return true if the iterator is not null
        return iterator != null;
    }

    public void addAtFrontNode(Object c){
        // Add a node containing the Object c to the head of the linked list.
        //head.addNodeAfter(c);
        //ObjectNode oldHead = ObjectNode.listCopy(head);
//        if (head == null){
//
//        }
        if (head.getData() == null){
            head = new ObjectNode(c, null);
            tail = head;
        } else if (head == tail) {

            head = new ObjectNode(c, tail);
//            head.setLink(tail);
//            head = new ObjectNode(c, head);
        }else{
            head = new ObjectNode(c, head);
        }
        countNodes++;
    }

    public void addAtEndNode(Object c){
        // Add a node containing the Object c to the end of the linked list.
        // No searching of the list is required. The tail pointer is used to access the last node
        // in O(1) time.
        if (tail.getData() == null){
            tail = new ObjectNode(c,null);
            head = tail;
        } else if (head == tail) {
            //ObjectNode oldTail = tail;
            tail = new ObjectNode(c, null);
            //oldTail.setLink(tail);
            head.setLink(tail);
        } else{
            ObjectNode oldTail = tail;
            tail = new ObjectNode(c, null);
            oldTail.setLink(tail);
        }
        countNodes++;
    }

    public int countNodes(){
        return countNodes;
    }

    public Object getObjectAt(int i){
        // Returns a reference (0 based) to the object with list index i. *
        return ObjectNode.listPosition(head, (i+1));
    }

    public Object getLast(){
        // Returns the data in the tail of the list
        return tail.getData();
    }

    public String toString(){
        // Returns the list as a String
        return head.toString();
    }

    /*
        9. Write code in the main method of SinglyLinkedList.java.
        This code should test each method of the class. In
        particular, it must include testing of list iteration using
        reset(), hasNext() and next(). You need to add these three
        methods to the linked list class. If s is a list, this code
        will display its contents from the main routine:
             s.reset();
             while(s.hasNext()) {
                System.out.println(s.next());
             }
        Also, see the Javadoc on the schedule for a description of
        these three methods.
     */
//    public static void main(java.lang.String[] a){
//        String[] nums = {"1","2","3","4","5"};
//        SinglyLinkedList s = new SinglyLinkedList();
//        s.addAtFrontNode(nums[0]);
//        s.addAtFrontNode(nums[1]);
//        s.addAtEndNode(nums[2]);
//        //s.addAtEndNode(nums[4]);
//        //System.out.println(s.toString());
//
//
//        s.reset();
//        //while(s.hasNext()) {
//            //System.out.println(s.next());
//        //}
//    }
}
