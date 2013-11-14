package queue.validate;

import beans.InstructionMessage;
import compare.InstructionMessageByTypeComparator;
import exceptions.InvalidMessageException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import queue.InstructionQueue;
import queue.InstructionQueueImpl;

import java.util.Comparator;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 13/11/2013
 *         Time: 17:30
 */
public class InstructionQueueMessageValidationTest
{

    private InstructionMessage instructionMessage;
    private static final Comparator<InstructionMessage> comparator = new InstructionMessageByTypeComparator();
    private InstructionQueue queue = new InstructionQueueImpl(5, comparator);

    @Before
    public void setUp()
    {
        // All valid properties for instruction message...
        instructionMessage = new InstructionMessage();
        instructionMessage.setInstructionType(Matchers.anyInt());
        instructionMessage.setProductCode(Matchers.anyInt());
        instructionMessage.setQuantity(Matchers.anyInt());
        instructionMessage.setUom(Matchers.anyInt());
        instructionMessage.setTimeStamp(Matchers.anyInt());
    }

    @Test(expected = InvalidMessageException.class)
    public void testValidateInstructionType1() throws Exception
    {
        instructionMessage.setInstructionType(-1);
        queue.addInstructionMessage(instructionMessage);
    }


    @Test(expected = InvalidMessageException.class)
    public void testValidateInstructionType2() throws Exception
    {
        instructionMessage.setInstructionType(103);
        queue.addInstructionMessage(instructionMessage);
    }


    @Test(expected = InvalidMessageException.class)
    public void testValidateInstructionType3() throws Exception
    {
        instructionMessage.setInstructionType(-1);
        queue.addInstructionMessage(instructionMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void testValidateProductCode() throws Exception
    {
        instructionMessage.setProductCode(-2);
        queue.addInstructionMessage(instructionMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void testValidateQuantity() throws Exception
    {
        instructionMessage.setQuantity(-1);
        queue.addInstructionMessage(instructionMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void testValidateUom() throws Exception
    {
        instructionMessage.setUom(280);
        queue.addInstructionMessage(instructionMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void testValidateTimeStamp() throws Exception
    {
        instructionMessage.setTimeStamp(-2);
        queue.addInstructionMessage(instructionMessage);

    }
}
