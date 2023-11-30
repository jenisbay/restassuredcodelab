package com.example.restassuredproject.models.dummy.users;

public class Bank{
    private String cardExpire;
    private String cardNumber;
    private String cardType;
    private String currency;
    private String iban;

    public String getCardExpire() {
        return cardExpire;
    }

    public void setCardExpire(String cardExpire) {
        this.cardExpire = cardExpire;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "cardExpire='" + cardExpire + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardType='" + cardType + '\'' +
                ", currency='" + currency + '\'' +
                ", iban='" + iban + '\'' +
                '}';
    }
}