package sciencelab;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;




import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;








public class UserInformation {
	
	
	public static HomePage homePage = new HomePage();
	static LabDashBoard labDashBoard = new LabDashBoard();
	 
	
	public static boolean firstTimeLogin=false;

	
    private static final String JSON_FILE = "user_information.json"; 
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]+$");
	
    
  

    static String lastLogin;
    String userActivity;
    private int index;
    private String password = "CTULAB";

    public List<String> Email = new ArrayList<>();
    public List<String> Password = new ArrayList<>();
    public List<String> IDNumber = new ArrayList<>();
    public List<String> LastName = new ArrayList<>();
    public List<String> Birthdate = new ArrayList<>();
    public List<String> FirstName = new ArrayList<>();
    public List<String> AccountDateCreated = new ArrayList<>();
    private List<String> LastLogin = new ArrayList<>();
    public List<String> UserHistory = new ArrayList<>();
    public List<InventoryItem> inventoryList = new ArrayList<>();
 

    public void createAccount(String emailAddress, String idNumber, String birthdate, String firstName, String lastName,JPanel panel) {
    	
    	
    	
        if (Email.contains(emailAddress.toLowerCase())) {
            showMessageDialog("Email Address already registered");
            return;
        }

        if (IDNumber.contains(idNumber)) {
            showMessageDialog("ID Number already registered");
            return;
        }

        if (!isValidEmail(emailAddress.toLowerCase())) {
            showMessageDialog("Invalid Email Address");
            return;
        }

        try {
            int id = Integer.parseInt(idNumber);
        } catch (NumberFormatException e) {
            showMessageDialog("Invalid ID Number");
            return;
        }

        if (!isValidBirthdate(birthdate)) {
            showMessageDialog("Invalid Birthdate");
            return;
        }

        if (!isValidName(firstName)) {
            showMessageDialog("Invalid First Name");
            return;
        }

        if (!isValidName(lastName)) {
            showMessageDialog("Invalid Last Name");
            return;
        }

        Email.add(emailAddress.toLowerCase());
        IDNumber.add(idNumber);
        Birthdate.add(birthdate);
        FirstName.add(firstName.toLowerCase());
        LastName.add(lastName.toLowerCase());
        Password.add(password + Email.indexOf(emailAddress));
        showPasswordDialog(password + Email.indexOf(emailAddress));
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy\n-h:mm:ss a");
        Date currentDate = new Date();
    	String formattedDateTime = dateFormat.format(currentDate);
    	AccountDateCreated.add(formattedDateTime);	
    	LastLogin.add("");
    	UserHistory.add("");
    	
    	 InventoryItem itemInventory = new InventoryItem();
         for (int i = 0; i < itemInventory.names.length; i++) {
             itemInventory.names[i] = itemInventory.getItemName(i);
             itemInventory.quantities[i] = itemInventory.getItemQuantity(i);
            
         }
         
         
         
         inventoryList.add(new InventoryItem());  // This creates a new inventory for each user
    

        saveToJson();
        
        try {
    		 
        	 JFrame tobeDestroyed= (JFrame) SwingUtilities.getWindowAncestor(panel); //gikuha nako ang panel sa main page/login page para e destroy diri
        	  if(tobeDestroyed!=null) {
              	tobeDestroyed.dispose();
              }
          	
             
             } catch (Exception ex) {
                
            	// System.out.println("panel cant be found");
             }
        homePage.ShowGUI();
        
    }

   


    public void loginAccount(String email, String password,JPanel jpanel) {
         index = Email.indexOf(email.toLowerCase());
        if (index != -1) {
            if (Password.get(index).equals(password)) {
            	
            	SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy\nh:mm:ss a");
            	Date currentDate = new Date();
            	String formattedDateTime = dateFormat.format(currentDate);
            	
            	if(!firstTimeLogin) {
            		showLoginSuccessDialog();
            		firstTimeLogin=true;         
                	if(!LastLogin.isEmpty() && LastLogin.get(index).length()>2) {
                		
                		lastLogin = LastLogin.get(index);
                		LastLogin.set(index, formattedDateTime);	   
                	}else {
                		lastLogin = formattedDateTime;
                		LastLogin.set(index, formattedDateTime);
                		saveToJson();
                		
                	}
            		
            		
            	}           
            	 try {
            		 
            	 JFrame tobeDestroyed= (JFrame) SwingUtilities.getWindowAncestor(jpanel); //gikuha nako ang panel sa main page/login page para e destroy diri
            	  if(tobeDestroyed!=null) {
                  	tobeDestroyed.dispose();
                  }
              	
                 
                 } catch (Exception ex) {
                    
                	// System.out.println("panel cant be found");
                 }
            	
            	
            	
            	
                
            	 String userIDNumber = "<span style='color:#00FF00;'>" + IDNumber.get(index) + "</span>";
           	 String userInfo = "<html>"
            	         + "<span style='color:#87CEEB;'>Welcome, </span><span style='color:#00FF00;'>" + FirstName.get(index).toUpperCase() + " " + LastName.get(index).toUpperCase() + "</span><br>"
            	             + "<span style='color:#87CEEB;'>ID Number: </span>" + userIDNumber + "<br><br>"
            	      + "<span style='color:#87CEEB;'>Account Date Created:<br> </span><span style='color:#00FF00;'>" + AccountDateCreated.get(index) + "</span><br><br>"
            	      + "<span style='color:#87CEEB;'>Last login:<br> </span><span style='color:#00FF00;'>" + lastLogin + "</span></html>";

            



                
                
           
               
                	
            		saveToJson();
            		
            		
             
                
        		
                if(!UserHistory.isEmpty() && UserHistory.get(index).length()>2) {
            		
                	userActivity = UserHistory.get(index);        	
            		
            	}else {
            		userActivity = "<html> <br><br><br>This account has no activity history.</html>";

            	
            	}
                
                String userHistory = "<html><span style='color:#87CEEB;'>Account Activity History  /  Type<br></span>" +
                      "<span style='color:#00FF00;'>" + userActivity + "</span></html>";




        		
        		
        		labDashBoard.userHistory.setText(userHistory);
                labDashBoard.userWelcome.setText(userInfo);
                labDashBoard.getUserIndex(index);
                
                
                   
                
                labDashBoard.StartDashBoard();  
              
            	
                
            	
            	
    
                
            } else {
                showPasswordIncorrectDialog();
            }
        } 
        else {
        	showAccountNotFoundtDialog();
        }
    }
    
 


    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidName(String name) {
        return NAME_PATTERN.matcher(name).matches();
    }

    private boolean isValidBirthdate(String birthdate) {
        return birthdate.matches("\\d{4}-\\d{1,2}-\\d{1,2}");
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showLoginSuccessDialog() {
        JOptionPane.showMessageDialog(null, "      Login success!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showPasswordDialog(String password) {
        JOptionPane.showMessageDialog(null, "Your Password is: " + password, "Congratulations", JOptionPane.INFORMATION_MESSAGE);
        
    }

    private void showAccountNotFoundtDialog() {
        JOptionPane.showMessageDialog(null, "Account Not Found", "Error", JOptionPane.ERROR_MESSAGE);
    }
    private void showPasswordIncorrectDialog() {
        JOptionPane.showMessageDialog(null, "Your Password is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    


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
                UserInformation data = gson.fromJson(reader, UserInformation.class);
                if (data != null) {
                    Email = data.Email;
                    Password = data.Password;
                    IDNumber = data.IDNumber;
                    LastName = data.LastName;
                    Birthdate = data.Birthdate;
                    FirstName = data.FirstName;
                    AccountDateCreated = data.AccountDateCreated;
                    LastLogin = data.LastLogin;
                    UserHistory = data.UserHistory;
                    inventoryList = data.inventoryList;
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error reading JSON file: " + e.getMessage());
            }
        } else {
            System.err.println("JSON file does not exist: " + JSON_FILE);
        }
    }


    

 
}

   class InventoryItem {
   
    public String[] names = {
        "Salt", "Glucose", "Calcium", "Sulfur", "Buffers", "Solvent",
        "Erlenmeyer Flask", "Beaker", "Microscope", "Test Tube", "Thermometer", "Weighing Scale"
    };


    public int[] quantities = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    
    public String getItemName(int index) {
        if (index >= 0 && index < names.length) {
            return names[index];
        }
        return "Invalid index";
    }


    public int getItemQuantity(int index) {
        if (index >= 0 && index < quantities.length) {
            return quantities[index];
        }
        return -1;  // Returns -1 if the index is invalid
    }

    
}
   
   


