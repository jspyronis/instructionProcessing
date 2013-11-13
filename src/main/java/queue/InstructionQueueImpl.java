package queue;

import beans.InstructionMessage;
import exceptions.InvalidMessageException;
import queue.validate.InstructionQueueMessageValidation;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 12/11/2013
 *         Time: 12:01
 */
public class InstructionQueueImpl extends PriorityBlockingQueue implements InstructionQueue<InstructionMessage>
{

    public InstructionQueueImpl(int i, Comparator comparator)
    {
        super(i, comparator);
    }

    @Override
    public int getNumberOfInstructionMessages()
    {
        return super.size();
    }

    @Override
    public void addInstructionMessage(InstructionMessage instructionMessage) throws InvalidMessageException
    {
        // validation of instruction messages here
        InstructionQueueMessageValidation instructionQueueMessageValidation = new InstructionQueueMessageValidation(instructionMessage);

        instructionQueueMessageValidation.validateInstructionType();
        instructionQueueMessageValidation.validateProductCode();
        instructionQueueMessageValidation.validateQuantity();
        instructionQueueMessageValidation.validateUom();
        instructionQueueMessageValidation.validateTimeStamp();

        super.add(instructionMessage);
    }

    @Override
    public void removeInstructionMessage(InstructionMessage instructionMessage)
    {
        super.remove(instructionMessage);
    }

    @Override
    public InstructionMessage getFrontInstructionMessage()
    {
        return (InstructionMessage) super.peek();
    }

    @Override
    public Object takeFrontInstructionMessage() throws InterruptedException
    {
        return super.take();
    }

    @Override
    public boolean isInstructionQueueEmpty()
    {
        return super.isEmpty();
    }
}
