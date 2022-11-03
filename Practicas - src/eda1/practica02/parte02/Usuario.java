package eda1.practica02.parte02;

public class Usuario implements Comparable<Usuario> {
	private String usuarioID;

	public Usuario(String usuarioID) {
		this.usuarioID = usuarioID.toLowerCase();
	}
	
	public String getUsuarioID() {
		return this.usuarioID;
	}
	
	@Override
	public String toString() {
		return this.usuarioID;
	}
	
	@Override
	public boolean equals(Object o) {
		return this.compareTo((Usuario)o) == 0;
	}
	@Override
	public int compareTo(Usuario o) {
		return this.usuarioID.compareTo(o.usuarioID);
	}
}
