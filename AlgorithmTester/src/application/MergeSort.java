package application;

public class MergeSort extends Sorter {

	public MergeSort() {
		super();
		this.testName = "Merge Sort";
	}
	
	@Override
	public int[] sort(int[] ints) {
	
	for (int i: ints) {
		System.out.println(i);
	}
		
	return mergeSort(ints, 0, ints.length-1);
	}
	
	public int[] mergeSort(int[] ints, int beg, int end) {
		
		if (ints.length > 1) {
			int[] firstHalf = new int[ints.length/2];
			int[] secondHalf;
			if (ints.length%2 == 0) {
				secondHalf = new int[ints.length/2];
			} else {
				secondHalf = new int[ints.length/2 + 1];
			}
			
			
			for (int i = 0; i < ints.length/2; i++) {
				firstHalf[i] = ints[i];
			}
			
			int count = 0;
			for (int i = ints.length/2; i < ints.length; i++) {
				secondHalf[count] = ints[i];
				count++;
			}
			
			firstHalf = mergeSort(firstHalf);
			secondHalf = mergeSort(secondHalf);
			
			int[] combined = new int[firstHalf.length + secondHalf.length];
			
			for (int i = 0; i < firstHalf.length; i++) {
				for (int j = 0; j < secondHalf.length; j++) {
					if (firstHalf[i] < secondHalf[j]) {
						combined[i+j] = firstHalf[i];
					} else {
						combined[i+j] = secondHalf[j];
					}
				}
			}
			
			System.out.println("next");
			for (int i: combined) {
				System.out.println(i);
			}
			
			return combined;
			
		} else {
			return ints;
		}
		
	}
}
