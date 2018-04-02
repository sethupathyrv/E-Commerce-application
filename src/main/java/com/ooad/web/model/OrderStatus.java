package com.ooad.web.model;

import org.json.JSONObject;

public enum OrderStatus {
    PLACED() {
        @Override
        public String getStatus() {
            return "Order Placed by User";
        }

        @Override
        public int getStatusCode() {
            return 102;
        }
    },
    MONEY_PAID(){
        @Override
        public String getStatus() {
            return "Money Paid";
        }

        @Override
        public int getStatusCode() {
            return 103;
        }
    },
    PARTIALLY_SHIPPED(){
        @Override
        public String getStatus() {
            return "Partially Shipped";
        }

        @Override
        public int getStatusCode() {
            return 104;
        }
    },
    SHIPPED(){
        @Override
        public String getStatus() {
            return "Shipped";
        }

        @Override
        public int getStatusCode() {
            return 105;
        }

    },
    PARTIALLY_DELIVERED(){
        @Override
        public String getStatus() {
            return "Partially Delivered";
        }

        @Override
        public int getStatusCode() {
            return 106;
        }
    },
    DELIVERED() {
        @Override
        public String getStatus() {
            return "Delivered";
        }

        @Override
        public int getStatusCode() {
            return 107;
        }
    },
    CANCELLED(){
        @Override
        public String getStatus() {
            return "Cancelled";
        }

        @Override
        public int getStatusCode() {
            return 108;
        }
    };

    public abstract String getStatus();
    public abstract int getStatusCode();
    public static OrderStatus getStatusByCode(int code){
        switch (code){
            case 102:
                return OrderStatus.PLACED;
            case 103:
                return OrderStatus.MONEY_PAID;
            case 104:
                return OrderStatus.PARTIALLY_SHIPPED;
            case 105:
                return OrderStatus.SHIPPED;
            case 106:
                return OrderStatus.PARTIALLY_DELIVERED;
            case 107:
                return OrderStatus.DELIVERED;
            case 108:
                return OrderStatus.CANCELLED;
            default:
                return null;
        }
    }

} 