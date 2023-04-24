package algorithms;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public final class QuickSort {
	private QuickSort() {}
	
	public static void main(String[] args) {
		ArrayList<Integer> values = new ArrayList<>(Arrays.asList(8, 6, 10, 2, 15, 1, 20, 11));
		QuickSort.sortFromLow(values, 0, values.size() - 1);
		System.out.println(values);
	}

	public static void sort(List<Integer> values, int low, int high) {
		if (low >= high) return;

		// take the last element as the partition value. Ideally, we should shuffle the values first but we skip the shuffle for simplicity.
		int partitionValue = values.get(high);
		
		// rearrange the list by the partition value and put the partitionValue in the correct position.
		int partition = low;
		for (int i = low; i < high; i++) {
			if (values.get(i) < partitionValue) {
				swap(values, partition, i);
				partition += 1;
			}
		}
		swap(values, high, partition);
		
		sort(values, low, partition - 1);
		sort(values, partition + 1, high);
	}
	
	public static void sortFromLow(List<Integer> values, int low, int high) {
		if (low >= high) return;
		
		// take the first element as the partition value. Ideally, we should shuffle the values first but we skip the shuffle for simplicity.
		int partitionValue = values.get(low);
		
		// put the partitionValue in the correct position.
		int partition = low + 1;
		for (int i = low + 1; i <= high; i++) {
			if (values.get(i) < partitionValue) {
				swap(values, partition, i);
				partition += 1;
			}
		}
		int targetPosition = partition - 1;
		swap(values, low, targetPosition);
		
		sort(values, low, targetPosition - 1);
		sort(values, targetPosition + 1, high);
	}
	
	private static void swap(List<Integer> values, int index1, int index2) {
		int value1 = values.get(index1);
		values.set(index1, values.get(index2));
		values.set(index2, value1);
		return;
	}
	
}
