package stack;

public interface Stack {
    boolean isEmpty();

    int getSize();

    void push(int element);

    int pop();

    int top();

    Integer find(int element);

    class IllegalCapacity extends RuntimeException {
    }

    class Empty extends RuntimeException{
    }

    public static class Overflow extends RuntimeException {
    }

    public static class Underflow extends RuntimeException {
    }
}
