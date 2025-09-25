package main;

public class TrieNode {
	char data;
	TrieNode[] children;
	boolean isTerminating;
	int childCount;
	
	public TrieNode(char chr) {
		this.data = chr;
		children = new TrieNode[26];
		isTerminating = false;
		childCount = 0;
	}
}
