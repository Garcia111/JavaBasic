Java内置观察者模式


主题Observable:
1.addObserver(Observer)
2.deleteObserver(Observer)
3.notifyObservers()
4.notifyObservers(Object)
5.deleteObservers()
6.setChanged()
7.clearChanged()
8.hasChanged()
9.countObservers()


Observer:
1. void update(Observable o, Object arg)

 Observavle----观察者绑定的主题
 Object--------notifyObservers()中主题传递给观察者的Object数据