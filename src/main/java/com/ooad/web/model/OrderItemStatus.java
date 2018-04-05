package com.ooad.web.model;

public enum OrderItemStatus {
    WAITING_FOR_SELLER {
        @Override
        public String getStatus() {
            return "Waiting for Seller Response";
        }

        @Override
        public int getStatusCode() {
            return 301;
        }
    },
    SHIPPED {
        @Override
        public String getStatus() {
            return "Seller Shipped the item";
        }

        @Override
        public int getStatusCode() {
            return 302;
        }
    },
    DELIVERED {
        @Override
        public String getStatus() {
            return "User got the Item";
        }

        @Override
        public int getStatusCode() {
            return 303;
        }
    };
    public abstract String getStatus();
    public abstract int getStatusCode();
    public static OrderItemStatus getOrderItemStatus(int code){
        switch (code){
            case 301:
                return OrderItemStatus.WAITING_FOR_SELLER;
            case 302:
                return OrderItemStatus.SHIPPED;
            case 303:
                return OrderItemStatus.DELIVERED;
            default:
                return null;
        }
    }

}
