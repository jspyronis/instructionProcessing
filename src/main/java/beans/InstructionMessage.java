package beans;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 12/11/2013
 *         Time: 11:47
 */
public class InstructionMessage
{
    private Integer instructionType;
    private Integer productCode;
    private Integer quantity;
    private Integer uom;
    private Integer timeStamp;

    public Integer getInstructionType()
    {
        return instructionType;
    }

    public void setInstructionType(Integer instructionType)
    {
        this.instructionType = instructionType;
    }

    public Integer getProductCode()
    {
        return productCode;
    }
    public void setProductCode(Integer productCode)
    {
        this.productCode = productCode;
    }

    public Integer getQuantity()
    {
        return quantity;
    }
    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Integer getUom()
    {
        return uom;
    }
    public void setUom(Integer uom)
    {
        this.uom = uom;
    }

    public Integer getTimeStamp()
    {
        return timeStamp;
    }
    public void setTimeStamp(Integer timeStamp)
    {
        this.timeStamp = timeStamp;
    }
}
