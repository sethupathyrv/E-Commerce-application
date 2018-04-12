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

        @Override
        public boolean isDelivered() {
            return false;
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

        @Override
        public boolean isDelivered() {
            return false;
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

        @Override
        public boolean isDelivered() {
            return true;
        }
    },
    REVIEWED {
        @Override
        public String getStatus() {
            return "User Reviewed the Seller";
        }

        @Override
        public int getStatusCode() {
            return 304;
        }

        @Override
        public boolean isDelivered() {
            return true;
        }
    };
    public abstract String getStatus();
    public abstract int getStatusCode();
    public abstract boolean isDelivered();
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
