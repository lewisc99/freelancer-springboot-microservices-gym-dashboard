package com.lewis.msuser.entities.domain;

public enum Status {

    WAITING_PAYMENT(1),
    PAID(2),
    CANCELED(5);
    private int code;

    private Status(int code)
    {
        this.code = code;
    }
    public int getCode()
    {
        return code;
    }

    public static  Status valueOf(int code)
    {
        for (Status value: Status.values())
        {
            if (value.getCode() == code)
            {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
