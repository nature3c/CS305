/**
 * @author npandya March 2016 The Class Node.
 */
class Node

{

    //the data
    protected int data;

    //the link
    protected Node link;


    /**
     * Instantiates a new node.
     */
    public Node()

    {

        link = null;

        data = 0;

    }

    /**
     * Instantiates a new node.
     * 
     * @param d - the data
     * 
     * @param n - the link to the next
     */
    public Node(int d, Node n)

    {

        data = d;
        link = n;

    }

    //function to set link to next Node

    /**
     * Sets the link.
     * 
     * @param n - the new link
     */
    public void setLink(Node n)

    {

        link = n;

    }

    //function to set data to current Node

    /**
     * Sets the data.
     * 
     * @param d
     *            the new data
     */
    public void setData(int d)

    {

        data = d;

    }

    //function to get link to next node

    /**
     * Gets the link.
     * 
     * @return the link
     */
    public Node getLink()

    {

        return link;

    }

    //function to get data from current Node

    /**
     * Gets the data.
     * 
     * @return the data
     */
    public int getData()

    {

        return data;

    }

}
