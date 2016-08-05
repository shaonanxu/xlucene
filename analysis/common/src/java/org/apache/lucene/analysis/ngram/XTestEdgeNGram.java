package org.apache.lucene.analysis.ngram;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.util.Version;

public class XTestEdgeNGram {
	
	public static void main(String[] args) throws IOException {
//		EdgeNGramTokenizer tokenizer = new EdgeNGramTokenizer(new StringReader("abcdefg"), 1, 3);
		NGramTokenizer tokenizer = new NGramTokenizer(Version.LUCENE_4_9, new StringReader("abcdefg"));
		tokenizer.reset();
		final CharTermAttribute termAtt = tokenizer.addAttribute(CharTermAttribute.class);
	    final OffsetAttribute offsetAtt = tokenizer.addAttribute(OffsetAttribute.class);
		while(tokenizer.incrementToken()){
			System.out.println(termAtt.toString());
		}
		tokenizer.end();
		tokenizer.close();
	}

}
