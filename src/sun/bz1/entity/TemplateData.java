package sun.bz1.entity;

/**
 * 接单区域表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/04
 */

public class TemplateData {

    private String key;
    private String value;

    private String color;

    public TemplateData() { super(); } public TemplateData(String value, String color) { super(); this.value = value; this.color = color; } public String getValue() { return value; } public void setValue(String value) { this.value = value; } public String getColor() { return color; } public void setColor(String color) { this.color = color; }
}