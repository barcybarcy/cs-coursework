import java.util.Random;
import java.util.Scanner;

public class Hw2Step1 {
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();

    // 몇개의 년을 입력받을지
    int NUM_YEARS = 5;

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
        int startYear = 2021;

        // 월별 매출액 출력, 연매출 합계 계산
        System.out.println("\n === 월별 매출액 및 연매출 합계===");
        for (int year = 0; year < salesData.length; year++) {
            double yearTotal = 0;
            System.out.printf("%d: ", startYear + year);

            // 월별 매출액
            for (int month = 0; month < 12; month++) {
                System.out.printf("%8.1f", salesData[year][month]);
                yearTotal += salesData[year][month];
            }

            // 연매출 배열
            yearlyTotals[year] = yearTotal;
            System.out.printf(" 연매출 = %.1f", yearTotal);

            // 증감율(%) = (이번값 - 이전값) / 이전값 * 100
            if (year > 0) {
                double difference = yearlyTotals[year] - yearlyTotals[year - 1];
                double changeRate = (difference / yearlyTotals[year - 1]) * 100;
                System.out.printf(" (증감액: %.1f, 증감율: %.2f%%)", difference, changeRate);
            }
            System.out.println();
        }
    }

    void mymain() {
        double[][] salesData = readSalesData();
        calculateAndDisplay(salesData);
        scan.close();
    }

    public static void main(String[] args) {
        Hw2Step1 my = new Hw2Step1();
        my.mymain();
    }
}