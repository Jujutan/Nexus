package Nexus.Util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


public  class BiMap<K, V> implements Map<K,V>{
	HashMap<K,V> map;
	LinkedList<EntrySet<K,V>> entrys;
	public BiMap(){
		map = new HashMap<K, V>();
		entrys = new LinkedList<EntrySet<K,V>>();
	}

	@SuppressWarnings({ "rawtypes" })
	public BiMap<V,K> inverse(){
		BiMap<V,K> var1 = new BiMap<V,K>();
		for(EntrySet t : entrys.toArray(new EntrySet[]{})){
			var1.addEntrySet(t.inverse());
		}
		return var1;
	}


	@Override
	public int size() {
		return entrys.size();
	}

	@Override
	public boolean isEmpty() {
		return entrys.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		Iterator<EntrySet<K, V>> i = entrys.iterator();
		while(i.hasNext()){
			if(i.next().getKey().equals(key)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		Iterator<EntrySet<K, V>> i = entrys.iterator();
		while(i.hasNext()){
			if(i.next().getVal().equals(value)){
				return true;
			}
		}
		return false;
	}

	@Override
	public V get(Object key) {
		Iterator<EntrySet<K, V>> i = entrys.iterator();
		EntrySet<K,V> var1;
		while(i.hasNext()){
			var1 = i.next();
			if(var1.getKey().equals(key)){
				return var1.getVal();
			}
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		entrys.add(new EntrySet<K,V>(key,value));
		return value;
	}

	@Override
	public V remove(Object key) {
		for(int i = 0; i < entrys.size(); i++){
			if(entrys.get(i).getKey().equals(key)){
				return entrys.remove(i).getVal();
			}
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		entrys.clear();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Set<K> keySet() {
		Set<K> var1 = new HashSet<K>();
		for(EntrySet t : this.entrys){
			var1.add((K) t.getKey());
		}
		return var1;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection<V> values() {
		LinkedList<V> var1 = new LinkedList<V>();
		for(EntrySet t : this.entrys){
			var1.add((V) t.getVal());
		}
		return var1;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addEntrySet(EntrySet entry){
		this.entrys.add(entry);
	}
	@SuppressWarnings({ "unused", "hiding" })
	private class EntrySet<K,V>{
		private K key;
		private V val;
		public EntrySet(K par1Key, V par2Val) {
			this.setKey(par1Key);
			this.setVal(par2Val);
		}
		public EntrySet() {
			this(null,null);
		}
		public K getKey() {
			return key;
		}
		public void setKey(K key) {
			this.key = key;
		}
		public V getVal() {
			return val;
		}
		public void setVal(V val) {
			this.val = val;
		}

		public EntrySet<V,K> inverse(){
			return new EntrySet<V,K>(this.val,this.key);
		}
	}
}
