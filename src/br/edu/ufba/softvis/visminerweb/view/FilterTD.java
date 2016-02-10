package br.edu.ufba.softvis.visminerweb.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "filterTD")
@ViewScoped
public class FilterTD {

	private int minValueSlider;  
    private int maxValueSlider;
    private String version;
    private String technicalDebt;
    
    @PostConstruct
    public void Init(){
    	this.minValueSlider=1;
    	this.maxValueSlider= selector.getTags().size();
    }
    
    @ManagedProperty(value = "#{selector}")
	private Selector selector;

	public Selector getSelector() {
		return selector;
	}

	public void setSelector(Selector selector) {
		this.selector = selector;
	}
	   
	public int getMinValueSlider() {
		return minValueSlider;
	}
	public void setMinValueSlider(int minValue) {
		this.minValueSlider = minValue;
	}
	public int getMaxValueSlider() {
		return maxValueSlider;
	}
	public void setMaxValueSlider(int maxValue) {
		this.maxValueSlider = maxValue;
	}
	
	public int getInitMaxSlider(){
		setMaxValueSlider(selector.getTags().size());
		return selector.getTags().size();
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTechnicalDebt() {
		return technicalDebt;
	}

	public void setTechnicalDebt(String technicalDebt) {
		this.technicalDebt = technicalDebt;
	}
     
}
