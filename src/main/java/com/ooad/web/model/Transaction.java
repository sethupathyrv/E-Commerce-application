package com.ooad.web.model;

import org.json.JSONObject;

import java.util.Date;

public class Transaction {
    private final int id;
    private final int amount;
    private final Order order;
    private final int userAccountId;
    private final Date date;
    private final int status;

    public Transaction(int id, int amount, Order order, int userAccountId, Date date, int status) {
        this.id = id;
        this.amount = amount;
        this.order = order;
        this.userAccountId = userAccountId;
        this.date = date;
        this.status = status;
    }


    public int getUserAccountId() {
        return userAccountId;
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
                ", userAccount=" + userAccountId +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
    public JSONObject toJSON() {
        JSONObject JsonObject = new JSONObject();
        JsonObject.put("id", id);
        JsonObject.put("amount", amount);
        JsonObject.put("order", order.toJSON());
        JsonObject.put("useraccount", userAccountId);
        JsonObject.put("date", date);
        JsonObject.put("status", status);

        return JsonObject;
    }
}
