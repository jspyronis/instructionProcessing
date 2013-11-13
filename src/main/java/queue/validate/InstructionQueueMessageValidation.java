package queue.validate;

import beans.InstructionMessage;
import exceptions.InvalidMessageException;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 13/11/2013
 *         Time: 17:20
 */
public class InstructionQueueMessageValidation
{
    private InstructionMessage instructionMessage;
    public InstructionQueueMessageValidation(InstructionMessage instructionMessage)
    {
        this.instructionMessage = instructionMessage;
    }

    public void validateInstructionType() throws InvalidMessageException
    {
        int instructionType = instructionMessage.getInstructionType();
        if (instructionType <= 0 || instructionType >= 100)
        {
            throw new InvalidMessageException("Invalid instruction type - value must be more than 0 and less than 99");
        }
    }

    public void validateProductCode() throws InvalidMessageException
    {
        if (instructionMessage.getProductCode() <= 0)
        {
            throw new InvalidMessageException("Invalid product code - value must be more than 0");
        }
    }

    public void validateQuantity() throws InvalidMessageException
    {
        if (instructionMessage.getQuantity() <= 0)
        {
            throw new InvalidMessageException("Invalid quantity - value must be more than 0");
        }
    }

    public void validateUom() throws InvalidMessageException
    {
        int uom = instructionMessage.getUom();
        if (uom < 0 && uom >= 256)
        {
            throw new InvalidMessageException("Invalid UOM - value must be more or equal than 0 and less than 256");
        }
    }

    public void validateTimeStamp() throws InvalidMessageException
    {
        if (instructionMessage.getTimeStamp() <= 0)
        {
            throw new InvalidMessageException("Invalid timestamp - value must be more than 0");
        }
    }

}
