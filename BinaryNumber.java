// Student Name - Sudarshana Sarma

// using little-endian

public class BinaryNumber {
    private int [] data;
    private boolean overflow;
    public BinaryNumber (int length) {
        this.data = new int[length];
    }


    // Constructor BinaryNumber taking a string input
    public BinaryNumber (String str) {
        this.data = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            this.data[i] = str.charAt(i) - '0';
        }
    }

    // getting the length of the data
    public int getLength() {
        return this.data.length;
    }

    // if the input index is out of range, it throws an error message otherwise returns the digit at the index
    public int getDigit(int index) {
        if (index < 0 || index >= data.length) {
            System.out.println("Invalid index range");
            return -1;
        }
        return this.data[index];
    }


    // shifting by adding zeroes to the start
    public void shiftR(int amount) {
        if (amount < 0 ) {
            System.out.println("Cannot shift negative amount");
            return;
        }
        int [] newData = new int[data.length + amount];
        for (int i = newData.length - 1, j = data.length - 1; j >= 0; i--, j--) {
            newData[i] = data[j];
        }
        data = newData;
    }

    // Adding binary number
    public void add(BinaryNumber binaryNumber) {
        if (this.data.length != binaryNumber.getLength()) {
            System.out.println("The provided binary number length does not match, not performing addition");
            return;
        }
        int carry = 0;
        for (int i = 0; i < data.length; i++) {
            int sum = carry + data[i] + binaryNumber.data[i];
            if (sum > 1) carry = 1;
            data[i] = sum % 2;
        }
        overflow = carry == 1;
    }
    //Convert to string
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i: data) {
            sb.append(i);
        }
        return sb.toString();
    }

    //calculate decimal value 
    public int toDecimal() {
        int sum = 0;
        int pow = 1;
        for (int i = 0; i < data.length; i++) {
            sum += data[i] * pow;
            pow *= 2;
        }
        return sum;
    }

    public void clearOverflow() {
        this.overflow = false;
    }
}