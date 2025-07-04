package calculator;

import java.util.ArrayList;

public interface Operations {
    //Enter 1 to addition the numbers
    public double addition(double num1, double num2);

    //Enter 2 to subtraction the numbers
    public double subtraction(double num1, double num2);

    //Enter 3 to multiplication the numbers
    public double multiplication(double num1, double num2);

    //Enter 4 to division the numbers
    public double division(double num1, double num2);

    //Enter 5 to modulus the numbers
    public double modulus(double num1, double num2);

    //Enter 6 to find minimum number
    public double minimum(double num1, double num2);

    //Enter 7 to find maximum number
    public double maximum(double num1, double num2);

    //Enter 8 to find the average of numbers
    public double average(ArrayList<Double> numbers_list);

    //Enter 9 to print the last result in calculator
    public double getLastResult();

    //Enter 10 to print the list of all results in calculator
    public ArrayList<Double> listAllResultsHistory();

    public void showMenu();
}
