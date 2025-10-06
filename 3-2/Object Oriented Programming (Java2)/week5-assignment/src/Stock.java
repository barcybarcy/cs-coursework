import java.util.ArrayList;

public class Stock {
    private char name;

    private int currentPrice;
    private int quantity;
    private ArrayList<TradingRecord> tradingRecord;

    public Stock(char name, int currentPrice, int quantity) {
        this.name = name;
        this.currentPrice = currentPrice;
        this.quantity = quantity;
        this.tradingRecord = new ArrayList<>();
    }

    public void addTradingRecord(TradingRecord inputTradingRecord) {
        this.tradingRecord.add(inputTradingRecord);

        if (inputTradingRecord.getTradingType().equals("매수")){
            this.quantity += inputTradingRecord.getTradingQuantity();
        }else{
            this.quantity -= inputTradingRecord.getTradingQuantity();
        }
    }

    public void printTradingRecords(){
        System.out.printf("================== 주식 거래 내역 (%c) ==================", this.name);
        for( TradingRecord tradingRecord : this.tradingRecord){
            tradingRecord.printTradingRecords();
        }
        System.out.println();
    }

    public void printStockRecords(){
        System.out.println("================== 주식 잔고 ==================");
        System.out.printf("[%c] 현재가=%,d원, 주식잔고 %d주, 현재주식가치 %,d원\n",
                this.name,
                this.currentPrice,
                this.quantity,
                this.quantity*this.currentPrice);
        System.out.println();
        System.out.printf("================== 주식 거래 내역 (%c) ==================\n", this.name);
        for(TradingRecord tradingRecord : this.tradingRecord){
            tradingRecord.printTradingRecords();
        }
        System.out.println();
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getTotalPrice(){
        return currentPrice * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public char getName() {
        return name;
    }
}
