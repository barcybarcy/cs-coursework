package hw3;
/* 첨부된 입력파일을 이용하여 주식거래내역 클래스와 주식거래관리 클래스를 만드시오.

수업시간에 했던 1~7단계를 적용하여 단계적으로 기능을 확장하고 클래스 구조는 수업시간에 다룬 것과 동일하게 해 주세요.

- 수업시간에 한 구조를 따르기 위해서는 단계별로 AI 도움 없이 직접 해보는 것도 좋은 방법입니다.

        - AI에게 도움을 청할 때는 단계 별로 어떤 일을 할지를 정확히 알려주어야 합니다. (간단한 것부터 반복적으로 확장)

        - AI가 생성한 코드에 대해 고칠 점을 얘기해 주면서 반복적으로 수업에서 했던 구조와 같은 코드가 나오게 계속 요청해야 합니다. (AI와 끈기있게 대화를 계속해야 함)

주식거래내역 클래스에 월, 일, 단가, 개수 등은 int 타입 필드로 할 것

관리 클래스는 (배열에서 시작 생략 가능) ArrayList를 가지고 readAllRecords(), printAllRecords() 메소드를 가지게 할 것

주식거래내역의 출력 형식은 자유롭게 하되 줄 맞추어 출력되도록 할 것.

과제는 수6이름.zip 형태로 제출하고 보고서에 작업한 방식과 후기를 반드시 넣으세요.

보고서는 pdf 파일로 제출해 주세요. */


import java.util.ArrayList;
import java.util.Scanner;

public class TradingManager {
    private ArrayList<TradingRecord> records;
    private Scanner scan = new Scanner(System.in);

    public TradingManager(){
        this.records = new ArrayList<>();
    }

    public void readAllRecords(){
        System.out.println("주식 거래 내역을 아래에 붙여넣기 하세요 (첫 줄은 총 개수):");
        //첫 줄 총 거래 횟수
        int total = scan.nextInt();

        // 2. 위에서 읽고 남은 줄바꿈(Enter) 문자를 소비합니다. (이 줄이 추가되었습니다!)
        scan.nextLine();

        for (int i = 0; i < total; i++){
            int month = scan.nextInt();
            int day = scan.nextInt();
            String stockCode = scan.next();
            String tradeType = scan.next();
            int unitPrice = scan.nextInt();
            int quantity = scan.nextInt();

            //읽어온 데이터로 TradingRecord 객체 생성하여 리스트에 추가
            records.add(new TradingRecord(month, day, stockCode, tradeType, unitPrice, quantity));
        }
        System.out.println("총 " + records.size() + "개의 거래 내역을 읽었습니다.");
    }
    public void printAllRecords(){
        System.out.println("\n전체 내역을 출력합니다.\n");
        System.out.println("=========================================================");
        System.out.println("                      주식 거래 내역");
        System.out.println("=========================================================");
        System.out.printf("%10s | %8s | %4s | %10s | %6s\n", "거래일", "종목코드", "구분", "단가", "수량");
        System.out.println("---------------------------------------------------------");

        // 리스트에 저장된 모든 record에 대해 toFormattedString() 메소드를 호출하여 출력
        for (TradingRecord record : records) {
            System.out.println(record.toFormattedString());
        }
        System.out.println("=========================================================");

    }

    public void mymain(){
        this.readAllRecords();
        this.printAllRecords();
        scan.close();
        System.out.println("프로그램을 종료합니다.");
    }

    public static void  main(String [] args){
        TradingManager manager = new TradingManager();
        manager.mymain();
    }
}
