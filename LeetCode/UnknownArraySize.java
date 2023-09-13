// package LeetCode;

// import java.io.*;
// import java.util.*;

// Search in a sorted array of unknown-size
public class UnknownArraySize {
    public static void main(String[] args) {
        
    }

    // public int search(ArrayReader reader, int target) {
    //     if(reader.get(0) == Integer.MAX_VALUE) return -1;

    //     int low = 0;
    //     int high = 1;

    //     while (reader.get(high) < target) {
    //         low = high;
    //         high = high * 2;
    //     }

    //     return BinarySearch(reader, target, low, high);
    // }

    // private int BinarySearch(ArrayReader reader, int target, int low, int high) {
    //     while (low <= high) {
    //         int mid = low + (high - low) / 2;

    //         if (reader.get(mid) == target) {
    //             return mid;
    //         } else if (reader.get(mid) < target) {
    //             low = mid + 1;
    //         } else {
    //             high = mid - 1;
    //         }
    //     }
    //     return -1;
    // }
}