import java.util.NoSuchElementException;

public class RingBuffer {
    private double[] buffer;
    private int first, last, size;

    public RingBuffer(int capacity) {
        buffer = new double[capacity];
        first = 0;
        last = 0;
        size = 0;
    }

    public int capacity() {
        return buffer.length;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == buffer.length;
    }

    public void enqueue(double x) {
        if (isFull()) {
            throw new IllegalStateException("Ring buffer is full");
        }
        buffer[last] = x;
        last = (last + 1) % buffer.length;
        size++;
    }

    public double dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Ring buffer is empty");
        }
        double value = buffer[first];
        first = (first + 1) % buffer.length;
        size--;
        return value;
    }

    public double peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Ring buffer is empty");
        }
        return buffer[first];
    }
}