package br.edu.ufba.softvis.visminerweb.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.sun.org.apache.xml.internal.security.Init;

@ManagedBean
@ViewScoped
public class SliderRange {

	private int minValue;  
    private int maxValue;
    
    @PostConstruct
    public void Init(){
    	this.minValue=1;
    	this.maxValue= selector.getTags().size();
    }
    
    @ManagedProperty(value = "#{selector}")
	private Selector selector;

	public Selector getSelector() {
		return selector;
	}

	public void setSelector(Selector selector) {
		this.selector = selector;
	}
	   
	public int getMinValue() {
		return minValue;
	}
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	
	public int getInitMax(){
		setMaxValue(selector.getTags().size());
		return selector.getTags().size();
	}
       
}
