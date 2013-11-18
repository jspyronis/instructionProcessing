package queue;

import beans.InstructionMessage;
import compare.InstructionMessageByTypeComparator;
import exceptions.InvalidMessageException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jspyronis on 18/11/2013.
 */
public class InstructionQueuePriorityMessagesTest
{

    InstructionQueue<InstructionMessage> queue = new InstructionQueueImpl(10, new InstructionMessageByTypeComparator());
    InstructionMessage instructionMessage1;
    InstructionMessage instructionMessage2;
    InstructionMessage instructionMessage3;
    InstructionMessage instructionMessage4;
    InstructionMessage instructionMessage5;

    @Before
    public void setUp()
    {
        instructionMessage1 = new InstructionMessage(12, 4, 5, 12, 30);
        instructionMessage2 = new InstructionMessage(4, 3, 29, 71, 21);
        instructionMessage3 = new InstructionMessage(8, 32, 72, 37, 10);
        instructionMessage4 = new InstructionMessage(13, 31, 52, 16, 1);
        instructionMessage5 = new InstructionMessage(2, 43, 2, 25, 1);
    }

    @Test
    public void testGetCorrectPriority()
    {

        try {
            queue.addInstructionMessage(instructionMessage1);
            queue.addInstructionMessage(instructionMessage2);
            queue.addInstructionMessage(instructionMessage3);
            queue.addInstructionMessage(instructionMessage4);
            queue.addInstructionMessage(instructionMessage5);


            assertEquals((Integer) 2, queue.getFrontInstructionMessage().getInstructionType());
            queue.takeFrontInstructionMessage();
            assertEquals((Integer) 4, queue.getFrontInstructionMessage().getInstructionType());
            queue.takeFrontInstructionMessage();
            assertEquals((Integer) 8, queue.getFrontInstructionMessage().getInstructionType());
            queue.takeFrontInstructionMessage();
            assertEquals((Integer) 12, queue.getFrontInstructionMessage().getInstructionType());
            queue.takeFrontInstructionMessage();
            assertEquals((Integer) 13, queue.getFrontInstructionMessage().getInstructionType());
            queue.takeFrontInstructionMessage();

        }
        catch (InvalidMessageException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }


    }
}
