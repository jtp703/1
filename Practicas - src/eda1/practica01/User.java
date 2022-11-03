package eda1.practica01;

import java.io.File;
import java.util.*;

public class User implements Iterable<Device>{
	private static int numUsers = 0;
	private String userId = "";
	private ArrayList<Device> devices = new ArrayList<>();

	
	public static void initializeNumUsers() {
		numUsers = 0;
	}
	
	public User(String name) {

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
		String formatedName = name.trim().toLowerCase();
		formatedName = formatedName.substring(0, 1).toUpperCase() + formatedName.substring(1);
		if(formatedName.contains("@")){
			this.userId = formatedName;
		}else {
			this.userId = ++numUsers + ".- " + formatedName;
		}
	}

	@Override
	public boolean equals(Object obj) {
		User u1 = (User) obj;

		return this.userId.substring(this.userId.indexOf(" ")).equalsIgnoreCase(
				u1.userId.substring(u1.userId.indexOf(" ")));

	}

	public void clear() {
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
			}
		}
		return true;
	}
	
	public int getNumDevices() {
		return this.devices.size();
	}

	public boolean loadMessages(Device dev, String fileName) {
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
		}
		scan.close();
		return true;
	}
	
	public boolean sendMessage(Device dev, String msg) {
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

		for (int i = 0; i < devices.size(); i++) {
			devices.get(i).substitute(word1,word2);
		}

	}
	
	public void remove(String word) {
		if (word == null) return;

		for (int i = 0; i < devices.size(); i++) {
			if (devices.get(i).contains(word)) {
				devices.get(i).remove(word);
			}
		}
	}
	
	public boolean contains(String word) {
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
		return result;
	}
	
	@Override
	public String toString() {
		return this.userId;
	}

	@Override
	public Iterator<Device> iterator() {
		return this.devices.iterator();
	}
}