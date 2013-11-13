import concurrency.InstructionMessageProducerConsumerService;
import exceptions.InvalidMessageException;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 12/11/2013
 *         Time: 12:02
 */
public class Main
{
    public static void main(String args[]) throws InvalidMessageException
    {

        InstructionMessageProducerConsumerService instructionMessageProducerConsumerService = new InstructionMessageProducerConsumerService();
        instructionMessageProducerConsumerService.runProducerConsumerThreads();

    }
}
