import beans.InstructionMessage;
import compare.InstructionMessageByTypeComparator;
import concurrency.InstructionMessageConsumer;
import concurrency.InstructionMessageProducer;
import exceptions.InvalidMessageException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import queue.InstructionQueue;
import queue.InstructionQueueImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 14/11/2013
 *         Time: 10:57
 */
public class IntegrationTest
{
    InstructionMessageByTypeComparator comparator = new InstructionMessageByTypeComparator();

    List<InstructionMessage> instructionMessageList = new ArrayList<InstructionMessage>();

    InstructionQueue queue = new InstructionQueueImpl(10, comparator);

    InstructionMessageProducer producer;
    InstructionMessageConsumer consumer;

    @Before
    public void setUp()
    {
        for (int i = 1; i < 20 ;  i++)
        {
            InstructionMessage instructionMessage = new InstructionMessage();
            instructionMessage.setInstructionType(i);
            instructionMessage.setQuantity(i);
            instructionMessage.setTimeStamp(i);
            instructionMessage.setUom(i);
            instructionMessage.setProductCode(i);

            instructionMessageList.add(instructionMessage);
        }

        producer = new InstructionMessageProducer(queue, instructionMessageList);
        consumer = new InstructionMessageConsumer(queue);
    }

    @Test
    public void test()
    {
        Runnable rP = new Thread(producer);
        rP.run();

        Runnable rC = new Thread(consumer);
        rC.run();

        consumer.abort();
        System.out.println("Aborting..");
    }


    //consumerShouldReadAllTheAvailableElements
    /*
    1) Know how many elements (N) you are going to insert
    2) Insert N elements into queue
    3) Start reading elements from queue
    4) Stop reading elements once you read N elements
    5) Verify queue is empty
     */

//    Verify the order of the retrieved elements (This is other test)
//    Verify retrieved elements are the inserted (This is other test)


    @After
    public void tearDown()
    {
        System.out.println("The end..");
    }




}
