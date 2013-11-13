package validate;

import beans.InstructionMessage;
import exceptions.InvalidMessageException;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 12/11/2013
 *         Time: 12:15
 */
public class InstructionMessageValidator
{

    public void validateInstructionMessage(InstructionMessage instructionMessage) throws InvalidMessageException
    {
        int instructionType = instructionMessage.getInstructionType();
        int uom = instructionMessage.getUom();

        if (instructionType <= 0 || instructionType >= 100)
        {
            throw new InvalidMessageException("Invalid instruction type - value must be more than 0 and less than 99");
        }
        if (instructionMessage.getProductCode() <= 0)
        {
            throw new InvalidMessageException("Invalid product code - value must be more than 0");
        }
        if (instructionMessage.getQuantity() <= 0)
        {
            throw new InvalidMessageException("Invalid quantity - value must be more than 0");
        }
        if (uom < 0 && uom >= 256)
        {
            throw new InvalidMessageException("Invalid UOM - value must be more or equal than 0 and less than 256");
        }
        if (instructionMessage.getTimeStamp() <= 0)
        {
            throw new InvalidMessageException("Invalid timestamp - value must be more than 0");
        }
    }
}
