import java.util.Scanner;

public class AliceBob {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();

        for (int t = 0; t < testCases; t++) {
            long n = scanner.nextLong();
            long[] a = new long[(int) n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextLong();
            }
            //if n is even,Alice will n/2 chances,if n is odd ,Alice will have n/2+1 chances
            //Check whether n is Even or not
            long mov = (n / 2);
            //If n is odd
            if (n % 2 != 0) mov++;

            long now = mov;

            long even = 0, odd = 0;
            for (long value : a) {
                if (value % 2 == 0) {
                    even++;
                } else {
                    odd++;
                }
            }

            boolean found = false;
            while (true) {
                if (now < 0) {
                    System.out.println("Bob");
                    found = true;
                }

                if (found) break;

                //Alice Conditions
                //1.X even numbers,0 odd Numbers
                //2.X-2 even numbers,2 odd Numbers
                //.....
                //X-n even numbers,n odd numbers

                long val1 = now + now - 1;
                long val2 = mov - now + mov - now;

                if (even >= val1 && odd >= val2) {
                    System.out.println("Alice");
                    found = true;
                }

                if (found) break;

                val1 = now + now;
                val2 = mov - now + mov - now - 1;

                if (even >= val1 && odd >= val2) {
                    System.out.println("Alice");
                    found = true;
                }

                if (found) break;

                now = now - 2;
            }
        }

        scanner.close();
    }
}

/*Example
input
4
3
1 3 5
4
1 3 5 7
4
1 2 3 4
4
10 20 30 40
output
Alice
Alice
Bob
Alice
 */

