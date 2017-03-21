
public enum Position
{
/*
	Hunter Posten
	November 22, 2016

	These enum file contains enum position constants that are used in the SimpleList<T> class

	Class Enum Constants:
		FIRST
			a constant that represents the first position.

		LAST
			a constant that represents the last position.

		RANDOM
			a constant that represents a random position.

	Methods:
		public int get(int size)
			an abstract method that is overwritten by each of the constants. returns an integer value in relation
			to the particular constant ie. FIRST.get(int size) returns 1, LAST.get(int size) returns size and
			RANDOM.get(int size) returns an random integer between one and size.

		private void checkParameter(String enumConstant, int size)
			checks that int size is an appropriate value. if the size is less than one this method will throw an
			IllegalArgumentException.

*/
	 FIRST
	 {
		public int get(int size)
		{

			checkParameter(this.name(), size);
			return 0;
		}
	 },

	 LAST
	 {
		 public int get(int size)
		 {
			 checkParameter(this.name(), size-1);
			 return size;
		 }
	 },

	 RANDOM
	 {
		public int get(int size)
		{
			 checkParameter(this.name(), size);
			 return (int)(Math.random() * size);
		}
	 };



	public abstract int get(int size);

	private static void checkParameter(String enumConstant, int size) throws IllegalArgumentException
	{
		if(size < 1)
		{
			throw new IllegalArgumentException("Size value cannot be less than one for enumConstant " + enumConstant + " inputed value was " + size);
		}//checks that the size parameter is at least one.
	}
/*
	public static void main(String args[])
	{
		Position a;
		Position b;
		Position c;
		a = FIRST;
		b = LAST;
		c = RANDOM;
		int size;
		size = 16;
		System.out.println(a.get(size) + " a get()");
		System.out.println(b.get(size) + " b get()");
		System.out.println(c.get(size) + " c get()");

		size = 20;
		System.out.println(a.get(size) + "   illegalArgument test");

		System.out.println("main end");
	}
*/
}