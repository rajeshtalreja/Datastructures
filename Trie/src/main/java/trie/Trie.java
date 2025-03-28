/**
 * 
 */
package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		t.insert("mobil");
		t.insert("mice");
		t.insert("apple");
		System.out.println(t);
		TrieNode n = t.search("mo");	
		//System.out.println(n);
		List<String> res = new ArrayList<String>();
		t.root.dfs(n, new ArrayList<>(), res);
		System.out.println(res);
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
			this.isEndOfWord = true;
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
	
	public void dfs(TrieNode n, List<Character> chars, List<String> res){
		
		if(n.isEndOfWord) {
			String string = Stream.of(chars).map(e -> e.toString()).collect(Collectors.joining());
			res.add(string);
		}
		for(int i=0;i<26;i++) {
			char c = (char)('a' + i);
			if(n.trieNodes.get(c) != null) {
				chars.add(c);
				dfs(n.trieNodes.get(c), new ArrayList<Character>(chars), res);
				chars.remove(Character.valueOf(c));
			}
		}
		
	}
	
}
