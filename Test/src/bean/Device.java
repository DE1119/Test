package bean;

public class Device {
	
	private int id;
	private String specification="";
	private String model="";
	private String location="";
	private String manager="";
	private String state="";
	private double unit_price=0.00;
	private String purc_place="";
	private String note="";
	
	@Override  
    public String toString()  
    {  
        return "User [id=" + id +", specification="+ specification +", model=" + model +", location=" + 
        		location +", manager=" + manager + " ,state=" + state +", unit_price=" + unit_price +
        		", purc_place=" + purc_place + ", note=" + note + "]";  
    }  
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public String getPurc_place() {
		return purc_place;
	}

	public void setPurc_place(String purc_place) {
		this.purc_place = purc_place;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}