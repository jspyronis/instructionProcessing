import beans.InstructionMessage;
import compare.InstructionMessageByTypeComparator;
import concurrency.InstructionMessageConsumer;
import concurrency.InstructionMessageProducer;
import exceptions.InvalidMessageException;
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
    InstructionMessageProducer producer2;
    InstructionMessageConsumer consumer;
    InstructionMessageConsumer consumer2;
    InstructionMessageConsumer consumer3;

    @Before
    public void setUp()
    {
        for (int i = 1; i < 50 ;  i++)
        {
            InstructionMessage instructionMessage = new InstructionMessage();
            instructionMessage.setInstructionType(i);
            instructionMessage.setQuantity(i);
            instructionMessage.setTimeStamp(i);
            instructionMessage.setUom(i);
            instructionMessage.setProductCode(i);

            instructionMessageList.add(instructionMessage);
        }

        System.out.print("Size.... " + instructionMessageList.size()) ;

        producer = new InstructionMessageProducer(queue, instructionMessageList);
        producer2 = new InstructionMessageProducer(queue, instructionMessageList);

        consumer = new InstructionMessageConsumer(queue);
        consumer2 =new InstructionMessageConsumer(queue);
        consumer3 =new InstructionMessageConsumer(queue);
    }

    @Test
    public void test() throws InvalidMessageException, InterruptedException
    {
        //InstructionQueueMessageValidation validation = new InstructionQueueMessageValidation(instructionMessage);

        producer.run();
        consumer.run();
        consumer2.run();
        consumer3.run();
        producer2.run();

//        new Thread(producer).start();
//        new Thread(consumer).start();
//        new Thread(consumer2).start();
//        new Thread(producer2).start();
//        new Thread(consumer3).start();

//        queue.addInstructionMessage(instructionMessage);
//
//        queue.takeFrontInstructionMessage();

    }




}
