package application;

import java.io.IOException;

import entities.Interface;
import entities.Sensor;
import entities.Target;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController  {
	
	Scenario scenario = new Scenario(); 
	Sensor testSensor = new Sensor("test", "testtype", "shit");
	TreeItem<Sensor> sensorTreeRoot = new TreeItem<Sensor>(new Sensor("root", "", ""));
	TreeItem<Target> targetTreeRoot = new TreeItem<Target>(new Target("root", "", ""));
	TreeItem<Interface> interfaceTreeRoot = new TreeItem<Interface>(new Interface("root"));

    @FXML private CheckMenuItem mapFileterTargetsChk;
    @FXML private Button ctrlSetPositionBtn;
    @FXML private TextField ctrlHdtTxt;
    @FXML private TreeView<Sensor> SensorsTreeView;
    @FXML private TreeView<Interface> outputsTreeView;
    @FXML private TreeView<Target> targetTreeView;
    @FXML private VBox centerVbox;
    @FXML private Button targetAddBtn;
    @FXML private Button targetRemoveBtn;
    @FXML private TextField ctrlSpeedTxt;
    @FXML private TextField ctrlLatTxt;
    @FXML private ListView<?> logList;
    @FXML private Button outputsRemove;
    @FXML private TextField ctrlLongTxt;
    @FXML private Button outputsEdit;
    @FXML private TextField ctrlRudderTxt;
    @FXML private Slider ctrlSpeedSlide;
    @FXML private MenuItem menuTargetAddBtn;
    @FXML private MenuItem menuSensorsAddBtn;
    @FXML private Button targetEditBtn;
    @FXML private Button menuStartSimulationBtn;
    @FXML private CheckMenuItem mapFileterMeasurementsChk;
    @FXML private CheckMenuItem mapFileterWorldMapChk;
    @FXML private Button menuStopSimulationBtn;
    @FXML private TextField ctrlAltTxt;
    @FXML private Tab TargetsTreeView;
    @FXML private MenuItem menuOutputsAddBtn;
    @FXML private Button outputsAddBtn;
    @FXML private Button sensorsEditBtn;
    @FXML private Button sensorRemoveBtn;
    @FXML private Slider ctrlAltSlide;
    @FXML private Button sensorsAddBtn;
    @FXML private Slider ctrlRudderSlide;
    
    
    public void initialize()  {
    	

    	


    	
    	scenario.getSUT().getSensors().addListener(new ListChangeListener<Sensor>() {
    	      @Override
    	      public void onChanged(ListChangeListener.Change change) {
    	        sensorTreeRoot = new TreeItem<Sensor>(new Sensor("root", "", ""));
    	        for(Sensor sensor : scenario.getSUT().getSensors()){
    				TreeItem<Sensor> sensorItem = new TreeItem<Sensor>(sensor);
    				sensorTreeRoot.getChildren().add(sensorItem);
    			}
    	        SensorsTreeView.setRoot(sensorTreeRoot);
    	        SensorsTreeView.setShowRoot(false);     
    	      }
    	    });
    	
    	scenario.getSUT().getInterfaces().addListener(new ListChangeListener<Interface>() {
  	      @Override
  	      public void onChanged(ListChangeListener.Change change) {
  	    	interfaceTreeRoot = new TreeItem<Interface>(new Interface("root"));
  	        for(Interface output : scenario.getSUT().getInterfaces()){
  				TreeItem<Interface> interfaceItem = new TreeItem<Interface>(output);
  				interfaceTreeRoot.getChildren().add(interfaceItem);
  			}
  	        outputsTreeView.setRoot(interfaceTreeRoot);
  	        outputsTreeView.setShowRoot(false);     
  	      }
  	    });
    	
    	scenario.getStaticTargets().addListener(new ListChangeListener<Target>() {
    	      @Override
    	      public void onChanged(ListChangeListener.Change change) {
    	    	  targetTreeRoot = new TreeItem<Target>(new Target("root", "", ""));
    	        for(Target target : scenario.getStaticTargets()){
    				TreeItem<Target> targetItem = new TreeItem<Target>(target);
    				targetTreeRoot.getChildren().add(targetItem);
    			}
    	        targetTreeView.setRoot(targetTreeRoot);
    	        targetTreeView.setShowRoot(false);     
    	      }
    	    });
    
    	
    }

    @FXML
    void setRudder(ActionEvent event) {

    }

    @FXML
    void ctrlSetPositionBtnClick(ActionEvent event) {

    }

    @FXML
    void outputsAddBtnClick(ActionEvent event) {
    	System.out.println("New Output");
    	Interface output = new Interface();
		Parent root;
        try {
        	
        	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/Interface.fxml"));
 
            Stage stage = new Stage();
            stage.setTitle("Output Configuration");
            stage.initModality(Modality.APPLICATION_MODAL);
            root = loader.load();
            stage.setScene(new Scene(root));
            
            
            InterfaceController controller = 
            		loader.<InterfaceController>getController();
            	  	controller.setOutput(output);
            	  	
            	  	

            stage.showAndWait();
            scenario.getSUT().getInterfaces().add(output);
            

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void outputsEditBtnClick(ActionEvent event) {
    	TreeItem<Interface> selectedItem = outputsTreeView.getSelectionModel().getSelectedItem();
		
		Parent root;
        try {
        	
        	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/Interface.fxml"));
 
            Stage stage = new Stage();
            stage.setTitle("Output Configuration");
            stage.initModality(Modality.APPLICATION_MODAL);
            root = loader.load();
            stage.setScene(new Scene(root));
            
            
            InterfaceController controller = 
            		loader.<InterfaceController>getController();
            	  	controller.setOutput(selectedItem.getValue());
            	  	
            	  	

            stage.showAndWait();
            
            

        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
    }

    @FXML
    void outputsRemoveBtnClick(ActionEvent event) {
    	TreeItem<Interface> selectedItem = outputsTreeView.getSelectionModel().getSelectedItem();
    	scenario.getSUT().getInterfaces().remove(selectedItem.getValue());
    }

    @FXML
    void sensorsAddBtnClick(ActionEvent event) {
    	System.out.println("New Sensor");
    	Sensor sensor = new Sensor();
		Parent root;
        try {
        	
        	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/Sensor.fxml"));
 
            Stage stage = new Stage();
            stage.setTitle("Sensor Configuration");
            stage.initModality(Modality.APPLICATION_MODAL);
            root = loader.load();
            stage.setScene(new Scene(root));
            
            
            SensorController controller = 
            		loader.<SensorController>getController();
            	  	controller.setSensor(sensor);
            	  	controller.setScenario(scenario);
            	  	
            	  	

            stage.showAndWait();
            scenario.getSUT().getSensors().add(sensor);
            

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void sensorsEditBtnClick(ActionEvent event) {
    		TreeItem<Sensor> selectedItem = SensorsTreeView.getSelectionModel().getSelectedItem();
    		
    		Parent root;
            try {
            	
            	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/Sensor.fxml"));
     
                Stage stage = new Stage();
                stage.setTitle("Sensor Configuration");
                stage.initModality(Modality.APPLICATION_MODAL);
                root = loader.load();
                stage.setScene(new Scene(root));
                
                
                SensorController controller = 
                		loader.<SensorController>getController();
                	  	controller.setSensor(selectedItem.getValue());
                	  	controller.setScenario(scenario);
                	  	

                stage.showAndWait();
                
                

            }
            catch (IOException e) {
                e.printStackTrace();
            }
    		
    }

    @FXML
    void sensorRemoveBtnClick(ActionEvent event) {
    	TreeItem<Sensor> selectedItem = SensorsTreeView.getSelectionModel().getSelectedItem();
    	scenario.getSUT().getSensors().remove(selectedItem.getValue());
    }

    @FXML
    void targetAddBtnClick(ActionEvent event) {
    	System.out.println("New Target");
    	Target target = new Target();
		Parent root;
        try {
        	
        	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/Target.fxml"));
 
            Stage stage = new Stage();
            stage.setTitle("Target Configuration");
            stage.initModality(Modality.APPLICATION_MODAL);
            root = loader.load();
            stage.setScene(new Scene(root));
            
            
            TargetController controller = 
            		loader.<TargetController>getController();
            	  	controller.setTarget(target);
            	  	
            	  	

            stage.showAndWait();
            scenario.getStaticTargets().add(target);
            

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void targetEditBtnClick(ActionEvent event) {
   		TreeItem<Target> selectedItem = targetTreeView.getSelectionModel().getSelectedItem();
		
		Parent root;
        try {
        	
        	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/Target.fxml"));
 
            Stage stage = new Stage();
            stage.setTitle("Target Configuration");
            stage.initModality(Modality.APPLICATION_MODAL);
            root = loader.load();
            stage.setScene(new Scene(root));
            
            
            TargetController controller = 
            		loader.<TargetController>getController();
            	  	controller.setTarget(selectedItem.getValue());
            	  	
            	  	

            stage.showAndWait();
            
            

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void targetRemoveBtnClick(ActionEvent event) {
    	TreeItem<Target> selectedItem = targetTreeView.getSelectionModel().getSelectedItem();
    	scenario.getStaticTargets().remove(selectedItem.getValue());
    }


    @FXML
    void menuStartSimulationBtnClick(ActionEvent event) {
    	scenario.startScenario();
    }	

    @FXML
    void menuStopSimulationBtnClick(ActionEvent event) {
    	scenario.stopScenario();
    }


}
