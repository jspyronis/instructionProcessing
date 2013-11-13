package compare;

import beans.InstructionMessage;

import java.util.Comparator;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 12/11/2013
 *         Time: 12:53
 */
public class InstructionMessageByTypeComparator implements Comparator<InstructionMessage>
{
    @Override
    public int compare(InstructionMessage instructionMessage1, InstructionMessage instructionMessage2)
    {
        return instructionMessage1.getInstructionType().compareTo(instructionMessage2.getInstructionType());
    }

}
