package com.mythread;
/**
 * 线程的几个状态  创建>就绪>执行>阻塞>死亡
 * 创建后>>>就绪状态  等待cpu调度  调度后才是执行
 * 阻塞结束>>>就绪状态  等待cpu调度  调度后才是执行
 *
 *
 * */
public class ThreadTest {

    public static void main(String[] args) {
        /**  线程中断 interrupt    和 判断是否是中断线程isInterrupted */


        /**  yield   线程礼让   礼让不一定成功*/

        /**  join 强制执行  插队  执行了join之后，必须等他执行完，后面的代码块才能执行。 */

        /**  priority 优先级   根据优先级的权重 优先级高 先执行的几率大 但是不是百分百 默认5  最小1  最大10*/

        /**  state 获取线程状态*/

        /**  daemon 守护线程*/
    }

}

//class MyRunnableInterrupt implements Runnable{
////    @Override
////    public void run() {
////        while (){
////            //获取当前是否中断
////            boolean interrupted = Thread.currentThread().isInterrupted();
////
////        }
////    }
//
//}