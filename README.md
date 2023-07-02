# Problem Statement
Suppose we are given a number n based on which a program creates a series 0102030...0n. There are three threads t1, t2, 
and t3 which print a specific type of number from the series. t1 only prints zeros, t2 prints odd numbers and t3 prints
even numbers from the series. The code for the class is given below
```java
public class PrintNumberSeries {
    private int n;
    
    public PrintNumberSeries(int n) {
        this.n = n;
    }
    
    public void printZero() {
        
    }
    
    public void printOdd() {
        
    }
    
    public void printEven() {
        
    }
}
```
You are required to write a program which takes a user input n and outputs the number series using three threads. The 
three threads work together to print zeros, odd numbers, and even numbers. The threads should be synchronized so that 
the functions `printZero()`, `printOdd()`, and `printEven()` are executed in order.

## Solution Approach
1. Use three semaphores: `zeroSemaphore` with 1 permit, `oddSemaphore` with 1 permit, `evenSemaphore` with 0 permits.
2. `printZero()` acquires the `zeroSemaphore` once, prints `0`, then releases both `oddSemaphore` and `evenSemaphore`
once each.
3. `printOdd()` acquires the `oddSemaphore` twice, prints the count, increments it, then releases `zeroSemaphore` once.
4. `printEven()` acquires the `evenSemaphore` twice, prints the count, increments it, then releases the `zeroSemaphore`
once.
5. when `count` becomes equal to `n`, the while loops terminate in all three methods. `printZero()` releases both 
`oddSemaphore` and `evenSemaphore` twice as a guarantee that other threads will surely be able to acquire the
semaphores.
6. In same situation, both `printOdd()` and `printEven()` release `zeroSemaphore` and the opposite semaphore once.

## Sample Output
```
Enter the last number in the series
17
010203040506070809010011012013014015016017
```
```
Enter the last number in the series
20
010203040506070809010011012013014015016017018019020
```