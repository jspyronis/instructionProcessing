package queue.validate;

import beans.InstructionMessage;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;

/** Unit test class for validating instruction message to be inserted in a queue..
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 13/11/2013
 *         Time: 17:30
 */
public class InstructionQueueMessageValidationTest
{

    private InstructionMessage instructionMessage;

    @Before
    public void setUp()
    {
        instructionMessage = new InstructionMessage(1, 1, 1, 1, 1);
    }

    @Test
    public void testValidateInstructionTypeOutOfBounds()
    {
        instructionMessage = new InstructionMessage(0, 1, 1, 1, 1);
        assertFalse(instructionMessage.isValid());

        instructionMessage = new InstructionMessage(-1, 1, 1, 1, 1);
        assertFalse(instructionMessage.isValid());

        instructionMessage = new InstructionMessage(100, 1, 1, 1, 1);
        assertFalse(instructionMessage.isValid());

        instructionMessage = new InstructionMessage(101, 1, 1, 1, 1);
        assertFalse(instructionMessage.isValid());
    }

    @Test
    public void testValidateProductCodeOutOfBounds()
    {
        instructionMessage = new InstructionMessage(1, 0, 1, 1, 1);
        assertFalse(instructionMessage.isValid());

        instructionMessage = new InstructionMessage(1, -1, 1, 1, 1);
        assertFalse(instructionMessage.isValid());
    }

    @Test
    public void testValidateQuantityOutOfBounds()
    {
        instructionMessage = new InstructionMessage(1, 1, 0, 1, 1);
        assertFalse(instructionMessage.isValid());

        instructionMessage = new InstructionMessage(1, 1, -1, 1, 1);
        assertFalse(instructionMessage.isValid());
    }

    @Test
    public void testValidateUomOutOfBounds()
    {
        instructionMessage = new InstructionMessage(1, 1, 1, -1, 1);
        assertFalse(instructionMessage.isValid());

        instructionMessage = new InstructionMessage(1, 1, 1, 256, 1);
        assertFalse(instructionMessage.isValid());

        instructionMessage = new InstructionMessage(1, 1, 1, 257, 1);
        assertFalse(instructionMessage.isValid());
    }

    @Test
    public void testValidateTimeStampOutOfBounds()
    {
        instructionMessage = new InstructionMessage(1, 1, 1, 1, 0);
        assertFalse(instructionMessage.isValid());

        instructionMessage = new InstructionMessage(1, 1, 1, 1, -1);
        assertFalse(instructionMessage.isValid());
    }

}
