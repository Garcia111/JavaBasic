package com.example.javabasic.enumration.enumclassify;

import com.example.javabasic.enumration.Enums;

/**
 * @author：Cheng.
 * @date：Created in 10:57 2019/9/16
 */
public enum SecurityCategory {

    STOCK(Security.Stock.class),
    BOND(Security.Bond.class);
    Security[] values;

    SecurityCategory(Class<? extends Security> kind) {
        values = kind.getEnumConstants();
    }

    //Security接口的作用是将其所包含的enum组合成一个公共类型，这一点是有必要的，因为之后SecurityCategory才能将
    //Security中的enum作为其构造器的参数使用，以起到组织的效果。

    interface Security{
        enum Stock implements Security{ SHORT, LONG, MARGIN}
        enum Bond implements Security{ MUNICIPAL, JUNK}
    }

    public Security randomSelection(){
        return Enums.random(values);
    }


    public static void main(String[] args){
        for (int i = 0; i<10 ; i++){
            SecurityCategory category = Enums.random(SecurityCategory.class);
            System.out.println(category +": "+category.randomSelection());
        }
    }
}


