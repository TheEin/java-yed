package de.adrianwilke.javayed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Io {

	private Io() {
	}

	/**
	 * Writes XML document to file.
	 */
	public static void write(Document document, File file) throws IOException, TransformerException {
		write(document, new FileOutputStream(file));
	}

	/**
	 * Writes XML document to OutputStream.
	 */
	public static void write(Document document, OutputStream out) throws IOException, TransformerException {
		DOMSource domSource = new DOMSource(document);
		StreamResult streamResult = new StreamResult(new OutputStreamWriter(out, StandardCharsets.UTF_8));

		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "1");

		transformer.transform(domSource, streamResult);
	}

	/**
	 * Reads XML document from file.
	 */
	public static Document read(File file)
			throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(new FileInputStream(file));
	}
}