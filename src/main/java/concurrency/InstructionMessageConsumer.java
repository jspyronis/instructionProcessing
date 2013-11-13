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
    private final String name;
    private static  int counter = 0;

    public InstructionMessageConsumer(InstructionQueue<InstructionMessage> queue){
        this.queue = queue;
        name = "Consumer " + ++counter;
    }

    @Override
    public void run() {
        try{
            while(true)
            {
                InstructionMessage instructionMessage = (InstructionMessage) queue.takeFrontInstructionMessage();
                System.out.println(name + " ........ " + instructionMessage.getInstructionType() + " instruction message, Queue size is : " + queue.getNumberOfInstructionMessages());
                Thread.sleep(200);
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}