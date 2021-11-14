public class Product {
    private String name;
    private double value;
    private String websiteLink;

    Product(String name, double value, String websiteLink)
    {
        this.name = name;
        this.value = value;
        this.websiteLink = websiteLink;
    }

    public String getName()
    {
        return this.name;
    }

    public double getValue()
    {
        return this.value;
    }

    public String getWebsiteLink()
    {
        return this.websiteLink;
    }
}
