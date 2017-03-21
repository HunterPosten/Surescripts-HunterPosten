import java.io.File;
public class DirectoryLister implements FileProcessor
{
/*
		Hunter Posten
		November 22, 2016

		This class, when the run() method is called, prints a list of all the
		files and directories in a root directory.

		Class Variables:
			FileProcessor fileProcessor
				this variable contains the FileProcessor object used in this class

			rootDirectory
				the file object of the root direcotry in which the files and directories
				that are printed in the list reside.

			SimpleList<File> filesNotYetProcessed
				this simple list of file objects contains every file object in the root
				directory that has not yet been processed by the processFile() method.

		Constuctors:
			DirectoryLister(File rootDirectory)
				this constructor initializes the rootDirectory instance varible to the
				inputted root directory in addition to initializing the filesNotYetProcessed
				list. This constructor will throw an IllegalArgumentException if the inputted
				rootDirectory is not a valid directory.

			DirectoryLister(File rootDirectory, FileProcessor fileProcessor)
				this constructor initializes the rootDirectory instance varible to the
				inputted root directory in addition to initializing the filesNotYetProcessed
				list. this constructor also initializes the fileProcessor instance variable
				to the inputted fileProcessor.

		Class Methods:
			FileProcessor getFileProcessor()
				this acessor method returns the fileProcessor instance varible

			SimpleList<File> getListOfUnprocessedFiles()
				this private accesor method returns the simple list containing all the unprocessed files.

			File getRootDirectory
				this accesor method returns the File rootDirectory instance varible

			void processFile()
				this method recieves an File object and determines if the object is a file or a directory.
				if it is a file then this method will print "File   (object filepath)" or if the object is a
				directory the the method will print "Directory    (Object filepath)".

			void run()
				this method takes the Simplelist of filesNotYetProcessed and examines each element
				in the list to determine if the element is a null file. if the element is not null then it
				is passed to the processFile() method. additionally this method prints the first line of the
				directory list.


*/

	private FileProcessor fileProcessor;
	private File rootDirectory;
	private SimpleList<File> filesNotYetProcessed;

	public DirectoryLister(File rootDirectory)throws IllegalArgumentException
	{
		if(rootDirectory == null)
		{
			throw new IllegalArgumentException("DirectoryLister was passed null. Directory Lister must be passed an existing directory");
		}

		if(!rootDirectory.isDirectory())
		{
			throw new IllegalArgumentException(rootDirectory + " is not a valid root Directory.");
		}

		this.rootDirectory = rootDirectory;
		this.filesNotYetProcessed = new SimpleList<File>(Position.FIRST, Position.LAST);

	}

	public DirectoryLister(File rootDirectory, FileProcessor fileProcessor)throws IllegalArgumentException
	{
		if(rootDirectory == null)
		{
			throw new IllegalArgumentException("DirectoryLister was passed null. Directory Lister must be passed an existing directory");
		}

		if(!rootDirectory.isDirectory())
		{
			throw new IllegalArgumentException(rootDirectory + " is not a valid root Directory.");
		}
		this.rootDirectory = rootDirectory;
		this.fileProcessor = fileProcessor;
		this.filesNotYetProcessed = new SimpleList<File>(Position.FIRST, Position.LAST);
	}//overloaded constructor

	public FileProcessor getFileProcessor()
	{
		return this.fileProcessor;
	}


	private SimpleList<File> getListOfUnprocessedFiles()
	{
		return this.filesNotYetProcessed;
	}//getListOfUnprocessedFiles

	public File getRootDirectory()
	{
		return this.rootDirectory;
	}//getRootDirectory

	public void processFile(File file)throws IllegalArgumentException
	{
		if(!file.exists())
		{
			throw new IllegalArgumentException("inputted file " + file.getName() + " does not exist");
		}

		if(file == null)
		{
			throw new IllegalArgumentException("inputted value " + file.getName() + " cannot be null");
		}
		if(file.isDirectory())
		{
			System.out.print("directory		");
		}//if file is a directory
		else if(file.isFile())
		{
			System.out.print("file			");
		}//if file is a file
		System.out.println(file.getPath());
	}//processFile

	public void run()
	{
		File entry;
		int size;
		this.getListOfUnprocessedFiles().put(this.getRootDirectory().listFiles());
		//System.out.println("Directory	" + this.getRootDirectory().getAbsolutePath());
		while(!filesNotYetProcessed.isEmpty())
		{
			entry = this.getListOfUnprocessedFiles().take();
			processFile(entry);
		}//while loop
	}//run



	public static void main(String[] args)throws Exception
	{
		File testFile;
		File testFile2;
		DirectoryLister lister;
		DirectoryLister lister2;
		testFile = new File("D:/current semester/programing/emptyfolder");
		testFile2 = new File("C:");
		System.out.println("construct below");

		lister = new DirectoryLister(testFile);
		System.out.println("lister successfully constructed");
		System.out.println("");

		System.out.println("construct2 below");
		lister2 = new DirectoryLister(testFile2);
		System.out.println("lister successfully constructed");
		System.out.println("");


		System.out.println("lister.run() try catch below(testFile1)");
		lister.run();

		System.out.println("lister.run() try catch below(testFile2)");
		lister.run();


	}
}//class