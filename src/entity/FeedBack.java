package entity;

/**
 * @author Dulanjana Lakshan Kumarasingha
 */
public class FeedBack {
    private int id;
    private int customerId;
    private String message;
    private int rating;

    public FeedBack() {
    }

    public FeedBack(int id, int customerId, String message, int rating) {
        this.id = id;
        this.customerId = customerId;
        this.message = message;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "FeedBack{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", message='" + message + '\'' +
                ", rating=" + rating +
                '}';
    }
}
