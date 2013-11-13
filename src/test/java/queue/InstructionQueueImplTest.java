package queue;

import beans.InstructionMessage;
import compare.InstructionMessageByTypeComparator;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;

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
        instructionMessage = new InstructionMessage();
        instructionMessage.setInstructionType(1);
        instructionMessage.setProductCode(1);
        instructionMessage.setQuantity(1);
        instructionMessage.setUom(1);
        instructionMessage.setTimeStamp(1);

        instructionMessage2 = new InstructionMessage();
        instructionMessage2.setInstructionType(2);
        instructionMessage2.setProductCode(2);
        instructionMessage2.setQuantity(2);
        instructionMessage2.setUom(2);
        instructionMessage2.setTimeStamp(2);

        instructionMessage3 = new InstructionMessage();
        instructionMessage3.setInstructionType(3);
        instructionMessage3.setProductCode(3);
        instructionMessage3.setQuantity(3);
        instructionMessage3.setUom(3);
        instructionMessage3.setTimeStamp(3);


        Comparator comparator = new InstructionMessageByTypeComparator();
        queue = new InstructionQueueImpl(10, comparator);
    }

    @Test
    public void testGetNumberOfInstructionMessages()
    {
        queue.addInstructionMessage(instructionMessage);
        queue.addInstructionMessage(instructionMessage2);
        assertEquals(2, queue.getNumberOfInstructionMessages());

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

        assertEquals(1, queue.getNumberOfInstructionMessages());

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
        assertEquals(2, queue.getNumberOfInstructionMessages());
        InstructionMessage expectedInstructionMessage = queue.getFrontInstructionMessage();
        assertSame(expectedInstructionMessage, instructionMessage2);

        queue.takeFrontInstructionMessage();
        assertEquals(1, queue.getNumberOfInstructionMessages());
        expectedInstructionMessage = queue.getFrontInstructionMessage();
        assertSame(expectedInstructionMessage, instructionMessage3);
    }

    @Test
    public void testIsInstructionQueueEmpty() throws Exception
    {
        assertEquals(0, queue.getNumberOfInstructionMessages());
        queue.addInstructionMessage(instructionMessage);
    }
}
