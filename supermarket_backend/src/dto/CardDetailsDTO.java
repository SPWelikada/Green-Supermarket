package dto;

public class CardDetailsDTO {
    private String cardNumber ;
    private String cardExpire ;
    private String cardCVV ;

    public CardDetailsDTO(String cardNumber, String cardExpire, String cardCVV) {
        this.cardNumber = cardNumber;
        this.cardExpire = cardExpire;
        this.cardCVV = cardCVV;
    }

    public CardDetailsDTO() {

    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpire() {
        return cardExpire;
    }

    public void setCardExpire(String cardExpire) {
        this.cardExpire = cardExpire;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }
}
