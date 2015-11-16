package br.com.roberto.mediaStore.utils;

import javax.swing.text.AttributeSet;  
import javax.swing.text.BadLocationException;  
import javax.swing.text.PlainDocument;  
  
public class TamanhoMaximo extends PlainDocument {  
   private static final long serialVersionUID = 7448914412099931831L;
	private int maxLength;  
    private boolean onlyDigits; 
  
    public TamanhoMaximo(int maxlen, boolean onlyDigits) {  
        super();  
          
        if (maxlen <= 0)  
            throw new IllegalArgumentException("You must specify a maximum length!");  
          
        maxLength = maxlen;  
        this.onlyDigits = onlyDigits;
    }  
  
    @Override  
    public void insertString(int offset, String str, AttributeSet attr)   
    throws BadLocationException {  
        if (str == null || getLength() == maxLength)  
            return;          
        
        if (onlyDigits && !Character.isDigit(str.toCharArray()[0])){
        	return;         	
        }
  
        int totalLen = (getLength() + str.length());  
        if (totalLen <= maxLength) {  
            super.insertString(offset, str, attr);  
            return;  
        }  
          
        String newStr = str.substring(0, (maxLength - getLength()));  
        super.insertString(offset, newStr, attr);  
    }  
}  