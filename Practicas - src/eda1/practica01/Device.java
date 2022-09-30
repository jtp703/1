package eda1.practica01;

import java.util.Iterator;
import java.util.LinkedList;

public class Device implements Iterable<String>{
	private static int numDevices=0; //contador de dispositivos...atributo estatico
	private String deviceId = "";
	private LinkedList<String> words = new LinkedList<>();
	private static int id = 1;
		
	public static void initializeNumDevices() {
		numDevices = 0;
	}
	
	public Device(String name){
		//Si name == null --> excepcion
		//Si name es vacio, el nombre del dispositivo sera "noName"
		//this.deviceID = id + ".- " + name
		//3 lineas...
		//...
		try
        {
			if(name == null) {
             throw new NullPointerException("El atributo name no puede ser nulo");
            }
        }
        catch(NullPointerException e)
        {
            System.out.println("El atributo name no puede ser nulo");
            throw e; 
        }
		//other option
		//if(name == null) throw new RuntimeException("El atributo name no puede ser nulo");

		if(name.equals("")) {
			this.deviceId = id +".- "+"noName";
		}else{
			this.deviceId = id + ".- " + name.trim();
		}
		//words.add(deviceId);
		++numDevices;
		++id;
	}
	
	public void clear() {
		this.words.clear();
	}
	
	public int size() {
		return this.words.size();
	}
	
	public void sendMessage(String msg) {
		if (msg == null) return;
		//Haz uso del metodo split(" ") para "trocear" la cadena y acceder a cada palabra de forma independiente
		//Un mensaje es un conjunto de palabras separadas por el caracter " " (un espacio en blanco)
		//Ten en cuenta que no queremos palabra repetidas
		//1 for()
		//...

		String[] arrOfStr = msg.split(" ");

		for (String a : arrOfStr) {
			//TODO if posiblemente no necesario
			if(!words.contains(a.toLowerCase())) {
				words.add(a.toLowerCase());
				System.out.println("añadida palabra"+a);
			}else{
				System.out.println("no añadida palabra"+a);
			}

		}



	}
	
	public boolean contains(String word) {
		return this.words.contains(word.toLowerCase());
	}
	
	public boolean substitute(String word1, String word2) {
		//Prohibido hacer uso de ListIterator<>
		//Si word1 o word2 es igual a null salimos del metodo (false)
		//Si no se encuentra la palabra word1 en el contenedor words, salimos del metodo (false)
		//En caso contrario se sustituye word1 por word2 y salimos del metodo (true)
		//...

		if((word1 == null || word2 == null)){
			return false;
		}
		if(!words.contains(word1)){
			return false;
		}else{
			for (int i = 0; i < words.size(); i++) {
				if (words.get(i).equals(word1.toLowerCase())) {
					words.remove(i);
					words.add(i,word2.toLowerCase());
				}
			}
		}


		return true;
	}
	
	public boolean remove(String word) {
		//Si word es nulo se rompe la ejecucion del metodo devolviendo false (precondicion)
		//2 lineas
		//...

		if(word == null){
			return false;
		}else{
			words.remove(word.toLowerCase());
		}
		return true;
	}
	
	@Override
	public String toString() {
		return this.deviceId;
	}
	
	@Override
	public boolean equals(Object o) {
		return this.deviceId.equals(((Device)o).deviceId);
	}

	@Override
	public Iterator<String> iterator() {
		//Iterar sobre Device equivale a iterar sobre la estructura LinkedList<String> words
		//1 unica linea
		return this.words.iterator();
	}
	
}