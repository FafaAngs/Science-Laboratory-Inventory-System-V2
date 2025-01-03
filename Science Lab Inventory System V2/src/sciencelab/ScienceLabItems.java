package sciencelab;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;


public class ScienceLabItems {

	
	private static final String JSON_FILE = "science_lab_items.json";

	
	 public  int[] materials = {
		        400, // Salt
		        400, // Glucose
		        400, // Calcium
		        400, // Sulfur
		        400, // Buffers
		        400  // Solvent
		        };
	 

	 public  int[] equipments = {
		        40, // Erlenmeyer Flask
		        40, // Beaker
		        40, // Microscope
		        40, // Test Tube
		        40, // Thermometer
		        40  // Weighing Scale
		    };
	

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public void saveToJson() {
		    try (Writer writer = new FileWriter(JSON_FILE)) {
		        Gson gson = new GsonBuilder().setPrettyPrinting().create();
		        gson.toJson(this, writer);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	 
	 
	 
	 
	 
	 
	 public void loadFromJson() {
	        File file = new File(JSON_FILE);
	        if (file.exists()) {
	            try (Reader reader = new FileReader(JSON_FILE)) {
	                Gson gson = new Gson();
	                ScienceLabItems data = gson.fromJson(reader, ScienceLabItems.class);
	                // Update the current instance with the loaded items
	                this.materials = data.materials;
	                this.equipments = data.equipments;
	            } catch (IOException e) {
	                e.printStackTrace();
	                System.err.println("Error reading JSON file: " + e.getMessage());
	            }
	        } else {
	            System.err.println("JSON file does not exist: " + JSON_FILE);
	        }
	    }



	
}