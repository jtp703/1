package eda1.practica02.parte03;

import java.util.ArrayList;

import eda1.auxiliar.BSTree;
import eda1.auxiliar.Pair;

public class MyTreeMap<K extends Comparable<K>,V> {
	
	private BSTree<Pair<K,V>> tree = new BSTree<Pair<K,V>>(); 
	
	public boolean put(K key, V value) {
		Pair<K,V> par = this.tree.find(new Pair<>(key, value));

		if(par == null){
			tree.add(new Pair<K,V>(key, value));
			return true;
		}else if(tree.contains(par)){
			tree.find(par).setValue(value);
		}
		return false;
	}
	
	public V get(K key) {
		Pair<K,V> aux = this.tree.find(new Pair<>(key, null));

		return (aux == null) ? null : aux.getValue();
	}
	
	public boolean containsKey(K key) {
		Pair<K,V> aux = this.tree.find(new Pair<>(key, null));
		if(aux == null){
			return false;
		}
		return true;
	}
	
	public void clear() {
		this.tree.clear();
	}
	
	public boolean isEmpty() {
		return this.tree.isEmpty();
	}

	public int size() {
		return this.tree.size();
	}

	public ArrayList<K> keySet(){
		ArrayList<K> resultado  = new ArrayList<>();
		for (Pair<K,V> value: tree ) {
			resultado.add(value.getKey());
		}
		return resultado;
	}
	
	public ArrayList<V> values(){
		ArrayList<V> resultado  = new ArrayList<>();
		for (Pair<K,V> value: tree ) {
			resultado.add(value.getValue());
		}
		return resultado;
	}
	
	public ArrayList<Pair<K,V>> entrySet(){
		ArrayList<Pair<K,V>> resultado  = new ArrayList<>();
		for (Pair<K,V> value: tree ) {
			resultado.add(value);
		}
		return resultado;
	}

	@Override
	public String toString() {
		return this.entrySet().toString();
	}
}
