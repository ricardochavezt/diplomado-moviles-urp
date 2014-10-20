package com.diplomadourp.listadomodelos;

public class DatosModelo {
	private String nombre;
	private String profesion;
	private String ciudad;
	private String fechaNacimiento;
	private int idFoto;
	
	public DatosModelo(String nombre, String profesion, String ciudad,
			String fechaNacimiento, int idFoto) {
		this.nombre = nombre;
		this.profesion = profesion;
		this.ciudad = ciudad;
		this.fechaNacimiento = fechaNacimiento;
		this.idFoto = idFoto;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getIdFoto() {
		return idFoto;
	}
	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}
}
