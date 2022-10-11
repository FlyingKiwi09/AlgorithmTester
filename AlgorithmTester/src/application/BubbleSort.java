package application;

import java.util.Collections;

public class BubbleSort extends Sorter {

	public BubbleSort() {
		super();
		this.testName = "Bubble Sort";
	}
	
	@Override
	public int[] sort(int[] ints) {
	
		this.stepCount = 0;	
		boolean changes = true;
		while(changes) {
			changes = false;
			for(int i = 0 ; i < ints.length-1; i++ ) {
				stepCount++;
				if (ints[i] > ints[i+1]) {
					int A = ints[i];
					int B = ints[i+1];
					ints[i] = B;
					ints[i+1] = A;
					changes = true;
				}
			}
		}

		return ints;
	}

}
