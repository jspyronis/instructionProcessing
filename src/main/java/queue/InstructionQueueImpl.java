package queue;

import beans.InstructionMessage;

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
    public void addInstructionMessage(InstructionMessage instructionMessage)
    {
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
        return super.poll();
    }

    @Override
    public boolean isInstructionQueueEmpty()
    {
        return super.isEmpty();
    }
}
