package xiangxue.concurrent.concurrent_in_action.chap4;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 委托线程安全到多个底层的状态变量
 * @author：Cheng.
 * @date：Created in 18:47 2020/2/2
 */
public class VisualComponent {

    //鼠标事件的监听器集与键盘事件的监听器集之间没有关系，它们彼此独立
    //VisualComponent可以将它的线程安全委托到这两个线程安全的清单上
    //CopyOnWriteArrayList
    private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<KeyListener>();

    private final List<MouseListener>  mouseListeners = new CopyOnWriteArrayList<MouseListener>();


    public void addKeyListener(KeyListener listener){
        keyListeners.add(listener);
    }


    public void addMouseListener(MouseListener listener){
        mouseListeners.add(listener);
    }

    public void removeKeyListener(KeyListener listener){
        keyListeners.remove(listener);
    }


    public void removeMouseListener(MouseListener listener){
        mouseListeners.remove(listener);
    }





}
