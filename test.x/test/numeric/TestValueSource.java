package test.numeric;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.AtomicReaderContext;
import org.apache.lucene.index.BinaryDocValues;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.DocsEnum;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;

public class TestValueSource {
	
	public static void main(String[] args) throws IOException {
		
		RAMDirectory d = new RAMDirectory();
		IndexWriterConfig config = null; //new IndexWriterConfig(Version.LUCENE_4_9, new StandardAnalyzer(Version.LUCENE_4_9));
		IndexWriter iw = new IndexWriter(d, config);
		
		
		addDoc(iw);
		iw.close();
		IndexReader ir = DirectoryReader.open(d);
//		IndexSearcher is = new IndexSearcher(ir);
		for(AtomicReaderContext arc : ir.leaves()){
			Terms terms = arc.reader().terms("a");
			System.out.println(terms.size());
			final TermsEnum termsEnum = terms.iterator(null);
			BytesRef bref = termsEnum.next();
			System.out.println(bref.utf8ToString());
			DocsEnum denum = termsEnum.docs(null, null);
			System.out.println(denum.nextDoc());
			bref = termsEnum.next();
			System.out.println(bref.utf8ToString());
			denum = termsEnum.docs(null, null);
			System.out.println(denum.nextDoc());
			bref = termsEnum.next();
			System.out.println(bref.utf8ToString());
			denum = termsEnum.docs(null, null);
			System.out.println(denum.nextDoc());
			bref = termsEnum.next();
			System.out.println(bref.utf8ToString());
			denum = termsEnum.docs(null, null);
			System.out.println(denum.nextDoc());
			
			BinaryDocValues values = FieldCache.DEFAULT.getTerms(arc.reader(), "a", false);
			System.out.println(values.get(0).utf8ToString());
		}
		
		
	}

	private static void addDoc(IndexWriter iw) throws IOException {
		Document d = new Document();
		IndexableField f = new StringField("a", "test1", Store.YES);
//		System.out.println(f.fieldType().docValueType());
		d.add(f);
		d.add(new StringField("a", "test2", Store.YES));
		d.add(new TextField("a", "test3", Store.YES));
		iw.addDocument(d);
		
		Document d1 = new Document();
		IndexableField f1 = new StringField("a", "test", Store.YES);
		d1.add(f1);
		iw.addDocument(d1);
	}

}
