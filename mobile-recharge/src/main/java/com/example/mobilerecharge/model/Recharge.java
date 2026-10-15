
package com.example.mobilerecharge.model;

public class Recharge {

    private Long id;
    private String mobileNumber;
    private String operator;
    private double amount;
    private String status;

    public Recharge() {
    }

    public Recharge(Long id, String mobileNumber,
                    String operator, double amount,
                    String status) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.operator = operator;
        this.amount = amount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
