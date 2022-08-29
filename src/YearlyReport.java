import java.util.HashMap;

public class YearlyReport {
    HashMap<String, Integer> yearlyDataIsExpense = new HashMap<>();
    HashMap<String, Integer> yearlyDataIsNotExpense = new HashMap<>();
    String year;

    YearlyReport(){

    }

    YearlyReport(String[] yearlyData, String yearName){
        for(int i = 1; i < yearlyData.length; i++) {
            String[] lineContents =  yearlyData[i].split(",");
            if(lineContents[2].equals("false")){
                yearlyDataIsNotExpense.put(lineContents[0], Integer.parseInt(lineContents[1]));
            } else {
                yearlyDataIsExpense.put(lineContents[0], Integer.parseInt(lineContents[1]));
                    }
                }
        this.year = yearName;
    }

    String getYearName(){ //получить название года
        return this.year;
    }

    void getProfitOfMonth(HashMap<String, Integer> isExpenseYear, HashMap<String, Integer> isNotExpenseYear){ //прибыль по каждому месяцу
        for(int i = 0; i < isExpenseYear.size(); i++){
            System.out.println("За 0" + (i+1) + " месяц прибыль составила : " + (isNotExpenseYear.get("0" + (i+1)) - isExpenseYear.get("0" + (i+1))));
        }
    }

    double getAverageExpense(HashMap<String, Integer> isExpenseYear){ //средний расход или расход (в зависимости от значения) за все месяцы
        double sum = 0;
        int countMonth = 0;

        for(Integer isExpense: isExpenseYear.values()){
            sum += isExpense;
            countMonth++;
        }

        sum = sum / countMonth;
        return sum;
    }


}
