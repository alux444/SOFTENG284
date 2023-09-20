import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pattern {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pattern = br.readLine();
        String text = br.readLine();
        System.out.println(RabinKarp.rabinKarp(pattern, text));
    }

    public static class RabinKarp {
        private static final int base = 256;
        private static final int mod = 101;

        public static int rabinKarp(String pattern, String text) {
            int len = text.length();
            int patternLen = pattern.length();
            int count = 0;

            int pHash = 0;
            int tHash = 0;
            int e = 1;

            // get exponential max
            for (int i = 0; i < patternLen - 1; i++) {
                e = (e * base) % mod;
            }

            // get first hashes
            for (int i = 0; i < patternLen; i++) {
                pHash = (pHash * base + pattern.charAt(i)) % mod;
                tHash = (tHash * base + pattern.charAt(i)) % mod;
            }

            for (int i = 0; i <= len - patternLen; i++) {

                if (pHash == tHash) {
                    boolean same = true;
                    // check for same letters if same hash
                    for (int j = 0; j < patternLen; j++) {
                        if (text.charAt(i + j) != pattern.charAt(j)) {
                            same = false;
                            break;
                        }
                    }

                    if (same)
                        count++;
                }

                if (i < len - patternLen) {
                    tHash = (base * (tHash - text.charAt(i) * e) + text.charAt(i + patternLen)) % mod;

                    if (tHash < 0) {
                        tHash += mod;
                    }
                }
            }

            return count;
        }
    }

}