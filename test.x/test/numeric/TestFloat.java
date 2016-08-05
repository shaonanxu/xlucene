package test.numeric;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FloatField;
import org.apache.lucene.index.BinaryDocValues;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;

public class TestFloat {
	
	static void testBytesRef2Float() throws IOException{
		RAMDirectory d = new RAMDirectory();
		IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, new StandardAnalyzer());
		IndexWriter iw = new IndexWriter(d, conf);
		iw.addDocument(doc());
		iw.close();
		
		IndexReader reader = DirectoryReader.open(d);
		BinaryDocValues bdvUids = FieldCache.DEFAULT.getTerms(reader.leaves().get(0).reader(), "test", false);
		BytesRef ref = bdvUids.get(0);
		System.out.println(FieldCache.NUMERIC_UTILS_FLOAT_PARSER.parseFloat(ref));
	}
	
	static Document doc(){
		Document doc = new Document();
		FloatField ff = new FloatField("test", 1.0f, Store.YES);
		doc.add(ff);
		return doc;
	}
	
	public static void main(String[] args) throws Exception{
		testBytesRef2Float();
	}

}
