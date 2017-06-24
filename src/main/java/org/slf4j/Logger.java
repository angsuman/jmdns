// Licensed under Apache License version 2.0
package org.slf4j; // Mimicked slf4j package structure to avoid changing existing codebase or usage

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Handler;
/** 
 * Replacement for slf4j Logger. Uses java.util.logging framework.
 */
public class Logger {
	private static java.util.logging.Logger logger;
	// DO NOT CHANGE
	public Logger(java.util.logging.Logger l) { logger = l; }
	
	// DO NOT CHANGE
	/**
	 * Clean room implementation of slf4j delayed evaluation of Strings
	 */
	public void log(Level level, String message, Object ... args) {
		if(logger.isLoggable(level)) {
			int aL = args.length;
			int index = 0;
			StringBuilder b = new StringBuilder();
			StringTokenizer st = new StringTokenizer(message, "{}");
			while(st.hasMoreTokens()) {
				if(index < aL) b.append(args[index++]);
				b.append(st.nextToken());				
			}
			logger.log(level, b.toString());
		}
	}
	
	// Add additional mapping methods below
	public void addHandler(Handler handler) { logger.addHandler(handler); }
	public void setLevel(Level l) { logger.setLevel(l); }
	
	public boolean isInfoEnabled()  { return logger.isLoggable(Level.INFO); }
	public boolean isErrorEnabled() { return logger.isLoggable(Level.SEVERE); }
	public boolean isDebugEnabled() { return logger.isLoggable(Level.FINE);	}
	public boolean isTraceEnabled() { return logger.isLoggable(Level.FINEST); }
	
	public void  info(String message, Object ... args) { log(Level.INFO,    message, args);	}
	public void debug(String message, Object ... args) { log(Level.FINE,    message, args); }
	public void  warn(String message, Object ... args) { log(Level.WARNING, message, args); }
	public void error(String message, Object ... args) { log(Level.SEVERE,  message, args); }
	public void trace(String message, Object ... args) { log(Level.FINEST,  message, args); }
}