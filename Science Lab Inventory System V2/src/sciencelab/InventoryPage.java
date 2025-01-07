package sciencelab;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;




public class InventoryPage {
	
	 HomePage homePage = new HomePage();
	 String userActivity="";
	 String[] measurements = {"g", "mL", "mL", "g", "g", "g"};
	public String[] materialNames = {"Salt", "Glucose", "Calcium", "Sulfur", "Buffers", "Solvent"};
	 public String[] equipmentNames = {"Erlenmeyer Flask", "Beaker", "Microscope", "Test Tube", "Thermometer", "Weighing Scale"};
	

	
	public static int userIndex;
	static LabDashBoard labDashBoard = new LabDashBoard();
	ScienceLabItems scienceLabItems = new ScienceLabItems();
	UserInformation userInformation = new UserInformation();
  AdminPanel adminPanel = new AdminPanel();
	
	int[] originalValueMaterials = new int[scienceLabItems.materials.length];
	int[]  originalValueEquipments = new int[scienceLabItems.equipments.length];

    public void StartInventoryPage() {
    	
    	userInformation.loadFromJson();
        JFrame frame = new JFrame("Science Laboratory Inventory System");
        JPanel panel = new JPanel();
      
panel.setBackground(Color.orange.darker());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 800);
        frame.setLocationRelativeTo(null);
        panel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new GridLayout(1, 2));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 60));
        headerPanel.setBackground(Color.ORANGE);
        headerPanel.setOpaque(true);

    
        JLabel equipmentsLabel = new JLabel("EQUIPMENTS");
        equipmentsLabel.setForeground(Color.BLACK);
        equipmentsLabel.setFont(new Font("Arial", Font.BOLD, 36));
        equipmentsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        equipmentsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100)); 

        JLabel materialsLabel = new JLabel("MATERIALS");
        materialsLabel.setForeground(Color.BLACK);
        materialsLabel.setFont(new Font("Arial", Font.BOLD, 36));
        materialsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        materialsLabel.setBorder(BorderFactory.createEmptyBorder(0, 280, 0, 0));

   
        headerPanel.add(equipmentsLabel);
        headerPanel.add(materialsLabel);

        panel.add(headerPanel, BorderLayout.NORTH);
        



    
        JButton button = new JButton("GET ITEMS");
        JButton backbutton = new JButton("BACK");


     button.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             
        	 
        	  boolean itemsTaken = false;
        	userInformation.loadFromJson();
          
              
               
               for (int i = 0; i < scienceLabItems.materials.length; i++) {
                   int materialTaken = originalValueMaterials[i] - scienceLabItems.materials[i];
                   if (materialTaken > 0) {
                	   itemsTaken = true;
                	   if (!userInformation.UserHistory.isEmpty() && userInformation.UserHistory.get(userIndex).length() > 2) {
                		  
                		    String oldHistory = userInformation.UserHistory.get(userIndex);
                		    String newActivity = "<br>" + materialNames[i] + ": " + materialTaken + measurements[i]+" taken";
                		    userInformation.UserHistory.set(userIndex, oldHistory + newActivity);  
       
                		} else {
          	  
                		    String newActivity = "<br>" + materialNames[i] + ": " + materialTaken+ measurements[i]+" taken";
                            userInformation.UserHistory.set(userIndex, newActivity);  
                		}
                	   SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy - h:mm:ss a");
                	    String formattedDateTime = dateFormat.format(new Date());
                	   
String[] newData = {userInformation.FirstName.get(userIndex).toUpperCase()+" "+userInformation.LastName.get(userIndex).toUpperCase(),userInformation.IDNumber.get(userIndex), materialNames[i],"-"+Integer.toString(materialTaken)+measurements[i],formattedDateTime};
      
              adminPanel.addData(newData);             		
              adminPanel.saveDataToJson();
              
              updateInventoryItemQuantity(userIndex,materialNames[i],materialTaken);
              

                	   userInformation.saveToJson();
                     
                                          
                   }
                   
                              
                  
                   
               }
               
               
               //  equipments
               for (int i = 0; i < scienceLabItems.equipments.length; i++) {
                   int equipmentTaken = originalValueEquipments[i] - scienceLabItems.equipments[i];
                   if (equipmentTaken > 0) {
                       itemsTaken = true;
                       if (!userInformation.UserHistory.isEmpty() && userInformation.UserHistory.get(userIndex).length() > 2) {
                           String oldHistory = userInformation.UserHistory.get(userIndex);
                           String newActivity = "<br>" + equipmentNames[i] + ": " + equipmentTaken + " taken";
                           userInformation.UserHistory.set(userIndex, oldHistory + newActivity);
                       } else {
                           String newActivity = "<br>" + equipmentNames[i] + ": " + equipmentTaken + " taken";
                           userInformation.UserHistory.set(userIndex, newActivity);
                       } 
                       SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy - h:mm:ss a");
               	    String formattedDateTime = dateFormat.format(new Date());
               String[] newData = {userInformation.FirstName.get(userIndex).toUpperCase()+" "+userInformation.LastName.get(userIndex).toUpperCase(),userInformation.IDNumber.get(userIndex), equipmentNames[i],"-"+Integer.toString(equipmentTaken),formattedDateTime};
               adminPanel.addData(newData);                  		
               adminPanel.saveDataToJson();
            
               
               
            
               
               updateInventoryItemQuantity(userIndex,equipmentNames[i],equipmentTaken);
            

                       userInformation.saveToJson();
                      
                   }
               }
               
               
               if (!itemsTaken) {
                   JOptionPane.showMessageDialog(null, "        No items to get.", "Invalid", JOptionPane.INFORMATION_MESSAGE);
                   return;
               }
               scienceLabItems.saveToJson(); 
               
               JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
               labDashBoard.getUserIndex(userIndex);
               JOptionPane.showMessageDialog(null, "You have successfully acquired the items", " ", JOptionPane.INFORMATION_MESSAGE);
               
               
               
               String user = userInformation.Email.get(userIndex);  
               String userPass = userInformation.Password.get(userIndex);
                  userInformation.loginAccount(user, userPass, null);
               topFrame.dispose(); 

         }
     });


        button.setPreferredSize(new Dimension(150, 20));

     
        button.setFont(new Font("Roboto", Font.BOLD, 15));
        
        
        
        
        
        backbutton.setPreferredSize(new Dimension(150, 20));
        backbutton.setFont(new Font("Roboto", Font.BOLD, 15));
        
        
        
        
        backbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
                userInformation.loadFromJson();
                String user = userInformation.Email.get(userIndex);  
                String userPass = userInformation.Password.get(userIndex);
                   userInformation.loginAccount(user, userPass, null);
                topFrame.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.ORANGE);
  
        buttonPanel.add(button);
        buttonPanel.add(backbutton);
        

      
        panel.add(buttonPanel, BorderLayout.SOUTH);


        panel.add(headerPanel, BorderLayout.NORTH);
        JPanel leftContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftContainer.setBorder(BorderFactory.createEmptyBorder(40,100, 0, 0)); 
        leftContainer.setBackground(Color.orange.darker());
        leftContainer.setPreferredSize(new Dimension(300, 200)); 
        panel.add(leftContainer, BorderLayout.WEST);

        JPanel rightContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightContainer.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 100));
        rightContainer.setBackground(Color.orange.darker());
        rightContainer.setPreferredSize(new Dimension(300, 200)); 
        panel.add(rightContainer, BorderLayout.EAST);

        Font serifFont = new Font("Serif", Font.BOLD, 13);
        
    
        for (int i = 1; i <= 6; i++) {
        	 ImageIcon icon = new ImageIcon();
     
        	 JLabel leftNameLabel = new JLabel();
        	 leftNameLabel.setForeground(Color.green);
        	 
        	
        	if(i==1) {
        		icon = new ImageIcon(getClass().getResource("/resources/flashGlass.png"));
        		leftNameLabel = new JLabel("Erlenmeyer Flask");
        		
        		
        		
              }
               if(i==2) {
            	   
            	   icon = new ImageIcon(getClass().getResource("/resources/beakerGlass.png"));
            	   leftNameLabel = new JLabel("Beaker Glass    ");
               }
               if(i==3) {
            	   icon = new ImageIcon(getClass().getResource("/resources/microscope.png"));;
            	   leftNameLabel = new JLabel("Microscope         ");
               }
               if(i==4) {
            	   icon = new ImageIcon(getClass().getResource("/resources/testTube.png"));;  
            	   leftNameLabel = new JLabel("TestTube          ");
               }
               if(i==5) {
            	   icon = new ImageIcon(getClass().getResource("/resources/thermometers.png"));;          	   
            	   leftNameLabel = new JLabel("Thermometer");
               }
               if(i==6) {
            	   icon = new ImageIcon(getClass().getResource("/resources/weighingBalance.png"));;            	
            	   leftNameLabel = new JLabel("Weighing Scale" );
               }
               leftNameLabel.setFont(serifFont);
           
               Image img = icon.getImage();
               BufferedImage resizedImg = new BufferedImage(100,100, BufferedImage.TYPE_INT_ARGB); 
               Graphics2D g2d = resizedImg.createGraphics();
               g2d.setColor(Color.orange.darker());
               g2d.fillRect(0, 0, resizedImg.getWidth(), resizedImg.getHeight()); 
               g2d.drawImage(img, 0, 0, 100, 100, null);
               g2d.dispose();
            
               
               
            ImageIcon resizedIcon = new ImageIcon(resizedImg);
            JLabel leftImageLabel = new JLabel(resizedIcon);
            leftNameLabel.setBackground(Color.orange.darker());
            leftNameLabel.setOpaque(true);
          
            JPanel leftPanel = new JPanel(new BorderLayout());
            leftPanel.add(leftImageLabel, BorderLayout.CENTER);
            leftPanel.add(leftNameLabel, BorderLayout.WEST);
            leftContainer.add(leftPanel);
        }

      
        for (int i = 1; i <= 6; i++) {
        	
        	
        	ImageIcon icon = new ImageIcon();
        	JLabel rightNameLabel = new JLabel(); 
        	if(i==1) {
        		icon = new ImageIcon(getClass().getResource("/resources/salt.png"));;
        		 rightNameLabel = new JLabel("         Salt");
              	
              }
               if(i==2) {
            	   icon = new ImageIcon(getClass().getResource("/resources/glucose.png"));;           
            	   rightNameLabel = new JLabel("Glucose" );
               }
               if(i==3) {
            	   icon = new ImageIcon(getClass().getResource("/resources/calciumCarbonate.png"));;            	
            	   rightNameLabel = new JLabel("Calcium ");
            	  
               }
               if(i==4) {
            	   icon = new ImageIcon(getClass().getResource("/resources/sulfur.png"));;         
            	   rightNameLabel = new JLabel("Sulfur ");
               }
               if(i==5) {
            	   icon = new ImageIcon(getClass().getResource("/resources/buffers.png"));;            
            	   rightNameLabel= new JLabel("Buffers");
               }
               if(i==6) {
            	   icon = new ImageIcon(getClass().getResource("/resources/solvent.png"));;
            	   rightNameLabel = new JLabel("Solvent ");
               }
               rightNameLabel.setFont(serifFont);
            
            Image img = icon.getImage();
            BufferedImage resizedImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB); 
            Graphics2D g2d = resizedImg.createGraphics();
            g2d.setColor(Color.orange.darker()); 
            g2d.fillRect(0, 0, resizedImg.getWidth(), resizedImg.getHeight()); 
            g2d.drawImage(img, 0, 0, 100, 100, null);
            g2d.dispose();
            ImageIcon resizedIcon = new ImageIcon(resizedImg);
            JLabel rightImageLabel = new JLabel(resizedIcon);
         
            
            rightNameLabel.setBackground(Color.orange.darker());
            rightNameLabel.setOpaque(true); 
            JPanel rightPanel = new JPanel(new BorderLayout());
           
            rightPanel.add(rightImageLabel, BorderLayout.CENTER);
            rightPanel.add(rightNameLabel, BorderLayout.EAST); 
            
            rightContainer.add(rightPanel);
        }
     
        JPanel innerPanel = new JPanel(new GridLayout(0, 2));
       innerPanel.setBackground(Color.orange.darker());
        panel.add(innerPanel, BorderLayout.CENTER);

        JPanel leftPanel = new JPanel(new GridLayout(6, 1));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0)); 
        leftPanel.setBackground(Color.orange.darker());
        
       
        scienceLabItems.loadFromJson();
        
        for (int i = 0; i < 6; i++) {
        	SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, scienceLabItems.equipments[i], 1);
            JSpinner spinner = new JSpinner(spinnerModel);
            spinner.setPreferredSize(new Dimension(40, spinner.getPreferredSize().height)); 
            JPanel spinnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel spinnerLabel = new JLabel("Stock Left: "+scienceLabItems.equipments[i]); 
          spinnerPanel.setBackground(Color.orange.darker());
          
          
          int index = i;
          
          
           originalValueEquipments[index] = scienceLabItems.equipments[index];
           
          
          
          
         
        
 
           spinnerModel.addChangeListener(e -> {
        	    int newValue = (int) spinner.getValue();
        	    int updatedEquipmentCount = originalValueEquipments[index] - newValue;

        	    if (updatedEquipmentCount < 0) {
        	        scienceLabItems.equipments[index] = 0;
        	        spinnerLabel.setText("<html>Stock Left: <span style='color:#FF0000;'>" + scienceLabItems.equipments[index] + "</span></html>");
        	        spinner.setValue(originalValueEquipments[index]);
        	        JOptionPane.showMessageDialog(null, "Cannot deduct more items. Stock is empty.", "Warning", JOptionPane.WARNING_MESSAGE);
        	        return;
        	    }

        	    scienceLabItems.equipments[index] = updatedEquipmentCount;
        	    if (updatedEquipmentCount == 0) {
        	        spinnerLabel.setText("<html>Stock Left: <span style='color:#FF0000;'>" + updatedEquipmentCount + "</span></html>");
        	    } else {
        	        spinnerLabel.setText("Stock Left: " + updatedEquipmentCount);
        	    }
        	});



          
          
     

     // for the keyboard input ni siya // equipments
          ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().addKeyListener(new KeyAdapter() {
              public void keyReleased(KeyEvent e) {
                  String inputText = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().getText();
                  
                 
                  
                  try {
                      if (inputText.isEmpty()) {
                      
                          scienceLabItems.equipments[index] = originalValueEquipments[index] ;
                      } else {
                          int inputInt = Integer.parseInt(inputText);
                
                          if (inputInt >originalValueEquipments[index] ) {
                            
                              inputInt = originalValueEquipments[index] ;
                              
                             
                              ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setText(String.valueOf(inputInt));
                          }
                        
                          scienceLabItems.equipments[index] = originalValueEquipments[index]  - inputInt;
                      }
                      spinnerLabel.setText("Stock Left: " + scienceLabItems.equipments[index]);
                     
                  } catch (NumberFormatException ex) {
                      
                  }
              }
          });

          
          
          
     
            if(i==1) {
           	 spinnerPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); 
           }
            if(i==2) {
            	 spinnerPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0)); 
            }
            if(i==3) {
            	 spinnerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); 
            }
            if(i==4) {
            	 spinnerPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
            }
            if(i==5) {
           	 spinnerPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0)); 
           }
           
            spinnerPanel.add(spinnerLabel); 
            spinnerPanel.add(spinner);
            leftPanel.add(spinnerPanel);
            
        }
        innerPanel.add(leftPanel);

        JPanel rightPanel = new JPanel(new GridLayout(6, 1));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0)); 
        rightPanel.setBackground(Color.orange.darker());
        
        
        
        for (int i = 0; i < 6; i++) {
        	SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, scienceLabItems.materials[i], 10);
            JSpinner spinner = new JSpinner(spinnerModel);
            spinner.setPreferredSize(new Dimension(60, spinner.getPreferredSize().height)); 
            JPanel spinnerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            spinnerPanel.setBackground(Color.orange.darker());
            JLabel spinnerLabel = new JLabel("Stock Left: " + scienceLabItems.materials[i] +measurements[i]); 
            
            int index = i;
            
            

           originalValueMaterials[index] = scienceLabItems.materials[index];
            
            
            
           
            
   
           spinnerModel.addChangeListener(e -> {
        	    int newValue = (int) spinner.getValue();
        	    int updatedMaterialCount = originalValueMaterials[index] - newValue;

        	    if (updatedMaterialCount < 0) {
        	        scienceLabItems.materials[index] = 0;
        	        spinnerLabel.setText("<html>Stock Left: <span style='color:#FF0000;'>" + scienceLabItems.materials[index] + measurements[index] + "</span></html>");
        	        spinner.setValue(originalValueMaterials[index]); 
        	        JOptionPane.showMessageDialog(null, "Cannot deduct more items. Stock is empty.", "Warning", JOptionPane.WARNING_MESSAGE);
        	        return;
        	    }

        	    scienceLabItems.materials[index] = updatedMaterialCount;
        	    if (updatedMaterialCount == 0) {
        	        spinnerLabel.setText("<html>Stock Left: <span style='color:#FF0000;'>" + updatedMaterialCount + measurements[index] + "</span></html>");
        	    } else {
        	        spinnerLabel.setText("Stock Left: " + updatedMaterialCount + measurements[index]);
        	    }
        	});


            
            
       

       // for the keyboard input ni siya
            ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    String inputText = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().getText();
                    
                   
                    
                    try {
                        if (inputText.isEmpty()) {
                        
                            scienceLabItems.materials[index] = originalValueMaterials[index];
                        } else {
                            int inputInt = Integer.parseInt(inputText);
                  
                            if (inputInt > originalValueMaterials[index]) {
                              
                                inputInt = originalValueMaterials[index];
                                
                               
                                ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setText(String.valueOf(inputInt));
                            }
                          
                            scienceLabItems.materials[index] = originalValueMaterials[index] - inputInt;
                        }
                        spinnerLabel.setText("Stock Left: " + scienceLabItems.materials[index] + measurements[index]);
                  
                    } catch (NumberFormatException ex) {
                        
                    }
                }
            });






            if(i==1) {
              	 spinnerPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); 
              }
               if(i==2) {
               	 spinnerPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0)); 
               }
               if(i==3) {
               	 spinnerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); 
               }
               if(i==4) {
               	 spinnerPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
               }
               if(i==5) {
              	 spinnerPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0)); 
              }
              
              
               spinnerPanel.add(spinnerLabel); 
            spinnerPanel.add(spinner);
            rightPanel.add(spinnerPanel);
            
        }
        innerPanel.add(rightPanel);
        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(false); 
        
   }
    
    public void getUserIndex(int index) {
		this.userIndex=index;
		
	}
    
    public void updateInventoryItemQuantity(int userIndex, String itemName, int quantityToAdd) {
        // Check if the provided index is valid
        if (userIndex >= 0 && userIndex < userInformation.inventoryList.size()) {
 
            InventoryItem inventoryItem = userInformation.inventoryList.get(userIndex);

          
            for (int i = 0; i < inventoryItem.names.length; i++) {
                if (inventoryItem.names[i].equals(itemName)) {
           
                    inventoryItem.quantities[i] += quantityToAdd; 
                  
                    return; // Exit after updating
                }
            }

          
            System.out.println("Item not found: " + itemName);
        } else {
         
            System.out.println("Invalid index!");
        }
    }



}