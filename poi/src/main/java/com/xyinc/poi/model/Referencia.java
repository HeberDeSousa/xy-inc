package com.xyinc.poi.model;

public class Referencia {

	Integer referenciaX;
	Integer referenciaY;
	Integer distanciaMaxima;

	public Referencia() {
	}

	public Referencia(Integer referenciaX, Integer referenciaY, Integer distanciaMaxima) {
		this.referenciaX = referenciaX;
		this.referenciaY = referenciaY;
		this.distanciaMaxima = distanciaMaxima;
	}

	public Integer getReferenciaX() {
		return referenciaX;
	}

	public void setReferenciaX(Integer referenciaX) {
		this.referenciaX = referenciaX;
	}

	public Integer getReferenciaY() {
		return referenciaY;
	}

	public void setReferenciaY(Integer referenciaY) {
		this.referenciaY = referenciaY;
	}

	public Integer getDistanciaMaxima() {
		return distanciaMaxima;
	}

	public void setDistanciaMaxima(Integer distanciaMaxima) {
		this.distanciaMaxima = distanciaMaxima;
	}

}
