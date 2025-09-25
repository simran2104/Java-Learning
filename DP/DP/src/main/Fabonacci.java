package main;

public class Fabonacci {
	
	public int fibonacci(int num) {
		if(num == 0 || num == 1) {
			return num;
		}
		return fibonacci(num-1) + fibonacci(num-2);
	}
	
	public int fibonacciM(int num) {
		int[] storage = new int[num+1];
		for(int i=0; i<num+1; i++) {
			storage[i] = -1;
		}
		return findFibonacci(num, storage);
	}
	
	public int fibonacciDP(int num) {
		int[] storage = new int[num+1];
		storage[0] = 0;
		storage[1] = 1;
		
		for(int i=2; i<= num; i++) {
			storage[i] = storage[i-1] + storage[i-2];
		}
		return storage[num];
	}
	
	private int findFibonacci(int num, int[] storage) {
		if(num == 0|| num==1) {
			storage[num] = num;
			return num;
		}
		if(storage[num] != -1) {
			return storage[num];
		}
		
		storage[num] = findFibonacci(num-1, storage) + findFibonacci(num-2, storage);
		return storage[num];
	}
}
