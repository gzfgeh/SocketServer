package com.gzfgeh.socketserver;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MyTextLineEncoder implements ProtocolEncoder {

	public void dispose(IoSession session) throws Exception {
		
	}

	public void encode(IoSession session, Object object, ProtocolEncoderOutput out)
			throws Exception {
		String s = null;
		if (object instanceof String){
			s = (String) object;
		}
		
		if (s != null){
			CharsetEncoder charsetEncoder = (CharsetEncoder) session.getAttribute("encoder");
			if (charsetEncoder == null){
				charsetEncoder = Charset.defaultCharset().newEncoder();
				session.setAttribute("encoder", charsetEncoder);
			}
			
			IoBuffer ioBuffer = IoBuffer.allocate(s.length());
			ioBuffer.setAutoExpand(true);
			ioBuffer.putString(s, charsetEncoder);
			ioBuffer.flip();
			out.write(ioBuffer);
		}
	}

}
