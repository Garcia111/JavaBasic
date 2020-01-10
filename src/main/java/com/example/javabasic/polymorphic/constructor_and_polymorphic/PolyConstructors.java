package com.example.javabasic.polymorphic.constructor_and_polymorphic;

/**
 * @author：Cheng.
 * @date：Created in 16:19 2020/1/10
 */
class Glyph{
    void draw(){
        System.out.println(" Glyph.draw() " );
    }

    Glyph(){
        System.out.println(" Glyph() before draw() " );
        draw();
        System.out.println(" Glyph() after draw() " );
    }
}

class RoundGlyph extends Glyph{
    private int radius = 1;
    RoundGlyph(int r){
        radius = r;
        System.out.println("RoundGlyph.RoundGlyph(),radius = " + radius);
    }

    //此对象还没有被完全构造完成，就要调用此对象的draw()了
    @Override
    void draw(){
        System.out.println("RoundGlyph.draw(),radius = " + radius);
    }
}



public class PolyConstructors {
    public static void main(String[] args) {
        new RoundGlyph(5);
    }
}
