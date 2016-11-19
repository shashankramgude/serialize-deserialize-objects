package genericCheckpointing.util;

import java.io.*;

public class FileProcessor {

	private String fileName;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileNameIn) {
		fileName = fileNameIn;

	}

	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	public void setBufferedReader(BufferedReader bufferedReaderIn) {
		bufferedReader = bufferedReaderIn;
	}

	public BufferedWriter getBufferedWriter() {
		return bufferedWriter;
	}

	public void setBufferedWriter(BufferedWriter bufferedWriterIn) {
		bufferedWriter = bufferedWriterIn;
	}

	public FileProcessor() {

	}

	/**
	 * This constructor creates new bufferedReader
	 * 
	 * @param file
	 *            name.
	 * @return Nothing.
	 * @exception IOException
	 *                on problem in closing bufferedReader and
	 *                FileNotFOundException on file not found.
	 */
	public FileProcessor(String fileNameIn, String operation) {
		fileName = fileNameIn;
		try {
			switch (operation) {
			case "R":
				this.bufferedReader = new BufferedReader(new FileReader(
						fileName));
				break;

			case "W":
				this.bufferedWriter = new BufferedWriter(new FileWriter(
						fileName));
				break;
			default:
				break;
			}
		} catch (FileNotFoundException e) {
			System.err.println("No such file found.");
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Error in file operation.");
			e.printStackTrace();
			System.exit(1);
		} finally {

		}
	}

	/**
	 * This method reads line from file
	 * 
	 * @param Nothing
	 *            .
	 * @return line read.
	 * @exception Nothing.
	 */
	public String readLineFromFile() throws IOException {
		String output = null;
		output = bufferedReader.readLine();
		return output;
	}

	public void writeLineToFile(String output) throws IOException {
		bufferedWriter.write(output);
		bufferedWriter.newLine();
	}

	/**
	 * This method closes the bufferedReader
	 * 
	 * @param Nothing
	 *            .
	 * @return Nothing.
	 * @exception IOException
	 *                on problem in closing bufferedReader.
	 */
	public void closeBufferedReader() {
		try {
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			
		}
	}

	public void closeBufferedWriter() {
		try {
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			
		}
	}

	@Override
	public String toString() {
		return "File Name=" + fileName;
	}

}
