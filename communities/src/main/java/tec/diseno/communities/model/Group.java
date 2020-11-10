package tec.diseno.communities.model;

public class Group extends AbstractAdministrativeLevel{
	private int number;
	private boolean established;
	
	public Group() {
		
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public boolean isEstablished() {
		return established;
	}
	public void setEstablished(boolean established) {
		this.established = established;
	}
}
