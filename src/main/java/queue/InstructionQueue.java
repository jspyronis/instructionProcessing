package queue;

import beans.InstructionMessage;
import exceptions.InvalidMessageException;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 12/11/2013
 *         Time: 11:50
 */
public interface InstructionQueue<I>
{
    public Integer getNumberOfInstructionMessages();
    public boolean addInstructionMessage(InstructionMessage message) throws InvalidMessageException;
    public void removeInstructionMessage(InstructionMessage message);
    public InstructionMessage getFrontInstructionMessage();
    public Object takeFrontInstructionMessage() throws InterruptedException;
    public boolean isInstructionQueueEmpty();
}
