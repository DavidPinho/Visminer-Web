package br.edu.ufba.softvis.visminerweb.view.technicaldebt;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SlideEndEvent;

import br.edu.ufba.softvis.visminerweb.view.Selector;

@ManagedBean(name = "filterTD")
@SessionScoped
public class FilterTD {

	private int minValueSlider;  
	private int initialVersionSlider;  
    private int maxValueSlider;
    private int finalVersionSlider;
    private Integer progressBarValue;
    private String version;
    private String technicalDebt;
    
    @PostConstruct
    public void Init(){
    	int tagsTotal = selector.getTags().size();
    	if(tagsTotal!=0) {
    		this.minValueSlider=1;      		
       		this.initialVersionSlider=1;       		       		
    	} else { 
    		this.minValueSlider=0; 
       		this.minValueSlider=0; 
        }
    	this.maxValueSlider = tagsTotal;
    	this.finalVersionSlider = tagsTotal;  	
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

	public int getInitialVersionSlider() {
		return initialVersionSlider;
	}

	public void setInitialVersionSlider(int initialVersionSlider) {
		this.initialVersionSlider = initialVersionSlider;
	}

	public int getFinalVersionSlider() {
		return finalVersionSlider;
	}

	public void setFinalVersionSlider(int finalVersionSlider) {
		this.finalVersionSlider = finalVersionSlider;
	}

	public Integer getProgressBarValue() {		 
        if(progressBarValue == null) {
            progressBarValue = 0;
        }
        else {
            progressBarValue = progressBarValue + 10 + (int)(Math.random() * 35);
             
            if(progressBarValue > 100)
                progressBarValue = 100;
        }
         
        return progressBarValue;		    
	}

	public void setProgressBarValue(Integer progressBarValue) {
		this.progressBarValue = progressBarValue;
	}    
	
	public void cancelProgressBar() {
        progressBarValue = null;
    }
}
