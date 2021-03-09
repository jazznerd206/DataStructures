package Sorts;

final class Utils {
    static <T> boolean swap(T[] a, int iX, int iY) {
        T swap = a[iX];
        a[iX] = a[iY];
        a[iY] = swap;
        return true;
    }
}
