package co.ind.sax.apps;

import java.util.Date;

public class DepositProductRate {

	private String productId;
	private String productName;
	private String accountCategory;
	private String interestPlan;
	private String term;
	private String termId;
	private String marketSegmentCode;
	private String marketSegmentName;
	private String zipRangeLow;
	private String zipRangeHigh;
	private String balanceLevel;
	private String interestRate;
	private String apy;
	private Date rateEffectiveDate;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getAccountCategory() {
		return accountCategory;
	}

	public void setAccountCategory(String accountCategory) {
		this.accountCategory = accountCategory;
	}

	public String getInterestPlan() {
		return interestPlan;
	}

	public void setInterestPlan(String interestPlan) {
		this.interestPlan = interestPlan;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getMarketSegmentCode() {
		return marketSegmentCode;
	}

	public void setMarketSegmentCode(String marketSegmentCode) {
		this.marketSegmentCode = marketSegmentCode;
	}

	public String getMarketSegmentName() {
		return marketSegmentName;
	}

	public void setMarketSegmentName(String marketSegmentName) {
		this.marketSegmentName = marketSegmentName;
	}

	public String getZipRangeLow() {
		return zipRangeLow;
	}

	public void setZipRangeLow(String zipRangeLow) {
		this.zipRangeLow = zipRangeLow;
	}

	public String getZipRangeHigh() {
		return zipRangeHigh;
	}

	public void setZipRangeHigh(String zipRangeHigh) {
		this.zipRangeHigh = zipRangeHigh;
	}

	public String getBalanceLevel() {
		return balanceLevel;
	}

	public void setBalanceLevel(String balanceLevel) {
		this.balanceLevel = balanceLevel;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getApy() {
		return apy;
	}

	public void setApy(String apy) {
		this.apy = apy;
	}

	public Date getRateEffectiveDate() {
		return rateEffectiveDate;
	}

	public void setRateEffectiveDate(Date rateEffectiveDate) {
		this.rateEffectiveDate = rateEffectiveDate;
	}

	@Override
	public String toString() {
		return "DefaultProductRate [productId=" + productId + ", productName="
				+ productName + ", accountCategory=" + accountCategory
				+ ", interestPlan=" + interestPlan + ", term=" + term
				+ ", termId=" + termId + ", marketSegmentCode="
				+ marketSegmentCode + ", marketSegmentName="
				+ marketSegmentName + ", zipRangeLow=" + zipRangeLow
				+ ", zipRangeHigh=" + zipRangeHigh + ", balanceLevel="
				+ balanceLevel + ", interestRate=" + interestRate + ", apy="
				+ apy + ", rateEffectiveDate=" + rateEffectiveDate + "]";
	}

}
