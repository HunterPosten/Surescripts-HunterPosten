import java.io.*;

public class CountFilesAndDirectories implements FileFilter, FileProcessor
{
/*
	Hunter Posten
	November 22, 2016

	this class opens a root directory and counts how many files and how many
	directories are contained within.

	Class Variables:
		long directoryCount
			this variable stores the number of directories counted

		long fileCount
			this varible stores the number of files counted

		FileFilter fileFilter
			this varible stores the fileFilter that is used to file out certain
			types of files during counting.

	Constructors:
		CountFilesAndDirectories()
			this constructor passes a null value to CountFilesAndDirectories
			constructor so that there the results of the counting will not be
			affected by any file filters because the FileFilter fileFilter
			instance varible will be set to null.

		CountFilesAndDirectories(FileFilter fileFilter)
			this constructor is passed a fileFileter object and initializes
			the FileFilter fileFilter instance varible to this parameter.
			this constructor also initializs the directoryCount and fileCount
			instance varibles to 0 as there has been no counting yet.

	Class Methods:
		long getFileCount()
			acessor method that returns the value of the long fileCount
			instance variable.

		long getDirectoryCount()
			acessor method that return the value of the long directoryCount
			instance variable.

		FileFilter getFileFilter()
			acessor method the returns the value of the FileFilter fileFilter
			instance variable.

		void processFile()
			this file performs the counting of the files and directories in the
			root directory. processFile() only counts files and directories that,
			are not null and are accepted by the filefilter ( checked by passing
			each object to the public boolean accept(File file) method. after every
			element has been processed this method prints a message stating how many
			files and directories are contained in this particular root directory.

		boolean accept(File file)
			this method examines file objects and check if their file extension is
			within the fileFilter instance variable, if so then accept returns true
			allowing that object to be counted and false if it is not contained in the
			fileFilter. if the fileFilter is null than accept(File file) will automatically
			return true.

*/
	private long directoryCount;
	private long fileCount;
	private FileFilter fileFilter;

	public CountFilesAndDirectories()
	{
		this(null);
	}//CountFilesAndDirectories constructor -- no parameters

	public CountFilesAndDirectories(FileFilter fileFilter)
	{
		this.directoryCount = 0;
		this.fileCount = 0;
		this.fileFilter = fileFilter;
	}//CountFilesAndDirectories constructor

	public long getFileCount()
	{
		return this.fileCount;
	}

	public long getDirectoryCount()
	{
		return this.directoryCount;
	}

	public FileFilter getFileFilter()
	{
		return this.fileFilter;
	}//getFileFilter

	public void processFile(File file)throws IllegalArgumentException
	{
		if(!file.exists())
		{
			throw new IllegalArgumentException("inputted file " + file.getName() + " Does not excist. Inputted file must excist.");
		}
		File[] fileArray;
		fileArray = file.listFiles();
		for(int i = 0; i < fileArray.length; i++)
		{
			if(this.accept(fileArray[i]))
			{
				if(fileArray[i].isDirectory())
				{
					this.directoryCount = this.directoryCount + 1;
				}//is a directory
				else if(fileArray[i].isFile())
				{
					this.fileCount = this.fileCount + 1;
				}//is a file
			}//if accepted
		}//for loop
		System.out.println("in file " + file.getName() + " there are " + this.getDirectoryCount() + " directories, and " + this.getFileCount() + " files.");
	}//processFile

	public boolean accept(File file)throws IllegalArgumentException
	{
		if(!file.exists())
		{
			throw new IllegalArgumentException("inputted file " + file.getName() + " Does not exist. Inputted file must excist.");
		}
		String fileExtension;
		fileExtension = "";
		int fileExtensionIndex;
		if(this.getFileFilter() == null)
		{
			return true;
		}//if there is no file filter
		else
		{
			fileExtensionIndex = file.getPath().lastIndexOf('.');
			fileExtension = file.getPath().substring(fileExtensionIndex, fileExtension.length()-1);
			if(this.getFileFilter().toString().indexOf(fileExtension) >= 0)
			{
				return true;
			}//returns true if the tested file extension is in the file filter
			else
			{
				return false;
			}//returns false if the tested file extension is not in the file filter
		}//if there is a file filter
	}//accept

}//CountFilesAndDirectories class