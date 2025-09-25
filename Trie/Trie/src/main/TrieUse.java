package main;

public class TrieUse {
	public static void main(String[] args) {
		Trie trie = new Trie();
		
		trie.add("NEW");
		trie.add("NEWS");
		trie.delete("AA");
		trie.add("BUT");
		trie.add("BYE");
		trie.add("TATA");
		trie.add("A");
		System.out.println(trie.search("NEQ"));
		System.out.println(trie.search("NEXT"));
		trie.delete("NEW");
		System.out.println(trie.search("BYE"));
		trie.delete("BYE");
		System.out.println(trie.search("BYE"));
		trie.delete("A");
		System.out.println(trie.search("A"));
	}
	
}
