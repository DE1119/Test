package bean;  

//POJO 简单无规则java对象：最基本的Java Bean，只有属性字段及setter和getter方法！。
public class User  
{  
    private int id;  
    private String type="";
	private String username="";  
    private String sex="";
    private String email="";
    private String tel="";
    private String work_unit;
    private String pswd=""; 
    private String note="";
    
    @Override  
    public String toString()  
    {  
        return "User [id=" + id +", type="+ type +", username=" + username +", sex=" + sex +
        		" ,email=" + email +", tel=" + tel +", work_unit=" + work_unit + ", pswd=" +
        		pswd +", note=" + note + "]";  
    }  
     
    public int getId()  
    {  
        return id;  
    }  
    public void setId(int id)  
    {  
        this.id = id;  
    }  
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getUsername()  {  
	        return username;  
	}  
    public void setUsername(String username)  
	    {  
	        this.username = username;  
	    }  
	    
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWork_unit() {
		return work_unit;
	}

	public void setWork_unit(String work_unit) {
		this.work_unit = work_unit;
	}
	
	  public String getPswd()  
	    {  
	        return pswd;  
	    }  
	    public void setPswd(String pswd)  
	    {  
	        this.pswd = pswd;    
	    }

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}  