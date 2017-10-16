package com.javalab.Storage;

public class Storage
{
    private Data d;

    protected Double capacity;

    public Storage()
    {
        d = new Data();
        this.capacity = 2048d;
    }

    public Data getD()
    {
        return d;
    }

    public void setD(Data d)
    {
        this.d = d;
    }
}
