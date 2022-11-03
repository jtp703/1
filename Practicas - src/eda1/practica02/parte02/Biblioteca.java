package eda1.practica02.parte02;

import java.util.ArrayList;

import eda1.auxiliar.AVLTree;
import eda1.auxiliar.Pair;

public class Biblioteca {
	private String bibliotecaID;
 	private AVLTree<Libro> libros;
	private AVLTree<Usuario> usuarios;
	private AVLTree<Pair<Libro, Usuario>> prestamos;
	private AVLTree<Pair<Usuario, ArrayList<Libro>>> historicoPrestamos;
	
	
	public Biblioteca(String bibliotecaID) {
		this.bibliotecaID = bibliotecaID;
		this.libros = new AVLTree<>();
		this.usuarios = new AVLTree<>();
		this.prestamos = new AVLTree<>();
		this.historicoPrestamos = new AVLTree<>();
	}
	
	public boolean addLibro(Libro libro) {
		return this.libros.add(libro);
	}
	
	public boolean addLibro(String libroID) {
		return this.libros.add(new Libro(libroID));
	}
		
	public boolean addUsuario(Usuario usuario) {
		return this.usuarios.add(usuario);
	}

	public boolean addUsuario(String usuarioID) {
		return this.usuarios.add(new Usuario(usuarioID));
	}
	
	public void clear() {
		this.libros.clear();
		this.usuarios.clear();
		this.prestamos.clear();
		this.historicoPrestamos.clear();
	}
	
	public boolean prestarLibro(String usuarioID, String libroID) {
		Pair<Libro, Usuario> par = new Pair<>(this.libros.find(new Libro(libroID)), this.usuarios.find(new Usuario(usuarioID)));

		if (par.getValue() == null | par.getKey() == null | prestamos.contains(par)){
			return false;
		}else{
			guardarHistorio(par.getKey(), par.getValue());
			prestamos.add(par);

		}

		return true;
	}
	
	public boolean devolverLibro(String usuarioID, String libroID) {
		Pair<Libro, Usuario> par = new Pair<>(this.libros.find(new Libro(libroID)), this.usuarios.find(new Usuario(usuarioID)));

		if(par.getValue() == null | par.getKey() == null) {
			return false;
		}

		Pair<Libro, Usuario> par1 = prestamos.find(new Pair<>(par.getKey(), null));
		if(par1 == null || !par1.getValue().equals(par.getValue())){
			return false;
		}else{
			prestamos.remove(par1);
			return true;
		}

	}
	
	private void guardarHistorio(Libro libro, Usuario usuario) {
		Pair<Usuario, ArrayList<Libro>> parCurrent = this.historicoPrestamos.find(new Pair<>(usuario, null));

		if(parCurrent != null){

			parCurrent.getValue().add(libro);
		}else{
			ArrayList<Libro> newBooks = new ArrayList<>();
			newBooks.add(libro);
			this.historicoPrestamos.add(new Pair<>(usuario, newBooks));
		}

	}

	public ArrayList<String> getUsuariosLibro(String libroID){
		ArrayList<String> result = new ArrayList<>();
		Libro libro = new Libro(libroID);

		if(libros.contains(libro)){
			for(Pair<Usuario, ArrayList<Libro>> numLibros : historicoPrestamos){
				if (numLibros.getValue().contains(libro)) {
					result.add(numLibros.getKey().getUsuarioID());
				}
			}
			return result;
		}else {
			return null;
		}
	}
	
	public ArrayList<String> getLibrosPrestados(String usuarioID){
		Usuario usuario = this.usuarios.find(new Usuario(usuarioID));
		ArrayList<String> newArray = new ArrayList<>();
		if(usuario != null){
			Pair<Usuario, ArrayList<Libro>> numLibros = this.historicoPrestamos.find(new Pair<>(usuario, null));

			for (Libro book: numLibros.getValue()) {
				newArray.add(book.getLibroID());
			}
			return newArray;
		}else{
			return null;
		}
	}
		
	public ArrayList<String> getLibrosPrestados(){
		ArrayList<String> result = new ArrayList<>();
		for(Pair<Libro, Usuario> numLibros : prestamos){
				result.add(numLibros.getKey().getLibroID());
		}
		return result;

	}
	
	@Override
	public String toString() {
		return this.bibliotecaID + " (" + this.libros.size() + " libros y " + this.usuarios.size() + " usuarios" + ")"; 
	}
}
