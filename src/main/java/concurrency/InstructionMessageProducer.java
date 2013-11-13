package concurrency;

import beans.InstructionMessage;
import compare.InstructionMessageByTypeComparator;
import exceptions.InvalidMessageException;
import queue.InstructionQueue;
import queue.InstructionQueueImpl;

import java.util.Comparator;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 13/11/2013
 *         Time: 14:20
 */
public class InstructionMessageProducer implements Runnable
{
    Comparator comparator = new InstructionMessageByTypeComparator();
    InstructionQueue<InstructionMessage> queue = new InstructionQueueImpl(5, comparator);

    public InstructionMessageProducer(InstructionQueue queue){
        this.queue = queue;
    }

    @Override
    public void run()
    {
        //produce 100 instruction messages
        for (int i = 1; i < 100; i++)
        {
            InstructionMessage instructionMessage = new InstructionMessage();
            instructionMessage.setInstructionType(i);
            instructionMessage.setQuantity(i);
            instructionMessage.setUom(i);
            instructionMessage.setProductCode(i);
            instructionMessage.setTimeStamp(i);

            try
            {
                queue.addInstructionMessage(instructionMessage);
            }
            catch (InvalidMessageException e)
            {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            System.out.println("Produced --- >"+ instructionMessage.getInstructionType() + " instruction message");
        }
    }
}
