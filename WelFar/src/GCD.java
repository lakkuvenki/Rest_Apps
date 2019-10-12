// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class GCD {
	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	public static int generalizedGCD(int arr[]) {
		int gcd = 1;
		int index = 2;
		if (arr.length == 1) {
			gcd = arr[1];
		}
		if (arr.length > 1) {
			gcd = gcd(arr[0], arr[1]);
		}
		while (index < arr.length) {
			gcd = gcd(gcd, arr[index]);
			index++;
		}
		return gcd;
	}

	// METHOD SIGNATURE ENDS
	public static int gcd(int num1, int num2) {
		int temp = 0;
		while (num2 != 0) {
			temp = num2;
			num2 = num1 % num2;
			num1 = temp;
		}
		num1 = num1 < 0 ? num1 * (-1) : num1;
		return num1;
	}

	public static void main(String[] args) {
		//int[] numbers = { 2,4,8,10 };
		int[] numbers = { 3,2,5,8,7 };
		System.out.println("*** Greatest Common Divisor ***");
		System.out.println(generalizedGCD(numbers));
	}
}