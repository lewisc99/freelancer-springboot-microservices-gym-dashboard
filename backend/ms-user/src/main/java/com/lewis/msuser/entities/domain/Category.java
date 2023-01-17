package com.lewis.msuser.entities.domain;

public enum Category {
    BASIC(0),
    VIP(1),
    PREMIUM(2);
    private int code;

    private Category(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }

    public static  Category valueOf(int code)
    {
        for (Category value: Category.values())
        {
            if (value.getCode() == code)
            {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
