package model;

public class Product
{
	private String hex;
	private String productName;
	private String sKU;
	
	public Product(String hex, String productName, String sKU)
	{
		super();
		this.hex = hex;
		this.productName = productName;
		this.sKU = sKU;
	}
	
	public String getHex()
	{
		return hex;
	}
	public String getProductName()
	{
		return productName;
	}
	public String getsKU()
	{
		return sKU;
	}
}
