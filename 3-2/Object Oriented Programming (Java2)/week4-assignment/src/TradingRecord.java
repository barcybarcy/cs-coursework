public class TradingRecord {
    private int month;
    private int day;
    private String stockCode;
    private String tradeType;
    private int unitPrice;
    private int quantity;

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
    public boolean matches(String keyword){
        if(keyword == null || keyword.isEmpty()){
            return false;
        }
        //날짜
        if(keyword.contains("/")){
            //월,일 분리
            try{
                String[] parts = keyword.split("/");
                int partMonth = Integer.parseInt(parts[0]);
                int partDay = Integer.parseInt(parts[1]);
                return (this.month == partMonth) && (this.day == partDay);
            } catch (Exception e){
                return false;
            }
        }
        //키워드가 날짜가 아니면 종목명 혹은 거래 타입이다.
        return this.stockCode.equals(keyword) || this.tradeType.equals(keyword);
    }
    /* Trade는 getSubtotal로 매수/매도 금액을 돌려주고 getCode()로 코드를 돌려준다 */
    public int getSubtotal(){
            return this.unitPrice * this.quantity;
    }

    // ★ 추가: 날짜를 비교하기 쉬운 정수 키로
    public int dateKey() {
        return this.month * 100 + this.day;  // 1/5 -> 105, 12/31 -> 1231
    }

    // ★ 수정: 기존 matches는 그대로 두고, 검색용 비-날짜 전용 매칭 함수 분리
    public boolean matchesNonDate(String keyword){
        if(keyword == null || keyword.isEmpty()) return false;
        // 종목코드 또는 거래유형(매수/매도) 매칭, 대소문자/공백 무시
        String k = keyword.trim();
        return this.stockCode.equalsIgnoreCase(k) || this.tradeType.equalsIgnoreCase(k);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
