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

    private String getName()
    {
        return this.name;
    }

    private double getValue()
    {
        return this.value;
    }

    private String getWebsiteLink()
    {
        return this.websiteLink;
    }
}
