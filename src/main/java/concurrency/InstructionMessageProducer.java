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

    public InstructionMessageProducer(InstructionQueue queue, List<InstructionMessage> instructionMessageList)
    {
        this.queue = queue;
        this.instructionMessageList = instructionMessageList;
    }

    public void run()
    {
        for (InstructionMessage instructionMessage : instructionMessageList)
        {
            System.out.println("Producing..");
            try {
                queue.addInstructionMessage(instructionMessage);
            } catch (InvalidMessageException e) {
                e.printStackTrace();
            }
        }
    }



}
