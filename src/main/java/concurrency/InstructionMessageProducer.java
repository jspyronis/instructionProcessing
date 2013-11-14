package concurrency;

import beans.InstructionMessage;
import compare.InstructionMessageByTypeComparator;
import exceptions.InvalidMessageException;
import queue.InstructionQueue;
import queue.InstructionQueueImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 13/11/2013
 *         Time: 14:20
 */
public class InstructionMessageProducer implements Runnable
{
    InstructionQueue<InstructionMessage> queue = new InstructionQueueImpl(10, new InstructionMessageByTypeComparator());
    List<InstructionMessage> instructionMessageList = new ArrayList<InstructionMessage>();
    private static  int counter = 0;
    private final String name;

    public InstructionMessageProducer(InstructionQueue queue, List<InstructionMessage> instructionMessageList){
        this.queue = queue;
        this.instructionMessageList = instructionMessageList;
        name = "Producer " + ++counter;
    }

    @Override
    public void run()
    {
        for (InstructionMessage instructionMessage : instructionMessageList)
        {
            try
            {
                queue.addInstructionMessage(instructionMessage);
                System.out.println(name + " size of queue is  : "  + queue.getNumberOfInstructionMessages());
                Thread.sleep(200);
            }
            catch (InvalidMessageException e)
            {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            System.out.println("Produced --- >"+ instructionMessage.getInstructionType() + " instruction message");
        }
    }
}
