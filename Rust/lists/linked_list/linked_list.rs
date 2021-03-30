use std::fmt::{self, Display, Formatter};
use std::ptr::NonNull;


// LINKED LIST NODES
// don't need public because both their data and methods are exposed to linked list which is the sole consumer of this struct. Node will never be called outside the context of Linked List.

// node definition
struct Node<T> {
    val: T,
    next: Option<NonNull<Node<T>>>,
    prev: Option<NonNull<Node<T>>>,
}

// new node constructor: new()
impl<T> Node<T> {
    fn new(t: T) -> Node<T> {
        Node {
            val: t,
            prev: None,
            next: None,
        }
    }
}

// LINKED LIST DEFINITION
// pub attached to expose data and methods

// linked list definition
pub struct LinkedList<T> {
    length: u32,
    start: Option<NonNull<Node<T>>>,
    end: Option<NonNull<Node<T>>>,
}

// default function? why?
impl<T> Default for LinkedList<T> {
    fn default() -> Self {
        Self::new()
    }
}

// Linked list constructor and methods
impl<T> LinkedList<T> {
    // create linked list
    // no input params
    // returns Self which is the list object
    pub fn new() -> Self {
        Self {
            length: 0,
            start: None,
            end: None,
        }
    }

    // add node
    // takes a mutable reference to self and an object of yet to determine type
    // returns nothing -> ()
    pub fn add(&mut self, obj: T) {
        let mut node = Box::new(Node::new(obj));
        // Since we are adding node at the end, next will always be None
        node.next = None;
        node.prev = self.end;
        // Get a pointer to node
        let node_ptr = Some(unsafe { NonNull::new_unchecked(Box::into_raw(node)) });
        match self.end {
            // This is the case of empty list
            None => self.start = node_ptr,
            Some(end_ptr) => unsafe { (*end_ptr.as_ptr()).next = node_ptr },
        }
        self.end = node_ptr;
        self.length += 1;
    }

    // get node
    // takes a mutable reference to self and an index of type i32 (integer)
    // Returns an Option object with a reference to the objec type?
        // Some(response) arm will return a reference to the requested object
        // None() arm will return a 404 you suck
    pub fn get(&mut self, index: i32) -> Option<&T> {
        self.get_ith_node(self.start, index)
    }

    // wtf?
    fn get_ith_node(&mut self, node: Option<NonNull<Node<T>>>, index: i32) -> Option<&T> {
        match node {
            None => None,
            Some(next_ptr) => match index {
                0 => Some(unsafe { &(*next_ptr.as_ptr()).val }),
                _ => self.get_ith_node(unsafe { (*next_ptr.as_ptr()).next }, index - 1),
            },
        }
    }
}

// print function
impl<T> Display for LinkedList<T>
where
    T: Display,
{
    // format list
    fn fmt(&self, f: &mut Formatter) -> fmt::Result {
        match self.start {
            Some(node) => write!(f, "{}", unsafe { node.as_ref() }),
            None => Ok(()),
        }
    }
}


// 🔼🔼🔼🔼🔼🔼🔼🔼🔼
// whats the difference????
// 🔽🔽🔽🔽🔽🔽🔽🔽🔽


impl<T> Display for Node<T>
where
    T: Display,
{
    fn fmt(&self, f: &mut Formatter) -> fmt::Result {
        match self.next {
            Some(node) => write!(f, "{}, {}", self.val, unsafe { node.as_ref() }),
            None => write!(f, "{}", self.val),
        }
    }
}


fn main() {
    println!("Main function here.");
    let mut list =  LinkedList::<String>::new();
    println!("{}", list);
    list.add("One".to_string());
    list.add("Two".to_string());
    list.add("Three".to_string());
    println!("{}", list);
    
}

// TESTS
#[cfg(test)]
mod tests {
    use super::LinkedList;

    #[test]
    fn create_numeric_list() {
        let mut list = LinkedList::<i32>::new();
        list.add(1);
        list.add(2);
        list.add(3);
        println!("Linked List is {}", list);
        assert_eq!(3, list.length);
    }

    #[test]
    fn create_string_list() {
        let mut list_str = LinkedList::<String>::new();
        list_str.add("A".to_string());
        list_str.add("B".to_string());
        list_str.add("C".to_string());
        println!("Linked List is {}", list_str);
        assert_eq!(3, list_str.length);
    }

    #[test]
    fn get_by_index_in_numeric_list() {
        let mut list = LinkedList::<i32>::new();
        list.add(1);
        list.add(2);
        println!("Linked List is {}", list);
        let retrived_item = list.get(1);
        assert!(retrived_item.is_some());
        assert_eq!(2 as i32, *retrived_item.unwrap());
    }

    #[test]
    fn get_by_index_in_string_list() {
        let mut list_str = LinkedList::<String>::new();
        list_str.add("A".to_string());
        list_str.add("B".to_string());
        println!("Linked List is {}", list_str);
        let retrived_item = list_str.get(1);
        assert!(retrived_item.is_some());
        assert_eq!("B", *retrived_item.unwrap());
    }
}