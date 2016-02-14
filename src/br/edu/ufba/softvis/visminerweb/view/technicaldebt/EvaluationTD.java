package br.edu.ufba.softvis.visminerweb.view.technicaldebt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.edu.ufba.softvis.visminer.constant.MetricUid;
import br.edu.ufba.softvis.visminer.constant.SoftwareUnitType;
import br.edu.ufba.softvis.visminer.model.business.Commit;
import br.edu.ufba.softvis.visminer.model.business.File;
import br.edu.ufba.softvis.visminer.model.business.SoftwareUnit;
import br.edu.ufba.softvis.visminer.retriever.CommitRetriever;
import br.edu.ufba.softvis.visminer.retriever.SoftwareUnitRetriever;
import br.edu.ufba.softvis.visminerweb.beans.EvaluatedSoftwareUnit;
import br.edu.ufba.softvis.visminerweb.beans.Metrics;
import br.edu.ufba.softvis.visminerweb.enums.TDType;
import br.edu.ufba.softvis.visminerweb.factory.RetrieverFactory;
import br.edu.ufba.softvis.visminerweb.view.Selector;

@ManagedBean(name = "evaluationTD")
@ViewScoped
public class EvaluationTD {
	
	@ManagedProperty(value = "#{selector}")
	private Selector selector;
    @ManagedProperty(value = "#{filterTD}")
	private FilterTD filterTD;
    private List<SoftwareUnit> softwareUnitList;
    private List<EvaluatedSoftwareUnit> evaluatedUnits;
    private EvaluatedSoftwareUnit selectedEvaluatedUnit;
    private Commit selectedCommit;
    
    private SoftwareUnitRetriever swUnitRetriever = RetrieverFactory.create(SoftwareUnitRetriever.class);
    private CommitRetriever commitRetrivier = RetrieverFactory.create(CommitRetriever.class);
        
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
	public List<EvaluatedSoftwareUnit> getEvaluatedUnits() {
		return evaluatedUnits;
	}
	public void setEvaluatedUnits(List<EvaluatedSoftwareUnit> evaluatedUnits) {
		this.evaluatedUnits = evaluatedUnits;
	}
	public EvaluatedSoftwareUnit getSelectedEvaluatedUnit() {
		return selectedEvaluatedUnit;
	}
	public void setSelectedEvaluatedUnit(EvaluatedSoftwareUnit selectedEvaluatedUnit) {
		this.selectedEvaluatedUnit = selectedEvaluatedUnit;
	}
	public List<SoftwareUnit> getSoftwareUnitList() {
		return softwareUnitList;
	}
	public void setSoftwareUnitList(List<SoftwareUnit> softwareUnitList) {
		this.softwareUnitList = softwareUnitList;
	}
	
	@PostConstruct
    public void init() {
        extractSoftwareUnits();
        evaluatedUnits = new ArrayList<>();
        analyzeDebt();
    }
	
	private void extractSoftwareUnits() {
		softwareUnitList = new ArrayList<>();
		
		List<Commit> commits = extractCommits();
		for (Commit commit : commits) {
			List<File> files = commit.getCommitedFiles();
			addToSoftwareUnitList(files, commit);
		}
				
	}
	
	private List<Commit> extractCommits() {
		int idVersion = filterTD.getIdVersionSelected();
		
		return commitRetrivier.retrieveByTree(idVersion);		
	}
	
	private void addToSoftwareUnitList(List<File> files, Commit commit){
		for (File file : files) {
			List<SoftwareUnit> softwareUnits = swUnitRetriever.findByFile(file.getId(), commit.getId());
			for(SoftwareUnit softwareUnit : softwareUnits) {
				int position = getPositionOnList(softwareUnit);
				if (position!=-1){
					softwareUnitList.remove(position);
				}
				softwareUnitList.add(softwareUnit);
			}
		}
	}
	
	private int getPositionOnList(SoftwareUnit softwareUnit){
		int position = -1;
		for (int i = 0; i<softwareUnitList.size(); i++) {
			if(softwareUnitList.get(i).getId()==softwareUnit.getId()){
				position=i;
			}
		}
		return position;
	}
		
	private void analyzeDebt() {
		for (SoftwareUnit swUnit : softwareUnitList) {
			if(swUnit.getType()==SoftwareUnitType.CLASS_OR_INTERFACE){
				Map<MetricUid, String> metrics = swUnit.getMetricValues();
				List<TDType> tdTypes = new ArrayList<>();
				boolean hasDuplicatedCode = false;
				boolean isGodClass = false;
				String tdAnalyzed = filterTD.getTechnicalDebt();
				
				//TODO Analyze code debt based on duplicated code and god class. 
				if(tdAnalyzed.equals(TDType.CODE.toString().toUpperCase()) || tdAnalyzed.equals("all")){
					if(hasDuplicatedCode(metrics)){						
						hasDuplicatedCode = true;
						tdTypes.add(TDType.CODE);				
					}
				}
				
				if(tdAnalyzed.equals(TDType.DESIGN.toString().toUpperCase()) || tdAnalyzed.equals("all")){
					if (isGodClass(metrics)) {
						isGodClass = true;
						tdTypes.add(TDType.DESIGN);
					}
				}
				
				if(isGodClass || hasDuplicatedCode) {				
					EvaluatedSoftwareUnit evaluatedSoftwareUnit = new EvaluatedSoftwareUnit();
					evaluatedSoftwareUnit.setName(swUnit.getName());
					evaluatedSoftwareUnit.setIdSoftwareUnit(swUnit.getId());
					evaluatedSoftwareUnit.setIdFile(swUnit.getFile().getId());
					evaluatedSoftwareUnit.setMetrics(setMetricsToObject(metrics));
					evaluatedSoftwareUnit.setTechnicalDebts(tdTypes);
					evaluatedUnits.add(evaluatedSoftwareUnit);
				}				
			}
		}
	}
	
	private boolean isGodClass(Map<MetricUid, String> metrics) {
		String wmcString = metrics.get(MetricUid.WMC);
		String atfdString = metrics.get(MetricUid.ATFD);
		String tccString = metrics.get(MetricUid.TCC);
		if (wmcString==null || atfdString==null || tccString==null){
			return false;
		}
		
		int wmc = Integer.parseInt(wmcString);
		int atfd = Integer.parseInt(atfdString);
		float tcc = Float.parseFloat(tccString);
		if (wmc >= 5 && atfd > 1 && tcc < 0.7) {
			return true;
		}
		
		return false;		
	}
	
	private boolean hasDuplicatedCode(Map<MetricUid, String> metrics) {
		String  wmcString = metrics.get(MetricUid.WMC);
		if(wmcString!=null){
			int wmc = Integer.parseInt(wmcString);					
			if(wmc>3){	
				return true;
			}
		}
		return false;
	}
	
	private Metrics setMetricsToObject(Map<MetricUid, String> metricsMap){
		String wmcString = metricsMap.get(MetricUid.WMC);
		String atfdString = metricsMap.get(MetricUid.ATFD);
		String tccString = metricsMap.get(MetricUid.TCC);
		if (wmcString==null || atfdString==null || tccString==null){
			return null;
		}
		Metrics metrics = new Metrics();
		metrics.setWmc(Integer.parseInt(wmcString));
		metrics.setAtfd(Integer.parseInt(atfdString));
		metrics.setTcc(Float.parseFloat(tccString));
		return metrics;
	}

}
