package com.diplomadourp.ejemplolistados;

public class DataPaises {

	private String nombrePais;
	private int idImagenBandera;
	
	public DataPaises(String nombrePais, int idImagenBandera) {
		this.nombrePais = nombrePais;
		this.idImagenBandera = idImagenBandera;
	}
	
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	public int getIdImagenBandera() {
		return idImagenBandera;
	}
	public void setIdImagenBandera(int idImagenBandera) {
		this.idImagenBandera = idImagenBandera;
	}
}
