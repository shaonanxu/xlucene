package x.structure;

import sun.misc.Unsafe;

public class XRingBuffer {

	private static final int BUFFER_PAD;
	private static final long REF_ARRAY_BASE;
	private static final int REF_ELEMENT_SHIFT;
	private static Unsafe unsafe = Unsafe.getUnsafe();

	static {
		final int scale = unsafe.arrayIndexScale(Object[].class);
		System.out.println(unsafe.arrayBaseOffset(Object[].class));
		System.out.println(scale);
		if (4 == scale) {
			REF_ELEMENT_SHIFT = 2;
		} else if (8 == scale) {
			REF_ELEMENT_SHIFT = 3;
		} else {
			throw new IllegalStateException("Unknown pointer size");
		}
		BUFFER_PAD = 128 / scale;
		// Including the buffer pad in the array base offset
		REF_ARRAY_BASE = unsafe.arrayBaseOffset(Object[].class)
				+ (BUFFER_PAD << REF_ELEMENT_SHIFT);
	}

	public static void main(String[] args) {
		System.out.println();
	}
}
