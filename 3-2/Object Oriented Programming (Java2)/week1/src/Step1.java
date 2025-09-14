import java.util.Random;
import java.util.Scanner;

public class Step1 {
    public static void main(String[] args) {
        // 1단계 : 개수 n을 입력받아 난수 n개를 생성하여 출력한다. (배열X, 이름붙이기 스타일 규칙 따를 것)
        Scanner inputScanner = new Scanner(System.in);
        Random randomGenerator = new Random();

        System.out.print("몇 개의 난수를 생성할까요? ");
        int numberCount = inputScanner.nextInt();
        int currentNumber = 0;
        System.out.print("난수는 1~100 사이입니다.\n");
        System.out.println("생성된 난수:");
        for(int i = 0; i < numberCount; i++){
            currentNumber = randomGenerator.nextInt(100) + 1;
            System.out.println(currentNumber);
        }
    }
}
