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

    // TODO 1 : 기본 실행 method 메뉴: (1) 전체보기, (2) 종목별 보기(종목 순으로 출력), (3) 검색, (4) 잔고 보기
    public void run(){
        while (true){
            this.printMenu();
            int choice = scan.nextInt();
            // 엔터키 소진
            scan.nextLine();

            if (choice == 0){
                System.out.println("프로그램 종료");
                break;
            }
            switch (choice){
                case 1:
                    this.printAllRecords();
                    break;
                case 2:
                    this.printByStock();
                    break;
                case 3:
                    System.out.println("검색할 키워드를 입력하시오 : ");
                    String searchKeywords = scan.nextLine();
                    this.searchRecords(searchKeywords);
                    break;
                case 4:
                    printBalance();
                    break;
                default:
                    System.out.println("잘못된 메뉴 선택입니다.");
            }
        }

    }

    // TODO 2 : 종목별 보기(종목 순으로 출력)
    /* 종목별 보기는 정렬을 하지 않고 'A'부터 종목 차례로 찍는다 (search 함수 재사용) */
    public void printByStock(){
        for(char c = 'A'; c <= 'F'; c++){
                System.out.printf("\n-- %c 종목 거래 내역 --\n", c);
                searchRecords(String.valueOf(c));
        }

    }

    // TODO 3: 검색
    /* 검색 기능은 다중 검색(1/5 매수)할 수 있게 하고 1/5는 날짜로 취급
[도전] 기간 검색(1/5 1/7), 또는 기간 검색을 다중 검색과 같이 할 수 있게 작성한다 (1/5 1/10 A)
         키워드에 '/'가 들어있으면 날짜로 취급 (kwd.charAt(1) == '/', 월을 한자리로 가정, 두 자리로 확장 가능) */
    public void searchRecords(String kwds){
        // 먼저 키워드들을 나눈다
        // trim = 불필요한 공백 제거
        String[] tokens = kwds.trim().split("\\s+");

        //도전 기간 검색
        ArrayList<String> dateTokens = new ArrayList<>();
        ArrayList<String> otherTokens = new ArrayList<>();

        for(String token : tokens){
            if (token.contains("/")){
                dateTokens.add(token);
            }else{
                otherTokens.add(token);
            }
        }

        //날짜 키워드 개수에 따라 분기 처리
        if (dateTokens.size() == 2) {
            // [기간 검색 로직]
            try {
                // 날짜를 비교하기 쉬운 숫자(MMDD)로 변환 (e.g., "1/22" -> 122)
                String[] parts1 = dateTokens.get(0).split("/");
                int date1 = Integer.parseInt(parts1[0]) * 100 + Integer.parseInt(parts1[1]);

                String[] parts2 = dateTokens.get(1).split("/");
                int date2 = Integer.parseInt(parts2[0]) * 100 + Integer.parseInt(parts2[1]);

                int startDate = Math.min(date1, date2); // 순서가 바뀌어도 괜찮도록 시작일 설정
                int endDate = Math.max(date1, date2);   // 순서가 바뀌어도 괜찮도록 종료일 설정

                System.out.printf("\n-- [%s ~ %s] 검색 결과 --\n", dateTokens.get(0), dateTokens.get(1));

                for (TradingRecord record : records) {
                    int recordDate = record.getMonth() * 100 + record.getDay();
                    // 조건 1: 날짜가 기간 내에 있는지 확인
                    boolean dateMatch = (recordDate >= startDate && recordDate <= endDate);

                    // 조건 2: 나머지 키워드들이 모두 일치하는지 확인
                    boolean otherMatch = checkAllTokensMatch(record, otherTokens);

                    if (dateMatch && otherMatch) {
                        System.out.println(record.toFormattedString());
                    }
                }
            } catch (Exception e) {
                System.out.println("잘못된 날짜 형식입니다. 검색을 중단합니다.");
            }
        } else {
            // [단순 매칭 로직] (날짜가 1개 또는 0개일 경우)
            // 기간 검색이 아니므로 날짜 토큰도 일반 토큰으로 취급
            otherTokens.addAll(dateTokens);
            System.out.println("\n-- [" + kwds + "] 검색 결과 --");
            for (TradingRecord record : records) {
                if (checkAllTokensMatch(record, otherTokens)) {
                    System.out.println(record.toFormattedString());
                }
            }
        }
    }


    //help for searchRecords
    public boolean checkAllTokensMatch(TradingRecord record, ArrayList<String> tokens){
        for(String token : tokens){
            if(!record.matches(token)){
                return false;
            }
        }
        return true;
    }

    // TODO 4: 잔고 보기
    /*  잔고 보기 메뉴 추가하여 천만원으로 시작해서 현재 남은 잔액과 주식 잔고 개수(A~F)를 표시한다
        팁: 잔고를 나타내는 배열 사용 stockBalance[code - 'A']로 code 종목의 잔고 접근 가능
             Trade는 getSubtotal로 매수/매도 금액을 돌려주고 getCode()로 코드를 돌려준다 */

    public void printBalance(){
        //초기 자본금
        int balance = 10000000;
        int[] stockBalance = new int[6];
        for(TradingRecord record : records){
            char code = record.getStockCode().charAt(0);
            int subtotal = record.getSubtotal();
            if(record.getTradeType().equals("매도")){
                balance += subtotal;
                stockBalance[code - 'A'] -= record.getQuantity();
            } else {
                balance -= subtotal;
                stockBalance[code - 'A'] += record.getQuantity();
            }
        }

        System.out.println("잔고현황");
        System.out.println("=========================================================");
        System.out.printf("현금 : %d\n", balance);
        for(int i = 0; i < stockBalance.length; i++){
            System.out.printf("- %c 주식 : %d 주\n",(char)('A' + i), stockBalance[i]);
        }
        System.out.println();

    }


    // TODO 5: 메뉴 출력
    public void printMenu(){
        System.out.println("\n =========메뉴========");
        System.out.println("1. 전체보기");
        System.out.println("2. 종목별 보기");
        System.out.println("3. 검색");
        System.out.println("4. 잔고 보기");
        System.out.println("0. 종료");
        System.out.print("메뉴를 선택하세요 : ");
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
//        this.printAllRecords();
        run();
        scan.close();
        System.out.println("프로그램을 종료합니다.");
    }

    public static void  main(String [] args){
        TradingManager manager = new TradingManager();
        manager.mymain();
    }
}
