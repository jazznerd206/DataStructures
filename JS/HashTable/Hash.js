// HASH FOR STRINGS
function hash(key, arrayLen) {
    let total = 0;
    for (i = 0; i < arrayLen; i++) {
        let char = key[i];
        let value = char.charCodeAt(0) - 'a'.charCodeAt(0);
        total = (total + value) % arrayLen
    } 
    return total;
}

// console.log(hash('pink', 4));
// console.log(hash('abcdefghijklmnopqrstuvwxyz', 26));

// not good because:
// only hashes strings
// non constant time - means the function gets more expensive in time complexity as the data grows
// could be more random


// IMPROVEMENT 1
// add a maximum length of 100
// add a prime number to increase distribution of hash
    // reduces collisions of map placements after factoring
function hash1(key, arrayLen) {
    let total = 7;
    let WEIRD_PRIME = 31;
    for (let i = 0; i < Math.min(key.length, 100); i++) {
        let char = key[i];
        let value = char.charCodeAt(0) - 'a'.charCodeAt(0);
        total += (total * WEIRD_PRIME + value) % arrayLen
    } 
    return total;
}

console.log(hash1('supercalafragilisticexpialadocious', 31))