package hw3;

public class TradingRecord {
    int month;
    int day;
    String stockCode;
    String tradeType;
    int unitPrice;
    int quantity;

    public TradingRecord(int month, int day, String stockCode, String tradeType, int unitPrice, int quantity) {
        this.month = month;
        this.day = day;
        this.stockCode = stockCode;
        this.tradeType = tradeType;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String toFormattedString(){
        return String.format("%4d월 %4d일 | %8s | %4s | %,10d원 | %5d주", month, day, stockCode, tradeType, unitPrice, quantity);
    }
}
