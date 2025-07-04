package calculator;

import java.util.ArrayList;

public class Calculator implements Operations {
    ArrayList<Double> history = new ArrayList<>();
    double last_result;

    @Override
    public double addition(double num1, double num2) {
        last_result = num1+num2;
        setLastResult(last_result);
        return last_result;
    }

    @Override
    public double subtraction(double num1, double num2) {
        last_result = num1-num2;
        setLastResult(last_result);
        return last_result;
    }

    @Override
    public double multiplication(double num1, double num2) {
        last_result = num1*num2;
        setLastResult(last_result);
        return last_result;
    }

    @Override
    public double division(double num1, double num2){
        try {
            last_result = num1 / num2;
            setLastResult(last_result);
            System.out.println("Done");
            return last_result;
        }catch (ArithmeticException e){
            throw new ArithmeticException("you cannot divide on zero");
        }
    }

    @Override
    public double modulus(double num1, double num2){
        last_result = num1%num2;
        setLastResult(last_result);
        return last_result;
    }

    @Override
    public double minimum(double num1, double num2) {
        last_result = Math.min(num1, num2);
        setLastResult(last_result);
        return last_result;
    }

    @Override
    public double maximum(double num1, double num2) {
        last_result = Math.max(num1,num2);
        setLastResult(last_result);
        return last_result;
    }

    public double average(ArrayList<Double> numbers_list) {
        double total = 0;
        for (Double n : numbers_list){
            total+=n;
        }
        last_result = total/numbers_list.size();
        setLastResult(last_result);
        return last_result;
    }

    @Override
    public double getLastResult() {
        return last_result;
    }

    @Override
    public ArrayList<Double> listAllResultsHistory() {
        return history;
    }

    @Override
    public void showMenu() {
        System.out.println("""
                chose operation:
                |-------------------------------------------------------|
                |Enter 1 to addition the numbers                        |
                |-------------------------------------------------------|
                |Enter 2 to subtraction the numbers                     |
                |-------------------------------------------------------|
                |Enter 3 to multiplication the numbers                  |
                |-------------------------------------------------------|
                |Enter 4 to division the numbers                        |
                |-------------------------------------------------------|
                |Enter 5 to modulus the numbers                         |
                |-------------------------------------------------------|
                |Enter 6 to find minimum number                         |
                |-------------------------------------------------------|
                |Enter 7 to find maximum number                         |
                |-------------------------------------------------------|
                |Enter 8 to find the average of numbers                 |
                |-------------------------------------------------------|
                |Enter 9 to print the last result in calculator         |
                |-------------------------------------------------------|
                |Enter 10 to print the list of all results in calculator|
                |-------------------------------------------------------|
                |Enter -1 to exit                                       |
                |-------------------------------------------------------|
                """);
    }

    private void setLastResult(double total){
        last_result = total;
        history.add(total);
    }

}
