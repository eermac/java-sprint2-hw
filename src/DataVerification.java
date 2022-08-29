import java.util.ArrayList;

public class DataVerification {
    void checkData(ArrayList<MonthlyReport> monthData, ArrayList<YearlyReport> operationalDataYear, int option){
        int sumOfMonth = 0;

        if(option == 1){ //считаем расходы
            for(int i = 0; i < monthData.size(); i++) {
                for(Integer cost : monthData.get(i).monthlyDataIsExpense.values()) {
                    sumOfMonth += cost;
                }

                if(operationalDataYear.get(0).yearlyDataIsExpense.get("0" + (i+1)) == sumOfMonth){
                    System.out.println("Данные отчета трат за 0" + (i+1) + " месяц совпадают с годовым отчетом");
                } else System.out.println("Данные отчета трат за 0" + (i+1) + " месяц не совпадают с годовым отчетом");

                sumOfMonth = 0;
            }
        } else if(option == 2){ //считаем доходы
            for(int i = 0; i < monthData.size(); i++) {
                for(Integer cost : monthData.get(i).monthlyDataIsNotExpense.values()) {
                    sumOfMonth += cost;
                }

                if(operationalDataYear.get(0).yearlyDataIsNotExpense.get("0" + (i+1)) == sumOfMonth){
                    System.out.println("Данные отчета доходов за 0" + (i+1) + " месяц совпадают с годовым отчетом");
                } else System.out.println("Данные отчета доходов за 0" + (i+1) + " месяц не совпадают с годовым отчетом");

                sumOfMonth = 0;
            }
        }
    }
}
