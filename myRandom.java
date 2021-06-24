import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

public class myRandom {
	
	// This is the internal state variable
	long state;
	
	public myRandom(long seed) {
		state = seed;
	}
	
	// getRand() should return a pseudorandom long
	// This is done by fetching the state, shifting it right 12,
	// left 25, and then right 27. This is then saved back to the state variable.
	// Then, we multiply it by 2685821657736338717 (add L on the end for long)
	// And return the resultant value.
	public long getRand() {
		long x = state;
		x ^= x >> 12;
		x ^= x << 25;
		x ^= x >> 27;
		state = x;
		return x *2685821657736338717L;
	}

	// Testing section
	public static void main(String[] args) {
		// First, we create the random object with seed = "1234"
		myRandom newRand = new myRandom(1234);
		// Then we generate an integer - this should return -4875686705675355890
		System.out.println(newRand.getRand());
		// This should return 5609927630774915935
		System.out.println(newRand.getRand());
		// And this should return 7579251470305882622
		System.out.println(newRand.getRand());
		// Create output files for our generator and the default Java Generator
		try {
			// Write a file with 1000000 longs from our Xorshift* generator
			Writer fileWriter = new FileWriter("myRandOutput.txt", false);
			for(int x = 0; x < 1000000; x++) {
				fileWriter.write(String.valueOf(newRand.getRand()));
			}
			fileWriter.close();
			Random rand = new Random();
			fileWriter = new FileWriter("javaRandOutput.txt", false);
			for(int x = 0; x < 1000000; x++) {
				fileWriter.write(String.valueOf(rand.nextLong()));
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
