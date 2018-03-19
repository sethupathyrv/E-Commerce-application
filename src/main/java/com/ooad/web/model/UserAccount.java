package com.ooad.web.model;

import org.json.JSONObject;

public class UserAccount {
    private final int id;
    private final String AccountName;
    private final int AccountNumber;
    private final int amount;

    public UserAccount(int id, String accountName, int accountNumber, int amount) {
        this.id = id;
        AccountName = accountName;
        AccountNumber = accountNumber;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getAccountName() {
        return AccountName;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", AccountName='" + AccountName + '\'' +
                ", AccountNumber=" + AccountNumber +
                ", amount='" + amount + '\'' +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject JsonObject = new JSONObject();
        JsonObject.put("id", id);
        JsonObject.put("name",AccountName);
        JsonObject.put("number",AccountNumber);
        JsonObject.put("amount",amount);
        return JsonObject;
    }
}
