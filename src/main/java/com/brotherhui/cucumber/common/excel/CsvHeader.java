package com.brotherhui.cucumber.common.excel;

public class CsvHeader implements Comparable<CsvHeader>{

    private String title;

    private int order;

    private String filed;

    private Class<?> filedClazz;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public Class<?> getFiledClazz() {
        return filedClazz;
    }

    public void setFiledClazz(Class<?> filedClazz) {
        this.filedClazz = filedClazz;
    }

    public int compareTo(CsvHeader o) {
        return order - o.order;
    }

    public CsvHeader(String title, int order, String filed, Class<?> filedClazz) {
        super();
        this.title = title;
        this.order = order;
        this.filed = filed;
        this.filedClazz = filedClazz;
    }

}
