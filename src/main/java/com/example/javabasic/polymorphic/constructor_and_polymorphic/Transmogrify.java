package com.example.javabasic.polymorphic.constructor_and_polymorphic;

/**
 * @author：Cheng.
 * @date：Created in 14:13 2020/1/13
 */
class Actor{
    public void act(){}
}

class HappyActor extends Actor{
    @Override
    public void act(){
        System.out.println(" HappyActor " );
    }
}

class SadActor extends Actor{
    @Override
    public void act(){
        System.out.println(" SadActor " );
    }
}

class Stage {
    private Actor actor = new HappyActor();

    //组合允许动态地选择类型
    public void change(){
        actor = new SadActor();
    }

    public void performPlay(){
        actor.act();
    }
}



public class Transmogrify {

    public static void main(String[] args) {
        Stage stage = new Stage();
        stage.performPlay();;
        stage.change();
        stage.performPlay();
    }
}
