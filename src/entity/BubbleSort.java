package entity;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] getallen = {9,22,34,7,11,2};

        for (int i = 0; i < getallen.length; i++){
            System.out.print(getallen[i]+ ", ");
        }
        System.out.println();

        bubbleSort(getallen);
    }
    public static void bubbleSort(int[] arr) {
        int temp;
        boolean swapped;
        for (int i = arr.length - 1; i > 0; i--) {
            swapped = false;
            for (int j = 0; j < i; j++) {

                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped = false){
                break;
            }
        }

        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i]+ ", ");
        }

    }
}
