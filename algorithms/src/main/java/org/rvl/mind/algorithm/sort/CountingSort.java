package org.rvl.mind.algorithm.sort;

public class CountingSort {

  public void sort(int[] arr, int range) {
    int[] countArr = countArr(arr);
    countArr = sumCountArr(countArr);
    outputArr(arr,countArr);
  }

  public int[] sumCountArr(int[] countArr) {
    for (int i = 1; i < countArr.length; i++) {
      countArr[i] = countArr[i - 1] + countArr[i];
    }
    return countArr;
  }

  public int[] outputArr(int[] arr, int sumCountArr[]) {
    int[] outputArr = new int[arr.length];

    for (int i = 0; i< arr.length; i++) {
      outputArr[i]=0;
    }

    for (int i = 0; i < arr.length; i++) {
      int value = arr[i];
      int valueCountArr=sumCountArr[value];
      outputArr[valueCountArr-1]=value;
      sumCountArr[value] = sumCountArr[value]-1;
    }

    return outputArr;
  }

  public int[] countArr(int[] arr) {

    int maxRange=-1;
    for (int i = 0; i < arr.length; i++) {
      if(arr[i]>maxRange) {
        maxRange = arr[i];
      }
    }

    int[] countArr = new int[maxRange+1];
    for (int i = 0; i <= maxRange; i++) {
      countArr[i] = 0;
    }

    for (int i = 0; i < arr.length; i++) {
      int x = arr[i];
      countArr[x] = countArr[x]+1;
    }

    return countArr;
  }

}
