package eda1.practica01;

import java.util.Iterator;
import java.util.LinkedList;

public class Device implements Iterable<String> {
    private static int numDevices = 0; //contador de dispositivos...atributo estatico
    private String deviceId = "";
    private LinkedList<String> words = new LinkedList<>();

    public static void initializeNumDevices() {
        numDevices = 0;
    }

    public Device(String name) {
        try {
            if (name == null) {
                throw new NullPointerException("El atributo name no puede ser nulo");
            }
        } catch (NullPointerException e) {
            System.out.println("El atributo name no puede ser nulo");
            throw e;
        }

        if (name.equals("")) {
            this.deviceId = ++numDevices + ".- " + "noName";
        } else {
            this.deviceId = ++numDevices + ".- " + name.trim();
        }
    }

    public void clear() {
        this.words.clear();
    }

    public int size() {
        return this.words.size();
    }

    public void sendMessage(String msg) {
        if (msg == null) return;
        String[] arrOfStr = msg.split(" ");

        for (String a : arrOfStr) {
            if (!words.contains(a.toLowerCase())) {

                words.add(a.toLowerCase());
                System.out.println("añadida palabra" + a);

            } else {

                System.out.println("no añadida palabra" + a);
            }
        }
    }

    public boolean contains(String word) {
        return this.words.contains(word.toLowerCase());
    }

    public boolean substitute(String word1, String word2) {
        if ((word1 == null || word2 == null)) {
            return false;
        }
        if (!words.contains(word1)) {
            return false;
        } else {
            for (int i = 0; i < words.size(); i++) {
                if (words.get(i).equals(word1.toLowerCase())) {
                    words.remove(i);
                    words.add(i, word2.toLowerCase());
                }
            }
        }
        return true;
    }

    public boolean remove(String word) {
        if (word == null) {
            return false;
        } else {
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
        return this.deviceId.equals(((Device) o).deviceId);
    }

    @Override
    public Iterator<String> iterator() {
        return this.words.iterator();
    }

}