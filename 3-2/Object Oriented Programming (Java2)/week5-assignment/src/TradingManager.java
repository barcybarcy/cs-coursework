import java.util.ArrayList;
import java.util.Scanner;

public class TradingManager {
    private Scanner scan = new Scanner(System.in);
    private ArrayList<TradingRecord> tradingRecords = new ArrayList<>();
    private ArrayList<Stock> stocks = new ArrayList<>();
    private int cashBalance;


    public void readData(){
        int tradingDataInput = this.scan.nextInt();
        for(int i = 0; i < tradingDataInput; i++){
            //SRP 원칙에 따라 scanner을 이용해 scan하는건 Manager에서.
            int month = scan.nextInt();
            int day = scan.nextInt();
            char stockName = scan.next().charAt(0);
            String tradingType = scan.next();
            int tradingPrice = scan.nextInt();
            int tradingQuantity = scan.nextInt();

            TradingRecord inputTradingRecord = new TradingRecord(month, day, stockName, tradingType, tradingPrice, tradingQuantity);
            tradingRecords.add(inputTradingRecord);
        }

        //현재 날짜 읽기
        int currentMonth = this.scan.nextInt();
        int currentDay = this.scan.nextInt();

        //현재 가격 읽기
        String input = this.scan.next();
        while(!input.equals("0")){
            char stockName = input.charAt(0);
            int currentPrice = Integer.parseInt(this.scan.next());

            Stock stock = new Stock(stockName, currentPrice, 0);
            stocks.add(stock);

            input = this.scan.next();
        }

        scan.nextLine(); // 이전에 남아있는 줄바꿈(엔터) 비우기. 이것이 중요합니다.
        String lastLine = scan.nextLine(); // "초기 투자금: 10000000원" 한 줄을 통째로 읽기
        String numbersOnly = lastLine.replaceAll("[^0-9]", ""); // 숫자가 아닌 모든 문자(공백 포함)를 제거
        this.cashBalance = Integer.parseInt(numbersOnly); // 숫자만 남은 문자열을 int로 변환


//        this.cashBalance = this.scan.nextInt();
    }

    public void distributeTradingRecords(){
        for(Stock stock : this.stocks){
            for(TradingRecord tradingRecord : this.tradingRecords){
                if(stock.getName() == tradingRecord.getStockName()){
                    stock.addTradingRecord(tradingRecord);
                    updateCashBalance(tradingRecord);
                }
            }
        }
    }

    public void updateCashBalance(TradingRecord tradingRecord){
        if (tradingRecord.getTradingType().equals("매수")){
            cashBalance -= tradingRecord.getTradingPrice() * tradingRecord.getTradingQuantity();
        }else{
            cashBalance += tradingRecord.getTradingPrice() * tradingRecord.getTradingQuantity();
        }
    }

    public void printEachStocks(){
        for (Stock stock : this.stocks){
            stock.printStockRecords();
        }
    }
    public void printAccountInfo(){
        int totalStockValue = 0;
        for(Stock stock : this.stocks){
            totalStockValue += stock.getTotalPrice();
            System.out.printf("[%c] %,7d원 * %3d주 = %,12d원\n",
                    stock.getName(),
                    stock.getCurrentPrice(),
                    stock.getQuantity(),
                    stock.getTotalPrice());
        }
        System.out.printf("총 주식가치: +%d\n", totalStockValue);
        System.out.printf("남은 투자금: +%d\n",cashBalance);
    }

    public void mymain(){
        readData();
        distributeTradingRecords();
        printEachStocks();
        printAccountInfo();

        this.scan.close();
    }

    public static void main(String[] args) {
        TradingManager tm = new TradingManager();
        tm.mymain();
    }
}
