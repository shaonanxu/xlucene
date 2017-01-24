package x.utils;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

import sun.misc.Unsafe;

public class JvmUtils {
	
	private static final Unsafe THE_UNSAFE;

	static {
		try {
			final PrivilegedExceptionAction<Unsafe> action = new PrivilegedExceptionAction<Unsafe>() {
				public Unsafe run() throws Exception {
					Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
					theUnsafe.setAccessible(true);
					return (Unsafe) theUnsafe.get(null);
				}
			};

			THE_UNSAFE = AccessController.doPrivileged(action);
		} catch (Exception e) {
			throw new RuntimeException("Unable to load unsafe", e);
		}
	}

	public static Unsafe xunsafe(){
		return THE_UNSAFE;
	}

	public static void main(String[] args) {
		System.out.println(xunsafe().arrayBaseOffset(Object[].class));
	}
}
