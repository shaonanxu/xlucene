package test.fst;

import java.io.IOException;

import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.BytesRefBuilder;
import org.apache.lucene.util.IntsRefBuilder;
import org.apache.lucene.util.fst.Builder;
import org.apache.lucene.util.fst.ByteSequenceOutputs;
import org.apache.lucene.util.fst.FST;
import org.apache.lucene.util.fst.Util;

public class TestBuilder {
	
	public static void main(String[] args) throws IOException {
		Builder<BytesRef> builder = new Builder(FST.INPUT_TYPE.BYTE1, ByteSequenceOutputs.getSingleton());
		BytesRefBuilder brb = new BytesRefBuilder();
		brb.copyChars("abc");
		IntsRefBuilder scratchInts = new IntsRefBuilder();
		byte[][] outputValues = new byte[][]{{1,2}};
		builder.add(Util.toIntsRef(brb.get(), scratchInts), new BytesRef(outputValues[0]));
		FST<BytesRef> fst = builder.finish();
		
	}

}
