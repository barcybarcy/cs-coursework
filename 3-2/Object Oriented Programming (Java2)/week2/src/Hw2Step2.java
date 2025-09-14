import java.util.Random;
import java.util.Scanner;

public class Hw2Step2 {
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();

    // 몇개의 년을 입력받을지
    int NUM_YEARS = 5;
    int START_YEAR = 2021;
    String[] MONTHNAEMS = {"1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"};

    int[][] fillArray() {
        System.out.print("숫자 행 열 개수: ");
        int nRow = scan.nextInt();
        int nCol = scan.nextInt();
        int array[][] = new int[nRow][nCol];
        for (int i = 0; i < nRow; i++)
            for (int j = 0; j < nCol; j++)
                array[i][j] = rand.nextInt(100);
        return array;
    }

    double[][] readSalesData() {
        System.out.printf("지난 %d년간의 연간 매출액을 입력하시오: ", NUM_YEARS);
        double[][] salesData = new double[NUM_YEARS][12];

        for (int year = 0; year < NUM_YEARS; year++) {
            // 년도 숫자 skip (2021, 2022, ...)
            scan.nextInt();
            for (int month = 0; month < 12; month++) {
                salesData[year][month] = scan.nextDouble();
            }
        }
        return salesData;
    }

    void calculateAndDisplay(double[][] salesData) {
        double[] yearlyTotals = new double[salesData.length];

        // 월별 매출액 출력, 연매출 합계 계산
        System.out.println("\n === 월별 매출액 및 연매출 합계===");
        for (int year = 0; year < salesData.length; year++) {
            double yearTotal = 0;
            System.out.printf("%d: ", START_YEAR + year);

            // 월별 매출액
            for (int month = 0; month < 12; month++) {
                System.out.printf("%8.1f", salesData[year][month]);
                yearTotal += salesData[year][month];
            }

            // 연매출 배열
            yearlyTotals[year] = yearTotal;
            System.out.printf(" 연매출 = %.1f", yearTotal);

            //증감액, 증감율
            // 증감율(%) = (이번값 - 이전값) / 이전값 * 100
            if (year > 0) {
                double difference = yearlyTotals[year] - yearlyTotals[year - 1];
                double changeRate = (difference / yearlyTotals[year - 1]) * 100;
                System.out.printf(" (증감액: %.1f, 증감율: %.2f%%)", difference, changeRate);
            }
            System.out.println();
        }
        calculateMonthlyAverages(salesData);
        calculateMonthlGrowthRates(salesData);

    }

    //월평균 매출 계산
    void calculateMonthlyAverages(double[][] salesData) {
        double[] monthlyAverages = new double[12];

        System.out.print("월평균: ");
        int highestMonth = 0;
        double maxAverage = 0;

        for (int month = 0; month < 12; month++) {
            double total = 0;
            for (int year = 0; year < salesData.length; year++) {
                total += salesData[year][month];
            }
            monthlyAverages[month] = total / salesData.length;
            System.out.printf("%8.1f", monthlyAverages[month]);

            //최고 평균 매출 월 구하기
            if (monthlyAverages[month] > maxAverage) {
                maxAverage = monthlyAverages[month];
                highestMonth = month;
            }
        }
        System.out.println();
        System.out.printf("월평균 매출이 가장 많은 달 : %s (%.1f)\n", MONTHNAEMS[highestMonth], maxAverage);
    }

    void calculateMonthlGrowthRates(double[][] salesData) {
        double maxGrowthRate = Double.NEGATIVE_INFINITY;
        double minGrowthRate = Double.POSITIVE_INFINITY;
        int maxGrowthYear = 0, maxGrowthMonth = 0;
        int minGrowthYear = 0, minGrowthMonth = 0;

        for (int year = 1; year < salesData.length; year++) {
            for (int month = 0; month < 12; month++) {
                double growthRate = (salesData[year][month] - salesData[year - 1][month]) / salesData[year - 1][month] * 100;

                if (growthRate > maxGrowthRate) {
                    maxGrowthRate = growthRate;
                    maxGrowthYear = year;
                    maxGrowthMonth = month;
                }

                if (growthRate < minGrowthRate) {
                    minGrowthRate = growthRate;
                    minGrowthYear = year;
                    minGrowthMonth = month;
                }
            }
        }
        System.out.printf("증감율이 가장 큰 월 : %d년 %s (%.2f%%)\n", START_YEAR + maxGrowthYear, MONTHNAEMS[maxGrowthMonth], maxGrowthRate);
        System.out.printf("증감율이 가장 작은 월 : %d년 %s (%.2f%%)\n", START_YEAR + minGrowthYear, MONTHNAEMS[minGrowthMonth], minGrowthRate);
    }

    void mymain() {
        double[][] salesData = readSalesData();
        calculateAndDisplay(salesData);
        scan.close();
    }

    public static void main(String[] args) {
        Hw2Step2 my = new Hw2Step2();
        my.mymain();
    }
}