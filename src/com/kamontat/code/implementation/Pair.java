package com.kamontat.code.implementation;

/**
 * @author kamontat
 * @version 1.0
 * @since Sun 05/Mar/2017 - 3:29 AM
 */
public class Pair<K, V> {
	private K key;
	private V value;
	
	public Pair() {
		key = null;
		value = null;
	}
	
	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	public V getValue() {
		return value;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
	
	public boolean isKeyContains(K key) {
		return this.key.equals(key);
	}
	
	public boolean isValueContains(V value) {
		return this.value.equals(value);
	}
	
	public boolean isContains(Object obj) {
		return obj.equals(key) || obj.equals(value);
	}
}
