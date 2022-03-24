package com.money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @version: 1.0.1
 * @author: HuaZHengWei
 * @date: 2022/02/21 12:01
 * @description: N_WXZJ_WXZQXX_FPBF_MX(维修支取信息明细表)
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NWxzjWxzqxxFpbfMx implements Serializable {

    /**
     * N_WXZJ_WXZQXX_FPBF_MX
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 本次拨付金额
     */
    private BigDecimal bcbfje;

    /**
     * 欠款金额
     */
    private BigDecimal qkje;

    /**
     * 是否补齐欠款
     */
    private String sfbqqk;

    /**
     * 房屋主键 ID
     */
    private Long fwid;

    /**
     * 自然幢主键 ID
     */
    private Long zrzid;

    /**
     * 自然幢名称/房屋室号
     */
    private String mc;

    /**
     * 地址
     */
    private String dz;

    /**
     * 房屋性质
     */
    private String fwxz;

    /**
     * 建筑面积
     */
    private BigDecimal jzmj;

    /**
     * 业主姓名
     */
    private String yzmc;

    /**
     * 业务流水号
     */
    private String ywlsh;

    /**
     * 证件号码
     */
    private String zjhm;

    /**
     * 证件类型
     */
    private String zjlx;

    /**
     * 房屋编号
     */
    private String fwbhn;

    /**
     * 自然幢编号
     */
    private String zrzbhn;

    /**
     * 实际分摊金额
     */
    private BigDecimal sjftje;

    /**
     * 应分摊金额
     */
    private BigDecimal yftje;

    /**
     * 资金账号
     */
    private String zjzh;

    /**
     * 账户类型
     */
    private String zhlx;

    /**
     * 存款账号
     */
    private String ckzh;

    /**
     * 存款类型
     */
    private String cklx;

    /**
     * 账户余额
     */
    private BigDecimal yue;

    /**
     * 支取本金
     */
    private BigDecimal stbj;

    /**
     * 支取利息
     */
    private BigDecimal stlx;

    /**
     * ID
     * @return ID ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 本次拨付金额
     * @return BCBFJE 本次拨付金额
     */
    public BigDecimal getBcbfje() {
        return bcbfje;
    }

    /**
     * 本次拨付金额
     * @param bcbfje 本次拨付金额
     */
    public void setBcbfje(BigDecimal bcbfje) {
        this.bcbfje = bcbfje;
    }

    /**
     * 欠款金额
     * @return QKJE 欠款金额
     */
    public BigDecimal getQkje() {
        return qkje;
    }

    /**
     * 欠款金额
     * @param qkje 欠款金额
     */
    public void setQkje(BigDecimal qkje) {
        this.qkje = qkje;
    }

    /**
     * 是否补齐欠款
     * @return SFBQQK 是否补齐欠款
     */
    public String getSfbqqk() {
        return sfbqqk;
    }

    /**
     * 是否补齐欠款
     * @param sfbqqk 是否补齐欠款
     */
    public void setSfbqqk(String sfbqqk) {
        this.sfbqqk = sfbqqk;
    }

    /**
     * 房屋主键 ID
     * @return FWID 房屋主键 ID
     */
    public Long getFwid() {
        return fwid;
    }

    /**
     * 房屋主键 ID
     * @param fwid 房屋主键 ID
     */
    public void setFwid(Long fwid) {
        this.fwid = fwid;
    }

    /**
     * 自然幢主键 ID
     * @return ZRZID 自然幢主键 ID
     */
    public Long getZrzid() {
        return zrzid;
    }

    /**
     * 自然幢主键 ID
     * @param zrzid 自然幢主键 ID
     */
    public void setZrzid(Long zrzid) {
        this.zrzid = zrzid;
    }

    /**
     * 自然幢名称/房屋室号
     * @return MC 自然幢名称/房屋室号
     */
    public String getMc() {
        return mc;
    }

    /**
     * 自然幢名称/房屋室号
     * @param mc 自然幢名称/房屋室号
     */
    public void setMc(String mc) {
        this.mc = mc;
    }

    /**
     * 地址
     * @return DZ 地址
     */
    public String getDz() {
        return dz;
    }

    /**
     * 地址
     * @param dz 地址
     */
    public void setDz(String dz) {
        this.dz = dz;
    }

    /**
     * 房屋性质
     * @return FWXZ 房屋性质
     */
    public String getFwxz() {
        return fwxz;
    }

    /**
     * 房屋性质
     * @param fwxz 房屋性质
     */
    public void setFwxz(String fwxz) {
        this.fwxz = fwxz;
    }

    /**
     * 建筑面积
     * @return JZMJ 建筑面积
     */
    public BigDecimal getJzmj() {
        return jzmj;
    }

    /**
     * 建筑面积
     * @param jzmj 建筑面积
     */
    public void setJzmj(BigDecimal jzmj) {
        this.jzmj = jzmj;
    }

    /**
     * 业主姓名
     * @return YZMC 业主姓名
     */
    public String getYzmc() {
        return yzmc;
    }

    /**
     * 业主姓名
     * @param yzmc 业主姓名
     */
    public void setYzmc(String yzmc) {
        this.yzmc = yzmc;
    }

    /**
     * 业务流水号
     * @return YWLSH 业务流水号
     */
    public String getYwlsh() {
        return ywlsh;
    }

    /**
     * 业务流水号
     * @param ywlsh 业务流水号
     */
    public void setYwlsh(String ywlsh) {
        this.ywlsh = ywlsh;
    }

    /**
     * 证件号码
     * @return ZJHM 证件号码
     */
    public String getZjhm() {
        return zjhm;
    }

    /**
     * 证件号码
     * @param zjhm 证件号码
     */
    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    /**
     * 证件类型
     * @return ZJLX 证件类型
     */
    public String getZjlx() {
        return zjlx;
    }

    /**
     * 证件类型
     * @param zjlx 证件类型
     */
    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    /**
     * 房屋编号
     * @return FWBHN 房屋编号
     */
    public String getFwbhn() {
        return fwbhn;
    }

    /**
     * 房屋编号
     * @param fwbhn 房屋编号
     */
    public void setFwbhn(String fwbhn) {
        this.fwbhn = fwbhn;
    }

    /**
     * 自然幢编号
     * @return ZRZBHN 自然幢编号
     */
    public String getZrzbhn() {
        return zrzbhn;
    }

    /**
     * 自然幢编号
     * @param zrzbhn 自然幢编号
     */
    public void setZrzbhn(String zrzbhn) {
        this.zrzbhn = zrzbhn;
    }

    /**
     * 实际分摊金额
     * @return SJFTJE 实际分摊金额
     */
    public BigDecimal getSjftje() {
        return sjftje;
    }

    /**
     * 实际分摊金额
     * @param sjftje 实际分摊金额
     */
    public void setSjftje(BigDecimal sjftje) {
        this.sjftje = sjftje;
    }

    /**
     * 应分摊金额
     * @return YFTJE 应分摊金额
     */
    public BigDecimal getYftje() {
        return yftje;
    }

    /**
     * 应分摊金额
     * @param yftje 应分摊金额
     */
    public void setYftje(BigDecimal yftje) {
        this.yftje = yftje;
    }

    /**
     * 资金账号
     * @return ZJZH 资金账号
     */
    public String getZjzh() {
        return zjzh;
    }

    /**
     * 资金账号
     * @param zjzh 资金账号
     */
    public void setZjzh(String zjzh) {
        this.zjzh = zjzh;
    }

    /**
     * 账户类型
     * @return ZHLX 账户类型
     */
    public String getZhlx() {
        return zhlx;
    }

    /**
     * 账户类型
     * @param zhlx 账户类型
     */
    public void setZhlx(String zhlx) {
        this.zhlx = zhlx;
    }

    /**
     * 存款账号
     * @return CKZH 存款账号
     */
    public String getCkzh() {
        return ckzh;
    }

    /**
     * 存款账号
     * @param ckzh 存款账号
     */
    public void setCkzh(String ckzh) {
        this.ckzh = ckzh;
    }

    /**
     * 存款类型
     * @return CKLX 存款类型
     */
    public String getCklx() {
        return cklx;
    }

    /**
     * 存款类型
     * @param cklx 存款类型
     */
    public void setCklx(String cklx) {
        this.cklx = cklx;
    }

    /**
     * 账户余额
     * @return YUE 账户余额
     */
    public BigDecimal getYue() {
        return yue;
    }

    /**
     * 账户余额
     * @param yue 账户余额
     */
    public void setYue(BigDecimal yue) {
        this.yue = yue;
    }

    /**
     * 支取本金
     * @return STBJ 支取本金
     */
    public BigDecimal getStbj() {
        return stbj;
    }

    /**
     * 支取本金
     * @param stbj 支取本金
     */
    public void setStbj(BigDecimal stbj) {
        this.stbj = stbj;
    }

    /**
     * 支取利息
     * @return STLX 支取利息
     */
    public BigDecimal getStlx() {
        return stlx;
    }

    /**
     * 支取利息
     * @param stlx 支取利息
     */
    public void setStlx(BigDecimal stlx) {
        this.stlx = stlx;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bcbfje=").append(bcbfje);
        sb.append(", qkje=").append(qkje);
        sb.append(", sfbqqk=").append(sfbqqk);
        sb.append(", fwid=").append(fwid);
        sb.append(", zrzid=").append(zrzid);
        sb.append(", mc=").append(mc);
        sb.append(", dz=").append(dz);
        sb.append(", fwxz=").append(fwxz);
        sb.append(", jzmj=").append(jzmj);
        sb.append(", yzmc=").append(yzmc);
        sb.append(", ywlsh=").append(ywlsh);
        sb.append(", zjhm=").append(zjhm);
        sb.append(", zjlx=").append(zjlx);
        sb.append(", fwbhn=").append(fwbhn);
        sb.append(", zrzbhn=").append(zrzbhn);
        sb.append(", sjftje=").append(sjftje);
        sb.append(", yftje=").append(yftje);
        sb.append(", zjzh=").append(zjzh);
        sb.append(", zhlx=").append(zhlx);
        sb.append(", ckzh=").append(ckzh);
        sb.append(", cklx=").append(cklx);
        sb.append(", yue=").append(yue);
        sb.append(", stbj=").append(stbj);
        sb.append(", stlx=").append(stlx);
        sb.append("]");
        return sb.toString();
    }
}