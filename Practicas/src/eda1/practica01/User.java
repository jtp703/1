package src.eda1.practica01;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class User{
	private static int numUsers = 0;
	private String userId = "";
	private ArrayList<Device> devices = new ArrayList<>();
	private static int id = 1;
	
	public static void initializeNumUsers() {
		numUsers = 0;
	}
	
	public User(String name) {
		//3 lineas
		//...
		try
		{
			if(name == null || name == "") {
				throw new NullPointerException("El atributo name no puede ser ni nulo ni vacio");
			}
		}
		catch(NullPointerException e)
		{
			System.out.println("El atributo name no puede ser nulo");
			throw e;
		}

		if(!name.equals("") && !name.equals(null)) {
			this.userId = id +".- "+ name.trim();
			++id;

		}

	}
	
	public void clear() {
		//1 for()
		//...
		devices.clear();
	}
	
	
	public boolean addDevices(Device... devs) {
		if (devs == null){
			return false;
		}else{
			for(Device newDev : devs){
				if(!devices.contains(newDev)) {
					devices.add(newDev);
				}
				//devices.add(newDev);
			}
		}
		//1 for()
		//...

		return true;
	}
	
	public int getNumDevices() {
		return this.devices.size();
	}

	public boolean loadMessages(Device dev, String fileName) {
		//Importante que tengas muy claro los pasos que seguimos para leer un archivo de texto...
		//Si no lo tienes claro, pregunta...
		Scanner scan = null;
		String line; 
		if (!this.devices.contains(dev)) return false;
		try {
			scan = new Scanner(new File(fileName));
		}catch(Exception e) {
			System.out.println("Error: no encuentra el archivo");
			return false;
		}
		while (scan.hasNextLine()) {
			line = scan.nextLine();
			if (line.isEmpty()){
				continue;
			}else{
				dev.sendMessage(line);
			}
			//1 linea
			//...
		}
		scan.close();
		return true;
	}
	
	public boolean sendMessage(Device dev, String msg) {
		//Si dev es nulo o el contenedor no contiene al dispositivo dev se devuelve false
		//En caso contrario se envia el mensaje...
		//2 lineas
		//...
		if(dev == null || devices.isEmpty()){
			return false;
		}
		if(!devices.contains(dev)){
			return false;
		}
		dev.sendMessage(msg);
		return true;
	}
	
	public void substitute(String word1, String word2) {
		if (word1 == null || word2 == null) return;
		//1 for()
		//...
		for (int i = 0; i < devices.size(); i++) {
			devices.get(i).substitute(word1,word2);
		}

	}
	
	public void remove(String word) {
		if (word == null) return;
		//1 for()
		//...
		for (int i = 0; i < devices.size(); i++) {
			if (devices.get(i).contains(word)) {
				devices.get(i).remove(word);
			}
		}
	}
	
	public boolean contains(String word) {
		//1 for()
		/*
		 * for () {
		 
			//1 linea
		}*/

		Device device ;
		for (int i = 0; i < devices.size(); i++) {
			device = devices.get(i);
			Iterator iteratorWords = device.iterator();
			while (iteratorWords.hasNext()){
				String word2 = iteratorWords.next().toString();
				if(word2.equals(word)){
					return true;
				}
			}

		}

		return false;
	}
	
	public String getWords() {
		String result = "";
		Device device ;
		//Tened en cuenta que los dispositivos pueden no contener ninguna palabra; si ocurre este caso, lo ignoramos
		//2 for()
		//...
		for (int i = 0; i < devices.size(); i++) {
			device = devices.get(i);
			if (device.size() != 0) {
				Iterator iteratorWords = device.iterator();
				result = result + device.toString() + ": ";
				while (iteratorWords.hasNext()){
					result = result + iteratorWords.next() + " ";
				}
				result = result + "\n";
			}
		}
		return result;

	}
	
	public ArrayList<String> getOrderedWords() {
		ArrayList<String> result = new ArrayList<String>();
		Device device ;
		//2 for()
		//...
		/*Crear bucle que añada en orden alfabetico las palabras, y sin repetir ninguna.*/

		for (int i = 0; i < devices.size(); i++) {
			device = devices.get(i);
			if (device.size() != 0) {
				Iterator iteratorWords = device.iterator();
				while (iteratorWords.hasNext()){
					String word = iteratorWords.next().toString();
					if (!result.contains(word)){
						result.add(word);
					}
				}
			}
		}

		Collections.sort(result);

		//Recuerda: orden ascencente (de menor a mayor)
		return result;
	}
	
	@Override
	public String toString() {
		return this.userId;
	}
}