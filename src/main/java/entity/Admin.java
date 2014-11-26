package entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Admin {
	@NotNull
	private String name;
	@Size(min=2,max=20)
	private String password;
	
	public Admin(){
		
	}
	
	public Admin(String name,String password){
		this.name=name;
		this.password=password;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
