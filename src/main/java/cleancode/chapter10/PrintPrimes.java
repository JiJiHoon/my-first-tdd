package cleancode.chapter10;

public class PrintPrimes {
    public static void main(String[] args) {
        final int M = 1000;
        final int RR = 50;
        final int CC = 4;
        int P[] = new int[M + 1];

        generatePrimeNumbers(M, P);
        printResult(M, RR, CC, P);
    }

    private static void generatePrimeNumbers(int M, int[] P) {
        final int ORDMAX = 30;
        int MULT[] = new int[ORDMAX + 1];
        boolean JPRIME;
        int N;
        int SQUARE;
        int ORD;
        int J;
        int K;
        J = 1;
        K = 1;
        P[1] = 2;
        ORD = 2;
        SQUARE = 9;

        while (K < M) {
            do {
                J = J + 2;
                if (J == SQUARE) {
                    ORD = ORD + 1;
                    SQUARE = P[ORD] * P[ORD];
                    MULT[ORD - 1] = J;
                }
                N = 2;
                JPRIME = true;
                while (N < ORD && JPRIME) {
                    while (MULT[N] < J)
                        MULT[N] = MULT[N] + P[N] + P[N];
                    if (MULT[N] == J)
                        JPRIME = false;
                    N = N + 1;
                }
            } while (!JPRIME);
            K = K + 1;
            P[K] = J;
        }
    }

    private static void printResult(int M, int RR, int CC, int[] P) {
        int PAGENUMBER;
        int C;
        int PAGEOFFSET;
        int ROWOFFSET;
        PAGENUMBER = 1;
        PAGEOFFSET = 1;
        while (PAGEOFFSET <= M) {
            System.out.println("The First " + M + " Prime Numbers --- Page " + PAGENUMBER);
            System.out.println("");
            for (ROWOFFSET = PAGEOFFSET; ROWOFFSET < PAGEOFFSET + RR; ROWOFFSET++) {
                for (C = 0; C < CC; C++)
                    if (ROWOFFSET + C * RR <= M)
                        System.out.format("%10d", P[ROWOFFSET + C * RR]);
                System.out.println("");
            }
            System.out.println("\f");
            PAGENUMBER = PAGENUMBER + 1;
            PAGEOFFSET = PAGEOFFSET + RR * CC;
        }
    }
}
