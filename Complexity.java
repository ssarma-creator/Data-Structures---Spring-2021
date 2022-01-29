// Student Name - Sudarshana Sarma

public class Complexity {
	
//Method to implement time complexity O(N^2)
	public static void method1(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}
// Method to implement time complexity O(N^3)
	public static void method2(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					System.out.println("Operation " + counter);
					counter++;
				}
			}
		}
	}

// Method to implement time complexity O(log n)
	public static void method3(int n) {
		int counter = 1;
		for (int i = 1; i < n; i = i * 2) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}

// Method to implement time complexity O(nlogn)
	public static void method4(int n) {
		int counter = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n; j *= 2) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}

//Method to implement time complexity O(log logn)
	public static void method5(int n) {
		int counter = 1;
		for (int i = 1, p = 1; p < n; i *= 2, p = (int) Math.pow(2, i)) {
            System.out.println("Operation " + counter);
			counter++;
		}
	}

//Method to implement time complexity O(2^n)
	public static int method6(int n) {
		if (n <= 1) {
			return n;
		}
		return method6(n - 2) + method6(n - 1);
	}
}

