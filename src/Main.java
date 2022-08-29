import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Поехали!
        ReadFile dataFile = new ReadFile();
        Scanner scanner = new Scanner(System.in);
        String year = "2021";
        String[] month = {"01", "02", "03"};

        printMenu(scanner, dataFile, year, month);
    }

    static void printMenu(Scanner scanner, ReadFile dataFile, String year, String[] month) {
        System.out.println("Добро пожаловать");
        ArrayList<MonthlyReport> monthData = new ArrayList<>();
        YearlyReport yearlyData = new YearlyReport();
        ArrayList<YearlyReport> operationalDataYear = new ArrayList<>();
        ArrayList<String> fileContents = new ArrayList<>();

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Считать все месячные отчеты\n2. Считать годовой отчет\n3. Сверить отчеты\n4. Вывести информацию о всех месячных отчётах\n" +
                    "5. Вывести информацию о годовом отчёте\n0. Выйти из приложения");

            int userInput = scanner.nextInt();

            if (userInput == 1) {
                readAndWriteMonthlyReport(month, fileContents, monthData, dataFile);
            } else if (userInput == 2) {
                readAndWriteYeralyReport(dataFile, year, yearlyData, operationalDataYear);
            } else if (userInput == 3) {
                if(monthData.size() == 0 || operationalDataYear.isEmpty()){
                    System.out.println("Отчеты еще не считаны");
                } else verifficationReport(monthData, operationalDataYear);
            } else if (userInput == 4) {
                if(monthData.size() == 0){
                    System.out.println("Отчеты еще не считаны");
                } else printMonthlyReport(month, monthData);
            } else if (userInput == 5){
                if(operationalDataYear.isEmpty()){
                    System.out.println("Отчеты еще не считаны");
                } else printYearlyReport(operationalDataYear);
            } else if (userInput == 0) {
                System.out.println("Приложение завершено");
                break;
            } else System.out.println("Такой команды нет, попробуйте еще раз");
        }
    }

    static void readAndWriteYeralyReport(ReadFile dataFile, String year, YearlyReport yearlyData, ArrayList<YearlyReport> operationalDataYear){
            String fileContents = dataFile.readFileContentsOrNull("y.2021.csv");
            String[] lines = fileContents.split("\\n");
            yearlyData = new YearlyReport(lines, year);
            operationalDataYear.add(0, yearlyData);
            System.out.println("Годовой отчет успешно считан");
    }

    static void printYearlyReport(ArrayList<YearlyReport> operationalDataYear){
            if(operationalDataYear.isEmpty()){
                System.out.println("Вы еще не считали файл");
            } else{
                System.out.println("Рассматриваемый год: " + operationalDataYear.get(0).getYearName());
                operationalDataYear.get(0).getProfitOfMonth(operationalDataYear.get(0).yearlyDataIsExpense, operationalDataYear.get(0).yearlyDataIsNotExpense);
                System.out.println("Средний расход за все месяцы составил: " + operationalDataYear.get(0).getAverageExpense(operationalDataYear.get(0).yearlyDataIsExpense));
                System.out.println("Средний доход за все месяцы составил: " + operationalDataYear.get(0).getAverageExpense(operationalDataYear.get(0).yearlyDataIsNotExpense));
            }
        }

    static void readAndWriteMonthlyReport(String[] month, ArrayList<String> fileContents, ArrayList<MonthlyReport> monthData, ReadFile dataFile ){
        for (int i = 0; i < month.length; i++) {
            fileContents.add(dataFile.readFileContentsOrNull("m.2021" + month[i] + ".csv"));
        }

        String[] lines = fileContents.get(0).split("\\n"); //использовал такой разделитель потому, что lineSeparator отрабатывал не так, как ожидалось
        MonthlyReport month01 = new MonthlyReport(lines);

        lines = fileContents.get(1).split("\\n");
        MonthlyReport month02 = new MonthlyReport(lines);

        lines = fileContents.get(2).split("\\n");
        MonthlyReport month03 = new MonthlyReport(lines);

        monthData.add(month01);
        monthData.add(month02);
        monthData.add(month03);

        System.out.println("Месячные отчеты успешно считаны");
    }

    static void printMonthlyReport(String[] month, ArrayList<MonthlyReport> monthData){
        for(int i = 0; i < month.length; i++){
            System.out.println("Рассматриваемый месяц: " + month[i]
                    + "\nСамый прибыльный товар за " + month[i] + ": " + monthData.get(0).getMostProfitOrExpense(monthData.get(i).monthlyDataIsNotExpense)
                    + "\nСамая большая трата: " + monthData.get(0).getMostProfitOrExpense(monthData.get(i).monthlyDataIsExpense));
        }
    }

    static void verifficationReport(ArrayList<MonthlyReport> monthData, ArrayList<YearlyReport> operationalDataYear){
        DataVerification verification = new DataVerification();
        System.out.println("Сверка данных трат");
        verification.checkData(monthData, operationalDataYear, 1);
        System.out.println("Сверка данных доходов");
        verification.checkData(monthData, operationalDataYear, 2);
    }
}