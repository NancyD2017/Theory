package org.example;

import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Какую задачу Вы хотите посмотреть (введите число от 1 до 15)?");
        int choice = scanner.nextInt();
        switch (choice) {
            case 2 ->
                System.out.println(second(100) + " " + second(1000) + " " + second(10000) + " " + second(100000) + " " + second(1000000));
            case 3 ->
                System.out.println(third(100) + " " + third(1000) + " " + third(10000) + " " + third(100000) + " " + third(1000000) + "\nАналитическое решение - 0.2113");
            case 4 -> {
                int n = 120;
                int k = 50;
                int m = 10;
                System.out.println("Аналитическое решение " + analiticFourth(n, k, m) + "\nпри n = " + n + " k = " + k + " m = " + m);
                System.out.println(fourth(100, n, k, m) + "\n" + fourth(1000, n, k, m) + "\n" + fourth(10000, n, k, m) + "\n" + fourth(100000, n, k, m) + "\n" + fourth(1000000, n, k, m));
            }
            case 5 ->
            System.out.println(fifth(100) + " " + fifth(1000) + " " + fifth(10000) + " " + fifth(100000) + " " + fifth(1000000));
            case 6 ->
                    System.out.println(sixth(100) + " " + sixth(1000) + " " + sixth(10000) + " " + sixth(100000) + " " + sixth(1000000));
            case 7 ->
            System.out.println("Решение аналитически: 2 группа\n" + seventh(100) + " " + seventh(1000) + " " + seventh(10000) + " " + seventh(100000) + " " + seventh(1000000));
            case 8 ->
                    System.out.printf("%.2f %.3f %.4f %.5f %.6f\nРешение аналитически: 0.665510", eighth(100), eighth(1000), eighth(10000), eighth(100000), eighth(1000000));
            case 9 ->
                    System.out.printf("%-10s %-10s%n" + nineth(100) + nineth(1000) + nineth(10000) + nineth(100000) + nineth(1000000) + "\nРешение аналитически:\na)0.0485735 б)0.205099", "a)", "б)");
            case 10 -> {
                tenth(1000, 0.4, 50);
                /*tenth(1000, 0.3, 50);
                //tenth(1000, 0.2, 50);
                */tenth(1000, 0.1, 50);
            }
            case 11 ->
                    System.out.println(eleventh(100) + " " + eleventh(1000) + " " + eleventh(10000) + " " + eleventh(100000) + " " + eleventh(1000000) + "\nАналитическое решение: 2");
            case 12 -> twelveth();
            case 13 -> thirteenth(5);
            case 14 ->
                    System.out.println(fourteenth(100) + " " + fourteenth(1000) + " " + fourteenth(10000) + " " + fourteenth(100000) + " " + fourteenth(1000000) + "\nАналитическое решение: 0.781730");
            case 15 -> {
                fifteenth(-1);
                fifteenth(0);
                fifteenth(1);
                fifteenth(5);
            }
            default -> System.out.println("Такую задачу пока не решили");
        }
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
    public static double tenth(int experiments, double p, int r) {
        for (int j = 0; j < 1000; j++) {
            double successful = 0.0;
            int newR = r;
            for (int i = 0; i < experiments; i++) {
                Random random = new Random();
                double trying = random.nextDouble();
                if (trying <= p) {
                    newR--;
                    successful++;
                }
                if (newR == 0) {
                    System.out.println(i);
                    break;
                }
            }
            if (newR != 0) System.out.println(experiments);
        }
        return 0;
    }

    public static int eleventh(int experiments) {
        int[][] weightsSet = {{1, 1, 2, 2, 1, 2, 2, 3, 3, 1},
                {1, 1, 1, 1, 2, 2, 2, 3, 3, 1},
                {1, 1, 2, 3, 1, 2, 2, 3, 4, 1}};
        int[] weightCount = {0, 0, 0};
        for (int i = 0; i < experiments; i++) {
            int weightToWeigh = (int) (Math.random() * 10);
            for (int j = 0; j < 3; j++){
                weightCount[j] += weightsSet[j][weightToWeigh];
            }
        }
        if (weightCount[0] < weightCount[1] && weightCount[0] < weightCount[2]) return 1;
        if (weightCount[1] < weightCount[0] && weightCount[1] < weightCount[2]) return 2;
        return 3;
        //Аналитическое решение: 2
    }
    public static void twelveth() {
        List<Double> statisticsA = new ArrayList<>();
        List<Double> statisticsB = new ArrayList<>();
        for (int j = 0; j < 1000; j++) {
            Random random = new Random();
            double x = random.nextDouble() * 10 - 5;
            double solutionA = 0;
            if (x >= 0 && x <= 1) solutionA = x;
            else if (x > 1) solutionA = 1;
            Double solutionB = Math.exp(-x * x / 2) / Math.sqrt(2 * Math.PI);
            statisticsA.add(solutionA);
            statisticsB.add(solutionB);
        }
        for (Double solution: statisticsA) System.out.println(solution);
        System.out.println("\n\n");
        for (Double solution: statisticsB) System.out.println(solution);
    }
    public static void thirteenth(double a) {
        List<Double> statistics = new ArrayList<>();
        for (int j = 0; j < 1000; j++) {
            Random random = new Random();
            double x = random.nextDouble() * 40 - 20;
            double solution = a/(Math.PI * (x * x + a * a));
            statistics.add(solution);
        }
        for (Double solution: statistics) System.out.println(solution);
    }
    public static double fourteenth(int experiments) {
        double sigma = 20.0;
        double mu1 = 5.0;
        double mu2 = -5.0;
        double goodExperiments = 0;
        for (int i = 0; i < experiments; i++) {
            Random random1 = new Random();
            Random random2 = new Random();
            double x1 = mu1 + sigma * Math.sqrt(-2.0 * Math.log(random1.nextDouble())) * Math.cos(2.0 * Math.PI * random1.nextDouble());
            double x2 = mu2 + sigma * Math.sqrt(-2.0 * Math.log(random2.nextDouble())) * Math.cos(2.0 * Math.PI * random2.nextDouble());
            if (Math.abs(x1) <= 15 || Math.abs(x2) <= 15) goodExperiments++;
        }
        return goodExperiments/experiments;
        //Аналитическое решение: 0.781730
    }
    public static long factorial(int n) {
        if (n <= 0) {
            return 1;
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    public static void fifteenth(double m0) {
        List<Double> statistics = new ArrayList<>();
        int min = 0;
        int max = 5;
        if (m0 == 1) {
            min = -1;
            max = 7;
        }
        else if (m0 == 5) {
            min = -2;
            max = 9;
        }
        for (int j = 0; j < 1000; j++) {
            Random random = new Random();
            double a = random.nextDouble() * max + min;
            double solution = (2 * Math.exp(-2 * a) * Math.pow(2*a,m0+1))/(factorial((int) (m0+1)));
            statistics.add(solution);
        }
        for (Double solution: statistics) System.out.println(solution);
    }
}
