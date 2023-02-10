package company.movierental.utils.genkey;

import java.util.UUID;

public class UniqueIDGenerator  {
	public static UUID generateUniqueID() {
		UUID uniqueID = UUID.randomUUID();
		return uniqueID;
	}
}