//Untuk ComboBox
public class ComboRoles {
	protected String ID;
	protected String Description;

	public ComboRoles() {

	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public ComboRoles(String iD, String description) {
		super();
		ID = iD;
		Description = description;
	}

}
