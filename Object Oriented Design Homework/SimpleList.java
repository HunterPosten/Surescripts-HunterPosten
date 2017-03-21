import java.util.*;
public class SimpleList <T>
{

/*
	Hunter Posten
	November 22, 2016

	This class models a sequential list of generic objects

	Class Varibles:
		list
			a generic ArrayList containing objects placed into it
			by the put() and put(valueArray T[]) methods

		putPosition
			a Position constant that indicates the location in list
			in which an object is placed.

		takePosition
			a Position constant that indicates the location in list
			in which an object is taken.

	Constructor:
		SimpleList(Position putPosition, Position takePosition)
			forms a SimpleList object with the inputted put and take positions.

	Class Methods:
		public void clear()
			this method clears the simple list object of all objects resulting
			in an empty list.

		public Position getPutPosition()
			An acessor method that returns the putPosition of the SimpleList.

		public Position getTakePosition()
			An acessor method that returns the takePosition of the simepleList object

		public boolean isEmpty()
			this method returns a boolean value in accordance to if the Simple
			list object is empty ie. if the SimpleList is empty then isEmpty() returns true.

		public void put(T value)
			this method places a value object into the SimpleList at the put
			Position.

		public void put(T[] valueArray)
			this method places an array of values into the SimpleList at the
			put position.

		public int size()
			this method returns an integer value representing how many objects are
			in the SimpleList.

		public T take()
			this method removes the object at the takePosition from SimpleList and
			returns that object.

		public ArrayList<T> takeAll()
			this method removes all elements in the SimpleList and returns all those
			elements in an ArrayList<T>.
*/

    private ArrayList<T> list;
    private Position putPosition;
    private Position takePosition;

    public SimpleList(Position putPosition, Position takePosition)throws IllegalArgumentException
    {
		System.out.println("");
		System.out.println("SIMPLE LIST CONSTRUCTOR");
		System.out.println("putPosition = " + putPosition.toString());
		System.out.println("takePosition = " + takePosition.toString());
		System.out.println("");
		if(putPosition != Position.FIRST && putPosition != Position.LAST && putPosition != Position.RANDOM)
		{
			throw new IllegalArgumentException("invalid putPosition value. putPosition must be either Position.FIRST, Position.LAST, or Position.RANDOM. Inputed value was " + putPosition);
		}//putposition check

		if(takePosition != Position.FIRST && takePosition != Position.LAST && takePosition != Position.RANDOM)
		{
			throw new IllegalArgumentException("invalid takePosition value. takePosition must be either Position.FIRST, Position.LAST, or Position.RANDOM. Inputed value was " + takePosition);
		}//putposition check

        this.putPosition = putPosition;
        this.takePosition = takePosition;
        this.list = new ArrayList<T>();
    }//constructor

    public void clear()
    {
		this.list.clear();
	}//clear

	public Position getPutPosition()
	{
		return this.putPosition;
	}//getputposition

	public Position getTakePosition()
	{
		return this.takePosition;
	}

	public boolean isEmpty()
	{
		return(this.list.size() == 0);
	}//isEmpty()

	public void put(T value)
	{
		this.list.add(this.getPutPosition().get(this.list.size()+1), value);
	}//put

	public void put(T[] valueArray)throws IllegalArgumentException
	{

		for(int i  = 0; i < valueArray.length ; i++)
		{
			this.put(valueArray[i]);
		}//for loop
	}//put array

	public int size()
	{
		return this.list.size();
	}//size

	public T take()
	{
		return this.list.remove(takePosition.get(this.list.size()));
	}//take

	public ArrayList<T> takeAll()
	{
		ArrayList<T> ret;
		ret = new ArrayList<T>();
		while(!this.isEmpty())
		{
			ret.add(this.take());
		}
		return ret;
	}//ArrayList

}//class
