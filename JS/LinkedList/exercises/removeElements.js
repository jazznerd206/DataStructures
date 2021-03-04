/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} val
 * @return {ListNode}
 */
var removeElements = function(head, val) {
    if (!head) return null;
    let current = new ListNode();
    current.next = head;
    let i = current;
    while(i && i.next) {
        i.next.val === val ? 
            i.next = i.next.next || null
                :
            i = i.next;
    }
    return current.next;
}