package co.ind.sax.apps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class DepositProductRatesMain extends DefaultHandler {

	private String depositProductReatesXML;

	public DepositProductRatesMain(String depositProductReatesXML) {
		this.depositProductReatesXML = depositProductReatesXML;
		parseDocument();
	}

	private void parseDocument() {
		XMLReader reader = null;
		try {
			reader = XMLReaderFactory.createXMLReader();
		} catch (SAXException e1) {
			e1.printStackTrace();
		}
		
		try {
			//filename is filepath string
			BufferedReader br = new BufferedReader(new FileReader(new File(depositProductReatesXML)));
			String line;
			StringBuilder sb = new StringBuilder();

			while((line=br.readLine())!= null){
			    sb.append(line.trim());
			}
			//System.out.println(sb.toString());
			DepositProductRateHandler defaultProductRateHandler = new DepositProductRateHandler();
			reader.setContentHandler(defaultProductRateHandler);
			//reader.setErrorHandler(new SimpleErrorHandler());
			InputSource source = new InputSource(new StringReader(sb.toString()));
			//new InputSource(new FileInputStream(new File(defaultProductReatesXML)))
			reader.parse(source);
			
			for ( DepositProductRate depositProductRate : defaultProductRateHandler.getDepositRates().getDepositProductRatesLst() ) {
				System.out.println(depositProductRate);
			}
		} catch (RuntimeException e) {
			System.out.println("ParserConfig error" + e.getMessage());
		} catch (SAXException e) {
			System.out.println("SAXException : xml not well formed");
		} catch (IOException e) {
			System.out.println("IO error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DepositProductRatesMain("depositProductRates.xml");
	}
}
