import java.util.Random;
import java.util.Scanner;

public class Step2 {
    public static void main(String[] args) {
        // 2단계: 최대값, 최소값을 출력한다.
        Scanner inputScanner = new Scanner(System.in);
        Random randomGenerator = new Random();

        System.out.print("몇 개의 난수를 생성할까요? ");
        int numberCount = inputScanner.nextInt();
        int currentNumber = 0;
        int maximumValue = 0;
        int minimumValue = 101; // 1~100 사이의 난수이므로 초기값을 101로 설정
        System.out.print("난수는 1~100 사이입니다.\n");
        System.out.println("생성된 난수:");
        for(int i = 0; i < numberCount; i++){
            currentNumber = randomGenerator.nextInt(100) + 1; // 1~100 사이의 난수 생성
            System.out.println(currentNumber);

            if (currentNumber > maximumValue){
                maximumValue = currentNumber;
            }
            if (currentNumber < minimumValue){
                minimumValue = currentNumber;
            }
        }
        System.out.println("최대값 : " + maximumValue);
        System.out.println("최소값 : " + minimumValue);
    }
}
