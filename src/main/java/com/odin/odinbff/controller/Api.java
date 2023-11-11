package com.odin.odinbff.controller;

public final class Api {

    public final static String PATH_PARAM_ID = "/{id}";

    public final static String PATH_PARAM_ID_ACTIVATE = PATH_PARAM_ID + "/activate";

    public final static String PATH_PARAM_ID_INACTIVATE = PATH_PARAM_ID + "/inactivate";

    public final static class Client {
        public final static String CLIENT_RESOURCE = "/api/client";
        public final static String CLIENT_READ_BY_ID = CLIENT_RESOURCE + PATH_PARAM_ID;

    }

    public final static class Purveyor {

        public final static String PURVEYOR_RESOURCE = "/api/purveyor";

        public final static String PURVEYOR_READ_BY_ID = PURVEYOR_RESOURCE + PATH_PARAM_ID;
    }

    public final static class Brand {

        public final static String BRAND_RESOURCE = "/api/brand";

        public final static String BRAND_READ_BY_ID = BRAND_RESOURCE + PATH_PARAM_ID;

        public final static String BRAND_ACTIVATE = BRAND_RESOURCE + PATH_PARAM_ID_ACTIVATE;

        public final static String BRAND_INACTIVATE = BRAND_RESOURCE + PATH_PARAM_ID_INACTIVATE;
    }

    public final static class Product {

        public final static String PRODUCT_RESOURCE = "/api/product";

        public final static String PRODUCT_READ_BY_ID = PRODUCT_RESOURCE + PATH_PARAM_ID;
    }

    public final static class Sale {

        public final static String SALE = "/api/sale";

        public final static String SALE_READ_BY_ID = SALE + PATH_PARAM_ID;
    }

    public final static class ServiceOrder {

        public final static String SERVICE_ORDER_RESOURCE = "/api/service-order";

        public final static String SERVICE_ORDER_READ_BY_ID = SERVICE_ORDER_RESOURCE + PATH_PARAM_ID;

        public final static String SERVICE_ORDER_CLOSE_PATH_PARAM = PATH_PARAM_ID + "/close";
    }

    public final static class Payment {

        public final static String PAYMENT_RESOURCE = "/api/payment";

        public final static String PAYMENT_READ_BY_ID = PAYMENT_RESOURCE + PATH_PARAM_ID;
    }
}
