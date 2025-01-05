package sciencelab;


public class ScienceLabInventory {
    
    static HomePage homePage = new HomePage();
    static CreationPage creationPage = new CreationPage();
    static RecoveryPage recoveryPage = new RecoveryPage();
    static LabDashBoard labDashBoard = new LabDashBoard();
    static InventoryPage inventoryPage = new InventoryPage();
    static ScienceLabItems scienceLabItems = new ScienceLabItems();
    static AdminPanel adminPanel = new AdminPanel();
    static FirstHomePage firstHomePage = new FirstHomePage();

    public static void main(String[] args) {
	//recoveryPage.StartRecovery();
    	firstHomePage.StartFirstHomePage();
//adminPanel.StartAdminPanel();
    	
//homePage.ShowGUI();
     //creationPage.createAndShowGUI();
  
//inventoryPage.StartInventoryPage();
    	//scienceLabItems.saveToJson();
//labDashBoard.StartDashBoard();
    	
    }
}
