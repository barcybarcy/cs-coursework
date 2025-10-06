# 자바 Assignment5

Week: 5

# 1. 과제 개요

| Name | 조수민 |
| --- | --- |
| Student Id | 201540321 |
| Major | Computer Science |
| **Date** | September 29, 2025 |

<aside>
💡

주식 클래스를 새로 만들고 보유수와 현재가를 입력받아 주식에 관한 계산을 수행하게 한다.

주식거래 내역을 입력받은 후 주식의 입력 데이터로 현재 날짜의 주식의 현재 가격을 입력받는다.

출력 형식은 주식 별로 거래 내역을 출력하고 남은 주식 수와 현재가로 주식의 현재 가치를 계산하여 출력한다.

마지막에 총 주식 가치와 현금 잔액을 출력한다.

- 입력과 출력 내용은 첨부파일을 참고해 주세요.

주식 클래스와 거래내역 클래스, 전체를 관리하는 클래스로 구성해서 세개 클래스 프로그램으로 작성합니다.

보고서에 클래스 간의 관계와 SRP에 의한 역할분담을 설명해 주세요.

</aside>

# 2. 구현 내용

## 1단계 - AI 도움 받은 부분

### scan할때 한줄에서 문자열은 빼고 int만 받고싶을 때

```java
String lastLine = scan.nextLine(); // "초기 투자금: 10000000원" 한 줄을 통째로 읽기
String numbersOnly = lastLine.replaceAll("[^0-9]", ""); // 숫자가 아닌 모든 문자(공백 포함)를 제거
this.cashBalance = Integer.parseInt(numbersOnly); // 숫자만 남은 문자열을 int로 변환
```

사용자가 “초기 투자금: 10000000원”처럼 문자가 포함된 한 줄을 입력하면, scan.nextLine()으로 전체 문장을 통째로 읽어들인다. 이후 replaceAll("[^0-9]", "") 정규식을 사용하여 숫자가 아닌 모든 문자(공백, 한글, 특수문자 등)를 제거하고, 순수한 숫자만 남긴다. 마지막으로 Integer.parseInt()를 통해 남은 숫자 문자열을 정수형으로 변환해 프로그램에서 사용할 수 있도록 한다.
이 접근 방식은 문자열 내 불필요한 요소를 깔끔하게 제거하면서, 사용자가 어떤 형식으로 입력하더라도 안정적으로 숫자 데이터를 추출할 수 있다는 점에서 효율적이다.

### If 문 간략화

```java
        String quantitySign = tradingType.equals("매수") ? "+" : "-";
        System.out.printf("%d월 %2d일\t %c\t%s\t%,8d원\t%s%d주\n", month, day, stockName, tradingType, tradingPrice, quantitySign, tradingQuantity);
```

기존에는 if-else 문을 사용해 거래 유형이 “매수”인지 “매도”인지에 따라 각각 다른 부호를 출력했지만, 이를 tradingType.equals("매수") ? "+" : "-" 형태로 한 줄로 표현함으로써 코드의 길이를 줄이고 가독성을 높였다. 이러한 방식은 프로그램의 흐름을 단순화하면서도, 한눈에 로직을 파악할 수 있게 해주기 때문에 유지보수성과 효율성을 모두 향상시킨다. 특히 출력문 내에서 바로 적용할 수 있어 불필요한 조건문 중첩을 방지할 수 있다는 점에서 실용적이다.

## 2단계 - 스스로 작성한 부분

## SRP에 따른 역할 분담

### 1. **TradingManager**

- 입력 데이터 읽기
- TradingRecord를 각 Stock에 **분배** (주입)
- 전체 결과 출력
    
    ```java
    import java.util.ArrayList;
    import java.util.Scanner;
    
    public class TradingManager {
        private Scanner scan = new Scanner(System.in);
        private ArrayList<TradingRecord> tradingRecords = new ArrayList<>();
        private ArrayList<Stock> stocks = new ArrayList<>();
        private int cashBalance;
    
        public void readData(){
        ...
        
            }
    
        // TradingRecord를 각 Stock에 분배
        private void distributeTradingRecords() {
            for (TradingRecord record : tradingRecords) {
                Stock targetStock = findStock(record.getStockName());
                if (targetStock != null) {
                    targetStock.addTradingRecord(record);
                }
            }
        }
    
    }
    
    ```
    

### 2. **Stock**

- 자신의 거래내역 **처리** (보유 수량 계산)
- 주식 가치 계산
- 자신의 거래내역 출력
    
    ```java
    import java.util.ArrayList;
    
    public class Stock {
        private char name;
        private int currentPrice;
        private int quantity;
        private ArrayList<TradingRecord> tradingRecords;
    
        public Stock(char name, int currentPrice) {
            ...
        }
    
        // TradingRecord를 추가하고 수량 계산
        public void addTradingRecord(TradingRecord record) {
            tradingRecords.add(record);
    
            // 매수/매도에 따라 수량 변경
            if (record.getTradingType().equals("매수")) {
                quantity += record.getTradingQuantity();
            } else if (record.getTradingType().equals("매도")) {
                quantity -= record.getTradingQuantity();
            }
        }
    
        public int totalPrice() {
            return currentPrice * quantity;
        }
    
    }
    
    ```
    

### 3. **TradingRecord**

- 거래 데이터 보관 (DTO)
    
    ```java
    public class TradingRecord {
        private int month;
        private int day;
        private char stockName;
        private String tradingType;
        private int tradingPrice;
        private int tradingQuantity;
    
        public TradingRecord(int month, int day, char stockName, String tradingType, int tradingPrice, int tradingQuantity) {
        }
    }
    
    ```
    

### 핵심 포인트

1. **TradingManager가 분배**: `distributeTradingRecords()` 메서드에서 각 거래내역을 해당하는 Stock에 전달
2. **Stock이 처리**: `addTradingRecord()` 메서드에서 거래내역을 추가하면서 **자신의 quantity 필드를 직접 변경**
3. **책임 분리**:
    - TradingManager: "누구에게 줄 것인가" 결정
    - Stock: "받은 데이터를 어떻게 처리할 것인가" 결정

## 클래스간의 관계

1. **TradingManager → Stock**
    - `TradingManager`는 여러 개의 `Stock` 객체를 `ArrayList<Stock>`로 관리
    - 각 주식의 생명주기를 관리
2. **TradingManager → TradingRecord**
    - `TradingManager`는 모든 거래 내역을 `ArrayList<TradingRecord>`로 저장
    - 입력 데이터를 받아 `TradingRecord` 객체를 생성
3. **Stock → TradingRecord**
    - 각 `Stock`은 자신과 관련된 거래 내역을 `ArrayList<TradingRecord>`로 보유
    - `TradingManager`가 `distributeTradingRecords()`를 통해 거래 내역을 각 주식에 분배

![image.png](%EC%9E%90%EB%B0%94%20Assignment5%2028277b59e04280e2865bf383891fb4ea/image.png)

# 3. 실행 결과

![Screenshot 2025-10-06 at 10.51.36 PM.png](%EC%9E%90%EB%B0%94%20Assignment5%2028277b59e04280e2865bf383891fb4ea/Screenshot_2025-10-06_at_10.51.36_PM.png)

![Screenshot 2025-10-06 at 10.51.36 PM.png](%EC%9E%90%EB%B0%94%20Assignment5%2028277b59e04280e2865bf383891fb4ea/Screenshot_2025-10-06_at_10.51.36_PM%201.png)

# 4. Lesson Learn

### 필요한 기능 함수 만들기 습관화

이번 과제를 통해 단순히 기능을 한 곳에 몰아넣기보다는 **필요한 기능을 별도의 함수로 분리해 만드는 습관의 중요성**을 배웠다. 이전까지는 프로그램을 빠르게 완성하는 데 집중하느라 코드가 길어지고 반복되는 부분이 많았지만, 이번에는 기능 단위로 함수를 나누면서 코드의 가독성과 재사용성이 크게 향상되었다. 예를 들어, 입력 처리, 거래 분배, 가치 계산 등 각각의 기능을 독립된 메서드로 구성하니 전체 흐름이 훨씬 명확해졌고, 버그를 수정하거나 새로운 기능을 추가할 때도 필요한 부분만 수정하면 되었다. 이러한 경험을 통해 “기능을 함수로 나누는 습관”이 단순한 형식이 아니라, **유지보수가 쉬운 구조적 사고의 핵심**임을 깨닫게 되었다.

### "주식 수량 업데이트를 TradingManager에서 할까, Stock에서 할까?”

```java
// Stock.java - 주식 객체가 자신의 상태를 관리
public void addTradingRecord(TradingRecord inputTradingRecord) {
    this.tradingRecord.add(inputTradingRecord);
    
    if (inputTradingRecord.getTradingType().equals("매수")){
        this.quantity += inputTradingRecord.getTradingQuantity();
    } else {
        this.quantity -= inputTradingRecord.getTradingQuantity();
    }
}

```

addTradingRecord() 메서드는 거래 내역(TradingRecord)을 Stock 객체 내부의 리스트에 추가하고, 동시에 해당 거래가 매수인지 매도인지에 따라 보유 주식 수량(quantity)을 업데이트한다. 이렇게 설계한 이유는 “주식의 수량은 그 주식 객체가 스스로 관리해야 한다”는 **데이터 소유권 원칙** 때문이다. 만약 TradingManager에서 외부적으로 수량을 변경하게 되면, 여러 객체가 동일 데이터를 수정하는 과정에서 불일치가 발생할 위험이 커진다. 반면 Stock 내부에서 직접 상태를 변경하면, 해당 객체의 일관성이 보장되고 캡슐화 원칙도 유지된다.