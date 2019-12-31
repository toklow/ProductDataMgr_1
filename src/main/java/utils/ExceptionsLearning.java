package utils;

import exception.NumberNotFoundException;

// For exceptions learning purposes
public class ExceptionsLearning {

    public static boolean isFoundNumber(int [] numbers, int element) throws NumberNotFoundException {
        for(int number : numbers) {
            if (number == element) {
                return true;
            }
        }
        throw new NumberNotFoundException(element + " not found");
    }
}
