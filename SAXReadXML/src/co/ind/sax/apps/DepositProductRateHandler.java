package co.ind.sax.apps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DepositProductRateHandler extends DefaultHandler {
	private List<DepositProductRate> depositProductRateLst;
	private String tmpValue;
	private DepositProductRate depositProductRate;
	private SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
	
	public DepositProductRateHandler() {
		depositProductRateLst = new ArrayList<DepositProductRate>();
	}
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("@@ start document @");
	}

	@Override
	public void startElement(String s, String s1, String elementName,
			Attributes attributes) throws SAXException {
		if (elementName.equalsIgnoreCase("DepositProduct")) {
			depositProductRate = new DepositProductRate();
		}
	}

	@Override
	public void endElement(String s, String s1, String element)
			throws SAXException {
		if (element.equals("DepositProduct")) {
			depositProductRateLst.add(depositProductRate);
		}
		if (element.equalsIgnoreCase("productId")) {
			depositProductRate.setProductId(tmpValue);
		}
		if (element.equalsIgnoreCase("productName")) {
			depositProductRate.setProductName(tmpValue);
		}
		if (element.equalsIgnoreCase("accountCategory")) {
			depositProductRate.setAccountCategory(tmpValue);
		}
		if (element.equalsIgnoreCase("interestPlan")) {
			depositProductRate.setInterestPlan(tmpValue);
		}
		if (element.equalsIgnoreCase("term")) {
			depositProductRate.setTerm(tmpValue);
		}
		if (element.equalsIgnoreCase("termId")) {
			depositProductRate.setTermId(tmpValue);
		}
		if (element.equalsIgnoreCase("marketSegmentCode")) {
			depositProductRate.setMarketSegmentCode(tmpValue);
		}
		if (element.equalsIgnoreCase("marketSegmentName")) {
			depositProductRate.setMarketSegmentName(tmpValue);
		}
		if (element.equalsIgnoreCase("zipRangeLow")) {
			depositProductRate.setZipRangeLow(tmpValue);
		}
		if (element.equalsIgnoreCase("zipRangeHigh")) {
			depositProductRate.setZipRangeHigh(tmpValue);
		}
		if (element.equalsIgnoreCase("balanceLevel")) {
			depositProductRate.setBalanceLevel(tmpValue);
		}
		if (element.equalsIgnoreCase("interestRate")) {
			depositProductRate.setInterestRate(tmpValue);
		}
		if (element.equalsIgnoreCase("apy")) {
			depositProductRate.setApy(tmpValue);
		}
		if (element.equalsIgnoreCase("rateEffectiveDate")) {
			try {
				depositProductRate.setRateEffectiveDate(sdf.parse(tmpValue));
			} catch (ParseException e) {
				System.out.println("date parsing error");
			}
		}
	}

	@Override
	public void characters(char[] ac, int i, int j) throws SAXException {
		tmpValue = new String(ac, i, j);
	}
	
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("@@ end document @");
	}

	public DepositProductRates getDepositRates() {
		DepositProductRates depositProductRates = new DepositProductRates();
		depositProductRates.setDepositProductRatesLst(depositProductRateLst);
		return depositProductRates;
	}
}
