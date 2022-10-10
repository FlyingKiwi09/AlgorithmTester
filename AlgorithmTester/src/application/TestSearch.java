package application;

public class TestSearch extends Searcher {

	public TestSearch() {
	}
	
	@Override
	public boolean search(int[] ints, int target) {
		
		for (int i : ints) {
			if (i == target) {
				return true;
			}
		}
		return false;
	}

}
