//Ravali Bommanaboina
//Ashley Calusin
package view;

import app.COVID;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;




public class Controller {

	CategoryAxis xAxis    = new CategoryAxis();
   

    NumberAxis yAxis = new NumberAxis();
   
	@FXML
	ListView<COVID> countries; 
	@FXML
	Button graphButton;
	@FXML
	BarChart<String,Number> barGraph;
	int run=0;
	private ObservableList<COVID> obsList;              

	public void start(Stage mainStage) {                
	    obsList = FXCollections.observableArrayList(); 
	    readInput();
	    FXCollections.sort(obsList,new Sort());
	    countries.setItems(obsList); 

	   
	   // countries.getSelectionModel().select(0);
	   
	   
	    
	    countries
	    .getSelectionModel()
	    .selectedIndexProperty()
	    .addListener(
	        (obs, oldVal, newVal) -> 
	        showSongInfo(mainStage));
	    countries.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    graphButton.setOnAction(new EventHandler<ActionEvent>() {
	        public void handle(ActionEvent e) {
	               showGraph(mainStage);
	            }
	      });
	}
	private void showSongInfo(Stage mainStage) {                
		  
		 
	    
	      
	}
	private void showGraph(Stage mainStage) {
		 
		ObservableList<Integer> index=countries.getSelectionModel().getSelectedIndices();
		if(index.size()!=5)
		{
			Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Pick 5 countries.");
            Optional<ButtonType> buttonResult = alert.showAndWait();
            return;
		}
		run++;
		
		barGraph.getData().clear();
		XYChart.Series dataSeries1 = new XYChart.Series();
		XYChart.Series dataSeries2 = new XYChart.Series();
	    dataSeries1.setName("Poverty");
	   
		
		 
		dataSeries1.getData().add(new XYChart.Data<String,Number>(obsList.get(index.get(0)).country,obsList.get(index.get(0)).poverty));
		dataSeries1.getData().add(new XYChart.Data<String,Number>(obsList.get(index.get(1)).country,obsList.get(index.get(1)).poverty));
		dataSeries1.getData().add(new XYChart.Data<String,Number>(obsList.get(index.get(2)).country,obsList.get(index.get(2)).poverty));
		dataSeries1.getData().add(new XYChart.Data<String,Number>(obsList.get(index.get(3)).country,obsList.get(index.get(3)).poverty));
		dataSeries1.getData().add(new XYChart.Data<String,Number>(obsList.get(index.get(4)).country,obsList.get(index.get(4)).poverty));
		
		dataSeries2.setName("Vaccine");
		dataSeries2.getData().add(new XYChart.Data<String,Number>(obsList.get(index.get(0)).country,obsList.get(index.get(0)).vaccine));
		dataSeries2.getData().add(new XYChart.Data<String,Number>(obsList.get(index.get(1)).country,obsList.get(index.get(1)).vaccine));
		dataSeries2.getData().add(new XYChart.Data<String,Number>(obsList.get(index.get(2)).country,obsList.get(index.get(2)).vaccine));
		dataSeries2.getData().add(new XYChart.Data<String,Number>(obsList.get(index.get(3)).country,obsList.get(index.get(3)).vaccine));
		dataSeries2.getData().add(new XYChart.Data<String,Number>(obsList.get(index.get(4)).country,obsList.get(index.get(4)).vaccine));
		
		
		barGraph.getData().add(dataSeries1);
	    barGraph.getData().add(dataSeries2);
	    if(run==1)
	    {
	    	showGraph(mainStage);
	    }
	        //primaryStage.show();
	}
	private void readInput() {
    	try { 
    	  
    	    File file = new File("Input.txt"); 
    	    Scanner sc = new Scanner(file); 
    	    String line="";
    	    String country="";
    	    Double pov=0.0;
    	    Double vac=0.0;
    	    
    	    while(sc.hasNext()) {
    	    	line=sc.next();
    	    	int count=0;
	    		int start = 0;
    	    	int end=0;
    	    	for(int i = 0;i<line.length();i++)
    	    	{
    	    		if(line.charAt(i)=='|')
    	    		{
    	    			count++;
    	    			end=i;
    	    			if(count==1)
        	    		{
        	    			country=line.substring(start,end);
        	    			start=i+1;
        	    		}
        	    		else if(count==2)
        	    		{
        	    			
        	    			if(line.charAt(start)=='-')
        	    			{
        	    				pov=(Double.parseDouble(line.substring(start+1,end)))*-1;
        	    			}
        	    			else
        	    			{
        	    				pov=Double.parseDouble(line.substring(start,end));
        	    			}
        	    			
        	    			start=i+1;
        	    		}
        	    		else if(count==3)
        	    		{
        	    			if(line.charAt(start)=='-')
        	    			{
        	    				vac=(Double.parseDouble(line.substring(start+1,end)))*-1;
        	    			}
        	    			else
        	    			{
        	    				vac=Double.parseDouble(line.substring(start,end));
        	    			}
        	    			
        	    			start=i+1;
        	    		}
        	    		
    	    		}
    	    		
    	    	}
    	    	obsList.add(new COVID(country,pov,vac));
    	    	
    	    }
    	}catch(FileNotFoundException e){
    		System.err.println("Error: " + e.getMessage());
    	}
    }
   
    
}