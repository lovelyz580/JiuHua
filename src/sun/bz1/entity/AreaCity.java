package sun.bz1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * 接单区域表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/04
 */

public class AreaCity {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;

    /**
	 * 接单区域ID(JDQY+UUID)
	 */
    private String areaid;


    /**
	 * 接单区域状态(Y:有效/N:无效)
	 */
    private String areastate;


    /**
     * 省份名称
     */
    private String areaprovince;

    /**
     * 省份代码
     */
    private String areaprovincecode;

    /**
     * 城市名称
     */
    private String areacity;

    /**
     * 上级代码
     */
    private String areacitycode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getAreastate() {
        return areastate;
    }

    public void setAreastate(String areastate) {
        this.areastate = areastate;
    }

    public String getAreaprovince() {
        return areaprovince;
    }

    public void setAreaprovince(String areaprovince) {
        this.areaprovince = areaprovince;
    }

    public String getAreaprovincecode() {
        return areaprovincecode;
    }

    public void setAreaprovincecode(String areaprovincecode) {
        this.areaprovincecode = areaprovincecode;
    }

    public String getAreacity() {
        return areacity;
    }

    public void setAreacity(String areacity) {
        this.areacity = areacity;
    }

    public String getAreacitycode() {
        return areacitycode;
    }

    public void setAreacitycode(String areacitycode) {
        this.areacitycode = areacitycode;
    }
}