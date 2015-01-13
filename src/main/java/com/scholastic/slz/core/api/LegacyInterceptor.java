/**
 * 
 */
package com.scholastic.slz.core.api;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.spi.interception.MessageBodyReaderContext;
import org.jboss.resteasy.spi.interception.MessageBodyReaderInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author snehalata.raulo
 * 
 */
@Provider
@ServerInterceptor
public class LegacyInterceptor implements MessageBodyReaderInterceptor {
	Logger logger = LoggerFactory.getLogger(LegacyInterceptor.class);

	@Override
	public Object read(MessageBodyReaderContext ctx) throws IOException,
			WebApplicationException {

		long start = System.currentTimeMillis();

		Object result = ctx.proceed();

		logger.info(String.format("Read mediaType %s as %s in %d ms.", ctx
				.getMediaType().toString(), ctx.getType().getName(),
				System.currentTimeMillis() - start));

		return result;

	}

}
