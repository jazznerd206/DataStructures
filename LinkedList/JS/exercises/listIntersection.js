import { Node, SinglyLinkedList } from '../LinkedList.js';

var getIntersectionNode = function(headA, headB) {
    
    // edge case, if either of the lists don't exist, bail out
    if (!headA || !headB) return null;
    
    // resource acquisition, take reference to params so we can still use intial values
    let l1 = headA;
    let l2 = headB;
    
    // while current are not equal, go to the next in the list
    // if this.next is not available (null), activate second arm of ternary operator and switch lists
    while (l1 !== l2) {
        l1 = l1 ? l1.next : headB;
        l2 = l2 ? l2.next : headA;
    }
    
    // return
    return l1;
};

// console.log(getIntersectionNode(A,B));
const arrayA = [4,1,8,4,5];
// const arrayB = [5,6,1,8,4,5];

const A = new SinglyLinkedList();
// const B = new SinglyLinkedList();

for (i = 0; i < arrayA.length; i++) {
    A.push(arrayA[i]);
}
console.log(A)