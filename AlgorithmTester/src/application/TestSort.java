package application;

public class TestSort extends Sorter{

	
	
	public TestSort() {
		super();
	}

	
	@Override
	public int[] sort(int[] ints) {
		this.stepCount = 0;
		
		for (int i = 0; i < ints.length; i++) {
			try {
			Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.stepCount++;
		}
			
		return null;
	}


}
