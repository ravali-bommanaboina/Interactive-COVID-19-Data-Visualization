
package app;


public class COVID {
	
	public String country;
	public Double vaccine;
	public Double poverty;
	public COVID(String country,Double poverty, Double vaccine)
	{
		this.country=country;
		this.vaccine=vaccine;
		this.poverty=poverty;
		
	}

	@Override
	public String toString(){
	      return country;
	}
}
