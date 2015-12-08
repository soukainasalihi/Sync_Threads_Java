/*
 *  program that launches 1000 threads. 
 *  Each thread adds 1 to a variable sum that
 *  initially is zero. I defined an Integer wrapper 
 *  object to hold sum.  
 */

 
  
 import java.util.concurrent.*; 
 import java.util.concurrent.locks.*; 

/**
 *
 * @author Soukaina
 */
public class SyncThreads 
{
   private static int Sum = new Integer(0);
   
     public static void main(String[] args) { 
          
         ExecutorService executor = Executors.newCachedThreadPool();         
         for (int j = 1; j <= 1000; j++){ 
             executor.execute(new AddOneToSum()); 
       }       
         executor.shutdown(); 
          
         while(!executor.isTerminated()){} 
          
         System.out.println("Threads sum is " + Sum);              
     }
   
private static class AddOneToSum implements Runnable{ 
          
         private static Lock lock = new ReentrantLock(); 
          
        @Override 
        public void run(){ 
             lock.lock(); 
             try{ 
             int newSum = Sum+1; 
             Thread.sleep(4); 
             Sum = newSum; 
            }             
             catch(InterruptedException ex){}             
             finally{ 
                lock.unlock();             
             } 
         } 
     }     
 } 
