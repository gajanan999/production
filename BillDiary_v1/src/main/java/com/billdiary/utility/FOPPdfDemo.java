package com.billdiary.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FOPPdfDemo {
	
	public static final String RESOURCES_DIR;
	public static final String OUTPUT_DIR;
	
	@Autowired
	GeneralUitilies utility;
	
	
	static {
		//RESOURCES_DIR = "src//main//resources//";
		RESOURCES_DIR="C://bill";
		//OUTPUT_DIR = "src//main//resources//output//";
		OUTPUT_DIR="C://bill";
	}
	
	public static void main(String[] args) {
		FOPPdfDemo fOPPdfDemo = new FOPPdfDemo();
		fOPPdfDemo.convertToPDF();
			
	}

	/**
	 * Method that will convert the given XML to PDF
	 * 
	 * @throws IOException
	 * @throws FOPException
	 * @throws TransformerException
	 */
	public void convertToPDF(){
		// the XSL FO file
		
		OutputStream out=null;
		try {
			//GeneralUitilies utility=new GeneralUitilies();	
			File xsltFile =utility.getPDFTemplate();
			// the XML file which provides the input
			StreamSource xmlSource = new StreamSource(new File(RESOURCES_DIR + "//Employees.xml"));
			// create an instance of fop factory
			FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
			// a user agent is needed for transformation
			FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
			// Setup output
			out = new java.io.FileOutputStream(OUTPUT_DIR + "//Bill.pdf");
			// Construct fop with desired output format
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

			// Setup XSLT
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

			// Resulting SAX events (the generated FO) must be piped through to
			// FOP
			Result res = new SAXResult(fop.getDefaultHandler());

			// Start XSLT transformation and FOP processing
			// That's where the XML is first transformed to XSL-FO and then
			// PDF is created
			transformer.transform(xmlSource, res);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * This method will convert the given XML to XSL-FO
	 * 
	 * @throws IOException
	 * @throws FOPException
	 * @throws TransformerException
	 */
	public void convertToFO() throws IOException, FOPException, TransformerException {
		// the XSL FO file
				URL url = getClass().getResource("/files/template.xsl");
				File xsltFile = new File(url.getPath());

		/*
		 * TransformerFactory factory = TransformerFactory.newInstance();
		 * Transformer transformer = factory.newTransformer(new
		 * StreamSource("F:\\Temp\\template.xsl"));
		 */

		// the XML file which provides the input
		StreamSource xmlSource = new StreamSource(new File(RESOURCES_DIR + "//Employees.xml"));

		// a user agent is needed for transformation
		/* FOUserAgent foUserAgent = fopFactory.newFOUserAgent(); */
		// Setup output
		OutputStream out;

		out = new java.io.FileOutputStream(OUTPUT_DIR + "temp.fo");

		try {
			// Setup XSLT
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

			// Resulting SAX events (the generated FO) must be piped through to
			// FOP
			// Result res = new SAXResult(fop.getDefaultHandler());

			Result res = new StreamResult(out);

			// Start XSLT transformation and FOP processing
			transformer.transform(xmlSource, res);

			// Start XSLT transformation and FOP processing
			// That's where the XML is first transformed to XSL-FO and then
			// PDF is created
			transformer.transform(xmlSource, res);
		} finally {
			out.close();
		}
	}

}


