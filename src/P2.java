import java.util.Scanner;

/**
 * Assignment 2 P2.java Due August 14, 2013 login #: cs11vau
 * This program checks the validity of a credit card number
 */
public class P2 {

  public static void main( String[] args ) {
    char choice; // Repeat loop
    long creditCardNum; // Credit card number
    Scanner scan = new Scanner(System.in); // Read input from keyboard
    String inputStr = null; // Input string
    
    do
    {
      do {
      System.out.print("Enter credit card number as a long integer:  ");
      creditCardNum = scan.nextLong(); // Input card number
        if(creditCardNum < 0) {
          System.out.println("ERROR! Enter a positive number.");
        }
      } while(creditCardNum < 0);
      if ( isValid (creditCardNum) ) {
        System.out.println(creditCardNum + " is valid");
      } else {
        System.out.println(creditCardNum + " is invalid.");
      }
      
      System.out.print("Want to validate credit card number (y/n)? ");
      inputStr = scan.next(); // Read and assign to String
      System.out.print("\n");
      choice = inputStr.charAt(0); // Assign to character
    } while (choice != 'n' && choice != 'N'); // Loop while not N or n
    
    }

  /** Return true if card number is valid */
  public static boolean isValid ( long creditCardNum ) {
    final int PRE_VISA = 4; // Visa prefix
    final int PRE_MASTER = 5; // Mastercard prefix
    final int PRE_DISC = 6; // Discover prefix
    final int PRE_AMEX = 37; // Amex prefix
    
    // Check if 13-16 digits
    if ( creditCardNum > 1E16-1 || creditCardNum < 1E13 )  {
      return false;
    }
    
    // Check if valid prefix
    if (!prefixMatched(creditCardNum, PRE_VISA) && 
        !prefixMatched(creditCardNum, PRE_MASTER) &&
        !prefixMatched(creditCardNum, PRE_DISC) &&
        !prefixMatched(creditCardNum, PRE_AMEX)) {
      return false;
    }
    // Check if (sumOfDblEvenPlace + sumOfOddPlace) % 10 = 0
    int sum = sumOfDblEvenPlace(creditCardNum) + sumOfOddPlace (creditCardNum);
    return sum % 10 == 0;
  }

  /** Get the result from Step 2 */
  public static int sumOfDblEvenPlace ( long creditCardNum ) {
    int sumOfEvens = 0;
    creditCardNum /= 10;
    while(creditCardNum > 0) {
      int digit = (int) (creditCardNum % 10);
      sumOfEvens += getDigit( digit );
      creditCardNum /= 100;
    }
    return sumOfEvens;
  }

  /** Return this number if it is a single digit, otherwise, 
   * return the sum of the two digits */
  public static int getDigit ( int ccNum ) {
    int product = ccNum *2;
    return product / 10 + product % 10;
  }

  /** Return sum of odd-place digits in number */
  public static int sumOfOddPlace ( long creditCardNum ) {
    int sum = 0;
    while(creditCardNum > 0) {
      sum += creditCardNum % 10;
      creditCardNum /= 100;
    }
    return sum;
  }

  /** Return true if the digit d is a prefix for number */
  public static boolean prefixMatched ( long creditCardNum, int d ) {
    int digit = getSize(d);
    return getPrefix(creditCardNum, digit) == d;
  }

  /** Returns the number of digits in d */
  public static int getSize ( long d ) {
    int numberOfDigits = 0; // Count number of digits in card number
    
    while ( d != 0) {
      numberOfDigits++;
      d /= 10;   // Reduce by 10 (place value)
    }
    return numberOfDigits;
  }
  
  /** Return the first k number of digits from number. If the number
   * of digits in number is less than k, return number. */
  public static long getPrefix ( long creditCardNum, int k ) {
    // checks 4/5/6/37
    
    long firstkDigits;
    int numberOfDigits = getSize(creditCardNum);
    
    firstkDigits = creditCardNum / (long) Math.pow(10, numberOfDigits - k); 
    
    return firstkDigits;
  }
}
