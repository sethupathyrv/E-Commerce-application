package com.ooad.web.model;

import org.json.JSONObject;

import java.util.Date;

public class Transaction {
    private final int id;
    private final int amount;
    private final Order order;
    private final UserAccount userAccount;
    private final Date date;
    private final int status;

    public Transaction(int id, int amount, Order order, UserAccount userAccount, Date date, int status) {
        this.id = id;
        this.amount = amount;
        this.order = order;
        this.userAccount = userAccount;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public Order getOrder() {
        return order;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public Date getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", order=" + order +
                ", userAccount=" + userAccount +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
    public JSONObject toJSON() {
        JSONObject JsonObject = new JSONObject();
        JsonObject.put("id", id);
        JsonObject.put("amount", amount);
        JsonObject.put("order", order.toJSON());
        JsonObject.put("useraccount", userAccount.toJSON());
        JsonObject.put("date", date);
        JsonObject.put("status", status);

        return JsonObject;
    }
}
