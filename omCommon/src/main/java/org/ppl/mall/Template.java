package org.ppl.mall;

/**
 * class模板
 * @author Smith
 */
public class Template {
    /*********************Field**********************/
    /*----------------static field-------------------*/
    public static final int MAX_SIZE = 200;
    /*-------------------field-----------------------*/
    private Integer status;

    /*********************Method**********************/
    /*----------------constructor--------------------*/
    public Template(){}

    /*--------------public static method-------------*/
    /**
     * 创建对象
     * @return
     */
    public static Template create() {
        return new Template();
    }


    /*-----------------public method-----------------*/

    /**
     * 获取状态
     * @return
     */
    public Integer getStatus() {
        return status;
    }


    /*-----------------private method----------------*/
    //private方法简单注释, 不写javadoc
    private void invisible() {

    }
}
