package org.example;

import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        //System.out.println(second(100) + " " + second(1000) + " " + second(10000) + " " + second(100000) + " " + second(1000000));
        //System.out.println(third(100) + " " + third(1000) + " " + third(10000) + " " + third(100000) + " " + third(1000000));
        //System.out.println("Аналитическое решение - 0.2113");
        // int n = 120;
        //int k = 50;
        //int m = 10;
        //System.out.println("Аналитическое решение " + analiticFourth(n,k,m) + "\nпри n = " + n + " k = " + k + " m = " + m);
        //System.out.println(fourth(100,n,k,m) + "\n" + fourth(1000,n,k,m) + "\n" + fourth(10000,n,k,m) + "\n" +
        //fourth(100000,n,k,m) + "\n" + fourth(1000000,n,k,m));
        //System.out.println(fifth(100) + " " + fifth(1000) + " " + fifth(10000) + " " + fifth(100000) + " " + fifth(1000000));
        //System.out.println("Решение аналитически: 2 группа\n" + seventh(100) + " " + seventh(1000) + " " + seventh(10000) + " " + seventh(100000) + " " + seventh(1000000));
        //System.out.printf("%.2f %.3f %.4f %.5f %.6f\nРешение аналитически: 0.665510", eighth(100), eighth(1000), eighth(10000), eighth(100000), eighth(1000000));
        //System.out.printf("%-10s %-10s%n" + nineth(100) + nineth(1000) + nineth(10000) + nineth(100000) + nineth(1000000) + "\nРешение аналитически:\na)0.0485735 б)0.205099", "a)", "б)");
        //!!!System.out.println(tenth(100,0.4,50) + " " + tenth(1000,0.4,50) +" " + tenth(10000,0.4,50)+ " " + tenth(100000,0.4,50)+ " " + tenth(1000000,0.4,50));
        System.out.println(eleventh(100) + " " + eleventh(1000) +" " + eleventh(10000)+ " " + eleventh(100000)+ " " + eleventh(1000000));
    }

    public static double second(int experiments) {
        double successful = 0;
        for (int i = 0; i < experiments; i++) {
            int a, b, c;
            do {
                a = (int) (Math.random() * 10);
                b = (int) (Math.random() * 10);
                c = (int) (Math.random() * 10);
            } while (a == b || a == c || b == c);
            int[] arr = {a, b, c};
            Arrays.sort(arr);

            if (arr[0] == arr[1] - 1 && arr[1] == arr[2] - 1) successful += 1;
        }

        return successful / experiments;
    }

    public static double third(int experiments) {
        double successful = 0.0;
        double earthRadius = 6356.7523;
        double r1 = earthRadius * Math.sin(Math.toRadians(30));
        double areaS60toN60 = Math.PI * (earthRadius + r1) * earthRadius * 2;
        double r2 = earthRadius * Math.sin(Math.toRadians(60));
        double l2 = earthRadius - Math.sqrt(Math.pow(earthRadius - r2, 2) + Math.pow(earthRadius * Math.cos(Math.toRadians(60)), 2));
        double areaN30toN60 = Math.PI * (r2 + r1) * l2;
        for (int i = 0; i < experiments; i++) {
            double position = Math.random() * areaS60toN60;
            if (position <= areaN30toN60) successful++;
        }
        return successful / experiments;
        //Аналитическое решение - 0.2113
    }

    public static double fourth(int experiments, int n, int k, int m) {
        double successful = 0.0;
        for (int i = 0; i < experiments; i++) {
            int newN = n;
            for (int j = 0; j < k; j++) {
                int r = (int) (Math.random() * newN) + 1;
                newN--;
                if (m >= r) {
                    successful++;
                    break;
                }
            }
        }
        return successful / experiments;
    }

    public static double analiticFourth(int n, int k, int m) {
        double one = 1;
        for (int i = 2; i <= n - m; i++) {
            one *= i;
        }
        double two = 1;
        for (int i = 2; i <= n - k; i++) {
            two *= i;
        }
        double three = 1;
        for (int i = 2; i <= n - m - k; i++) {
            three *= i;
        }
        double four = 1;
        for (int i = 2; i <= n; i++) {
            four *= i;
        }
        return 1 - (one * two / three / four);
    }

    public static String fifth(int experiments) {
        double successfula = 0.0;
        double successfulb = 0.0;
        for (int i = 0; i < experiments; i++) {
            int a = (int) (Math.random() * Integer.MAX_VALUE);
            if (a % 2 != 0 && a % 3 != 0) successfula++;
            if (a % 2 != 0 || a % 3 != 0) successfulb++;
        }
        return "a) " + successfula / experiments + " b) " + successfulb / experiments + "\n";
    }

    public static double sixth(int experiments) {
        int[] pocketRight = {20, 20, 20, 3, 3, 3, 3};
        double successful = 0;
        Random random = new Random();
        for (int i = 0; i < experiments; i++) {
            int[] moveFromLeft = IntStream.generate(() -> pocketRight[random.nextInt(pocketRight.length)]) //реализация в виде массива "переноса" из правого кармана случайных 5 монет
                    .limit(5)
                    .toArray();
            int[] pocketLeft = {20, 20, 20, 20, 20, 20, 3, 3, 3};
            int[] newLeftPocket = IntStream.concat(Arrays.stream(moveFromLeft), Arrays.stream(pocketLeft)).toArray(); //добавление случайных монет в левый карман
            if (newLeftPocket[random.nextInt(14)] == 20) successful++;
        }
        return successful / experiments;
    }

    public static int seventh(int experiments) {
        double[] groups = {0,0,0,0};
        for (int i = 0; i < experiments; i++) {
            Random random = new Random();
            double[] p = {0.8, 0.8, 0.8, 0.8, 0.8, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.6, 0.6, 0.6, 0.6, 0.5, 0.5};
            int[] n = {1,1,1,1,1,2,2,2,2,2,2,2,3,3,3,3,4,4};
            int failedShooter = random.nextInt(18);
            groups[n[failedShooter] - 1] += (1 - p[failedShooter]);
        }
        double max = 0;
        int result = 0;
        for (int i = 0; i < 4; i++) {
            if (groups[i] > max) {
                result = i + 1;
                max = groups[i];
            }
        }
        return result;
        //Решение аналитически: 2 группа
    }

    public static double eighth(int experiments){
        double[] probs = {1.0/2.0, 2.0/3.0};
        int nReal = 116;
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        int[][] nStrikes = new int[2][experiments];
        for (int i = 0; i < 2; i++) {
            BinomialDistribution binomial = new BinomialDistribution(200, probs[i]);
            for (int k = 0; k < experiments; k++) {
                nStrikes[i][k] = binomial.sample();
            }
        }
        int[] nStrikesGood = new int[2];
        int[] choices = new int[experiments];
        for (int i = 0; i < experiments; i++) {
            choices[i] = randomDataGenerator.nextInt(0, 1);
        }
        for (int i = 0; i < 2; i++) {
            nStrikesGood[i] = 0;
            for (int k = 0; k < experiments; k++) {
                if (nStrikes[i][k] == nReal && choices[k] == i) {
                    nStrikesGood[i]++;
                }
            }
        }
        return (double) nStrikesGood[0] / (nStrikesGood[0] + nStrikesGood[1]);
    }
    public static String nineth(int experiments) {
        int n = 10;
        int m = 30;
        int result;
        double successfulA = 0.0;
        double successfulB = 0.0;
        for (int i = 0; i < experiments; i++) {
            result  = 0;
            Random random = new Random();
            for (int j = 0; j < n; j++) {
                result+= random.nextInt(6) + 1;
            }
            if (result == m) successfulA++;
            if (result <= m) successfulB++;
        }
        return String.format("%.6f   %.6f\n", successfulA / experiments, successfulB / experiments);
        //Решение аналитически: a)0.0485735    б)0.205099
    }
    public static double tenth(int experiments, double p, int r){
        double successful = 0.0;
        int newR = r;
        for (int i = 0; i <experiments; i++){
            Random random = new Random();
            double trying = random.nextDouble();
            if (trying <= p) {
                newR--;
                successful++;
            }
            if (newR == 0) return successful/i;
        }
        return successful/experiments;
    }

    public static int eleventh(int experiments) {
        int[] weightsSet1 = {1, 1, 2, 2, 1, 2, 2, 3, 3, 1};
        int[] weightsSet2 = {1, 1, 1, 1, 2, 2, 2, 3, 3, 1};
        int[] weightsSet3 = {1, 1, 2, 3, 1, 2, 2, 3, 4, 1};
        int needed1 = 0;
        int needed2 = 0;
        int needed3 = 0;
        for (int i = 0; i < experiments; i++) {
            int weightToWeigh = (int) (Math.random() * 10) + 1;
            int[] dp1 = new int[weightToWeigh + 1];
            int[] dp2 = new int[weightToWeigh + 1];
            int[] dp3 = new int[weightToWeigh + 1];
            Arrays.fill(dp1, Integer.MAX_VALUE);
            Arrays.fill(dp2, Integer.MAX_VALUE);
            Arrays.fill(dp3, Integer.MAX_VALUE);
            dp1[0] = 0;
            dp2[0] = 0;
            dp3[0] = 0;
            for (int i1 = 0; i1 < 5; i1++) {
                for (int j = weightsSet1[i1]; j <= weightToWeigh; j++) {
                    dp1[j] = Math.min(dp1[j], dp1[j - weightsSet1[i1]] + 1);
                }
            }
            for (int i2 = 0; i2 < 5; i2++) {
                for (int j = weightsSet2[i2]; j <= weightToWeigh; j++) {
                    dp2[j] = Math.min(dp2[j], dp2[j - weightsSet2[i2]] + 1);
                }
            }
            for (int i3 = 0; i3 < 5; i3++) {
                for (int j = weightsSet3[i3]; j <= weightToWeigh; j++) {
                    dp3[j] = Math.min(dp3[j], dp3[j - weightsSet3[i3]] + 1);
                }
            }
            System.out.println(Arrays.toString(dp1));
            System.out.println(Arrays.toString(dp2));
            System.out.println(Arrays.toString(dp3));
        }
        return 1;
    }
}
