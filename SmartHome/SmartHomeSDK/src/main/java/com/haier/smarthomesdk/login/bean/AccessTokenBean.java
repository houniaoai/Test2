package com.haier.smarthomesdk.login.bean;

import java.io.Serializable;

/**
 * Created by 01438511 on 2018/8/30.
 */

public class AccessTokenBean implements Serializable {

    /**
     * "access_token":"fFAGRNJru1FTz70EXAMPLE",
     * "expires_in":3920,
     * "id_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ",
     * "token_type":"Bearer"
     */

    public String access_token;
    public String expires_in;
    public String id_token;
    public String token_type;

    public String getAccess_token() {
        return access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public String getId_token() {
        return id_token;
    }

    public String getToken_type() {
        return token_type;
    }

    @Override
    public String toString() {
        return "AccessTokenBean{" +
                "access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", id_token='" + id_token + '\'' +
                ", token_type='" + token_type + '\'' +
                '}';
    }


}
