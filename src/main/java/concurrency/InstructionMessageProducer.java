package concurrency;

import beans.InstructionMessage;
import compare.InstructionMessageByTypeComparator;
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
        for (int i = 0; i < 100; i++)
        {
            InstructionMessage instructionMessage = new InstructionMessage();
            instructionMessage.setInstructionType(i);
            instructionMessage.setQuantity(i);
            instructionMessage.setUom(i);
            instructionMessage.setProductCode(i);
            instructionMessage.setTimeStamp(i);

            queue.addInstructionMessage(instructionMessage);
            System.out.println("Produced --- >"+ instructionMessage.getInstructionType() + " instruction message");
        }
    }
}
