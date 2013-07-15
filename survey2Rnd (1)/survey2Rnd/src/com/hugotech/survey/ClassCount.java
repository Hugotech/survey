package com.hugotech.survey;

public class ClassCount {
	
	static public int i = 0;
	
	public ClassCount() {
		i = DataStore.getQuestionCount();
	}

		
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
	
}
