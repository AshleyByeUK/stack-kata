package stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
    Stack stack;

    @Before
    public void setUp() {
        stack = BoundedStack.make(2);
    }

    @Test
    public void newlyCreatedStack_ShouldBeEmpty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.getSize());
    }

    @Test
    public void afterOnePush_StackSizeShouldBeOne() {
        stack.push(1);
        assertEquals(1, stack.getSize());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void afterOnePushAndOnePop_StackShouldBeEmpty() {
        stack.push(1);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test(expected = BoundedStack.Overflow.class)
    public void whenPushedPastLimit_StackOverflows() {
        stack.push(1);
        stack.push(1);
        stack.push(1);
    }

    @Test(expected = BoundedStack.Underflow.class)
    public void whenEmptyStackIsPopped_ShouldThrowUnderflow() {
        stack.pop();
    }

    @Test
    public void whenOneIsPushed_OneIsPopped() {
        stack.push(1);
        assertEquals(1, stack.pop());
    }

    @Test
    public void WhenOneAndTwoArePushed_TwoAndOneArePopped() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test(expected = BoundedStack.IllegalCapacity.class)
    public void whenCreatingStackWithNegativeSize_ShouldThrowIllegalCapacity() {
        BoundedStack.make(-1);
    }

    @Test(expected = BoundedStack.Overflow.class)
    public void whenCreatingStackWithZeroCapacity_AnyPushShouldOverflow() {
        Stack stack = BoundedStack.make(0);
        stack.push(1);
    }

    @Test
    public void whenOneIsPushed_OneIsOnTop() {
        stack.push(1);
        assertEquals(1, stack.top());
    }

    @Test(expected = Stack.Empty.class)
    public void whenStackIsEmpty_TopThrowsEmpty() {
        stack.top();
    }

    @Test(expected = Stack.Empty.class)
    public void withZeroCapacityStack_TopThrowsEmpty() {
        Stack stack = BoundedStack.make(0);
        stack.top();
    }

    @Test
    public void givenStackWithOneTwoPushed_FindOneAndTwo() {
        stack.push(1);
        stack.push(2);
        int oneIndex = stack.find(1);
        int twoIndex = stack.find(2);
        assertEquals(1, oneIndex);
        assertEquals(0, twoIndex);
    }

    @Test
    public void givenStackWithNoTwo_FindTwoShouldReturnNull() {
        assertNull(stack.find(2));
    }
}
