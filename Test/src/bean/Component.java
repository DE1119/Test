package bean;

public class Component {
	
	private int id;
	private String specification="";
	private String model="";
	private double unit_price=0.00;
	private int number=0;
	private String location="";
	private String manager="";
	private String note="";

    @Override  
    public String toString()  
    {  
        return "Component [id=" + id +", specification="+ specification +", model=" + model +
        		", unit_price=" + unit_price +" ,number=" + number +", location=" + location +
        		", manager=" + manager +", note=" + note + "]";  
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

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
