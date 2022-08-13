import java.util.Arrays;
import java.util.Scanner;

public class Test1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter number k:");
        int k = in.nextInt();
        System.out.println("Please enter number n:");
        int n = in.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Please enter number");
            numbers[i] = in.nextInt();
        }

        int[] results = findSumPair(numbers, k);
        System.out.println("results: " + Arrays.toString(results));

    }

    public static int[] findSumPair(int[] numbers, int k) {
        int[] results = new int[2];
        int size = numbers.length;
        boolean shouldStop = false;
        int i = 0, j = 0;
        while (i < size - 1 && !shouldStop) {
            j = i + 1;
            while (j < size && !shouldStop) {
                if (numbers[i] + numbers[j] != k) {
                    j++;
                } else {
                    shouldStop = true;
                }
            }
            if (j == size) {
                i++;
            }
        }
        results[0] = i;
        results[1] = j;
        return results;

    }
}
