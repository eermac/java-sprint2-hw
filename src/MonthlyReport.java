import java.util.HashMap;

public class MonthlyReport {
    HashMap<String, Integer> monthlyDataIsExpense = new HashMap<>();
    HashMap<String, Integer> monthlyDataIsNotExpense = new HashMap<>();

    MonthlyReport(String[] monthlyData){
        for(int i = 1; i < monthlyData.length; i++) {
            String[] lineContents =  monthlyData[i].split(",");
            if(lineContents[1].equals("FALSE")){
                monthlyDataIsNotExpense.put(lineContents[0], Integer.parseInt(lineContents[2]) * Integer.parseInt(lineContents[3]));
            } else {
                monthlyDataIsExpense.put(lineContents[0], Integer.parseInt(lineContents[2]) * Integer.parseInt(lineContents[3]));
            }
        }
    }

    String getMostProfitOrExpense(HashMap<String, Integer> expense){
        int bestProduct = 0;
        String nameProduct = "";
        for(Integer cost: expense.values()){
            if(bestProduct < cost){
                bestProduct = cost;
            }
        }

        for(String name: expense.keySet()){
            if(bestProduct == expense.get(name)){
                nameProduct = name;
            }
        }

        return nameProduct + ": " + bestProduct;
    }
}
