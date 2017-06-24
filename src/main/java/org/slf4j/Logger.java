package org.slf4j;

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Handler;

public class Logger {
	private static java.util.logging.Logger logger;
	
	public Logger(java.util.logging.Logger l) {
		logger = l;
	}
	
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
	
	public void addHandler(Handler handler) { logger.addHandler(handler); }
	
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