package eda1.practica02.Parte01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Empresa implements Comparable<Empresa>, Iterable<Proyecto> {
	
	private String empresaID;
	private ArrayList<Proyecto> proyectos;
	
	
	public Empresa(String empresaID) {
		this.empresaID = empresaID.trim().toLowerCase();
		this.proyectos = new ArrayList<>();
	}

	public boolean add(String proyectoID, String...ciudades) {

		Proyecto proyectoAux = new Proyecto(proyectoID);
		Proyecto proyectoCurr = null;
		int pos = proyectos.indexOf(proyectoAux);
		if(proyectos.contains(proyectoAux)){
			for (String c: ciudades) {
				proyectos.get(pos).add(c);
			}
		}else{
			proyectos.add(proyectoAux);
			for (String c: ciudades) {
				proyectoAux.add(c);
			}
		}
			return pos == -1;
	}

	public String getEmpresaID(){
		return this.empresaID;
	}
	
	public int size() {
		return proyectos.size();
	}
	
	public Proyecto find(String proyectoId){
		int pos ;
		for (int i = 0; i < proyectos.size(); i++) {
			if (proyectos.get(i).getProyectoID().equals(proyectoId.trim().toLowerCase())) {
				pos = i;
				return proyectos.get(pos);
			}
		}
		return null;
	}

	public void clear() {
		this.proyectos.clear();
	}
	
	public void sort() {
		Collections.sort(proyectos);
		for(Proyecto p: proyectos){
			p.sort();
		}
	}
	
	@Override
	public String toString(){
		return this.empresaID + " -> " + (proyectos.isEmpty() ? "<empty>" : proyectos.toString());
	}

	@Override
	public boolean equals(Object o) {
		return this.compareTo((Empresa)o) == 0;
	}
	
	@Override
	public int compareTo(Empresa otra) {
		return this.empresaID.compareTo(otra.empresaID);
	}

	@Override
	public Iterator<Proyecto> iterator() {
		return proyectos.iterator();
	}
}
