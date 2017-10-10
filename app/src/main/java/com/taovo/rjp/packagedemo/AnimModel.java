package com.taovo.rjp.packagedemo;

/**
 * @author Gimpo create on 2017/6/12 12:17
 * @email : jimbo922@163.com
 */

public class AnimModel {
    // 1 open  0 close
    public int state;
    public int number;

    public AnimModel(int number, int state){
        this.number = number;
        this.state = state;
    }
}
