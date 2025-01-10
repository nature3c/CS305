public class GuitarString {
    private RingBuffer buffer;
    private static final double DECAY = 0.996; //energy decay factor

    //create a guitar string of the given frequency
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(44100 / frequency);
        buffer = new RingBuffer(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.enqueue(0.0); //initialize with zeros
        }
    }

    //create a guitar string with the given initial buffer values
    public GuitarString(double[] init) {
        buffer = new RingBuffer(init.length);
        for (double v : init) {
            buffer.enqueue(v);
        }
    }

    //pluck the guitar string by replacing the buffer with white noise
    public void pluck() {
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.dequeue(); //remove old values
            buffer.enqueue(Math.random() - 0.5); //add random values between -0.5 and 0.5
        }
    }

    //advance the simulation one time step by applying the Karplus-Strong update
    public void tic() {
        double first = buffer.dequeue();
        double second = buffer.peek();
        double newSample = DECAY * 0.5 * (first + second);
        buffer.enqueue(newSample);
    }

    //return the current sample
    public double sample() {
        return buffer.peek();
    }
}