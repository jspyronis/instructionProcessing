package concurrency;

import beans.InstructionMessage;
import compare.InstructionMessageByTypeComparator;
import queue.InstructionQueue;
import queue.InstructionQueueImpl;

import java.util.Comparator;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 13/11/2013
 *         Time: 14:29
 */
public class InstructionMessageProducerConsumerService
{
    Comparator comparator = new InstructionMessageByTypeComparator();

    //Creating queue size 10
    InstructionQueue<InstructionMessage> queue = new InstructionQueueImpl(10, comparator);

    InstructionMessageProducer producer = new InstructionMessageProducer(queue);
    InstructionMessageConsumer consumer = new InstructionMessageConsumer(queue);
    InstructionMessageConsumer consumer2 = new InstructionMessageConsumer(queue);
    InstructionMessageConsumer consumer3 = new InstructionMessageConsumer(queue);

    public void runProducerConsumerThreads()
    {
        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer2).start();
        new Thread(consumer3).start();

        System.out.println("Producer and Consumer has been started");
    }
}
