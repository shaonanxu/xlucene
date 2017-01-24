package x.utils;

import sun.reflect.Reflection;

public class Test {
	
	
	public static void main(String[] args) {
		System.out.println(Reflection.getCallerClass(2).getSimpleName());
	}

}
