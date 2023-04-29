/**
 * 
 */
package trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rtalreja
 *
 *Very clear explanation can be found here - https://www.youtube.com/watch?v=m9zawMC6QAI
 */

public class Trie {
	TrieNode root = new TrieNode();
	public void insert(String s) {
		this.root.insert(s);
	}
	public static void main(String[] args) {
		Trie t = new Trie();
		t.insert("mouse");
		t.insert("mobile");
		t.insert("mice");
		t.insert("apple");
		System.out.println(t);
		TrieNode n = t.search("ab");
		
		System.out.println(n);
	}
	public TrieNode search(String prefix) {
		return this.root.search(prefix);
	}
}

/**
 * 
 * @author iswitch
 *
 */
class TrieNode{
	Map<Character,TrieNode> trieNodes = new HashMap<>();
	boolean isEndOfWord;
	public void insert(String s) {
		if(s == null || s.length() == 0) {
			return;
		}
		Character c = s.charAt(0);
		String rem = s.substring(1);
		TrieNode n = null;
		if(trieNodes.get(c) == null) {
			n = new TrieNode();
			this.trieNodes.put(c, n);
		}else {
			n = trieNodes.get(c);
		}
		n.insert(rem);
		if(rem.length() == 0) {
			n.isEndOfWord = true;
		}
	}
	public TrieNode search(String prefix) {
		if(prefix == null || prefix.length() == 0) {
			return this;
		}
		Character c = prefix.charAt(0);
		if(this.trieNodes.get(c) == null) {
			return null;
		}else {
			TrieNode t = this.trieNodes.get(c);
			return t.search(prefix.substring(1));
		}
	}
	
}
