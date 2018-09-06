package com.haier.smarthomesdk;

public class RequestErrorBean {

    /**
     * kind : error
     * resource : /v1/appliance/D828C9165BA0/control/refrigerator-start-hot-water
     * code : 400
     * message : The request could not be understood by the server due to malformed syntax
     * reason : user error temperature out of bounds
     */

    public String kind;
    public String resource;
    public int code;
    public String message;
    public String reason;
}
