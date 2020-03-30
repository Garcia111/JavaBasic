package xiangxue.concurrent.concurrent_in_action.chap5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
public class Preloader {

    private final FutureTask<ProductionInfo> future =
            new FutureTask<ProductionInfo>(new Callable<ProductionInfo>(){

                @Override
                public ProductionInfo call() throws DataLoadException {
                    //从数据库加载信息
                    return loadProductInfo();
                }
            });


    private final Thread thread = new Thread(future);

    //在构造函数或静态初始化方法中启动线程并不明智，使用start方法启动线程
    public void start(){
        thread.start();
    }

    /**
     * 当程序经过一段时间之后需要ProductInfo时，它可以调用get(),如果已经加载就绪的话，就会返回这些数据，
     * 否则会等待结束再返回
     *
     * Callable记录的这些任务，无论抛出什么Error 或者checked exception或者 unchecked Exception
     * 都会被封装为一个ExecutionExceptionn, 并被Future.get重新抛出。
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public ProductionInfo get() throws InterruptedException, DataLoadException {
        try{
            return future.get();
        }catch (ExecutionException e){
            Throwable cause = e.getCause();
            if(cause instanceof DataLoadException){
                throw (DataLoadException) cause;
            }else{
                throw launderThrowable(cause);
            }
        }
    }


    public ProductionInfo loadProductInfo() throws DataLoadException{
        return new ProductionInfo();
    }


    /**
     * 如果Throwable是Error,那么将它抛出，如果是RuntimeException，那么返回
     * 否则抛出IllegalStateException
     * @param t
     * @return
     */
    public static RuntimeException launderThrowable(Throwable t){
        if(t instanceof  RuntimeException){
            return (RuntimeException) t;
        }else if(t instanceof  Error){
            throw (Error) t;
        }else{
            throw new IllegalStateException("Not unchecked",t);
        }
    }




}
