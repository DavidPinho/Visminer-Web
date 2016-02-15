package br.edu.ufba.softvis.visminerweb.view.technicaldebt;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import br.edu.ufba.softvis.visminerweb.view.Selector;

@ManagedBean(name = "managementTD")
@ViewScoped
public class ManagementTD {

	private Dashboard dashboard;
	@ManagedProperty(value = "#{selector}")
	private Selector selector;
 
	public Selector getSelector() {
		return selector;
	}

	public void setSelector(Selector selector) {
		this.selector = selector;
	}

	public Dashboard getDashboard() {
		return dashboard;
	}
	
	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	
	 public ManagementTD() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
    public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Application application = fc.getApplication();

		dashboard = (Dashboard) application.createComponent(fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
		dashboard.setId("dashboard");
		
		DashboardModel model = new DefaultDashboardModel();
		DashboardColumn todoCard = new DefaultDashboardColumn();
		DashboardColumn doingCard = new DefaultDashboardColumn();
		DashboardColumn doneCard = new DefaultDashboardColumn();
		
		setTodoCards(todoCard);
		setDoingCards(doingCard);
		setDoneCards(doneCard);
		
		model.addColumn(todoCard);
		model.addColumn(doingCard);
		model.addColumn(doneCard);
		
		dashboard.setModel(model);
	}
	
	private void setTodoCards(DashboardColumn todo){
		FacesContext fc = FacesContext.getCurrentInstance();
		Application application = fc.getApplication();
		
		Panel header = createHeader("TODO");			
		getDashboard().getChildren().add(header);		
		todo.addWidget(header.getId());		
		
		Panel pessoa = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
		pessoa.setId("measure_1");
		pessoa.setHeader("pkg.Pessoa");
		pessoa.setStyle("text-align:center");
		
		getDashboard().getChildren().add(pessoa);
		todo.addWidget(pessoa.getId());
		
		HtmlOutputText text = new HtmlOutputText();
		text.setValue("Debt: code, design" );
		pessoa.getChildren().add(text);	
		
		Panel animal = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
		animal.setId("measure_2");
		animal.setHeader("pkg.Animal");
		animal.setStyle("text-align:center");
		
		getDashboard().getChildren().add(animal);
		todo.addWidget(animal.getId());		
		HtmlOutputText text2 = new HtmlOutputText();
		text2.setValue("Debt: code, design" );
		animal.getChildren().add(text2);
	}
	
	private Panel createHeader(String title) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Application application = fc.getApplication();
		
		Panel header = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
		header.setId("header"+title);
		header.setHeader(title);
		header.setStyleClass("card-management-header");
		
		return header;
	}
	
	private void setDoingCards(DashboardColumn doing){
		FacesContext fc = FacesContext.getCurrentInstance();
		Application application = fc.getApplication();
		
		Panel header = createHeader("DOING");			
		getDashboard().getChildren().add(header);		
		doing.addWidget(header.getId());	
		
		Panel panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
		panel.setId("measure_3");
		panel.setHeader("test.Casarao");
		panel.setStyle("text-align:center");
		
		getDashboard().getChildren().add(panel);
		
		doing.addWidget(panel.getId());
		HtmlOutputText text = new HtmlOutputText();
		text.setValue("Debt: design" );

		panel.getChildren().add(text);	
	}
	
	private void setDoneCards(DashboardColumn done){
		FacesContext fc = FacesContext.getCurrentInstance();
		Application application = fc.getApplication();
		
		Panel header = createHeader("DONE");			
		getDashboard().getChildren().add(header);		
		done.addWidget(header.getId());	
		
		Panel panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
		panel.setId("measure_4");
		panel.setHeader("test.Casa");
		panel.setStyle("text-align:center");
		getDashboard().getChildren().add(panel);
		
		done.addWidget(panel.getId());
		HtmlOutputText text = new HtmlOutputText();
		text.setValue("Debt: code" );

		panel.getChildren().add(text);	
	}
	
	public void handleReorder(DashboardReorderEvent event) {
       
    }
}
