/* Sean Michael
 * CS 211
 * 20-Oct-2020
 * implements new functionality in the arrayintlist class
 * exercises labeled as 'Ex##'
 */
// Class ArrayIntList can be used to store a list of integers.

import java.util.*;

public class ArrayIntList {

    private int[] elementData; // list of integers
    private int size;          // current number of elements in the list

    public static final int DEFAULT_CAPACITY = 100;

    // pre : capacity >= 0 (throws IllegalArgumentException if not)
    // post: constructs an empty list with the given capacity
    public ArrayIntList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        elementData = new int[capacity];
        size = 0;
    }

    // post: constructs an empty list of default capacity
    public ArrayIntList() {
        this(DEFAULT_CAPACITY);
    }

    // post: returns the current number of elements in the list
    public int size() {
        return size;
    }
    
    /* Ex05
     * Write a method called runningTotal that returns a new ArrayIntList
     * that contains a running total of the original list. In other words,
     * the ith value in the new list should store the sum of elements 0 through i 
     * of the original list. For example, given a variable list that stores 
     * [2, 3, 5, 4, 7, 15, 20, 7], consider what happens when the following 
     * call is made: ArrayIntList list2 = list.runningTotal(); The variable 
     * list2 should store [2, 5, 10, 14, 21, 36, 56, 63]. The original list 
     * should not be changed by the method. If the original list is empty, 
     * the result should be an empty list. The new list should have the 
     * same capacity as the original. Remember that there is a list 
     * constructor that accepts a capacity as a parameter.
     */
    public ArrayIntList runningTotal() {
    	int prev = this.get(0);
    	ArrayIntList list = new ArrayIntList(this.size);
    	list.add(prev);
    	for (int i = 1; i < this.size; i++) {
    		prev += this.get(i);
    		list.add(prev);
    	}
    	return list;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: returns the integer at the given index in the list
    public int get(int index) {
        checkIndex(index);
        return elementData[index];
    }
    
    /* Ex08
     * Write a method called count that accepts an element value as a 
     * parameter and returns the number of occurrences of that value 
     * in the list. For example, suppose a variable named list stores 
     * [2, -3, 2, 0, 5, 2, 2, 6]. A call of list.count(2) should 
     * return 4 because there are four occurrences of that value in the list
     */
    
    public int count(int input) {
    	int count = 0;
    	for (int num : this.elementData) {
    		if (num == input) count++;
    	}
    	return count;
    }

    // post: creates a comma-separated, bracketed version of the list
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + elementData[0];
            for (int i = 1; i < size; i++) {
                result += ", " + elementData[i];
            }
            result += "]";
            return result;
        }
    }
    /* Ex01
     * Write a method called lastIndexOf that accepts an integer as a parameter 
     * and returns the index in the list of the last occurrence of that value, 
     * or -1 if the value is not found in the list. For example, if the list 
     * stores [1, 18, 2, 7, 18, 39, 18, 40], then the last index of 18 is 6 
     * and the last index of 3 is -1.
     */
    public int lastIndexOf(int num) {
    	for (int i = size-1; i >= 0; i--) {
    		if (elementData[i] == num) return i;
    	}
    	return -1;
    }
    // post : returns the position of the first occurrence of the given
    //        value (-1 if not found)
    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == value) {
                return i;
            }
        }
        return -1;
    }
    /* Ex02
     * Write a method called indexOfSubList that accepts another list 
     * L as a parameter and returns the starting index of where L first 
     * appears in this list, or -1 if it is not found. All elements of 
     * L must appear in sequence and in the same order. For example, 
     * if variables called list1 and list2 store [11, -7, 3, 42, 0, 14]
     * and [3, 42, 0], respectively, the call of 
     * list1.indexOfSubList(list2) should return 2.
     */
    public int indexOfSubList(List<Integer> list) {
    	for (int i = 0; i < size; i++) {
    		if (this.get(i) == (int) list.get(0)) {
    			boolean found = true;
    			for (int j = 0; j < list.size(); j++) {
    				if (this.get(i+j) != (int) list.get(j)) {
    					found = false;
    					break;
    				}
    			}
    			if (found) return i;
    		}
    	}
    	return -1;
    }

    // post: returns true if list is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    // post: returns true if the given value is contained in the list,
    //       false otherwise
    public boolean contains(int value) {
        return indexOf(value) >= 0;
    }

    // pre : size() < capacity (throws IllegalStateException if not)
    // post: appends the given value to the end of the list
    public void add(int value) {
        ensureCapacity(size + 1); //this public method throws the exception
        elementData[size] = value;
        size++;
    }

    // pre : size() < capacity (throws IllegalStateException if not) &&
    //       0 <= index <= size() (throws IndexOutOfBoundsException if not)
    // post: inserts the given value at the given index, shifting subsequent
    //       values right
    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        ensureCapacity(size + 1); //this public method throws the exception
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: removes value at the given index, shifting subsequent values left
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
    }
    
    /* Ex11
     * Write a method called removeLast that removes and returns the last value 
     * from a list of integers. For example, if a variable called list stores 
     * [8, 17, 42, 3, 8], a call of list.removeLast(); should return 8 and 
     * change the list’s state to [8, 17, 42, 3]. The next call would return 3 
     * and remove 3 from the list, and so on. If the list is empty, 
     * throw a NoSuchElementException.
     */
    public int removeLast() {
    	if (this.isEmpty()) throw new NoSuchElementException();
    	int last = this.get(size-1);
    	this.remove(size-1);
    	return last;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: replaces the integer at the given index with the given value
    public void set(int index, int value) {
        checkIndex(index);
        elementData[index] = value;
    }

    // post: list is empty
    public void clear() {
        size = 0;
    }

    // post: ensures that the underlying array has the given capacity; if not,
    //       the size is doubled (or more if given capacity is even larger)
    private void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            int newCapacity = elementData.length * 2 + 1;
            if (capacity > newCapacity) {
                newCapacity = capacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    // post: throws an IndexOutOfBoundsException if the given index is
    //       not a legal index of the current list
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }
}
