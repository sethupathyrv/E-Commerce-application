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

} 