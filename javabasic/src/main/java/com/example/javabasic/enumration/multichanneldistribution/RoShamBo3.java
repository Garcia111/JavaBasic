package com.example.javabasic.enumration.multichanneldistribution;

/**
 * @author：Cheng.
 * @date：Created in 15:03 2019/9/17
 */
public enum RoShamBo3 implements Competitor<RoShamBo3> {

    //使用常量方法实现多路分发

    PAPER{

        @Override
        public Outcome compete(RoShamBo3 it) {
            switch (it){
                default:
                case PAPER: return Outcome.DRAW;
                case SCISSORS: return Outcome.LOSE;
                case ROCK: return Outcome.WIN;
            }
        }
    },

    SCISSORS{
        @Override
        public Outcome compete(RoShamBo3 it) {
            switch (it){
                default:
                case PAPER: return Outcome.WIN;
                case SCISSORS: return Outcome.DRAW;
                case ROCK: return Outcome.LOSE;
            }

        }
    },

    ROCK{
        @Override
        public Outcome compete(RoShamBo3 it) {
            switch (it){
                default:
                case PAPER: return Outcome.LOSE;
                case SCISSORS: return Outcome.WIN;
                case ROCK: return Outcome.DRAW;
            }
        }
    };


    public static void main(String[] args){
        RoShamBo.play(RoShamBo3.class,20);
    }

}
