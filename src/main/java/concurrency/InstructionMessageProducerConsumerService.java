package concurrency;

import beans.InstructionMessage;
import compare.InstructionMessageByTypeComparator;
import queue.InstructionQueue;
import queue.InstructionQueueImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 13/11/2013
 *         Time: 14:29
 */
public class InstructionMessageProducerConsumerService
{
    Comparator comparator = new InstructionMessageByTypeComparator();

    //Creating queue size 10
    InstructionQueue<InstructionMessage> queue = new InstructionQueueImpl(20, comparator);

    InstructionMessage instructionMessage = new InstructionMessage();

    private List<InstructionMessage> generateInstructionMessages()
    {
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
        return instructionMessageList;
    }


    public void runProducerConsumerThreads()
    {

        List<InstructionMessage> instructionMessageList = generateInstructionMessages();

        InstructionMessageProducer producer = new InstructionMessageProducer(queue, instructionMessageList);
        InstructionMessageProducer producer2 = new InstructionMessageProducer(queue, instructionMessageList);

        InstructionMessageConsumer consumer = new InstructionMessageConsumer(queue);
        InstructionMessageConsumer consumer2 = new InstructionMessageConsumer(queue);
        InstructionMessageConsumer consumer3 = new InstructionMessageConsumer(queue);

        new Thread(producer).start();
        //new Thread(producer2).start();
        new Thread(consumer).start();
        new Thread(consumer2).start();
        new Thread(consumer3).start();

        System.out.println("Producer and Consumer has been started");
    }
}
