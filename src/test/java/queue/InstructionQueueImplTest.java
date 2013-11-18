package queue;

import beans.InstructionMessage;
import compare.InstructionMessageByTypeComparator;
import exceptions.InvalidMessageException;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static junit.framework.Assert.*;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 13/11/2013
 *         Time: 16:44
 */

public class InstructionQueueImplTest
{
    private InstructionMessage instructionMessage;
    private InstructionMessage instructionMessage2;
    private InstructionMessage instructionMessage3;

    private InstructionQueue<InstructionMessage> queue;

    @Before
    public void setUp() throws Exception
    {
        instructionMessage = new InstructionMessage(1, 1, 1, 1, 1);
        instructionMessage2 = new InstructionMessage(2, 2, 2, 2, 2);
        instructionMessage3 = new InstructionMessage(3, 3, 3, 3, 3);

        Comparator comparator = new InstructionMessageByTypeComparator();
        queue = new InstructionQueueImpl(10, comparator);
    }

    @Test
    public void testGetNumberOfInstructionMessages() throws InvalidMessageException
    {
        queue.addInstructionMessage(instructionMessage);
        queue.addInstructionMessage(instructionMessage2);
        assertEquals(Integer.valueOf(2) , queue.getNumberOfInstructionMessages());
    }

    @Test
    public void testAddInstructionMessage() throws Exception
    {
        queue.addInstructionMessage(instructionMessage);

        InstructionMessage newInstructionMessage = queue.getFrontInstructionMessage();

        assertSame(newInstructionMessage , instructionMessage);
    }

    @Test
    public void testRemoveInstructionMessage() throws Exception
    {
        queue.addInstructionMessage(instructionMessage);
        queue.addInstructionMessage(instructionMessage2);

        queue.removeInstructionMessage(instructionMessage2);

        assertEquals(Integer.valueOf(1), queue.getNumberOfInstructionMessages());

        InstructionMessage newInstructionMessage = queue.getFrontInstructionMessage();
        assertSame(newInstructionMessage , instructionMessage);
    }

    @Test
    public void testGetFrontInstructionMessage() throws Exception
    {
        queue.addInstructionMessage(instructionMessage3);
        queue.addInstructionMessage(instructionMessage);
        queue.addInstructionMessage(instructionMessage2);

        // The priority should be 1 - > 2  - > 3

        InstructionMessage expectedInstructionMessage = queue.getFrontInstructionMessage();
        assertSame(expectedInstructionMessage, instructionMessage);
    }

    @Test
    public void testTakeFrontInstructionMessage() throws Exception
    {
        queue.addInstructionMessage(instructionMessage3);
        queue.addInstructionMessage(instructionMessage);
        queue.addInstructionMessage(instructionMessage2);

        queue.takeFrontInstructionMessage();
        assertEquals(Integer.valueOf(2), queue.getNumberOfInstructionMessages());
        InstructionMessage expectedInstructionMessage = queue.getFrontInstructionMessage();
        assertSame(expectedInstructionMessage, instructionMessage2);

        queue.takeFrontInstructionMessage();
        assertEquals(Integer.valueOf(1), queue.getNumberOfInstructionMessages());
        expectedInstructionMessage = queue.getFrontInstructionMessage();
        assertSame(expectedInstructionMessage, instructionMessage3);
    }

    @Test
    public void testIsInstructionQueueEmpty() throws Exception
    {
        assertTrue(queue.isInstructionQueueEmpty());
        queue.addInstructionMessage(instructionMessage);
        assertFalse(queue.isInstructionQueueEmpty());
    }
}
