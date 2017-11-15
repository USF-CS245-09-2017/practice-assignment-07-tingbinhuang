// Practice assignment 7 from CS 245 Section I
// Author Tingbin Huang 

import java.util.*;

public class Hashtable {
	
	/*
		Initialize an inner class, HashNode. 
	*/
	class HashNode{
		private String key;
		private String value; 
		private HashNode next; 
		
		public HashNode(String newKey, String newValue){
			this.key = newKey;
			this.value = newValue;
		}
		
		public void setNext(HashNode node){
			next = node; 
		}
		
		public HashNode next(){
			return next; 
		}
		
		public String getValue(){
			return value; 
		}
		
		public String getKey(){
			return key; 
		}
		
		public void setNewValue(String newValue){
			this.value = newValue;
					
		}
	}
	
	/**
	 * declare class members for 'Hash-table'. 
	 */
	private ArrayList<HashNode> arrayList;
	private final int NUM_BUCKET = 300000; 
	private int size; 
	
	/**
	 * Constructor for Hash-table.
	 */
	public Hashtable(){
		arrayList = new ArrayList<>();
		size = 0; 
		
		for(int i = 0; i < NUM_BUCKET; i++){
			arrayList.add(null);
		}
	}
	
	/**
	 * return size of Hash-table.
	 */
	public int size(){
		return size; 
	}
	
	/**
	 * return index associated with key/value from arrayList. 
	 * @param String key
	 * @return int index
	 */
	public int getIndex(String key){
		int hashCode = key.hashCode();
		int index = Math.abs(hashCode) % NUM_BUCKET;
		return index; 
	}
	
	/**
	 * return value associated with key.
	 * @param String key
	 * @return String value
	 */
	public String get(String key){
		int index = getIndex(key);
		HashNode headNode = arrayList.get(index);
		
		while(headNode != null){
			if(headNode.getKey().equals(key)){
				return headNode.getValue();
			}
			headNode = headNode.next();
		}
		return null; 
	}
	
	/**
	 * add key/value to hash-table.
	 * @param String key
	 * @param String value
	 */
	public void put(String key, String value){
		HashNode headNode = null;
		
		int index = getIndex(key);
		headNode = arrayList.get(index);
		
		while(headNode != null){
			if(headNode.getKey() == key){
				headNode.setNewValue(value); //set new value if key already exists.
			}
			headNode = headNode.next();
		}
		size++;
		
		HashNode head = arrayList.get(index);
		HashNode newNode = new HashNode(key, value);
		newNode.setNext(head);
		
		arrayList.set( index, newNode);
	}
	
	/**
	 * check if hash-table contains key. 
	 * @param String key
	 * @return true if it has the key false otherwise.
	 */
	public boolean containsKey(String key){
		int index = getIndex(key);
		HashNode node = arrayList.get(index);
			
		while(node != null){
			if(node.getKey().equals(key)){
				return true;
			}
			node = node.next();
		}
		return false;
	}
	
	/**
	 * remove a hashNode of key/value from hash-table and return value deleted.
	 * @param String key
	 * @return String value
	 */
	public String remove(String key){
		int index = getIndex(key);
		HashNode node = arrayList.get(index);
		HashNode pre = null; 
		
		while(node != null){
			if(node.getKey().equals(key)){
				String val = node.getValue();
				if(node.next() == null){
					node = node.next;
					arrayList.set(index, node);
				} else{
					pre.setNext(node.next());
					
				}
				size--;
				return val;
			}
			
			pre = node;
			node = node.next();
		}
			
		return null;
	}
	
	
}
