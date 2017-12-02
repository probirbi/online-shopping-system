package onlineshopping;

public class Products {

    private int id;
    private String name;
    private float price;
    private String addDate;
    private byte[] picture;

    public Products(int pid, String pname, float pprice, String AddDate, byte[] pimg) {
        this.id = pid;
        this.name = pname;
        this.price = pprice;
        this.addDate = AddDate;
        this.picture = pimg;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getAddDate() {
        return addDate;
    }

    public byte[] getImage() {
        return picture;
    }
}
