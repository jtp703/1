package eda1.practica02.Parte01;

import java.util.ArrayList;

import java.util.Scanner;

import eda1.auxiliar.AVLTree;


import java.io.File;
import java.io.IOException;

public class GestionEmpresas {

	private AVLTree<Empresa> empresas = new AVLTree<Empresa>();

	/**
	 * Cargar archivo
	 * Metodo empleado para introducir valores al arbol a partir del fichero que se indica por parametro,
	 * Este metodo en primer lugar leera cada linea del fichero y usara dicha linea segun cumpla los requisitos de no
	 * contener @ o de ser una linea vacia.
	 * Si no ocurre esta anterior, divide cada palabra con el metodo split para obtener cada valor, en este caso cada
	 * linea esta compuesta por Empresa - Proyecto - Ciudad, por lo tanto en ese orden se iran guardando en variables
	 * que mas tarde crear el objeto de tipo empresa si este no existiese o buscan el objeto y lo insertan dentro de él.
	 * @param file
	 */
	public void cargarArchivo(String file) {
		Scanner scan = null;
		String line;
		String[] items;
		Empresa empresa = null;
		String proyecto = "";
		String ciudad = "";

		this.empresas.clear();
		try {
			scan = new Scanner(new File(file));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		while (scan.hasNextLine()) {
			line = scan.nextLine().trim();
			String[] arrOfStr = line.split(" - ");
			if(line.isEmpty() || line.contains("@")){
				continue;
			}
			add(arrOfStr[0],arrOfStr[1],arrOfStr[2]);
		}
		scan.close();
	}
	
	public void sort() {
		for (Empresa e:empresas) {
			e.sort();
		}
	}

	public boolean add(String empresaID, String proyectoID, String ciudad) {
		Empresa empresaAux = new Empresa(empresaID);
		Empresa empresaCurr = this.empresas.find(empresaAux);

		if(empresaCurr == null){
			this.empresas.add(empresaAux);
			empresaAux.add(proyectoID, ciudad);

		}else{
			empresaCurr.add(proyectoID, ciudad);
		}

		return empresaCurr == null;
	}

	public int size() {
		return this.empresas.size();
	}

	public int numeroProyectosEmpresa(String empresaID) {
		Empresa empresaCurr = new Empresa(empresaID);
		Empresa e = empresas.find(empresaCurr);
		int numeroProyectos = -1;

		if (e != null) {
			numeroProyectos = e.size();
		}
		return numeroProyectos;
	}

	public int numeroCiudadesProyecto(String proyectoID) {
		Proyecto proyectoCurr;
		int numeroCiudades = -1;

		for (Empresa e: this.empresas){
			proyectoCurr = e.find(proyectoID);
			if(proyectoCurr != null){
				numeroCiudades = proyectoCurr.size();
			}
		}
		return numeroCiudades;
	}

	public int numeroCiudadesEmpresa(String empresaID) {
		ArrayList<String> ciudades;
		Empresa currentEmpresa = this.empresas.find(new Empresa(empresaID));

		if(currentEmpresa == null){
			return -1;
		}

		ciudades = new ArrayList<>();
		for (Proyecto p : currentEmpresa){
			for(String s:p){
				if(!ciudades.contains(s)){
					ciudades.add(s);
				}
			}
		}

		return ciudades.size();
	}

	@Override
	public String toString() {
		String cadena = "";
		for (Empresa e:empresas) {
			cadena += e.toString() + "\n";
		}

		return cadena;
	}

	public String devolverEmpresasCiudad(String ciudad) {
		ArrayList<String> empresasIDaux = new ArrayList<>();
		for(Empresa e : this.empresas){
			for (Proyecto p: e){
				if(p.contains(ciudad)){
					empresasIDaux.add(e.getEmpresaID());
					break;
				}
			}
		}
		if(empresasIDaux.isEmpty()){
			return "<empty>";
		}else{
			return empresasIDaux.toString();
		}

	}

	public String devolverProyectosCiudad(String ciudad) {
		ArrayList<String> proyectosIDaux = new ArrayList<>();

		for(Empresa e : this.empresas){
			for(Proyecto p : e){
				if(p.contains(ciudad)){
					proyectosIDaux.add(p.getProyectoID());
				}
			}
		}

		if(proyectosIDaux.isEmpty()){
			return "<empty>";
		}else{
			return proyectosIDaux.toString();
		}
	}
	
	 public String devolverCiudadesEmpresa(String empresaID) {
		 ArrayList<String> ciudades;
		 Empresa empresaCurr= this.empresas.find(new Empresa(empresaID));
		 if (empresaCurr == null) return "<empty>";
		 ciudades = new ArrayList<>();
		 for(Proyecto p : empresaCurr){
			for (String s : p){
				if(!ciudades.contains(s)){
					ciudades.add(s);
				}
			}
		 }

		 return ciudades.toString();
	}
}
