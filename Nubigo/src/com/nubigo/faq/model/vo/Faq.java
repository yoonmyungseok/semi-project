package com.nubigo.faq.model.vo;

public class Faq {
	private String faqTitle;
	private String faqContent;
	public Faq() {
		super();
	}
	public Faq(String faqTitle, String faqContent) {
		super();
		this.faqTitle = faqTitle;
		this.faqContent = faqContent;
	}
	public String getFaqTitle() {
		return faqTitle;
	}
	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}
	public String getFaqContent() {
		return faqContent;
	}
	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}
	@Override
	public String toString() {
		return "Faq [faqTitle=" + faqTitle + ", faqContent=" + faqContent + "]";
	}
	
	
}
