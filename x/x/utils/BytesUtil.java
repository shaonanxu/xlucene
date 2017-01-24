package x.utils;

public class BytesUtil {
	
	/**
	 *   __asm__("bsrl %1,%0\n\t"
            "jnz 1f\n\t"
            "movl $-1,%0\n"
            "1:" : "=r" (r) : "rm" (x));
    	return r+1;
	 */
	public static int roundupPowOf2(int v){
		int pos = 0;
		if((v & 0xffff0000) != 0){
			v = v >>> 16;
			pos += 16;
		}
		while(v != 0){
			v = v >> 1;
			pos ++;
		}
		return 1<<pos;
	}
	
	public static boolean isPowOf2(int v){
		return (v & (v-1)) == 0;
	}
	
	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(0x000f0000));
		System.out.println(Integer.toBinaryString(roundupPowOf2(0x000f0000)));
		System.out.println(roundupPowOf2(0x000f0000));
	}
}
