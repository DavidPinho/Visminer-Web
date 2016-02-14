package br.edu.ufba.softvis.visminerweb.beans;


import java.util.ArrayList;
import java.util.List;

import br.edu.ufba.softvis.visminerweb.enums.TDType;

public class EvaluatedSoftwareUnit {
	
	private String name;
	private String path;
	private int idSoftwareUnit;
	private int idFile;
	private int idCommit;
	private List<TDType> technicalDebts = new ArrayList<>();
	private Metrics metrics = new Metrics();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getIdSoftwareUnit() {
		return idSoftwareUnit;
	}
	public void setIdSoftwareUnit(int idSoftwareUnit) {
		this.idSoftwareUnit = idSoftwareUnit;
	}
	public int getIdFile() {
		return idFile;
	}
	public void setIdFile(int idFile) {
		this.idFile = idFile;
	}
	public int getIdCommit() {
		return idCommit;
	}
	public void setIdCommit(int idCommit) {
		this.idCommit = idCommit;
	}
	public List<TDType> getTechnicalDebts() {
		return technicalDebts;
	}
	public void setTechnicalDebts(List<TDType> technicalDebts) {
		this.technicalDebts = technicalDebts;
	}
	public Metrics getMetrics() {
		return metrics;
	}
	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}
}
