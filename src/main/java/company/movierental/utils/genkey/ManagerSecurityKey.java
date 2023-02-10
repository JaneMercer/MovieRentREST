package company.movierental.utils.genkey;


/**
 * Would be some kind of security system that manages manager keys for different level DataBase access
 * Right now I'll just create hardcoded key to present API functionality
 */
public class ManagerSecurityKey {
	
	private String managerKey;

	public String getManagerKey() {
		return managerKey;
	}
	
	//Here could be written some method to assign special key depending on managers role
	public void setManagerKey(String managerKey) {
		this.managerKey = managerKey;
	}

}
