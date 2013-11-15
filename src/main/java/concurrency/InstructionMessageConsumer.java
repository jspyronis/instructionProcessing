package concurrency;

import beans.InstructionMessage;
import queue.InstructionQueue;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 13/11/2013
 *         Time: 14:28
 */

public class InstructionMessageConsumer implements Runnable{

    private InstructionQueue<InstructionMessage> queue;
    private boolean stop;

    public InstructionMessageConsumer(InstructionQueue<InstructionMessage> queue)
    {
        this.queue = queue;
        stop = false;
    }

    public void run()
    {
        while(!stop)
        {
            //Thread.sleep(200);
            try {
                System.out.println("Consuming...");
                queue.takeFrontInstructionMessage();
            }
            catch (InterruptedException e)
            {
                System.out.print("Interrupted exception....");
            }

        }
    }

    public void abort()
    {
        System.out.println("Calling aborted...");
        stop = true;

    }







}