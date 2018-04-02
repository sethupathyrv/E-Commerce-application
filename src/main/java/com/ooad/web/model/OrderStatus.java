package com.ooad.web.model;

import org.json.JSONObject;

public enum OrderStatus {
    CANCELLED(){
        @Override
        public String getStatus() {
            return "Cancelled";
        }

        @Override
        public int getStatusCode() {
            return 103;
        }

        @Override
        public boolean isDelivered() {
            return false;
        }
    },
    PLACED() {
        @Override
        public String getStatus() {
            return "Order Placed";
        }

        @Override
        public int getStatusCode() {
            return 102;
        }

        @Override
        public boolean isDelivered() {
            return false;
        }
    },
    AMOUNT_DEDUCTED(){
        @Override
        public String getStatus() {
            return "Amount Deducted from User";
        }

        @Override
        public int getStatusCode() {
            return 0;
        }

        @Override
        public boolean isDelivered() {
            return false;
        }
    },
    DELIVERED() {
        @Override
        public String getStatus() {
            return "Delivered";
        }

        @Override
        public int getStatusCode() {
            return 101;
        }

        @Override
        public boolean isDelivered() {
            return true;
        }
    };

    public abstract String getStatus();
    public abstract int getStatusCode();
    public abstract boolean isDelivered();
    public static OrderStatus getStatusByCode(int code){
        switch (code){
            case 101:
                return OrderStatus.DELIVERED;
            case 102:
                return OrderStatus.PLACED;
            case 103:
                return OrderStatus.CANCELLED;
            default:
                return null;
        }
    }

} 