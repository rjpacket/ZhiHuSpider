package com.taovo.rjp.packagedemo.listview;

/**
 * @Author：RJP on 2016/11/8 18:21
 */

public class SeniorZhuiHaoModel {

    /**
     * lotteryType : 1014
     * phase : 161108066
     * endSaleTime : 1478600484000
     */

    private int lotteryType;
    private String phase = "2017001";
    private long endSaleTime;

    private boolean isSelect = true;
    private long num = 1;
    private boolean isFocus = false;

    public int getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(int lotteryType) {
        this.lotteryType = lotteryType;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public long getEndSaleTime() {
        return endSaleTime;
    }

    public void setEndSaleTime(long endSaleTime) {
        this.endSaleTime = endSaleTime;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public boolean isFocus() {
        return isFocus;
    }

    public void setFocus(boolean focus) {
        isFocus = focus;
    }
}
