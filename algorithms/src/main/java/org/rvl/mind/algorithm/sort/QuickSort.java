package org.rvl.mind.algorithm.sort;

import org.rvl.mind.port.sort.Sort;

import java.util.List;

public class QuickSort implements Sort {

    private final Swap swap;

    public QuickSort(Swap swap) {
        this.swap = swap;
    }

    @Override
    public List<Integer> sort(List<Integer> list) {
        return sort(list, Direction.ASC);
    }

    @Override
    public List<Integer> sort(List<Integer> list, Direction direction) {
        quickSort(list, 0, list.size() - 1);
        return list;
    }

    private void quickSort(List<Integer> list, int start, int end) {
        if (start < end) {
            int pIndex = partition(list, start, end);
            quickSort(list, start, pIndex - 1);
            quickSort(list, pIndex + 1, end);
        }
    }

    public int partition(List<Integer> list, int start, int end) {
        int pivot = list.get(end);
        int partitionIndex = start - 1;
        for (int i = start; i <= end - 1; i++) {

            if (list.get(i) < pivot) {
                partitionIndex++;
                swap.apply(list, partitionIndex, i);
            }
        }
        swap.apply(list, partitionIndex + 1, end);
        return partitionIndex + 1;
    }

}
