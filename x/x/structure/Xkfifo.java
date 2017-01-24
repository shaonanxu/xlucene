package x.structure;

import sun.misc.Unsafe;
import x.utils.BytesUtil;

/**
 * 
 * Linux kfifo
 * 
 * 
 * @author xushaonan
 *
 */
public class Xkfifo {
	
	private Unsafe unsafe = Unsafe.getUnsafe(); 
	private final long address_0;
	
	public Xkfifo(int size){
		if((size & (size-1)) > 0){
			size = BytesUtil.roundupPowOf2(size);
		}
		
		address_0 = unsafe.allocateMemory(size);
		
	}
	
	
	
	
	public void free(){
		unsafe.freeMemory(address_0);
	}

}
