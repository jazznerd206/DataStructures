function FindPrimes(n) {
    var primes = [];
    for( var i = 2;  n > 0;  i++) {
        if(isPrime(i)) {
            primes.push(i);
            --n;
        }
    }
    return primes;
}

function isPrime(n) {
    var max = Math.sqrt(n);
    for (i = 2; i <= max; i++) {
        if (n % i === 0)
            return false;
    }
    return true;
}

console.log(`FindPrimes();`, FindPrimes(100));