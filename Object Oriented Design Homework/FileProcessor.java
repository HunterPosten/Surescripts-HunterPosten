import java.io.*;
public interface FileProcessor
{
/*
	Hunter Posten
	November 22, 2016

	this interface allows objects to process files.

	Interface:
		processFile
			this interface takes a file object and
			returns nothing.
*/
	public void processFile(File file);
}//interface