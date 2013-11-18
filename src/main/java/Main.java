import beans.InstructionMessage;
import compare.InstructionMessageByTypeComparator;
import exceptions.InvalidMessageException;
import queue.InstructionQueue;
import queue.InstructionQueueImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 12/11/2013
 *         Time: 12:02
 */
public class Main
{
    public static void main(String args[]) throws InvalidMessageException
    {

        Comparator comparator = new InstructionMessageByTypeComparator();
        //Creating queue size 10
        InstructionQueue<InstructionMessage> queue = new InstructionQueueImpl(20, comparator);
        InstructionMessage instructionMessage = new InstructionMessage();

        List<InstructionMessage> instructionMessageList = new ArrayList<InstructionMessage>();
        for (int i = 1 ; i < 100 ; i++)
        {
            instructionMessage = new InstructionMessage();
            instructionMessage.setInstructionType(i);
            instructionMessage.setQuantity(i);
            instructionMessage.setTimeStamp(i);
            instructionMessage.setUom(i);
            instructionMessage.setProductCode(i);
            instructionMessageList.add(instructionMessage);
        }

        InstructionMessageProducer producer = new InstructionMessageProducer(queue, instructionMessageList);
        InstructionMessageConsumer consumer = new InstructionMessageConsumer(queue);

        System.out.println("Producer and Consumer has been started");

        new Thread(producer).start();
        new Thread(consumer).start();

        consumer.abort();
    }
}

class InstructionMessageProducer implements Runnable
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

class InstructionMessageConsumer implements Runnable{

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







