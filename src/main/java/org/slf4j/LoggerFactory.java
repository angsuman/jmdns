package org.slf4j;

import java.util.HashMap;
import java.util.Map;

/** Replacement for slf4j package */
public class LoggerFactory {
	private static final Map<String, Logger> loggers = new HashMap<String, Logger>();
	
	public static Logger getLogger(String name) {
		Logger l = loggers.get(name);
		if(l == null) {
			l = new Logger(java.util.logging.Logger.getLogger(name));
			loggers.put(name, l);
		}
		return l;
	}
}