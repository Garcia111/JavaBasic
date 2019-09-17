package com.example.javabasic.enumration.multichanneldistribution;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author：Cheng.
 * @date：Created in 9:08 2019/9/17
 */
public enum State {
    液体,气体,固体;
    static EnumMap<State, Map<State, Transition>> enumMap;
    enum Transition{
        凝固(液体,固体),
        汽化(液体,气体),
        凝华(气体,固体),
        液化(气体,液体),
        融化(固体,液体),
        升华(固体,气体);
        private  Transition(State src,State dst){
            this.src =src;
            this.dst = dst;
        }
        private State src;
        private State dst;

        public State getSrc() {
            return src;
        }
        public void setSrc(State src) {
            this.src = src;
        }
        public State getDst() {
            return dst;
        }
        public void setDst(State dst) {
            this.dst = dst;
        }

    }

    static {
        enumMap =
                new EnumMap<State, Map<State,Transition>>(State.class);
        for (State state : State.values()) {
            enumMap.put(state, new EnumMap<State, State.Transition>(State.class));
        }
        for (Transition  transaction : Transition.values()) {
            enumMap.get(transaction.getSrc()).put(transaction.getDst(), transaction);
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println(State.enumMap.get(固体).get(液体));
        System.out.println(State.enumMap.get(液体).get(固体));
        System.out.println(State.enumMap.get(气体).get(固体));
        System.out.println(State.enumMap.get(固体).get(气体));
    }

}

