public class TradingRecord {
        private int month;
        private int day;
        private char stockName;
        String tradingType;
        int tradingPrice;
        int tradingQuantity;

    public TradingRecord(int month, int day, char stockName, String tradingType, int tradingPrice, int tradingQuantity) {
        this.month = month;
        this.day = day;
        this.stockName = stockName;
        this.tradingType = tradingType;
        this.tradingPrice = tradingPrice;
        this.tradingQuantity = tradingQuantity;
    }

    public void printTradingRecords(){

        String quantitySign = tradingType.equals("매수") ? "+" : "-";
        System.out.printf("%d월 %2d일\t %c\t%s\t%,8d원\t%s%d주\n", month, day, stockName, tradingType, tradingPrice, quantitySign, tradingQuantity);
    }

    public char getStockName() {
        return stockName;
    }

    public String getTradingType() {
        return tradingType;
    }

    public int getTradingQuantity() {
        return tradingQuantity;
    }

    public int getTradingPrice() {
        return tradingPrice;
    }
}
