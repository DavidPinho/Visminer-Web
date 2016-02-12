package br.edu.ufba.softvis.visminerweb.view.technicaldebt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.edu.ufba.softvis.visminer.model.business.Tree;
import br.edu.ufba.softvis.visminerweb.view.Selector;

@ManagedBean(name = "lineChartTDEvolution")
@ViewScoped
public class LineChartTDEvolution {
	    
    @ManagedProperty(value = "#{selector}")
	private Selector selector;
    @ManagedProperty(value = "#{filterTD}")
	private FilterTD filterTD;
	private Map<String, List<LineChartSerieTD>> mappedList;
    
    public Selector getSelector() {
		return selector;
	}

	public void setSelector(Selector selector) {
		this.selector = selector;
	}
	
	public FilterTD getFilterTD() {
		return filterTD;
	}

	public void setFilterTD(FilterTD filterTD) {
		this.filterTD = filterTD;
	}
	
    public Map<String, List<LineChartSerieTD>> getMappedList() {
        return mappedList;
    }          
     
    @PostConstruct
    public void Init(){
    	reload();
    }
 
    public void reload() {
       List<LineChartSerieTD> codeDebt = new ArrayList<>();
       List<LineChartSerieTD> designDebt = new ArrayList<>();
       mappedList = new HashMap<>();  
       
       List<Tree> tags = selector.getTags();
       int initialVersion = filterTD.getInitialVersionSlider();
       int finalVersion = filterTD.getFinalVersionSlider();
       Random r = new Random();
       
        for (int i = initialVersion; i<=finalVersion; i++) {
        	Tree tag = tags.get(i-1);
            codeDebt.add(new LineChartSerieTD(tag.getName(), r.nextInt(10)));
            designDebt.add(new LineChartSerieTD(tag.getName(), r.nextInt(10)));
        }
        
        mappedList.put("Code Debt", codeDebt);
        mappedList.put("Design Debt", designDebt);
    }
    
    public class LineChartSerieTD{
		
		public LineChartSerieTD(){
			
		}
		
		public LineChartSerieTD(String version, int amountTD) {
			this.version = version;
			this.amountTD = amountTD;
		}
		
		private String version;
		private int amountTD;
		
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public int getAmountTD() {
			return amountTD;
		}
		public void setAmountTD(int amountTD) {
			this.amountTD = amountTD;
		}
	}
}
