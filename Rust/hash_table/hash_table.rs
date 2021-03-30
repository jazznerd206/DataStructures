// hashmap implemented with quadratic probing

use std::collections::HashMap;

#[derive(Hash, Eq, PartialEq, Debug)]
struct Scale {
    tonality: String,
    mode: String,
}

impl Scale {
    fn new(tonality: &str, mode: &str) -> Scale {
        Scale { tonality: tonality.to_string(), mode: mode.to_string() }
    }
}

fn main() {
    
    let mut scales = HashMap<&str, u8>::new;
    
    scales.insert(Scale::new("major", "ionian"), 1);
    scales.insert(Scale::new("minor", "dorian"), 2);
    scales.insert(Scale::new("minor", "phrygian"), 3);
    scales.insert(Scale::new("major", "lydian"), 4);
    scales.insert(Scale::new("major", "mixolydian"), 5);
    scales.insert(Scale::new("minor", "aeolian"), 6);
    scales.insert(Scale::new("diminished", "locrian"), 7);
    
    for (tonality, mode) in &scales {
        println!("{:?}, roman numeral {}", scale, mode);
    }
}
