package application;

import java.util.ArrayList;

public class InsertSort extends Sorter {

	public InsertSort() {
		super();
		this.testName = "Insert Sort";
	}
	
	@Override
	public int[] sort(int[] ints) {
	
		this.stepCount = 0;
		for (int loop = 1; loop < ints.length; loop++) {
			int toInsert = ints[loop];
			int hole = loop;
			while ((hole > 0) && (ints[hole - 1] > toInsert)) {
				this.stepCount++;
				ints[hole] = ints[hole - 1];
				hole--;
			}
			ints[hole] = toInsert;
		}
		
		return ints;
	}

}
