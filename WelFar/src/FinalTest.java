import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FinalTest extends Thread {
	public void run() {
		System.out.println("stsrt");
		this.stop();
		System.out.println("stop");
	}

	public static void main(String[] args) {
		FinalTest finalTest = new FinalTest();
		finalTest.start();
	}
}
