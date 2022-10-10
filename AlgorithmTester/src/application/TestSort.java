package application;

public class TestSort extends Sorter{

	public TestSort() {
	}

	
	@Override
	public int[] sort(int[] ints) {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}


}
