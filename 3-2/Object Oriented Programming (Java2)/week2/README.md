# 2주 과제 보고서

이름: 조수민
학번: 201540321

# 1. 과제 개요

본 과제에서는 5년간의 월 매출 데이터를 입력받아 다음을 계산하고 출력한다.

<aside>
💡

1단계 (AI의 도움을 받을 수 있음)

5년간 월 매출액 정보를 읽어 다음을 계산한다.

(1) 월별 매출액을 출력하고 연매출 합계 계산하여 줄 맨 끝에 출력

(2) 연매출 증감액과 증감율을 계산하여 출력

2단계 (스스로 해결)

(3) 월 평균 매출이 가장 많은 달

(4) 월 매출 증감율(전년동월대비)이 가장 큰 월(OOOO년OO월)와 가장 작은 달을 찾아 출력

증감율(%) = 이번값 / 이전값 * 100

</aside>

# 2. 구현 내용

## 1단계 - AI 도움 받은 부분

### **Skeleton Code**

- readSalesData(), calculateAndDisplay(), mymain(), main() 함수의 뼈대는 AI가 제안하였다.
- 반복 구조, 연도별 합계 배열(yearlyTotals) 처리 구조를 큰 틀로 제공받음.

```java
double[][] readSalesData(){}
void calculateAndDisplay(double[][] salesData){}
void mymain(){}
public static void main(String[] args){}
```

### **증감율 계산 로직**

- AI가 (이번값 - 이전값) / 이전값 * 100 공식을 제안해 주었음.
- 이를 그대로 적용하여 연도별 증감액과 증감율을 출력하였다.

```java
// 증감율(%) = (이번값 - 이전값) / 이전값 * 100
if (year > 0) {
  double difference = yearlyTotals[year] - yearlyTotals[year - 1];
  double changeRate = (difference / yearlyTotals[year - 1]) * 100;
  System.out.printf(" (증감액: %.1f, 증감율: %.2f%%)", difference, changeRate);
}
```

## 2단계 - 스스로 작성한 부분

### 모듈화

- calculateAndDisplay()에 모든 기능을 넣지 않고,
    - calculateMonthlyAverages() (월평균 계산)
    - calculateMonthlGrowthRates() (월별 증감율 계산)

  두 함수로 분리하여 관리.


```java
double[][] readSalesData(){}
void calculateMonthlyAverages(double[][] salesData){}
void calculateMonthlGrowthRates(double[][] salesData){
void calculateAndDisplay(double[][] salesData){
	...
	calculateMonthlyAverages(salesData);
	calculateMonthlGrowthRates(salesData);
}

void mymain() {
    double[][] salesData = readSalesData();
    calculateAndDisplay(salesData);
    scan.close();
}
```

### 월평균 매출 계산

각 월별 매출 평균을 구해 배열에 저장하고, 평균이 가장 높은 달을 찾아 출력.

```java
if (monthlyAverages[month] > maxAverage) {
    maxAverage = monthlyAverages[month];
    highestMonth = month;
}
System.out.printf("월평균 매출이 가장 많은 달 : %s (%.1f)\n",
    MONTHNAEMS[highestMonth], maxAverage);
```

### 전년 동월 대비 증감율 최대/최소 계산

- maxGrowthRate, minGrowthRate 변수를 각각 **Double.NEGATIVE_INFINITY**, **Double.POSITIVE_INFINITY** 로 초기화하였다.
- 이렇게 하면 첫 번째 비교에서 어떤 값이 들어오더라도 무조건 업데이트되도록 보장된다.
- 만약 0이나 임의의 값으로 초기화하면, 실제 매출 데이터가 모두 그 값보다 크거나 작은 경우 최댓값/최솟값이 갱신되지 않을 수 있는 위험이 있다.

```java
void calculateMonthlGrowthRates(double[][] salesData) {
      double maxGrowthRate = Double.NEGATIVE_INFINITY;
      double minGrowthRate = Double.POSITIVE_INFINITY;
      int maxGrowthYear = 0, maxGrowthMonth = 0;
      int minGrowthYear = 0, minGrowthMonth = 0;
```

# 3. 실행 결과

## 1단계

![Screenshot 2025-09-14 at 6.24.37 PM.png](2%EC%A3%BC%20%EA%B3%BC%EC%A0%9C%20%EB%B3%B4%EA%B3%A0%EC%84%9C%2026d77b59e04280218450c301316a0632/Screenshot_2025-09-14_at_6.24.37_PM.png)

## 2단계

![Screenshot 2025-09-14 at 6.24.57 PM.png](2%EC%A3%BC%20%EA%B3%BC%EC%A0%9C%20%EB%B3%B4%EA%B3%A0%EC%84%9C%2026d77b59e04280218450c301316a0632/Screenshot_2025-09-14_at_6.24.57_PM.png)

# 4. Lesson Learn

- 한 함수에 많은 기능을 몰아넣으면 가독성과 유지보수가 어려워진다는 점을 깨달았다.
- 모듈화를 통해 각 함수가 “한 가지 책임만 수행”하도록 분리하는 것이 중요함을 배웠다.
- maxGrowthRate = Double.NEGATIVE_INFINITY, minGrowthRate = Double.POSITIVE_INFINITY 로 초기화하면 첫 번째 비교에서 반드시 값이 갱신되어 안정적으로 최댓값/최솟값을 구할 수 있음을 이해했다.
- AI가 제공한 skeleton code는 뼈대 역할을 해주었지만, 실제로 디테일을 채우고 디버깅하면서 더 깊이 이해할 수 있었다.